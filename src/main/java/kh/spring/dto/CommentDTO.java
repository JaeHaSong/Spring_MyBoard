package kh.spring.dto;

import java.sql.Timestamp;

public class CommentDTO
{
	private int c_seq;
	private int c_p_seq;
	private String c_contents;
	private String c_writer;
	private Timestamp c_write_time;
	
	public CommentDTO(){}
	public CommentDTO(int c_seq, int c_p_seq, String c_contents, String c_writer, Timestamp c_write_time)
	{
		this.c_seq = c_seq;
		this.c_p_seq = c_p_seq;
		this.c_contents = c_contents;
		this.c_writer = c_writer;
		this.c_write_time = c_write_time;
	}

	
	
	public int getC_seq()
	{
		return c_seq;
	}
	public void setC_seq(int c_seq)
	{
		this.c_seq = c_seq;
	}
	
	public int getC_p_seq()
	{
		return c_p_seq;
	}
	public void setC_p_seq(int c_p_seq)
	{
		this.c_p_seq = c_p_seq;
	}

	public String getC_contents()
	{
		return c_contents;
	}
	public void setC_contents(String c_contents)
	{
		this.c_contents = c_contents;
	}
	
	public String getC_writer()
	{
		return c_writer;
	}
	public void setC_writer(String c_writer)
	{
		this.c_writer = c_writer;
	}
	
	public Timestamp getC_write_time()
	{
		return c_write_time;
	}
	public void setC_write_time(Timestamp c_write_time)
	{
		this.c_write_time = c_write_time;
	}
    
	
	 
}
