<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:baseHead title="Create order" scriptLink="/js/orderCreationScript.js"/>
<body>
<header>
    <%@include file="/WEB-INF/includes/studentNavbar.jsp" %>
</header>
<div class="container">
    <div class="row main-form">
        <div class="card" id="order-creation-card" >
            <div class="card-body">
                <h5 class="card-title">Please, fill in all fields</h5>
                <form action='' method='POST'>

                    <label class="form-label" for="subject">Subject</label>
                    <select class="form-select" name='subject' id="subject" aria-label="Default select example">
                        <option selected value="${subjects.get(0)}">${subjects.get(0)}</option>
                        <c:forEach var="j" begin="1" end="${subjects.size()-1}">
                            <option value="${subjects.get(j)}">${subjects.get(j)}</option>
                        </c:forEach>
                    </select>

                    <label class="form-label" for="format">Format</label>
                    <select class="form-select" name='format' id="format" aria-label="Default select example">
                        <option selected value="online">Online</option>
                        <option value="offline">Offline</option>
                        <option value="both">Both</option>
                    </select>

                    <label class="form-label" for="gender">Tutor's gender</label>
                    <select class="form-select" name='gender' id="gender" aria-label="Default select example">
                        <option value="male">Only male</option>
                        <option value="female">Only female</option>
                        <option selected value="both">Both</option>
                    </select>

                    <label class="form-label" for="rating">Raiting</label>
                    <select class="form-select" name='rating' id='rating' aria-label="Default select example">
                        <option selected value="0.0">Any rating</option>
                        <option value="4.0">Only with a rating greater than 4.0</option>
                    </select>

                    <div class="mb-3">
                        <label for="textarea1" class="form-label">Description</label>
                        <textarea class="form-control" name='description' id="textarea1" maxlength="120" rows="3"></textarea>
                        <span id = "remaining-symblos">Left 120/120</span>
                    </div>

                    <div class="form-outline">
                        <label class="form-label" for="price">Price</label>
                        <input type="number" name='price' min="100" max="10000" required id="price" class="form-control">
                    </div>

                    <input type='submit' class="btn btn-primary" value='Create'>

                </form>

            </div>
        </div>

        <c:if test="${orders.size() == 0}">
            <div class="col">
                <img class="placeholder-image-education" src="<c:url value="/static/images/education-placeholder.png"/>"/>
            </div>
        </c:if>

        <c:if test="${orders.size() > 0}">
        <div id="carousel-orders-read-only" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <c:forEach var="i" begin="0" end="${orders.size()-1}" step="4">
                <c:if test = "${i == 0}">
                <div class="carousel-item active">
                    </c:if>
                    <c:if test = "${i != 0}">
                    <div class="carousel-item" id="order-item">
                        </c:if>
                        <div class="row">
                            <div class="col">
                                <div class="card order-card">
                                    <div class="card-body">
                                        <h5 class="card-title">${orders.get(i).getSubject()}</h5>
                                        <h6 class="card-subtitle mb-2 text-muted">${orders.get(i).getDescription()}</h6>
                                        <p class="card-text">
                                            <mt:lastModifiedTag lastModified="${orders.get(i).getCreationDate()}" action = "${action}"/>
                                        </p>
                                    </div>
                                </div>
                            <c:if test="${i+1 < orders.size()}">
                                    <div class="card order-card">
                                        <div class="card-body">
                                            <h5 class="card-title">${orders.get(i+1).getSubject()}</h5>
                                            <h6 class="card-subtitle mb-2 text-muted">${orders.get(i+1).getDescription()}</h6>
                                            <p class="card-text">
                                                <mt:lastModifiedTag lastModified="${orders.get(i+1).getCreationDate()}" action = "${action}"/>
                                            </p>
                                        </div>
                                    </div>
                            </c:if>
                            <c:if test="${i+2 < orders.size()}">
                                    <div class="card order-card">
                                        <div class="card-body">
                                            <h5 class="card-title">${orders.get(i+2).getSubject()}</h5>
                                            <h6 class="card-subtitle mb-2 text-muted">${orders.get(i+2).getDescription()}</h6>
                                            <p class="card-text"><mt:lastModifiedTag lastModified="${orders.get(i+2).getCreationDate()}" action = "${action}"/>
                                            </p>
                                        </div>
                                    </div>
                            </c:if>
                            <c:if test="${i+3 < orders.size()}">
                            <div class="card order-card">
                            <div class="card-body">
                                <h5 class="card-title">${orders.get(i+3).getSubject()}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${orders.get(i+3).getDescription()}</h6>
                                <p class="card-text"><mt:lastModifiedTag lastModified="${orders.get(i+3).getCreationDate()}" action = "${action}"/>
                                </p>
                            </div>
                        </div>
                            </c:if>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carousel-orders-read-only" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carousel-orders-read-only" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
        </c:if>

    </div>
</div>


<c:if test="${answer != null}">
    <t:modal answer="${answer}" answerTitle = "${answerTitle}"/>
</c:if>

</div>

</body>
</html>


