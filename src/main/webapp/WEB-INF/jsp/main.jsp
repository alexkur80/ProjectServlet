<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>main.jsp</title>
<link href="news.css" rel="stylesheet">
</head>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.local" var="locale" />

<fmt:message bundle="${locale}" key="reference.russian" var="button_ru" />
<fmt:message bundle="${locale}" key="reference.english" var="button_en" />
<fmt:message bundle="${locale}" key="table.logo" var="table_logo" />
<fmt:message bundle="${locale}" key="button.create-news" var="button_create_news" />
<fmt:message bundle="${locale}" key="button.list-all-news" var="button_list_all_news" />
<fmt:message bundle="${locale}" key="copyright" var="copyright" />
<fmt:message bundle="${locale}" key="form.welcome" var="form_welcome" />
<fmt:message bundle="${locale}" key="result.operation.create" var="result_operation_create" />
<fmt:message bundle="${locale}" key="result.operation.update" var="result_operation_update" />
<fmt:message bundle="${locale}" key="result.operation-del" var="result_operation_del" />
<fmt:message bundle="${locale}" key="result.operation-del-group" var="result_operation_del_group" />


<c:url var="locale_ru" value="controller"  >
    <c:param name="command" value="localization" />
    <c:param name="locale" value="ru" />
    <c:param name="current_command" value="welcome_page" />
</c:url>

<c:url var="locale_en" value="controller"  >
    <c:param name="command" value="localization" />
    <c:param name="locale" value="en" />
    <c:param name="current_command" value="welcome_page" />
</c:url>


<body>

<table border="1">

  <tr>
  <td><font color="red"><c:out value="${table_logo}" /></font></td>
    <td  align="right" class="classverticalalignbottom">
    <font size="1" color="black">

        <a href="${locale_en}">${button_en}</a>
        <a href="${locale_ru}">${button_ru}</a>
    </font> 
    </td>
  </tr>
  
  <tr>
  <td width="25%">
  
 <form action="controller" method="GET">
<input type="hidden" name="command" value="fill_news" /> 
<input type="submit" name="submit" value = "${button_create_news}" class="myButton">
</form>
    
       <form action="controller" method="GET">
<input type="hidden" name="command" value="find_all_news" /> 
          <input type="submit" name="submit" value = "${button_list_all_news}" class="myButton">

</form>
  </td>
  <td align="center">
  <c:out value="${form_welcome}" /> <br><br>

<c:set var = "message" scope="session" value = "${sessionScope.result_operation}"/>
  
  <c:choose>
  <c:when test="${message eq 'News added successfully'}">
    ${result_operation_create}
  </c:when>
   
   <c:when test="${message eq 'News deleted successfully'}">
   ${result_operation_del}
  </c:when>
  
  <c:when test="${message eq 'Group news deleted successfully'}">
   ${result_operation_del_group}
  </c:when>
  
  <c:when test="${message eq 'News updated successfully'}">
   ${result_operation_update}
  </c:when>
  
   <c:otherwise>
      ${message}
   </c:otherwise>
    
  </c:choose>
  
<c:remove var="result_operation" scope="session" />

  	</td>
  </tr>

    <tr>

  <td colspan="2" align="center"><b><font size="1" color="black" face="Arial"><center><c:out value="${copyright}" /></center></font></b></td>
  </tr>
</table>
</body>
</html>