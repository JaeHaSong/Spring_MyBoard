package kh.spring.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO
{

	@Autowired
	private JdbcTemplate template;
	String sql;
	
	@Override
	public int insert(MemberDTO dto) throws Exception 
	{
		sql = "insert into member_table values(?,?,?)";
		
		return template.update(sql, dto.getM_id(), dto.getM_pw(), dto.getM_nickname());
	}

	@Override
	public MemberDTO selectDTOById(String id) throws Exception 
	{
		sql = "select * from member_table where ( m_id = ? )";
		return template.queryForObject(sql, new Object[] {id}, new RowMapper<MemberDTO>()
		{
			@Override
			public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				MemberDTO dto = new MemberDTO();
				dto.setM_id(rs.getString("m_id"));
				dto.setM_nickname(rs.getString("m_nickname"));
				
				return dto;
			}
			
		});
	}
	
	@Transactional("txManager")
	@Override
	public MemberDTO login(String id, String pw) throws Exception
	{
		sql = "select * from member_table where ( m_id = ? ) and ( m_pw = ? )";
		return template.queryForObject(sql, new Object[] {id, pw}, new RowMapper<MemberDTO>()
		{
			@Override
			public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				MemberDTO dto = new MemberDTO();
				dto.setM_id(rs.getString("m_id"));
				dto.setM_nickname(rs.getString("m_nickname"));
				
				return dto;
			}
			
		});
	}
	
}
