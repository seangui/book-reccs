package com.bookreccs.dao;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookreccs.dto.BookDto;
import com.bookreccs.entity.Book;
import com.bookreccs.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUsername(String theUsername) {
		// retrieves current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUsername);
		User theUser = null;

		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}
		
		return theUser;
	}

	@Override
	public void saveOrUpdate(User theUser) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theUser);
	}
	
	// We're constructing a HQL query to avoid the Lazy Exception 
	// and so we don't have to change lazy to eager fetching bc performance issues
	// HQL is a way we can retrieve the data and access it after the session is closed
	// without having to add @Transactional to our controller and keep it 
	// in our service layer 
	@Override
	public Collection<Book> getBookCollection(User user)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		
		long id = user.getId();
		
		Query<Book> query = currentSession.createQuery("select b from Book b join b.users u where u.id=:userId", Book.class);
		
		query.setParameter("userId", id);
		
		Collection<Book> bookCollection = query.getResultList(); 
		
		return bookCollection;
	}
}
