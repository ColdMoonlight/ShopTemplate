<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="UTF-8">
  <title>Introduce</title>
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

  <jsp:include page="pcheader.jsp"></jsp:include>

  <!-- main -->
  <div class="container word_box">
  	 <div class="main article"></div>
  </div>
 
  <jsp:include page="pcfooter.jsp"></jsp:include>
  
 	<script type="text/javascript">
		var footnavId = '${sessionScope.footnavId}';
		// console.log("footnavId:" + footnavId)
	  /* details of banner */
		$.ajax({
			url: '${APP_PATH}/MlbackFootNav/getOneMlbackFootNavOneAllDetail',
			data: {"footnavId": footnavId},
			type: "POST",
			success: function (data) {
				if (data.code === 100) {
					var resData = data.extend.MlbackFootNavOne;
					// console.log(resData);
          var elMain = $('.main');
          var html = /* '<div class="article-title">' + resData.footnavName + '</div>' + */
            '<div class="article-content">' + resData.footnavDesc + '</div>';

          elMain.html(html);
				} else {
					renderErrorMsg(elMain, 'Did not get relevant data');
				}
			}
		});
  </script>
</body>

</html>