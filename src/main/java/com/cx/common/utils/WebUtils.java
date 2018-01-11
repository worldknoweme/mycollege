package com.cx.common.utils;

import com.cx.common.constants.CommonConstants;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;

public class WebUtils {

	/**获取k位由0-9的随机数字拼成的符串*/
	public static String getRandomNum(int k){
		String RandomNum = "";
		for (int i=0;i<k;i++){
			Random rd = new Random();
			RandomNum += (int)Math.floor(rd.nextInt(9));
		}
		return RandomNum;
	}

	/**获取请求IP地址*/
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		ipAddress = request.getHeader("x-forwarded-for");
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}
		if ((ipAddress != null) && (ipAddress.length() > 15)) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}


	/**获取请求URL*/
	public static String getServletRequestUrlParms(HttpServletRequest request) {
		StringBuffer sbUrlParms = request.getRequestURL();
		sbUrlParms.append("?");
		@SuppressWarnings("unchecked")
		Enumeration<String> parNames = request.getParameterNames();
		while (parNames.hasMoreElements()) {
			String parName = parNames.nextElement().toString();
			try {
				sbUrlParms.append(parName).append("=")
						.append(URLEncoder.encode(request.getParameter(parName), "UTF-8"))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				return "";
			}
		}
		return sbUrlParms.toString();
	}
	/**获取请求URI*/
	public static String getServletRequestUriParms(HttpServletRequest request) {
		StringBuffer sbUrlParms = new StringBuffer(request.getRequestURI());
		sbUrlParms.append("?");
		@SuppressWarnings("unchecked")
		Enumeration<String> parNames = request.getParameterNames();
		while (parNames.hasMoreElements()) {
			String parName = parNames.nextElement().toString();
			try {
				sbUrlParms.append(parName).append("=")
				.append(URLEncoder.encode(request.getParameter(parName), "UTF-8"))
				.append("&");
			} catch (UnsupportedEncodingException e) {
				return "";
			}
		}
		return sbUrlParms.toString();
	}
	
	/**后台用户登录号验证*/
	public static boolean checkLoginName(String value){
		return value.matches(CommonConstants.loginRegex);
	}

	/**正则表达试验证邮箱号*/
	public static boolean checkMobile (String value) {
		return (value.matches(CommonConstants.telRegex));
	}
	/**正则表达试验证邮箱号*/
	public static boolean checkEmail(String value, int length) {
		if (length == 0) {
			length = 40;
		}
		return (value.matches(CommonConstants.emailRegex)) && (value.length() <= length);
	}

	/**正则表达试验证密码*/
	public static boolean isPasswordAvailable(String password) {
		String partten = "^[_0-9a-zA-Z]{6,}$";
		boolean flag = (password.matches(partten)) && (password.length() >= 6) && (password.length() <= 16);
		return flag;
	}

	/**获取用户访问浏览器信息*/
	public static String getUserAgent(HttpServletRequest request) {
		String uabrow = request.getHeader("User-Agent");//获取浏览器信息
		UserAgent userAgent =UserAgent.parseUserAgentString(uabrow);
		Browser browser = userAgent.getBrowser();
		OperatingSystem os = userAgent.getOperatingSystem();
		return browser.getName().toLowerCase()+";"+os.getName().toLowerCase();
	}


}