package kh.spring.serviceImpl;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kh.spring.dao.PortDAO;
import kh.spring.dto.PortDTO;

@Service
public class PortServiceImpl
{
	static final int recordCountPerPage = 3;
	static final int naviCountPerPage = 5;
	
	@Autowired
	HttpSession session;
	@Autowired
	PortDAO pdao;
	
	public Object insert(HttpServletRequest request) throws Exception
	{
		String name = (String)session.getAttribute("loginId");
		String resoucesPath = session.getServletContext().getRealPath("/resources/"+name+"/");
		
		System.out.println(resoucesPath);
		
		File folder = new File(resoucesPath);
		if (!folder.exists()) 
		{
			folder.mkdir();
	    }
		
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest mr = new MultipartRequest(request, resoucesPath, maxSize,"UTF-8", new DefaultFileRenamePolicy());
		String file = mr.getFilesystemName("img_file");
		
		boolean fileCheck = false;
		
		String p_thumnail = "";
		if(file == null)
		{
			p_thumnail = "/resources/default_img.jpg";
			fileCheck = true;
		}
		else
		{
			if(!file.substring(file.length()-3, file.length()).equals("jpg"))
			{
				p_thumnail = "/resources/default_img.jpg";
				fileCheck = true;
			}
			else
			{
				p_thumnail = "/resources/"+name+"/"+file;
			}
			
		}
		
		if(fileCheck)
		{
			Paths.get(resoucesPath + file).toFile().delete();
		}
		
		String p_title =  mr.getParameter("title");
		String p_contents =  mr.getParameter("contents");
		String p_writer = name;
		
		System.out.println(p_thumnail);
		System.out.println(p_title);
		System.out.println(p_contents);
		System.out.println(p_writer);
		
		String[] contentsArr = p_contents.replaceAll(">[^<]*<", "> <").split("> <");
		for(int i = 1 ; i <= contentsArr.length ; i++)
		{
			System.out.println("실행시작");
			String pat = "src=\".{1,"+contentsArr[i-1].length()+"}\\.";
			Pattern regex = Pattern.compile(pat);
			Matcher regexMatcher = regex.matcher(contentsArr[i-1]);
			
			if(regexMatcher.find()) 
			{
				System.out.println(contentsArr[i-1]);
				System.out.println(regexMatcher.group(0));
				p_contents = p_contents.replace(contentsArr[i-1], "img style=\"width: 500px;\" "+regexMatcher.group(0)+"jpg\"");
			}
			
			System.out.println("실행끝");
		}
		
		
		int insertResult = pdao.insert(new PortDTO(p_thumnail, p_title, p_contents, p_writer));
		
		if(insertResult == 1)
		{
			return "redirect: port_list_page?page=1";
		}
		else
		{
			return "error";
		}
	}

	public Object paging(int currentPage) throws Exception
	{
		int pageTotalCount;
		
		boolean needPrev = true;
		boolean needNext = true;
		
		ModelAndView mav = new ModelAndView();
		
		List<PortDTO> list = pdao.selectList(currentPage*recordCountPerPage-recordCountPerPage+1 , currentPage*recordCountPerPage);
		
		int recordTotalCount = pdao.selectCount();
		
		if( recordTotalCount % recordCountPerPage == 0)
		{
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		else
		{
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}

		if(currentPage < 1)
		{
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount)
		{
			currentPage = pageTotalCount;
		}
		
		int startNavi = (currentPage - 1) / naviCountPerPage * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > pageTotalCount)
		{
			endNavi = pageTotalCount;
		}
		
		if(startNavi == 1)
		{
			needPrev = false;
		}
		if(endNavi == pageTotalCount)
		{
			needNext = false;
		}
		
		mav.addObject("list", list);
		mav.addObject("listsize", list.size());
		
		mav.addObject("currentPage", currentPage);
		mav.addObject("needPrev", needPrev);
		mav.addObject("needNext", needNext);
		mav.addObject("startNavi", startNavi);
		mav.addObject("endNavi", endNavi);
	
		mav.setViewName("port_list");
		
		return mav;
	}

