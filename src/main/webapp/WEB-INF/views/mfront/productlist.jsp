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

	<jsp:include page="mheader.jsp"></jsp:include>

	<!-- main -->
	<div class="main">
		<div class="condition select">
			<select class="select-item select-category" data-type="category"></select>
		</div>
		<div class="product-list"></div>
	</div>

	<jsp:include page="mfooter.jsp"></jsp:include>

	<script>
		var condition = $('.select');
		var productList = $('.product-list');
		var sessionScopecategoryId = '${sessionScope.categoryId}';
		//var cidA = window.location.href.split('?')[1].split('=');
		var cidA = sessionScopecategoryId;

		//default codition
		getProductList({
			"productCategoryid": cidA
		});

		/* category condition */
		$.ajax({
			url: '${APP_PATH}/MlbackCategory/getOneMlbackCategoryParentDetail',
			type: "GET",
			success: function (data) {
				if (data.code === 100) {
					var resData = data.extend.mlbackCategorydownEr;
					// console.log(resData);
					renderCondition($('.select-item.select-category'), resData)
					$('.select-item').each(function (i, item) {
						if ($('.select-category').val() && $('.select-category').val().trim().length > 0) {
							$(item).on('change', function () {
								//console.log($('.select-category').val(), $('.select-color').val());
								getProductList({
									"productCategoryid": $('.select-category').val() || cidA[1]
								});
							})

						}
					})

				} else {
					renderErrorMsg(prodcutBox, 'No product-related data was obtained');
				}
			}
		});
		/* product list for category */
		function getProductList(data) {
			$.ajax({
				url: '${APP_PATH}/MlbackProduct/getMlbackProductByparentCategoryIdListNew',
				data: JSON.stringify(data),
				dataType: "JSON",
				contentType: 'application/json',
				type: "POST",
				success: function (data) {
					// console.log(data.extend.mlbackProductResList);
//					console.log(JSON.parse(data));
//					console.log("data.extend.mlbackProductResList");
					//从类别中获取fb所需要的当前页面的类下产品
					var shopidlist = toFbidsPurchase((JSON.parse(data)).extend.mlbackProductResList);
					fbq('track', 'ViewCategory', {
			              content_ids: [shopidlist],
			              content_type: 'product'
			            });
					// console.log("shopidlist");
					// console.log(shopidlist);
					// console.log("shopidlist");
					var data = JSON.parse(data);
					if (data.code === 100) {
						var productData = data.extend.mlbackProductResList;
						if(productData==null){
							renderErrorMsg(productList, 'No product-related data was obtained');
						}else{
							var DataproListBySaleNum =orderProListBySaleNum(productData);
							rednerProduct(productList,DataproListBySaleNum);
						}
						// rednerProduct(productList, productData);
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
		function orderProListBySaleNum(reqData) {
				if(reqData.length>0){
				   var n = reqData.length;
				   for(var i=0;i<n;i++){
					 for(var j=0;j<n-1-i;j++){
						// console.log(reqData[j].productHavesalenum);
					   if(reqData[j].productHavesalenum<reqData[j+1].productHavesalenum){
						 var  DateOne = reqData[j];
						 reqData[j] = reqData[j+1];
						 reqData[j+1] = DateOne;
					   }
					 }
				   }
				 }
				return reqData;
			}
		
		//计算fb所需要的当前页面的类下产品
		function toFbidsPurchase(resData){
	       	var infoStrlids = '';
	       	var infoRelids = '';
	       	for(var i=0;i<resData.length;i++){
	       		infoStrlids=infoStrlids+resData[i].productId+',';
	       	}
	       	infoRelids=infoStrlids.substr(0,infoStrlids.length-1);
	       	return infoRelids;
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
					html += '<div class="product-item">' +
					 '<span class="hui_icon '+showspan+'"></span>'+
						'<div class="product-img">' +
						/* '<a href="${APP_PATH}/MlbackProduct/tomProductDetailPage?productId=' + data[i].productId + '">' + */
						'<a href="${APP_PATH}/' + data[i].productSeo + '.html">' +
						'<img src="' + data[i].productMainimgurl + '" alt="">' +
						'</a>' +
						'</div>' +
						'<div class="product-desc">' +
						'<div class="product-title">' +
						'<a href="${APP_PATH}/' + data[i].productSeo + '.html">'+data[i].productName+'</a>' +
						'</div>' +
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
					// console.log("*********")
					html = '<option value="' + data[i].categoryId + '">' + data[i].categoryName + '</option>' + html;
				} else {
					html += '<option value="' + data[i].categoryId + '">' + data[i].categoryName + '</option>';
				}
			}

			parent.html(html);
		}
	</script>
	<!-- megalook-->
  	<script src="//code.tidio.co/sjcpaqy3xxtkt935ucnyf2gxv1zuh9us.js"></script>
	<!-- megalookhair 
  	<script src="//code.tidio.co/0rpdotjoqewxstfjahkd1ajtxrcp8phh.js"></script>-->
  	<!-- huashuohair -->
  	<!-- <script src="//code.tidio.co/folzahtp5vdopiwathysfiyz75dk5vnm.js"></script> -->
</body>

</html>