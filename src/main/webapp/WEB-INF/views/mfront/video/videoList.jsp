<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<title>Product List</title>
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
	<script>
  		fbq('track', 'ViewContent');
	</script>
	<!-- Facebook Pixel Code end -->
</head>

<body>

	<jsp:include page="../mheader.jsp"></jsp:include>

	<!-- main -->
	<!-- main -->
	<div class="main">
		<div class="banvideo">
			<img src="${APP_PATH }/static/pc/img/video/ban.jpg" >
		</div>
		<div class="tipsimg clearfix"><img src="${APP_PATH }/static/pc/img/video/wp2.jpg" ></div>
		<div class="banvideo">
			<img src="${APP_PATH }/static/pc/img/video/ty.jpg" >
		</div>
		<div class="container videobox  clearfix">
			<div class="tipsimg clearfix"><img src="${APP_PATH }/static/pc/img/video/wp3.jpg" ></div>
            <div class="videotop clearfix">
            	<ul>
            	      <li onclick="clickVideo(event)" videonum-id="157" data-src="https://www.youtube.com/embed/Wdbz0TRf6sI" data-img="${APP_PATH }/static/pc/img/video/v2.jpg"><img src="${APP_PATH }/static/pc/img/video/v1.jpg" ></li>
            		  <li onclick="clickVideo(event)" videonum-id="57" data-src="https://www.youtube.com/embed/NGrPAnTX5VA"><img src="${APP_PATH }/static/pc/img/video/v2.jpg" ></li>
            		  <li onclick="clickVideo(event)" videonum-id="193" data-src="https://www.youtube.com/embed/zD5dgYYDb08"><img src="${APP_PATH }/static/pc/img/video/v3.jpg" ></li>
            		  <li onclick="clickVideo(event)" videonum-id="56" data-src="https://www.youtube.com/embed/aiUDfS-h-Es"><img src="${APP_PATH }/static/pc/img/video/v4.jpg" ></li>
            		  <li onclick="clickVideo(event)" videonum-id="37" data-src="https://www.youtube.com/embed/TD4mGC5xNcU"><img src="${APP_PATH }/static/pc/img/video/v5.jpg" ></li>
            		  <li onclick="clickVideo(event)" videonum-id="25" data-src="https://www.youtube.com/embed/2cJvNAYpI6Y"><img src="${APP_PATH }/static/pc/img/video/v6.jpg" ></li>
            		  <li onclick="clickVideo(event)" videonum-id="41" data-src="https://www.youtube.com/embed/Wdbz0TRf6sI"><img src="${APP_PATH }/static/pc/img/video/v7.jpg" ></li>
            		  <li onclick="clickVideo(event)" videonum-id="26" data-src="https://www.youtube.com/embed/Wdbz0TRf6sI"><img src="${APP_PATH }/static/pc/img/video/v8.jpg" ></li>
            	</ul>
            </div>
			<div class="tipsimg clearfix"><img src="${APP_PATH }/static/pc/img/video/wp1.jpg" ></div>
			<div class="videocont clearfix">
				<ul class="clearfix">
			
				</ul>
			</div>
			<div class="tipsimg clearfix"><img src="${APP_PATH }/static/pc/img/video/wp4.jpg" ></div>
			<div class="four_hot container clearfix">
				<ul class="clearfix">
					<li><a href="${APP_PATH }/B-13x4-Straight-Body-Wave-Highlight-Lace-Wig.html"><img src="${APP_PATH }/static/pc/img/video/v2.jpg" ><p>sdfsdfsdfsdfsdfsdf</p></a></li>
					<li><a href="${APP_PATH }/B-360-Body-Wave-Wig.html"><img src="${APP_PATH }/static/pc/img/video/v2.jpg" ><p>sdfsdfsdfsdfsdfsdf</p></a></li>
					<li><a href="${APP_PATH }/B-360-Body-Wave-Wig.html"><img src="${APP_PATH }/static/pc/img/video/v2.jpg" ><p>sdfsdfsdfsdfsdfsdf</p></a></li>
					<li><a href="${APP_PATH }/B-13x4-Water-Wave-Wig.html"><img src="${APP_PATH }/static/pc/img/video/v2.jpg" ><p>sdfsdfsdfsdfsdfsdf</p></a></li>
				</ul>
				<dl class="clearfix">
					<dd><a href="${APP_PATH }/All-Color-4x4-Bob-Wig.html"><img src="${APP_PATH }/static/pc/img/video/v2.jpg" ></a></dd>
					<dd><a href="${APP_PATH }/13x4-Bob-Wig-1B-30-Color-Straight-Lace-Wig.html"><img src="${APP_PATH }/static/pc/img/video/v2.jpg" ></a></dd>
				</dl>
			</div>
		</div>
	</div>
	<jsp:include page="../mfooter.jsp"></jsp:include>

	<script>
		$(function(){
			// $img.attr('src', $img.attr('data-src')) //把data-src的值 赋值给src
			$(".video_enlarge_close").click(function(){
				$(".video_enlarge_corver").remove();
			})
			
			
		})
		/************/
		/***********************************/
			function videoProduct (data,videoLink){
			 var elBox = $('<div class="video_enlarge_corver" style="display: block;"></div>');
			 var html = '<div class="video_enlarge_wrap">'+
							   '<div class="video_enlarge_content">'+
									   '<iframe frameborder="0" allowfullscreen="1" allow="autoplay; encrypted-media" title="YouTube video player" width="100%" height="260" src="'+videoLink+'"></iframe>'+
										 '<div class="promotion_product_list clearfix">'+
										   '<a href="###" class="product-image"><img src="'+data.productMainimgurl+'" alt=""></a>'+
										   '<div class="rightcont">'+
										   '<h3 class="product-name"><a href="javascript:;">'+data.productName+'</a></h3>'+
											'<div class="price-box_promotion">'+	
												 '<div class="price-box">'+ 
														'<p class="old-price">'+
															'<span class="price-label">Regular Price:</span>'+
															'<span class="price">$' + (data.productOriginalprice ? data.productOriginalprice : 0) +
														'</p>'+
														'<p class="special-price span12">'+
															'<span class="price-label">SALE PRICE:</span>'+
															'<span class="price">$' + (data.productOriginalprice && data.productActoffoff ? (data.productOriginalprice * data.productActoffoff / 100).toFixed(2) : 0) + '</span>' +
															
														'</p>'+	
												 '</div>'+	
											'</div>'+	
											'<a class="video_link_buyBtn" href="${APP_PATH }/'+data.productSeo+'.html">Buy Now </a>'+
											'</div>'+
										 '</div>'+		
								'</div>'+	
								'<button class="video_enlarge_close">X</button>'		
						 '</div>'+						
			 $(document.body).append(elBox.html(html));
			 $(".video_enlarge_close").on("click",function(){
				 elBox.remove();
			 })
			}
		/***********************/	
			function clickVideo(e) {
				e.stopPropagation();
				var item  = $(e.target);
				var videoLink = item.parent().attr('data-src');
				var productId = item.parent().attr('videonum-id');
				data = {
					"productId":productId
				};
				console.log(productId)
				$.ajax({
						 url: '${APP_PATH}/MlbackProduct/getOneProductSimple',
				        data:data,
						 type: 'post',
						 success: function (data) {
							console.log(data)/***data**/
							   if (data.code === 100) {
												    var resData = data.extend. mlbackProductOne;
													videoProduct(resData,videoLink)
							   } 
							 }
				       });
				
			}
		/*******/
          var videocont= $(".videocont ul")
          function videoCont(parent, data,videoNum) {
            var html = '';
            for (var i=0, len=data.length; i < len; i += 1) {
				 var v_num =videoNum[i];
          		html += '<li>' +
          				'<a href="${APP_PATH }/MlbackVideo/toVideoAreaPage?videoArea='+data[i].videoshowareaId+'">' +
          				'<img src="'+data[i].videoshowareaWapimgurl+'">' +
          				'<p><em>'+data[i].videoshowareaName+'</em><span>('+v_num+')</span></p>'+
          				'</a>' +
          				'</li>';
          		}
            parent.html(html);
          }
          
          $.ajax({
          		 url: '${APP_PATH}/MlbackVideoShowArea/getMlbackVideoShowAreapcListByArea',
          			data: JSON.stringify({
          		   "videoshowareaAreanum": 2
          		 }),
          		 type: 'post',
          		 dataType: 'JSON',
          		 contentType: 'application/json',
          		 success: function (data) {
					 var JsonDate = JSON.parse(data);
          			console.log(JsonDate)/***data**/
          			   if (JsonDate.code === 100) {
          				 var resData = JsonDate.extend.mlbackVideoShowAreaList;
						  var videoNum = JsonDate.extend.videoNumByAreaListList;
          				 console.log(resData)
          				 videoCont(videocont,resData,videoNum)
          				 
          			   } 
          			 }
                 });

	</script>
	<!-- megalook-->
  	<script src="//code.tidio.co/sjcpaqy3xxtkt935ucnyf2gxv1zuh9us.js"></script>
	<!-- megalookhair 
  	<script src="//code.tidio.co/0rpdotjoqewxstfjahkd1ajtxrcp8phh.js"></script>-->
  	<!-- huashuohair -->
  	<!-- <script src="//code.tidio.co/folzahtp5vdopiwathysfiyz75dk5vnm.js"></script> -->
</body>

</html>