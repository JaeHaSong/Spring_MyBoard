package kh.spring.dto;

import java.sql.Timestamp;

public class BoardDTO
{
    private int b_seq;
    private String b_title;
    private String b_contents;
    private String b_writer;
    private Timestamp b_write_date;
	
    public BoardDTO()
	{
		super();
	}
	public BoardDTO(String b_title, String b_contents, String b_writer)
	{
		super();
		this.b_title = b_title;
		this.b_contents = b_contents;
		this.b_writer = b_writer;
	}
	public BoardDTO(int b_seq, String b_title, String b_contents, String b_writer, Timestamp b_write_date)
	{
		super();
		this.b_seq = b_seq;
		this.b_title = b_title;
		this.b_contents = b_contents;
		this.b_writer = b_writer;
		this.b_write_date = b_write_date;
	}
	
	public int getB_seq()
	{
		return b_seq;
	}
	
	public void setB_seq(int b_seq)
	{
		this.b_seq = b_seq;
	}
	
	public String getB_title()
	{
		return b_title;
	}
	
	public void setB_title(String b_title)
	{
		this.b_title = b_title;
	}
	
	public String getB_contents()
	{
		return b_contents;
	}
	
	public void setB_contents(String b_contents)
	{
		this.b_contents = b_contents;
	}
	
	public String getB_writer()
	{
		return b_writer;
	}
	
	public void setB_writer(String b_writer)
	{
		this.b_writer = b_writer;
	}
	
	public Timestamp getB_write_date()
	{
		return b_write_date;
	}
	
	public void setB_write_date(Timestamp b_write_date)
	{
		this.b_write_date = b_write_date;
	}
    
    
}
