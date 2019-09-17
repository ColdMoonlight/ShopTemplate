<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="UTF-8">
  <title>MegaLook Hair</title>
  <% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
  <meta name="viewport"
    content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, viewport-fit=cover">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <!--  禁用telphone -->
   <link rel="stylesheet" href="${APP_PATH }/static/common/swiper/swiper.min.css">
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
	<!-- End Facebook Pixel Code -->
	
	<script>
  		fbq('track', 'ViewContent');
	</script>
	<!-- Facebook Pixel Code end -->
</head>

<body>

  <jsp:include page="mheader.jsp"></jsp:include>
  
	<script src="${APP_PATH }/static/common/swiper/swiper.min.js"></script>
  <!-- main -->
  <div class="main">
    <!-- sale -->
    <div class="bannerfirst">
        <div id="ban_silder">
        	<div class="swiper-wrapper">
        		
        	</div>
			<div class="swiper-pagination"></div>
        </div>
    </div>
    <!-- count date -->
    <div class="banner">
		<div class="coupon_cont2">
			
		</div>
    </div>
    <!-- countDownArea date -->
    <div id="countdown-area"> </div>
    <!-- product intro -->
    <div id="hot-product"></div>
    <!-- product show -->
    <div id="product-block"></div>
  </div>
   <div class="mask maskindex" style="display: none;"></div>
   <div class="go_re" style="display: none;">
	   <span class="close">×</span> 
	   <a href="${APP_PATH }/MlfrontUser/toLoginRegisterPage"><img src="${APP_PATH }/static/pc/img/zctc.jpg"></a>
   </div>
  <jsp:include page="mfooter.jsp"></jsp:include>
  <script src="${APP_PATH }/static/js/countdown.min.js"></script>
  <script>
    function rednerHotProduct(parent, data) {
      var html = '';
      for (var i = 0; i < data.length; i += 1) {
        html += '<div class="banner">' +
        '<a href="${APP_PATH}/' + data[i].actshowproSeoname + '.html">' +
							'<img src="' + data[i].actshowproImgwapurl + '" alt="">' +
        '</a>' +
        '</div>';
      }
      parent.html(html);
    }
    $.ajax({
      url: '${APP_PATH}/MlbackActShowPro/getMlbackActShowProListByActnum',
      data: "actshowproActnum=" + 1,
      type: "POST",
      success: function (data) {
        if (data.code === 100) {
			console.log(data)
        	var resData = data.extend.mlbackActShowProList;
            console.log(resData);/*banban*/
          rednerHotProduct($('#hot-product'), resData)
        } else {
          renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
        }
      }
    });
    
    /*---------------countDownArea--------*/
    function rednerCountDownAreaOne(parent, data) {
      var html = '';
      html += '<div class="title">'+data.countdownTitle+'</div>'+
        '<div class="body"></div>'+
        '<div class="desc">'+ data.countdownDescription +'</div>';

      parent.html(html);
      var countdown = new countDown('#countdown-area .body', {
	      start: {
	        time: data.countdownStarttime
	      },
	      end: {
	        time: data.countdownEndtime
	      },
	      format: 'DD : HH : MM : SS',
	      themeClass: 'dark',
	      interval: 1000,
	      stateText: {
    	    before: 'Start of distance activity:',
    	    progress: 'End of distance activity:',
    	    after: 'The activity is over:',
	    	}
	    });

      if (countdown && countdown.state === 'after') {
    	  $('#countdown-area').addClass('hide');
      }
    }
    var countDownArea = $('#countdown-area');
    $.ajax({
      url: '${APP_PATH}/MlbackCountDown/getOneMlbackCountDownDetail',
      data: "countdownId=" + 1,
      type: "POST",
      success: function (data) {
        // console.log("mlbackCountDownOne");
        if (data.code === 100) {
        	var resData = data.extend.mlbackCountDownOne;
          // console.log(resData);
          if(data.extend.mlbackCountDownOne==null){
          	// console.log("mlbackCountDownOne为null");
          }else{
          	rednerCountDownAreaOne(countDownArea,resData)
          }
        } else {
          renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
        }
      }
    });

    /*------rednerProduct----------*/
    function rednerProduct(data) {
      var html = '';
      for (var i = 0; i < data.length; i += 1) {
		  var productactoffif = data[i].productActoffIf;
		  // console.log(productactoffif)
		  var productactoffid  =  data[i].productActoffid;
		   // console.log(productactoffid)  
		  var cp_icon = "";
		  var showspan = "";
		  if(productactoffif == 1){
		  			  if(productactoffid==1){
		  				   showspan ="showactive1"
		  			  }else if(productactoffid==2){
		  				   showspan ="showactive2"
		  			  }else if(productactoffid==3){
		  				   showspan ="showactive3"
		  			  }else if(productactoffid==4){
		  				   showspan ="showactive4"
		  			  }
		  			  
		  }else{
		  			   showspan ="hideactive"
		  }
		  
        html += '<div class="product-item">' +
		 '<span class="hui_icon '+showspan+'"></span>'+
          '<div class="product-img">' +
          '<a href="${APP_PATH}/' + data[i].productSeo + '.html">' +
          '<img src="' + data[i].productMainimgurl + '" alt="">' +
          '</a>' +
          '</div>' +
          '<div class="product-desc">' +
          '<div class="product-title">' + data[i].productName + '</div>' +
          '<div class="product-type"></div>' +
          '<div class="product-data">' +
          '<span class="pay-num">' + (data[i].productHavesalenum ? data[i].productHavesalenum : 0) + ' Payment</span>' +
          '<span class="review-num">' + (data[i].productReviewnum ? data[i].productReviewnum : 0) +
          ' Review(s)</span>' +
          '</div>' +
          '<div class="product-price">' +
          '<span class="product-now-price">$' + (data[i].productOriginalprice && data[i].productActoffoff ? (data[i]
            .productOriginalprice * data[i].productActoffoff / 100).toFixed(2) : 0) + '</span>' +
          '<span class="product-define-price">$' + (data[i].productOriginalprice ? data[i].productOriginalprice : 0) +
          '</span>' +
          '<span class="product-to-cart" data-id="' + data[i].productId + '"><i class="icon cart2"></i></span>' +
          '</div>' +
          '</div>' +
          '</div>';
      }

      return $('<div class="product-list"></div>').html(html);
    }
    /*------rednerBanner----------*/
    function renderBanner(data) {
    	return $('<div class="banner"></div>').html('<img src="' + data.showareaImgurl + '" alt=" + data.showareaName + ">');
    }
    
		var blockList = [
 		  { banner: 1, product: 1},
 		  { banner: 2, product: 2},
 		  { banner: 3, product: 3},
 		  { banner: 4, product: 4}
 		];
    /*---------------product block--------*/
    for (var i=0, len=blockList.length; i<len; i+=1) {
    	var productBlock = $('#product-block');
    	// banner
   	 	$.ajax({
 	      url: '${APP_PATH}/MlbackShowArea/getMlbackShowAreaOne',
 	      data: "showareaNumth=" + blockList[i].banner,
 	      type: "POST",
 	      async: false,
 	      success: function (data) {
 	        if (data.code === 100) {
 	          var resData = data.extend.mlbackShowAreaOne;
 	          // console.log(resData);
 	          productBlock.append(renderBanner(resData))
 	        } else {
 	          renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
 	        }
 	      }
 	    });
    	// product
   	 	$.ajax({
	         url: '${APP_PATH}/MlbackProduct/getMlbackProductLableList',
	         data: "productLable=" + blockList[i].product,
	         type: "POST",
	         async: false,
	         success: function (data) {
	           // console.log(productList1)
	           if (data.code === 100) {
	             var resData = data.extend.mlbackProductResList;
	             // console.log(resData);
	             productBlock.append(rednerProduct(resData));
	           } else {
	             renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
	           }
	         }
	       });
    }
    /*************优惠券***************/
	 // 	var couponCont = $(".coupon_cont");
	 //   function renderCoupon (parent, data){
		// 	var htmlcoup = '';
		// 	for(var i=0, len=data.length; i < len; i += 1){
		// 		htmlcoup += '<div class="coup_img">' +
		// 		'<img src="' + data[i].couponImgwapurl + '">' +
		// 		'</div>';
		// 	}
		// couponCont.html(htmlcoup);
		// }
  //   $.ajax({
		// url: '${APP_PATH}/MlbackCoupon/getMlbackCouponShowByAreaNum',
		// data: JSON.stringify({
		// 	"couponAreaNum": 2
		// }),
		// type: 'post',
		// dataType: 'JSON',
		// contentType: 'application/json',
		// success: function (data) {
		// 	 console.log(data)/***data**/
  //          if (JSON.parse(data).code === 100) {
  //            var resData = JSON.parse(data).extend.mlbackCouponResList;;
		// 	 console.log(resData);
		// 	  renderCoupon(couponCont,resData)
  //          } else {
  //            renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
  //          }
  //        }
  //      });
