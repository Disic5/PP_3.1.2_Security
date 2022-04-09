package com.den.spring_security.Dao;

import com.den.spring_security.Model.Role;
import com.den.spring_security.Model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user, Set<Role> roles) {
        user.setRoles(roles);
        entityManager.persist(user);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findUserById(id));
    }

    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User change(User user, Set<Role> roles) {
        user.setRoles(roles);
        return entityManager.merge(user);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findUserByName(String name) {
        Query query = entityManager.createQuery("select u from User u where u.name=:name", User.class);
        query.setParameter("name", name);
        return (User) query.getSingleResult();
    }
}