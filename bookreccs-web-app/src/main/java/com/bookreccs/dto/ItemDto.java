package com.bookreccs.dto;

public class ItemDto {
	private VolumeInfoDto volumeInfo;

	public VolumeInfoDto getVolumeInfo() {
		return volumeInfo;
	}

	public void setVolumeInfo(VolumeInfoDto volumeInfo) {
		this.volumeInfo = volumeInfo;
	}

	@Override
	public String toString() {
		return "ItemDto [volInfo=" + volumeInfo + "]";
	}
	
	
}
