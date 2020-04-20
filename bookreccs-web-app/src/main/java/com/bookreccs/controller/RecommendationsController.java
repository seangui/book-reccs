package com.bookreccs.controller;

import java.util.Collection;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookreccs.dto.tastedive.Info;
import com.bookreccs.dto.tastedive.TasteDiveItemDto;
import com.bookreccs.entity.Book;
import com.bookreccs.service.ReccsService;
import com.bookreccs.service.UserService;


@Controller
@RequestMapping("/recommendations")
public class RecommendationsController {
	
	@Autowired
	private ReccsService reccsService;
	
	@Autowired
	private UserService userService;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@GetMapping("/")
	public String showRecommendations(Model theModel)
	{	
		Collection<Book> bookCollection = userService.getBookCollection();
		
		TasteDiveItemDto items = reccsService.getReccs(bookCollection);
		
		logger.info("Items from Taste Dive API Retrieved...");
		
		logger.info("Items: " + items.toString());
		
//		ArrayList<String> itemTitltes = new ArrayList<>();
//		
//		ArrayList<String> itemUrls = new ArrayList<>();
//		
//		// Gets the results titles and urls 
//		for (int i = 0; i < items.getSimilar().getResults().length; i++)
//		{
//			itemTitltes.add(items.getSimilar().getResults()[i].getName());
//			
//		}
//		
		Info[] results = items.getSimilar().getResults();

		theModel.addAttribute("reccs", results);
		
		return "book-recommendations";
	}
}
