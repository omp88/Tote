<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}"  />
<fmt:setBundle basename="locale"/>

<html>

<%@include file="header_info.jsp" %>

</head>

<body>

<nav>
		<div class="nav-container">
			<div class="navbar-header">
				<form action="Controller" method="GET">
							<input type="hidden" name="command" value="welcome" />
							<button type="submit" class="btn btn-info"><fmt:message key="text.title"/></button>
						</form>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<form action="Controller" method="GET">
							<input type="hidden" name="command" value="register" />
							<button type="submit" class="btn btn-info"><fmt:message key="text.register"/></button>
						</form>
					</li>
					<li>
						<form action="Controller" method="GET">
							<input type="hidden" name="command" value="log_in" />
							<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-log-in"></span>  <fmt:message key="text.auth"/></button>
						</form></li>
					<li>
						<form action="Controller" method="GET">
							<input type="hidden" name="command" value="log_out" />
							<button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-log-out"></span>  <fmt:message key="text.logout"/></button>
						</form>
					</li>
				</ul>
			</div>
		</div>
	</nav>

<c:if test="${message != null}">
 <div class="content-wrap">
 <div class="alert alert-success">
  <label><fmt:message key="${message}"/></label>
 </div>
 </div>
 </c:if>
 <c:remove var="message" scope="session" />
 
 <c:if test="${error != null}">
 <div class="content-wrap">
 <div class="alert alert-warning">
  <label><fmt:message key="${error}"/></label>
 </div>
 </div>
 </c:if>
 <c:remove var="error" scope="session" />
	
	<form class="register-form" action="Controller" method="POST">
		<input type="hidden" name="command" value="registered"/>
		<div class="form-row">
			<label><fmt:message key="text.first_name"/></label>
			<input class="form-control" type="text" name="first_name" maxlength="30" required/>
		</div>
		<div class="form-row">
			<label><fmt:message key="text.last_name"/></label>
			<input class="form-control" type="text" name="last_name" maxlength="30" required/>
		</div>
		<div class="form-row">
			<label><fmt:message key="text.email"/></label>
			<input class="form-control" type="email" name="email" maxlength="30" pattern="[A-z0-9._%+-]+@[A-z0-9.-]+\.[a-z]{2,3}$" required/>
		</div>
		<div class="form-row">
			<label><fmt:message key="text.login"/></label>
			<input class="form-control" type="text" name="login" maxlength="30" required/>
		</div>
		<div class="form-row">
			<label><fmt:message key="text.password"/></label>
			<input class="form-control" type="password" name="password" maxlength="30" required/>
		</div>
		<div class="form-row">
			<label><fmt:message key="text.passport"/></label>
			<input class="form-control" type="text" name="passport" maxlength="30" required/>
		</div>
		<div class="form-row">
			<input class="btn btn-info" value=<fmt:message key="text.submit"/> type="submit" />
		</div>
	</form>
</body>
</html>