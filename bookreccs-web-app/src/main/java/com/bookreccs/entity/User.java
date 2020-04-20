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


@Entity
@Table(name="user")
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id; 
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	// Cascade Types refer to a cascading(propagation) effect to 
	// the dependencies. So here we don't declare the delete cascade
	// because we don't want to delete both the user and book if we 
	// just delete the user. However for roles, it's different. 
	@ManyToMany(
			fetch=FetchType.LAZY, 
			cascade={
				CascadeType.PERSIST, 
				CascadeType.MERGE, 
				CascadeType.DETACH, 
				CascadeType.REFRESH
				}
			)
	@JoinTable(
			name="books_users", 
			joinColumns=@JoinColumn(name="user_id"), 
			inverseJoinColumns=@JoinColumn(name="book_id")
			)
	private Collection<Book> books;
	
	@ManyToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL
			)
	@JoinTable(
			name = "users_roles", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Collection<Role> roles;
	
	public User() {}
	
	public User(String username, String password, String email)
	{
		this.username = username; 
		this.password = password;
		this.email = email; 
	}
	
	public User(String username, String password, String email, Collection<Role> roles)
	{
		this.username = username; 
		this.password = password;
		this.email = email; 
		this.roles = roles;
	}
	
	public User(String username, String password, String email, Collection<Book> books, 
			Collection<Role> roles)
	{
		this.username = username; 
		this.password = password;
		this.email = email; 
		this.books = books;
		this.roles = roles;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Book> getBooks() {
		return books;
	}
	
	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


}
