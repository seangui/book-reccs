package com.bookreccs.service;

import java.util.Collection;

import com.bookreccs.dto.tastedive.TasteDiveItemDto;
import com.bookreccs.entity.Book;

public interface ReccsService {
	TasteDiveItemDto getReccs(Collection<Book> bookCollection);
}
