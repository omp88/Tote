<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}"  />
<fmt:setBundle basename="locale"/>

<html>
<head>

<title>Admin page</title>

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

     <h2 class="form-label"><fmt:message key="text.add_new_event"/></h2>
     
	<form class="register-form" action="Controller" method="POST">
		<input type="hidden" name="command" value="add_event">

				<div class="form-row">
				<label><fmt:message key="text.event_name"/></label>
				<input type="text" name="event_name" maxlength="40" required/>
				</div>
				<div class="form-row">
				<label><fmt:message key="text.event_start_time"/></label>
				<input class="form-control" type="datetime-local" name="event_start" required />
				</div>
				<div class="form-row">
				<label><fmt:message key="text.team1_name"/></label>
				<input class="form-control" type="text" name="team1_name" maxlength="40" required />
				</div>
				<div class="form-row">
				<label><fmt:message key="text.team2_name"/></label>
				<input class="form-control" type="text" name="team2_name" maxlength="40" required/>
				</div>
				<div class="form-row">
				<label><fmt:message key="text.team1_win_coeff"/></label>
				<input class="form-control" type="number" step="any" name="team1_win_coeff" maxlength="5" required/>
				</div>
				<div class="form-row">
				<label><fmt:message key="text.team2_win_coeff"/></label>
				<input class="form-control" type="number" step="any" name="team2_win_coeff" maxlength="5" required/>
				</div>
				<div class="form-row">
				<label><fmt:message key="text.draw_coeff"/></label>
				<input class="form-control" type="number" step="any" name="draw_coeff" maxlength="5" required/>
				</div>
				<div class="form-row">
				<input class="btn btn-info" type="submit" value=<fmt:message key="text.submit"/> />
				</div>
	</form>
	
	<h2 class="form-label"><fmt:message key="text.delete_event"/></h2>
	<form class="register-form" action="Controller" method="POST">
		<input type="hidden" name="command" value="delete_event">
		<div class="form-row">
		<label><fmt:message key="text.event_id"/></label>
		<input class="form-control" type="number" name="event_id" min="1" maxlength="10" required />
		</div>
		<div class="form-row">
		<input class="btn btn-info" value=<fmt:message key="text.submit"/> type="submit" />
		</div>
	</form>

	<h2 class="form-label"><fmt:message key="text.add_new_result"/></h2>

	<form class="register-form" action="Controller" method="POST">
		<input type="hidden" name="command" value="add_result">
		<div class="form-row">
			<label><fmt:message key="text.event_id"/></label>
			<input class="form-control" type="number" name="event_id" maxlength="10" required/>
		</div>
		<div class="form-row">
			<label><fmt:message key="text.event_result"/></label>
			<label><input type="radio" name="result" VALUE="first" required /><fmt:message key="text.first"/></label>
			<label><input type="radio" name="result" VALUE="second" required /><fmt:message key="text.second"/></label>
			<label><input type="radio" name="result" VALUE="draw" required /><fmt:message key="text.draw"/></label>
		</div>
		<div class="form-row">
			<label><fmt:message key="text.team1_score"/></label>
			<input class="form-control" type="number" name="team1_score" min="0" size="10" maxlength="3" required />
		</div>	
		<div class="form-row">
			<label><fmt:message key="text.team2_score"/></label>
			<input class="form-control" type="text" name="team2_score"  min="0" size="10" maxlength="3" required/>
		</div>	
		<div class="form-row">	
			<input class="btn btn-info" type="submit" value=<fmt:message key="text.submit"/> />
		</div>
	</form>
	
	
	<h2 class="form-label" ><fmt:message key="text.find_client_bets"/></h2>
	<form class="register-form" action="Controller" method="POST">
		<input type="hidden" name="command" value="find_client_bets">
		<div class="form-row">
		<label><fmt:message key="text.client_id"/></label>
		<input class="form-control" type="number" name="client_id" min="1" maxlength="10" required />
		</div>
		<div class="form-row">
		<input class="btn btn-info" type="submit" value=<fmt:message key="text.submit"/> />
		</div>
	</form>
	
	
	<h2 class="form-label"><fmt:message key="text.find_clients"/></h2>
	<form class="register-form" action="Controller" method="POST">
		<input type="hidden" name="command" value="find_clients">
		<div class="form-row">
		<input class="btn btn-info" type="submit" value=<fmt:message key="text.submit"/> />
		</div>
	</form>
	
	<h2 class="form-label" ><fmt:message key="text.find_event_bets"/></h2>
	<form class="register-form" action="Controller" method="POST">
		<input type="hidden" name="command" value="find_event_bets">
		<div class="form-row">
		<label><fmt:message key="text.event_id"/></label>
		<input class="form-control" type="number" name="event_id" min="1" maxlength="10" required />
		</div>
		<div class="form-row">
		<input class="btn btn-info" type="submit" value=<fmt:message key="text.submit"/> />
		</div>
	</form>
	



</body>
</html>