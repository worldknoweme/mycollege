package com.cx.os.dao.system;

import com.cx.common.model.PageEntity;
import com.cx.os.model.system.SysUserLoginLog;

import java.util.List;

public interface SysUserLoginLogDao {
    /**
     * 添加登录日志
     * @param loginLog
     * @return 日志ID
     */
    public int createLoginLog(SysUserLoginLog loginLog);

    /**
     * 查询用户登录日志
     * @param userId 用户ID
     * @param page 分页条件
     * @return List<SysUserLoginLog>
     */
    public List<SysUserLoginLog> queryUserLogPage(int userId, PageEntity page);

}
