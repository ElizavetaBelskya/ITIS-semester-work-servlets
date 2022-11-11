<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:baseHead title="Sign up" scriptLink="/js/registration.js"/>

<body>
<header>
    <%@include file="/WEB-INF/includes/anonNavbar.jsp" %>
</header>
<form action='' method='POST'>
            <div class="container h-100" id="reg-container">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                        <div class="card reg-card">
                            <div class="card-body p-5">
                                <h2 class="text-uppercase text-center mb-5">Create an account as student</h2>

                                <label class="form-label" for="name-reg-student">Passport name since big letter</label>
                                <input type="text" id = "name-reg-student" class="form-control form-control-lg" name="name" required placeholder="Name" aria-label="Username" pattern="[A-Z][a-z]{1,30}" value="${name}"
                                       title="The name is not correct" maxlength="31" aria-describedby="basic-addon1">

                                <label class="form-label" for="email-reg-student">Email</label>
                                <input type="text" id="email-reg-student" class="form-control form-control-lg" name='email' required
                                       placeholder="email@example.com" pattern="[A-Za-z0-9-]{2,50}@[a-z]{2,20}.[a-z]{2,4}"
                                       value="${email}" maxlength="76" title="The email is not correct"/>

                                <label class="form-label" for="phone-reg-student">Phone</label>
                                <input type="text" id="phone-reg-student" class="form-control form-control-lg" name='phone' required placeholder="89000000000"
                                       pattern="[0-9]{11}" minlength="11" maxlength="11" title="The phone is not correct (only 11 numbers)" value="${phone}" title="The phone format is not correct"/>


                                    <div class="mb-4 pb-2">
                                        <label class="form-label" for="city-reg-tutor">City</label>
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
                                    </div>

                                <div class="mb-4 pb-2">
                                    <label class="form-label" for="password-reg-student">Password</label>
                                    <div class="form-outline">
                                        <input id="password-reg-student" type='password' name='password' required placeholder="Password123" pattern="([a-z]+[A-Z]+[0-9]+|[a-z]+[0-9]+[A-Z]+|[A-Z]+[a-z]+[0-9]+|[A-Z]+[0-9]+[a-z]+|[0-9]+[a-z]+[A-Z]+|[0-9]+[A-Z]+[a-z]+)"
                                               title="The password must contain small and big Latin letters and numbers" maxlength="50" class="form-control form-control-lg" />
                                    </div>
                                </div>

                                    <div class="d-flex justify-content-center">
                                        <input type='submit' class="btn btn-success btn-block btn-lg gradient-custom-4 text-body" value='Sign in'>
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
