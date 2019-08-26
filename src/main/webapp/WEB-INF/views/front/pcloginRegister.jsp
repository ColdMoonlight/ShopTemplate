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
	  fbq('init', '667403967094866');
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
	<jsp:include page="pcheader.jsp"></jsp:include>
	<!-- main -->
	<div class="main">
		<div class="container loginregister">
			<div class="login-register">
				<div class="tab">
					<div class="tab-item active" data-id="signin">Sign In</div>
					<div class="tab-item" data-id="register">Register</div>
				</div>
				<div class="tab-pane signin active">
					<form id="signin">
						<div class="input-group">
							<label for="userEmail">e-mail</label>
							<input type="email" name="userEmail" placeholder="eamil" required>
						</div>
						<div class="input-group">
							<label for="userPassword">password</label>
							<input type="password" name="userPassword" placeholder="password at least six figures">
						</div>
					</form>
					<a href="javascript:;" class="btn btn-black sigin"> SIGIN </a>
					<div class="input-group">
						<p class="forget_pass"><a href="${APP_PATH }/MlfrontUser/topcForgetPassWord">Forget PassWord?</a></p>
					</div>
				</div>
				<div class="tab-pane register">
					<form id="register">
						<div class="input-group">
							<label for="userEmail">e-mail</label>
							<input type="email" name="userEmail" placeholder="please input eamil" required>
								<p class="error_tips">Please enter the correct mailbox</p>
						</div>
						<div class="input-group">
							<label for="userPassword">password</label>
							<input type="password" name="userPassword" placeholder="password at least six figures">
						</div>
					</form>
			
					<a href="javascript:;" class="btn btn-black register"> REGISTER </a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="pcfooter.jsp"></jsp:include>
</body>
<script>
	var loginRegister = $('.login-register');
	var activeItem = loginRegister.find('.tab-item.active');
	var tabPaneActive = loginRegister.find('.tab-pane.active');
	loginRegister.find('.tab-item').each(function (i, item) {
		$(item).on('click', function () {
			if ($(this).html() !== activeItem.html()) {
				activeItem.removeClass('active');
				activeItem = $(this);
				activeItem.addClass('active')
				var tabPaneId = $(this).data('id');
				tabPaneActive.removeClass('active');
				tabPaneActive = loginRegister.find('.tab-pane.' + tabPaneId);
				tabPaneActive.addClass('active');
			}
		})
	})
	/**
	 private String userEmail;
	 private String userPassword;
	 ${APP_PATH }/MlfrontUser/login",//register
	 */

	$('.btn.sigin').on('click', function () {
		var email = $('#signin input[name=userEmail]').val();
		var password = $('#signin input[name=userPassword]').val();
		var reqData = {};
		reqData.userEmail = email;
		reqData.userPassword = password;
		// console.log(reqData);

		if (inputCheck(reqData)) {
			$.ajax({
				url: "${APP_PATH }/MlfrontUser/login", //register
				type: 'POST',
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					// console.log(data);
					var resData = data.extend;
					if (resData.loginYes === 0) {
						renderSysMsg(resData.resMsg);
						sysFlag = !sysFlag;
					}
					if (resData.loginYes === 1) {
						renderSysMsg(resData.resMsg);
						setTimeout(function(){
							$('.sys-box').remove();
							$('.mask').remove();
							sysFlag = !sysFlag;
						},800)
						setInterval(function() {
							// if (sysFlag) {
								window.location.href = "${APP_PATH}/index.html";	
							// }
						}, 1000);
					}
				}
			})
		} else {
			renderSysMsg('email or password format incorrect!');
		}
	})
    var emailf = $('#register input[name=userEmail]');
     emailf.focus(function(){
		 $(this).parents(".input-group").find(".error_tips").show();
	 })

	$('.btn.register').on('click', function () {
		var email = $('#register input[name=userEmail]').val();
		var password = $('#register input[name=userPassword]').val();
		var reqData = {};
		reqData.userEmail = email;
		reqData.userPassword = password;
		// console.log(reqData);

		if (inputCheck(reqData)) {
			$.ajax({
				url: "${APP_PATH }/MlfrontUser/register",
				type: 'POST',
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					// console.log(data);
					var resData = data.extend;
					if (resData.registerYes === 0) {
						renderSysMsg(resData.resMsg)
					}

					if (resData.registerYes === 1) {
						fbq('track', 'CompleteRegistration');//追踪'注册'事件		facebook广告插件可以注释掉，但不要删除
						renderSysMsg(resData.resMsg);
						setTimeout(function(){
							$('.sys-box').remove();
							$('.mask').remove();
							sysFlag = !sysFlag;
						},1000)
						setInterval(function() {
							// if (sysFlag) {
								window.location.href = "${APP_PATH}/index.html";	
							// }
						}, 1000);
					}
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
		}

		return flag;
	}
</script>


</html>