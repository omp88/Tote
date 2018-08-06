<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}"  />
<fmt:setBundle basename="locale"/>
<html>
<head>

<title>Admin result page</title>

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
							<input type="hidden" name="command" value="admin" />
							<button type="submit" class="btn btn-info"><fmt:message key="text.back"/></button>
						</form>
					</li>
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
	
	<div class="content-wrap"> 
      <div class="table-responsive">
		<table class="table">
		<c:if test="${clients != null}">
				<tr>

 					<td><fmt:message key="text.id"/></td>
 					<td><fmt:message key="text.first_name"/></td>
 					<td><fmt:message key="text.last_name"/></td>
 					<td><fmt:message key="text.email"/></td>
 					<td><fmt:message key="text.delete"/></td> 					
				</tr>
 </c:if>
			<c:forEach items="${clients}" var="item">
				<tr>
				    <td>${item.id}</td>
 					<td>${item.firstName}</td>
 					<td>${item.lastName}</td>
					<td>${item.email}</td>
					<td>
						<form action="Controller" method="POST">
							<input type="hidden" name="command" value="delete_client" /> <input
								type="hidden" name="client_id" value="${item.id}" />
							<button type="submit" class="btn btn-info"><fmt:message key="text.delete"/></button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
    </div>
	<div class="content-wrap"> 
      <div class="table-responsive">
		<table class="table">
		<c:if test="${clientBets != null}">
				<tr>

 					<td><fmt:message key="text.id"/></td>
 					<td><fmt:message key="text.event_id"/></td>
 					<td><fmt:message key="text.condition"/></td>
 					<td><fmt:message key="text.team1_score"/></td>
 					<td><fmt:message key="text.team2_score"/></td>
 					<td><fmt:message key="text.bet_amount"/></td>
 					<td><fmt:message key="text.delete"/></td> 					
				</tr>
 </c:if>
			<c:forEach items="${clientBets}" var="item">
				<tr>
				    <td>${item.id}</td>
 					<td>${item.eventId}</td>
 					<td>${item.condition}</td>
 					<td>${item.team1ExpectedScore}</td>
 					<td>${item.team2ExpectedScore}</td>
 					<td>${item.amount}</td>
					<td>
						<form action="Controller" method="POST">
							<input type="hidden" name="command" value="delete_bet" />
							 <input	type="hidden" name="bet_id" value="${item.id}" />
							<button type="submit" class="btn btn-info"><fmt:message key="text.delete"/></button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
    </div>
	<div class="content-wrap"> 
      <div class="table-responsive">
		<table class="table">
		<c:if test="${eventBets != null}">
				<tr>

 					<td><fmt:message key="text.id"/></td>
 					<td><fmt:message key="text.event_id"/></td>
 					<td><fmt:message key="text.condition"/></td>
 					<td><fmt:message key="text.team1_score"/></td>
 					<td><fmt:message key="text.team2_score"/></td>
 					<td><fmt:message key="text.bet_amount"/></td>
 					<td><fmt:message key="text.delete"/></td> 					
				</tr>
 </c:if>
		
			<c:forEach items="${eventBets}" var="item">
				<tr>
				    <td>${item.id}</td>
 					<td>${item.eventId}</td>
 					<td>${item.condition}</td>
 					<td>${item.team1ExpectedScore}</td>
 					<td>${item.team2ExpectedScore}</td>
 					<td>${item.amount}</td>
					<td>
						<form action="Controller" method="POST">
							<input type="hidden" name="command" value="delete_bet" />  
							<input type="hidden" name="bet_id" value="${item.id}" />
							<button type="submit" class="btn btn-info"><fmt:message key="text.delete"/></button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
    </div>

</body>
</html>