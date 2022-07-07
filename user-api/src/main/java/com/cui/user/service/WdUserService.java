package com.cui.user.service;

import com.cui.common.utils.BaseResult;
import com.cui.user.entity.WdUser;

public interface WdUserService {
    /**
     * 登录
     * @param mobile
     * @param password
     * @return
     */
    BaseResult login(String mobile, String password, String ip, String sessionId);

    /**
     * 根据电话号码 查用户
     * @param mobile
     * @return
     */
    WdUser getUserByMobile(String mobile);

    //注册
    BaseResult registerWD(String mobile, String password, String code) throws Exception;

    /**
     * 校验登录token是否过期
     * @param token
     * @param uid
     * @return
     */
    BaseResult checkUserAuth(String token);

    //获取用户信息
    BaseResult getUserDealList();

    //修改密码4.0
    BaseResult updatePasswordAtPC(String mobile,String code, String newPassword, Integer uid);

    //修改手机号4.0
    BaseResult updateMobileById(String oldMobile,String newMobile, Integer uid,String oldMobileCode, String newMobileCode);

    //验证码登录4.0
    BaseResult loginByCode(String mobile, String code, String ip,String sessionId);
}
