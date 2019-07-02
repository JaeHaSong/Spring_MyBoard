
package kh.spring.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kh.spring.daoimpl.MemberDAOImpl;
import kh.spring.dto.MemberDTO;
import kh.spring.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService
{
	
	@Autowired
	HttpSession session;
	
	@Autowired
	MemberDAOImpl mdao;
	
	@Transactional("txManager")
	@Override
	public int insert(MemberDTO dto, String pwc) throws Exception
	{
		return mdao.insert(new MemberDTO(dto.getM_id(), dto.getM_pw(), dto.getM_nickname()));
	}
	
	@Override
	public void changeProfileImg(HttpServletRequest request) throws Exception
	{
		String name = (String)session.getAttribute("loginId");
		
		String resoucesPath = session.getServletContext().getRealPath("/resources/" + name + "/");
		File folder = new File(resoucesPath);
		
		if( ! folder.exists())
		{
			folder.mkdir();
		}
		
		int maxSize = 10 * 1024 * 1024;
		
		MultipartRequest mr = new MultipartRequest(request, resoucesPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String file = mr.getFilesystemName("img_file");
		
		File savingFile = new File(resoucesPath + file);
		File changeFile = new File(resoucesPath + name + "_profile_img.jpg");
		
		System.out.println(Files.move(savingFile.toPath(), changeFile.toPath(), StandardCopyOption.REPLACE_EXISTING));
		
		if(changeFile.exists())
		{
			
			for(int timeout = 100 ; timeout > 0 ; timeout -- )
			{
				RandomAccessFile ran = null;
				
				try
				{
					ran = new RandomAccessFile(changeFile, "rw");
//					Thread.sleep(100);
					break; // no errors, done waiting
				}
				catch(Exception ex)
				{
					System.out.println("timeout: " + timeout + ": " + ex.getMessage());
				}
				finally
				{
					if(ran != null) try
					{
						ran.close();
					}
					catch(IOException ex)
					{
						// do nothing
					}
					
					ran = null;
				}
				
				try
				{
					Thread.sleep(100); // wait a bit then try again
				}
				catch(InterruptedException ex)
				{
					// do nothing
				}
			}
			
		}
		else
		{
			System.out.println("File does not exist");
		}
		
		
//		for(int i = 1 ; i <= 10000 ; i++)
//		{
//			if(savingFile.exists())
//			{
//				System.out.println(i);
//			}
//			else
//			{
//				break;
//			}
//		}
	}
	
	@Override
	public Object kakaoLogin(String json) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json);
		
		JsonObject root = je.getAsJsonObject();
		JsonObject prop = root.get("properties").getAsJsonObject();
		JsonObject account = root.get("kakao_account").getAsJsonObject();
		
		String id = "k" + root.get("id").toString();
		String nickname = prop.get("nickname").toString().replace("\"", "");
		
		try
		{
			MemberDTO dto = mdao.selectDTOById(id);
		}
		catch(EmptyResultDataAccessException eeeeeeee)
		{
//			String ageRange = "";
//			String birthday = "";
//			String gender = "";
//			
//			try
//			{
//				ageRange = account.get("age_range").toString().replace("\"", "");
//			}
//			catch(NullPointerException e){}
//
//			try
//			{
//				birthday = account.get("birthday").toString().replace("\"", "");
//			}
//			catch(NullPointerException e){}
//
//			try
//			{
//				gender = account.get("gender").toString().replace("\"", "");
//
//				if((gender.equals("male")) || (gender.equals("M")))
//				{
//					gender = "M";
//				}else if((gender.equals("female")) || (gender.equals("F")))
//				{
//					gender = "F";
//				}
//			}
//			catch(NullPointerException e){}
//			
//			System.out.println(nickname);
//			System.out.println(birthday);
//			System.out.println(ageRange);
//			System.out.println(gender);
			
			mdao.insert(new MemberDTO(id, "12345678901234567890", nickname));
			
		}
		
		session.setAttribute("loginId", id);
		mav.setViewName("redirect: /");
		return mav;
	}
}
