package kh.spring.service;

import kh.spring.dto.CommentDTO;

public interface CommentService
{
	public Object insert(CommentDTO dto) throws Exception;
}
