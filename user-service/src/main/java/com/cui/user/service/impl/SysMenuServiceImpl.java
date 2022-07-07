package com.cui.user.service.impl;

import com.cui.common.utils.StringUtil;
import com.cui.user.dao.SysMenuMapper;
import com.cui.user.entity.domain.SysMenu;
import com.cui.user.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单  业务层处理
 *
 * @Author cuihongmin
 * @Date 2022/6/17 11:08
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private SysMenuMapper menuMapper;


    @Override
    public List<SysMenu> selectMenuList(Long userId) {
        return null;
    }

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId) {
        return null;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        HashSet<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtil.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Integer> selectMenuListByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<SysMenu> buildMenuTree(List<SysMenu> menus) {
        return null;
    }

    @Override
    public SysMenu selectMenuById(Long menuId) {
        return null;
    }

    @Override
    public boolean hasChildByMenuId(Long menuId) {
        return false;
    }

    @Override
    public boolean checkMenuExistRole(Long menuId) {
        return false;
    }

    @Override
    public int insertMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public int updateMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return 0;
    }

    @Override
    public String checkMenuNameUnique(SysMenu menu) {
        return null;
    }
}
