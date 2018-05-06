package com.cmf.service.impl;

import com.cmf.domain.UserDomain;
import com.cmf.repository.UserDao;
import com.cmf.repository.UserMongoDao;
import com.cmf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yaowei on 2018/5/4.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMongoDao userMongoDao;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public UserDomain findUserByUserNo(String userNo) {
        return userDao.findByUserNo(userNo);
    }

    @Override
    public UserDomain findMongoUserByUserNo(String userNo) {
        return userMongoDao.findByUserNo(userNo);
    }

    @Override
    public UserDomain save(UserDomain user) {
        return userDao.save(user);
    }

    @Override
    public UserDomain saveMongo(UserDomain user) {
        return userMongoDao.save(user);
    }

    @Override
    public void setRedis() {
        redisTemplate.opsForValue().set("test", "4321");
    }

    @Override
    public String showRedis() {
        return redisTemplate.opsForValue().get("test").toString();
    }
}
