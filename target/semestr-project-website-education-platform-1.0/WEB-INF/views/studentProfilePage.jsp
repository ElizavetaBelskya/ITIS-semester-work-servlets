<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mt" uri="myTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:baseHead title="My profile"/>
<body>
<header>
    <%@include file="/WEB-INF/includes/studentNavbar.jsp" %>
</header>
<div class="container h-100" id="reg-container">
<div class="row d-flex justify-content-center align-items-center h-100">
    <div class="col-12 col-md-9 col-lg-7 col-xl-6">
        <div class="card reg-card">
            <div class="card-body p-5">
                <h2 class="text-uppercase text-center mb-5">Student profile</h2>

                <div>
                    <p>
                        Name: ${name}
                    </p>
                    <p>
                        Email: ${email}
                    </p>
                    <p>
                        Phone: ${phone}
                    </p>
                    <p>
                        City: ${city}
                    </p>
                </div>

                <div>
                    <form method="get" action="<c:url value='/logout'/>">
                        <input type='submit' class="btn btn-info" value='Log out'/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
</div>


</body>
</html>
