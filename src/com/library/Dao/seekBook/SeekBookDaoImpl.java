package com.library.Dao.seekBook;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.library.pojo.Book;
import com.library.utils.HibernateUtils;

public class SeekBookDaoImpl implements com.library.Dao.seek.SeekBookDao {

	@Override
	public List seekBook(String name) {
		Session session=null;
        session=HibernateUtils.getSession();
        String hql="from Book b where b.name like '%"+name+"%'";
        Query query= session.createQuery(hql);
        List<Book> list=query.list();
        System.out.println("dao1:"+list);
        if(list.size()!=0){
            System.out.println("dao:"+list);
            return list;
        }
		return null;
	}

    public List<Book> showBook()
    {
        Session session=null;
        session=HibernateUtils.getSession();
        String hql="from Book";
        Query query= session.createQuery(hql);
        List<Book> list=query.list();
        if(list.size()!=0){
            return list;
        }
        return null;
    }

}
