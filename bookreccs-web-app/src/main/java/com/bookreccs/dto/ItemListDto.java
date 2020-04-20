package com.bookreccs.dto;

import java.util.Arrays;

public class ItemListDto
{
	private ItemDto[] items;

	public ItemDto[] getItems() {
		return items;
	}


	public void setItems(ItemDto[] items) {
		this.items = items;
	}


	@Override
	public String toString() {
		return "ItemListDto [items=" + Arrays.toString(items) + "]";
	}



	
}
