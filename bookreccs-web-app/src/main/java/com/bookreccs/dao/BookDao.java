package com.bookreccs.dao;

import java.util.Collection;

import com.bookreccs.entity.Book;
import com.bookreccs.entity.User;

public interface BookDao 
{
	void save(Book book);
	
	Book findBook(String title, String author, String imageLink);

	Collection<User> getUserCollection(Book book);
}
