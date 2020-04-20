package com.bookreccs.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.bookreccs.dao.BookDao;
import com.bookreccs.dto.BookDto;
import com.bookreccs.dto.ItemListDto;
import com.bookreccs.entity.Book;
import com.bookreccs.entity.User;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private ReactorClientHttpConnector httpConnector;

	@Autowired
	private Environment env;

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private UserService userService;

	@Value("${googlebooksapi.key}")
	private String API_KEY;

	@Value("${googlebooksapi.fields}")
	private String FIELDS;
	
	private final String BASE_URL = "https://www.googleapis.com/books/v1/";

	private final WebClient CLIENT;

	private Logger logger = Logger.getLogger(getClass().getName());

	public BookServiceImpl() {
		this.CLIENT = WebClient.builder().clientConnector(httpConnector).baseUrl(BASE_URL).build();
	}

	@Override
	@Transactional
	public Book findBook(BookDto bookDto) {

		String title = bookDto.getTitle();

		String author = bookDto.getAuthor();

		String imageLink = bookDto.getImageLink();
		
		logger.info("Finding book entry in DB");

		return bookDao.findBook(title, author, imageLink);
	}

	@Override
	public ItemListDto searchBook(String title, String author) {
		String collectionName = "/volumes";
		String parameters = "q=intitle:" + title + "+inauthor:" + author + "&" + FIELDS + "&" + API_KEY;

		logger.info("Searching for Book");
		
		// We have no interest in making this a reactive web app
		// therefore we make use of the .block() method
		return CLIENT.get().uri(collectionName + "?" + parameters).retrieve().bodyToMono(ItemListDto.class).block();
	}
	
	@Override
	@Transactional
	public Collection<User> getUserCollection(Book book)
	{
		return bookDao.getUserCollection(book);
	}
	
	@Override
	@Transactional
	public void save(BookDto bookDto)
	{
		// Find if the book already exists in the DB 
		Book book = findBook(bookDto);
		
		logger.info("Finding book in DB...");
		
		// Get the logged in user 
		User currentUser = userService.getCurrentUser();
		
		
		if(book == null)
		{
			logger.info("Book not present in DB...");
			
			logger.info("Creating new Book Entity to catalog in DB...");
			
			book = new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getImageLink());
			
			ArrayList<User> userList = new ArrayList<>();
			
			userList.add(currentUser);
			
			book.setUsers(userList);
		}
		else
		{
			logger.info("Book found in DB and successfully fetched...");
			
			logger.info("Getting user colletion from Book");
			
			// get user collection for book 
			Collection<User> userCollection = getUserCollection(book);
			
			// Add user to books' user collection
			userCollection.add(currentUser);
			
			// update book's user collection
			book.setUsers(userCollection);
		}
		
		logger.info("Saving book to DB...");
		
		bookDao.save(book);
		
		logger.info("Save successful!!");		
	}
}
