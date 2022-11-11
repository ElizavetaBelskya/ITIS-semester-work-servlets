<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:baseHead title="Create order" scriptLink="/js/tutorsAndCandidatesListScript.js"/>

<body>
<header>
    <%@include file="/WEB-INF/includes/studentNavbar.jsp" %>
</header>

<c:if test="${approvedTutors.size() > 0}">
<h3 class="candidates-title">Your tutors</h3>
<div id="carousel-approved-tutors" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <c:forEach var="i" begin="0" end="${approvedTutors.size()-1}" step="3">
        <c:if test = "${i == 0}">
        <div class="carousel-item active">
            </c:if>
            <c:if test = "${i != 0}">
            <div class="carousel-item">
                </c:if>
                <div class="row">
                    <div class="col">
                        <div class="card candidate-card">
                            <div class="card-body">
                                <h5 class="card-title order-card-editable">Name: ${approvedTutors.get(i).getName()}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">Rating: ${approvedTutors.get(i).getRating()}</h6>
                                <h6 class="card-subtitle mb-2 text-muted">Email: ${approvedTutors.get(i).getEmail()}</h6>
                                <h6 class="card-subtitle mb-2 text-muted">Phone: ${approvedTutors.get(i).getPhone()}</h6>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">
                                            ${approvedTutors.get(i).isWorkingOnline()? "Online" : "Offline"}
                                    </li>
                                </ul>
                                <form action="<c:url value="/my_tutors?action=reject"/>" method="post">
                                    <input type="hidden" name="reject" value="${approvedTutors.get(i).getId()}">
                                    <input type="submit" value="Reject" class="btn btn-outline-info"/>
                                </form>

                                <button class="btn btn-outline-info rate" value="${approvedTutors.get(i).getId()}">Rate</button>
                            </div>
                        </div>
                    </div>
                    <c:if test="${i+1 < approvedTutors.size()}">
                        <div class="col">
                            <div class="card candidate-card">
                                <div class="card-body">
                                    <h5 class="card-title order-card-editable">Name: ${approvedTutors.get(i+1).getName()}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">Rating: ${approvedTutors.get(i+1).getRating()}</h6>
                                    <h6 class="card-subtitle mb-2 text-muted">Email: ${approvedTutors.get(i+1).getEmail()}</h6>
                                    <h6 class="card-subtitle mb-2 text-muted">Phone: ${approvedTutors.get(i+1).getPhone()}</h6>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">
                                                ${approvedTutors.get(i+1).isWorkingOnline()? "Online" : "Offline"}
                                        </li>
                                    </ul>

                                    <form action="<c:url value="/my_tutors?action=reject"/>" method="post">
                                        <input type="hidden" name="reject" value="${approvedTutors.get(i+1).getId()}">
                                        <input type="submit" value="Reject" class="btn btn-outline-info"/>
                                    </form>

                                    <button class="btn btn-outline-info rate" value="${approvedTutors.get(i+1).getId()}">Rate</button>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${i+2 < approvedTutors.size()}">
                        <div class="col">
                            <div class="card candidate-card">
                                <div class="card-body">
                                    <<h5 class="card-title order-card-editable">Name: ${approvedTutors.get(i+2).getName()}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">Rating: ${approvedTutors.get(i+2).getRating()}</h6>
                                    <h6 class="card-subtitle mb-2 text-muted">Email: ${approvedTutors.get(i+2).getEmail()}</h6>
                                    <h6 class="card-subtitle mb-2 text-muted">Phone: ${approvedTutors.get(i+2).getPhone()}</h6>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">
                                                ${approvedTutors.get(i+2).isWorkingOnline()? "Online" : "Offline"}
                                        </li>
                                    </ul>

                                    <form action="<c:url value="/my_tutors?action=reject"/>" method="post">
                                        <input type="hidden" name="reject" value="${approvedTutors.get(i+2).getId()}">
                                        <input type="submit" value="Reject" class="btn btn-outline-info"/>
                                    </form>
                                    <button class="btn btn-outline-info rate" value="${approvedTutors.get(i+2).getId()}">Rate</button>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
            </c:forEach>


        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carousel-approved-tutors" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carousel-approved-tutors" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</div>
