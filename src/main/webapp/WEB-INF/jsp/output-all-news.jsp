<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>output_all_news.jsp</title>
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
<fmt:message bundle="${locale}" key="form.logo" var="form_logo" />
<fmt:message bundle="${locale}" key="form.section" var="form_section" />
<fmt:message bundle="${locale}" key="form.author" var="form_author" />
<fmt:message bundle="${locale}" key="form.brief" var="form_brief" />
<fmt:message bundle="${locale}" key="form.content" var="form_content" />
<fmt:message bundle="${locale}" key="reference.delete" var="reference_delete" />
<fmt:message bundle="${locale}" key="button.delete-selected" var="button_delete_selected" />
<fmt:message bundle="${locale}" key="button.back" var="button_back" />
<fmt:message bundle="${locale}" key="reference.view" var="reference_view" />
<fmt:message bundle="${locale}" key="reference.edit" var="reference_edit" />
<fmt:message bundle="${locale}" key="reference.delete" var="reference_delete" />
<fmt:message bundle="${locale}" key="copyright" var="copyright" />
<fmt:message bundle="${locale}" key="operation.view-all-news.logo" var="operation_view_all_news_logo" />
<fmt:message bundle="${locale}" key="result.empty.table" var="result_empty_table" />

<c:url var="locale_ru" value="controller"  >
    <c:param name="command" value="localization" />
    <c:param name="locale" value="ru" />
    <c:param name="current_command" value="find_all_news" />
</c:url>

<c:url var="locale_en" value="controller"  >
    <c:param name="command" value="localization" />
    <c:param name="locale" value="en" />
    <c:param name="current_command" value="find_all_news" />
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
<input type="submit" name="submit" value="${button_list_all_news}" class="myButton"/>

</form>
  </td>
  <td>



<form action="controller" method="POST">
<input type="hidden" name="command" value="delete_news_selected" />

    <fieldset>

  <legend><c:out value="${form_logo}" /></legend>
 <c:out value="${operation_view_all_news_logo}" />

  <c:forEach items="${news}" var="news">
	<table class="class1" border="1">

  <tr>
    <td>
      <table border=1 class="classhorisontalalightop">
  <tr>
    <td width="15%"> <c:out value="${form_author}" /></td>
     <td> <c:out value="${news.author}" /> </td>
  </tr>

    <tr>
    <td><c:out value="${form_section}" /></td>
     <td> <c:out value="${news.section}" /> </td>
  </tr>

    <tr>
    <td><c:out value="${form_brief}" /></td>
     <td> <c:out value="${news.brief}" /> </td>
  </tr>

  <tr>
    <td><c:out value="${form_content}" /></td>
     <td> <c:out value="${news.content}" /> </td>
  </tr>

</table>

    </td>

     <td class="classhorisontalalightop"><c:out value=" ${news.date}" /></td>
  </tr>

  <tr>

    <td colspan="2" align="right"><p align="right">
    	<a href="controller?command=find_news&id=${news.id}"><c:out value="${reference_view}" /></a>
		<a href="controller?command=update_news_read&id=${news.id}"><c:out value="${reference_edit}" /></a>
	    <a href="controller?command=delete_news&id=${news.id}"><c:out value="${reference_delete}" /></a>
		<input type="checkbox" name="selected_news_id" value="${news.id}" >
    </p>
	</td>
  </tr>

  <tr>
    <td colspan="2">
    <br>
    </td>
    </tr>

  </table>
</c:forEach>
        <c:set var = "buttonStatus" scope = "session" value = "${sessionScope.emptyNewsTable}"/>

        <c:choose>
            <c:when test="${buttonStatus == 'displayButton'}">
                <input type="submit" name="submit" value = "${button_delete_selected}" class="myButton" align="right">
                <input type="button" onclick="history.back();" value="${button_back}" class="myButton"/>
            </c:when>
            <c:otherwise>
                <br>
                ${result_empty_table}
            </c:otherwise>
        </c:choose>
    </fieldset>
  </form>


  	</td>
  </tr>

    <tr>

  <td colspan="2" align="center"><b><font size="1" color="black" face="Arial"><center><c:out value="${copyright}" /></center></font></b></td>
  </tr>
</table>
</body>
</html>