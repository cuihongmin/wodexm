package com.cui.plateweb.factory.service;

import com.cui.common.utils.StringUtil;
import com.cui.plateweb.enums.UserStatus;
import com.cui.plateweb.exception.BaseException;
import com.cui.plateweb.factory.LoginUser;
import com.cui.user.entity.SysUser;
import com.cui.user.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private PasswordEncoder pw;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private SysPermissionService permissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("执行了自定义登录逻辑方法");
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtil.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
        return createLoginUser(user);

//        if (!"admin".equals(s)) {
//            throw new UsernameNotFoundException("用户名或密码错误!");
//        }
//        String password = pw.encode("123456");
//        return new User("admin",password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
