package com.cx.os.controller.admin;

import com.cx.common.constants.CommonConstants;
import com.cx.common.utils.MD5;
import com.cx.common.utils.WebUtils;
import com.cx.os.model.system.SysUser;
import com.cx.os.model.system.SysUserLoginLog;
import com.cx.os.service.system.SysUserLoginLogService;
import com.cx.os.service.system.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserLoginLogService sysUserLoginLogService;
    @RequestMapping
    public String login(HttpServletRequest request){
        return "/admin/login";
    }

    /**
     * 执行登录
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/main/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("sysUser") SysUser sysUser){
        ModelAndView model = new ModelAndView();
        try{
            model.setViewName("");
            model.addObject("sysUser", sysUser);
            if(sysUser.getLoginName()==null || sysUser.getLoginName().trim().equals("")){
                model.addObject("message", "请输入用户名!");
                return model;
            }
            if(sysUser.getLoginPwd()==null || sysUser.getLoginPwd().trim().equals("")){
                model.addObject("message", "请输入密码!");
                return model;
            }

            //获取Session中验证码
            String randCode = (String) request.getSession().getAttribute(CommonConstants.RAND_CODE);
            //用户输入的验证码
            String randomCode = request.getParameter("randomCode");
            if(randomCode==null || !randomCode.equals(randCode)){
                model.addObject("message", "验证码不正确！");
                return model;
            }
            request.getSession().removeAttribute(CommonConstants.RAND_CODE);
            sysUser.setLoginPwd(MD5.getMD5(sysUser.getLoginPwd()));
            SysUser su = sysUserService.queryLoginUser(sysUser);
            if(su==null){
                model.addObject("message", "用户名或密码错误！");
                return model;
            }
            //判断用户是否是可用状态
            if(su.getStatus()!=0){
                model.addObject("message", "该用户已经冻结！");
                return model;
            }
            //缓存用登录信息
//            CacheUtil.set(CacheConstans.LOGIN_MEMCACHE_PREFIX+su.getUserId(), su);
//            //request.getSession().setAttribute(CacheConstans.LOGIN_MEMCACHE_PREFIX+su.getUserId(),su );
//            WebUtils.setCookie(response, CacheConstans.LOGIN_MEMCACHE_PREFIX, CacheConstans.LOGIN_MEMCACHE_PREFIX+su.getUserId(), 1);

            //修改用户登录记录
            sysUserService.updateUserLoginLog(su.getUserId(), new Date(), WebUtils.getIpAddr(request));

            //添加登录记录
            SysUserLoginLog loginLog = new SysUserLoginLog();
            loginLog.setUserId(su.getUserId());//用户ID
            loginLog.setLoginTime(new Date());//
            loginLog.setIp(WebUtils.getIpAddr(request));//登录IP
            String userAgent = WebUtils.getUserAgent(request);
            if(StringUtils.isNotEmpty(userAgent)){
                loginLog.setUserAgent(userAgent.split(";")[0]);//浏览器
                loginLog.setOsName(userAgent.split(";")[1]);//操作系统
            }
            //保存登录日志
            sysUserLoginLogService.createLoginLog(loginLog);
            model.setViewName("/admin/main");
        }catch (Exception e) {
            model.addObject("message", "系统繁忙，请稍后再操作！");
            logger.error("login()--error",e);
        }
        return model;
    }
}