/********弹层注册********************************/
	   // setTimeout(function() {
		  //  renderSysMsg('');
		  //  
	   // }, 1000);
                    var go_re = $(".go_re")
					function get_cookie(Name) {
						var search = Name + "=";
						var returnvalue = "";
						if (document.cookie.length > 0) {
							offset = document.cookie.indexOf(search);
							if (offset != -1) {
								// if cookie exists
								offset += search.length;
								// set index of beginning of value
								end = document.cookie.indexOf(";", offset);
								// set index of end of cookie value
								if (end == -1)
									end = document.cookie.length;
								returnvalue=decodeURI(document.cookie.substring(offset, end))
							}
						}
						return returnvalue;
					}
					setTimeout(function(){
						if (get_cookie("popped")==""){
							document.cookie="popped=yes";
							go_re.show();
							$(".mask").show();
						}
					},2000);
					$(".close").click(function(){
						go_re.hide();
						$(".mask").hide();
					}) 
						
/***************首页banner***********************************************/		
              var bannerfirst=$("#ban_silder .swiper-wrapper")
			    function rednertop(parent, data) {
					  var html = '';
					  for (var i = 0; i < data.length; i += 1) {
						  var slideIfinto_click = data[i].slideIfinto;
						  // console.log(slideIfinto_click);
						  if(slideIfinto_click==0){
						  	html += '<div class="swiper-slide">' +
						  		  '<a href="javascript:;">' +
						  					'<img src="' + data[i].slideWapimgurl + '" alt="">' +
						  			'</a>' +
						  		'</div>';
						  }else{
						  	html += '<div class="swiper-slide">' +
						  		  '<a href="${APP_PATH}/' + data[i].slideSeoname + '.html">' +
						  					'<img src="' + data[i].slideWapimgurl + '" alt="">' +
						  			'</a>' +
						  		'</div>';
						  }
							
					  }
					  parent.html(html);
					}
			 $.ajax({
				 url: '${APP_PATH}/MlbackSlide/getMlbackSlidewapListByArea',
					data: JSON.stringify({
				   "slideArea": 1
				 }),
				 type: 'post',
				 dataType: 'JSON',
				 contentType: 'application/json',
				 success: function (data) {
						console.log(data)/***data**/
						if (JSON.parse(data).code === 100) {
						  var resData = JSON.parse(data).extend.mlbackSlideList;
							rednertop(bannerfirst, resData);
							  new Swiper('#ban_silder', {
								freeMode: true,
								autoplay: {delay: 3000,},
									pagination: {
										el: '.swiper-pagination',
										clickable: true
									}
				                })
						  // console.log(resData);
				         // rednertop(bannerfirst,resData)
						} else {
						  renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
						}
					  }
		        });
