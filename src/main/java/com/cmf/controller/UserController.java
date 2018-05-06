package com.cmf.controller;

import com.cmf.base.ResponseModel;
import com.cmf.domain.UserDomain;
import com.cmf.exception.XcHandlerException;
import com.cmf.service.UserService;
import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yaowei on 2018/5/4.
 */
@RestController
@RequestMapping(value = "/")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * hello word
     *
     * @return
     */
    @RequestMapping(value = "/hello")
    public ResponseModel sayHello() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData("Hello word");
        throw new XcHandlerException("系统错误，测试！");
    }

    @RequestMapping(value = "/save")
    public ResponseModel save() {
        ResponseModel responseModel = new ResponseModel();
        UserDomain user = new UserDomain();
        user.setSalt("1111");
        user.setUserName("姚伟");
        user.setUserNo("NO12345");
        user.setUserPwd("3456787");
        user.setUserStatus("1");
        user = userService.save(user);
        responseModel.setData(user);
        return responseModel;
    }

    @RequestMapping(value = "/saveMongo")
    public ResponseModel saveMongo() {
        ResponseModel responseModel = new ResponseModel();
        UserDomain user = new UserDomain();
        user.setSalt("1111");
        user.setId(1L);
        user.setUserName("姚伟");
        user.setUserNo("NO12345");
        user.setUserPwd("3456787");
        user.setUserStatus("1");
        user = userService.saveMongo(user);
        responseModel.setData(user);
        return responseModel;
    }

    /**
     * 根据用户编号获取用户信息
     *
     * @param userNo
     * @return
     */
    @RequestMapping(value = "/getUserByNo")
    public ResponseModel getUserByNo(String userNo) {
        ResponseModel responseModel = new ResponseModel();
        UserDomain user = userService.findUserByUserNo(userNo);
        responseModel.setData(user);
        return responseModel;
    }

    /**
     * 根据用户编号获取用户信息
     *
     * @param userNo
     * @return
     */
    @RequestMapping(value = "/getMongoUserByNo")
    public ResponseModel getMongoUserByNo(String userNo) {
        ResponseModel responseModel = new ResponseModel();
        UserDomain user = userService.findMongoUserByUserNo(userNo);
        responseModel.setData(user);
        return responseModel;
    }




    @RequestMapping("/setRedis")
    public void setRedis() {
        userService.setRedis();
    }

    @RequestMapping("/showRedis")
    public String showRedis(){
        return userService.showRedis();
    }
}
