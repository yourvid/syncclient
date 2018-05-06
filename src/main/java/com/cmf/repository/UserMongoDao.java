package com.cmf.repository;

import com.cmf.domain.UserDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description: 类描述
 * @Author: yaowei
 * @CreateDate: 2018/5/5 23:07
 * @Version: v1.0
 */
public interface UserMongoDao extends MongoRepository<UserDomain, Long> {

    /**
     * 根据用户名称查询用户
     * @param userNo
     * @return
     */
    UserDomain findByUserNo(String userNo);
}
