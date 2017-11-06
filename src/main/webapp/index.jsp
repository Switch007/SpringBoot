<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<script src="../../../assets/js/jquery-1.10.2.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	var url="https://uic.youzan.com/sso/open/initToken";
	$.post(url,{"client_id":"22ae4cd74ae9353df8","client_secret":"ab32e9fa93f1433d7b01d027e66cd471"},function(result){
		console(result.data.access_token);
	})
})
</script>
</head>
<body>
	<h2>Hello Wo6666rld!</h2>
	<c:out value="${xx}"></c:out>
	${xx}
</body>
</html>
