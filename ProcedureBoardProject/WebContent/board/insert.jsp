<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="board.css"/>
</head>
<body>
   <center>
     <h3>�۾���</h3>
     <form method=post action="insert_ok.jsp">
     <table border=1 width=450 bordercolor="black" cellpadding="0" cellspacing="0">
       <tr>
         <td>
           <table border=0 width=450>
            <tr>
             <td width=10% align=right>�̸�</td>
             <td width=90% align=left>
               <input type=text name=name size=15>
             </td>
            </tr>
             <tr>
             <td width=10% align=right>�̸���</td>
             <td width=90% align=left>
               <input type=text name=email size=45>
             </td>
            </tr>
            <tr>
             <td width=10% align=right>����</td>
             <td width=90% align=left>
               <input type=text name=subject size=45>
             </td>
            </tr>
            <tr>
             <td width=10% align=right>����</td>
             <td width=90% align=left>
               <textarea rows="8" cols="50" name=content></textarea>
             </td>
            </tr>
            
            <tr>
             <td width=10% align=right>���</td>
             <td width=90% align=left>
               <input type=password name=pwd size=10>
             </td>
            </tr>
            <tr>
             <td colspan=2 align=center>
               <input type=submit value=�۾���>
               <input type=button value=��� 
               onclick="javascript:history.back()">
             </td>
            </tr>
            
           </table>
         </td>
       </tr>
     </table>
     </form>
   </center>
</body>
</html>






