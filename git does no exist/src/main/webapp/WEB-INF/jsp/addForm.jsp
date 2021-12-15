<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Новости</title>
</head>
<body>
<div>
    <h2>Создание статьи</h2>
    <a href="/">Главная</a>

    <form:form method="POST" modelAttribute="newArticle">
        <h2>Регистрация</h2>
        <div>
            <form:input maxlength="255" type="text" path="title" placeholder="Title"
                        autofocus="true"></form:input>
            <form:errors path="title"></form:errors>
                ${titleError}
        </div>
        <div>
            <form:input maxlength="10000" type="text" path="content" placeholder="Content"></form:input>
        </div>
        <form:input type="hidden" path="likes" placeholder="Likes" value = "0"></form:input>
        <form:input type="hidden" path="dislikes" placeholder="Dislikes" value = "0"></form:input>
        <button type="submit">Добавить</button>
    </form:form>

    <%--<form method="post" modelAttribute="newArticle">--%>
        <%--<input type="text" name="title" value="like"/>--%>
        <%--<input type="submit" name="action" value="dislike"/>--%>
        <%--<input type="hidden" name="article" value="${currentArticle.articleId}"/>--%>
    <%--</form>--%>

</div>
</body>
</html>