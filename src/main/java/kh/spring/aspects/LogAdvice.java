package kh.spring.aspects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.daoimpl.LogDAOImpl;
import kh.spring.daoimpl.MemberDAOImpl;
import kh.spring.dto.LogDTO;
import kh.spring.serviceImpl.BoardServiceImpl;

@Component
@Aspect
public class LogAdvice
{
	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	BoardServiceImpl bs;
	
	@Autowired
	LogDAOImpl ldao;
	@Autowired
	MemberDAOImpl mdao;
	
//	@Before("execution(* kh.spring.main.HomeController.login(..))")
//	public void printLog(JoinPoint jp)
//	{
//		try
//		{
//			LogDTO dto = new LogDTO();
//			
//			dto.setL_ip(request.getRemoteAddr());
//			dto.setL_target(jp.getArgs()[0].toString());
//			dto.setL_behavior("login");
//			dto.setL_type("시도");
//			
//			ldao.insert(dto);
//			
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
	
	@Around("execution(* kh.spring.main.HomeController.login(..))")
	public Object printLog(ProceedingJoinPoint pjp)
	{
		Object returnObj = null;
		try
		{
			String id = pjp.getArgs()[0].toString();
			String pw = pjp.getArgs()[1].toString();
			
			LogDTO dto = new LogDTO();
			
			dto.setL_ip(request.getRemoteAddr());
			dto.setL_target(id);
			dto.setL_behavior("login");
			
			try
			{
				mdao.login(id, pw);
				
				dto.setL_type("성공");
				ldao.insert(dto);
				
				session.setAttribute("loginId", id);
				
			}
			catch(org.springframework.dao.EmptyResultDataAccessException e)
			{
				dto.setL_type("실패");
				ldao.insert(dto);
				
				ModelAndView mav = new ModelAndView();
				
				mav.setViewName("redirect: login_false");
				
				return mav;
			}
			
			returnObj = pjp.proceed();
			return returnObj;
			
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			return "error";
		}
		
	}
}
