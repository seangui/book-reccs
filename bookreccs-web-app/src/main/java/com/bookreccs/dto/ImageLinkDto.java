package com.bookreccs.dto;

public class ImageLinkDto {
	private String smallThumbnail;

	public String getSmallThumbnail() {
		return smallThumbnail;
	}

	public void setSmallThumbnail(String smallThumbnail) {
		this.smallThumbnail = smallThumbnail;
	}

	@Override
	public String toString() {
		return "ImageLinkDto [smallThumbnail=" + smallThumbnail + "]";
	}
	

}
