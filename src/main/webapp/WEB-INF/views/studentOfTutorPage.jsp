<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:baseHead title="My students"/>
<body>
<header>
    <%@include file="/WEB-INF/includes/tutorNavbar.jsp" %>
</header>
<c:if test="${orders.size() > 0}">
<div id="carousel-student" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <c:forEach var="i" begin="0" end="${orders.size()-1}" step="3">
        <c:if test = "${i == 0}">
        <div class="carousel-item active">
            </c:if>
            <c:if test = "${i != 0}">
            <div class="carousel-item">
                </c:if>
                <div class="row">
                    <div class="col">
                        <div class="card order-card-for-delete">
                            <div class="card-body">
                                <h6 class="card-subtitle mb-2 text-muted">${"Order #".concat(orders.get(i).getId().toString())}</h6>
                                <h5 class="card-title order-card-editable">${students.get(i).getName()}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${students.get(i).getPhone()}</h6>
                                <h6 class="card-subtitle mb-2 text-muted">${students.get(i).getEmail()}</h6>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">Price: ${orders.get(i).getPrice()}</li>
                                    <li class="list-group-item">
                                            ${orders.get(i).getOnline() == null? "Both online and offline": (orders.get(i).getOnline()? "Online":"Offline")}
                                    </li>
                                    <li class = "list-group-item">${orders.get(i).getSubject()}</li>
                                </ul>
                                <p class="card-text">
                                    <mt:lastModifiedTag lastModified="${orders.get(i).getCreationDate()}" action = "${actionTitle}"/>
                                </p>
                                <form action="<c:url value="/my_students?action=reject"/>" method="post">
                                    <input type="hidden" name="reject" value="${orders.get(i).getId()}">
                                    <input type="submit" value="Reject" class="btn btn-outline-info"/>
                                </form>
                            </div>
                        </div>
                    </div>
                    <c:if test="${i+1 < orders.size()}">
                        <div class="col">
                            <div class="card order-card-for-delete">
                                <div class="card-body">
                                    <h6 class="card-subtitle mb-2 text-muted">${"Order #".concat(orders.get(i+1).getId().toString())}</h6>
                                    <h5 class="card-title order-card-editable">${students.get(i+1).getName()}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">${students.get(i+1).getPhone()}</h6>
                                    <h6 class="card-subtitle mb-2 text-muted">${students.get(i+1).getEmail()}</h6>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">Price: ${orders.get(i+1).getPrice()}</li>
                                        <li class="list-group-item">
                                                ${orders.get(i+1).getOnline() == null? "Both online and offline": (orders.get(i+1).getOnline()? "Online":"Offline")}
                                        </li>
                                        <li class = "list-group-item">${orders.get(i+1).getSubject()}</li>
                                    </ul>
                                    <p class="card-text">
                                        <mt:lastModifiedTag lastModified="${orders.get(i+1).getCreationDate()}" action = "${actionTitle}"/>
                                    </p>
                                    <form action="<c:url value="/my_students?action=reject"/>" method="post">
                                        <input type="hidden" name="reject" value="${orders.get(i+1).getId()}">
                                        <input type="submit" value="Reject" class="btn btn-outline-info"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${i+2 < orders.size()}">
                        <div class="col">
                            <div class="card order-card-for-delete">
                                <div class="card-body">
                                    <h6 class="card-subtitle mb-2 text-muted">${"Order #".concat(orders.get(i+2).getId().toString())}</h6>
                                    <h5 class="card-title order-card-editable">${students.get(i+2).getName()}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">${students.get(i+2).getPhone()}</h6>
                                    <h6 class="card-subtitle mb-2 text-muted">${students.get(i+2).getEmail()}</h6>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">Price: ${orders.get(i+2).getPrice()}</li>
                                        <li class="list-group-item">
                                                ${orders.get(i+2).getOnline() == null? "Both online and offline": (orders.get(i+2).getOnline()? "Online":"Offline")}
                                        </li>
                                        <li class = "list-group-item">${orders.get(i+2).getSubject()}</li>
                                    </ul>
                                    <p class="card-text">
                                        <mt:lastModifiedTag lastModified="${orders.get(i+2).getCreationDate()}" action = "${actionTitle}"/>
                                    </p>
                                    <form action="<c:url value="/my_students?action=reject"/>" method="post">
                                        <input type="hidden" name="reject" value="${orders.get(i+2).getId()}">
                                        <input type="submit" value="Reject" class="btn btn-outline-info"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
            </c:forEach>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carousel-student" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carousel-student" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</div>
</c:if>

<c:if test="${orders.size() == 0}">
    <t:infoTextWithButton text="You don't have any students at the moment" action="Orders" link="/new_orders"/>
</c:if>


</body>
</html>
