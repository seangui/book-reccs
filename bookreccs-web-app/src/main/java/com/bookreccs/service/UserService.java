package com.bookreccs.service;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;

import com.bookreccs.dto.BookDto;
import com.bookreccs.dto.UserDto;
import com.bookreccs.entity.Book;
import com.bookreccs.entity.User;

public interface UserService extends UserDetailsService {
	
	User findByUserName(String username);
	
	void save(UserDto userDto);
	
	User getCurrentUser();
	
	Collection<Book> getBookCollection();
	
	void deleteBook(BookDto bookToDelete);

	boolean containsBook(BookDto book);

}