</c:if>
<c:if test="${uncompletedOrders.size() > 0}">
<c:forEach var="j" begin="0" end="${uncompletedOrders.size()-1}">
    <c:if test="${candidatesLists.get(j).size() > 0}">
    <h3 class="candidates-title">Candidates for order # ${uncompletedOrders.get(j).getId()}</h3>
    <div id="carousel-candidates-${j}" class="carousel slide carousel-candidates" data-bs-ride="carousel">
        <div class="carousel-inner">
            <c:forEach var="i" begin="0" end="${candidatesLists.get(j).size()-1}" step="3">
            <c:if test = "${i == 0}">
            <div class="carousel-item active">
                </c:if>
                <c:if test = "${i != 0}">
                <div class="carousel-item">
                    </c:if>
                    <div class="row">
                        <div class="col">
                            <div class="card candidate-card">
                                <div class="card-body">
                                    <h5 class="card-title order-card-editable">${candidatesLists.get(j).get(i).getName()}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">${candidatesLists.get(j).get(i).getRating()}</h6>
                                    <h6 class="card-subtitle mb-2 text-muted">${candidatesLists.get(j).get(i).getEmail()}</h6>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">
                                                ${candidatesLists.get(j).get(i).isWorkingOnline()? "Online" : "Offline"}
                                        </li>
                                    </ul>
                                    <form action="<c:url value="/my_tutors?action=choose"/>" method="post">
                                        <input type="hidden" name="chooseOrderId" value="${uncompletedOrders.get(j).getId()}">
                                        <input type="hidden" name="chooseTutorId" value="${candidatesLists.get(j).get(i).getId()}">
                                        <input type="submit" value="Choose" class="btn btn-outline-info"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <c:if test="${i+1 < candidatesLists.get(j).size()}">
                            <div class="col">
                                <div class="card candidate-card">
                                    <div class="card-body">
                                        <h5 class="card-title order-card-editable">${candidatesLists.get(j).get(i+1).getName()}</h5>
                                        <h6 class="card-subtitle mb-2 text-muted">${candidatesLists.get(j).get(i+1).getRating()}</h6>
                                        <h6 class="card-subtitle mb-2 text-muted">${candidatesLists.get(j).get(i+1).getEmail()}</h6>
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">
                                                    ${candidatesLists.get(j).get(i+1).isWorkingOnline()? "Online" : "Offline"}
                                            </li>
                                        </ul>
                                        <form action="<c:url value="/my_tutors?action=choose"/>" method="post">
                                            <input type="hidden" name="chooseOrderId" value="${uncompletedOrders.get(j).getId()}">
                                            <input type="hidden" name="chooseTutorId" value="${candidatesLists.get(j).get(i+1).getId()}">
                                            <input type="submit" value="Choose" class="btn btn-outline-info"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${i+2 < candidatesLists.get(j).size()}">
                            <div class="col">
                                <div class="card candidate-card">
                                    <div class="card-body">
                                        <<h5 class="card-title order-card-editable">${candidatesLists.get(j).get(i+2).getName()}</h5>
                                        <h6 class="card-subtitle mb-2 text-muted">${candidatesLists.get(j).get(i+2).getRating()}</h6>
                                        <h6 class="card-subtitle mb-2 text-muted">${candidatesLists.get(j).get(i+2).getEmail()}</h6>
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">
                                                    ${candidatesLists.get(j).get(i+2).isWorkingOnline()? "Online" : "Offline"}
                                            </li>
                                        </ul>
                                        <form action="<c:url value="/my_tutors?action=choose"/>" method="post">
                                            <input type="hidden" name="chooseOrderId" value="${uncompletedOrders.get(j).getId()}">
                                            <input type="hidden" name="chooseTutorId" value="${candidatesLists.get(j).get(i+2).getId()}">
                                            <input type="submit" value="Choose" class="btn btn-outline-info"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                </c:forEach>

            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carousel-candidates-${j}" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carousel-candidates-${j}" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
    </c:if>
</c:forEach>
</c:if>

<div class="modal fade" id="rate-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Rate your tutor</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="stars">
                    <div class="star active"></div>
                    <div class="star"></div>
                    <div class="star"></div>
                    <div class="star"></div>
                    <div class="star"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <form action="<c:url value="/my_tutors?action=rated"/>" method="post">
                    <input type="hidden" id="id-rated-tutor" name="idRatedTutor" value="0"/>
                    <input type="hidden" id = "star-count" name="starCount" value="0"/>
                    <input type="submit" value="Save" class="btn btn-primary"/>
                </form>
            </div>
        </div>
    </div>
</div>

<c:if test="${approvedTutors.size() == 0 && uncompletedOrders.size() == 0}">
    <t:infoTextWithButton text="You have not created any orders" action="Create" link="/create_order"/>
</c:if>

<c:if test="${noCandidates}">
    <t:infoText bigText="Now we are looking for the best tutors for you!" middleText="Wait just a little bit..."/>
</c:if>

</body>
</html>
