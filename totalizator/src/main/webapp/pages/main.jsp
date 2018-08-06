<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							<input type="hidden" name="command" value="welcome" />
							<input type="hidden" name="sessionLocale" value="ru_ru" />							
							<button type="submit" class="btn btn-basic"><fmt:message key="text.ru"/></button>
						</form>
					</li>
						<li>
						<form action="Controller" method="GET">
							<input type="hidden" name="command" value="welcome" />
							<input type="hidden" name="sessionLocale" value="en_us" />
							<button type="submit" class="btn btn-basic"><fmt:message key="text.en"/></button>
						</form>
					</li>
					 <c:if test="${role =='client'}">
						<li>
						<form action="Controller" method="GET">
							<input type="hidden" name="command" value="client_info" />
							<%-- <input type="hidden" name="client_id" value="${client.id}"> --%>
							<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-user"></span>  <fmt:message key="text.client_info"/></button>
						</form>
					</li>
					</c:if>
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


    <div class="content-wrap"> 
      <div class="table-responsive">
      <c:if test="${role =='client'}">
		<h2>Hello ${client.firstName} you have ${client.money} to play</h2>
	</c:if>
		<table class="table">
				<tr>
					<td><fmt:message key="text.id"/></td>
					<td><fmt:message key="text.short_event_name"/></td>
					<td><fmt:message key="text.short_event_start_time"/></td>
					<td><fmt:message key="text.short_team1_name"/></td>
					<td><fmt:message key="text.short_team2_name"/></td>
					<td><fmt:message key="text.short_team1_win_coeff"/></td>
					<td><fmt:message key="text.short_team2_win_coeff"/></td>
					<td><fmt:message key="text.short_draw_coeff"/></td>
					<td><fmt:message key="text.short_bet"/></td>
					</tr>
			<c:forEach items="${football}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.footballName}</td>
					<td>${item.startTime}</td>
					<td>${item.team1Name}</td>
					<td>${item.team2Name}</td>
					<td>${item.team1WinCoef}</td>
					<td>${item.team2WinCoef}</td>
					<td>${item.drawCoef}</td>
					<td>
						<form action="Controller" method="POST">
							<input type="hidden" name="command" value="bet" /> <input
								type="hidden" name="event_id" value="${item.id}" />
							<button type="submit" class="btn btn-success"><fmt:message key="text.bet"/></button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
    </div>
    <div class="content-wrap">
	<ctg:footer/>
	</div>
</body>
</html>