package com.kh.home.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String initPage() {
		return "index";
	}
	
	@RequestMapping("/success")
	public String loginSuccess(HttpSession session) {
		session.setAttribute("googleLogin", "구글로그인유저");
		return "redirect:/";
	}
	
	@RequestMapping("/failure")
	public String loginFailure(Model model) {
		model.addAttribute("errorMsg", "로그인 실패!");
		return "errorPage";
	}
	
	@RequestMapping("/login/oauth/google")
	public String googleLoginResult(@RequestParam(value="code", defaultValue="") String code, @RequestParam(value="error", defaultValue="") String error, 
									HttpSession session) throws Exception, IOException {
		if(code.isEmpty()) {		// 인증 실패
			session.setAttribute("errorMsg", error);	// 세션영역에 에러메시지 저장
			return "redirect:/failure";				// 로그인 실패 페이지로 재요청
		} else {				// 인증 성공
			session.setAttribute("user", code);		// 세션영역에 인증토큰 저장
			return "redirect:/success";				// 로그인 성공 페이지로 재요청
		}
	}
}
