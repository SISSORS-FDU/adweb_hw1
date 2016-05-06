package adweb.service;

import adweb.bean.User;
import adweb.dao.UserDao;

/**
 * Created by 张亚中 on 2016-05-05.
 */
public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public boolean addUser(User user) {
        if (userDao.query(user.getAccount()) != null) {
            return false;
        }
        userDao.save(user);
        return true;
    }
    public void updateUser(User user) {
        userDao.update(user);
    }
    public User findUserByAccount(String account) {
        return userDao.query(account);
    }
}
