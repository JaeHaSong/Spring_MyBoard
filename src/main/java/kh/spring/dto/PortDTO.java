package kh.spring.dto;

import java.sql.Timestamp;

public class PortDTO
{
	private int p_seq;
	private String p_thumnail;
	private String p_title;
	private String p_contents;
	private String p_writer;
	private Timestamp p_write_time;
	
	public PortDTO(){}
	
	public PortDTO(String p_thumnail, String p_title, String p_contents, String p_writer)
	{
		this.p_thumnail = p_thumnail;
		this.p_title = p_title;
		this.p_contents = p_contents;
		this.p_writer = p_writer;
	}

	public PortDTO(int p_seq, String p_thumnail, String p_title, String p_contents, String p_writer, Timestamp p_write_time)
	{
		this.p_seq = p_seq;
		this.p_thumnail = p_thumnail;
		this.p_title = p_title;
		this.p_contents = p_contents;
		this.p_writer = p_writer;
		this.p_write_time = p_write_time;
	}

	
	public int getP_seq()
	{
		return p_seq;
	}
	public void setP_seq(int p_seq)
	{
		this.p_seq = p_seq;
	}
	
	public String getP_thumnail()
	{
		return p_thumnail;
	}
	public void setP_thumnail(String p_thumnail)
	{
		this.p_thumnail = p_thumnail;
	}

	
	public String getP_title()
	{
		return p_title;
	}
	public void setP_title(String p_title)
	{
		this.p_title = p_title;
	}

	
	public String getP_contents()
	{
		return p_contents;
	}
	public void setP_contents(String p_contents)
	{
		this.p_contents = p_contents;
	}

	
	public String getP_writer()
	{
		return p_writer;
	}
	public void setP_writer(String p_writer)
	{
		this.p_writer = p_writer;
	}

	
	public Timestamp getP_write_time()
	{
		return p_write_time;
	}
	public void setP_write_time(Timestamp p_write_time)
	{
		this.p_write_time = p_write_time;
	}
	
	
}
