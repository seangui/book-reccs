package com.bookreccs.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Hibernate is an implementation of the Java Persistence API - which is why you only declare hibernate 
 * and not persistence api in your POM.xml file. 
 * 
 * We use persistence annotations so we can theoretically run our code on other JPA implementations.
 * 
 * Only when we need hibernate specific functionality we should use the hibernate annotations. 
 * @author sguiao
 *
 */
@Entity
@Table(name="book")
public class Book 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="author")
	private String author;
	
	@Column(name="imageLink")
	private String imageLink;
	
	@ManyToMany(
			fetch=FetchType.LAZY,
			cascade= {
					CascadeType.PERSIST, 
					CascadeType.MERGE,
					CascadeType.DETACH, 
					CascadeType.REFRESH
					}
			)
	@JoinTable(
			name="books_users",
			joinColumns=@JoinColumn(name="book_id"),
			inverseJoinColumns=@JoinColumn(name="user_id")
			)
	private Collection<User> users;
	
	public Book()
	{}
	
	// We don't add the id to the constructor bc it will be filled in 
	// by hibernate 
	public Book(String title, String author, String imageLink)
	{
		this.title = title; 
		this.author = author; 
		this.imageLink = imageLink; 
	}
	
	public Book(String title, String author, Collection<User> users, String imageLink)
	{
		this.title = title; 
		this.author = author; 
		this.users = users;
		this.imageLink = imageLink;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
}
