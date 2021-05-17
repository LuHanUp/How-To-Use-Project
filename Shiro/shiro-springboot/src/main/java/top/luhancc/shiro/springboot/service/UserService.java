package top.luhancc.shiro.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.luhancc.shiro.springboot.dao.UserDao;
import top.luhancc.shiro.springboot.domain.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByName(String name) {
        return this.userDao.findByUsername(name);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
