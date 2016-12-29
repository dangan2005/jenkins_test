package oa.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @包名：oa.controller
 * @类名：WelcomeController
 * @创建人：孙伟隆
 * @创建时间：2016年12月20日 下午4:12:12
 * 
 * @描述：TODO
 *
 * @SVN版本号：$Rev$
 * @更新时间：$Date$
 * @更新人：$Author$
 * @更新描述：TODO
 * 
 */
@RestController
public class WelcomeController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("welcome")
	public String welcome(HttpServletRequest request) {
		if (log.isInfoEnabled()) {
			String ipAddr = getIpAddr(request);
			log.info("访问IP地址为: {}", ipAddr);
		}
		return "Hello World Java";
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (ip.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}
}
