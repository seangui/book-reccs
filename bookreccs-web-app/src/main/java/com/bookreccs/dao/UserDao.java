package com.bookreccs.dao;

import java.util.Collection;

import com.bookreccs.dto.BookDto;
import com.bookreccs.entity.Book;
import com.bookreccs.entity.User;

public interface UserDao {
	User findByUsername(String username);
	
	void saveOrUpdate(User user);
	
	Collection<Book> getBookCollection(User user);
}
