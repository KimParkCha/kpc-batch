package com.ssafy.kpc.model.service;

import com.ssafy.kpc.model.dto.User;
import com.ssafy.kpc.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public Long join(User user){
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateMember(User user) {
//        List<User> findUsers = userRepository.findByName(user.getUserName());
//        if(!findUsers.isEmpty()){
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
    }

    public List<User> findMembers(){
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }
}
