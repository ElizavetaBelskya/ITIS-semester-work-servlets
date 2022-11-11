<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:baseHead title="Sign up" scriptLink="/js/registration.js"/>

<body>
<header>
    <%@include file="/WEB-INF/includes/anonNavbar.jsp" %>
</header>

<form action='' method='POST'>
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12">
                    <div class="card card-registration card-registration-2">
                        <div class="card-body p-0">
                            <div class="row g-0">
                                <div class="col-lg-6">
                                    <div class="p-5">
                                        <h3 class="fw-normal mb-5" id="title-reg-tutor">General Infomation</h3>

                                        <div class="row">
                                            <div class="col-md-6 mb-4 pb-2">

                                                <div class="form-outline">
                                                    <input type="text" id="name-reg-tutor" name='name' required placeholder="Name"
                                                           pattern="[A-Z][a-z]{1,30}" maxlength="31" value="${name}" title="The name is not correct" class="form-control form-control-lg" />
                                                    <label class="form-label" for="name-reg-tutor">Passport name since big letter</label>
                                                </div>

                                            </div>

                                            <div class="col-md-6 mb-4 pb-2">
                                                <div class="form-outline">
                                                    <input type="text" id="email-reg-tutor" name='email' required placeholder="email@example.com" pattern="[A-Za-z0-9-]{2,50}@[a-z]{2,20}.[a-z]{2,4}"
                                                           maxlength="76" value="${email}" title="The email is not correct" class="form-control form-control-lg" />
                                                    <label class="form-label" for="email-reg-tutor">Email</label>
                                                </div>

                                            </div>
                                        </div>

                                        <div class="col-md-6 mb-4 pb-2">

                                            <div class="form-outline">
                                                <input type="text" id="phone-reg-tutor" name='phone' required placeholder="89000000000" pattern="[0-9]{11}"
                                                       title="The phone is not correct (only 11 numbers)" maxlength="11" minlength="11" value="${phone}" class="form-control form-control-lg" />
                                                <label class="form-label" for="phone-reg-tutor">Phone</label>
                                            </div>

                                        </div>

                                        <div class="mb-4 pb-2">
                                            <select class="form-select form-control-lg" id="city-reg-tutor" name="city">
                                                <с:if test="${city == null}">
                                                    <option selected value="${cities.get(0)}">${cities.get(0)}</option>
                                                    <c:forEach var="j" begin="1" end="${cities.size()-1}">
                                                        <option value="${cities.get(j)}">${cities.get(j)}</option>
                                                    </c:forEach>
                                                </с:if>
                                                <c:if test="${city != null}">
                                                    <c:forEach var="j" begin="1" end="${cities.size()-1}">
                                                        <c:if test="${city.equals(cities.get(j))}">
                                                            <option selected value="${cities.get(j)}">${cities.get(j)}</option>
                                                        </c:if>
                                                        <c:if test="${!city.equals(cities.get(j))}">
                                                            <option value="${cities.get(j)}">${cities.get(j)}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                            <label class="form-label" for="city-reg-tutor">City</label>
                                        </div>

                                        <div class="mb-4 pb-2">
                                            <div class="form-outline">
                                                <input id="password-reg-tutor" type='password' required name='password' placeholder="Password123" pattern="([a-z]+[A-Z]+[0-9]+|[a-z]+[0-9]+[A-Z]+|[A-Z]+[a-z]+[0-9]+|[A-Z]+[0-9]+[a-z]+|[0-9]+[a-z]+[A-Z]+|[0-9]+[A-Z]+[a-z]+)"
                                                      maxlength="50" title="The password must contain small and big Latin letters and numbers" class="form-control form-control-lg" />
                                                <label class="form-label" for="password-reg-tutor">Password</label>
                                            </div>
                                        </div>

                                        <c:if test="${gender == null || gender}">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="gender" value='1' checked>
                                                <label class="form-check-label">Male</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="gender" value='0'>
                                                <label class="form-check-label">Female</label>
                                            </div>
                                        </c:if>

                                        <c:if test="${gender != null && !gender}">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="gender" value='1'>
                                                <label class="form-check-label">Male</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="gender" value='0' checked>
                                                <label class="form-check-label">Female</label>
                                            </div>
                                        </c:if>

                                        <c:if test="${isWorkingOnline == null}">
                                            <input class="form-check-input" type="checkbox" name="isWorkingOnline" id="onlineCheckbox">
                                            <label class="form-check-label" for="onlineCheckbox">Working online</label>
                                        </c:if>
                                        <c:if test="${isWorkingOnline != null}">
                                            <input class="form-check-input" type="checkbox" name="isWorkingOnline" id="onlineCheckbox" checked>
                                            <label class="form-check-label" for="onlineCheckbox">Working online</label>
                                        </c:if>

                                    </div>
                                </div>
                                <div class="col-lg-6 bg-indigo text-white">
                                    <div class="p-5">
                                        <h3 class="fw-normal mb-5">Subjects</h3>


                                        <c:if test="${subjects.size() > 0}">
                                            <c:forEach var="i" begin="0" end="${subjects.size()-1}">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" name="${subjects.get(i)}" value="${subjects.get(i)}" id="subject_${i}">
                                                    <label class="form-check-label" for="${subjects.get(i)}">
                                                            ${subjects.get(i)}
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </c:if>

                                        <input type='submit' id="btn-reg-tutor" class="btn btn-light" value='Sign in'>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

</form>

    <c:if test="${answer != null}">
        <t:modal answer="${answer}" answerTitle = "${answerTitle}"/>
    </c:if>

</body>
</html>

