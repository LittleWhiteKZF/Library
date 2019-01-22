package com.library.service.rank;

import com.library.Dao.rank.ShowRankDaoImpl;
import com.library.pojo.User;
import java.util.List;

public class ShowRankServiceImpl implements ShowRankService {
    @Override
    public List<User> showRank() {
        ShowRankDaoImpl showRankDao=new ShowRankDaoImpl();
        List<User> list=showRankDao.showRank();
        return list;
    }
}
