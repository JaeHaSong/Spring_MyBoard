package kh.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import kh.spring.dto.BoardDTO;

public interface BoardService
{
	public int insert(String writer,String title, String contents) throws Exception;
	
	public List<BoardDTO> selectList(int start, int end) throws Exception;
	
	public int selectCount() throws Exception;
	
	public Object paging(int currentPage) throws Exception;
	
	public Object detail(int b_seq) throws Exception;
	
	public Object updatePage(BoardDTO dto) throws Exception;
	
	public Object update(BoardDTO dto) throws Exception;
	
	public Object delete(BoardDTO dto) throws Exception;
	
	public String imgAjax(HttpServletRequest request) throws Exception; 
}
