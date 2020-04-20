package com.bookreccs.dao;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookreccs.entity.Book;
import com.bookreccs.entity.User;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Book book) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(book);
	}
	
	@Override
	public Book findBook(String title, String author, String imageLink)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Book> query = currentSession.createQuery("from Book where title=:title and author=:author and imageLink=:imageLink", Book.class);
		
		query.setParameter("title", title);
		query.setParameter("author", author);
		query.setParameter("imageLink", imageLink);
		
		Book book = null; 
		
		try
		{
			book = query.getSingleResult();
		}
		catch(Exception e)
		{
			book = null;
		}
		
		return book;
	}
	
	@Override
	public Collection<User> getUserCollection(Book book)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		
		long id = book.getId();
		
		Query<User> query = currentSession.createQuery("select u from User u join u.books b where b.id=:bookId" , User.class);
		
		query.setParameter("bookId", id);
		
		Collection<User> userCollection = query.getResultList();
		
		return userCollection;
	}

}
