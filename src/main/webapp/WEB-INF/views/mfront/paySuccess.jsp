<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="UTF-8">
  <title>Pay Success</title>
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
	
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-128377932-2"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	  gtag('config', 'UA-128377932-2');
	</script>
</head>

<body>

  <jsp:include page="mheader.jsp"></jsp:include>

  <!-- main -->
  <div class="main">
  
  	<input type="hidden" class="order-id" name="orderId">

    <!-- purechase step -->
    <img class="purechase-step" src="${APP_PATH }/static/m/img/other/step_pay.jpg">

    <div class="box-none">
      <img src="${APP_PATH}/static/m/img/other/pay-success.png" alt="">
      <p>Successful Payment Of Orders</p>

      <a href="${APP_PATH}/index/isMobileOrPc" class="btn btn-gray">Check The Order Details</a>
      <a href="${APP_PATH}/index.html" class="btn btn-pink">ConTine Shopping</a>
    </div>
  </div>

  <jsp:include page="mfooter.jsp"></jsp:include>
  <script type="text/javascript">
	$.ajax({
		url: '${APP_PATH }/MlfrontPayInfo/getsuccessPayinfo',
		type: "POST",
		success: function (data) {
			// console.log(data)
			var mlfrontOrderOne = data.extend.mlfrontOrderOne;
			var payinfoId = data.extend.payinfoId;
			var orderMoney = mlfrontOrderOne.orderMoney;
			// console.log(mlfrontOrderOne);
			console.log("orderMoney:"+orderMoney);
			getPayInfo(payinfoId,orderMoney);
			
			// console.log("fbq111111");
			//fbq('track', 'Purchase', {value:orderMoney,currency:'USD'});//追踪'购买'事件		facebook广告插件可以注释掉，但不要删除
			// console.log("fbq222222");
			$(".order-id").val(mlfrontOrderOne.orderId);
			
		}
	});
	
	function getPayInfo(payinfoId,orderMoney){
		var reqData = {
				"payinfoId": payinfoId
			};
		console.log("orderMoney:"+orderMoney);
		$.ajax({
			url: "${APP_PATH}/MlfrontPayInfo/getOneMlfrontPayInfoDetail",
			data: reqData,
			type: "POST",
			success: function (result) {
				if (result.code == 100) {
					var resData = result.extend;
					var resDataOrderItemList = result.extend.mlfrontOrderItemList;
					// console.log(mlfrontOrderItemList);
					//拼接所需参数:content_ids
					stridsContent=toFbidsPurchase(resDataOrderItemList);
					console.log("stridsContent:"+stridsContent);
					//拼接所需参数:contents
					strContent=toFbPurchase(resDataOrderItemList);
					console.log("strContent:"+strContent);
					//fbq('track', 'Purchase');//追踪'购买'事件		facebook广告插件可以注释掉，但不要删除
					fbq('track', 'Purchase', {
						  content_ids: [stridsContent],
						  //contents: [strContent],
						  content_type: 'product',
						  value: orderMoney,
						  currency: 'USD'
						});
				} else {
					//alert("联系管理员");
				}
			}
		});
	}
	
	function toFbidsPurchase(resDataOrderItemList){
		var infoStrlids = '';
		var infoRelids = '';
		for(var i=0;i<resDataOrderItemList.length;i++){
			infoStrlids=infoStrlids+resDataOrderItemList[i].orderitemPid+',';
		}
		infoRelids=infoStrlids.substr(0,infoStrlids.length-1);
		console.log("infoRelids:"+infoRelids);
		return infoRelids;
	}
	
	function toFbPurchase(resDataOrderItemList){
		var infoRel = '';
		var infoStr = '';
		for(var i=0;i<resDataOrderItemList.length;i++){
			var infoStrOne = '';
			var pid = resDataOrderItemList[i].orderitemPid;
			var pnum = resDataOrderItemList[i].orderitemPskuNumber;
			var price = resDataOrderItemList[i].orderitemPskuReamoney;
			infoStrOne = infoStrOne+'{'+'id:'+pid+','+'quantity:'+pnum+','+'item_price:'+price+'}';
			infoStr=infoStr+infoStrOne+',';
		}
		infoRel=infoStr.substr(0,infoStr.length-1);
		console.log("infoRel:"+infoRel);
		return infoRel;
	}
</script>

<script>

var orderId = $(".order-id").val();
console.log("window.dataLayer = window.dataLayer");
	window.dataLayer = window.dataLayer || [];
	dataLayer.push({
	   'transactionId': orderId ,
	   'transactionAffiliation': 'megalookhair',
	   'transactionTotal': 500,
	   'transactionTax': 0,
	   'transactionShipping': 18,
	   'transactionProducts': [{
	       'sku': 'DD44',
	       'name': 'T-Shirt',
	       'category': 'Apparel',
	       'price': 11.99,
	       'quantity': 1
	   },{
	       'sku': 'AA1243544',
	       'name': 'Socks',
	       'category': 'Apparel',
	       'price': 9.99,
	       'quantity': 2
	   }]
	});
</script>

</body>

</html>