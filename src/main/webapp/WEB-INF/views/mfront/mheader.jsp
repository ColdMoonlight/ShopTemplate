<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
   <link rel="stylesheet" href="${APP_PATH }/static/common/swiper/swiper.min.css">
  <link rel="stylesheet" href="${APP_PATH }/static/m/css/main.css">
  <script src="${APP_PATH }/static/common/swiper/swiper.min.js"></script>
  <script src="${APP_PATH }/static/m/js/zepto.min.js"></script>
  <script>
	  //if (navigator.language.toLocaleLowerCase().indexOf('zh') > -1) window.location.href = "https://www.baidu.com";
	  $.fn.prevAll = function(selector){
		  var prevEls = [];
		  var el = this[0];
		  if(!el) return $([]);
			while (el.previousElementSibling) {
		    var prev = el.previousElementSibling;
		    if (selector) {
		      if($(prev).is(selector)) prevEls.push(prev);
		    }
		    else prevEls.push(prev);
		    el = prev;
			}
	    return $(prevEls);
	 	};
	  
		$.fn.nextAll = function (selector) {
	    var nextEls = [];
	    var el = this[0];
	    if (!el) return $([]);
	    while (el.nextElementSibling) {
	      var next = el.nextElementSibling;
	      if (selector) {
	        if($(next).is(selector)) nextEls.push(next);
	      }
	      else nextEls.push(next);
	      el = next;
	    }
	    return $(nextEls);
	 };
  </script>
	  <!-- Global site tag (gtag.js) - Google Analytics 生产-->
	<!-- <script async src="https://www.googletagmanager.com/gtag/js?id=UA-145178467-1"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'UA-145178467-1');
	</script> -->
	
	<!-- Global site tag (gtag.js) - Google Analytics huashuohair-->
	<!-- <script async src="https://www.googletagmanager.com/gtag/js?id=UA-128377932-1"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'UA-128377932-1');
	</script> -->
	
	<!-- Global site tag (gtag.js) - Google Analytics 测试-->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-148326913-1"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'UA-148326913-1');
	</script>
	<style type="text/css">
		#addindex{z-index: 9999999999999 !important;}
	</style>
	
</head>

<body>
	<div id="addindex">
	    <p><span>x</span></p>
	    <img src="${APP_PATH }/static/m/img/index/add.gif" /></a>	
	</div>
   <div class="header bd-b">
	  <div class="img_show">
	  	<div class="alinktop"></div>	
	  	<div class="img_show_cont">
	  		<a href="javascript:;"></a>
	  	</div>
	  </div>
    <div class="container">
      <span id="menu" class="icon menu"></span>
      <span class="icon person"></span>
	  <a href="TEL:(501)7226336" class="icon myphone"></a>
      <a href="${APP_PATH}/index.html">
	      <div class="logo">
	        <img src="${APP_PATH }/static/m/img/index/logo.png" alt="">
	      </div>
      </a>
	   <span class="icon add_phone"></span>
      <span class="icon cart"><span class="num hide">0</span></span>
      <span class="icon search"></span>
    </div>
  </div>
  <div class="category">
    <i class="icon close close2"></i>
    <ul class="main-category category_ul bd-b">
	</ul>
  </div>
   <div class="serch_cont">
      <div class="serch_box">
        <span class="close_serch">X</span>
        <p class="serch_tit">What are you Looking for?</p>
        <div class="tt-col">
          <input type="text" class="tt-search-input" placeholder="Search Products..." />
          <button class="tt-btn-search" type="submit">Search</button>
        </div>
        <ul class="show_ul">
          <li>bob</li>
          <li>wigs</li>
          <li>bundle</li>
		  <li>613</li>
		  <li>Water</li>
        </ul>
      </div>
    </div>
