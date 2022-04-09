package com.den.spring_security.Service;

import com.den.spring_security.Model.Role;
import com.den.spring_security.Model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void add(User user, Set<Role> roles);

    void delete(long id);

    User change(User user, Set<Role> roles);

    List<User> listUsers();

    User findUserById(long id);

    public User findUserByName(String name);
}