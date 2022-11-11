<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">

        <a class="navbar-brand" href="<c:url value="/main"/>">
            <img src="<c:url value="/static/images/free-icon-studying-1903172.png"/>" alt="IE" width="40" height="40">
        </a>

        <a class="navbar-brand text-white" href="<c:url value="/main"/>">IndependentEducation</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Sign up
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="<c:url value="/registration_tutor"/>">As tutor</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/registration_student"/>">As student</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
