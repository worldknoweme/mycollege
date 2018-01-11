package com.cx.os.service.impl.system;

import com.cx.common.model.PageEntity;
import com.cx.os.dao.system.SysUserDao;
import com.cx.os.model.system.QuerySysUser;
import com.cx.os.model.system.SysUser;
import com.cx.os.service.system.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;


    public int createSysUser(SysUser sysuser) {
        return sysUserDao.createSysUser(sysuser);
    }


    public void updateSysUser(SysUser sysuser) {
        sysUserDao.updateSysUser(sysuser);
    }


    public void updateSysUserStatus(int userId, int status) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("status", status);
        sysUserDao.updateSysUserStatus(params);
    }


    public SysUser querySysUserByUserId(int userId) {
        return sysUserDao.querySysUserByUserId(userId);
    }


    public List<SysUser> querySysUserPage(QuerySysUser querySysUser,
                                          PageEntity page) {
        return sysUserDao.querySysUserPage(querySysUser, page);
    }


    public boolean validateLoginName(String userLoginName) {
        int count = sysUserDao.validateLoginName(userLoginName);
        if(count<=0){
            return true;
        }
        return false;
    }


    public SysUser queryLoginUser(SysUser sysUser) {
        return sysUserDao.queryLoginUser(sysUser);
    }


    public void updateUserPwd(SysUser sysUser) {
        sysUserDao.updateUserPwd(sysUser);
    }


    public void updateDisableOrstartUser(int userId, int type) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("type", type);
        sysUserDao.updateDisableOrstartUser(map);
    }


    public void updateUserLoginLog(int userId, Date time, String ip) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("time", time);
        map.put("ip", ip);
        sysUserDao.updateUserLoginLog(map);
    }

    /**
     * 查询用户列表
     * @param querySysUser 查询条件
     * @return 用户实体列表
     */
    public List<SysUser> querySysUserList(QuerySysUser querySysUser){
        return sysUserDao.querySysUserList(querySysUser);
    }

    @Override
    public SysUser queryUserByLoginName(SysUser sysUser) {
        return sysUserDao.queryUserByLoginName(sysUser);
    }

}
