package cn.evendy.iutil.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import cn.evendy.iutil.module.User;


/**
 * @author: evendy
 * @time: 2015/5/6 15:36
 */
public class UserDao {
    private Context mContext;
    private Dao<User, Integer> userDao;
    private DatabaseHelper helper;

    public UserDao(Context context) {
        this.mContext = context;
        try {
            helper = DatabaseHelper.getHelper(mContext);
            userDao = helper.getDao(User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     *
     * @param user
     */
    public boolean addUser(User user) {
        int result = 0;
        try {
            result = userDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0 ? true : false;
    }

    public User queryUserByName(String name) {
        User user = null;
        try {
            user = userDao.queryBuilder().where().eq("name", name).query().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
