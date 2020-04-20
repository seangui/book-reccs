package com.bookreccs.service;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bookreccs.dto.tastedive.TasteDiveItemDto;
import com.bookreccs.entity.Book;

@Service
public class ReccsServiceImpl implements ReccsService {

	@Autowired
	private ReactorClientHttpConnector httpConnector;

	@Autowired
	private Environment env;

	@Value("${tastediveapi.key}")
	private String API_KEY;

	private final String BASE_URL = "https://tastedive.com";

	private final WebClient CLIENT;

	private Logger logger = Logger.getLogger(getClass().getName());

	public ReccsServiceImpl() {
		this.CLIENT = WebClient.builder().clientConnector(httpConnector).baseUrl(BASE_URL).build();
	}

	@Override
	public TasteDiveItemDto getReccs(Collection<Book> bookCollection) {

		List<String> parameters = new LinkedList<>();

		for (Book b : bookCollection)
			parameters.add("book:" + b.getTitle());
		
		String message = String.join(",", parameters);

		logger.info("Parameters = " + message);

		// We want 2 reccs for every 1 book in the collection
		int limit = bookCollection.size() * 2;

		logger.info("Accessing TasteDive API...");		
			
		TasteDiveItemDto resp = CLIENT
							.get()
							.uri(uriBuilder -> uriBuilder.path("/api/similar")
							.queryParam("q", String.join(",", parameters))
							.queryParam("limit", Integer.toString(limit))
							.queryParam("info", "1")
							.queryParam("k", API_KEY).build())
							.retrieve()
							.bodyToMono(TasteDiveItemDto.class)
							.block();
		
		return resp;
	}

}
