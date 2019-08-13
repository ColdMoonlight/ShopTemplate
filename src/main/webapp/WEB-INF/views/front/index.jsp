<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
 %>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${APP_PATH }/static/common/swiper/swiper.min.css">
</head>

<body>

  <jsp:include page="pcheader.jsp"></jsp:include>






	<!-- main -->
  <div class="main">
  	<div class="container">
  		<!-- banner -->
	    <div id="banner" class="swiper-container">
	      <div class="swiper-wrapper"></div>
			  <div class="swiper-pagination"></div>
	    </div>
	    <!-- discount -->
	    <img class="discount" src="${APP_PATH }/static/pc/discount.jpg" />
	    <!-- countDownArea date -->
	    <div id="countdown-area"> </div>
	    <!-- product intro -->
	    <div class="hot-product-title">
	    	<h3>Hot Selling</h3>
	    </div>
	    <div id="hot-product" class="swiper-container">
			   <div class="swiper-wrapper"></div>
			   <div class="swiper-pagination"></div>
			</div>
	    <!-- product show -->
	    <div id="product-block"></div>
	    <!-- buyer show -->
	    <div id="buyer-show">
	    	<img src="${APP_PATH }/static/pc/buyer-show.jpg">
	    </div>
  	</div>
  </div>
  
  <jsp:include page="pcfooter.jsp"></jsp:include>
 	<script src="${APP_PATH }/static/js/countdown.min.js"></script>
	<script src="${APP_PATH }/static/common/swiper/swiper.min.js"></script>
  <script>
  	/* banner */
  	var slideData = [
       {
    	   title: '',
    	   link: 'javascript:;',
    	   path: '${APP_PATH }/static/pc/slide/pc_firsr01.jpg'
       },
       {
    	   title: '',
    	   link: 'javascript:;',
    	   path: '${APP_PATH }/static/pc/slide/pc_firsr02.jpg'
       },
       {
    	   title: '',
    	   link: 'javascript:;',
    	   path: '${APP_PATH }/static/pc/slide/pc_firsr03.jpg'
       },
       {
    	   title: '',
    	   link: 'javascript:;',
    	   path: '${APP_PATH }/static/pc/slide/pc_firsr04.jpg'
       },
       {
    	   title: '',
    	   link: 'javascript:;',
    	   path: '${APP_PATH }/static/pc/slide/pc_firsr05.jpg'
       }
  	];

  	function renderSlide(parent, data) {
  		var html = '';
  		for (var i=0, len=data.length; i<len; i+=1) {
  			html += '<div class="swiper-slide">' +
				  '<a href="' + data[i].link + '">' +
							'<img src="' + data[i].path + '" alt="">' +
					'</a>' +
				'</div>';
  		}
  		parent.html(html);
  	}
  	
  	renderSlide($('#banner .swiper-wrapper'), slideData);
  	new Swiper('#banner', {
      freeMode: true,
			pagination: {
				el: '.swiper-pagination',
				clickable: true
			},
			autoplay: {
		    delay: 3000,
		  },
  	});
  	
    function renderHotProduct(parent, data) {
      var html = '';
      for (var i=0, len=data.length; i < len; i += 1) {
				html += '<div class="swiper-slide">' +
	          '<a href="${APP_PATH}/MlbackProduct/topcProductDetailPage?productId=' + data[i].actshowproProid + '">' +
								'<img src="' + data[i].actshowproImgwapurl + '" alt="">' +
						'</a>' +
					'</div>';
			}
      parent.html(html);
    }
		/* xxxx */
    $.ajax({
      url: '${APP_PATH}/MlbackActShowPro/getMlbackActShowProListByActnum',
      data: "actshowproActnum=" + 1,
      type: "POST",
      success: function (data) {
        // console.log("mlbackActShowProList");
        if (data.code === 100) {
        	var resData = data.extend.mlbackActShowProList;
          // console.log(resData);
          renderHotProduct($('#hot-product .swiper-wrapper'), resData);
          new Swiper('#hot-product', {
        	  slidesPerView: 4,
            spaceBetween: 10,
            freeMode: true,
						pagination: {
							el: '.swiper-pagination',
							clickable: true
						}
					})
        } else {
          renderErrorMsg(prodcutBox, 'Not acquired product-related data');
        }
      }
    });
    
    /*---------------countDownArea--------*/
    function rednerCountDownAreaOne(parent, data) {
      var html = '';
      html += '<h3 class="title">'+ data.countdownTitle +'</h3>'+
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
          // console.log(data.extend.mlbackCountDownOne);
          if(data.extend.mlbackCountDownOne==null){
          	// console.log("mlbackCountDownOne为null");
          }else{
          	rednerCountDownAreaOne(countDownArea, data.extend.mlbackCountDownOne)
          }
        } else {
          renderErrorMsg(prodcutBox, 'Not acquired product-related data');
        }
      }
    });

    /*------rednerProduct----------*/
    function rednerProduct(data) {
      var html = '';
      for (var i = 0; i < data.length; i += 1) {
        html += '<div class="product-item col-lg-3">' +
          '<div class="product-img">' +
          '<a href="${APP_PATH}/MlbackProduct/topcProductDetailPage?productId=' + data[i].productId + '">' +
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
    	//把showareaImgurl字段		换成		showArea_imgpcurl	
    	return $('<div class="banner"></div>').html('<img src="' + data.showareaImgpcurl + '" alt=" + data.showareaName + ">');
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
 	          var bannerData = data.extend.mlbackShowAreaOne;
 	          // console.log(bannerData);
 	          // categoryName categoryImgurl;
 	          productBlock.append(renderBanner(bannerData))
 	        } else {
 	          renderErrorMsg(prodcutBox, 'Not acquired product-related data');
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
             var productData = data.extend.mlbackProductResList;
             // console.log(productData);
             productBlock.append(rednerProduct(productData));
           } else {
             renderErrorMsg(prodcutBox, 'Not acquired product-related data');
           }
         }
       });
    }
  </script>
</body>

</html>