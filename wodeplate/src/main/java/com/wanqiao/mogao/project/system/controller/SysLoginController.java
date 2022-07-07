package com.wanqiao.mogao.project.system.controller;

import java.util.List;
import java.util.Set;

import com.wanqiao.mogao.common.constant.Constants;
import com.wanqiao.mogao.common.sign.MoGaoAES;
import com.wanqiao.mogao.common.utils.ServletUtils;
import com.wanqiao.mogao.framework.security.LoginBody;
import com.wanqiao.mogao.framework.security.LoginUser;
import com.wanqiao.mogao.project.system.domain.SysMenu;
import com.wanqiao.mogao.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.wanqiao.mogao.framework.security.service.SysLoginService;
import com.wanqiao.mogao.framework.security.service.SysPermissionService;
import com.wanqiao.mogao.framework.security.service.TokenService;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.project.system.service.ISysMenuService;

/**
 * 登录验证
 * 
 * @author wanqiao
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     * 
     * @param loginBody 登陆信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        try
        {
            loginBody.setPassword(MoGaoAES.getInstance().Decrypt(loginBody.getPassword()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return AjaxResult.error("密码解密失败");
        }
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
