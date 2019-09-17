<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<title>Cart List</title>
	<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
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
	<link rel="stylesheet" href="${APP_PATH }/static/common/swiper/swiper.min.css">
	<script src="${APP_PATH }/static/common/swiper/swiper.min.js"></script>
	
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

	<jsp:include page="pcheader.jsp"></jsp:include>

	<!-- main -->
	<div class="main cart-box clearfix">
		<div class="container cat_list_box clearfix">
			<div class="cart-title" style="display: none">
				<img class="purechase-step" src="${APP_PATH }/static/pc/img/sep1.jpg">
				<!-- <div class="cart-status">
					Shopping List
					<span class="icon dot"></span>
				</div> -->
			</div>
			<!-- purechase step -->
			<div class="cart-page">
				     <div class="cart-list">
		
					</div>
					<div class="cart-footer" style="display: none">
						<div class="show-t-num">
							<span class="show-t-num-text">NUMTOTAL:</span>
							<span class="show-t-num-num"></span>
						</div>
						<div class="show-t-price">
							<span class="show-t-price-text">SUBTOTAL:</span>
							<span class="show-t-price-num"></span>
						</div>
						<div class="op">
							<a href="${APP_PATH}/index.html" class="btn add-product">Continue To Add</a>
							<a href="javascript:;" class="btn calc-price">calculate Price</a>
						</div>
					</div>
			</div>
			
			
		</div>
		
	</div>

	<jsp:include page="pcfooter.jsp"></jsp:include>

	<script>
			var cartBox = $('.main.cart-box');
			var cartList = $('.cart-list');
			var cartObj = {};
			var subTotal = $('.show-t-price-num');
			var numTotal = $('.show-t-num-num');
			
			function clearData(data) {
				var targetObj = JSON.parse(window.window.localStorage.getItem('cartlist')) || {};
				if (targetObj === {}) {} else {
					for (var key in data) {
						if (targetObj[data[key].cartitemId]) {
							cartObj[data[key].cartitemId] = targetObj[data[key].cartitemId]
						}
					}
				}
			}
			
			function renderProdcutList(parent, data) {
				// console.log(data);
				var html = '';
				for (var i = 0, len = data.length; i < len; i += 1) {
					var hasStorageItem = cartObj[data[i].cartitemId];
					html += '<div class="cart-item bd-b">' +
						'<input onclick="selectCartItem(event)" '+ (hasStorageItem ? 'checked' : '') +' class="checkbox" type="checkbox" data-cartitemid="' + data[i]
						.cartitemId + '" data-productid="' + data[i].cartitemProductId + '">' +
						'<img class="img" src="' + data[i].cartitemProductMainimgurl + '" alt="">' +
						'<div class="content">' +
						' <div class="text">' +
						'<div class="title">' + data[i].cartitemProductName + '</div>' +
						' <div class="condition">';
					var skuIdArr = data[i].cartitemProductskuIdstr.split(',');
					var skuIdNameArr = data[i].cartitemProductskuIdnamestr.split(',');
					var skuNameArr = data[i].cartitemProductskuNamestr.split(',');
					var skuPriceArr = data[i].cartitemProductskuMoneystr.split(',');
					for (var j = 0, len2 = skuIdArr.length; j < len2; j += 1) {
						html += '<div class="c-item" data-id="' + skuIdArr[j] + '" data-price="+ skuPriceArr[j] +">' + skuIdNameArr[j] +
							': ' + skuNameArr[j] + '</div>';
					}
					var dataPrice = getPrice(data[i].cartitemProductOriginalprice, skuPriceArr, data[i]
					.cartitemProductActoff);
					html += '</div>' +
						'</div>' +
						'<div class="num" data-cartitemid="' + data[i].cartitemId + '" data-cartid="' + data[i].cartitemCartId +
						'" data-productname="' + data[i].cartitemProductName + '">' +
						'<span class="price">$' + (dataPrice.current) + '</span>' +
						'<span class="original">$' + (dataPrice.origin) + '</span>' +
						'<div class="input-group">' +
						'<span class="input-group-addon" id="product-num-sub" onclick="subNum(event)"><i class="icon sub"></i></span>' +
						'<input type="text" name="cart-product-num" disabled="disabled" class="form-control" value="' + (hasStorageItem ? cartObj[data[i].cartitemId].num : data[i].cartitemProductNumber) +
						'">' +
						'<span class="input-group-addon" id="product-num-add" onclick="addNum(event)"><i class="icon plus"></i></span>' +
						'</div>' +
						'<span class="icon delete"  onclick="deleteCartItem(event)">' + '</span>' +
						'</div>' +
						'</div>' +
						'</div>';
				}

				parent.html(html);
			}

		function renderProductNone(parent) {
			var html = '';
			html += '<div class="box-none">' +
				'<img src="${APP_PATH}/static/m/img/index/cart-none.png" alt="">' +
				'<p>The shopping cart is empty. Come and fill it up!</p>' +
				'<a href="${APP_PATH}/index.html" class="btn btn-pink"> Shop Now</a>' +
				'</div>';
			parent.html(html)
		}

		function addNum(e) {
			e.stopPropagation();
			var item  = $(e.target);
			var checkbox = item.parents('.cart-item').find('.checkbox');
			var productNum = item.parent().parent().find('input');
			var productNumText = parseInt(productNum.val());
			productNumText += 1;
			productNum.val(productNumText);

			if(checkbox.is(':checked')) {
				cartObj[checkbox.data('cartitemid')].num = productNumText;
				window.localStorage.setItem('cartlist', JSON.stringify(cartObj));
				getTotalPrice();
			}

			updateCartItemNum(item, productNumText);
		}

		function subNum(e) {
			e.stopPropagation();
			var item  = $(e.target);
			var checkbox = item.parents('.cart-item').find('.checkbox');
			var productNum = item.parent().parent().find('input');
			var productNumText = parseInt(productNum.val());
			if (productNumText <= 1) {
				productNumText = 1;
				productNum.val(productNumText);
			} else {
				productNumText -= 1;
				productNum.val(productNumText);
			}
			
			if(checkbox.is(':checked')) {
				cartObj[checkbox.data('cartitemid')].num = productNumText;
				window.localStorage.setItem('cartlist', JSON.stringify(cartObj));
				getTotalPrice();
			}
			
			updateCartItemNum(item, productNumText);
		}
		/* private Integer cartitemId;
		private String cartitemProductName;
		${APP_PATH}/MlbackCart/updateCartItemSkuNum		POST请求 */
		function updateCartItemNum(el, num) {
			target = el.parent().parent().parent();
			var reqData = {
				cartitemId: target.data('cartitemid'),
				cartitemProductName: target.data('productname'),
				cartitemProductNumber: num
			}
			// console.table(reqData);
			$.ajax({
				url: '${APP_PATH}/MlbackCart/updateCartItemSkuNum	',
				data: JSON.stringify(reqData),
				type: "POST",
				dataType: 'JSON',
				contentType: 'application/json',
				success: function (data) {
					console.info('success')
				},
				error: function () {
					alert('add fail')
				}
			})
		}
		function getPrice(originalePrice, skuPriceArr, discount) {
			var singlePrice = parseFloat(originalePrice);
			for (var k = 0, len = skuPriceArr.length; k < len; k += 1) {
				singlePrice += (parseFloat(skuPriceArr[k]) ? parseFloat(skuPriceArr[k]) : 0);
			}
			// console.log(singlePrice, discount)
			return {
				origin: parseFloat(singlePrice).toFixed(2),
				current: parseFloat(singlePrice * ((parseFloat(discount) ? parseFloat(discount) : 100) / 100)).toFixed(2)
			}
		}
		function getTotalPrice() {
			var price = 0;
			var num = 0;
			for (var i in cartObj) {
				price = (parseFloat(price) + cartObj[i].num * cartObj[i].price).toFixed(2);
				num += cartObj[i].num;
			}
			numTotal.text(num)
			subTotal.text('$ ' + price);
		}

		function calcTotalPrice() {
			var cartItemArr = []
			$('input[type="checkbox"]').each(function (i, item) {
				if ($(item).is(':checked')) {
					cartItemArr.push({
						cartitemId: $(item).data('cartitemid'),
						cartitemProductId: $(item).data('productid'),
						cartitemProductNumber: $(item).parent().find('.input-group input').val()
					});
				}
			})
			if (cartItemArr.length) {
				// console.log(cartItemArr)
				$.ajax({
					url: '${APP_PATH}/MlbackCart/cartToOrder',
					data: JSON.stringify(cartItemArr),
					type: "POST",
					dataType: 'JSON',
					contentType: 'application/json',
					success: function (data) {
						//var data = JSON.parse(data);
						if (data.code === 100) {
							window.location.href = '${APP_PATH}/MlbackCart/topcCheakOut';
						}
					},
					error: function (data) {
						cartText.text(num);
					}
				})
			} else {
				renderSysMsg('请选择最终要购买的产品！')
			}
		}
		$('.btn.calc-price').on('click', function () {
			calcTotalPrice();
			fbq('track', 'InitiateCheckout');
		});
		$.ajax({
			url: '${APP_PATH}/MlbackCart/getCartProductNumber',
			type: 'POST',
			success: function (data) {
				if (data.extend.number) {
					$.ajax({
						url: '${APP_PATH}/MlbackCart/getCartitemIdDetails',
						type: 'POST',
						success: function (data) {
							// console.log(data);
							var resData = data.extend.mlfrontCartItemListRes;
							if (resData.length > 0) {
								$('.cart-title').show();
								renderProdcutList(cartList, resData);
								$('.cart-footer').show();
								getTotalPrice();
								// for all cart-product bind event of going product-details
								toProductDetails();
							} else {
								renderProductNone(cartBox);
							}
						}
					})
				} else {
					renderProductNone(cartBox);
				}
			}
		})
		function toProductDetails() {
			// $('.cart-item').each(function (i, item) {
			// 	$(item).on('click', function () {
			// 		toProductItem($(this).find('.checkbox').data('productid'))
			// 	})
			// }, true)
			
			$(".cart-item").each(function(){
				var textlink =$(this).children(".content").children(".text").find(".title");
				// var imglink =$(this).find(".img");
				textlink.on('click', function () {
					var product_id = $(this).parents(".content").parents(".cart-item").find(".checkbox").data('productid');
					// alert(product_id)
				     toProductItem(product_id)
				});
				// imglink.on('click', function () {
				// 	var product_id = $(this).prev(".checkbox").data('productid');
				// 	// alert(product_id)
				//      toProductItem(product_id)
				// });
				
				
			}, true)
		}
		
		function selectCartItem(e) {
			e.stopPropagation();
			var item = $(e.target);
			var cartItemId = item.data('cartitemid');
			if(item.is(':checked') && !cartObj[cartItemId]) {
				cartObj[cartItemId] = {
						num: parseInt(item.parent().find('input[type=text]').val().trim(), 10),
						price: (+item.parent().find('.price').text().trim().substring(1))
				}
			} else {
				delete cartObj[cartItemId];
			}
			window.localStorage.setItem('cartlist', JSON.stringify(cartObj));
			getTotalPrice();
		}
		/**
		 *     private Integer cartitemId;
		 *		private Integer cartitemCartId;
		 *		url: '${APP_PATH}/MlbackCart/delCartItem',
		 *		JSon格式，post请求
		 */
		var cartNum = parseInt(cartText.text());

		function deleteCartItem(e) {

			e.stopPropagation();
			var el = $(e.target);
			// 只需要传递这一个参数就可以了。cartitemId
			/* var reqData = { // 测试数据
					cartitemCartId: 1000000,
					// cartitemId: 1000000
			} */
			var reqData = {
				//cartitemCartId: el.data('cartid'),
				cartitemId: el.parent().data('cartitemid')
			}
			el.parent().parent().parent().remove();
			cartNum -= 1
			if (cartNum <= 0) {
				cartText.text(0);
				renderProductNone(cartBox);
			} else {
				cartText.text(cartNum);
			}
			$.ajax({
				url: '${APP_PATH}/MlbackCart/delCartItem',
				type: 'POST',
				data: JSON.stringify(reqData),
				// async: false,
				contentType: 'application/json',
				success: function (data) {
					// console.log(data)
					var resData = data.extend;

					if (data.code === 100) {
						if (resData.isDelSuccess === 1) {
							//取出isDelSuccess字段
							//1，删除成功。  不alert，刷新 
							//alert('删除成功')
							window.location.href = '${APP_PATH}/myCart.html';
						}
						if (resData.isDelSuccess === 0) {
							//0，删除失败。alert（resMsg），刷新
							//全是错误情况，直接刷新
							renderSysMsg('The system is abnormal, please try again later');
							if (sysFlag) {
								window.location.href = '${APP_PATH}/myCart.html';
							}
						}
					} else {
						renderSysMsg('The system is abnormal, please try again later');
						if (sysFlag) {
							window.location.href = '${APP_PATH}/myCart.html';
						}
					}
				}
			})
		}
	</script>
</body>

</html>