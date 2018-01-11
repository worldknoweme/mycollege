package com.cx.os.service.impl.system;

import com.cx.common.model.PageEntity;
import com.cx.os.dao.system.SysUserLoginLogDao;
import com.cx.os.model.system.SysUserLoginLog;
import com.cx.os.service.system.SysUserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysUserLoginLogService")
public class SysUserLoginLogServiceImpl implements SysUserLoginLogService {

    @Autowired
    private SysUserLoginLogDao sysUserLoginLogDao;

    public int createLoginLog(SysUserLoginLog loginLog) {
        return sysUserLoginLogDao.createLoginLog(loginLog);
    }

    public List<SysUserLoginLog> queryUserLogPage(int userId, PageEntity page) {
        return sysUserLoginLogDao.queryUserLogPage(userId, page);
    }
}
