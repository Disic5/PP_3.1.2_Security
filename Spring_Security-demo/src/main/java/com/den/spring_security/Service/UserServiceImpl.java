package com.den.spring_security.Service;

import com.den.spring_security.Config.PasswordConfig;
import com.den.spring_security.Dao.UserDao;
import com.den.spring_security.Model.Role;
import com.den.spring_security.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    private final PasswordConfig passwordConfig;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordConfig passwordConfig) {
        this.userDao = userDao;
        this.passwordConfig = passwordConfig;
    }

    @Transactional
    @Override
    public void add(User user, Set<Role> roles) {
        user.setPassword(passwordConfig.passwordEncoder().encode(user.getPassword()));
        userDao.add(user, roles);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public User change(User user, Set<Role> roles) {
        user.setPassword(passwordConfig.passwordEncoder().encode(user.getPassword()));
        return userDao.change(user, roles);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User findUserById(long id) {
        return userDao.findUserById(id);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findUserByName(username);
    }
}