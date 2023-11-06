package com.ssafy.kpc.model.service;

import com.ssafy.kpc.model.dto.User;
import com.ssafy.kpc.model.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception{
        User user = new User();
        user.setUserName("kim");

        Long saveId = userService.join(user);

        assertEquals(user, userRepository.findOne(saveId));
    }
}