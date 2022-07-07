package com.cui.user.service;

import com.cui.common.utils.BaseResult;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductService {
    public BaseResult addInfoToRedis();

    // 发送短信接口

    public BaseResult sendSmsWithCode(String mobile, String tmpKey);

    // 验证验证码
    public BaseResult checkCode(String mobile, String tmpKey,String code);



}
