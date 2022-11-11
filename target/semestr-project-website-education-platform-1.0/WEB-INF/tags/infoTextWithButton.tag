<%@ tag description = "information placeholder with button" pageEncoding="utf-8"%>
<%@ attribute name="text"%>
<%@ attribute name="link"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="action"%>
<div class = "empty-list">
  <h2 class="empty-list-text">${text}</h2>
  <form action="<c:url value="${link}"/>" method="GET">
    <input type="submit" value="${action}" class="btn btn-outline-info"/>
  </form>
</div>