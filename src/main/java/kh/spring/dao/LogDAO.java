package kh.spring.dao;

import kh.spring.dto.LogDTO;

public interface LogDAO
{
	public int insert(LogDTO dto) throws Exception;
}
