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
 *  </c:forEach> jstl 13장
 *  
 *  
 */
public class BoardDAO {
	private Connection conn;
	private CallableStatement cs;
	//주소얻기
	public void getConnection()
	{
		try
		{
			Context init=new InitialContext();//init안에서 이름을 주고 객체를 가져오기
			DataSource ds=(DataSource)init.lookup("java://comp/env/jdbc/oracle");
			conn=ds.getConnection();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	//반환
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
	//기능
	public List<BoardDTO> boardListData(int page)
	{
		List<BoardDTO> list=new ArrayList<BoardDTO>();
		try
		{
			getConnection();
			int rowSize=10;
			int start=(page*rowSize)-(rowSize-1);//1 부터 시작 2페이지는 11부터...
			int end=page*rowSize;
			String sql="{CALL boardListData(?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setInt(2, start);
			cs.setInt(3, end);
			cs.executeUpdate();
			//커서나오면 ResultSet(전체목록가져오기)
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
		//인터페이스-리턴명과 클래스명 다르면 인터페이스아니면 추상클래스
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
			cs.registerOutParameter(1, OracleTypes.INTEGER);//number아니고 integer
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
			cs.registerOutParameter(1, OracleTypes.INTEGER);//number아니고 integer
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
