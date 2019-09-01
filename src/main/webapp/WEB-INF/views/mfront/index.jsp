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

  <jsp:include page="mheader.jsp"></jsp:include>

  <!-- main -->
  <div class="main">
    <!-- sale -->
    <div class="banner">
      <a href="#" alt="">
        <img src="${APP_PATH }/static/m/img/firstpage/first1.jpg" alt="">
      </a>
    </div>
    <!-- count date -->
    <div class="banner">
      <a href="#" alt="">
        <img src="${APP_PATH }/static/m/img/firstpage/first2.jpg" alt="">
      </a>
    </div>
    <!-- countDownArea date -->
    <div id="countdown-area"> </div>
    <!-- product intro -->
    <div id="hot-product"></div>
    <!-- product show -->
    <div id="product-block"></div>
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
        	var resData = data.extend.mlbackActShowProList;
          // console.log(resData);
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
  </script>
  <script src="//code.tidio.co/0rpdotjoqewxstfjahkd1ajtxrcp8phh.js"></script>
</body>

</html>