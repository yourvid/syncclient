package com.cmf.repository;

import com.cmf.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yaowei on 2018/5/4.
 */
public interface UserDao extends JpaRepository<UserDomain, Long> {
    /**
     * 根据用户名称查询用户
     * @param userNo
     * @return
     */
    UserDomain findByUserNo(String userNo);
}
