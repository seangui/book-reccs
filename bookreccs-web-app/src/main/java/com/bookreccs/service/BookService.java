package com.bookreccs.service;

import java.util.Collection;

import com.bookreccs.dto.BookDto;
import com.bookreccs.dto.ItemListDto;
import com.bookreccs.entity.Book;
import com.bookreccs.entity.User;

public interface BookService {
	ItemListDto searchBook(String title, String author); 
		
	void save(BookDto bookToAdd);
	
	Book findBook(BookDto book);

	Collection<User> getUserCollection(Book book);
}
