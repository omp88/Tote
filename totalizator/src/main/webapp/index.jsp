<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>welcome page</title>
</head>
<body>

<jsp:forward page="Controller">
<jsp:param value="welcome" name="command"/>
</jsp:forward>

</body>
</html>