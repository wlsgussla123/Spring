package com.jinhyeonpark.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	@RequestMapping("/test")
	public String testView() {
		return "test";
	}
	
	@RequestMapping("content/test2")
	public String testView2(Model model) {
		model.addAttribute("id", "test");
		
		return "content/test2";
	}
	
	@RequestMapping("/test3")
	public ModelAndView testView3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", "asdf");
		mv.setViewName("test3");
		
		return mv;
	}
}
