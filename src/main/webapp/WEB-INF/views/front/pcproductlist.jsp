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

	<jsp:include page="pcheader.jsp"></jsp:include>

	<!-- main -->
	<div class="main">
		<div class="container">
			<div class="condition select list_homeselect">
				<div class="slect_po">
				<span>style</span>	<select class="select-item select-category" data-type="category"></select>
				</div>
			</div>
			<div class="product-list product-list_home"></div>
			<div class="page-info-area product_list"></div>
		</div>
	</div>

	<jsp:include page="pcfooter.jsp"></jsp:include>

	<script>
		var reviewId = null;
		var condition = $('.select');
		var productList = $('.product-list');
		var sessionScopecategoryId = '${sessionScope.categoryId}';
		console.log("sessionScopecategoryId:"+sessionScopecategoryId);
		//var cidA = window.location.href.split('?')[1].split('=');
		var cidA = sessionScopecategoryId;
		var productCategoryid = cidA;

		//default codition
		/* getProductList({
			"productCategoryid": cidA
		}); */
		
		// getProductList(productCategoryid,1);
	to_page(1);
		/* category condition */
		$.ajax({
			url: '${APP_PATH}/MlbackCategory/getOneMlbackCategoryParentDetail',
			type: "GET",
			success: function (data) {
				if (data.code === 100) {
					var resData = data.extend.mlbackCategorydownEr;
					//console.log(resData);
					renderCondition($('.select-item.select-category'), resData)
					$('.select-item').each(function (i, item) {
						if ($('.select-category').val() && $('.select-category').val().trim().length > 0) {
							$(item).on('change', function () {
								//console.log($('.select-category').val(), $('.select-color').val());
								/* getProductList({
									"productCategoryid": $('.select-category').val() || cidA[1]
								}); */
								productCategoryid = $('.select-category').val() || cidA;
								to_page(1);
							})

						}
					})

				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained');
				}
			}
		});
		
		function to_page(pn) {
			$.ajax({
				url: '${APP_PATH}/MlbackProduct/getMlbackProductByparentCategoryIdListAndpn',
				data: {
     			"categoryId": productCategoryid,
     			"pn": pn
   		  },
				type: "POST",
				success: function (data) {
					// console.log(data)
					// var data = JSON.parse(data);
					if (data.code === 100) {
						var reviewTextData =  data.extend.mlbackProductResList;
						console.log(reviewTextData)
						rednerProduct(productList,reviewTextData);
						var pageInfo = data.extend.pageInfo;
						console.log("************pageInfo*************");
						console.log(pageInfo);
						console.log("************pageInfo*************");
						render_page_nav($('.page-info-area'), pageInfo)
					} else {
						renderErrorMsg(productList, 'No product-related data was obtained');
					}
				},
				error: function (error) {
					if (error.status === 400) {
						renderErrorMsg(productList, 'There is no relevant product, the page will jump to the home page after 3s!');
						setTimeout(function () {
							window.location.href = "${APP_PATH}/index/isMobileOrPc";
						}, 3000);
					}
				}
			});
		}
		
		// var pageInfo

		// render page nav
		function render_page_nav(parent, pageInfo) {
			//page_nav_area
			parent.empty();
			var ul = $("<ul></ul>").addClass("pagination");

			//构建元素
			var firstPageLi = $("<li></li>").append($("<a></a>").append("first").attr("href", "javascript:;"));
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
			if (pageInfo.hasPreviousPage == false) {
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			} else {
				//为元素添加点击翻页的事件
				firstPageLi.click(function () {
					to_page(1);
				});
				prePageLi.click(function () {
					to_page(pageInfo.pageNum - 1);
				});
			}

			var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append($("<a></a>").append("last").attr("href", "javascript:;"));
			if (pageInfo.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			} else {
				nextPageLi.click(function () {
					to_page(pageInfo.pageNum + 1);
				});
				lastPageLi.click(function () {
					to_page(pageInfo.pages);
				});
			}

			//添加首页和前一页 的提示
			ul.append(firstPageLi).append(prePageLi);
			//1,2，3遍历给ul中添加页码提示
			$.each(pageInfo.navigatepageNums, function (index, item) {

				var numLi = $("<li></li>").append($("<a></a>").append(item));
				if (pageInfo.pageNum == item) {
					numLi.addClass("active");
				}
				numLi.click(function () {
					to_page(item);
					
				});
				ul.append(numLi);
			});
			//添加下一页和末页 的提示
			ul.append(nextPageLi).append(lastPageLi).appendTo(parent);
		}

 		function renderErrorMsg(parent, msg) {
			parent.html('<p>' + msg + '</p>');
		}

 		function rednerProduct(parent, data) {
			var html = '';
			if (data.length > 0) {
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
					html += '<div class="product-item" data-productid="'+ data[i].productId +'">' +
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

				parent.html(html);
			} else {
				renderErrorMsg(parent, 'Relevant product classification products have been removed from the shelves!');
			}
		}

		function renderCondition(parent, data, defaultHtml) {
			var html = defaultHtml || '';
			html += ''

			for (var i = 0, len = data.length; i < len; i += 1) {
				if (data[i].categoryId === parseInt(cidA[1])) {
					html = '<option value="' + data[i].categoryId + '">' + data[i].categoryName + '</option>' + html;
				} else {
					html += '<option value="' + data[i].categoryId + '">' + data[i].categoryName + '</option>';
				}
			}

			parent.html(html);
		}
	</script>
	<!--  这是一个聊天交流的工具 -->
  	<script src="//code.tidio.co/0rpdotjoqewxstfjahkd1ajtxrcp8phh.js"></script>
</body>

</html>