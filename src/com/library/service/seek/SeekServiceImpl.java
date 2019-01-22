package com.library.service.seek;

import java.util.ArrayList;
import java.util.List;

import com.library.Dao.seekBook.SeekBookDaoImpl;
import com.library.pojo.Book;
import com.library.pojo.Page;

public class SeekServiceImpl implements SeekService {
	private int num;
	List<Book> books=new ArrayList<>();
	List<Page> pages=new ArrayList<>();
	Page page = new Page();

	@Override
	public List<Page> seekBook(String name) {
		SeekBookDaoImpl seekBook = new SeekBookDaoImpl();
		List<Book> list = seekBook.seekBook(name);
		if(list==null)
			return null;
		if(list.size()%4 == 0) {
			num = list.size()/4;
		}else {
			num = list.size()/4+1;
		}
		for(int i=1;i<=num;i++) {
			for(int j=4*(i-1);j<4*i;j++){ 
				if(j == list.size())
					break;
				else
					books.add(list.get(j));
			}
			page.setData(books);
			page.setPage(i);
			page.setTotalnum(num);
			pages.add(page);
			books = new ArrayList<Book>();
			page = new Page();
		}
		return pages;
	}
	@Override
	public List<Page> showBook(){
		SeekBookDaoImpl seekBook=new SeekBookDaoImpl();
		List<Book> list=seekBook.showBook();
		if(list.size()%4 == 0) {
			num = list.size()/4;
		}else {
			num = list.size()/4+1;
		}
		for(int i=1;i<=num;i++) {
			for(int j=4*(i-1);j<4*i;j++){ 
				if(j == list.size())
					break;
				else
					books.add(list.get(j));
			}
			page.setData(books);
			page.setPage(i);
			page.setTotalnum(num);
			pages.add(page);
			books = new ArrayList<Book>();
			page = new Page();
		}
		return pages;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

}
