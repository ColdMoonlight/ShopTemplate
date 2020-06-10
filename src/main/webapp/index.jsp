<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="google-site-verification" content="SAL4KVmok7Gucsll1buOMRYO2RQVPPNdF163maxOVSU" />
<!-- google-site-verification 网站地图所需 上线时打开
	<meta name="google-site-verification" content="S_nwEF7Oc2MiSJHmAoJ8M8QA8vIA9QxyUrFDSTq1-YI" /> -->
<title></title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/crud
-->
<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>

</head>
<body>

<script>
	//接口初始hua
	  $(function() {
		window.location.href="${APP_PATH}/index.html";
		//启动index页面，调用接口判断请求来源 
	}) 
</script>
</body>

</html>