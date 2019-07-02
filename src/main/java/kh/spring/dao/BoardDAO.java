package kh.spring.dao;

import java.util.List;

import kh.spring.dto.BoardDTO;

public interface BoardDAO
{
	public int insert(BoardDTO dto) throws Exception;
	
	public List<BoardDTO> selectList(int start, int end) throws Exception;
	
	public int selectCount() throws Exception;
	
	public BoardDTO selectDTO(int b_seq) throws Exception;
	
	public int updateTitleBySeq(BoardDTO dto) throws Exception;
	
	public int updateContentsBySeq(BoardDTO dto) throws Exception;
	
	public int deleteBySeq(BoardDTO dto) throws Exception;
}
