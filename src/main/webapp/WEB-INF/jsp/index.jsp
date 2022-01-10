<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<link rel="stylesheet" href="/css/index.css">
<meta charset="UTF-8">
<title>Register</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="container">
		<div class="title">Register</div>
			<div class="content">
				<form action="/register" method="post">
					<div class="user-details">
						<div class="input-box">
							<div class="input-box">
								<span class="details">Username</span> <input type="text"
								name="userName" required="required" />
							</div>
							<div class="input-box">
								<span class="details">Firstname</span> <input type="text"
								name="userFirstName" required="required" />
							</div>
							<div class="input-box">
								<span class="details">Lastname</span> <input type="text"
								name="userLastName" required="required" />
							</div>
							<div class="input-box">
								<span class="details">Password</span> <input type="password"
								name="userPassword" required="required" />
							</div>
							<div class="input-box">
								<span class="details">Role</span> <input type="text" name="role"
								required="required" />
							</div>
							<div class="button">
								<input type="submit" value="Register">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<a href="/login">Already User?Login</a>
</body>
</html>
