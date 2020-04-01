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
		<div class="container clearfix" style="margin: 0;">
			<div class="customer_widget_box clearfix">
				<h3>Customer Reviews</h3>
				<div class="review-left clearfix">
					<div class="left_allstatr">
						<div class="review-toal-num">Based on <span class="data">1868</span> Customer Reviews</div>
						<div class="reviews-data2">
							 <div class="stars-list2">
								 <i class="icon star2"></i>
								 <i class="icon star2"></i>
								 <i class="icon star2"></i>
								 <i class="icon star2"></i>
								 <i class="icon star2"></i>
							 </div>
						</div>
					</div>
					 <div class="reviews-statics">
						<div class="review-statics-item five">
							<div class="reviews-data2">
								 <div class="stars-list2">
									 <i class="icon star2"></i>
									 <i class="icon star2"></i>
									 <i class="icon star2"></i>
									 <i class="icon star2"></i>
									 <i class="icon star2"></i>
								 </div>
							</div>
						<div class="progress"><div class="progress-inner" style="width: 93.00%"></div></div><div class="data">93.00%</div></div>
						<div class="review-statics-item four">
							<div class="reviews-data2">
								 <div class="stars-list2">
									 <i class="icon star2"></i>
									 <i class="icon star2"></i>
									 <i class="icon star2"></i>
									 <i class="icon star2"></i>
								 </div>
							</div>
						<div class="progress"><div class="progress-inner" style="width: 30.00%"></div></div><div class="data">6.00%</div></div>
						<div class="review-statics-item three">
							<div class="reviews-data2">
								 <div class="stars-list2">
									 <i class="icon star2"></i>
									 <i class="icon star2"></i>
									 <i class="icon star2"></i>
								 </div>
							</div>
						<div class="progress"><div class="progress-inner" style="width: 5.00%"></div></div><div class="data">1.00%</div></div>
						<div class="review-statics-item two">
							<div class="reviews-data2">
								 <div class="stars-list2">
									 <i class="icon star2"></i>
									 <i class="icon star2"></i>
								 </div>
							</div>
						<div class="progress"><div class="progress-inner" style="width: 0.00%"></div></div><div class="data">0.00%</div></div>
						<div class="review-statics-item one">
							<div class="reviews-data2">
								 <div class="stars-list2">
									 <i class="icon star2"></i>
								 </div>
							</div>
						<div class="progress"><div class="progress-inner" style="width: 0.00%"></div></div><div class="data">0.00%</div></div>
					 </div>
					</div>
					<h4>Product Reviews(659)</h4>
			</div>
            <div class="list_pro_cont" id="waterfull">
            	<ul>
            		
            	</ul>
            </div>
			
			
		</div>
	</div>

	<jsp:include page="../mfooter.jsp"></jsp:include>
	<script src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${APP_PATH }/static/js/masonry.min.js"></script>
	<script type="text/javascript">
        $(function(){
			
			var masonrycont =$("#waterfull ul")
			$.ajax({
				url: "${APP_PATH}/MlfrontReview/selectReviewListFrom",
					data:{
					  "reviewFrom": 1
					},
					type: "POST",
					success: function (result) {
					  if (result.code == 100) {
							console.log(result);
							  resData = result.extend.mlfrontReviewList;
							  resDataimg = result.extend.mlfrontReviewImgList;
							  masonryHtml(masonrycont,resData,resDataimg)
						  }
					}
			  });
			function masonryHtml(parent, data1, data2){
				var html = '';
				for(var i=0;i<data1.length;i++){
					var reviewProstarnum=data1[i].reviewProstarnum;
					var statrnum = "";
					  if(reviewProstarnum==1){
						   statrnum ="statrnum1"
					  }else if(reviewProstarnum==2){
						   statrnum ="statrnum2"
					  }else if(reviewProstarnum==3){
						   statrnum ="statrnum3"
					  }else if(reviewProstarnum==4){
						   statrnum ="statrnum4"
					  }else if(reviewProstarnum==5){
						   statrnum ="statrnum5"
					  }
					
					html += '<li class="item">';
					// var data11 =data2[i];
					 // console.log(data11);
					for(var j=0;j<data2[i].length;j++){
						// var data11 =data2[i][j].reviewimgUrl;
						 // console.log(data11);
					    html +=	'<img src="'+data2[i][j].reviewimgUrl+'" />';
					 }	
					 html +=  '<p class="statrnum '+statrnum+'"><span></span</p>'+
					          '<p class="p_contpl">'+ data1[i].reviewDetailstr +'</p>'+
							  '<p class="time_namep2">'+
							  '<span class="time">by <i class="sp1"> '+ data1[i].reviewUname +'</i></span>'+
							  '<span class="time">'+ data1[i].reviewCreatetime +'</span>'+
							  '</p>'+
							  '<p class="btnlink">'+
							  '<a href="${APP_PATH }/MlbackProduct/tomProductDetailPage?productId='+data1[i].reviewPid+'" class="btn_shop">Shop This Look</a>'+
							  '</p>';
					 html += '</li>';
				}
				  parent.html(html);
				/*瀑布流开始*/
				var $container = $('.list_pro_cont ul');
				/*判断瀑布流最大布局宽度，最大为1280*/
				$container.imagesLoaded( function(){
					$container.masonry({
							itemSelector : '.item',
							isFitWidth: true,//是否根据浏览器窗口大小自动适应默认false
							isRTL:false,//设置布局的排列方式，即：定位砖块时，是从左向右排列还是从右向左排列。默认值为false，即从左向右
							isResizable: true,//是否自动布局默认true
					});
				});
			}

		})

		addTidio();
	</script>
</body>

</html>