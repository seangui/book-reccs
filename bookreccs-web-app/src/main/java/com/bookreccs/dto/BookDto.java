package com.bookreccs.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookDto {
	
	@NotNull(message="required")
	@Size(min = 1, message = "required")
	private String title;
	
	@NotNull(message="required")
	@Size(min = 1, message = "required")
	private String author;
	
	private String imageLink;
	
	public BookDto()
	{}
	
	public BookDto(String title, String author, String imageLink)
	{
		this.title = title;
		this.author = author; 
		this.imageLink = imageLink;
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

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	
	
}
