package kh.spring.dao;

import java.util.List;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.PortDTO;

public interface PortDAO
{
	
	public int insert(PortDTO dto) throws Exception;
	
	public List<PortDTO> selectList(int start, int end) throws Exception;
	
	public int selectCount() throws Exception;
	
	public PortDTO selectDTO(int b_seq) throws Exception;
	
	public int updateTitleBySeq(PortDTO dto) throws Exception;
	
	public int updateContentsBySeq(PortDTO dto) throws Exception;
	
	public int updateThumnailBySeq(PortDTO dto) throws Exception;
	
	public int deleteBySeq(PortDTO dto) throws Exception;
}

