package com.library.Dao.seek;

import java.util.List;

import com.library.pojo.Book;

public interface SeekBookDao {
	public List<Book> seekBook(String name);
}
