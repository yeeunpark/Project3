<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.dao.*"%>
<jsp:useBean id="dao" class="com.dao.BoardDAO"/>
<%
    String strNo=request.getParameter("no");
    String strPage=request.getParameter("page");
    String strRn=request.getParameter("rn");
    // DB����
    BoardDTO d=dao.boardContentData(Integer.parseInt(strNo));
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
     <h3>���뺸��</h3>
     <table border=1 width=500 cellpadding="0" cellspacing="0">
       <tr height=27>
        <td width=20% align=center bgcolor=#ccccff>��ȣ</td>
        <td width=30% align=center><%=d.getNo() %></td>
        <td width=20% align=center bgcolor=#ccccff>�ۼ���</td>
        <td width=30% align=center><%=d.getRegdate().toString() %></td>
       </tr>
       <tr height=27>
        <td width=20% align=center bgcolor=#ccccff>�̸�</td>
        <td width=30% align=center><%=d.getName() %></td>
        <td width=20% align=center bgcolor=#ccccff>��ȸ��</td>
        <td width=30% align=center><%=d.getHit() %></td>
       </tr>
       <tr height=27>
        <td width=20% align=center bgcolor=#ccccff>����</td>
        <td colspan="3" align=left><%=d.getSubject() %></td>
       </tr>
       <tr>
         <td align=left valign="top" height=200
          colspan="4">
          <%=d.getContent() %>
         </td>
       </tr>
      
     </table>
     <table border=0 width=500>
       <tr>
         <td align=right>
           <a href="update.jsp?no=<%=strNo%>&page=<%=strPage%>">����</a>&nbsp;
           <a href="delete.jsp?no=<%=strNo%>&page=<%=strPage%>">����</a>&nbsp;
           <a href="list.jsp?page=<%=strPage%>">���</a>
         </td>
       </tr>
     </table>
  </center>
</body>
</html>