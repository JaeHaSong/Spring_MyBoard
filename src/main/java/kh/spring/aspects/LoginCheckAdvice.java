
package kh.spring.aspects;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kh.spring.daoimpl.BoardDAOImpl;
import kh.spring.daoimpl.MemberDAOImpl;
import kh.spring.daoimpl.PortDAOImpl;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.PortDTO;

@Component
@Aspect
public class LoginCheckAdvice
{
	@Autowired
	HttpSession session;
	@Autowired
	MemberDAOImpl mdao;
	@Autowired
	BoardDAOImpl bdao;
	@Autowired
	PortDAOImpl pdao;
	
	
	@Around("execution(* kh.spring.main.HomeController.boardWrite(..))")
	public Object loginCheck(ProceedingJoinPoint pjp)
	{
		Object obj;
		try
		{
			String writer = pjp.getArgs()[0].toString();
//			String title = pjp.getArgs()[1].toString();
//			String contents = pjp.getArgs()[2].toString();
			
			if((writer != null) && (writer.equals((String)session.getAttribute("loginId"))))
			{
				try
				{
					mdao.selectDTOById(writer);
					
					obj = pjp.proceed();
					
					return obj;
				}
				catch(org.springframework.dao.EmptyResultDataAccessException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				
			}
			return "error";
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@Around("execution(* kh.spring.serviceImpl.BoardServiceImpl.update*(..))"
		+ " || execution(* kh.spring.serviceImpl.BoardServiceImpl.delete(..))")
	public Object boardWriterCheck(ProceedingJoinPoint pjp)
	{
		try
		{
			BoardDTO dto = (BoardDTO)pjp.getArgs()[0];
			BoardDTO oldDTO = bdao.selectDTO(dto.getB_seq());
			String writer = oldDTO.getB_writer();
			
			System.out.println();
			
			if(writer.equals(session.getAttribute("loginId")))
			{
				System.out.println(pjp.getSignature().toShortString() + " : " + writer + " : 정상");
				return pjp.proceed();
			}
			else
			{
				System.out.println(pjp.getSignature().toShortString() + " : " + writer + " : 비정상");
				return "error";
			}
			
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
//	@Around("execution(* kh.spring.main.HomeController.portWrite(..))")
//	public Object portLoginCheck(ProceedingJoinPoint pjp)
//	{
//		Object obj;
//		try
//		{
//			HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[0];
//			
//			String name = (String)session.getAttribute("loginId");
//			String resoucesPath = session.getServletContext().getRealPath("/resources/");
//			
//			System.out.println(resoucesPath);
//			
//			
//			MultipartRequest mr = new MultipartRequest(request, resoucesPath);
//			
//			String writer = request.getParameter("writer");
//			
//			System.out.println(writer);
//			
//			if((writer != null) && (writer.equals((String)session.getAttribute("loginId"))))
//			{
//				try
//				{
//					mdao.selectDTOById(writer);
//					
//					obj = pjp.proceed();
//					
//					return obj;
//				}
//				catch(org.springframework.dao.EmptyResultDataAccessException e)
//				{
//					e.printStackTrace();
//				}
//			}
//			return "error";
//		}
//		catch(Throwable e)
//		{
//			e.printStackTrace();
//			return "error";
//		}
//	}
	
//	@Around("execution(* kh.spring.serviceImpl.PortServiceImpl.update*(..))"
//		+ " || execution(* kh.spring.serviceImpl.PortServiceImpl.delete(..))")
//	public Object portWriterCheck(ProceedingJoinPoint pjp)
//	{
//		try
//		{
//			PortDTO dto = (PortDTO)pjp.getArgs()[0];
//			PortDTO oldDTO = pdao.selectDTO(dto.getP_seq());
//			String writer = oldDTO.getP_writer();
//			
//			System.out.println();
//			
//			if(writer.equals(session.getAttribute("loginId")))
//			{
//				System.out.println(pjp.getSignature().toShortString() + " : " + writer + " : 정상");
//				return pjp.proceed();
//			}
//			else
//			{
//				System.out.println(pjp.getSignature().toShortString() + " : " + writer + " : 비정상");
//				return "error";
//			}
//			
//		}
//		catch(Throwable e)
//		{
//			e.printStackTrace();
//			return "error";
//		}
//	}
}
