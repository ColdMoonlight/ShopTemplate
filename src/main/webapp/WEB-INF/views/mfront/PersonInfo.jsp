<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<title>Personnal</title>
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
	<div class="main person-info">
		<div class="form-op"><span class="edit-save" data-flag="0">编辑</span></div>
		<div class="form">
			<input class="userid" name="userId" type="hidden" />
			<div class="input-group">
				<label for="userEmail">e-mail</label>
				<div class="input userEmail"></div>
			</div>
			<div class="input-group">
				<label for="userPassword">password</label>
				<div class="input userPassword"></div>
			</div>
			<div class="input-group">
				<label for="userTelephone">telphone</label>
				<div class="input userTelephone"></div>
			</div>
			<div class="input-group">
				<label for="userFirstname">firstName</label>
				<div class="input userFirstname"></div>
			</div>
			<div class="input-group">
				<label for="userLastname">lastname</label>
				<div class="input userLastname"></div>
			</div>
			<div class="input-group">
				<label for="userAddressCountry">country</label>
				<div class="input userAddressCountry"></div>
			</div>
			<div class="input-group">
				<label for="userAddressProvince">province</label>
				<div class="input userAddressProvince"></div>
			</div>
			<div class="input-group">
				<label for="userAddressCity">city</label>
				<div class="input userAddressCity"></div>
			</div>
			<div class="input-group">
				<label for="userAddressStreetaddress">details address</label>
				<div class="input userAddressStreetaddress"></div>
			</div>
			<div class="input-group">
				<label for="userAddressPostalcode">post code</label>
				<div class="input userAddressPostalcode"></div>
			</div>
			<div class="input-group">
				<label for="userPoint">user point</label>
				<div class="input userPoint"></div>
			</div>
		</div>
	</div>

	<jsp:include page="mfooter.jsp"></jsp:include>
</body>

<script>
	/* 初始化请求这个接口
 ${APP_PATH }/MlfrontUser/getLoginUserDetail
 
 
 无参数，post请求
 
$('.btn.signout').on('click', function() {	
	console.log('*******')
	$.ajax({
		url: "${APP_PATH }/MlfrontUser/exit",
		type:'POST',
		success:function(data) {
			console.log(data);
			var resData = data.extend;
			
			if (resData.exitInt === 0) {
				alert(resData.resMsg)
				window.location.href="${APP_PATH }/MlfrontUser/toLoginRegisterPage";
			}
		}
	})
})

    private Integer userId;
	private String userEmail;
    private String userPassword;(明文展示就行)
    private String userTelephone;
    private String userAddressCountry;
    private String userAddressProvince;
    private String userAddressCity;
    private String userAddressStreetaddress;
    private String userAddressPostalcode;
    private String userFirstname;
    private String userLastname;
    // private String userLastonlinetime;
    private Integer userPoint;
    // private String userCouponidstr; .split(","),显示成数量s
    
点击编辑，修改完走

json格式，post请求

${APP_PATH }/MlfrontUser/update 
*/
	// get user info
	$.ajax({
		url: "${APP_PATH }/MlfrontUser/getLoginUserDetail",
		type: 'POST',
		success: function (data) {
			//console.log(data);
			var resData = data.extend.mlfrontUserOne;
			//console.table(resData)
			tianchong(resData)
		}
	})

	function tianchong(data) {
		$(".userid").val(data.userId);
		$(".input.userEmail").text(data.userEmail ? data.userEmail : '请添加');
		$(".input.userPassword").text(data.userPassword ? data.userPassword : '请添加');
		$(".input.userTelephone").text(data.userTelephone ? data.userTelephone : '请添加');
		$(".input.userAddressCountry").text(data.userAddressCountry ? data.userAddressCountry : '请添加');
		$(".input.userAddressProvince").text(data.userAddressProvince ? data.userAddressProvince : '请添加');
		$(".input.userAddressCity").text(data.userAddressCity ? data.userAddressCity : '请添加');
		$(".input.userAddressStreetaddress").text(data.userAddressStreetaddress ? data.userAddressStreetaddress : '请添加');
		$(".input.userAddressPostalcode").text(data.userAddressPostalcode ? data.userAddressPostalcode : '请添加');
		$(".input.userFirstname").text(data.userFirstname ? data.userFirstname : '请添加');
		$(".input.userLastname").text(data.userLastname ? data.userLastname : '请添加');
		$(".input.userPoint").text(data.userPoint ? data.userPoint : '请添加');
	}

	var editSave = $('.edit-save');

	editSave.on('click', function () {
		var flag = parseInt($(this).data('flag'));
		if (!flag) {
			$(this).attr('data-flag', 1).text('保存');
			$('.input').each(function (i, item) {
				$(item).attr('contenteditable', true);
			});
			$('.input').eq(0).focus();
		} else {
			var reqData = {
				userId: parseInt($(".userid").val()),
				userEmail: $(".input.userEmail").text() === "请添加" ? null : $(".input.userEmail").text(),
				userPassword: $(".input.userPassword").text() === "请添加" ? null : $(".input.userPassword").text(),
				userTelephone: $(".input.userTelephone").text() === "请添加" ? null : $(".input.userTelephone").text(),
				userAddressCountry: $(".input.userAddressCountry").text() === "请添加" ? null : $(".input.userAddressCountry")
					.text(),
				userAddressProvince: $(".input.userAddressProvince").text() === "请添加" ? null : $(
					".input.userAddressProvince").text(),
				userAddressCity: $(".input.userAddressCity").text() === "请添加" ? null : $(".input.userAddressCity").text(),
				userAddressStreetaddress: $(".input.userAddressStreetaddress").text() === "请添加" ? null : $(
					".input.userAddressStreetaddress").text(),
				userAddressPostalcode: $(".input.userAddressPostalcode").text() === "请添加" ? null : $(
					".input.userAddressPostalcode").text(),
				userFirstname: $(".input.userFirstname").text() === "请添加" ? null : $(".input.userFirstname").text(),
				userLastname: $(".input.userLastname").text() === "请添加" ? null : $(".input.userLastname").text(),
				userPoint: $(".input.userPoint").text() === "请添加" ? null : parseInt($(".input.userPoint").text())
			}
			// console.table(reqData)
			// save
			$.ajax({
				url: "${APP_PATH }/MlfrontUser/update",
				type: 'POST',
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					// console.log(data);
					if (data.code === 100) {
						alert(data.extend.resMsg);

						$(this).attr('data-flag', 0).text('编辑');
						$('.input').each(function (i, item) {
							$(item).removeAttr('contenteditable');
						});
					}
				}
			})

		}
	})

	$('.input').each(function (i, item) {
		var flag = editSave.data('flag');
		$(item).on('click', function () {
			$(this).attr('contenteditable', true);
			$(this).focus();
			if (!flag) {
				editSave.attr('data-flag', 1).text('保存');
			}
		})
	});
</script>


</html>