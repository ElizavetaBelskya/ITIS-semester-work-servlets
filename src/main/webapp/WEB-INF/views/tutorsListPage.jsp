<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:baseHead title="Create order" scriptLink="/js/tutorListScript.js"/>
<body>

<header>
    <%@include file="/WEB-INF/includes/studentNavbar.jsp" %>
</header>
<form action="" method="GET">
    <input type="hidden" name="page" value="1">
    <select class="form-select tutor-list-select" name="sorted" aria-label="Default select example">
        <c:if test = "${sorted}">
            <option selected value="1">Sorted by raiting</option>
            <option value="0">Without sorting</option>
        </c:if>
        <c:if test="${!sorted}">
            <option selected value="0">Without sorting</option>
            <option value="1">Sorted by raiting</option>
        </c:if>
    </select>
    <select class="form-select tutor-list-select" name="subject" aria-label="Default select example">
        <c:if test="${subject.equals('Any')}">
            <option selected value="Any">Any subject</option>
            <c:forEach var="j" begin="0" end="${subjects.size()-1}">
                <option value="${subjects.get(j)}">${subjects.get(j)}</option>
            </c:forEach>
        </c:if>
        <c:if test="${!subject.equals('Any')}">
            <option value="Any">Any subject</option>
            <c:forEach var="j" begin="0" end="${subjects.size()-1}">
                <c:if test="${subject.equals(subjects.get(j))}">
                    <option selected value="${subjects.get(j)}">${subjects.get(j)}</option>
                </c:if>
                <c:if test="${!subject.equals(subjects.get(j))}">
                    <option value="${subjects.get(j)}">${subjects.get(j)}</option>
                </c:if>
            </c:forEach>

        </c:if>
    </select>
    <input id="apply-btn" type='submit' name='apply' class="btn btn-info" value='Apply'>
</form>


<c:if test="${tutors.size() > 0}">
<div class="slider">
    <div class="slider-line">
        <input id="tutors-list-size" type="hidden" value="${tutors.size()}">
        <c:forEach var="i" begin="0" end="${tutors.size()-1}" step="4">
            <c:if test="${i < tutors.size()}">
                <div class = "tutor-card">
                    <div class="tutor-text">${tutors.get(i).getName()}</div>
                    <div class="tutor-text">Rating: ${tutors.get(i).getRating()}</div>
                    <div class="tutor-text">City: ${tutors.get(i).getCity()}</div>
                    <div class="tutor-text">Gender: ${tutors.get(i).isGender()?"Male":"Female"}</div>
                </div>
            </c:if>

            <c:if test="${i+1 < tutors.size()}">
                <div class = "tutor-card">
                    <div class="tutor-text">${tutors.get(i+1).getName()}</div>
                    <div class="tutor-text">Rating: ${tutors.get(i+1).getRating()}</div>
                    <div class="tutor-text">City: ${tutors.get(i+1).getCity()}</div>
                    <div class="tutor-text">Gender: ${tutors.get(i+1).isGender()?"Male":"Female"}</div>
                </div>
            </c:if>

            <c:if test="${i+2 < tutors.size()}">
                <div class = "tutor-card">
                    <div class="tutor-text">${tutors.get(i+2).getName()}</div>
                    <div class="tutor-text">Rating: ${tutors.get(i+2).getRating()}</div>
                    <div class="tutor-text">City: ${tutors.get(i+2).getCity()}</div>
                    <div class="tutor-text">Gender: ${tutors.get(i+2).isGender()?"Male":"Female"}</div>
                </div>
            </c:if>

            <c:if test="${i+3 < tutors.size()}">
                <div class = "tutor-card">
                    <div class="tutor-text">${tutors.get(i+3).getName()}</div>
                    <div class="tutor-text">Rating: ${tutors.get(i+3).getRating()}</div>
                    <div class="tutor-text">City: ${tutors.get(i+3).getCity()}</div>
                    <div class="tutor-text">Gender: ${tutors.get(i+3).isGender()?"Male":"Female"}</div>
                </div>
            </c:if>

        </c:forEach>
    </div>
</div>

<div class="arrow-6">
    <span id = "left">
    <svg width="18px" height="17px" viewBox="0 0 18 17" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
        <g transform="translate(8.500000, 8.500000) scale(-1, 1) translate(-8.500000, -8.500000)">
            <polygon class="arrow-6-pl" points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596"></polygon>
            <polygon class="arrow-6-pl-fixed" points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596"></polygon>
            <path d="M-1.48029737e-15,0.56157424 L-1.48029737e-15,16.1929159 L9.708,8.33860465 L-2.66453526e-15,0.56157424 L-1.48029737e-15,0.56157424 Z M1.33333333,3.30246869 L7.62533333,8.34246869 L1.33333333,13.4327013 L1.33333333,3.30246869 L1.33333333,3.30246869 Z"></path>
        </g>
    </svg>
    </span>
    <span id = "right">
        <svg width="18px" height="17px" viewBox="-1 0 18 17" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
            <g>
                <polygon class="arrow-6-pl" points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596"></polygon>
                <polygon class="arrow-6-pl-fixed" points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596"></polygon>
                <path d="M-4.58892184e-16,0.56157424 L-4.58892184e-16,16.1929159 L9.708,8.33860465 L-1.64313008e-15,0.56157424 L-4.58892184e-16,0.56157424 Z M1.33333333,3.30246869 L7.62533333,8.34246869 L1.33333333,13.4327013 L1.33333333,3.30246869 L1.33333333,3.30246869 Z"></path>
            </g>
        </svg>
    </span>
</div>


<form action="" method="GET">
    <input type="hidden" name="sorted" value="${sorted}">
    <input type="hidden" name="subject" value="${subject}">
    <div class="tutor-pagination">
    <c:if test = "${countOfPages > 1}">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <c:if test = "${page > 1}">
                <li class="page-item">
                    <input type="submit" name="page" class="page-link" value="${page-1}">
                </li>
            </c:if>
                    <input type="submit" name="page" class="page-link" value="${page}">
            <c:if test="${page < countOfPages}">
                   <input type="submit" name="page" class="page-link" value="${page+1}">
            </c:if>
        </ul>
    </nav>
    </c:if>
    </div>
</c:if>
</form>

</body>
</html>