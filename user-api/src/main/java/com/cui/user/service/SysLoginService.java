package com.cui.user.service;

import com.cui.common.utils.BaseResult;

public interface SysLoginService {
    /**
     * 登录
     * @param username
     * @param password
     * @param code
     * @param uuid
     * @return
     */
    String login(String username, String password, String code, String uuid);
}
