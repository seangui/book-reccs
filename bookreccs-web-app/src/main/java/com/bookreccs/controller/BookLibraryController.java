package com.bookreccs.controller;

import java.util.Collection;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookreccs.dto.BookDto;
import com.bookreccs.dto.ImageLinkDto;
import com.bookreccs.dto.ItemDto;
import com.bookreccs.dto.ItemListDto;
import com.bookreccs.entity.Book;
import com.bookreccs.entity.User;
import com.bookreccs.service.BookService;
import com.bookreccs.service.UserService;

@Controller
@RequestMapping("/library")
public class BookLibraryController {
	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	private Logger logger = Logger.getLogger(getClass().getName());

	@GetMapping("/")
	public String showBookLibrary(Model theModel) {

		Collection<Book> bookCollection = userService.getBookCollection();
		
		theModel.addAttribute("bookCollection", bookCollection);

		theModel.addAttribute("bookToDelete", new BookDto());

		return "book-library";
	}

	@PostMapping("/delete-confirm")
	public String confirmDelete(@ModelAttribute BookDto bookToDelete) {
		userService.deleteBook(bookToDelete);

		return "delete-confirm";
	}

	@GetMapping("/search-book")
	public String showSearchBook(Model theModel) {
		// The Item List DTO contains all our
		theModel.addAttribute("book", new BookDto());

		return "search-book";
	}

	@PostMapping("/search-results")
	public String showSearchResults(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult theBindingResult,
			Model theModel) {
		logger.info("Processing serach results");

		/*
		 * The @Valid annotation performs the validation that we established in the DTO
		 * on the bookDto Obj. The results are then placed in the bindingResult field to
		 * check for errors.
		 */
		if (theBindingResult.hasErrors()) {
			logger.info("Search Failed");
			return "search-book";
		}

		// Get Book Query from API
		String title = bookDto.getTitle();

		String author = bookDto.getAuthor();

		ItemListDto items = bookService.searchBook(title, author);
				
		if(items.getItems() == null)
			return "search-null";

		logger.info("Search Successful");

		// Create book dtos and add to the model to display
		BookDto[] bookDtoList = createBookList(cleanItems(items).getItems());

		theModel.addAttribute("bookList", bookDtoList);

		// Create a new attribute to send the added book to the confirmation page and
		// add to our DB
		theModel.addAttribute("bookToAdd", new BookDto());

		return "search-results";
	}

	@PostMapping("/confirm")
	public String addBook(@ModelAttribute("bookToAdd") BookDto bookToAdd) {		
		
		// Check if user already contains book in their library
		if(userService.containsBook(bookToAdd))
			return "book-already-added";
		
		// Save the book
		bookService.save(bookToAdd);
		
		return "add-confirm";
	}

	// Helper method
	public BookDto[] createBookList(ItemDto[] itemDtoList) {
		BookDto[] bookList = new BookDto[itemDtoList.length];

		// the .getAuthors()[0] is indexed at 1 because the API will return an array of
		// authors
		// but we're only interested in storing the main author aka the first author
		// that shows
		
		logger.info("Creating bookDto objects to store into list");
		
		for (int i = 0; i < itemDtoList.length; i++) {
			BookDto book = new BookDto(itemDtoList[i].getVolumeInfo().getTitle(),
					itemDtoList[i].getVolumeInfo().getAuthors()[0],
					itemDtoList[i].getVolumeInfo().getImageLinks().getSmallThumbnail());

			bookList[i] = book;
		}

		return bookList;
	}
	
	// Helper method to clean the JSON response to avoid NPE in case any fields are left blank
	public ItemListDto cleanItems(ItemListDto items)
	{
		logger.info("Cleaning JSON data");
		
		for(int i = 0; i < items.getItems().length; i++)
		{
			if(items.getItems()[i].getVolumeInfo().getTitle() == null)
				items.getItems()[i].getVolumeInfo().setTitle("Title not listed");
			
			if(items.getItems()[i].getVolumeInfo().getAuthors()[0] == null)
				items.getItems()[i].getVolumeInfo().setTitle("Author not listed");
			
			if(items.getItems()[i].getVolumeInfo().getImageLinks() == null)
			{
				items.getItems()[i].getVolumeInfo().setImageLinks(new ImageLinkDto());
				items.getItems()[i].getVolumeInfo().getImageLinks().setSmallThumbnail("https://www.wildhareboca.com/wp-content/uploads/sites/310/2018/03/image-not-available.jpg");
			}
		}
		
		return items;
	}
}
