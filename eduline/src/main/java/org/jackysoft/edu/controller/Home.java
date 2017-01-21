package org.jackysoft.edu.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;

@Controller
public class Home {

	static final Log logger = LogFactory.getLog(Home.class);

	@Autowired
	private SysUserService userService;

	@RequestMapping("/")
	public String index() {
		return "home";
	}

	@RequestMapping(value = "/home")
	public ModelAndView home(@AuthenticationPrincipal SysUser loginuser) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("sysUser", loginuser);
		return mav;
	}

	@RequestMapping("/profile")
	public ModelAndView profile(@AuthenticationPrincipal SysUser loginuser) {
		ModelAndView mav = new ModelAndView("profile");
		SysUser user = userService.findById(loginuser.getName());
		mav.addObject("ownerUser", user);
		return mav;
	}

	@RequestMapping("/modify/password")
	public ModelAndView modifypassword(@RequestParam String username,
			@RequestParam String password, @RequestParam String passwordn,
			@RequestParam String passwordr) {
		ModelAndView mav = new ModelAndView("profile");

		SysUser user = userService.findById(username);
		if (user == null) {
			mav.addObject("message", "用户不存在");
			return mav;
		}
		if (!userService.passwordCorrect(password, user.getPassword())) {
			mav.addObject("message", "旧密码不正确");
			return mav;
		}

		if (Strings.isNullOrEmpty(passwordn)
				|| Strings.isNullOrEmpty(passwordr)
				|| !passwordn.equals(passwordr)) {
			mav.addObject("message", "两次输入的新密码不一致");
			return mav;
		}

		userService.updatePartial(username, "password:" + passwordn);
		mav.addObject("ownerUser", user);
		mav.addObject("message", "密码已修改");
		return mav;
	}

	@RequestMapping("/reset/password/{username}")
	public String resetpassword(@PathVariable("username") String username) {
		userService.resetPassword(username);
		return "blank";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		logger.info("client ip address is " + ip);
		return "login";
	}

	@RequestMapping("/errorlogin")
	public ModelAndView loginerror() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("error", true);
		return mav;
	}

	@RequestMapping("/logout")
	public String logout() {
		return "logout";
	}
}