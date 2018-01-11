package com.cx.os.dao.system;

import com.cx.common.model.PageEntity;
import com.cx.os.model.system.QuerySysUser;
import com.cx.os.model.system.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserDao {
    /***
     * 创建用户
     * @param sysuser 用户实体
     * @return 用户ID
     */
    public int createSysUser(SysUser sysuser);

    /**
     * 更新用户信息
     * @param sysuser 用户实体
     */
    public void updateSysUser(SysUser sysuser);

    /**
     * 修改用户状态
     * @param params 修改条件 userId用户ID status状态（0正常 1冻结 2删除）
     */
    public void updateSysUserStatus(Map<String, Object> params);

    /**
     * 通过ID，查询用户实体信息
     * @param userId 用户ID
     * @return SysUser
     */
    public SysUser querySysUserByUserId(int userId);

    /**
     * 分页查询用户列表
     * @param querySysUser 查询条件
     * @param page 分页条件
     * @return 用户实体列表
     */
    public List<SysUser> querySysUserPage(QuerySysUser querySysUser , PageEntity page);

    /**
     * 验证用户帐户是否存在
     * @param userLoginName
     */
    public int validateLoginName(String userLoginName);

    /**
     * 查询登录用户
     * @param sysUser 查询条件
     * @return SysUser
     */
    public SysUser queryLoginUser(SysUser sysUser);

    /**
     * 修改用户密码
     * @param sysUser
     */
    public void updateUserPwd(SysUser sysUser);

    /**
     *禁用或启用后台用户
     * @param map 修改条件  userId 用户ID type 1启用 2禁用
     */
    public void updateDisableOrstartUser(Map<String,Object> map);

    /***
     * 修改用户登录最后登录时间和IP
     * @param map
     */
    public void updateUserLoginLog(Map<String,Object> map);

    /**
     * 查询用户列表
     * @param querySysUser 查询条件
     * @return 用户实体列表
     */
    public List<SysUser> querySysUserList(QuerySysUser querySysUser);
    /**
     * 通过登陆名查询用户信息
     * @param sysUser 查询条件
     * @return 用户实体列表
     */
    public SysUser queryUserByLoginName(SysUser sysUser);
}
