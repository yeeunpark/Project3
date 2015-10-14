<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*,java.text.*,com.dao.*"%>
<jsp:useBean id="dao" class="com.dao.BoardDAO"/>
<%
    String strPage=request.getParameter("page");
    if(strPage==null)
    	// list.jsp?page=1
    	strPage="1";
    
    int curpage=Integer.parseInt(strPage);
    List<BoardDTO> list=dao.boardListData(curpage);
    int totalpage=dao.boardTotalPage();
    int count=dao.boardRowCount();
    count=count-((curpage*10)-10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="board.css"/>
</head>
<body>
   <center>
     <h3>�����Խ���</h3>
     <table border=0 width=700>
      <tr> 
       <td align=left>
         <a href="insert.jsp">���</a>
       </td>
      </tr>
    </table>
    <table border=0 width=700>
      <tr bgcolor=#ccffcc>
        <th width=10%>��ȣ</th>
        <th width=45%>����</th>
        <th width=15%>�̸�</th>
        <th width=20%>�ۼ���</th>
        <th width=10%>��ȸ��</th>
      </tr>
      <%
         for(BoardDTO d:list)
         {
      %>
             <tr>
		        <td width=10% align=center><%=count-- %></td>
		        <td width=45% align=left>
		         <%
		             if(d.getGroup_tab()>0)
		             {
		            	 for(int i=0;i<d.getGroup_tab();i++)
		            	 {
		            		 out.println("&nbsp;&nbsp;");
		            	 }
		            	 out.println("<img src=\"image/re_icon.gif\">");
		             }
		         %>
		         <%
		            String msg="�����ڿ� ���� ������ �Խù��Դϴ�";
		            if(!msg.equals(d.getSubject()))
		            {
		         %>
		             <a href="content.jsp?no=<%=d.getNo()%>&page=<%=curpage%>&rn=<%=d.getRownum()%>">
		             <%=d.getSubject() %></a>
		         <%
		            }
		            else
		            {
		         %>
		               <span style="color:gray"><%=d.getSubject() %></span>  
		         <%
		            }
		         %>
		         <%
		             String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		             String dbday=d.getRegdate().toString();
		             if(today.equals(dbday))
		             {
		            	 out.println("<sup><img src=\"image/new.gif\"></sup>");
		             }
		         %>
		        </td>
		        <td width=15% align=center><%=d.getName() %></td>
		        <td width=20% align=center><%=d.getRegdate().toString()%></td>
		        <td width=10% align=center><%=d.getHit() %></td>
		      </tr>
      <%
         }
      %>
     </table>
     <hr width=700>
     <table border=0 width=700>
      <tr>
        <td align=right>
        <%=curpage %>page / <%=totalpage %> pages
        </td>
      </tr>
     </table>
   </center>
</body>
</html>






