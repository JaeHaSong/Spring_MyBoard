package kh.spring.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.daoimpl.CommentDAOImpl;
import kh.spring.dto.CommentDTO;
import kh.spring.service.CommentService;

public class CommentServiceImpl implements CommentService
{
	@Autowired
	CommentDAOImpl cdao;
	
	
	
	@Override
	public Object insert(CommentDTO dto) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		int result = cdao.insert(dto);
		
		if(result == 1)
		{
			mav.setViewName("");
		}
		else
		{
			mav.setViewName("error");
		}
		
		return mav;
	}
	
}
