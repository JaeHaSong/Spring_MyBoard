package kh.spring.dao;

import kh.spring.dto.MemberDTO;

public interface MemberDAO 
{
	public int insert(MemberDTO dto) throws Exception;

	public MemberDTO selectDTOById(String id) throws Exception;
	
	public MemberDTO login(String id, String pw) throws Exception;

}
