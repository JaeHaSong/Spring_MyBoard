package kh.spring.service;

import javax.servlet.http.HttpServletRequest;

import kh.spring.dto.MemberDTO;

public interface MemberService
{
	public int insert(MemberDTO dto, String pwc) throws Exception;
	
	public void changeProfileImg(HttpServletRequest request) throws Exception;
	
	public Object kakaoLogin(String json) throws Exception;
}
