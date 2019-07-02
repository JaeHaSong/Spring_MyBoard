package kh.spring.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.MemberDTO;
import kh.spring.dto.PortDTO;
import kh.spring.serviceImpl.BoardServiceImpl;
import kh.spring.serviceImpl.MemberServiceImpl;
import kh.spring.serviceImpl.PortServiceImpl;

@Controller
public class HomeController 
{
	@Autowired
	HttpSession session;
	
	@Autowired
	MemberServiceImpl ms;
	@Autowired
	BoardServiceImpl bs;
	@Autowired
	PortServiceImpl ps;
	
	
	@RequestMapping(value = "/")
	public String home()
	{
		return "home";
	}
	
	
	
	//login & logout start
	@RequestMapping(value = "login_page")
	public String loginPage()
	{
		return "login";
	}
	@RequestMapping(value = "login_do", method = RequestMethod.POST)
	public ModelAndView login(String id, String pw)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect: /");
		return mav;
	}
	@RequestMapping(value = "login_false")
	public ModelAndView loginFalse(String id, String pw)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("loginFalse", "true");
		mav.setViewName("login");
		return mav;
	}
	@RequestMapping(value = "kakao_login_do", method = RequestMethod.POST)
	public Object kakaoLogin(String json)
	{
		try
		{
			return ms.kakaoLogin(json);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
		
		
	}
	@RequestMapping(value = "logout_do")
	public String logout()
	{
		session.invalidate();
		
		return "redirect: /";
	}
	//login & logout end
	
	
	
	//signup start
	@RequestMapping(value = "signup_page")
	public String signupPage()
	{
		return "signup";
	}
	@RequestMapping(value = "signup_do", method = RequestMethod.POST)
	public String signup(String id, String pw, String pwc, String nickname)
	{
		
		try
		{
			ms.insert(new MemberDTO(id, pw, nickname), pwc);
			
			return "redirect: /";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "redirect: error";
		}
		
	}
	//signup end
	
	
	
	//change profile start
	@RequestMapping(value="change_profile_page")
	public String changeProfilePage()
	{
		return "change_profile_img";
	}
	
	@RequestMapping(value="change_profile_do")
	public String changeProfile(HttpServletRequest request)
	{
		try
		{
			ms.changeProfileImg(request);
			return "redirect: close";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	//change profile end
	
	
	
	//close start
	
	@RequestMapping(value="close")
	public String closePage()
	{
		return "close";
	}
	
	//close end
	
	
	
	//board start
	
	@RequestMapping(value="board_list_page")
	public Object boardListPage(String page)
	{
		try
		{
			int currentPage = Integer.parseInt(page);
			
			return bs.paging(currentPage);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value="board_detail_page")
	public Object boardDetail(String b_seq)
	{
		try
		{
			return bs.detail(Integer.parseInt(b_seq));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value="board_write_page")
	public String boardWritePage()
	{
		return "board_write";
	}
	
	@RequestMapping(value="board_write_do", method = RequestMethod.POST)
	public Object boardWrite(String writer,String title, String contents)
	{
		try
		{
			bs.insert(writer, title, contents);
			return "redirect: board_list_page?page=1";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
		
	}
	
	@RequestMapping(value="board_update_page")
	public Object boardUpdatePage(BoardDTO dto)
	{
		try
		{
			return bs.updatePage(dto);
		}
		catch(Exception e)
		{
			return "error";
		}
	}
	
	@RequestMapping(value="board_update_do")
	public Object boardUpdate(BoardDTO dto)
	{
		try
		{
			return bs.update(dto);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value="board_delete_do")
	public Object boardDelete(BoardDTO dto)
	{
		try
		{
			return bs.delete(dto);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping(value="board_img_ajax", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	@ResponseBody
	public Object boardImgAjax(HttpServletRequest request)
	{
		try
		{
			Object returnObj = bs.imgAjax(request);
			if(returnObj == null)
			{
				return "error";
			}
			else
			{
				return returnObj;
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	//board end
	
	
	
	//port start
	
	@RequestMapping(value="port_list_page")
	public Object portlistPage(String page)
	{
		try
		{
			return ps.paging(Integer.parseInt(page));	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value="port_detail_page")
	public Object portDetailPage(String p_seq)
	{
		try
		{
			return ps.detail(Integer.parseInt(p_seq));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	@RequestMapping(value="port_write_page")
	public Object portWritePage()
	{
		return "port_write";
	}
	
	@RequestMapping(value="port_write_do")
	public Object portWrite(HttpServletRequest request)
	{
		try
		{
			return ps.insert(request);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value="port_update_page")
	public Object portUpdatePage(PortDTO dto)
	{
		try
		{
			return ps.updatePage(dto);
		}
		catch(Exception e)
		{
			return "error";
		}
	}
	
	@RequestMapping(value="port_update_do")
	public Object portUpdate(HttpServletRequest request)
	{
		try
		{
			return ps.update(request);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value="port_delete_do")
	public Object portDelete(PortDTO dto)
	{
		try
		{
			return ps.delete(dto);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	//port end
}
