package kh.spring.dto;

import java.sql.Timestamp;

public class LogDTO
{
	private String l_ip;
	private String l_target;
	private String l_behavior;
	private String l_type;
	private Timestamp l_time;
	
	public LogDTO()
	{
		super();
	}
	public LogDTO(String l_ip, String l_target, String l_behavior, String l_type)
	{
		super();
		this.l_ip = l_ip;
		this.l_target = l_target;
		this.l_behavior = l_behavior;
		this.l_type = l_type;
	}
	public LogDTO(String l_ip, String l_target, String l_behavior, String l_type, Timestamp l_time)
	{
		super();
		this.l_ip = l_ip;
		this.l_target = l_target;
		this.l_behavior = l_behavior;
		this.l_type = l_type;
		this.l_time = l_time;
	}
	
	public String getL_ip()
	{
		return l_ip;
	}
	
	public void setL_ip(String l_ip)
	{
		this.l_ip = l_ip;
	}
	
	public String getL_target()
	{
		return l_target;
	}
	
	public void setL_target(String l_target)
	{
		this.l_target = l_target;
	}
	
	public String getL_behavior()
	{
		return l_behavior;
	}
	
	public void setL_behavior(String l_behavior)
	{
		this.l_behavior = l_behavior;
	}
	
	public String getL_type()
	{
		return l_type;
	}
	
	public void setL_type(String l_type)
	{
		this.l_type = l_type;
	}
	
	public Timestamp getL_time()
	{
		return l_time;
	}
	
	public void setL_time(Timestamp l_time)
	{
		this.l_time = l_time;
	}
	
	
	
}
