package com.dao;
import java.sql.*;
import javax.sql.*;

import oracle.jdbc.internal.OracleTypes;

import javax.naming.*;
import java.util.*;
/*
 * 	for(BoardDTO  d:list)
 *  {
 *  }
 *  <c:forEach var="d" items="list">
 *   <tr>
 *   
 *   </tr>
 *  </c:forEach> jstl 13��
 *  
 *  
 */
public class BoardDAO {
	private Connection conn;
	private CallableStatement cs;
	//�ּҾ��
	public void getConnection()
	{
		try
		{
			Context init=new InitialContext();//init�ȿ��� �̸��� �ְ� ��ü�� ��������
			DataSource ds=(DataSource)init.lookup("java://comp/env/jdbc/oracle");
			conn=ds.getConnection();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	//��ȯ
	public void disConnection()
	{
		try
		{
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	//���
	public List<BoardDTO> boardListData(int page)
	{
		List<BoardDTO> list=new ArrayList<BoardDTO>();
		try
		{
			getConnection();
			int rowSize=10;
			int start=(page*rowSize)-(rowSize-1);//1 ���� ���� 2�������� 11����...
			int end=page*rowSize;
			String sql="{CALL boardListData(?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setInt(2, start);
			cs.setInt(3, end);
			cs.executeUpdate();
			//Ŀ�������� ResultSet(��ü��ϰ�������)
			ResultSet rs=(ResultSet)cs.getObject(1);
			while(rs.next())
			{
				BoardDTO d=new BoardDTO();
				d.setNo(rs.getInt(1));
				d.setSubject(rs.getString(2));
				d.setName(rs.getString(3));
				d.setRegdate(rs.getDate(4));
				d.setHit(rs.getInt(5));
				d.setGroup_tab(rs.getInt(6));
				d.setRownum(rs.getInt(7));
				list.add(d);
			}
			rs.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getLocalizedMessage());
		}
		finally
		{
			disConnection();
		}
		return list;
		//�������̽�-���ϸ�� Ŭ������ �ٸ��� �������̽��ƴϸ� �߻�Ŭ����
	}
	public void boardInsert(BoardDTO d)
	{
		try
		{
			getConnection();
			String sql="{CALL boardInsert(?,?,?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setString(1, d.getName());
			cs.setString(2, d.getEmail());
			cs.setString(3, d.getSubject());
			cs.setString(4, d.getContent());
			cs.setString(5, d.getPwd());
			cs.execute();
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			disConnection();
		}
	}
	public BoardDTO boardContentData(int no)
	{
		BoardDTO d=new BoardDTO();
		try
		{
			getConnection();
			String sql="{CALL boardContentData(?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.executeUpdate();
			ResultSet rs=(ResultSet)cs.getObject(2);
			rs.next();
			d.setNo(rs.getInt(1));
			d.setName(rs.getString(2));
			d.setEmail(rs.getString(3));
			d.setSubject(rs.getString(4));
			d.setContent(rs.getString(5));
			d.setRegdate(rs.getDate(6));
			d.setHit(rs.getInt(7));
			rs.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			disConnection();
		}
		return d;
	}
	public int boardTotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="{CALL boardRowCount(?)}";
			cs=conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.INTEGER);//number�ƴϰ� integer
			cs.executeUpdate();
			int count=cs.getInt(1);
			total=(int)(Math.ceil(count/10.0));
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			disConnection();
		}
		return total;
	}
	public int boardRowCount()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="{CALL boardRowCount(?)}";
			cs=conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.INTEGER);//number�ƴϰ� integer
			cs.executeUpdate();
			total=cs.getInt(1);
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			disConnection();
		}
		return total;
	}
	
}