	public Object detail(int seq) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", pdao.selectDTO(seq));
		mav.setViewName("port_detail");
		return mav;
	}
	
	public Object updatePage(PortDTO dto) throws Exception
	{
		try
		{
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("port_write");
			mav.addObject("rewriting", "Y");
			mav.addObject("seq", dto.getP_seq());
			mav.addObject("title", dto.getP_title());
			mav.addObject("contents", dto.getP_contents());
			return mav;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@Transactional("txManager")
	public Object update(HttpServletRequest request) throws Exception
	{
		try
		{
			String name = (String)session.getAttribute("loginId");
			String resoucesPath = session.getServletContext().getRealPath("/resources/"+name+"/");
			
			System.out.println(resoucesPath);
			
			File folder = new File(resoucesPath);
			if (!folder.exists()) 
			{
				folder.mkdir();
		    }
			
			int maxSize = 10 * 1024 * 1024;
			MultipartRequest mr = new MultipartRequest(request, resoucesPath, maxSize,"UTF-8", new DefaultFileRenamePolicy());
			String file = mr.getFilesystemName("img_file");
			
			int seq = Integer.parseInt(mr.getParameter("seq"));
			String contents = mr.getParameter("contents");
			String title = mr.getParameter("title");
			
			PortDTO dto = new PortDTO(seq, "/resources/"+name+"/"+file, title, contents, name, null);
			
			PortDTO oldDTO = pdao.selectDTO(dto.getP_seq());
			
			if(file != null)
			{
				new File(resoucesPath + file);
				pdao.updateThumnailBySeq(dto);
			}
			
			String p_contents = dto.getP_contents();
			String[] contentsArr = p_contents.replaceAll(">[^<]*<", "> <").split("> <");
			for(int i = 1 ; i <= contentsArr.length ; i++)
			{
				System.out.println("실행시작");
				String pat = "src=\".{1,"+contentsArr[i-1].length()+"}\\.";
				Pattern regex = Pattern.compile(pat);
				Matcher regexMatcher = regex.matcher(contentsArr[i-1]);
				
				if(regexMatcher.find()) 
				{
					System.out.println(contentsArr[i-1]);
					System.out.println(regexMatcher.group(0));
					p_contents = p_contents.replace(contentsArr[i-1], "img style=\"width: 500px;\" "+regexMatcher.group(0)+"jpg\"");
				}
				
				System.out.println("실행끝");
			}
			oldDTO.setP_contents(p_contents);
			oldDTO.setP_title(dto.getP_title());
			pdao.updateContentsBySeq(oldDTO);
			pdao.updateTitleBySeq(oldDTO);
				
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect: port_list_page");
			mav.addObject("page","1");
			return mav;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	public Object delete(PortDTO dto) throws Exception
	{
		try
		{
			String thumnail = pdao.selectDTO(dto.getP_seq()).getP_thumnail();
			String contents = pdao.selectDTO(dto.getP_seq()).getP_contents();
			int deleteResult = pdao.deleteBySeq(dto);
			
			if(deleteResult == 1)
			{
				String[] contentsArr = contents.replaceAll(">[^<]*<", "> <").split("> <");
				for(int i = 1 ; i <= contentsArr.length ; i++)
				{
					Pattern regex = Pattern.compile("src=\"(.*)\"");
					Matcher regexMatcher = regex.matcher(contentsArr[i-1]);
					
					if(regexMatcher.find()) 
					{
						String filePath = session.getServletContext().getRealPath((regexMatcher.group(1)));
						Paths.get(filePath).toFile().delete();
					}
				}
				
				if(!thumnail.equals("/resources/default_img.jpg"))
				{
					Paths.get(session.getServletContext().getRealPath(thumnail)).toFile().delete();
				}
				
				ModelAndView mav = new ModelAndView();
				mav.setViewName("redirect: port_list_page");
				mav.addObject("page","1");
				return mav;
			}
			else
			{
				return "error";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
}
