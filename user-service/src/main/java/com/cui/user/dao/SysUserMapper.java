package com.cui.user.dao;

import com.cui.user.entity.SysUser;

/**
* @Author cuihongmin
* 用户表  数据层
*
* @return */
public interface SysUserMapper {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);
}
