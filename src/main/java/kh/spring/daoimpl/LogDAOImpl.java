package kh.spring.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.dao.LogDAO;
import kh.spring.dto.LogDTO;

@Repository
public class LogDAOImpl implements LogDAO
{
	@Autowired
	private JdbcTemplate template;
	String sql;
	
	@Transactional("txManager")
	@Override
	public int insert(LogDTO dto) throws Exception
	{
		sql = "insert into log_table values(?,?,?,?,default)";
		
		return template.update(sql, dto.getL_ip(), dto.getL_target(), dto.getL_behavior(), dto.getL_type());
	}
	
}
