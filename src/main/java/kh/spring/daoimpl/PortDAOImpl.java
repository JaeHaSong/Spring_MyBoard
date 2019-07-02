package kh.spring.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dao.PortDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.PortDTO;

@Repository
public class PortDAOImpl implements PortDAO
{
	@Autowired
	SqlSessionTemplate sst;
	
	@Override
	public int insert(PortDTO dto) throws Exception
	{
		return sst.insert("PortDAO.insert", dto);
	}

	@Override
	public List<PortDTO> selectList(int start, int end) throws Exception
	{
		return sst.selectList("PortDAO.selectList", new Object[] {start, end});
	}
	@Override
	public int selectCount() throws Exception
	{
		return sst.selectOne("PortDAO.selectCount");
	}
	@Override
	public PortDTO selectDTO(int p_seq) throws Exception
	{
		return sst.selectOne("PortDAO.selectDTO", new Object[] {p_seq});
	}
	
	
	
	@Override
	public int updateTitleBySeq(PortDTO dto) throws Exception
	{
		return sst.update("PortDAO.updateTitleBySeq", dto);
	}
	@Override
	public int updateContentsBySeq(PortDTO dto) throws Exception
	{
		return sst.update("PortDAO.updateContentsBySeq", dto);
	}
	@Override
	public int updateThumnailBySeq(PortDTO dto) throws Exception
	{
		return sst.update("PortDAO.updateThumnailBySeq", dto);
	}

	
	
	@Override
	public int deleteBySeq(PortDTO dto) throws Exception
	{
		return sst.delete("PortDAO.deleteBySeq", dto);
	}
	
}
