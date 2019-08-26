<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<title>User Center</title>
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
</head>

<body>

	<jsp:include page="mheader.jsp"></jsp:include>

	<!-- main -->
	<div class="main pserson-center">
		<img class="purechase-step" src="${APP_PATH }/static/m/img/other/personCenter.jpg">
		<!-- <div class="person-title">Personal Center</div> -->
		<div class="person-info">
			<img src="" alt="" class="person-info-img">
			<div class="person-info-name" id="UHello_zsh">login in</div>
			<a href="javascript:;" class="btn signout">sign out</a>
		</div>

		<div class="integral-details">
			<div class="id-title bd-b">My All Details</div>
			<div class="id-list">
				<div class="id-item" id="order_info">
					<img src="${APP_PATH }/static/m/img/other/My-Order.png" class="id-item-img">
					<div class="id-item-name">My Order</div>
				</div>
				<div class="id-item" id="shopping-cart">
					<img src="${APP_PATH }/static/m/img/other/Shopping-Cart.png" class="id-item-img">
					<div class="id-item-name">Shopping Cart</div>
				</div>
				<div class="id-item" id="coupon_info">
					<img src="${APP_PATH }/static/m/img/other/Coupon.png" class="id-item-img">
					<div class="id-item-name">Coupon</div>
				</div>
				<div class="id-item" id="personal_info">
					<img src="${APP_PATH }/static/m/img/other/Personal-Data.png" class="id-item-img">
					<div class="id-item-name">Personal Info</div>
				</div>
				<div class="id-item">
					<img src="${APP_PATH }/static/m/img/other/Integral-Details.png" class="id-item-img">
					<div class="id-item-name">Integral Details</div>
				</div>
				<div class="id-item">
					<img src="${APP_PATH }/static/m/img/other/Wish-List.png" class="id-item-img">
					<div class="id-item-name">more</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="mfooter.jsp"></jsp:include>
</body>


<script type="text/javascript">
	var Session_userEmail = '${sessionScope.loginUser.userEmail}';
	var Session_userFirstname = '${sessionScope.loginUser.userFirstname}';
	var Session_userLastname = '${sessionScope.loginUser.userLastname}';
	if (Session_userFirstname == "" && Session_userLastname != "") {
		$("#UHello_zsh").html(Session_userLastname);
	} else if (Session_userFirstname != "" && Session_userLastname == "") {
		$("#UHello_zsh").html(Session_userFirstname);
	} else if (Session_userFirstname != "" && Session_userLastname != "") {
		$("#UHello_zsh").html(Session_userFirstname + " " + Session_userLastname);
	} else {
		$("#UHello_zsh").html(Session_userEmail);
	}
</script>
<script>
	//  ${APP_PATH }/MlfrontUser/exit
	$('.btn.signout').on('click', function () {
		$.ajax({
			url: "${APP_PATH }/MlfrontUser/exit",
			type: 'POST',
			success: function (data) {
				// console.log(data);
				var resData = data.extend;

				if (resData.exitInt === 0) {
					// console.log(resData.resMsg)
					window.location.href = "${APP_PATH }/MlfrontUser/toLoginRegisterPage";
				}
			}
		})
	})


	// 直接   window.location.href="${APP_PATH }/MlfrontUser/toPersonInfoPage";到这个页面再请求userInfo信息

	$('#order_info').on('click', function () {
		window.location.href = "${APP_PATH }/MlfrontUser/tommyOrderPage";
	})

	$('#shopping-cart').on('click', function () {
		window.location.href = "${APP_PATH }/MlbackCart/toCartList";
	})

	$('#coupon_info').on('click', function () {
		window.location.href = "${APP_PATH }/MlfrontUser/tomCouponPage";
	})

	$('#personal_info').on('click', function () {
		window.location.href = "${APP_PATH }/MlfrontUser/toPersonInfoPage";
	})
</script>

</html>