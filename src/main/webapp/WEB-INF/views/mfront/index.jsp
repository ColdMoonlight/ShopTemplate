<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
	<title>Megalook Hair - Megalook Offical Store</title>
  	<meta name="keyword" content="Megalook,MegaLook Hair,Human Hair Wig,Full Lace Wig, 360 Lace Front Wig, Lace Front Wig,Bundles with Closure,straight Human Hair,Body Wave Human Hair,Water Wave Human Hair,Deep Wave Wig,Loose Wave Wig,kinky Curly Wig,Jerry Curly Wig,Bob Wig,Yaki Straight Wig,Full Lace Wig,Transparent Lace Wig,613 Blonde Wig,Ombre Color Wig,613 blonde hair">
  	<meta name="description" content="Megalook Hair Official Website Sells Best Virgin Hair, Full Lace Wig, Transparent Lace Wig, 360 Lace Wig, Frontal Lace Wig, Closure Lace Wig, Bundles with Closure, Bundles with Frontal, Free Wig Making Service. Hottest Products, Free Gifts, Free & Fast Shipping. No Shedding, No Tangles, Save Big! Lowest Price For U.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, viewport-fit=cover">
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
	<link rel="apple-touch-icon" sizes="120x120" href="${APP_PATH }/static/m/img/index/micon1.png">
	<link rel="apple-touch-icon" sizes="180x180" href="${APP_PATH }/static/m/img/index/micon2.png">
	<link rel="icon" sizes="120x120" href="${APP_PATH }/static/m/img/index/micon1.png">
	<!-- Facebook Pixel Code 这是facebook广告插件可以注释掉，但不要删除-->
	<script>
		!function (f, b, e, v, n, t, s) {
			if (f.fbq) return; n = f.fbq = function () {
				n.callMethod ?
				n.callMethod.apply(n, arguments) : n.queue.push(arguments)
			};
			if (!f._fbq) f._fbq = n; n.push = n; n.loaded = !0; n.version = '2.0';
			n.queue = []; t = b.createElement(e); t.async = !0;
			t.src = v; s = b.getElementsByTagName(e)[0];
			s.parentNode.insertBefore(t, s)
		}(window, document, 'script',
			'https://connect.facebook.net/en_US/fbevents.js');
		fbq('init', '246433859565492');//huashuohair
		//fbq('init', '667403967094866');//megalook
		fbq('track', 'PageView');
	</script>
	<!--huashuohair-->
	<noscript><img height="1" width="1" style="display:none"src="https://www.facebook.com/tr?id=246433859565492&ev=PageView&noscript=1" /></noscript>
	<!--megalook-->
	<!-- <noscript><img height="1" width="1" style="display:none"src="https://www.facebook.com/tr?id=667403967094866&ev=PageView&noscript=1" /></noscript> -->
	<!-- End Facebook Pixel Code -->

	<script>
		fbq('track', 'ViewContent');
	</script>
	<!-- Facebook Pixel Code end -->
	<style>
		.go_re {
			z-index: 9999999999 !important;
		}
	</style>
</head>

<body>

	<jsp:include page="mheader.jsp"></jsp:include>
	<link rel="stylesheet" href="${APP_PATH }/static/m/css/wapindex.css">
	<div class="bannerfirst">
		<div id="ban_silder">
			<div class="swiper-wrapper">
				<div class="swiper-slide"><img src="${APP_PATH }/static/m/img/loading/load-banner.gif" /></div>
			</div>
			<div class="swiper-pagination"></div>
		</div>
	</div>
   <div class="links_contimg clearfix" id="class_nav">
