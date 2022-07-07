package com.cui.user.dao;

import com.cui.user.entity.UserLoginLogs;
import com.cui.user.entity.UserOperationLogs;
import com.cui.user.entity.WdUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WdUserDao {
    /**
     * 插入User到数据库,包括null值
     * @param value
     * @return
     */
    int insertUser(WdUser value);
    /**
     * 通过User的mobile获得User对象
     * @param mobile
     * @return
     */
    WdUser selectUserByMobile(@Param("mobile") String mobile);

    /**
     * 插入UserLoginLogs到数据库,包括null值
     * @param value
     * @return
     */
    int insertUserLoginLogs(UserLoginLogs value);

    //获取用户信息
    List<WdUser> getUserDealList();

    /**
     * 通过uid修改password
     * @param id
     * @return
     */
    int updatePasswordById(@Param("uid")Integer id, @Param("password")String password);

    /**
     * 插入UserOperationLogs到数据库,包括null值
     * @param value
     * @return
     */
    int insertUserOperationLogs(UserOperationLogs value);

}
