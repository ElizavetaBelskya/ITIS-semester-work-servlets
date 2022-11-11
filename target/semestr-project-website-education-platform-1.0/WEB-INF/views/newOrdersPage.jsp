<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mt" uri="myTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:baseHead title="New orders"/>

<body>
<header>
    <%@include file="/WEB-INF/includes/tutorNavbar.jsp" %>
</header>
<c:if test="${orders.size() > 0}">
    <div id="carousel-orders" class="carousel slide" data-bs-ride="carousel">
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
                            <div class="card order-card-tutor">
                                <div class="card-body">
                                    <h5 class="card-title order-card-editable">${orders.get(i).getSubject()}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">${orders.get(i).getDescription()}</h6>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">Price: ${orders.get(i).getPrice()}</li>
                                        <li class="list-group-item">
                                                ${orders.get(i).getOnline() == null? "Both online and offline": (orders.get(i).getOnline()? "Online":"Offline")}
                                        </li>
                                    </ul>
                                    <p class="card-text">
                                        <mt:lastModifiedTag lastModified="${orders.get(i).getCreationDate()}" action = "${actionName}"/>
                                    </p>
                                    <c:if test="${takenOrders.get(i).equals(false)}">
                                        <form action="<c:url value="/new_orders?action=take"/>" method="post">
                                            <input type="hidden" name="take" value="${orders.get(i).getId()}">
                                            <input type="submit" value="Take it" class="btn btn-outline-info"/>
                                        </form>
                                    </c:if>

                                    <c:if test="${takenOrders.get(i).equals(true)}">
                                        <button type="button" class="btn btn-info">Taken</button>
                                    </c:if>


                                </div>
                            </div>
                        </div>
                        <c:if test="${i+1 < orders.size()}">
                            <div class="col">
                                <div class="card order-card-tutor">
                                    <div class="card-body">
                                        <h5 class="card-title order-card-editable">${orders.get(i+1).getSubject()}</h5>
                                        <h6 class="card-subtitle mb-2 text-muted">${orders.get(i+1).getDescription()}</h6>
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">Price: ${orders.get(i+1).getPrice()}</li>
                                            <li class="list-group-item">
                                                    ${orders.get(i+1).getOnline() == null? "Both online and offline": (orders.get(i+1).getOnline()? "Online":"Offline")}
                                            </li>
                                        </ul>
                                        <p class="card-text">
                                            <mt:lastModifiedTag lastModified="${orders.get(i+1).getCreationDate()}" action = "${actionName}"/>
                                        </p>
                                        <c:if test="${takenOrders.get(i+1).equals(false)}">
                                            <form action="<c:url value="/new_orders?action=take"/>" method="post">
                                                <input type="hidden" name="take" value="${orders.get(i+1).getId()}">
                                                <input type="submit" value="Take it" class="btn btn-outline-info"/>
                                            </form>
                                        </c:if>
                                        <c:if test="${takenOrders.get(i+1).equals(true)}">
                                            <button type="button" class="btn btn-info">Taken</button>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${i+2 < orders.size()}">
                            <div class="col">
                                <div class="card order-card-tutor">
                                    <div class="card-body">
                                        <h5 class="card-title order-card-editable">${orders.get(i+2).getSubject()}</h5>
                                        <h6 class="card-subtitle mb-2 text-muted">${orders.get(i+2).getDescription()}</h6>
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">Price: ${orders.get(i+2).getPrice()}</li>
                                            <li class="list-group-item">
                                                    ${orders.get(i+2).getOnline() == null? "Both online and offline": (orders.get(i+2).getOnline()? "Online":"Offline")}
                                            </li>
                                        </ul>
                                        <p class="card-text">
                                            <mt:lastModifiedTag lastModified="${orders.get(i+2).getCreationDate()}" action = "${actionName}"/>
                                        </p>
                                        <c:if test="${takenOrders.get(i+2).equals(false)}">
                                            <form action="<c:url value="/new_orders?action=take"/>" method="post">
                                                <input type="hidden" name="take" value="${orders.get(i+2).getId()}">
                                                <input type="submit" value="Take it" class="btn btn-outline-info"/>
                                            </form>
                                        </c:if>
                                        <c:if test="${takenOrders.get(i+2).equals(true)}">
                                            <button type="button" class="btn btn-info">Taken</button>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                </c:forEach>
            </div>

            <button class="carousel-control-prev" type="button" data-bs-target="#carousel-orders" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carousel-orders" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
</c:if>

<c:if test="${orders.size() == 0}">
    <t:infoText bigText="There are no suitable orders for you" middleText="Wait for a while, please"/>
</c:if>

</body>
</html>
