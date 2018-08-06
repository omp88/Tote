<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>

<head>

<title>Error</title>

<%@include file="header_info.jsp" %>
</head>
<body>

<nav>
		<div class="nav-container">
			<div class="navbar-header">
				<form action="Controller" method="GET">
							<input type="hidden" name="command" value="welcome" />
							<button type="submit" class="btn btn-info">TOTE</button>
						</form>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<form action="Controller" method="GET">
							<input type="hidden" name="command" value="register" />
							<button type="submit" class="btn btn-info">Register</button>
						</form>
					</li>
					<li>
						<form action="Controller" method="GET">
							<input type="hidden" name="command" value="log_in" />
							<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-log-in"></span>  Login</button>
						</form></li>
					<li>
						<form action="Controller" method="GET">
							<input type="hidden" name="command" value="log_out" />
							<button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-log-out"></span>  Logout</button>
						</form>
					</li>
				</ul>
			</div>
		</div>
	</nav>
<h2>OOps!</h2>
</body>
</html>