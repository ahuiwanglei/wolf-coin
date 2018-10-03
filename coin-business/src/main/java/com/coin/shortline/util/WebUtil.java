package com.coin.shortline.util;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * web常用方法对象类
 * 
 * @author meiguiyang
 * @version [版本号, Mar 30, 2013]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class WebUtil {
	private static final Logger logger = Logger.getLogger(WebUtil.class);

	public static final int GONE = 0;
	public static final int VISABLE = 1;
	public static final int PAGEVISABLE = 2;

	/**
	 * <默认构造函数>
	 */
	private WebUtil() {
	}

	/**
	 * 返回当前的请求
	 * 
	 * @return HttpServletRequest
	 * @see [类、类#方法、类#成员]
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 获取服务器url
	 * 
	 * @author wangbo
	 * @return url
	 */
	public static String getServerURL() {
		StringBuffer url = new StringBuffer("http://");
		url.append(getRequest().getServerName());
		url.append(':');
		url.append(getRequest().getServerPort());
		url.append(getRequest().getContextPath());
		return url.toString();
	}


	/**
	 * 根据key返回session的对象
	 * 
	 * @param key
	 *            key
	 * @return Object
	 * @see [类、类#方法、类#成员]
	 */
	public static Object getObject(String key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 提供对应的key,value存放到session
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @see [类、类#方法、类#成员]
	 */
	public static void saveObject(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 返回session
	 * 
	 * @return HttpSession
	 * @see [类、类#方法、类#成员]
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 返回服务器的应用根目录
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public static String getWebRoot() {
		return getSession().getServletContext().getRealPath("/");
	}

	/**
	 * 返回CookieId
	 * 
	 * @return CookieId
	 * @see [类、类#方法、类#成员]
	 */
	public static String getCookieId() {
		return getRequest().getHeader("Cookie");
	}

	public static void printParams() {
		Enumeration<String> tt = WebUtil.getRequest().getParameterNames();
		while (tt.hasMoreElements())
			logger.debug("page=" + tt.nextElement());
	}

	private static final String notice = "display";
	private static final String msg = "msg";

	public static Map<String, String> httpNotice(String display, String message) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(notice, display);
		map.put(msg, message);
		if(StringUtil.getInt(display) == PAGEVISABLE){
			getSession().setAttribute(notice, display);
			getSession().setAttribute(msg, message);
		}
		return map;
	}
}
