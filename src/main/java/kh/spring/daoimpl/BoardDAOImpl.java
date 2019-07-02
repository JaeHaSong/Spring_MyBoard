package kh.spring.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO
{
	@Autowired
	private SqlSessionTemplate sst;
	
	@Override
	public int insert(BoardDTO dto) throws Exception
	{
		return sst.insert("BoardDAO.insert", dto);
	}

	@Override
	public List<BoardDTO> selectList(int start, int end) throws Exception
	{
		return sst.selectList("BoardDAO.selectList", new Object[] {start, end});
	}

	@Override
	public int selectCount() throws Exception
	{
		return sst.selectOne("BoardDAO.selectCount");
	}

	@Override
	public BoardDTO selectDTO(int b_seq) throws Exception
	{
		return sst.selectOne("BoardDAO.selectDTO", new Object[] {b_seq});
	}

	@Override
	public int updateTitleBySeq(BoardDTO dto) throws Exception
	{
		return sst.update("BoardDAO.updateTitleBySeq", dto);
	}
	@Override
	public int updateContentsBySeq(BoardDTO dto) throws Exception
	{
		return sst.update("BoardDAO.updateContentsBySeq", dto);
	}

	@Override
	public int deleteBySeq(BoardDTO dto) throws Exception
	{
		return sst.delete("BoardDAO.deleteBySeq", dto);
	}

	
}
