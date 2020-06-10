<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<title>Checkout</title>
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
	<link rel="stylesheet" href="${APP_PATH }/static/common/swiper/swiper.min.css">
	<script src="${APP_PATH }/static/common/swiper/swiper.min.js"></script>

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

</head>

<body>

	<jsp:include page="mheader.jsp"></jsp:include>

	<!-- main -->
	<div class="main">
		<!-- purechase step -->
		<img class="purechase-step" src="${APP_PATH }/static/m/img/other/step_checkout.jpg">
		<!-- add/show address -->
		<div class="tit_numtt">
			<span>1</span><b>SHIPPING ADDRESS</b>
		</div>
		<!-- address box -->
		<div class="shopingaddress address-box">
			<div class="win-box-content">
				<form action="">
					<!-- address id -->
					<input type="hidden" class="address-id" name="addressId">
					<!-- first name -->
					<div class="form-group">
						<label for="addressUserfirstname" class="form-label required">First Name</label>
						<div class="form-input">
							<input type="text" name="addressUserfirstname" class="form-control firstname">
						</div>
					</div>
					<!-- last name -->
					<div class="form-group">
						<label for="addressUserlastname" class="form-label required">Last Name</label>
						<div class="form-input">
							<input type="text" name="addressUserlastname" class="form-control lastname">
						</div>
					</div>
					<!-- email address -->
					<div class="form-group">
						<label for="addressEmail" class="form-label required">Email Adress</label>
						<div class="form-input">
							<input type="text" name="addressEmail" placeholder="eg:@gmail.com,and so on"
								class="form-control email">
						</div>
					</div>
					<!-- telephone -->
					<div class="form-group">
						<label for="addressTelephone" class="form-label required">Telephone</label>
						<div class="form-input">
							<input type="text" name="addressTelephone" class="form-control phone">
						</div>
					</div>
					<!-- address -->
					<div class="form-group" style="width: 100%">
						<label for="addressDetail" class="form-label required">Address</label>
						<span class="label-exp">Don't forget the apartment No.</span>
						<div class="form-input">
							<input type="text" name="addressDetail"
								placeholder="street address (Dont't forget the apartment)"
								class="form-control address addreNo">
						</div>
					</div>
					<!-- country -->
					<div class="form-group form-groupcountry">
						<label for="addressCountry" class="form-label required">Country</label>
						<div class="form-input">
							<select name="addressCountry" class="form-control" id="country" value="US">
								<option value="US" selected="selected">United States</option>
							</select>
						</div>
					</div>
					<!-- stateprovinceName -->
					<div class="form-group form-group_select selectActive">
						<label for="addressProvince" class="form-label required">State/Province</label>
						<div class="form-input">
							<select name="addressProvince" class="form-control" id="province"></select>
						</div>
					</div>
					<!-- city -->
					<div class="form-group form-group2">
						<label for="addressCity" class="form-label required">City</label>
						<div class="form-input">
							<input type="text" name="addressCity" class="form-control city">
						</div>
					</div>
					<!-- Zip/Postal code -->
					<div class="form-group">
						<label for="addressPost" class="form-label required">Zip/Postal code</label>
						<div class="form-input">
							<input type="text" name="addressPost" class="form-control code">
						</div>
					</div>
				</form>
				<div class="errortips"><span>A match of the shipping Address city,State and Postal Code failed.</span></div>
			</div>
		</div>
		<div class="shipping">SHIPPING COST: <span>$0</span></div>
		<div class="tit_numtt">
			<span>2</span><b>Checkout Review</b>
		</div>
		<!-- ordrer list  -->
		<div class="order-list order_box">
			<div class="order-item">
				<div class="order-title"><span class="text" id="newDate_zsh">*</span></div>
				<div class="order-body">
					<div class="cart-list"> </div>
				</div>
				<div class="list-group">
					<li class="list-group-item">
						<!-- <div class="group-title"><span>Choose Coupons</span> <span class="price-info"></span></i></div> -->
						<div class="tit_numtt"><span>3</span><b>DISCOUNT CODES</b></div>
						<div class="sale_coupon"></div>
						<div class="group-details coupons active"></div>
					</li>
					<li class="list-group-item">
						<!-- <div class="group-title"><span>PAYMENT METHOD</span></div> -->
						<div class="tit_numtt"><span>4</span><b>PAYMENT METHOD</b></div>
						<div class="group-details pay-method active">
							<div class="coupon-item">
								<input type="radio" name="payment" data-payid="0" checked onclick="selectPay(event)" class="checkbox active">
								<img src="${APP_PATH }/static/m/img/other/paypal.jpg">
							</div>
						</div>
					</li>
					<li class="list-group-item">
						<!-- <div class="group-title"><span>Buyer messages</span></div> -->
						<div class="tit_numtt"><span>5</span><b>ADDITIONAL INFORMATION</b></div>
						<div class="group-details customer-message active">
							<textarea placeholder="Buyer message"></textarea>
						</div>
					</li>
				</div>

				<div class="order-cal bd-t">
					<div class="cal-price">
						<div class="cal-price-item c-prototal">
							<span class="cal-price-text">prototal:</span>
							<span class="cal-price-num"></span>
						</div>
						<div class="cal-price-item c-shipping">
							<span class="cal-price-text">shipping:</span>
							<span class="cal-price-num"></span>
						</div>
						<div class="cal-price-item c-coupon">
							<span class="cal-price-text">coupon:</span>
							<span class="cal-price-num">-$0</span>
						</div>
						<div class="cal-price-item c-subtotal">
							<span class="cal-price-text">subtotal:</span>
							<span class="cal-price-num"></span>
						</div>
					</div>
					<div class="btn btn-black place-order">Pay Securely Now</div>
				</div>
			</div>
		</div>
	</div>
	<div class="loading">
		<div class="boxload">
			<div class="loader-14"></div>
		</div>
	</div>
	<jsp:include page="mfooter.jsp"></jsp:include>
	<script type="text/javascript">
		var myDate = new Date(),
			year_zsh = myDate.getFullYear(), // 获取完整的年份(4位,1970-????)
			month_zsh = myDate.getMonth() + 1, // 获取当前月份(0-11,0代表1月)
			day_zsh = myDate.getDate(), // 获取当前日(1-31)
			hours_zsh = myDate.getHours(), // 获取当前小时数(0-23)
			minutes_zsh = myDate.getMinutes(), // 获取当前分钟数(0-59)
			seconds_zsh = myDate.getSeconds(), // 获取当前秒数(0-59)
			newDate_zsh = '';
		if (seconds_zsh < 10) {
			seconds_zsh = "0" + seconds_zsh;
		}
		newDate_zsh = year_zsh + "-" + month_zsh + "-" + day_zsh + " " + hours_zsh + ":" + minutes_zsh + ":" + seconds_zsh;
		$("#newDate_zsh").html(newDate_zsh);
	</script>
	<script>
		var resDataMoney = 0,
			totalPrice = 0,
			couponId = null,
			couponCode = null,
			addressId,
			orderId,
			orderItemArr = [],
			orderItemPidArr = [],
			productNumArr = [],
			payplate = 0,
			couponPriceOld = 0,
			jiecountry,
			shopidlist,
			counponDataList = {},
			shippingTipPriceEl = $('.shipping').find('span'),
			subtotalPriceEl = $('.cal-price-item.c-subtotal').find('.cal-price-num'),
			prototalPriceEl = $('.cal-price-item.c-prototal').find('.cal-price-num'),
			shippingPriceEl = $('.cal-price-item.c-shipping').find('.cal-price-num'),
			couponPriceEl = $('.cal-price-item.c-coupon').find('.cal-price-num'),
			hasProvince = false;

		// PAYMENT_ALREADY_DONE
		if ('${sessionScope.PaypalErrorName}' == "VALIDATION_ERROR") {
			$(".errortips").show();
		} else {
			$(".errortips").hide();
		}

		$(".phone, #country, #province, .city, .code").click(function () {
			$(".errortips").hide();
		})

		initialData(); // 初始化 checkout data

		$("#country").on("change", function () {
			var radio_zt = $(".coupons .coupon-item input[type='radio']"),
				dataname = $(this).val();
			resetCouponCode();
			$(".coupons .coupon-item input[type=radio]").removeClass("active");
			$(".coed_inp").val("");
			$(".without-data").text("Enter coupon code to get a discount!");
			getProvinceData(dataname);
		});

		$('.place-order').on('click', function () {
			if (orderItemArr.length <= 0) {
				renderSysMsg('Invalid order, will return to the Home Page three seconds later');
				setTimeout(function () {
					window.location.href = "${APP_PATH }/index.html"
				}, 3000);
				return;
			}
			if (!inputCheck9()) {
				$(".loading").show();
				var formData = $('.address-box form').serializeArray(),
					reqData = formData.reduce(function (obj, item) {
						obj[item.name] = item.value;
						return obj
					}, {});
				reqData.addressId = reqData.addressId == '' ? null : parseInt(reqData.addressId);
				$.ajax({
					url: '${APP_PATH}/MlfrontAddress/save',
					type: 'post',
					dataType: 'json',
					data: JSON.stringify(reqData),
					contentType: 'application/json',
					success: function (data) {
						// console.log(data)
						var resDataAddress = data && data.extend.mlfrontAddress,
							addressBox = $('.address'),
							reqData = {},
							reqDataUp = {};
						
						addressId = resDataAddress.addressId;
						$('.address-id').val(addressId);

						reqData = {
							"orderId": orderId,
							"orderOrderitemidstr": orderItemArr.join(','),
							"orderCouponId": couponId,
							"orderCouponCode": (couponCode ? couponCode : null), //传递真的code码
							"orderPayPlate": payplate, //选择的付款方式,int类型   paypal传0，后来再有信用卡传1
							"orderProNumStr": productNumArr.join(','), //就这样,,zheli你传给我了，但是我接到之后，再处理的话，要同时动4张表。。所以，能早处理早处理。早处理的话，就动一张
							"orderBuyMess": $('.customer-message textarea').val(), //买家的留言
							"addressinfoId": addressId,
						};
						reqDataUp = {
							"orderId": orderId,
							"orderOrderitemidstr": orderItemArr.join(','),
							"orderCouponId": couponId,
							"orderCouponCode": (couponCode ? couponCode : null), //传递真的code码
							"orderPayPlate": payplate, //选择的付款方式,int类型   paypal传0，后来再有信用卡传1
							"orderProNumStr": productNumArr.join(','), //就这样,,zheli你传给我了，但是我接到之后，再处理的话，要同时动4张表。。所以，能早处理早处理。早处理的话，就动一张
							"orderBuyMess": $('.customer-message textarea').val(), //买家的留言
							"addressinfoId": addressId,
						};
						if (checkAddress(reqDataUp)) {
							//fbq('track', '');//追踪'发起结账'事件  facebook广告插件可以注释掉，但不要删除
							stridsContent = shopidlist;
							orderMoney = subtotalPriceEl.text().slice(1);
							fbq('track', 'AddPaymentInfo', {
								content_ids: [stridsContent],
								//contents: [strContent],
								content_type: 'product',
								value: orderMoney,
								currency: 'USD'
							});
							$.ajax({
								url: '${APP_PATH}/MlfrontOrder/orderToPayInfo',
								data: JSON.stringify(reqData),
								type: 'post',
								dataType: 'json',
								contentType: 'application/json',
								success: function (data) {
									// console.log(data)
									window.location.href = '${APP_PATH }/paypal/mpay';
									// window.location.href = '${APP_PATH }/index.html';
								}
							})
						} else {
							renderSysMsg('Please fill in the shipping address And save it ')
						}
					}
				})
			}
		});

		function resetCouponCode() {
			couponCode = null;
			couponId = null;
		}
		/*******************/
		function renderAddressDetail(data) {
			var dataprov = data.addressProvince ? data.addressProvince : '',
				dataProvinceCode = data.addressProvinceCode;
			$("input.firstname").val(data.addressUserfirstname ? data.addressUserfirstname : '');
			$("input.lastname").val(data.addressUserlastname ? data.addressUserlastname : '');
			$("input.email").val(data.addressEmail ? data.addressEmail : '');
			$("input.phone").val(data.addressTelephone ? data.addressTelephone : '');
			$("input.address").val(data.addressDetail ? data.addressDetail : '');
			$("input.code").val(data.addressPost ? data.addressPost : '');
			$("input.city").val(data.addressCity ? data.addressCity : '');

			jiecountry = data.addressCountry ? data.addressCountry : '';

			$("#country option:checked").attr("value", jiecountry);
			$("#country option:checked").text(data.addressCountryAll ? data.addressCountryAll : '');

			if (dataProvinceCode && dataprov) {
				$("#province option:checked").val(dataprov);
				$("#province option:checked").text(dataprov);
	
				$(".form-group_select").addClass("selectActive");
				$(".form-groupcountry").css("width", "50%");
			} else {
				$("#province").val('');
				$(".form-group_select").hide();
				$(".form-group_select").removeClass("selectActive")
				$(".form-groupcountry").css("width", "100%");
			}
		}

		function initialData() {
			// 用户信息
			$.ajax({
				url: '${APP_PATH}/MlfrontAddress/getOneMlfrontAddressDetailByUinfo',
				type: 'post',
				dataType: 'json',
				async: false,
				success: function (data) {
					// console.log(data)
					var resDataAddress = data && data.extend.mlfrontAddressOne,
						resDataUserType = data && data.extend.usertype,
						addressBox = $('.address'),
						couponBox = $('.coupons'),
						subtotalText = '';
					resDataMoney = data && data.extend.areafreightMoney;
					addressId = resDataAddress ? resDataAddress.addressId : null;

					renderCoupons(couponBox, resDataUserType);
					if (resDataAddress) {
						var addProvince = resDataAddress.addressProvince,
							addProvinceCode = resDataAddress.addressProvinceCode;

						renderAddressDetail(resDataAddress);
						$('.address-id').val(resDataAddress.addressId);
						shippingTipPriceEl.text(' $' + resDataMoney);
						shippingPriceEl.text('$' + resDataMoney)
						$(".address").addClass("active");
					} else {
						shippingTipPriceEl.text(' $' + resDataMoney);
						shippingPriceEl.text('$' + resDataMoney);
					}

					subtotalText = (parseFloat(resDataMoney) + parseFloat(totalPrice)).toFixed(2);
					subtotalPriceEl.text('$' + subtotalText);
				}
			});

			/* couponlist */
			$.ajax({
				url: '${APP_PATH}/MlbackCouponDescTitle/getMlbackCouponDescTitlewapListByStatus',
				type: 'post',
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					var saleCouponEl = $('.sale_coupon'),
						titleData = data.extend.mlbackCouponDescTitleList,
						couponArrData = data.extend.mlbackCouponDescDetailList;
					if (data.code == 100 && titleData.length > 0) {
						renderSaleCoupon(saleCouponEl, {
							title: titleData[0],
							couponList: couponArrData
						});
					} else {
						saleCouponEl.hide();
					}
				}
			});
			function renderSaleCoupon(parent, data) {
				var htmlStr = '';
				if (data.title && data.title.coupondesctieleWapstatus)
					htmlStr += '<img src="' + data.title.coupondesctieleWapimgurl + '" />';
				htmlStr += '<div class="sale_coupon-body"><p>' + data.title.coupondesctieleTieledetail + '</p><ul>';
				for (var i = 0, len = data.couponList.length; i < len; i++) {
					htmlStr += '<li>' + data.couponList[i].coupondescdetailStrengthpre + '&nbsp;<span>&nbsp;'
						+ data.couponList[i].coupondescdetailStrength + '&nbsp;</span>&nbsp;'
						+ data.couponList[i].coupondescdetailCodepre
						+ '&nbsp;<b>&nbsp;' + data.couponList[i].coupondescdetailCode + '</b></li>';
				}
				htmlStr += '</ul></div>';
				parent.html(htmlStr);
			}

			// 订单列表数据
			$.ajax({
				url: '${APP_PATH}/MlfrontOrder/tomOrderDetailOne',
				type: 'get',
				dataType: 'json',
				success: function (data) {
					var resData = data.extend.mlfrontOrderItemList,
						cartList = $('.cart-list'),
						allPriceObj = calAllProductPrice(resData),
						resDataMoneym = shippingPriceEl.text().slice(1) * 1;
					// console.log(resData);
					shopidlist = toFbidsPurchase(resData);
					orderId = resData && resData.length > 0 ? resData[0].orderId : null;
					cartList.attr('data-id', resData.orderId);
					renderCartList(cartList, resData);
					// console.log(typeof totalPrice)
					prototalPriceEl.text('$' + (allPriceObj.allSubtotalPrice).toFixed(2));

					totalPrice = (allPriceObj.allSubtotalPrice + resDataMoneym).toFixed(2);
					subtotalPriceEl.text('$' + totalPrice);
				}
			});

			getCountry($('#country'));
			getProvinceData($('#country').val());
		}

		function getCountry(parent) {
			var defaultValue = parent.val(),
				htmlStr = '',
				countryList = [{name:"United States",nickname:"US"},{name:"United Kingdom",nickname:"GB"},{name:"Canada",nickname:"CA"},{name:"Nigeria",nickname:"NG"},{name:"Australia",nickname:"AU"},{name:"South Africa",nickname:"ZA"},{name:"Germany",nickname:"DE"},{name:"Kenya",nickname:"KE"},{name:"Spain",nickname:"ES"},{name:"United Arab Emirates",nickname:"AE"},{name:"Afghanistan",nickname:"AF"},{name:"France",nickname:"FR"},{name:"Åland Islands",nickname:"AX"},{name:"Albania",nickname:"AL"},{name:"Algeria",nickname:"DZ"},{name:"American Samoa",nickname:"AS"},{name:"Andorra",nickname:"AD"},{name:"Angola",nickname:"AO"},{name:"Anguilla",nickname:"AI"},{name:"Antarctica",nickname:"AQ"},{name:"Antigua and Barbuda",nickname:"AG"},{name:"Argentina",nickname:"AR"},{name:"Armenia",nickname:"AM"},{name:"Aruba",nickname:"AW"},{name:"Austria",nickname:"AT"},{name:"Azerbaijan",nickname:"AZ"},{name:"Bahamas",nickname:"BS"},{name:"Bahrain",nickname:"BH"},{name:"Bangladesh",nickname:"BD"},{name:"Barbados",nickname:"BB"},{name:"Belarus",nickname:"BY"},{name:"Belgium",nickname:"BE"},{name:"Belize",nickname:"BZ"},{name:"Benin",nickname:"BJ"},{name:"Bermuda",nickname:"BM"},{name:"Bhutan",nickname:"BT"},{name:"Bolivia",nickname:"BO"},{name:"Bosnia and Herzegovina",nickname:"BA"},{name:"Botswana",nickname:"BW"},{name:"Bouvet Island",nickname:"BV"},{name:"Brazil",nickname:"BR"},{name:"British Indian Ocean Territory",nickname:"IO"},{name:"British Virgin Islands",nickname:"VG"},{name:"Brunei",nickname:"BN"},{name:"Bulgaria",nickname:"BG"},{name:"Burkina Faso",nickname:"BF"},{name:"Burundi",nickname:"BI"},{name:"Cambodia",nickname:"KH"},{name:"Cameroon",nickname:"CM"},{name:"Cape Verde",nickname:"CV"},{name:"Cayman Islands",nickname:"KY"},{name:"Central African Republic",nickname:"CF"},{name:"Chad",nickname:"TD"},{name:"Chile",nickname:"CL"},{name:"China",nickname:"CN"},{name:"Christmas Island",nickname:"CX"},{name:"Cocos (Keeling) Islands",nickname:"CC"},{name:"Colombia",nickname:"CO"},{name:"Comoros",nickname:"KM"},{name:"Congo - Brazzaville",nickname:"CG"},{name:"Congo - Kinshasa",nickname:"CD"},{name:"Cook Islands",nickname:"CK"},{name:"Costa Rica",nickname:"CR"},{name:"Côte d’Ivoire",nickname:"CI"},{name:"Croatia",nickname:"HR"},{name:"Cuba",nickname:"CU"},{name:"Cyprus",nickname:"CY"},{name:"Czech Republic",nickname:"CZ"},{name:"Denmark",nickname:"DK"},{name:"Djibouti",nickname:"DJ"},{name:"Dominica",nickname:"DM"},{name:"Dominican Republic",nickname:"DO"},{name:"Ecuador",nickname:"EC"},{name:"Egypt",nickname:"EG"},{name:"El Salvador",nickname:"SV"},{name:"Equatorial Guinea",nickname:"GQ"},{name:"Eritrea",nickname:"ER"},{name:"Estonia",nickname:"EE"},{name:"Ethiopia",nickname:"ET"},{name:"Falkland Islands",nickname:"FK"},{name:"Faroe Islands",nickname:"FO"},{name:"Fiji",nickname:"FJ"},{name:"Finland",nickname:"FI"},{name:"French Guiana",nickname:"GF"},{name:"French Polynesia",nickname:"PF"},{name:"French Southern Territories",nickname:"TF"},{name:"Gabon",nickname:"GA"},{name:"Gambia",nickname:"GM"},{name:"Georgia",nickname:"GE"},{name:"Ghana",nickname:"GH"},{name:"Gibraltar",nickname:"GI"},{name:"Greece",nickname:"GR"},{name:"Greenland",nickname:"GL"},{name:"Grenada",nickname:"GD"},{name:"Guadeloupe",nickname:"GP"},{name:"Guam",nickname:"GU"},{name:"Guatemala",nickname:"GT"},{name:"Guernsey",nickname:"GG"},{name:"Guinea",nickname:"GN"},{name:"Guinea-Bissau",nickname:"GW"},{name:"Guyana",nickname:"GY"},{name:"Haiti",nickname:"HT"},{name:"Heard &amp; McDonald Islands",nickname:"HM"},{name:"Honduras",nickname:"HN"},{name:"Hong Kong SAR China",nickname:"HK"},{name:"Hungary",nickname:"HU"},{name:"Iceland",nickname:"IS"},{name:"India",nickname:"IN"},{name:"Indonesia",nickname:"ID"},{name:"Iran",nickname:"IR"},{name:"Iraq",nickname:"IQ"},{name:"Ireland",nickname:"IE"},{name:"Isle of Man",nickname:"IM"},{name:"Israel",nickname:"IL"},{name:"Italy",nickname:"IT"},{name:"Jamaica",nickname:"JM"},{name:"Japan",nickname:"JP"},{name:"Jersey",nickname:"JE"},{name:"Jordan",nickname:"JO"},{name:"Kazakhstan",nickname:"KZ"},{name:"Kiribati",nickname:"KI"},{name:"Kuwait",nickname:"KW"},{name:"Kyrgyzstan",nickname:"KG"},{name:"Laos",nickname:"LA"},{name:"Latvia",nickname:"LV"},{name:"Lebanon",nickname:"LB"},{name:"Lesotho",nickname:"LS"},{name:"Liberia",nickname:"LR"},{name:"Libya",nickname:"LY"},{name:"Liechtenstein",nickname:"LI"},{name:"Lithuania",nickname:"LT"},{name:"Luxembourg",nickname:"LU"},{name:"Macau SAR China",nickname:"MO"},{name:"Macedonia",nickname:"MK"},{name:"Madagascar",nickname:"MG"},{name:"Malawi",nickname:"MW"},{name:"Malaysia",nickname:"MY"},{name:"Maldives",nickname:"MV"},{name:"Mali",nickname:"ML"},{name:"Malta",nickname:"MT"},{name:"Marshall Islands",nickname:"MH"},{name:"Martinique",nickname:"MQ"},{name:"Mauritania",nickname:"MR"},{name:"Mauritius",nickname:"MU"},{name:"Mayotte",nickname:"YT"},{name:"Mexico",nickname:"MX"},{name:"Micronesia",nickname:"FM"},{name:"Moldova",nickname:"MD"},{name:"Monaco",nickname:"MC"},{name:"Mongolia",nickname:"MN"},{name:"Montenegro",nickname:"ME"},{name:"Montserrat",nickname:"MS"},{name:"Morocco",nickname:"MA"},{name:"Mozambique",nickname:"MZ"},{name:"Myanmar (Burma)",nickname:"MM"},{name:"Namibia",nickname:"NA"},{name:"Nauru",nickname:"NR"},{name:"Nepal",nickname:"NP"},{name:"Netherlands",nickname:"NL"},{name:"Netherlands Antilles",nickname:"AN"},{name:"New Caledonia",nickname:"NC"},{name:"New Zealand",nickname:"NZ"},{name:"Nicaragua",nickname:"NI"},{name:"Niger",nickname:"NE"},{name:"Niue",nickname:"NU"},{name:"Norfolk Island",nickname:"NF"},{name:"Northern Mariana Islands",nickname:"MP"},{name:"North Korea",nickname:"KP"},{name:"Norway",nickname:"NO"},{name:"Oman",nickname:"OM"},{name:"Pakistan",nickname:"PK"},{name:"Palau",nickname:"PW"},{name:"Palestinian Territories",nickname:"PS"},{name:"Panama",nickname:"PA"},{name:"Papua New Guinea",nickname:"PG"},{name:"Paraguay",nickname:"PY"},{name:"Peru",nickname:"PE"},{name:"Philippines",nickname:"PH"},{name:"Pitcairn Islands",nickname:"PN"},{name:"Poland",nickname:"PL"},{name:"Portugal",nickname:"PT"},{name:"Puerto Rico",nickname:"PR"},{name:"Qatar",nickname:"QA"},{name:"Réunion",nickname:"RE"},{name:"Romania",nickname:"RO"},{name:"Russia",nickname:"RU"},{name:"Rwanda",nickname:"RW"},{name:"Saint Barthélemy",nickname:"BL"},{name:"Saint Helena",nickname:"SH"},{name:"Saint Kitts and Nevis",nickname:"KN"},{name:"Saint Lucia",nickname:"LC"},{name:"Saint Martin",nickname:"MF"},{name:"Saint Pierre and Miquelon",nickname:"PM"},{name:"Samoa",nickname:"WS"},{name:"San Marino",nickname:"SM"},{name:"São Tomé and Príncipe",nickname:"ST"},{name:"Saudi Arabia",nickname:"SA"},{name:"Senegal",nickname:"SN"},{name:"Serbia",nickname:"RS"},{name:"Seychelles",nickname:"SC"},{name:"Sierra Leone",nickname:"SL"},{name:"Singapore",nickname:"SG"},{name:"Slovakia",nickname:"SK"},{name:"Slovenia",nickname:"SI"},{name:"Solomon Islands",nickname:"SB"},{name:"Somalia",nickname:"SO"},{name:"South Georgia &amp; South Sandwich Islands",nickname:"GS"},{name:"South Korea",nickname:"KR"},{name:"Sri Lanka",nickname:"LK"},{name:"St. Vincent &amp; Grenadines",nickname:"VC"},{name:"Sudan",nickname:"SD"},{name:"Suriname",nickname:"SR"},{name:"Svalbard and Jan Mayen",nickname:"SJ"},{name:"Swaziland",nickname:"SZ"},{name:"Sweden",nickname:"SE"},{name:"Switzerland",nickname:"CH"},{name:"Syria",nickname:"SY"},{name:"Taiwan",nickname:"TW"},{name:"Tajikistan",nickname:"TJ"},{name:"Tanzania",nickname:"TZ"},{name:"Thailand",nickname:"TH"},{name:"Timor-Leste",nickname:"TL"},{name:"Togo",nickname:"TG"},{name:"Tokelau",nickname:"TK"},{name:"Tonga",nickname:"TO"},{name:"Trinidad and Tobago",nickname:"TT"},{name:"Tunisia",nickname:"TN"},{name:"Turkey",nickname:"TR"},{name:"Turkmenistan",nickname:"TM"},{name:"Turks and Caicos Islands",nickname:"TC"},{name:"Tuvalu",nickname:"TV"},{name:"Uganda",nickname:"UG"},{name:"Ukraine",nickname:"UA"},{name:"Uruguay",nickname:"UY"},{name:"U.S. Outlying Islands",nickname:"UM"},{name:"U.S. Virgin Islands",nickname:"VI"},{name:"Uzbekistan",nickname:"UZ"},{name:"Vanuatu",nickname:"VU"},{name:"Vatican City",nickname:"VA"},{name:"Venezuela",nickname:"VE"},{name:"Vietnam",nickname:"VN"},{name:"Wallis and Futuna",nickname:"WF"},{name:"Western Sahara",nickname:"EH"},{name:"Yemen",nickname:"YE"},{name:"Zambia",nickname:"ZM"},{name:"Zimbabwe",nickname:"ZW"}];
			for (var i = 0, len = countryList.length; i < len; i++) {
				htmlStr += '<option value="' + countryList[i].nickname + '"'+ (countryList[i].nickname == defaultValue ? "selected" : "") +'>' + countryList[i].name + '</option>';
			}

			parent.html(htmlStr);
		}

		function getProvinceData(dataname) {
			$.ajax({
				url: '${APP_PATH}/MlfrontAddress/getAreafreightMoney',
				data: JSON.stringify({ "addressCountry": dataname }),
				async: false,
				type: 'post',
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					// console.log(data)
					var resareafreightMoney = data && data.extend.areafreightMoney,
						mlPaypalStateprovinceList = data && data.extend.mlPaypalStateprovinceList,
						totalPriceselect,
						prototalnum = $(".c-prototal .cal-price-num").text().slice(1);
					// console.log(mlPaypalStateprovinceList)
					if (mlPaypalStateprovinceList && mlPaypalStateprovinceList.length > 0) {
						hasProvince = true;
						renderProvince($('#province'), mlPaypalStateprovinceList);
						$(".form-group_select").show();
						$(".form-group_select").addClass("selectActive")
						$(".form-groupcountry").css("width", "50%");
					} else {
						hasProvince = false;
						$(".form-group_select").hide();
						$(".form-group_select").removeClass("selectActive");
						$(".form-groupcountry").css("width", "100%");
						$('#province').val('');
					}
					shippingTipPriceEl.text(' $' + resareafreightMoney);
					shippingPriceEl.text('$' + resareafreightMoney);
					couponPriceEl.text('-$' + 0);

					resDataMoney = resareafreightMoney;
					totalPriceselect = (parseFloat(prototalnum) + resDataMoney).toFixed(2);
					subtotalPriceEl.text('$' + totalPriceselect);
					couponPriceOld = 0;
				}
			});
		}

		function renderProvince(el, data) {
			var htmlStr = '<option disabled>State</option>',
				defaultValue = $(el).val();
			for (var i = 0, len = data.length; i < len; i += 1) {
				htmlStr += '<option value="' + data[i].stateprovinceName + '"'+ (defaultValue == data[i].stateprovinceName ? "selected" : "") +'>' + data[i].stateprovinceName + '</option>';
			}

			el.html(htmlStr);
		}

		/* 所购商品列表 */
		function renderCartList(parent, data) {
			var html = '';
			// console.log(data)
			for (var i = 0, len = data.length; i < len; i += 1) {
				var skuIdNameArr = data[i].orderitemPskuIdnamestr.split(','),
					skuNameArr = data[i].orderitemPskuNamestr.split(','),
					skuMoneyArr = data[i].orderitemPskuMoneystr.split(','),
					len2 = skuNameArr.length;

				orderItemArr.push(data[i].orderitemId);
				orderItemPidArr.push(data[i].orderitemPid);
				productNumArr.push(data[i].orderitemPskuNumber);

				html += '<div class="cart-item" data-orderitemid="' + data[i].orderitemId + '">' +
					'<img class="img" src="' + data[i].orderitemProductMainimgurl + '" alt="">' +
					'<div class="content">' +
					'<div class="text">' +
					'<div class="title">' + data[i].orderitemPname + '</div>' +
					'<div class="condition">';
				for (var j = 0; j < len2; j += 1) {
					html += '<span class="c-item">' + skuIdNameArr[j] + ': ' + skuNameArr[j] + '</span>'
				}
				html += '</div>' +
					'</div>' +
					'<div class="num" data-orderitemid="">' +
					'<span class="price">' + (getPrice(data[i].orderitemProductOriginalprice, skuMoneyArr, data[i].orderitemProductAccoff).current) + '</span>' +
					'<span class="original">' + (getPrice(data[i].orderitemProductOriginalprice, skuMoneyArr, data[i].orderitemProductAccoff).origin) + '</span>' +
					'<div class="input-group">' +
					'<span class="input-group-addon" id="product-num-sub" onclick="subNum(event)"><i class="icon sub"></i></span>' +
					'<input type="text" name="cart-product-num" disabled="disabled" class="form-control" value="' + data[i].orderitemPskuNumber + '">' +
					'<span class="input-group-addon" id="product-num-add" onclick="addNum(event)"><i class="icon plus"></i></span>' +
					'</div>' +
					'<span class="icon delete" onclick="deleteOrderItem(event)">' + '</span>' +
					'</div>' +
					'</div>' +
					'</div>';
			}
			parent.html(html)
		}

		function updateOrderItemNum(el, num) {
			var orderItem = el.parents('.cart-item'),
				reqData = {
					orderitemId: orderItem.data('orderitemid'),
					orderitemPskuNumber: num
				};
			// console.table(reqData);
			$(".loading").show();
			$.ajax({
				url: '${APP_PATH}/MlfrontOrder/updateOrderItemNum',
				data: JSON.stringify(reqData),
				type: "post",
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						resetCouponCode();
						resetProductNumArr();
					} else {
						renderSysMsg('handle product fail.');
					}
					$(".loading").hide();
				},
				error: function () {
					renderSysMsg('handle product fail.');
				}
			});
		}

		function resetProductNumArr() {
			productNumArr = [];
			$('.cart-item').each(function(index, item) {
				productNumArr.push($(item).find('input[name=cart-product-num]').val());
			});
		}

		function deleteOrderItem(e) {
			e.stopPropagation();
			var orderItem = $(e.target).parents('.cart-item'),
				reqData = { orderitemId: orderItem.data('orderitemid') };
			// console.table(reqData);
			$.ajax({
				url: '${APP_PATH}/MlfrontOrder/delOrderItem',
				data: JSON.stringify(reqData),
				type: "post",
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					var isDelSuccess = data.extend.isDelSuccess,
						orginalItemNum = data.extend.orginalItemNum;
					if (isDelSuccess == 0) {
						renderSysMsg('Delete error.');
					} else {
						if (orginalItemNum > 0) {
							window.location.href = '${APP_PATH}/MlbackCart/toCheakOut';
						} else {
							renderSysMsg('The shopping cart is empty, return to the shopping cart page after 5s.');
							setTimeout(function () {
								window.location.href = '${APP_PATH}/myCart.html';
							}, 5000);
						}
					}
				},
				error: function () {
					renderSysMsg('Handle product fail, please contact customer service.')
				}
			})
		}

		/* all */
		function calAllProductPrice(data) {
			var len = data.length,
				allSubtotalPrice = 0,
				allOriginPrice = 0;
			if (len == 0) {
				return {
					allOriginPrice: 0,
					allSubtotalPrice: 0
				};
			}
			// console.log(data);
			for (var i = 0; i < len; i += 1) {
				var singPrice = 0,
					originalPrice = parseFloat(data[i].orderitemProductOriginalprice),
					discount = parseFloat(data[i].orderitemProductAccoff) / 100,
					skuPriceArr = data[i].orderitemPskuMoneystr.split(','),
					originSinglePrice = 0,
					currentSinglePrice = 0;
				singlePrice = skuPriceArr.reduce(function (price, item) {
					return (price + parseFloat(item));
				}, originalPrice);
				// console.log(data[i])
				originSinglePrice = parseFloat(singlePrice.toFixed(2));
				currentSinglePrice = parseFloat((singlePrice * discount).toFixed(2));
				allOriginPrice = parseFloat((allOriginPrice + originSinglePrice * data[i].orderitemPskuNumber).toFixed(2));
				allSubtotalPrice = parseFloat((allSubtotalPrice + currentSinglePrice * data[i].orderitemPskuNumber).toFixed(2));
			}
			return {
				allOriginPrice: allOriginPrice,
				allSubtotalPrice: allSubtotalPrice
			};
		}

		/* single */
		function getPrice(originalePrice, skuPriceArr, discount) {
			var singlePrice = parseFloat(originalePrice);
			for (var k = 0, len = skuPriceArr.length; k < len; k += 1) {
				singlePrice += (parseFloat(skuPriceArr[k]) ? parseFloat(skuPriceArr[k]) : 0);
			}
			// console.log(singlePrice, discount)

			return {
				origin: parseFloat(singlePrice).toFixed(2),
				current: parseFloat(singlePrice * ((parseFloat(discount) ? parseFloat(discount) : 100) / 100)).toFixed(2)
			};
		}

		/* 优惠券 */
		function renderCoupons(parent, userType) {
			var html = '<div class="input-group">' +
				'<input type="text" class="form-control coed_inp" value="" placeholder="Please enter coupon code">' +
				'<span class="input-group-addon" id="coupon-check" onclick="checkCouponCode(event)">check it</span>' +
				'</div><div class="coupon-error"><p class="without-data">Enter coupon code to get a discount!</p></div>';
			parent.html(html);
		}

		function selectPay(e) {
			var targetEl = $(e.target);
			payplate = targetEl.data('payid');
		}

		function checkCouponCode(event) {
			var couponCode2 = $(event.target).prev('input').val(),
				data = { couponCode: couponCode2 }; // MEGA12	couponCode
			$.ajax({
				url: '${APP_PATH}/MlbackCoupon/getOneMlbackCouponDetailByCode',
				data: JSON.stringify({
					"couponAdminOperatorname": orderItemPidArr.join(','),
					"couponCode": couponCode2
				}),
				type: 'post',
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					function resetCouponPrice() {
						couponPriceOld = 0;
						couponPriceEl.text('-$' + 0);
						subtotalPriceEl.text('$' + (totalPricecou).toFixed(2));
					}
					var couponErrorBox = $('.coupon-error'),
						resData = data && data.extend.mlbackCouponOne,
						c_prototalnum = $(".c-prototal .cal-price-num").text().slice(1),
						shopingnum = $(".c-shipping .cal-price-num").text().slice(1),
						totalPricecou = c_prototalnum * 1 + shopingnum * 1;
					// console.log(data);
					if (data.code == 100 && resData) {
						var couponType = resData.couponType;
						// resData.couponProductOnlyType && data.extend.couponProductOnlyTypeifHave
						if (resData.couponProductOnlyType) {
							if (couponType == 0) {
								if (data.extend.couponProductOnlyTypeifHave) {
									couponPriceEl.text('-$' + resData.couponPrice);
									subtotalPriceEl.text('$' + (totalPricecou - resData.couponPrice).toFixed(2));
									couponPriceOld = resData.couponPrice;
									couponId = resData.couponId;
									couponCode = couponCode2;
									renderErrorMsg(couponErrorBox, resData.couponName + '，Has been used!');
								} else {
									resetCouponPrice();
									renderErrorMsg(couponErrorBox, resData.couponName + '，Code invalid For This Product!');
								}
							} else {
								resetCouponPrice();
								renderErrorMsg(couponErrorBox, resData.couponName + '，Code invalid For This Product!');
							}
						} else {
							if (couponType == 0) {
								if (totalPricecou >= resData.couponPriceBaseline) {
									couponPriceEl.text('-$' + resData.couponPrice);
									subtotalPriceEl.text('$' + (totalPricecou - resData.couponPrice).toFixed(2));
									couponPriceOld = resData.couponPrice;
									couponId = resData.couponId;
									couponCode = couponCode2;
									renderErrorMsg(couponErrorBox, resData.couponName + '，Has been used!');
								} else {
									resetCouponPrice();
									renderErrorMsg(couponErrorBox, 'The minimum usage price of this coupon is' + resData.couponPriceBaseline);
									$(".coed_inp").val("");
								}
							} else {
								if (totalPricecou >= resData.couponPriceBaseline) {
									var offcoup = resData.couponPriceOff, // 取出折扣
										downPrice = (totalPricecou * offcoup / 100).toFixed(2); // 计算减免力度(总价*折扣比)
									couponPriceEl.text('-$' + downPrice);
									subtotalPriceEl.text('$' + (totalPricecou - downPrice).toFixed(2));
									couponPriceOld = downPrice;
									couponId = resData.couponId;
									couponCode = couponCode2;
									renderErrorMsg(couponErrorBox, resData.couponName + '，Has been used!');
								} else {
									resetCouponPrice();
									renderErrorMsg(couponErrorBox, 'The minimum usage price of this coupon is' + resData.couponPriceBaseline);
									$(".coed_inp").val("");
								}
							}
						}
					} else {
						resetCouponPrice();
						renderErrorMsg(couponErrorBox, "Coupons don't exist!");
					}
				}
			});
		}

		function addNum(e) {
			e.stopPropagation();
			var item = $(e.target),
				productNum = item.parent().parent().find('input'),
				productNumText = parseInt(productNum.val());
			productNumText += 1;
			productNum.val(productNumText);

			reCalPrice(item, true);
			updateOrderItemNum(item, productNumText);
		}

		function subNum(e) {
			e.stopPropagation();
			var item = $(e.target),
				productNum = item.parent().parent().find('input'),
				productNumText = parseInt(productNum.val());
			if (productNumText > 1) {
				productNumText -= 1;
				productNum.val(productNumText);

				reCalPrice(item, false);
				updateOrderItemNum(item, productNumText);
			}
		}

		function reCalPrice(item, flag) {
			var parentEl = $(item).parents('.num'),
				prototalEl = $('.c-prototal>.cal-price-num'),
				subtotalEl = $('.c-subtotal>.cal-price-num'),
				currentPrice = parseFloat(parentEl.find('.price').text());
			// console.log("获取当前的currentPrice:"+currentPrice);
			if (flag) {
				var nowtotalPrice = subtotalEl.text().slice(1);
				prototalEl.text('$' + (parseFloat(prototalEl.text().slice(1)) + currentPrice).toFixed(2));

				//先把优惠券加回来,再加上一个原价
				totalPrice = (parseFloat(nowtotalPrice) + currentPrice + parseFloat(couponPriceOld));
				//清空掉又会减掉的钱
				$(".coed_inp").val("");
				//变量归0显示
				couponPriceOld = 0;
				couponPriceEl.text('-$' + 0);
				//显示未折扣的钱
				subtotalEl.text('$' + totalPrice.toFixed(2));
				$(".without-data").text("Enter coupon code to get a discount!");
			} else {
				var nowtotalPrice = subtotalEl.text().slice(1);
				prototalEl.text('$' + (parseFloat(prototalEl.text().slice(1)) - currentPrice).toFixed(2));
				totalPrice = (nowtotalPrice - currentPrice + parseFloat(couponPriceOld));

				couponPriceOld = 0;
				couponPriceEl.text('-$' + 0);
				$(".coed_inp").val("");
				$(".without-data").text("Enter coupon code to get a discount!");
				subtotalEl.text('$' + totalPrice.toFixed(2));
				$(".coupons .coupon-item input[type=radio]").removeClass("active");
			}
		}

		/******************************************************/
		function toFbidsPurchase(resData) {
			var infoStrlids = '',
				infoRelids = '';
			for (var i = 0; i < resData.length; i++) {
				infoStrlids = infoStrlids + resData[i].orderitemPid + ',';
			}
			infoRelids = infoStrlids.substr(0, infoStrlids.length - 1);
			//console.log("infoRelids:"+infoRelids);
			return infoRelids;
		}

		/******************************************************/
		function checkAddress(reqDataUp) {
			var flag = false;
			$.ajax({
				url: '${APP_PATH}/MlfrontAddress/getOneMlfrontAddressDetailByUinfo',
				type: 'post',
				dataType: 'json',
				async: false,
				success: function (data) {
					// console.log(data)
					var resData = data.extend.mlfrontAddressOne;
					if (resData) {
						flag = true;
					} else {
						falg = false;
						if (reqDataUp.addressinfoId != null) {
							flag = true;
						}
					}
				}
			});
			return flag;
		}

		function inputCheck(data) {
			var flag = true;

			//1验证是否为空
			//2验证格式
			for (var key in data) {
				// console.log(key)
				if (key == 'addressEmail') {
					var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
					if (!pattern.test(data[key])) {
						flag = !flag;
						renderSysMsg('Email address format is incorrect');
						break;
					}
				} else if (key == "addressId") {
					continue;
				} else {
					if (data[key].trim().length < 1) {
						flag = !flag;

						renderSysMsg('Required fields with an asterisk cannot be empty. Please check before submitting');
						break;
					}
				}
			}
			return flag;
		}

		function inputCheck9() {
			var flag = 0,
				firstnamestr = $(".firstname").val(),
				lastnamestr = $(".lastname").val(),
				emailstr = $(".email").val(),
				phonestr = $(".phone").val(),
				addressstr = $(".addreNo").val(),
				codestr = $(".code").val(),
				citystr = $(".city").val(),
				countrystr = $('#country').val(),
				provincestr = $("#province").val();

			if (firstnamestr == null || firstnamestr == '') {
				flag = 1;
				// alert("firstnamestr is empty");
				renderSysMsg('firstnamestr is empty')
				$(".firstname").addClass("error_br");
				$(".firstname").focus(function () {
					$(this).removeClass("error_br")
				})
			} else if (lastnamestr == null || lastnamestr == '') {
				flag = 1;
				// alert("lastnamestr is empty");
				renderSysMsg('lastnamestr is empty')
				$(".lastname").addClass("error_br");
				$(".lastname").focus(function () {
					$(this).removeClass("error_br")
				})
			} else if (emailstr == null || emailstr == '') {
				flag = 1;
				// alert("emailstr is empty");
				renderSysMsg('emailstr is empty')
				$(".email").addClass("error_br");
				$(".email").focus(function () {
					$(this).removeClass("error_br")
				})
			} else if (phonestr == null || phonestr == '') {
				flag = 1;
				// alert("phonestr is empty");
				renderSysMsg('phonestr is empty')
				$(".phone").addClass("error_br");
				$(".phone").focus(function () {
					$(this).removeClass("error_br")
				})
			} else if (addressstr == null || addressstr == '') {
				flag = 1;
				// alert("addressstr is empty");
				renderSysMsg('addressstr is empty')
				$(".addreNo").addClass("error_br");
				$(".addreNo").focus(function () {
					$(this).removeClass("error_br")
				})
			} else if (addressstr.length > 100) {
				flag = 1;
				// alert("addressstr is empty");
				renderSysMsg('Street Address length must less than 100 characters !')
				$(".addreNo").addClass("error_br");
				$(".addreNo").focus(function () {
					$(this).removeClass("error_br")
				})
			} else if (codestr == null || codestr == '') {
				flag = 1;
				// alert("codestr is empty");
				renderSysMsg('codestr is empty')
				$(".codestr").addClass("error_br");
				$(".codestr").focus(function () {
					$(this).removeClass("error_br")
				})
			} else if (citystr == null || citystr == '') {
				flag = 1;
				// alert("citystr is empty");
				renderSysMsg('citystr is empty')
				$(".city").addClass("error_br");
				$(".city").focus(function () {
					$(this).removeClass("error_br")
				})
			} else if (countrystr == null || countrystr == '' || countrystr == 'select the Country') {
				flag = 1;
				// alert("countrystr is empty");
				renderSysMsg('countrystr is empty')
				$("#country").addClass("error_br");
				$("#country").focus(function () {
					$(this).removeClass("error_br")
				})
			} else if (hasProvince && !provincestr) {
				flag = 1;
				// alert("provincestr is empty");
				renderSysMsg('provincestr is empty')
				$(".selectActive").addClass("error_br");
				$(".selectActive").focus(function () {
					$(this).removeClass("error_br")
				})
			}

			return flag;
		}
		addTidio();
	</script>
</body>

</html>