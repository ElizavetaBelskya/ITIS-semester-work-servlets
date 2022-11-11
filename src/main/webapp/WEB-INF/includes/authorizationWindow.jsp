<form action='' method='POST'>
  <div id="sign-in-menu">
    <div class = "sign-in-form">
      <label for="Email1" class="form-label">Email address</label>
      <input type="email" name='email' required class="form-control" id="Email1" value="${email}" placeholder="email@example.com"
             pattern="[A-Za-z0-9-]{2,50}@[a-z]{2,20}.[a-z]{2,4}">
    </div>

    <div class = "sign-in-form">
      <label for="Password1" class="form-label">Password</label>
      <input type="password" name='password' required class="form-control" id="Password1" pattern="[A-Za-z0-9]{5,50}"
             title="The password is in incorrect format" placeholder="Password">
    </div>

    <div>
      <input class="form-check-input" type="radio" name="role" value='tutor' id="radio1" checked>
      <label class="form-check-label">Tutor</label>
      <input class="form-check-input" type="radio" name="role" value='student' id="radio2">
      <label class="form-check-label">Student</label>
    </div>

    <button type="submit" id="sign-in-btn" class="btn btn-primary">Sign in</button>

    <c:if test="${alertMessage != null}">
      <div class="alert alert-warning" role="alert">${alertMessage}</div>
    </c:if>
  </div>

</form>