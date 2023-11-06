package com.ssafy.kpc.model.repository;

import com.ssafy.kpc.model.dto.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(Long id){
        return em.find(User.class , id);
    }

    public List<User> findAll(){
        return em.createQuery("select u from users u", User.class)
                .getResultList();
    }

//    public List<User> findByName(String name){
//        return em.createQuery("select u from users u where u.name = :name", User.class)
//                .setParameter("name", name)
//                .getResultList();
//    }


}
