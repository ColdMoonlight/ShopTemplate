<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>MEGALOOK</title>
	<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
	<link rel="shortcut icon" href="${APP_PATH }/static/pc/img/favicon1.ico" />
	<link rel="stylesheet" href="${APP_PATH }/static/pc/css/theme.css">
	<link rel="stylesheet" href="${APP_PATH }/static/pc/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/static/pc/css/new.css">
	
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
	
	<!-- google-site-verification 网站地图所需 上线时打开 
	<meta name="google-site-verification" content="S_nwEF7Oc2MiSJHmAoJ8M8QA8vIA9QxyUrFDSTq1-YI" />-->
	
</head>
<body>
	<header class="desktop-header">
		<!-- tt-desktop-header -->
			<div class="alinktop"></div>
		   	<div class="logo_search">
			   	<div class="container head_box">
					<!-- search logo cart -->
					<div class="header_holder">
						<div class="logo">
							<a href="${APP_PATH}/index.html">
								 <img src="${APP_PATH }/static/m/img/index/logo.png" alt="">
							</a>
						</div>
						 <div class="serch_cont">
							<div class="serch_box">
								<input type="text" class="tt-search-input" placeholder="Search Products..." />
								<button class="tt-btn-search" type="submit"></button>
							</div>
							<ul class="show_ul">
								<li>bob</li>
								<li>wigs</li>
								<li>bundle</li>
								<li>613</li>
								<li>Water</li>
							</ul>
						 </div>
						 <div class="loginbox">
							<div class="tt-desctop-parent-account tt-parent-box person">
								<div class="tt-account tt-dropdown-obj">
									<button class="tt-dropdown-toggle" data-tooltip="My Account" data-tposition="bottom">
										<i class="icon-f-94"></i>
									</button>
								</div>
							</div>
						 </div>
					</div>
				</div>
		   </div>
			<div class="container head_box clearfix">
			  <div class="nav_boxfirst clearfix">
				  <ul class="navul clearfix">
				  </ul>
			  </div>
			</div>
			 <div class="navbar_itemContent-background"></div>
		
	</header>
	<div class="kongbai"></div>

	<div id="cart_icon" class="cart_icon liman"><a href="###"></a><span class="cart_num">0</span></div>
	<div class="go_top liman"><a href="###"></a></div>
	
	<script src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<script src="${APP_PATH }/static/pc/js/jquery.fly.min.js"></script>
	<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
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
			url: '${APP_PATH}/MlbackSlide/getMlbackSlidepcListByArea',
			data: JSON.stringify({ "slideArea": 5 }),
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				var headerData = data.extend.mlbackSlideList;
				if (data.code == 100 && headerData.length > 0) {
					renderAlinkTop($('.alinktop'), headerData[0]);
				}
			}
	  });
	  function renderAlinkTop(parent, data) {
			var html = '',
				slideIfinto_click = data.slideIfinto,
				slideIfproORcateORpage = data.slideIfproORcateORpage;
			if (slideIfinto_click == 0) {
				html += '<a href="javascript:;" style="cursor: default;">' +
						'<img src="' + data.slidePcimgurl + '" alt="">' +
					'</a>';
			} else {
				if (slideIfproORcateORpage == 0) {
					html += '<a href="${APP_PATH}/' + data.slideSeoname + '.html">' + '<img src="' + data.slidePcimgurl + '" alt="">' + '</a>';
				} else if (slideIfproORcateORpage == 1) {
					html += '<a href="${APP_PATH}/search/' + data.slideCateSeoname + '.html">' + '<img src="' + dat.slidePcimgurl + '" alt="">' + '</a>';
				} else if (slideIfproORcateORpage == 2) {
					html += '<a href="${APP_PATH}/' + data.slidePageSeoname + '.html">' + '<img src="' + data.slidePcpimgurl + '" alt="">' + '</a>';
				}
			}
			parent.html(html);
	 }
       $(function(){
		   	$(".img_show_cont a").click(function(){
			 	$(".img_show").animate({ 
				    height: "0", 
				}, 100);
		   	});
		   	$(".go_top").click(function(){
				$("body,html").animate({scrollTop:0},500);  
			   return false;
		    });
			$(window).scroll(function(){
				var scrollhead=$(window).scrollTop();
				if(scrollhead>5){
					$(".desktop-header").addClass("headActive");
				   	$(".show_ul").hide();
				} else {
					$(".desktop-header").removeClass("headActive")
				}
				/**************************/
				var winS=$(this).scrollTop();
				if(winS>0){
					$(".go_top").show();
				} else {
					$(".go_top").hide();
				}
			   });
			   /***************************************/
		   	});
			var categoryActiveNum = 1;
			var iCart = $('.cart_icon');
			var iPerson = $('.person');
			var mask = $('<div class="mask"></div>');
			var sysFlag = 0;
			function renderErrorMsg(parent, msg) {
				  parent.html('<p class="without-data">' + msg + '</p>');
			}
		    function renderSysMsg(msg) {
		    	var elBox = $('<div class="modal sys-box pc_tanc" style="display: block;"></div>');
		    	
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
			/**********************************/
			var nav_boxfirst =$(".navul")
			var prodcutBox=$(".kongbai")
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
						html += '<li class="home-menu_list '+classimg+'"><a href="${APP_PATH}/search/nowig.html">'+ data1[i].categoryName +'</a>';
					}else if(twonav == "home"){
						html += '<li class="home-menu_list '+classimg+'"><a href="${APP_PATH}/index.html">'+ data1[i].categoryName +'</a>';
					}else if(twonav == "Activty"){
						html += '<li class="home-menu_list '+classimg+'"><a href="${APP_PATH}/Activty.html">'+ data1[i].categoryName +'</a>';
					}else if(twonav == "CUSTOMER-VOICE"){
					    html += '<li class="home-menu_list '+classimg+'"><a href="javascript:void(0)">'+ data1[i].categoryName +'</a>';		   
					}else{
						html += '<li class="home-menu_list '+classimg+'"><a href="${APP_PATH}/search/' + data1[i].categorySeo + '.html">'+ data1[i].categoryName +'</a>';
					}

					// html += '<li class="home-menu_list '+classimg+'"><a href="${APP_PATH}/search/' + data1[i].categorySeo + '.html">'+ data1[i].categoryName +'</a>'
					if(data2 && data2.length > 0 && data2[i] && data2[i].length > 0){
				    html += '<div class="menu_list-wap">';
					html += '<div class="home-menu_list_wrap_inner">';
					html += '<div class="home-menu_list--left_left"></div>';
					var html2 = '',
						isWrap = true;
					// html += '<div class="home_left_middle">';
					
					for(var j=0;j<data2[i].length;j++){
						  if(data2[i][j] && data2[i][j].length > 0){
							  html2 += '<dl>';
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
												html2 += '<dt class="'+classimg+'"><a href="${APP_PATH}/search/nowig.html">'+ data2[i][j][k].categoryName +'</a></dd>';     
										}else if(threenav=="Customer-Videos"){
												html2 += '<dt class="dljc '+classimg+'"><a href="${APP_PATH}/MlbackVideoShowArea/toVideoListPage">'+ data2[i][j][k].categoryName +'</a></dt>';     
										}else if(threenav=="Shop-By-Look"){
												html2 += '<dt class="dljc '+classimg+'"><a href="${APP_PATH}/MlfrontReview/toReviewCustomer">'+ data2[i][j][k].categoryName +'</a></dt>';     
										}else if(threenav=="Photo-Gallery"){
											     html2 += '<dt class="dljc '+classimg+'"><a href="${APP_PATH}/MlfrontReview/toReviewInsPage">'+ data2[i][j][k].categoryName +'</a></dt>';     
										}else{
												html2 += '<dt class="'+classimg+'"><a href="${APP_PATH}/search/' + data2[i][j][k].categorySeo + '.html">'+ data2[i][j][k].categoryName +'</a></dt>';     
										}
								   }else{
									  isWrap = false;
									  html2 += '<dd class="'+classimg+'"><a href="${APP_PATH}/search/' + data2[i][j][k].categorySeo + '.html">'+ data2[i][j][k].categoryName +'</a></dd>';    
								   }
							   }
							  html2 += '</dl>';	  
						  }
					 }
					if (isWrap) {						
						html += '<div class="home_left_middle nowrap">' + html2;
					} else {
						html += '<div class="home_left_middle">' + html2;
					}
					  html += '</div>';
					  html += '<div class="home-menu_list--left_right"></div>';
					  html += '</div>';	
					 html += '</div>';	
					}
					 html += '	</li>';	
			    }
			 parent.html(html);
			 $('.home-menu_list').on('mouseenter', function(e) {
	 			var menuContentHright = $(this).find('.menu_list-wap').outerHeight();
	 			$('.navbar_itemContent-background').height(menuContentHright);
	 			var targetEl = $(this).find('.menu_list-wap'),
			 		outerWidth = 0,
				 	boxWidth = $(this).outerWidth(),
				 	clientRect = $(this)[0].getBoundingClientRect(),
				 	offsetWidth = window.innerWidth > 1300 ? 1300 : window.innerWidth;
	 			targetEl.addClass('wrap_active');
	 			outerWidth = targetEl.outerWidth();
	 			if (outerWidth >= offsetWidth) {
	 				targetEl.css({
	 					'width': offsetWidth + 'px',
	 					'left': '0' + 'px',
	 				});
	 				targetEl.find('.home_left_middle').css('flexWrap', 'wrap');
	 			} else {
	 				if (clientRect.left <= (outerWidth - boxWidth) / 2 && (clientRect.left + boxWidth / 2 < offsetWidth / 2)) {
	 					targetEl.css({
		 					'left': '0',
		 					'transform': 'none'
		 				});	
	 				} else if(clientRect.left + outerWidth / 2 > offsetWidth && (clientRect.left + boxWidth / 2 >  offsetWidth / 2)) {
 						targetEl.css({
 							'left': 'unset',
		 					'right': '0',
		 					'transform': 'none'
		 				});
	 				} else {
	 					var defaultLeft = clientRect.left - (outerWidth - boxWidth) / 2 - $('.navul')[0].getBoundingClientRect().left;
	 					targetEl.css({
		 					'left': defaultLeft +'px',
		 					'transform': 'none'
		 				});
	 				}
	 			}
	            $(this).find('a').addClass('active');
			 });
			 $('.home-menu_list').on('mouseleave', function(e) {
	 			$(this).find('.menu_list-wap').removeClass('wrap_active');
				$(this).find('a').removeClass('active');
	 			$('.navbar_itemContent-background').height(0);
			 });
			}
		$.ajax({
		        url: '${APP_PATH}/MlbackCategory/getCategorySuperMenu',
		        method: 'POST',
		        success: function (data) {
				  if(data.code === 100){
					  FirstList = data.extend.categoryFirstList,
					  SuperList = data.extend.mlbackCategorySuperList;
					  renderMainCategory(nav_boxfirst,FirstList,SuperList)
				  }else{
					 renderErrorMsg(nav_boxfirst, '未获取到目录相关的数据'); 
				  }
		        }
		      })
		var cartText = iCart.find('.cart_num');
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
		    window.location.href = "${APP_PATH }/MlfrontUser/topcUserCenter";
		  } else {
		    window.location.href = "${APP_PATH }/MlfrontUser/toPcLoginRegisterPage";
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
		
		/* check user is login or not  */
		  function toProductItem(id) {
		  if (parseInt(id)) window.location.href = "${APP_PATH }/MlbackProduct/topcProductDetailPage?productId=" + id;
		}
/*******搜索****************************************/
		   	$(".serch_cont button").click(function(){
		     	var seaProductName = $(".tt-search-input").val();
		     	if(seaProductName==""){
		         	alert("Please enter keywords;")
		     	} else {
		         	window.location.href = "${APP_PATH}/MlbackProduct/toSearchPage?seaProductName=" +seaProductName;
		     	}
		   	});

			$(".serch_cont .show_ul li").click(function(){
			  	var seaProductName =$(this).html();
				window.location.href = "${APP_PATH}/MlbackProduct/toSearchPage?seaProductName=" +seaProductName;
			});

			$(".serch_cont input").focus(function(){
				 $(".serch_cont .show_ul").slideDown(200);
			});

			$('body').delegate('.serch_cont .show_ul li', 'click', function(e){
				var val = $(this).text();
				$(".serch_cont input").val(val);
				$(".serch_cont .show_ul").slideUp(200);
				e.stopPropagation();
			});

		    $("body").on('click', '.serch_cont .close_serch',function() {
			    $('.serch_cont').slideUp(200);
			});

			$(document).click(function(e){
			    var target = $(e.target);
			     if(target.closest(".tt-search-input").length != 0) return;
			    $('.serch_cont .show_ul').slideUp(200)
			});
	</script>
</body>
</html>