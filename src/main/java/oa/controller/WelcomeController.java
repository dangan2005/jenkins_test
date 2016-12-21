package oa.controller;

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
			log.info("访问IP地址为:?", request.getRemoteAddr());
		}
		return "Hello World Java";
	}
}
