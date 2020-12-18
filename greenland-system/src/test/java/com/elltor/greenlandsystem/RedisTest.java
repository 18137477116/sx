package com.elltor.greenlandsystem;

import com.elltor.greenlandcommon.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void redisTest(){
        redisUtils.set("liuqichun","刘启春");
        UserDetails user = userDetailsService.loadUserByUsername("ssm");
        System.out.println(user);

    }

}
