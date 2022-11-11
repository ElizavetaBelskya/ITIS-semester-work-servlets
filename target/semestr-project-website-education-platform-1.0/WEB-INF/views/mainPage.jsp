<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:baseHead title="IndependentEducation" scriptLink="/js/mainScript.js"/>

<body>
<header>
    <%@include file="/WEB-INF/includes/anonNavbar.jsp" %>
</header>

<div id="gray"></div>
<div id="nav-sign-in">
    <img id = "login-img-button" src = "<c:url value="/static/images/login.png"/>"/>
</div>
<%@include file="/WEB-INF/includes/authorizationWindow.jsp" %>
<div class="container">
<div class = "row g-0 .mx-0">
    <div class = "col-8">
        <div id="carousel-presentation" class="carousel slide" data-bs-ride="false">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselPresentation" data-bs-slide-to="0" class="active"
                            aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselPresentation" data-bs-slide-to="1"
                            aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselPresentation" data-bs-slide-to="2"
                            aria-label="Slide 3"></button>
                </div>

                <div class="carousel-inner">
                    <div class="carousel-item active" data-bs-interval="5000">
                        <img src="<c:url value="/static/images/close-up-student-reading-book-formed.jpg"/>"
                             class="d-block w-100" alt="close-up-student-reading-book"/>
                        <div class="carousel-caption d-none d-md-block">
                            <h2>Freedom in education for every person</h2>
                            <p>Our teaching methods have been tested by thousands of students, but there is still a place for
                                creativity in them</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="<c:url value="/static/images/medium-shot-kid-holding-hand-up-formed.jpg"/>"
                             class="d-block w-100" alt="medium-shot-kid-holding-hand-up"/>
                        <div class="carousel-caption d-none d-md-block" data-bs-interval="5000">
                            <h2>We guarantee that no question will go unnoticed</h2>
                            <p>The online format is not a hindrance: we pay equal attention to each student</p>
                        </div>
                    </div>
                    <div class="carousel-item" data-bs-interval="5000">
                        <img src="<c:url value="/static/images/never-stop-learning-3653430_1920.jpg"/>"
                             class="d-block w-100" alt="learing-distance"/>
                        <div class="carousel-caption d-none d-md-block">
                            <h2>Study anywhere</h2>
                            <p>Study is available wherever there is internet access</p>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carousel-presentation" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carousel-presentation" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
        </div>
    </div>

    <div class="col-4">
        <div class = "welcome-card">
            <h2 class="big-title">Welcome to the independent education industry!</h2>
            <h5 class="welcome-text">Online education is a new stage in the development of the education system, which allows you to gain knowledge without leaving home, anywhere and at any time.
                This is an opportunity to get an education with the help of modern technologies, without wasting time, effort and money on trips to other cities and countries.</h5>
        </div>
    </div>
</div>


    <div class="row">

        <div class="col-6">
            <h3 class="welcome-text-white-title">
                The best tutors for you!
            </h3>
            <h5 class = "welcome-text-white">
                It can be very difficult to find a tutor. As a rule, people do not seek to find professionals who are able to meet all their needs.
                In most cases, they want the teacher to be easy to communicate, know his subject well and not require too much money.
                But, unfortunately, this is not the case at all.
                Even the simplest math tutor, who has nothing to do with professional education, is able to give an excellent result.
                The tutor must be competent, knowledgeable, loving his job.
            </h5>
        </div>
        <div class="col-6">
            <img class="placeholder-image-elearinig" src="<c:url value="/static/images/elearning.png"/>"/>
        </div>

    </div>

    <div class="row">

        <div class="col-6">
            <img class="placeholder-image-classroom" src="<c:url value="/static/images/classroom.png"/>"/>
        </div>

        <div class="col-6">
            <h3 class="welcome-text-white-title">
                It is not difficult to find students!
            </h3>
            <h5 class = "welcome-text-white">
                We offer students and tutors free pricing, teachers do not pay commissions and have no obligations
                to the platform, except for one thing - to do their job well.
                You may not have the experience, but you may have the talent to teach other people.
                Being a teacher is, first of all, a great love for children and a willingness to give them all your knowledge and strength.
                A teacher should always be ready to discover new, unknown things.
            </h5>
        </div>

    </div>
</div>

</body>
</html>