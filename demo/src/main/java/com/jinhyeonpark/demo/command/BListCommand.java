package com.jinhyeonpark.demo.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.jinhyeonpark.demo.dao.BDao;
import com.jinhyeonpark.demo.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list(); // dao에서 DB에 접근해서 데이터를 가져올 것.
		
		model.addAttribute("list", dtos); // 작업한 내용을 model에다가 넣자. list 속성을 이용해, view page에서 DAO 객체를 계속해서 사용 가능.
	}

}
