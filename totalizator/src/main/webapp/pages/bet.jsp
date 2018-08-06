<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" />
<html>
<head>

<title>Bet</title>

<%@include file="header_info.jsp"%>
</head>
<body>

	<nav>
		<div class="nav-container">
			<div class="navbar-header">
				<form action="Controller" method="GET">
					<input type="hidden" name="command" value="welcome" />
					<button type="submit" class="btn btn-info">
						<fmt:message key="text.title" />
					</button>
				</form>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<form action="Controller" method="GET">
							<input type="hidden" name="command" value="log_out" />
							<button type="submit" class="btn btn-warning">
								<span class="glyphicon glyphicon-log-out"></span>
								<fmt:message key="text.logout" />
							</button>
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
 
	<div class="content-wrap">
		<div class="table-responsive">
			<table class="table">
				<tr>
					<td><fmt:message key="text.event_id" /></td>
					<td><fmt:message key="text.event_name" /></td>
					<td><fmt:message key="text.event_start_time" /></td>
					<td><fmt:message key="text.team1_name" /></td>
					<td><fmt:message key="text.team2_name" /></td>
					<td><fmt:message key="text.team1_win" /></td>
					<td><fmt:message key="text.team2_win" /></td>
					<td><fmt:message key="text.draw" /></td>
				</tr>
				<tr>
					<td>${event.id}</td>
					<td>${event.footballName}</td>
					<td>${event.startTime}</td>
					<td>${event.team1Name}</td>
					<td>${event.team2Name}</td>
					<td>${event.team1WinCoef}</td>
					<td>${event.team2WinCoef}</td>
					<td>${event.drawCoef}</td>
				</tr>
			</table>

			<h2 class="form-label">
				<fmt:message key="text.choose_result" />
			</h2>
			<form class="register-form" action="Controller" method="POST">
				<input type="hidden" name="command" value="bet_done" />
				<input type="hidden" name="event_id" value="${event.id}" /> 
				<input type="hidden" name="team1_win_coeff" value="${event.team1WinCoef}" />
				<input type="hidden" name="team2_win_coeff"	value="${event.team2WinCoef}" /> 
				<input type="hidden" name="draw_coeff" value="${event.drawCoef}" />
				<div class="form-row">
					<label><fmt:message key="text.event_result" /></label> 
					<label>
						<input type="radio" name="condition" value="first" />${event.team1Name} <fmt:message key="text.win" />
					</label>
					 <label>
					 <input type="radio" name="condition" value="second" />${event.team2Name} <fmt:message key="text.win" />
					 </label> 
					 <label>
					 <input type="radio" name="condition" value="draw" /><fmt:message key="text.draw" />
					 </label>
				</div>
				<div class="form-row">
					<label><fmt:message key="text.or_guess_the_score" /></label> 
					<label><fmt:message	key="text.clarification" /></label>
				</div>
				<div class="form-row">
					<label>${event.team1Name}</label> 
					<input class="form-control"	type="number" name="team1_score" min="0" maxlength="4" size="5" />
				</div>
				<div class="form-row">
					<label>${event.team2Name}</label>
					<input class="form-control" type="number" name="team2_score" min="0" maxlength="4" size="5" />
				</div>
				<div class="form-row">
					<label><fmt:message key="text.bet_amount" /></label> 
					<input class="form-control" type="number" name="amount" maxlength="15" min="0.01" step="0.01" required />
				</div>
				<div class="form-row">
					<input class="btn btn-info" value=<fmt:message key="text.submit"/>	type="submit" />
				</div>
			</form>
		</div>
	</div>

	<div class="content-wrap">
	<ctg:footer/>
	</div>
</body>
</html>