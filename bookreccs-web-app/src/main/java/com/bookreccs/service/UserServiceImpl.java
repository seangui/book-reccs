package com.bookreccs.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookreccs.dao.RoleDao;
import com.bookreccs.dao.UserDao;
import com.bookreccs.dto.BookDto;
import com.bookreccs.dto.UserDto;
import com.bookreccs.entity.Book;
import com.bookreccs.entity.Role;
import com.bookreccs.entity.User;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao; 
	
	@Autowired
	private BookService bookService;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder; 
		
	private Logger logger = Logger.getLogger(getClass().getName());
		
	
	@Override
	@Transactional 
	public User findByUserName(String username)
	{
		return userDao.findByUsername(username);
	}
	
	
	@Override
	@Transactional
	public User getCurrentUser()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String currentPrincipalName = authentication.getName();

		User user = userDao.findByUsername(currentPrincipalName);
		
		return user;
	}
	
	
	@Override
	@Transactional
	public void save(UserDto userDto)
	{
		// assign the user details to the user object
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setBooks(new ArrayList<Book>());
		
		// give user default role of "reader"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_READER")));
		
		// save user in the database 
		userDao.saveOrUpdate(user);
	}
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
	
	
	@Override
	@Transactional
	public Collection<Book> getBookCollection()
	{
		logger.info("Getting user book collection from DB");
		
		Collection<Book> books = userDao.getBookCollection(getCurrentUser());	
		
		return books;
	}
	
	
	@Override
	@Transactional
	public boolean containsBook(BookDto bookDto)
	{
		logger.info("Checking if user contains book...");
		
		Book book = bookService.findBook(bookDto);
		
		Collection<Book> bookCollection = getBookCollection();
			
		if(book == null)
			return false;
		
		return bookCollection.contains(book);
	}
	
	
	@Override
	@Transactional
	public void deleteBook(BookDto bookToDelete)
	{
		logger.info("Attempting to delete book...");
				
		Book book = bookService.findBook(bookToDelete);
		
		Collection<Book> bookCollection = getBookCollection();
		
		bookCollection.remove(book);
		
		getCurrentUser().setBooks(bookCollection);
		
		userDao.saveOrUpdate(getCurrentUser());
	}
	
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
