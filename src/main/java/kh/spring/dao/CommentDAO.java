package kh.spring.dao;

import kh.spring.dto.CommentDTO;

public interface CommentDAO
{
	public int insert(CommentDTO dto) throws Exception;
}
