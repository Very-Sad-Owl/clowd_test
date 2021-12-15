<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix = "gr" uri = "/WEB-INF/greetings.tld"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Новости</title>
</head>
<body>
<div>
    <h2>Новости</h2>
    <a href="/">Главная</a>

    <gr:greetings dayPart = "${dayPart}" username="${username}"/>

    <c:choose>
        <c:when test="${currentArticle != null}">
            <h1>${currentArticle.title}</h1>
            <p>${currentArticle.content}</p>
            <div class = "rating">
                <form method="post">
                    <input type="submit" name="action" value="like"/>
                    <input type="submit" name="action" value="dislike"/>
                    <input type="hidden" name="article" value="${currentArticle.articleId}"/>
                </form>
            </div>
            <br/>
        </c:when>
        <c:otherwise>
            <table>
                <c:forEach items="${allNews}" var="article">
                    <tr>
                        <td>${article.title}</td>
                    </tr>
                    <tr>
                        <td>${fn:substring(article.content, 0, 255)}</td>
                    </tr>
                    <tr>
                        <sec:authorize access="isAuthenticated()">
                            <a href="/news/${article.articleId}">Read</a>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </table>
            <br/>
        </c:otherwise>
    </c:choose>



</div>
</body>
</html>