<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>update_news_by_id_db_data.jsp</title>
	<link href="news.css" rel="stylesheet">
</head>

<fmt:setLocale value="${sessionScope.locale}" />
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
<fmt:message bundle="${locale}" key="form.date" var="form_date" />
<fmt:message bundle="${locale}" key="reference.delete" var="reference_delete" />
<fmt:message bundle="${locale}" key="button.delete-selected" var="button_delete_selected" />
<fmt:message bundle="${locale}" key="button.back" var="button_back" />
<fmt:message bundle="${locale}" key="button.submit" var="button_submit" />
<fmt:message bundle="${locale}" key="reference.view" var="reference_view" />
<fmt:message bundle="${locale}" key="reference.edit" var="reference_edit" />
<fmt:message bundle="${locale}" key="reference.delete" var="reference_delete" />
<fmt:message bundle="${locale}" key="copyright" var="copyright" />
<fmt:message bundle="${locale}" key="operation.update-news.logo" var="operation_update_news_logo" />

<fmt:message bundle="${locale}" key="button.register-user" var="button_register_user" />
<fmt:message bundle="${locale}" key="button.list-users" var="button_list_users" />

<c:url var="locale_ru" value="controller"  >
	<c:param name="command" value="localization" />
	<c:param name="locale" value="ru" />
</c:url>

<c:url var="locale_en" value="controller"  >
	<c:param name="command" value="localization" />
	<c:param name="locale" value="en" />
</c:url>

<body>

<table border="1">

	<tr>
		<td><font color="red"><c:out value="${table_logo}" /></font></td>
		<td align="right" class="classverticalalignbottom"><font
				size="1" color="black">
			<a href="${locale_en}">${button_en}</a>
			<a href="${locale_ru}">${button_ru}</a>
		</font></td>
	</tr>

	<tr>
		<td width="25%">


			<form action="controller" method="GET">
				<input type="hidden" name="command" value="find_all_news" /> <input
					type="submit" name="submit" value="${button_list_all_news}"
					class="myButton">

			</form>

			<form action="controller" method="GET">
				<input type="hidden" name="command" value="fill_news" /> <input
					type="submit" name="submit" value="${button_create_news}"
					class="myButton">
			</form>




			<form action="controller" method="GET">
				<input type="hidden" name="command" value="register_user" /> <input
					type="submit" name="submit" value="${button_register_user}"
					class="myButton">

			</form>

			<form action="controller" method="GET">
				<input type="hidden" name="command" value="list_users" /> <input
					type="submit" name="submit" value="${button_list_users}"
					class="myButton">

			</form>
		</td>
		<td>
			<form action="controller" method="POST">
				<input type="hidden" name="command" value="update_news_save" />
				<input type="hidden" name="id" value="${news.id}" />
				<input type="hidden" name="date" value="${news.date}" />

				<fieldset>

					<legend><c:out value="${form_logo}" /></legend>
					<table>
						<tr>
							<td><c:out value="${operation_update_news_logo}" /></td>
							<td></td>

						</tr>

					<tr>
							<td><c:out value="${form_section}" /></td>
							<td><select name="section">
								<option value="${news.section}" selected> <c:out value="${news.section}" /></option>
								<option value="Gomel news">Gomel news</option>
								<option value="Minsk news">Minsk ews</option>
								<option value="Brest news">Brest news</option>
								<option value="Grodno news">Grodno news</option>
								<option value="Vitebsk news">Vitebsk news</option>
							</select></td>
						</tr>
						<tr>
							<td><c:out value="${form_author}" /></td>
							<td><input type="text" name="author" value="${news.author}"
									   required></td>
						</tr>

						<tr>
							<td><c:out value="${form_brief}" /></td>
							<td><input type="text" name="brief" value="${news.brief}" required></td>
						</tr>
						<tr>
							<td><c:out value="${form_content}" /></td>
							<td><textarea name="content" required><c:out value="${news.content}" /></textarea></td>
						</tr>

					</table>

					<input type="submit" name="submit" value="${button_submit}"
						   class="myButton"> <input type="button"
													onclick="history.back();" value="${button_back}" class="myButton" />
					<br>

				</fieldset>
			</form>
		</td>
	</tr>

	<tr>

		<td colspan="2" align="center"><b><font size="1"
												color="black" face="Arial"><center><c:out value="${copyright}" /></center></font></b></td>
	</tr>
</table>
</body>
</html>