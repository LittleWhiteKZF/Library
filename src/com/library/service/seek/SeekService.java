package com.library.service.seek;

import java.util.List;

import com.library.pojo.Book;
import com.library.pojo.Page;

public interface SeekService {
	public List<Page> seekBook(String name);
	public List<Page> showBook();
}