<div class="kongbai"></div>
  <script>
  		var config = {
			// megalook
			"tidio": '//code.tidio.co/sjcpaqy3xxtkt935ucnyf2gxv1zuh9us.js',
			// megalookhair
			// "tidio": '//code.tidio.co/0rpdotjoqewxstfjahkd1ajtxrcp8phh.js'
			// huashuohair
			// "tidio": '//code.tidio.co/folzahtp5vdopiwathysfiyz75dk5vnm.js'
		}
	
		function addTidio() {
			var script = document.createElement('script');
			script.async = true;
			script.src = config.tidio;
			document.body.appendChild(script);
		}
	  $.ajax({
			url: '${APP_PATH}/MlbackSlide/getMlbackSlidewapListByArea',
			data: JSON.stringify({ "slideArea": 5 }),
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					var headerData = data.extend.mlbackSlideList;
					if (data.code == 100 && headerData.length > 0) {
						renderAlinkTop($('.alinktop'), headerData[0]);
					}
				}
			}
	  });
	  function renderAlinkTop(parent, data) {
			var html = '',
				slideIfinto_click = data.slideIfinto,
				slideIfproORcateORpage = data.slideIfproORcateORpage;
			if (slideIfinto_click == 0) {
				html += '<a href="javascript:;" style="cursor: default;">' +
						'<img src="' + data.slideWapimgurl + '" alt="">' +
					'</a>';
			} else {
				if (slideIfproORcateORpage == 0) {
					html += '<a href="${APP_PATH}/' + data.slideSeoname + '.html">' + '<img src="' + data.slideWapimgurl + '" alt="">' + '</a>';
				} else if (slideIfproORcateORpage == 1) {
					html += '<a href="${APP_PATH}/search/' + data.slideCateSeoname + '.html">' + '<img src="' + dat.slideWapimgurl + '" alt="">' + '</a>';
				} else if (slideIfproORcateORpage == 2) {
					html += '<a href="${APP_PATH}/' + data.slidePageSeoname + '.html">' + '<img src="' + data.slideWapimgurl + '" alt="">' + '</a>';
				}
			}
			parent.html(html);
	 }
	  $(function(){
		  var u = navigator.userAgent, app = navigator.appVersion;
		     var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //g
		     var isIOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		  if (isAndroid) {
		  			 $("#addindex").remove();
		  }
		  if (isIOS) {
			  $(".add_phone").show();
		  }
		  
		  
		  $(".add_phone").click(function(){
		  			 $("#addindex").show(); 
		  })
		  $("#addindex p span").click(function(){
		  	 $("#addindex").hide(); 
		  })
		  $(window).scroll(function(){
			   var scrollhead=$(window).scrollTop();
			   if(scrollhead>1000){
				   $(".header").addClass("headActive")
				   $(".show_ul").hide();
			   }else{
				   $(".header").removeClass("headActive")
			   }
		  });
		  
		  /*****************************/
	  	$(".fixed_link .close").click(function(){
	  		$(".fixed_link").addClass("active");
	  		$(".fixed_small").addClass("active");
	  	});
	  	$(".fixed_small").click(function(){
	  		$(".fixed_link").removeClass("active");
	  		$(this).removeClass("active");
	  	})
	  })
    var categoryActiveNum = 1;
    var iCart = $('.icon.cart');
    var iPerson = $('.icon.person');
	var mask = $('<div class="mask"></div>');
	var sysFlag = 0;

    function renderErrorMsg(parent, msg) {
      parent.html('<p class="without-data">' + msg + '</p>');
    }

    function renderSysMsg(msg, cls) {
    	var elBox = $('<div class="modal sys-box '+ cls +'" style="display: block;"></div>');
    	
    	var html = '<div class="sys-title">' +
    		'<span class="icon close"></span>' +
    	'</div>' +
    	'<div class="sys-body"><p>'+ msg +'</p></div>';
 
    	$(document.body).append(elBox.html(html));
    	$(document.body).append(mask)
    	
    	$('.sys-box .close').on('click', function() {
    		$('.sys-box').remove();
    		$('.mask').remove();
    		sysFlag = !sysFlag;
    	});
    }

    function toNumber(val) {
      var n = parseFloat(val);
      return isNaN(n) ? 0 : n
    }
