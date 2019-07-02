package kh.spring.dto;

public class MemberDTO 
{
	private String m_id;
	private String m_pw;
	private String m_nickname;
	
	public MemberDTO() 
	{
		super();
	}
	public MemberDTO(String m_id, String m_pw, String m_nickname) 
	{
		super();
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_nickname = m_nickname;
	}
	
	public String getM_id() 
	{
		return m_id;
	}
	public void setM_id(String m_id) 
	{
		this.m_id = m_id;
	}
	public String getM_pw() 
	{
		return m_pw;
	}
	public void setM_pw(String m_pw) 
	{
		this.m_pw = m_pw;
	}
	public String getM_nickname() 
	{
		return m_nickname;
	}
	public void setM_nickname(String m_nickname) 
	{
		this.m_nickname = m_nickname;
	}
	
}
