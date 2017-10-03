package com.jinhyeonpark.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jinhyeonpark.demo.command.BCommand;
import com.jinhyeonpark.demo.command.BContentCommand;
import com.jinhyeonpark.demo.command.BDeleteCommand;
import com.jinhyeonpark.demo.command.BListCommand;
import com.jinhyeonpark.demo.command.BModifyCommand;
import com.jinhyeonpark.demo.command.BReplyCommand;
import com.jinhyeonpark.demo.command.BReplyViewCommand;
import com.jinhyeonpark.demo.command.BWriteCommand;

@Controller
public class BController {
	BCommand command; // 서비스들을 하나로 묶을 수 있기 때문에
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
		
		command = new BListCommand();
		command.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/write_view")
	// 작성 기능이 아니라, 그냥 작성하는 페이지로 넘기는 메서드
	public String write_view(Model model) {
		System.out.println("write_view()");
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write");
		
		model.addAttribute("request", request); // Controller에서는 작업을 하는 것이 아니라 서비서, DAO에서 작업을 하기 때문에 통째로 넣어놓자
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list"; // 작성을 하고 나면 list 페이지가 보이면 됨.
	}
	
	@RequestMapping("/content_view")
	// 실제로 컨텐츠가 보여지는 페이지
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view");
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model); // 실제로 컨텐츠에 표시될 작업은 서비스에서 작업을 해준다
		
		return "content_view";
	}
	
	@RequestMapping(method = RequestMethod.POST ,value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list"; // 수정 후 list로
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view()");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list"; // 답변 후 list로
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list"; // 삭제 후 list로
	}
}
