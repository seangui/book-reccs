package com.bookreccs.dto;

import java.util.Arrays;

public class VolumeInfoDto 
{
	private String title;
	
	private String[] authors; 
	
	private double averageRating; 
	
	private ImageLinkDto imageLinks;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public ImageLinkDto getImageLinks() {
		return imageLinks;
	}

	public void setImageLinks(ImageLinkDto imageLinks) {
		this.imageLinks = imageLinks;
	}

	@Override
	public String toString() {
		return "VolumeInfoDto [title=" + title + ", authors=" + Arrays.toString(authors) + ", averageRating="
				+ averageRating + ", imageLinks=" + imageLinks + "]";
	}	
}