/*******优惠券*************************/			
	                  var bannercoupon=$(".coupon_cont2")
					function rednerCoupon(parent, data) {
						  var html = '';
						  for (var i = 0; i < data.length; i += 1) {
							   var slideIfinto_click = data[i].slideIfinto;
							  if(slideIfinto_click==0){
								  html += '<div class="banner">' +
								  '<a href="javascript:;">' +
								  '<img src="' + data[i].slideWapimgurl + '" alt="">' +
								  '</a>' +
								  '</div>';
							  }else{
								  html += '<div class="banner">' +
								  '<a href="${APP_PATH}/' + data[i].slideSeoname + '.html">' +
								  '<img src="' + data[i].slideWapimgurl + '" alt="">' +
								  '</a>' +
								  '</div>';
							  }
							  
							
						  }
						  parent.html(html);
						}
				 $.ajax({
					 url: '${APP_PATH}/MlbackSlide/getMlbackSlidewapListByArea',
						data: JSON.stringify({
					   "slideArea": 2
					 }),
					 type: 'post',
					 dataType: 'JSON',
					 contentType: 'application/json',
					 success: function (data) {
							console.log(data)/***data**/
							if (JSON.parse(data).code === 100) {
							  var resData = JSON.parse(data).extend.mlbackSlideList;;
							  console.log(resData);
					         rednerCoupon(bannercoupon,resData)
							} else {
							  renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
							}
						  }
	   });			
					
	   
  </script>
  <script src="//code.tidio.co/0rpdotjoqewxstfjahkd1ajtxrcp8phh.js"></script>
</body>

</html>