<!-- 	   <ul class="clearfix">
			<li><a href="###"><img src="${APP_PATH }/static/m/img/index/bt_02.jpg" /></a></li>
			<li><a href="###"><img src="${APP_PATH }/static/m/img/index/bt_04.jpg" /></a></li>
			<li><a href="###"><img src="${APP_PATH }/static/m/img/index/bt_08.jpg" /></a></li>
			<li><a href="###"><img src="${APP_PATH }/static/m/img/index/bt_10.jpg" /></a></li>
			<li><a href="###"><img src="${APP_PATH }/static/m/img/index/bt_06.jpg" /></a></li>
	   </ul> -->
   </div>
	<div class="narrow clearfix" id="hot-two">
		<img src="${APP_PATH }/static/m/img/loading/load-product2.gif" />
	</div>
	<div class="coupon_cont2">
		<img src="${APP_PATH }/static/m/img/loading/load-product2.gif" />
	</div>
	<div id="countdown-area"></div>
	<div class="actionhot clearfix" id="hot-product"></div>
	<div class="banner_fl_1"></div>
	<div class="hot_index_product clearfix">
		<div class="hot_box_product_cont">
			<div class="swiper-wrapper"></div>
			<div class="swiper-button-prev hotbtn_prev"></div>
			<div class="swiper-button-next hotbtn_next"></div>
		</div>
	</div>
	<div class="grid" id="hot-adv"></div>
	<div class="banner_fl_2"></div>
	<div class="new_index_product clearfix">
		<div class="new_box_product_cont">
			<div class="swiper-wrapper"></div>
			<div class="swiper-button-prev newbtn_prev"></div>
			<div class="swiper-button-next newbtn_next"></div>
		</div>
	</div>
	<div class="banner_fl_3"></div>
	<div class="comment_cont clearfix"> </div>
	<div class="mask maskindex" style="display: none;">
		<div class="go_re">
			<span class="close">×</span>
			<%-- <a href="${APP_PATH }/MlfrontUser/toLoginRegisterPage"><img src="${APP_PATH }/static/pc/img/sdd.jpg"></a> --%>
			<div class="lottery">
				<!-- div class="lottery-close">x</div> -->
				<div class="lottery-email">
					<div class="lottery-title">
						<span><b>M</b>EGA<b>L</b>OOK</span>
						<span>Your Hair Your Surprise</span>
					</div>
					<input type="text" placeholder="Email address">
		            <div class="lottery-join-tip">Enter Your Email, Get Gift Pack 30$ & Barcelet 25$ ,Free ！！！</div>
					<div class="lottery-email-tip"></div>
				</div>
	
				<div class="lottery-game-box">
					<div class="lottery-game">
						<div class="lottery-startgame">
							<!-- <div class="lottery-game-item">
		                        <span>Start</span>
		                        <span>GAME</span>
		                    </div> -->
						</div>
						<div class="lottery-game-list"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="mfooter.jsp"></jsp:include>
	<script src="${APP_PATH }/static/js/countdown.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/lazyload@2.0.0-rc.2/lazyload.js"></script>
	<script>
		function rednerHotProduct(parent, data) {
			var html = '';
			html += '<div class="actionhot_cont clearfix">';
			html += '<ul class="clearfix">';
			for (var i = 0; i < data.length; i += 1) {
				var actshowprolei = data[i].actshowproIfproORcate;
				if (actshowprolei == 0) {
					html += '<li>' +
						'<a href="${APP_PATH}/' + data[i].actshowproSeoname + '.html">' +
						'<img src="'+ loadProductUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
						'</a>' +
						'</li>';
				} else if (actshowprolei == 1) {
					html += '<li>' +
						'<a href="${APP_PATH}/search/' + data[i].actshowproCateSeoname + '.html">' +
						'<img src="'+ loadProductUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
						'</a>' +
						'</li>';
				} else if (actshowprolei == 2) {
					html += '<li>' +
						'<a href="${APP_PATH}/' + data[i].actshowproPageSeoname + '.html">' +
						'<img src="'+ loadProductUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
						'</a>' +
						'</li>';
				}
			}
			html += '</ul>';
			html += '</div>';

			parent.html(html);
		}
		/************************************/
		function rednerHotTwo(parent, data) {
			var html = '';
			html += '<div class="narrow_cont clearfix">';
			for (var i = 0; i < data.length; i += 1) {
				var actshowprolei = data[i].actshowproIfproORcate;
				if (actshowprolei == 0) {
					html += '<li>' +
						'<a href="${APP_PATH}/' + data[i].actshowproSeoname + '.html">' +
						'<img src="'+ loadProductUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
						'</a>' +
						'</li>';
				} else if (actshowprolei == 1) {
					html += '<li>' +
						'<a href="${APP_PATH}/search/' + data[i].actshowproCateSeoname + '.html">' +
						'<img src="'+ loadProductUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
						'</a>' +
						'</li>';
				} else if (actshowprolei == 2) {
					html += '<li>' +
						'<a href="${APP_PATH}/' + data[i].actshowproPageSeoname + '.html">' +
						'<img src="'+ loadProductUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
						'</a>' +
						'</li>';
				}
			}
			html += '</div>';
			parent.html(html);
		}
		
		/******advadvadvadvadv******************************/
		function rednerHotAdv(parent, data) {
			var html = '';
			html += '<ul class="grid_cont clearfix">';
			for (var i = 0; i < data.length; i += 1) {
				var actshowprolei = data[i].actshowproIfproORcate;
				if (actshowprolei == 0) {
					html += '<li>' +
						'<a href="${APP_PATH}/' + data[i].actshowproSeoname + '.html">' +
						'<img src="'+ loadAdUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
						'</a>' +
						'</li>';
				} else if (actshowprolei == 1) {
					html += '<li>' +
						'<a href="${APP_PATH}/search/' + data[i].actshowproCateSeoname + '.html">' +
						'<img src="'+ loadAdUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
						'</a>' +
						'</li>';
				} else if (actshowprolei == 2) {
					html += '<li>' +
						'<a href="${APP_PATH}/' + data[i].actshowproPageSeoname + '.html">' +
						'<img src="'+ loadAdUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
						'</a>' +
						'</li>';
				}

			}
			html += '</ul>';
			parent.html(html);
		}

		/************************************/
		function rednerCountDownAreaOne(parent, data) {
			var html = '';
			html += '<div class="title">' + data.countdownTitle + '</div>' +
				'<div class="body"></div>' +
				'<div class="desc">' + data.countdownDescription + '</div>';
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

			if (countdown && countdown.state == 'after') {
				$('#countdown-area').addClass('hide');
			}
		}
		
		/*------rednerBanner----------*/
		function renderBanner(data) {
			return $('<div class="bannerpic"></div>').html('<img src="'+ loadHotUrl +'" data-src="' + data.showareaImgurl + '" alt="' + data.showareaName + '">');
		}

		/*------rednerProduct----------*/
		function rednerProduct(parent, data, type, morelink) {
			var html = '',
				productUrl = loadProductUrl;
			if (type == 2) productUrl = loadProduct2Url;

			for (var i = 0; i < data.length; i += 1) {
				var productactoffif = data[i].productActoffIf;
				// console.log(productactoffif)
				var productactoffid = data[i].productActoffid;
				// console.log(productactoffid)  
				var cp_icon = "";
				var showspan = "";
				if (productactoffif == 1) {
					if (productactoffid == 1) {
						showspan = "showactive1"
					} else if (productactoffid == 2) {
						showspan = "showactive2"
					} else if (productactoffid == 3) {
						showspan = "showactive3"
					} else if (productactoffid == 4) {
						showspan = "showactive4"
					}

				} else {
					showspan = "hideactive"
				}
				html += '<div class="swiper-slide">' +
					'<div class="product-item" data-productid="' + data[i].productId + '">' +
					'<span class="hui_icon ' + showspan + '"></span>' +
					'<div class="product-img">' +
					'<a href="${APP_PATH}/' + data[i].productSeo + '.html">' +
					'<img src="'+ productUrl +'" data-src="' + data[i].productMainimgurl + '" alt="">' +
					'</a>' +
					'</div>' +
					'<div class="product-desc">' +
					'<div class="product-title">' + data[i].productName + '</div>' +
					'<div class="product-type"></div>' +
					'<div class="product-data">' +
					'<span class="pay-num">' + (data[i].productHavesalenum ? data[i].productHavesalenum : 0) + ' Orders</span>' +
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
					'</div>' +
					'</div>';
			}
			// top-selling/New-Arrival
			html += '<div class="swiper-slide"><div class="product-item"><a href="${APP_PATH}/search/'+ morelink +'.html" class="morelink"><img src="${APP_PATH }/static/m/img/firstpage/filter.png"><div class="word-desc"></div></div></a></div>';
			parent.html(html);
			var heightno = $(".product-desc").height();
			$(".word-desc").css("height", heightno);

		}
		/*******排序*****************/
		function orderProListBySaleNum(reqData) {
			if (reqData.length > 0) {
				var n = reqData.length;
				for (var i = 0; i < n; i++) {
					for (var j = 0; j < n - 1 - i; j++) {
						// console.log(reqData[j].productHavesalenum);
						if (reqData[j].productHavesalenum < reqData[j + 1].productHavesalenum) {
							var DateOne = reqData[j];
							reqData[j] = reqData[j + 1];
							reqData[j + 1] = DateOne;
						}
					}
				}
			}
			return reqData;
		}

		/***************首页banner***********************************************/
		function rednertop(parent, data) {
			var html = '';
			for (var i = 0; i < data.length; i += 1) {
				var slideIfinto_click = data[i].slideIfinto;
				var slideIfproORcateORpage = data[i].slideIfproORcateORpage;
				if (slideIfinto_click == 0) {
					html += '<div class="swiper-slide">' +
						'<a href="javascript:;">' +
						'<img class="lazyload" src="' + loadBannerUrl + '" data-src="' + data[i].slideWapimgurl + '" alt="">' +
						'</a>' +
						'</div>';
				} else {
					if (slideIfproORcateORpage == 0) {
						html += '<div class="swiper-slide">' +
							'<a href="${APP_PATH}/' + data[i].slideSeoname + '.html">' + '<img class="lazyload" src="' + loadBannerUrl + '" data-src="' + data[i].slideWapimgurl + '" alt="">' + '</a>' +
							'</div>';
					} else if (slideIfproORcateORpage == 1) {
						html += '<div class="swiper-slide">' +
							'<a href="${APP_PATH}/search/' + data[i].slideCateSeoname + '.html">' + '<img class="lazyload" src="' + loadBannerUrl + '" data-src="' + data[i].slideWapimgurl + '" alt="">' + '</a>' +
							'</div>';
					} else if (slideIfproORcateORpage == 2) {
						html += '<div class="swiper-slide">' +
							'<a href="${APP_PATH}/' + data[i].slidePageSeoname + '.html">' + '<img class="lazyload" src="' + loadBannerUrl + '" data-src="' + data[i].slideWapimgurl + '" alt="">' + '</a>' +
							'</div>';
					}
				}
			}
			parent.html(html);
		}
		/******分类导航*******************/
         function classNavigation(parent, data) {
         	var html = '';
         	html += '<ul class="clearfix">';
         	for (var i = 0; i < data.length; i += 1) {
         		var actshowprolei = data[i].actshowproIfproORcate;
         		if (actshowprolei == 0) {
         			html += '<li>' +
         				'<a href="${APP_PATH}/' + data[i].actshowproSeoname + '.html">' +
         				'<img src="'+ loadProductUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
         				'</a>' +
         				'</li>';
         		} else if (actshowprolei == 1) {
         			html += '<li>' +
         				'<a href="${APP_PATH}/search/' + data[i].actshowproCateSeoname + '.html">' +
         				'<img src="'+ loadProductUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
         				'</a>' +
         				'</li>';
         		} else if (actshowprolei == 2) {
         			html += '<li>' +
         				'<a href="${APP_PATH}/' + data[i].actshowproPageSeoname + '.html">' +
         				'<img src="'+ loadProductUrl +'" data-src="' + data[i].actshowproImgwapurl + '" alt="">' +
         				'</a>' +
         				'</li>';
         		}
         	}
         	html += '</ul>';
         
         	parent.html(html);
         }


		/*******优惠券*************************/
		function renderCoupon(parent, data) {
			var html = '';
			for (var i = 0; i < data.length; i += 1) {
				var slideIfinto_click = data[i].slideIfinto;
				var slideIfproORcateORpage = data[i].slideIfproORcateORpage;
				if (slideIfinto_click == 0) {
					html += '<div class="banner"><a href="javascript:;">' +
						'<img src="'+ loadCouponUrl +'" data-src="' + data[i].slideWapimgurl + '" alt="">' +
						'</a></div>';
				} else {
					if (slideIfproORcateORpage == 0) {
						html += '<div class="banner">' +
							'<a href="${APP_PATH}/' + data[i].slideSeoname + '.html">' + '<img src="'+ loadCouponUrl +'" data-src="' + data[i].slideWapimgurl + '" alt="">' + '</a>' +
							'</div>';
					} else if (slideIfproORcateORpage == 1) {
						html += '<div class="banner">' +
							'<a href="${APP_PATH}/search/' + data[i].slideCateSeoname + '.html">' + '<img src="'+ loadCouponUrl +'" data-src="' + data[i].slideWapimgurl + '" alt="">' + '</a>' +
							'</div>';
					} else if (slideIfproORcateORpage == 2) {
						html += '<div class="banner">' +
							'<a href="${APP_PATH}/' + data[i].slidePageSeoname + '.html">' + '<img src="'+ loadCouponUrl +'" data-src="' + data[i].slideWapimgurl + '" alt="">' + '</a>' +
							'</div>';
					}
				}
			}
			parent.html(html);
		}

		/***ins品论*****************************/
		/***********************/
		function masonryHtml(parent, data1, data2) {
			var html = '';
			html += '<ul class="clearfix">';
			for (var i = 0; i < data1.length; i++) {
				html += '<li class="list_ins" onclick="clickVideo(event)" videonum-id="' + data1[i].reviewPid + '">';
				for (var j = 0; j < data2[i].length; j++) {
					html += '<img src="'+ loadProductUrl +'" data-src="' + data2[i][j].reviewimgUrl + '" />';
				}
				html += '</li>';
			}
			html += '</ul>';
			parent.html(html);

		}

		/*******************************/
		function videoProduct(data) {
			var elBox = $('<div class="video_enlarge_corver index_tcbox" style="display: block;"></div>');
			var html = '<div class="video_enlarge_wrap">' +
				'<div class="video_enlarge_content">' +
				'<div class="promotion_product_list clearfix">' +
				'<a href="###" class="product-image"><img src="' + data.productMainimgurl + '" alt=""></a>' +
				'<div class="rightcont">' +
				'<h3 class="product-name"><a href="javascript:;">' + data.productName + '</a></h3>' +
				'<div class="price-box_promotion">' +
				'<div class="price-box">' +
				'<p class="old-price">' +
				'<span class="price-label">Regular Price:</span>' +
				'<span class="price">$' + (data.productOriginalprice ? data.productOriginalprice : 0) +
				'</p>' +
				'<p class="special-price span12">' +
				'<span class="price-label">SALE PRICE:</span>' +
				'<span class="price">$' + (data.productOriginalprice && data.productActoffoff ? (data.productOriginalprice * data.productActoffoff / 100).toFixed(2) : 0) + '</span>' +

				'</p>' +
				'</div>' +
				'</div>' +
				'<a class="video_link_buyBtn" href="${APP_PATH }/' + data.productSeo + '.html">Buy Now </a>' +
				'</div>' +
				'</div>' +
				'</div>' +
				'<button class="video_enlarge_close">X</button>'
			'</div>' +
				$(document.body).append(elBox.html(html));
			$(".video_enlarge_close").on("click", function () {
				elBox.remove();
			})
		}
		/*********************************/
		function clickVideo(e) {
			e.stopPropagation();
			var item = $(e.target);
			var productId = item.parent().attr('videonum-id');
			data = {
				"productId": productId
			};
			// console.log(productId)
			$.ajax({
				url: '${APP_PATH}/MlbackProduct/getOneProductSimple',
				data: data,
				type: 'post',
				success: function (data) {
					//										 console.log(data)/***data**/
					if (data.code == 100) {
						var resData = data.extend.mlbackProductOne;
						videoProduct(resData)
					}
				}
			});

		}

		var bannerfirst = $("#ban_silder .swiper-wrapper"),
			bannercoupon = $(".coupon_cont2"),
			countDownArea = $('#countdown-area'),
			bannerBlock1 = $(".banner_fl_1"),
			bannerBlock2 = $(".banner_fl_2"),
			bannerBlock3 = $(".banner_fl_3"),
			hot_pic = $('.hot_box_product_cont .swiper-wrapper'),
			new_pic = $('.new_box_product_cont .swiper-wrapper'),
			masonrycont = $(".comment_cont");

		var loadProductUrl = "${APP_PATH }/static/m/img/loading/load-product.gif",
			loadProduct2Url = "${APP_PATH }/static/m/img/loading/load-product2.gif",
			loadBannerUrl = "${APP_PATH }/static/m/img/loading/load-banner.gif",
			loadAdUrl = "${APP_PATH }/static/m/img/loading/load-ad.gif",
			loadCouponUrl = "${APP_PATH }/static/m/img/loading/load-coupon.gif",
			loadHotUrl = "${APP_PATH }/static/m/img/loading/load-hot.gif";
		/* ban_silder */
		$.ajax({
			url: '${APP_PATH}/MlbackSlide/getMlbackSlidewapListByArea',
			data: JSON.stringify({ "slideArea": 1 }),
			type: 'post',
			dataType: 'json',
			async: false,
			contentType: 'application/json',
			success: function (data) {
				// console.log(data)/***data**/
				if (data.code == 100) {
					var resData = data.extend.mlbackSlideList;
					// console.log(resData)
					rednertop(bannerfirst, resData);
					new Swiper('#ban_silder', {
						freeMode: true,
						autoplay: {
							delay: 5000,
							stopOnLastSlide: false,
							disableOnInteraction: false,
						},
						pagination: {
							el: '.swiper-pagination',
							clickable: true
						}
					});
					new LazyLoad(bannerfirst.find('img'), {
						root: null,
						rootMargin: "10px",
						threshold: 0
					});
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
				}
			}
		});
		/* hot-two */
		$.ajax({
			url: '${APP_PATH}/MlbackActShowPro/getMlbackActShowProListByActnum',
			data: "actshowproActnum=" + 8,
			type: "post",
			async: false,
			success: function (data) {
				if (data.code == 100) {
					var resData = data.extend.mlbackActShowProList;
					rednerHotTwo($('#hot-two'), resData);
					new LazyLoad($('#hot-two').find('img'), {
						root: null,
						rootMargin: "10px",
						threshold: 0
					});
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
				}
			}
		});
		/* coupon_cont2 */
		$.ajax({
			url: '${APP_PATH}/MlbackSlide/getMlbackSlidewapListByArea',
			data: JSON.stringify({ "slideArea": 2 }),
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				// console.log(data)/***data**/
				if (data.code == 100) {
					var resData = data.extend.mlbackSlideList;;
					// console.log(resData);
					renderCoupon(bannercoupon, resData);
					new LazyLoad(bannercoupon.find('img'), {
						root: null,
						rootMargin: "10px",
						threshold: 0
					});
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
				}
			}
		});
		/* countdown-area */
		$.ajax({
			url: '${APP_PATH}/MlbackCountDown/getOneMlbackCountDownDetail',
			data: "countdownId=" + 1,
			type: "post",
			success: function (data) {
				// console.log("mlbackCountDownOne");
				if (data.code == 100) {
					var resData = data.extend.mlbackCountDownOne;
					// console.log(resData);
					if (data.extend.mlbackCountDownOne == null) {
						// console.log("mlbackCountDownOne为null");
					} else {
						rednerCountDownAreaOne(countDownArea, resData);
					}
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
				}
			}
		});
		/* hot-product */
		$.ajax({
			url: '${APP_PATH}/MlbackActShowPro/getMlbackActShowProListByActnum',
			data: "actshowproActnum=" + 7,
			type: "post",
			success: function (data) {
				if (data.code == 100) {
					// console.log(data)
					var resData = data.extend.mlbackActShowProList;
					rednerHotProduct($('#hot-product'), resData);
					new LazyLoad($('#hot-product').find('img'), {
						root: null,
						rootMargin: "0px",
						threshold: 0
					});
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
				}
			}
		});
		
		$.ajax({
			url: '${APP_PATH}/MlbackActShowPro/getMlbackActShowProListByActnum',
			data: "actshowproActnum=" + 9,
			type: "post",
			success: function (data) {
				if (data.code == 100) {
					console.log(data)
					var resData = data.extend.mlbackActShowProList;
					if(resData.length==0){
						$("#class_nav").hide();
					}else{
						classNavigation($('#class_nav'), resData);
						new LazyLoad($('#class_nav').find('img'), {
							root: null,
							rootMargin: "0px",
							threshold: 0
						});
					}
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
				}
			}
		});
		
		
		
		/* banner_fl_1 */
		$.ajax({
			url: '${APP_PATH}/MlbackShowArea/getMlbackShowAreaOne',
			data: "showareaNumth=" + 1,
			type: "post",
			success: function (data) {
				// console.log(data);
				if (data.code == 100) {
					var resData = data.extend.mlbackShowAreaOne;
					// console.log(resData);
					bannerBlock1.append(renderBanner(resData));
					new LazyLoad(bannerBlock1.find('img'), {
						root: null,
						rootMargin: "0px",
						threshold: 0
					});
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
				}
			}
		});
		/* hot_index_product */
		$.ajax({
			url: '${APP_PATH}/MlbackCategory/searchBycategorySeo',
			data: JSON.stringify({ "categorySeo": "top-selling" }),
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					var resDataProduct = data.extend.mlbackProductResList,
						DataproListBySaleNum = orderProListBySaleNum(resDataProduct)
					rednerProduct(hot_pic, DataproListBySaleNum.slice(0, 8), 2, "top-selling");
					new Swiper('.hot_box_product_cont', {
						slidesPerView: 2,
						spaceBetween: 5,
						freeMode: true,
						navigation: {
							nextEl: '.hotbtn_next',
							prevEl: '.hotbtn_prev',
						}
					});
					new LazyLoad($('.hot_box_product_cont').find('img'), {
						root: null,
						rootMargin: "0px",
						threshold: 0
					});
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained');
				}
			}
		});
		/* hot-adv */
		$.ajax({
			url: '${APP_PATH}/MlbackActShowPro/getMlbackActShowProListByActnum',
			data: "actshowproActnum=" + 6,
			type: "POST",
			success: function (data) {
				if (data.code == 100) {
					var resData = data.extend.mlbackActShowProList;
					rednerHotAdv($('#hot-adv'), resData);
					new LazyLoad($('#hot-adv').find('img'), {
						root: null,
						rootMargin: "0px",
						threshold: 0
					});
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
				}
			}
		});
		/* banner_fl_2 */
		$.ajax({
			url: '${APP_PATH}/MlbackShowArea/getMlbackShowAreaOne',
			data: "showareaNumth=" + 2,
			type: "POST",
			success: function (data) {
				if (data.code == 100) {
					var resData = data.extend.mlbackShowAreaOne;
					// console.log(resData);
					bannerBlock2.append(renderBanner(resData));
					new LazyLoad(bannerBlock2.find('img'), {
						root: null,
						rootMargin: "0px",
						threshold: 0
					});
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
				}
			}
		});
		/* new_index_product */
		$.ajax({
			url: '${APP_PATH}/MlbackCategory/searchBycategorySeo',
			data: JSON.stringify({ "categorySeo": "New-Arrival" }),
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				if (data.code == 100) {
					var resDataProduct = data.extend.mlbackProductResList,
						DataproListBySaleNum = orderProListBySaleNum(resDataProduct);
					rednerProduct(new_pic, DataproListBySaleNum.slice(0, 8), 2, "New-Arrival");
					new Swiper('.new_box_product_cont', {
						slidesPerView: 2,
						spaceBetween: 5,
						freeMode: true,
						navigation: {
							nextEl: '.newbtn_next',
							prevEl: '.newbtn_prev',
						}
					});
					new LazyLoad($('.new_box_product_cont').find('img'), {
						root: null,
						rootMargin: "0px",
						threshold: 0
					});
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained');
				}
			}
		});
		/* banner_fl_3 */
		$.ajax({
			url: '${APP_PATH}/MlbackShowArea/getMlbackShowAreaOne',
			data: "showareaNumth=" + 3,
			type: "POST",
			success: function (data) {
				if (data.code == 100) {
					var resData = data.extend.mlbackShowAreaOne;
					// console.log(resData);
					bannerBlock3.append(renderBanner(resData));
					new LazyLoad(bannerBlock3.find('img'), {
						root: null,
						rootMargin: "0px",
						threshold: 0
					});
				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
				}
			}
		});
		/* comment_cont */
		$.ajax({
			url: "${APP_PATH}/MlfrontReview/selectReviewListFrom",
			data: { "reviewFrom": 3 },
			type: "POST",
			success: function (result) {
				if (result.code == 100) {
					resData = result.extend.mlfrontReviewList;
					resDataimg = result.extend.mlfrontReviewImgList;
					masonryHtml(masonrycont, resData.slice(0, 10), resDataimg.slice(0, 10));
					new LazyLoad(masonrycont.find('img'), {
						root: null,
						rootMargin: "0px",
						threshold: 0
					});
				}
			}
		});
		addTidio();
	</script>
	<script>
		function isValidEmail(email) {
			var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
			return reg.test(email);
		}

		function startGame() {
			if (!isPushEmail) {
				$.ajax({
					url: '${APP_PATH}/MlbackCoupon/getCouponLuckDrawResultAndUserEmail',
					type: 'post',
					dataType: 'json',
					data: {
						userEmail: emailEl.val(),
						couponId: String(lotteryData.couponId)
					},
					/* contentType: 'application/json', */
					success: function (data) {
						if (data.code == 100) {
							isPushEmail = true;
						}
					}
				});
			}
			var timer = null,
				speed = 100,
				gameItemEls = $('.lottery-game-list .lottery-game-item'),
				currentItem = gameItemEls[rollCount % 8];

			if (rollCount >= lotteryIndex + defaultTimes) {
				clearTimeout(timer);
				/* 重置 */
				rollCount = 0;
				$(prevItem).removeClass('active');
				isStartLottery = false;
				lotteryIndex = -1;

				if (isPushEmail) {
					$(".lottery").hide();
					/* $(".mask").hide(); */
					renderLotterySuccess();
					lotteryData = null;
					logStatus();
					/* 5s后， 转盘出现 */
		            /* setTimeout(function() {
		            	// alert('注册中')
		            	window.location.reload();
		            }, 10000); */
				} else {
					$('.lottery-email-tip').text('Lucky draw failed, please try again!');
				}
			} else {
				speed = rollCount <= defaultTimes ? speed - 5 : speed + 20;

				prevItem && $(prevItem).removeClass('active');
				$(currentItem).addClass('active');
				prevItem = currentItem;
				rollCount += 1;

				timer = setTimeout(startGame, speed);
			}
		}

		function logStatus() {
			var date = new Date();
			document.cookie = "isHideLottery=true;expires=" + (date.setTime(date.getTime() + 30 * 60 * 1000), date.toGMTString());
		}

		function renderLotterySuccess() {
			var lotteryResultHtml = '';
			lotteryResultHtml += '<div class="lottery-result">'
				+ '<div class="lottery-container">'
				+ '<div class="lottery-result-title"><span>Congratulations!</span><span>You got a</span></div>'
				+ '<div class="lottery-card">'
				+ '<div class ="card-item card-price"><span>';
			if (parseInt(lotteryData.couponType)) {
				lotteryResultHtml += parseInt(lotteryData.couponPriceOff) + '%';
			} else {
				lotteryResultHtml += '$' + lotteryData.couponPrice;
			}
			lotteryResultHtml += '</span><span>Discount</span></div><div class="card-item card-code"><span>Your Discount Code is:</span><span>' + lotteryData.couponCode + '</span></div>'
				+ '<div class="card-use">'
				+ (lotteryData.couponPriceBaseline ? 'Upon Order $' + lotteryData.couponPriceBaseline : 'No limit!') + '.</div>'
				+ '</div>'
				+ '<div class="card-tip">in order to use this discount add it to the relevant field in checkout!</div>'
				+ '<div class="card-op">'
				+ '<a class="btn" href="javascript: window.location.reload();">Use Discount</a>'
        		/* + '<a class="btn" href="${APP_PATH}/MlfrontUser/toCouponPage">Use Discount</a>'
        		+ '<a class="btn" href="javascript: window.location.reload();">HOME</a>' */
				+ '</div></div></div>';
			$('.go_re').append(lotteryResultHtml);
		}

		function getLotteryIndex() {
			var couponArr = [],
				couponList = [],
				index = 0,
				lotteryRequest = null;

			$.ajax({
				url: '${APP_PATH}/MlbackCoupon/getMlbackCouponShowByLuckDrawType',
				type: 'post',
				dataType: 'json',
				contentType: 'application/json',
				async: false,
				success: function (data) {
					if (data.code == 100) {
						lotteryRequest = data;
					}
				}
			});

			couponList = lotteryRequest.extend.mlbackCouponResList;
			if (couponList.length != 8) {
				return 0;
			}
			if (lotteryCount < 1) {
				var htmlStr = '',
					lotteryGameListEl = document.querySelector('.lottery-game-list');

				for (var item in couponList) {
					var itemData = couponList[item];
					couponArr.push(itemData.couponId);
					htmlStr += '<div class="lottery-game-item"><img src="' + couponList[item].couponImgwapurl + '"/></div>';
				}

				setTimeout(function() {
		    		if (index > - 1) {
						lotteryGameListEl.innerHTML = htmlStr;
						$(".mask").show();
						$(document.body).css('overflow', 'hidden');		    			
		    		}
				}, 2000);
	
				$(".go_re .close").click(function(e){
					$(".mask").hide();
					$(document.body).css('overflow', 'unset');
		            logStatus();
				});
				
				$(".mask").click(function(e){
					if (e.target == this) {
						$(".mask").hide();
						$(document.body).css('overflow', 'unset');
			            logStatus();
					}
				});
			}
			index = couponArr.indexOf(lotteryRequest.extend.luckDrawDate && lotteryRequest.extend.luckDrawDate.luckDrawCouponId);
			lotteryData = couponList[index];
			return index > -1 ? index + 1 : 0;
		}

		function checkUserEmail(email) {
			var isUsed = false;
			$.ajax({
				url: '${APP_PATH}/MlbackCoupon/checkCouponLuckDrawResultAndUserEmail',
				type: 'post',
				dataType: 'json',
				data: {
					userEmail: email,
					couponId: String(lotteryData.couponId)
				},
				async: false,
				success: function (data) {
					if (data.code == 100) {
						isUsed = data.extend && data.extend.emailIsCan ? true : false;
					}
				},
				fail: function () {
					$('.lottery-email-tip').text('The email address validation failed, please try again!');
				}
			});

			return isUsed;
		}

		function getCookie(name) {
			var arr,
				reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

			if (arr = document.cookie.match(reg))
				return (arr[2]);
			else
				return null;
		}

		function isShowLottery() {
	    	return !isHideLottery && lotteryIndex
	    }
		
	    function startLotteryGame() {
	    	$('.lottery-email>input').on('change', function () {
				$('.lottery-email-tip').text('');
			});
			gameStartEl.on('click', function (e) {
				var timer = null;

				if (!isStartLottery) {
					if (isValidEmail(emailEl.val())) {
						isStartLottery = true;
						// 判断是否使用过
						if (!checkUserEmail(emailEl.val())) {
							startGame();
						} else {
							$('.lottery-email-tip').text('This email address has been used!');
							isStartLottery = false;
						}
					} else {
						$('.lottery-email-tip').text('Please enter a valid email address first!');
					}

					if (!gameStartEl.hasClass('active')) {
						gameStartEl.addClass('active');
						timer = setTimeout(function () {
							gameStartEl.removeClass('active');
							clearTimeout(timer);
						}, 300);
					}
				}
			});
	    }

		var emailEl = $('.lottery-email input'),
			gameStartEl = $('.lottery-startgame'),
			isPushEmail = false,
			lotteryCount = 0,
			defaultTimes = 16,
			rollCount = 0,
			lotteryData = null,
			isStartLottery = false,
			isHideLottery = getCookie('isHideLottery') || false,
			lotteryIndex = isHideLottery ? undefined : getLotteryIndex(),
			prevItem = null;
		if (isShowLottery()) startLotteryGame();
	</script>
</body>

</html>