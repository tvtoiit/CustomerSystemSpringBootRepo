<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Training</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<div class="content">
		<div class="content-text">Login</div>
		<div class="content-login">
			<div class="content-login__header">
				<h3>LOGIN</h3>
				<div id="lblErrorMessage">
					 <!-- <html:errors/> -->
					 <%-- <form:errors path="userId" cssClass="error" /> --%>
				</div>
			</div>
			<div class="content-login__container">
				<div class="form-group">
					<label for="fullname" class="form-label form-label__userID">User Id: </label>
					<input name="userId" id="txtUserID" value="" maxlength="8" class="form-control" />
				</div>
				<div class="form-group__password">
					<label for="password" class="form-label">Password: </label>
					<input name="passWord" id="txtPassword" value="" maxlength="8" class="form-control" />
				</div>
				<div class="form-group__btn">
					<button id="btnLogin" class="form-submit">Login</button>
				</div>
			</div>
		</div>
	</div>
	
	 <script>
        $(document).ready(function() {
            $("#btnLogin").click(function() {
                var userId = $("#txtUserID").val();
                var password = $("#txtPassword").val();

                // Gửi dữ liệu bằng AJAX
                $.ajax({
                    type: "POST",
                    url: "/login", // Đặt URL của controller tại đây
                    data: {
                        userId: userId,
                        passWord: password
                    },
                    success: function(response) {
                       window.location.href = "/Search";
                    },
                    error: function(error) {
                    	console.error(xhr.responseText);
                        console.error(status);
                        console.error(error);
                    }
                });
            });
        });
    </script>
</body>
</html>