/************************/
              var mainCategory =$(".main-category");
			  var categoryBox = $('.category');
			function renderMainCategory(parent,data1,data2) {
				var html="";
				for(var i=0;i<data1.length;i++){
					var categoryLableInt = data1[i].categoryLable;
					var	classimg = "";
					if(categoryLableInt == 0){
						classimg = "classimg0";
					}else if(categoryLableInt == 1){
						classimg = "classimg1";
					}else if(categoryLableInt == 2){
						classimg = "classimg2";
					}else if(categoryLableInt == 3){
						classimg = "classimg3";
					}else if(categoryLableInt == 4){
						classimg = "classimg4";
					}else if(categoryLableInt == 5){
						classimg = "classimg5";
					}
					var twonav = data1[i].categorySeo;
					if(twonav==""){
						html += '<li class="home-menu_list '+classimg+'"><a href="${APP_PATH}/search/nowig.html">'+ data1[i].categoryName +'</a><i class="gw-i"></i>'
					}else if(twonav=="home"){
						html += '<li class="home-menu_list '+classimg+'"><a href="${APP_PATH}/index.html">'+ data1[i].categoryName +'</a><i class="gw-i"></i>'
					}else if(twonav == "Activty"){
						html += '<li class="home-menu_list '+classimg+'"><a href="${APP_PATH}/Activty.html">'+ data1[i].categoryName +'</a><i class="gw-i"></i>';
					}else if(twonav == "CUSTOMER-VOICE"){
					    html += '<li class="home-menu_list '+classimg+'"><a href="javascript:void(0)">'+ data1[i].categoryName +'</a><i class="gw-i"></i>';		   
					}else{
						html += '<li class="home-menu_list '+classimg+'"><a href="${APP_PATH}/search/' + data1[i].categorySeo + '.html">'+ data1[i].categoryName +'</a><i class="gw-i"></i>'
					}


					// html += '<li class="home-menu_list '+classimg+'"><a href="${APP_PATH}/search/' + data1[i].categorySeo + '.html">'+ data1[i].categoryName +'</a><i class="gw-i"></i>'
					if(data2 && data2.length > 0 && data2[i] && data2[i].length > 0){
				    html += '<div class="menu_list-wap">';
					for(var j=0;j<data2[i].length;j++){
						  if(data2[i][j] && data2[i][j].length > 0){
							  html += '<dl>'; 
							   for(var k=0;k<data2[i][j].length;k++){
								   var categoryLableInt2 = data2[i][j][k].categoryLable;
								   var	classimg = "";
								   if(categoryLableInt2 == 0){
								   	classimg = "classimg0";
								   }else if(categoryLableInt2 == 1){
								   	classimg = "classimg1";
								   }else if(categoryLableInt2 == 2){
								   	classimg = "classimg2";
								   }else if(categoryLableInt2 == 3){
								   	classimg = "classimg3";
								   }else if(categoryLableInt2 == 4){
								   	classimg = "classimg4";
								   }else if(categoryLableInt2 == 5){
								   	classimg = "classimg5";
								   }
								   if(k==0){
									   var threenav =data2[i][j][k].categorySeo;
									   if(threenav==""){
									   			 html += '<dt class="'+classimg+'"><a href="${APP_PATH}/search/nowig.html">'+ data2[i][j][k].categoryName +'</a><i class="gw-i2"></i></dt>';     
									   }else if(threenav=="Customer-Videos"){
									   			html += '<dt class="dljc '+classimg+'"><a href="${APP_PATH}/MlbackVideoShowArea/toVideoListPage">'+ data2[i][j][k].categoryName +'</a><i class="gw-i2"></i></dt>';     
									   }else if(threenav=="Shop-By-Look"){
									   			 html += '<dt class="dljc '+classimg+'"><a href="${APP_PATH}/MlfrontReview/toReviewCustomer">'+ data2[i][j][k].categoryName +'</a><i class="gw-i2"></i></dt>';     
									   }else if(threenav=="Photo-Gallery"){
									   			html += '<dt class="dljc '+classimg+'"><a href="${APP_PATH}/MlfrontReview/toReviewInsPage">'+ data2[i][j][k].categoryName +'</a><i class="gw-i2"></i></dt>';     
									   }else{
									   		    html += '<dt class="'+classimg+'"><a href="${APP_PATH}/search/' + data2[i][j][k].categorySeo + '.html">'+ data2[i][j][k].categoryName +'</a><i class="gw-i2"></i></dt>';     
									   }
								   }else{
									   html += '<dd class="'+classimg+'"><a href="${APP_PATH}/search/' + data2[i][j][k].categorySeo + '.html">'+ data2[i][j][k].categoryName +'</a><i class="gw-i2"></i></dd>';      
								   }
								    // html += '<dd class="'+classimg+'"><a href="${APP_PATH}/search/' + data2[i][j][k].categorySeo + '.html">'+ data2[i][j][k].categoryName +'</a><i class="gw-i2"></i></dd>';     
								  
							   }
							     html +=  '</dl>';
						  }
					 }	
					 html += '</div>';	
					}
					 html += '	</li>';	
			    }
			 parent.html(html);
			 $(".category_ul li i.gw-i").click(function(){
			   var str = $(this).next('.menu_list-wap').css('display');
			   if(str == 'none'){
			   	$(this).next('.menu_list-wap').show(200);
				$(this).addClass("active");
			   }else{
			   	$(this).next('.menu_list-wap').hide(200);
				$(this).removeClass("active");
			   }
			   $(this).parent('.home-menu_list').siblings('.home-menu_list').find('.gw-i').removeClass('active').next('.menu_list-wap').hide(200);
			 })
			 $(".menu_list-wap dl dt .gw-i2").click(function(){
				 $(this).toggleClass('active');
				 if($(this).hasClass('active')){
					   $(this).parents("dl").addClass("submenu");
					   $(this).parents("dl").siblings("dl").removeClass("submenu"); 
					   $(this).parents("dl").siblings("dl").children("dd:first-child").find(".gw-i2").removeClass("active"); 
				 }else{
					  $(this).parents("dl").removeClass("submenu");
					  $(this).removeClass("active")
				 }
			 })
			}
		$.ajax({
			url: '${APP_PATH}/MlbackCategory/getCategorySuperMenu',
		    type: 'POST',
			dataType: 'JSON',
			contentType: 'application/json',
			success: function (data) {
				var JsonDate = JSON.parse(data);
				// console.log(JsonDate);
			  if(JsonDate.code === 100){
				  FirstList = JsonDate.extend.categoryFirstList;
				  SuperList = JsonDate.extend.mlbackCategorySuperList;
				  renderMainCategory(mainCategory,FirstList,SuperList)
			  }else{
				 renderErrorMsg(mainCategory, '未获取到目录相关的数据'); 
			  }
			}
		  })
    $('#menu').on('click', function () {
      categoryBox.show();
    });
    categoryBox.find('.close2').on('click', function () {
      categoryBox.hide();
    });
    // cart icon default number
    var cartText = iCart.find('.num');
    var num = window.localStorage.getItem('productNum') || 0;

    $.ajax({
      url: '${APP_PATH}/MlbackCart/getCartProductNumber',
      type: 'POST',
      success: function (data) {
        cartText.text(parseInt(data.extend.number) || 0);
      }
    })
    iCart.on('click', function () {
      window.location.href = "${APP_PATH }/myCart.html";
    })
    iPerson.on('click', function () {
      if ($(this).hasClass('active')) {
        window.location.href = "${APP_PATH }/MlfrontUser/toUserCenter";
      } else {
        window.location.href = "${APP_PATH }/MlfrontUser/toLoginRegisterPage";
      }
    })
    /* check user is login or not  */
    $.ajax({
      url: "${APP_PATH }/MlfrontUser/ifLogin",
      type: 'POST',
      success: function (data) {
        // console.log(data);
        var resData = data.extend;

        if (resData.ifLogin === 1) {
          if (!iPerson.hasClass('active')) iPerson.addClass('active')
        }

        if (resData.ifLogin === 0) {
          if (iPerson.hasClass('active')) iPerson.removeClass('active')
        }
      }
    })
    function toProductItem(id) {
      if (parseInt(id)) window.location.href = "${APP_PATH }/MlbackProduct/tomProductDetailPage?productId=" + id;
    }
	/************advlistpic**********************************/
	var banneradv=$("#adv_silder .swiper-wrapper")
	   function rednertop(parent, data) {
		  var html = '';
		  for (var i = 0; i < data.length; i += 1) {
				html += '<div class="swiper-slide">' +
					'<a href="javascript:;">' +
							'<img src="' + data[i].slideWapimgurl + '" alt="">' +
					'</a>' +
					'</div>';
		  }
		  parent.html(html);
		}
			$.ajax({
			 url: '${APP_PATH}/MlbackSlide/getMlbackSlidewapListByArea',
				data: JSON.stringify({
			   "slideArea": 4
			 }),
			 type: 'post',
			 dataType: 'JSON',
			 contentType: 'application/json',
			 success: function (data) {
					// console.log(data)/***1111111111data**/
					if (JSON.parse(data).code === 100) {
					  var resData = JSON.parse(data).extend.mlbackSlideList;
					  // console.log(resData)/****************首页广告******/
						rednertop(banneradv, resData);
						  new Swiper('#adv_silder', {
							// freeMode: true,
							// loop:true,
							// autoplay: {
							//     disableOnInteraction: false,
							//   },
							})
					  // console.log(resData);
					 // rednertop(bannerfirst,resData)
					} else {
					  renderErrorMsg(banneradv, 'No product-related data was obtained.');
					}
				  }
		});
	  var prodcutBox=$(".kongbai")
	  /************************/
	        $(".tt-btn-search").click(function(){
	           var seaProductName = $(this).parents(".tt-col").find("input.tt-search-input").val();
			   if(seaProductName==""){
				   alert("Please enter keywords;")
			   }else{
				    window.location.href = "${APP_PATH}/MlbackProduct/toSearchPage?seaProductName=" +seaProductName;
			   }
	        });
			  $(".show_ul li").click(function(){
			    var seaProductName =$(this).html();
				window.location.href = "${APP_PATH}/MlbackProduct/toSearchPage?seaProductName=" +seaProductName;
	           });
	    /**************搜索*************************/
	        $(".tt-search-input").focus(function(){
	          $(".serch_box").find("ul.show_ul").show()
	        });
	        $('body').delegate('.show_ul li','click',function(e){
	          var val = $(this).text();
	          $(".tt-col").find("input.tt-search-input").val(val);
	          $(".serch_box").find("ul.show_ul").hide()
	          e.stopPropagation();
	        });
	        $("body").on('click', '.search',function() {
	            $('.serch_cont').show()
	        })
	        $("body").on('click', '.close_serch',function() {
	            $('.serch_cont').hide()
	        })
  </script>
</body>

</html>