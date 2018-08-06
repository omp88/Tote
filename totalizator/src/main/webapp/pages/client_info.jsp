<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}"  />
<fmt:setBundle basename="locale"/>

<html>
<head>
<title>Main</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
 
 <h2 class="form-label"><fmt:message key="text.done_bets"/></h2>
 
 <div class="content-wrap"> 
      <div class="table-responsive">
		<table class="table">
				<tr>
				<td><fmt:message key="text.bet_id"/></td>
				<td><fmt:message key="text.event_id"/></td>
				<td><fmt:message key="text.condition"/></td>
				<td><fmt:message key="text.amount"/></td>
				<td><fmt:message key="text.team1_score"/></td>
				<td><fmt:message key="text.team2_score"/></td>
				<td><fmt:message key="text.coeff"/></td>
				<td><fmt:message key="text.result"/></td>
				</tr>
			<c:forEach items="${done_bets}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.eventId}</td>
					<td>${item.condition}</td>
					<td>${item.amount}</td>
					<td>${item.team1ExpectedScore}</td>
					<td>${item.team2ExpectedScore}</td>
					<td>${item.coeff}</td>
					<td>${item.state}</td>
				  <c:if test="${item.state eq 'WIN'}">
				  <c:set var="win" value="${fn:split(item.amount*item.coeff, '.')}"/>
				  <td>${win[0]}</td>
				  </c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
    </div>
    
    <h2 class="form-label"><fmt:message key="text.stand_bets"/></h2>
    
    <div class="content-wrap"> 
      <div class="table-responsive">
		<table class="table">
		<tr>
				<td><fmt:message key="text.bet_id"/></td>
				<td><fmt:message key="text.event_id"/></td>
				<td><fmt:message key="text.condition"/></td>
				<td><fmt:message key="text.amount"/></td>
				<td><fmt:message key="text.team1_score"/></td>
				<td><fmt:message key="text.team2_score"/></td>
				<td><fmt:message key="text.coeff"/></td>
				</tr>
			<c:forEach items="${stand_bets}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.eventId}</td>
					<td>${item.condition}</td>
					<td>${item.amount}</td>
					<td>${item.team1ExpectedScore}</td>
					<td>${item.team2ExpectedScore}</td>
					<td>${item.coeff}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
    </div>
    
    <h2 class="form-label"><fmt:message key="text.add_money"/></h2>
	<form class="register-form" action="Controller" method="POST">
		<input type="hidden" name="command" value="add_money">
		<div class="form-row">
		<input class="form-control" type="number" name="amount" min="0.01" step="0.01" maxlength="10" required />
		</div>
		<div class="form-row">
		<input class="btn btn-info" value=<fmt:message key="text.submit"/> type="submit" />
		</div>
	</form>


 <div class="content-wrap">
	<ctg:footer/>
	</div>
</body>
</html>