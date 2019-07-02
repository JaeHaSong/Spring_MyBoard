package kh.spring.serviceImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
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

import kh.spring.daoimpl.BoardDAOImpl;
import kh.spring.dto.BoardDTO;
import kh.spring.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService
{
	static final int recordCountPerPage = 10;
	static final int naviCountPerPage = 10;
	
	@Autowired
	BoardDAOImpl bdao;
	
	@Autowired
	HttpSession session;
	
	@Override
	public int insert(String writer, String title, String contents) throws Exception
	{
		BoardDTO dto = new BoardDTO();
		dto.setB_writer(writer);
		dto.setB_title(title);
		dto.setB_contents(contents);
		
		return bdao.insert(dto);
	}

	@Override
	public List<BoardDTO> selectList(int start, int end) throws Exception
	{
		return bdao.selectList(start, end);
	}

	@Override
	public int selectCount() throws Exception
	{
		return bdao.selectCount();
	}

	@Override
	public Object paging(int currentPage) throws Exception
	{
		int pageTotalCount;
		
		boolean needPrev = true;
		boolean needNext = true;
		
		ModelAndView mav = new ModelAndView();
		
		List<BoardDTO> list = bdao.selectList(currentPage*recordCountPerPage-recordCountPerPage+1 , currentPage*recordCountPerPage);
		
		int recordTotalCount = bdao.selectCount();
		
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
	
		mav.setViewName("board_list");
		
		return mav;
	}

	@Override
	public Object detail(int b_seq) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", bdao.selectDTO(b_seq));
		mav.setViewName("board_detail");
		return mav;
	}

	@Override
	public Object updatePage(BoardDTO dto) throws Exception
	{
		try
		{
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("board_write");
			mav.addObject("rewriting", "Y");
			mav.addObject("seq", dto.getB_seq());
			mav.addObject("title", dto.getB_title());
			mav.addObject("contents", dto.getB_contents());
			return mav;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@Override
	@Transactional("txManager")
	public Object update(BoardDTO dto) throws Exception
	{
		try
		{
			BoardDTO oldDTO = bdao.selectDTO(dto.getB_seq());
			
			oldDTO.setB_contents(dto.getB_contents());
			oldDTO.setB_title(dto.getB_title());
			bdao.updateContentsBySeq(dto);
			bdao.updateTitleBySeq(dto);
				
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect: board_list_page");
			mav.addObject("page","1");
			return mav;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public Object delete(BoardDTO dto) throws Exception
	{
		try
		{
			String contents = bdao.selectDTO(dto.getB_seq()).getB_contents();
			int deleteResult = bdao.deleteBySeq(dto);
			
			if(deleteResult == 1)
			{
//				System.out.println(contents);
//				System.out.println(contents.replaceAll(">[^<]*<", "> <"));
				String[] contentsArr = contents.replaceAll(">[^<]*<", "> <").split("> <");
				for(int i = 1 ; i <= contentsArr.length ; i++)
				{
//					System.out.println("실행시작");
					Pattern regex = Pattern.compile("src=\"(.*)\"");
					Matcher regexMatcher = regex.matcher(contentsArr[i-1]);
					
					if(regexMatcher.find()) 
					{
						String filePath = session.getServletContext().getRealPath((regexMatcher.group(1)));
						Paths.get(filePath).toFile().delete();
					}
//					System.out.println("실행끝");
				}
				
				ModelAndView mav = new ModelAndView();
				mav.setViewName("redirect: board_list_page");
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

	@Override
	public String imgAjax(HttpServletRequest request) throws Exception
	{
//		String name = (String)session.getAttribute("loginId");
//		String resoucesPath = session.getServletContext().getRealPath("/resources/"+name+"/");
//		File folder = new File(resoucesPath);
//		if (!folder.exists()) 
//		{
//			folder.mkdir();
//	    }
//		int maxSize = 10 * 1024 * 1024;
//		
//		String fileName = file.getOriginalFilename();
//		
//		File filepath = new File(resoucesPath + fileName);
//		
//		System.out.println(filepath);
//		
//		return "";
		
		String name = (String)session.getAttribute("loginId");
		String resoucesPath = session.getServletContext().getRealPath("/resources/"+name+"/");
		
	    int size = 10 * 1024 * 1024;  // 업로드 사이즈 제한 10M 이하
		
		String fileName = ""; // 파일명
		
		try
		{
	        // 파일업로드 및 업로드 후 파일명 가져옴
			MultipartRequest multi = new MultipartRequest(request, resoucesPath, size, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<?> files = multi.getFileNames();
			String file = (String)files.nextElement(); 
			fileName = multi.getFilesystemName(file); 
			
			int exLength = 0;
			
			if(fileName.charAt(fileName.length()-4) == '.')
			{
				exLength = 4;
			}
			else if(fileName.charAt(fileName.length()-5) == '.')
			{
				exLength = 5;
			}
			else
			{
				return "error";
			}
			System.out.println(fileName.charAt(fileName.length()-exLength));
			
			if(!fileName.substring(fileName.length()-exLength, fileName.length()).equals(".jpg"))
			{
				String originalName = fileName;
				fileName = originalName.substring(0, originalName.length()-exLength) + ".jpg";
				int i = 1;
				while(true)
				{
					if(Paths.get(resoucesPath+fileName).toFile().exists())
					{
						fileName = originalName.substring(0, originalName.length()-exLength)+i + ".jpg";
						i++;
					}
					else
					{
						break;
					}
				}
				
				
				Files.move(new File(resoucesPath+originalName).toPath(), new File(resoucesPath+fileName).toPath(), StandardCopyOption.ATOMIC_MOVE);
			}
			
			System.out.println(fileName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
//		String uploadPath = resoucesPath + fileName;
//		return uploadPath;
		
		return fileName;
	}
	
}
