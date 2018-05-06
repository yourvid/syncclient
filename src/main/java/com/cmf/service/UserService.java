package com.cmf.service;

import com.cmf.domain.UserDomain;

/**
 * Created by yaowei on 2018/5/4.
 */
public interface UserService {
    /**
     * 根据用户编号获取用户信息
     * @param userNo
     * @return
     */
    UserDomain findUserByUserNo(String userNo);

    UserDomain save(UserDomain user);

    UserDomain findMongoUserByUserNo(String userNo);

    UserDomain saveMongo(UserDomain user);

    void setRedis();

    String showRedis();
}
