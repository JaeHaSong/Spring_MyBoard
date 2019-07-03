package kh.spring.daoimpl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dao.CommentDAO;
import kh.spring.dto.CommentDTO;

@Repository
public class CommentDAOImpl implements CommentDAO
{
	@Autowired
	SqlSessionTemplate sst;
	
	
	
	@Override
	public int insert(CommentDTO dto) throws Exception
	{
		return sst.insert("CommentDAO.insert",dto);
	}
	
}
