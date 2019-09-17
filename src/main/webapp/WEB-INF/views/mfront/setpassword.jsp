<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<title>Login & Register</title>
	<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, viewport-fit=cover">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<!--  禁用telphone -->
	<meta name="format-detection" content="telephone=no">
	<!-- 关闭下拉刷新 -->
	<meta id="WV.Meta.DisableRefresh" value="true">
	<!-- 禁用ios的bounces效果 -->
	<meta id="WV.Meta.Share.Disabled" value="true">
	<!-- uc 字体放大-->
	<meta name="wap-font-scale" content="no">
	<meta name="aplus-touch" content="1">
	<link rel="stylesheet" href="${APP_PATH }/static/common/swiper/swiper.min.css">
	<script src="${APP_PATH }/static/common/swiper/swiper.min.js"></script>
	<!-- Facebook Pixel Code 这是facebook广告插件可以注释掉，但不要删除-->
	<script>
	  !function(f,b,e,v,n,t,s)
	  {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
	  n.callMethod.apply(n,arguments):n.queue.push(arguments)};
	  if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
	  n.queue=[];t=b.createElement(e);t.async=!0;
	  t.src=v;s=b.getElementsByTagName(e)[0];
	  s.parentNode.insertBefore(t,s)}(window, document,'script',
	  'https://connect.facebook.net/en_US/fbevents.js');
	  fbq('init', '246433859565492');
	  //fbq('init', '667403967094866');
	  fbq('track', 'PageView');
	</script>
	<noscript><img height="1" width="1" style="display:none"src="https://www.facebook.com/tr?id=667403967094866&ev=PageView&noscript=1"/></noscript>
	<!-- End Facebook Pixel Code -->
	
	<script>
  		fbq('track', 'ViewContent');
	</script>
	<!-- Facebook Pixel Code end -->
</head>

<body>

	<jsp:include page="mheader.jsp"></jsp:include>




	<!-- main -->
	<div class="main">
		<div class="container loginregister">
			<div class="login-register">
				<div class="tab-pane signin active">
					<form id="chage_password">
						<div class="input-group">
							<label for="userEmail">e-mail</label>
							<input type="email" name="userEmail" placeholder="eamil">
						</div>
						<div class="input-group">
							<label for="">password</label>
							<input class="newpassword" type="password" name="userPassword" placeholder="password">
						</div>
						<div class="input-group">
							<label for="">password</label>
							<input class="repassword" type="password" name="userPassword" placeholder="password">
						</div>
					</form>
					<a href="javascript:;" class="btn btn-black save_newpass"> SURE </a>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="mfooter.jsp"></jsp:include>
</body>

<script>
		$('.btn.save_newpass').on('click', function () {
		var email = $('#chage_password input[name=userEmail]').val();
		var password = $('#chage_password input[name=userPassword]').val();
		var newPass = $('#chage_password input.newpassword').val();
		var txtRePass=$('#chage_password input.repassword').val();
		var reqData = {};
		reqData.userEmail = email;
		reqData.userPassword = password;
		// console.log(reqData);
		if (inputCheck(reqData)) {
			$.ajax({
				url: "${APP_PATH }/MlfrontUser/reSetPwd", //register
				type: 'POST',
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					// console.log(data);
					var resData = data.extend;
						renderSysMsg(resData.resMsg);
						setTimeout(function(){
							$('.sys-box').remove();
							$('.mask').remove();
							sysFlag = !sysFlag;
						},1000)
						setInterval(function() {
							if (sysFlag) {
								window.location.href = "${APP_PATH}/MlfrontUser/toLoginRegisterPage";	
							}
						}, 1000);
					
				}
			})
		} else {
			renderSysMsg('email or password format incorrect!');
		}
	})
	
		function inputCheck(data) {
		var flag = true;
		for (var key in data) {
			if (key === 'userEmail') {
				var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				if (!pattern.test(data[key])) {
					flag = !flag;
					break;
				}
			}
			if (key === 'userPassword') {
				if (data[key].trim().length < 6) {
					flag = !flag;
					break;
				}
			}
			var newPass = $('#chage_password input.newpassword').val();
			var txtRePass=$('#chage_password input.repassword').val();
			 if(newPass != txtRePass){
				flag = !flag;
				break;
			}
		}
		
		return flag;
	}
	
</script>


</html>