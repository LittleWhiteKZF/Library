package com.library.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.pojo.Book;
import com.library.pojo.Page;
import com.library.service.seek.SeekServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SeekAction extends ActionSupport{

	private String bookName;
	int num = 1;


	public String seekBook()  {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SeekServiceImpl seekBook = context.getBean(SeekServiceImpl.class);
		List<Page> list = seekBook.seekBook(bookName);
		ActionContext.getContext().getSession().put("pages",list);
		ActionContext.getContext().getSession().put("num",num);
		ActionContext.getContext().getSession().put("seek","1");
		System.out.println(list);
		return SUCCESS;

	}

	public void showBook(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SeekServiceImpl seekBook = context.getBean(SeekServiceImpl.class);
		List<Page> list=seekBook.showBook();
		ActionContext.getContext().getSession().put("pages",list);
		ActionContext.getContext().getSession().put("num",num);	
		System.out.println(list);
	}


	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
}
