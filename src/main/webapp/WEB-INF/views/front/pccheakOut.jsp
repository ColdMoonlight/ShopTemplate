<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<title>Checkout</title>
	<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
	<meta name="viewport"	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, viewport-fit=cover">
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
	<div class="main">
		<div class="container pc_cheakout clearfix">
		<!-- purechase step -->
		<div class="cart-title">
		   <img class="purechase-step" src="${APP_PATH }/static/pc/img/sep2.jpg">
	    </div>
		<!-- ordrer list  -->
		<div class="order-list clearfix">
			<div class="order-item clearfix">
					<!-- add/show address -->
				<div class="left_list_check">
				<!-- <div class="address bd-t"></div> -->
				<div class="rece">
			    	<b>Receiving information</b>	
				</div>
				<div class="address-box">
					<div class="win-box-content">
						<form action="">
							<!-- address id -->
							<input type="hidden" class="address-id" name="addressId" />
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
									<input type="text" name="addressEmail" class="form-control email">
								</div>
							</div>
							<!-- telephone -->
							<div class="form-group">
								<label for="addressTelephone" class="form-label required ">Telephone</label>
								<div class="form-input">
									<input type="text" name="addressTelephone" class="form-control phone">
								</div>
							</div>
							<!-- address -->
							<div class="form-group" style="width: 100%">
								<label for="addressDetail" class="form-label required">Address</label>
								<span class="label-exp">Don't forget the apartment No.</span>
								<div class="form-input">
									<input type="text" name="addressDetail" placeholder="street address (Dont't forget the apartment)"
										class="form-control address addreNo">
								</div>
							</div>
							<!-- Zip/Postal code -->
							<div class="form-group">
								<label for="addressPost" class="form-label required">Zip/Postal code</label>
								<div class="form-input">
									<input type="text" name="addressPost" class="form-control code">
								</div>
							</div>
							<!-- city -->
							<div class="form-group">
								<label for="addressCity" class="form-label required">City</label>
								<div class="form-input">
									<input type="text" name="addressCity" class="form-control city">
								</div>
							</div>
							<!-- country -->
							<div class="form-group">
								<label for="addressCountry" class="form-label required">Country</label>
								<div class="form-input">
									<!-- <input type="text" name="addressCountry" class="form-control"> -->
									<select name="addressCountry" class="form-control" id="country">
										<!-- <option value="select the Country">select the Country</option> -->
										<option value="Afghanistan">Afghanistan</option>
										<option value="Åland Islands">Åland Islands</option>
										<option value="Albania">Albania</option>
										<option value="Algeria">Algeria</option>
										<option value="American Samoa">American Samoa</option>
										<option value="Andorra">Andorra</option>
										<option value="Angola">Angola</option>
										<option value="Anguilla">Anguilla</option>
										<option value="Antarctica">Antarctica</option>
										<option value="Antigua and Barbuda">Antigua and Barbuda</option>
										<option value="Argentina">Argentina</option>
										<option value="Armenia">Armenia</option>
										<option value="Aruba">Aruba</option>
										<option value="Australia">Australia</option>
										<option value="Austria">Austria</option>
										<option value="Azerbaijan">Azerbaijan</option>
										<option value="Bahamas">Bahamas</option>
										<option value="Bahrain">Bahrain</option>
										<option value="Bangladesh">Bangladesh</option>
										<option value="Barbados">Barbados</option>
										<option value="Belarus">Belarus</option>
										<option value="Belgium">Belgium</option>
										<option value="Belize">Belize</option>
										<option value="Benin">Benin</option>
										<option value="Bermuda">Bermuda</option>
										<option value="Bhutan">Bhutan</option>
										<option value="Bolivia">Bolivia</option>
										<option value="Bosnia and Herzegovina">Bosnia and Herzegovina</option>
										<option value="Botswana">Botswana</option>
										<option value="Bouvet Island">Bouvet Island</option>
										<option value="Brazil">Brazil</option>
										<option value="British Indian Ocean Territory">British Indian Ocean Territory</option>
										<option value="British Virgin Islands">British Virgin Islands</option>
										<option value="Brunei">Brunei</option>
										<option value="Bulgaria">Bulgaria</option>
										<option value="Burkina Faso">Burkina Faso</option>
										<option value="Burundi">Burundi</option>
										<option value="Cambodia">Cambodia</option>
										<option value="Cameroon">Cameroon</option>
										<option value="Canada">Canada</option>
										<option value="Cape Verde">Cape Verde</option>
										<option value="Cayman Islands">Cayman Islands</option>
										<option value="Central African Republic">Central African Republic</option>
										<option value="Chad">Chad</option>
										<option value="Chile">Chile</option>
										<option value="China">China</option>
										<option value="Christmas Island">Christmas Island</option>
										<option value="Cocos (Keeling) Islands">Cocos (Keeling) Islands</option>
										<option value="Colombia">Colombia</option>
										<option value="Comoros">Comoros</option>
										<option value="Congo - Brazzaville">Congo - Brazzaville</option>
										<option value="Congo - Kinshasa">Congo - Kinshasa</option>
										<option value="Cook Islands">Cook Islands</option>
										<option value="Costa Rica">Costa Rica</option>
										<option value="Cote Ivoire">Cote Ivoire</option>
										<option value="Croatia">Croatia</option>
										<option value="Cuba">Cuba</option>
										<option value="Cyprus">Cyprus</option>
										<option value="Czech Republic">Czech Republic</option>
										<option value="Denmark">Denmark</option>
										<option value="Djibouti">Djibouti</option>
										<option value="Dominica">Dominica</option>
										<option value="Dominican Republic">Dominican Republic</option>
										<option value="Ecuador">Ecuador</option>
										<option value="Egypt">Egypt</option>
										<option value="El Salvador">El Salvador</option>
										<option value="Equatorial Guinea">Equatorial Guinea</option>
										<option value="Eritrea">Eritrea</option>
										<option value="Estonia">Estonia</option>
										<option value="Ethiopia">Ethiopia</option>
										<option value="Falkland Islands">Falkland Islands</option>
										<option value="Faroe Islands">Faroe Islands</option>
										<option value="Fiji">Fiji</option>
										<option value="Finland">Finland</option>
										<option value="France">France</option>
										<option value="French Guiana">French Guiana</option>
										<option value="French Polynesia">French Polynesia</option>
										<option value="French Southern Territories">French Southern Territories</option>
										<option value="Gabon">Gabon</option>
										<option value="Gambia">Gambia</option>
										<option value="Georgia">Georgia</option>
										<option value="Germany">Germany</option>
										<option value="Ghana">Ghana</option>
										<option value="Gibraltar">Gibraltar</option>
										<option value="Greece">Greece</option>
										<option value="Greenland">Greenland</option>
										<option value="Grenada">Grenada</option>
										<option value="Guadeloupe">Guadeloupe</option>
										<option value="Guam">Guam</option>
										<option value="Guatemala">Guatemala</option>
										<option value="Guernsey">Guernsey</option>
										<option value="Guinea">Guinea</option>
										<option value="Guinea-Bissau">Guinea-Bissau</option>
										<option value="Guyana">Guyana</option>
										<option value="Haiti">Haiti</option>
										<option value="Heard Island and Mcdonald Islands">Heard Island and Mcdonald Islands</option>
										<option value="Honduras">Honduras</option>
										<option value="Hong Kong SAR China">Hong Kong SAR China</option>
										<option value="Hungary">Hungary</option>
										<option value="Iceland">Iceland</option>
										<option value="India">India</option>
										<option value="Indonesia">Indonesia</option>
										<option value="Iran">Iran</option>
										<option value="Iraq">Iraq</option>
										<option value="Ireland">Ireland</option>
										<option value="Isle of Man">Isle of Man</option>
										<option value="Israel">Israel</option>
										<option value="Italy">Italy</option>
										<option value="Jamaica">Jamaica</option>
										<option value="Japan">Japan</option>
										<option value="Jersey">Jersey</option>
										<option value="Jordan">Jordan</option>
										<option value="Kazakhstan">Kazakhstan</option>
										<option value="Kenya">Kenya</option>
										<option value="Kiribati">Kiribati</option>
										<option value="Kuwait">Kuwait</option>
										<option value="Kyrgyzstan">Kyrgyzstan</option>
										<option value="Laos">Laos</option>
										<option value="Latvia">Latvia</option>
										<option value="Lebanon">Lebanon</option>
										<option value="Lesotho">Lesotho</option>
										<option value="Liberia">Liberia</option>
										<option value="Libya">Libya</option>
										<option value="Liechtenstein">Liechtenstein</option>
										<option value="Lithuania">Lithuania</option>
										<option value="Luxembourg">Luxembourg</option>
										<option value="Macau SAR China">Macau SAR China</option>
										<option value="Macedonia">Macedonia</option>
										<option value="Madagascar">Madagascar</option>
										<option value="Malawi">Malawi</option>
										<option value="Malaysia">Malaysia</option>
										<option value="Maldives">Maldives</option>
										<option value="Mali">Mali</option>
										<option value="Malta">Malta</option>
										<option value="Marshall Islands">Marshall Islands</option>
										<option value="Martinique">Martinique</option>
										<option value="Mauritania">Mauritania</option>
										<option value="Mauritius">Mauritius</option>
										<option value="Mayotte">Mayotte</option>
										<option value="Mexico">Mexico</option>
										<option value="Micronesia">Micronesia</option>
										<option value="Moldova">Moldova</option>
										<option value="Monaco">Monaco</option>
										<option value="Mongolia">Mongolia</option>
										<option value="Montenegro">Montenegro</option>
										<option value="Montserrat">Montserrat</option>
										<option value="Morocco">Morocco</option>
										<option value="Mozambique">Mozambique</option>
										<option value="Myanmar (Burma)">Myanmar (Burma)</option>
										<option value="Namibia">Namibia</option>
										<option value="Nauru">Nauru</option>
										<option value="Nepal">Nepal</option>
										<option value="Netherlands">Netherlands</option>
										<option value="Netherlands Antilles">Netherlands Antilles</option>
										<option value="New Caledonia">New Caledonia</option>
										<option value="New Zealand">New Zealand</option>
										<option value="Nicaragua">Nicaragua</option>
										<option value="Niger">Niger</option>
										<option value="Nigeria">Nigeria</option>
										<option value="Niue">Niue</option>
										<option value="Norfolk Island">Norfolk Island</option>
										<option value="Northern Mariana Islands">Northern Mariana Islands</option>
										<option value="North">North Korea</option>
										<option value="Norway">Norway</option>
										<option value="Oman">Oman</option>
										<option value="Pakistan">Pakistan</option>
										<option value="Palau">Palau</option>
										<option value="Palestinian Territories">Palestinian Territories</option>
										<option value="Panama">Panama</option>
										<option value="Papua New Guinea">Papua New Guinea</option>
										<option value="Paraguay">Paraguay</option>
										<option value="Peru">Peru</option>
										<option value="Philippines">Philippines</option>
										<option value="Pitcairn Islands">Pitcairn Islands</option>
										<option value="Poland">Poland</option>
										<option value="Portugal">Portugal</option>
										<option value="Puerto Rico">Puerto Rico</option>
										<option value="Qatar">Qatar</option>
										<option value="Reunion">Reunion</option>
										<option value="Romania">Romania</option>
										<option value="Russia">Russia</option>
										<option value="Rwanda">Rwanda</option>
										<option value="Saint Barthelemy">Saint Barthelemy</option>
										<option value="Saint Helena">Saint Helena</option>
										<option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
										<option value="Saint Lucia">Saint Lucia</option>
										<option value="Saint Martin">Saint Martin</option>
										<option value="Saint Pierre and Miquelon">Saint Pierre and Miquelon</option>
										<option value="Samoa">Samoa</option>
										<option value="San Marino">San Marino</option>
										<option value="Sao Tome and Principe">Sao Tome and Principe</option>
										<option value="Saudi Arabia">Saudi Arabia</option>
										<option value="Senegal">Senegal</option>
										<option value="Serbia">Serbia</option>
										<option value="Seychelles">Seychelles</option>
										<option value="Sierra Leone">Sierra Leone</option>
										<option value="Singapore">Singapore</option>
										<option value="Slovakia">Slovakia</option>
										<option value="Slovenia">Slovenia</option>
										<option value="Solomon Islands">Solomon Islands</option>
										<option value="Somalia">Somalia</option>
										<option value="South Africa">South Africa</option>
										<option value="South Georgia and the South Sandwich Islands">South Georgia and the South Sandwich Islands
										</option>
										<option value="South Korea">South Korea</option>
										<option value="Spain">Spain</option>
										<option value="Sri Lanka">Sri Lanka</option>
										<option value="Saint Vincent and the Grenadines">Saint Vincent and the Grenadines</option>
										<option value="Sudan">Sudan</option>
										<option value="Suriname">Suriname</option>
										<option value="Svalbard and Jan Mayen">Svalbard and Jan Mayen</option>
										<option value="Swaziland">Swaziland</option>
										<option value="Sweden">Sweden</option>
										<option value="Switzerland">Switzerland</option>
										<option value="Syria">Syria</option>
										<option value="Taiwan">Taiwan</option>
										<option value="Tajikistan">Tajikistan</option>
										<option value="Tanzania">Tanzania</option>
										<option value="Thailand">Thailand</option>
										<option value="Timor-Leste">Timor-Leste</option>
										<option value="Togo">Togo</option>
										<option value="Tokelau">Tokelau</option>
										<option value="Tonga">Tonga</option>
										<option value="Trinidad and Tobago">Trinidad and Tobago</option>
										<option value="Tunisia">Tunisia</option>
										<option value="Turkey">Turkey</option>
										<option value="Turkmenistan">Turkmenistan</option>
										<option value="Turks and Caicos Islands">Turks and Caicos Islands</option>
										<option value="Tuvalu">Tuvalu</option>
										<option value="Uganda">Uganda</option>
										<option value="Ukraine">Ukraine</option>
										<option value="United Arab Emirates">United Arab Emirates</option>
										<option value="United Kingdom">United Kingdom</option>
										<option value="United States" selected="selected">United States</option>
										<option value="Uruguay">Uruguay</option>
										<option value="U.S. Outlying Islands">U.S. Outlying Islands</option>
										<option value="U.S. Virgin Islands">U.S. Virgin Islands</option>
										<option value="Uzbekistan">Uzbekistan</option>
										<option value="Vanuatu">Vanuatu</option>
										<option value="Vatican City">Vatican City</option>
										<option value="Venezuela">Venezuela</option>
										<option value="Vietnam">Vietnam</option>
										<option value="Wallis and Futuna">Wallis and Futuna</option>
										<option value="Western Sahara">Western Sahara</option>
										<option value="Yemen">Yemen</option>
										<option value="Zambia">Zambia</option>
										<option value="Zimbabwe">Zimbabwe</option>
									</select>
								</div>
							</div>
							<!-- State/Province -->
							<div class="form-group">
								<label for="addressProvince" class="form-label required">State/Province</label>
								<div class="form-input">
									<input type="text" name="addressProvince" class="form-control province">
								</div>
							</div>
						</form>
					</div>
					<div class="shipping">SHIPPING: <span>$0</span></div>
				</div>
<!-- 				<div class="win-box-title">
					<span class="save">  <b>save it</b> </span>
				</div> -->
				</div>
				<!--*********************-->
				<div class="right_checkout clearfix">
					<div class="list-group clearfix">
						<li class="list-group-item">
							<div class="group-title"><span>Choose Coupons</span> <!-- <span class="price-info"></span> --><!-- <iclass="icon right"></i> --></div>
							<div class="group-details coupons"></div>
						</li>
						<li class="list-group-item">
							<div class="group-title"><span>PAYMENT METHOD</span> <!-- <i class="icon bottom"></i> --></div>
							<div class="group-details pay-method active">
								<div class="coupon-item">
									<input type="radio" name="payment" data-payid="0" checked onclick="selectPay(event)" class="checkbox">
									<img src="${APP_PATH }/static/m/img/other/paypal.jpg">
								</div>
							</div>
						</li>
						
						<li class="list-group-item">
							<div class="group-title"><span>Buyer messages</span> <!-- <i class="icon right"></i> --></div>
							<div class="group-details customer-message">
								<textarea placeholder="Buyer message"></textarea>
							</div>
						</li>
					</div>
				</div>
				<div class="bottom_list_account">
					<div class="left_bt_list">
						<div class="order-title">
							<i class="icon bag"></i>
							<span class="text" id="newDate_zsh">*</span>
						</div>
						<div class="order-body">
							<div class="cart-list"> </div>
						</div>
						
					</div>
					<div class="right_allcont">
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
							<div class="btn btn-black place-order">Place Order</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>
	<jsp:include page="pcfooter.jsp"></jsp:include>
	<script type="text/javascript">
		var myDate = new Date();
		var year_zsh = myDate.getFullYear(); // 获取完整的年份(4位,1970-????)
		var month_zsh = myDate.getMonth() + 1; // 获取当前月份(0-11,0代表1月)
		var day_zsh = myDate.getDate(); // 获取当前日(1-31)
		var hours_zsh = myDate.getHours(); // 获取当前小时数(0-23)
		var minutes_zsh = myDate.getMinutes(); // 获取当前分钟数(0-59)
		var seconds_zsh = myDate.getSeconds(); // 获取当前秒数(0-59)
		if (seconds_zsh < 10) {
			seconds_zsh = "0" + seconds_zsh;
		}
		var newDate_zsh = year_zsh + "-" + month_zsh + "-" + day_zsh + " " + hours_zsh + ":" + minutes_zsh + ":" +
		seconds_zsh;
		$("#newDate_zsh").html(newDate_zsh);
	</script>
	<script>
	var totalPrice = 0;
	var resDataMoney = 0; // 邮费
	var totalPriceText = $('.total-price').find('.text');
	var subtotalPriceText = $('.cal-price-item.c-subtotal').find('.cal-price-num');
	var prototalPriceText = $('.cal-price-item.c-prototal').find('.cal-price-num');
	var shippingPriceText = $('.cal-price-item.c-shipping').find('.cal-price-num');
	var couponPriceText = $('.cal-price-item.c-coupon').find('.cal-price-num');
	var couponId;
	var couponCode = '';
	var addressId;
	var orderId;
	var orderItemArr = [];
	var productNumArr = [];
	var payplate = 0;
	var addressIdIntInt;
			$("#country").bind("change",function(){
				var radio_zt =$(".coupons .coupon-item input[type='radio']");
				 couponPriceText.text('-$' + 0);
				   if(radio_zt.is(":checked")){
					  radio_zt.removeAttr("checked");
					 couponPriceText.text('-$' + 0);
				   }
				   $(".coed_inp").val("");
				   $(".without-data").text("Enter coupon code to get a discount!");
				 var dataname = $(this).val();
				 $.ajax({
					  url: '${APP_PATH}/MlfrontAddress/getAreafreightMoney',
					  data: JSON.stringify({
						"addressCountry": dataname
					  }),
					  type: 'post',
					  dataType: 'text',
					  contentType: 'application/json',
					  success: function (data) {
						// console.log(data)
						var resData = JSON.parse(data);
						// console.log(resData)
						var resareafreightMoney = resData.extend.areafreightMoney;
						// console.log(resareafreightMoney)
						// console.log("resareafreightMoney:"+resareafreightMoney)
						$('.shipping').find('span').text(dataname + ' of $' + resareafreightMoney);
						shippingPriceText.text('$' + resareafreightMoney)
						
						totalPrice = (parseFloat(totalPrice) - resDataMoney).toFixed(2);
						resDataMoney = resareafreightMoney;
			
						totalPrice = (parseFloat(totalPrice) + resDataMoney).toFixed(2);
						
						subtotalPriceText.text('$' + totalPrice);
						// subtotalPriceText.text('$' + (totalPrice - resData.couponPrice).toFixed(2));
						
					  }
					});
			});
	
		function renderAddressDetail(data) {
			
			$("input.firstname").val(data.addressUserfirstname ? data.addressUserfirstname : '');
			$("input.lastname").val(data.addressUserlastname ? data.addressUserlastname : '');
			$("input.email").val(data.addressEmail ? data.addressEmail : '');
			$("input.phone").val(data.addressTelephone ? data.addressTelephone : '');
			$("input.address").val(data.addressDetail ? data.addressDetail : '');
			$("input.code").val(data.addressPost ? data.addressPost : '');
			$("input.city").val(data.addressCity ? data.addressCity : '');
			$("input.province").val(data.addressProvince ? data.addressProvince : '');
		    //$("#country option:checked").text(data.addressCountry ? data.addressCountry : ''); 
		    // $("select option:checked").text(data.addressCountry ? data.addressCountry : '');
		    $("#country").val(data.addressCountry ? data.addressCountry : '');

		}
	
		function renderAddressAdd(parent) {
			parent.html(
				'<div class="add-address address-trigger" style="display:none"><!--*<i class="icon plus"></i>*--><b> Add address consignee information</b></div>');
		}
		/* 初始化地址模块 */
		/* 初始化地址模块 */
		$.ajax({
			url: '${APP_PATH}/MlfrontAddress/getOneMlfrontAddressDetailByUinfo',
			type: 'post',
			success: function (data) {
				// console.log(data)
				var resDataAddress = data.extend.mlfrontAddressOne;
				// console.log(resDataAddress)
				var resDataUserType = data.extend.usertype;
				addressId = resDataAddress ? resDataAddress.addressId : null;
				resDataMoney = data.extend.areafreightMoney;
				// console.log(resDataMoney)
				var addressBox = $('.address');
				var couponBox = $('.coupons');
				// console.log(data)
				renderCoupons(couponBox, resDataUserType);
				if (resDataAddress) {
					renderAddressDetail(resDataAddress);
					$('.address-id').val(resDataAddress.addressId);
					$('.shipping').find('span').text(resDataAddress.addressCountry + ' of $' + resDataMoney);
					shippingPriceText.text('$' + resDataMoney)
					$(".address").addClass("active")
				} else {
					// renderAddressAdd(addressBox);
					$('.shipping').find('span').text('United States'+ ' of $' + resDataMoney);
					if($("#country").val="United States"){
						shippingPriceText.text('$' + resDataMoney)
					}
				}
				var subtotalText = (parseFloat(resDataMoney) + parseFloat(totalPrice)).toFixed(2);
				subtotalPriceText.text(subtotalText);
				
			}
		});
		
				function savr_address(){
					//var returnaddressId;
					var formData = $('.address-box form').serializeArray();
					var reqData = formData.reduce(function (obj, item) {
						obj[item.name] = item.value;
						return obj
					}, {});
					//if (!inputCheck(reqData)) return;
					// console.log("************")
					// console.log(reqData)
					reqData.addressId = reqData.addressId === '' ? null : parseInt(reqData.addressId);
					$.ajax({
						url: '${APP_PATH}/MlfrontAddress/save',
						type: 'post',
						dataType: 'text',
						data: JSON.stringify(reqData),
						contentType: 'application/json',
						success: function (data) {
							 // console.log(data)
							var resDataAddress = JSON.parse(data).extend.mlfrontAddress;
							 // var resDataAddress = data.extend.mlfrontAddress;
							 // console.log(resDataAddress)
							addressId = resDataAddress.addressId;
							addressIdIntInt = resDataAddress.addressId;
							returnaddressId = addressIdIntInt;
							// console.log("addressIdIntInt:"+addressIdIntInt);
							var addressBox = $('.address');
							$('.address-id').val(resDataAddress.addressId);
						}
					})
					//return returnaddressId;
				}
// 		$('.left_list_check .save').on('click', function () {
// 			$(this).toggleClass("active")
// 			var formData = $('.address-box form').serializeArray();
// 			var reqData = formData.reduce(function (obj, item) {
// 				obj[item.name] = item.value;
// 				return obj
// 			}, {});
// 			if (!inputCheck(reqData)) return;
// 			reqData.addressId = reqData.addressId === '' ? null : parseInt(reqData.addressId);
// 				 if($(this).hasClass("active")){
// 				$(".left_list_check .form-input").each(function(){
// 						$(this).find("input").attr("disabled","disabled");
// 						$(this).find("select").attr("disabled","disabled");
// 					})
// 				}else{
// 					$(".left_list_check .form-input").each(function(){
// 						$(this).find("input").removeAttr("disabled");
// 						$(this).find("select").removeAttr("disabled");
// 					})
// 					$('.shipping').find('span').text(resDataAddress.addressCountry + ' of $' + resDataMoney);
// 					totalPriceText.text('$' + totalPrice);
// 				}
// 			
// 			//console.log(reqData)
// 			$.ajax({
// 				url: '${APP_PATH}/MlfrontAddress/save',
// 				type: 'post',
// 				dataType: 'text',
// 				data: JSON.stringify(reqData),
// 				contentType: 'application/json',
// 				success: function (data) {
// 					// console.log(data)
// 					var resDataAddress = JSON.parse(data).extend.mlfrontAddress;
// 					addressId = resDataAddress.addressId;
// 					// console.log(addressId)
// 					totalPrice -= resDataMoney;
// 					resDataMoney = JSON.parse(data).extend.areafreightMoney;
// 					totalPrice += resDataMoney;
// 					var addressBox = $('.address');
// 					$('.address-id').val(resDataAddress.addressId);
// 
// 					// console.log(resDataMoney)
// 					$('.shipping').find('span').text(resDataAddress.addressCountry + ' of $' + resDataMoney);
// 					totalPriceText.text('$' + totalPrice);
// 					
// 					shippingPriceText.text('$' + resDataMoney);
// 					subtotalPriceText.text('$' + totalPrice);
// 					renderAddressDetail(addressBox, reqData);
// 				}
// 			})
// 		});
		/* 所购商品列表 */
		/* 所购商品列表 */
		function renderCartList(parent, data) {
			var html = '';
			// console.log(data)
			for (var i = 0, len = data.length; i < len; i += 1) {
				orderItemArr.push(data[i].orderitemId);
				productNumArr.push(data[i].orderitemPskuNumber);
				html += '<div class="cart-item bd-b" data-orderitemid="' + data[i].orderitemId + '" onclick="toProductItem(' +
					data[i].orderitemPid + ')">' +
					'<img class="img" src="' + data[i].orderitemProductMainimgurl + '" alt="">' +
					'<div class="content">' +
					'<div class="text">' +
					'<div class="title">' + data[i].orderitemPname + '</div>' +
					'<div class="condition">';
				var skuIdNameArr = data[i].orderitemPskuIdnamestr.split(',');
				var skuNameArr = data[i].orderitemPskuNamestr.split(',');
				var skuMoneyArr = data[i].orderitemPskuMoneystr.split(',');
				var len2 = skuNameArr.length;
				for (var j = 0; j < len2; j += 1) {
					html += '<span class="c-item">' + skuIdNameArr[j] + ': ' + skuNameArr[j] + '</span>'
				}
				html += '</div>' +
					'</div>' +
					'<div class="num">' +
					'<span class="price">' + (getPrice(data[i].orderitemProductOriginalprice, skuMoneyArr, data[i]
						.orderitemProductAccoff).current) + '</span>' +
					'<span class="original">' + (getPrice(data[i].orderitemProductOriginalprice, skuMoneyArr, data[i]
						.orderitemProductAccoff).origin) + '</span>' +
					'<span class="p-num">X' + data[i].orderitemPskuNumber + '</span>' +
					'</div>' +
					'</div>' +
					'</div>';
			}
			parent.html(html)
		}
		$.ajax({
			url: '${APP_PATH}/MlfrontOrder/tomOrderDetailOne',
			type: 'get',
			success: function (data) {
				var resData = data.extend.mlfrontOrderItemList;
				// console.log(resData);/*************************dfgdfgdfgdfg***************dfgdfgdf*/
				orderId = resData[0].orderId || null;
				var cartList = $('.cart-list');
				cartList.attr('data-id', resData.orderId);
				renderCartList(cartList, resData)
				// console.log(typeof totalPrice)
				var allPriceObj = calAllProductPrice(resData);
				totalPrice = allPriceObj.allSubtotalPrice + resDataMoney;
				totalPriceText.text('$' + totalPrice);
				prototalPriceText.text('$' + (allPriceObj.allSubtotalPrice));
				subtotalPriceText.text('$' + totalPrice);
			}
		})
		/* all */
		function calAllProductPrice(data) {
			var len = data.length;
			var allSubtotalPrice = 0;
			var allOriginPrice = 0;
			if (len === 0) {
				return 0;
			}
			// console.log(data);
			for (var i = 0; i < len; i += 1) {
				var singPrice = 0;
				var originalPrice = parseFloat(data[i].orderitemProductOriginalprice);
				var discount = parseFloat(data[i].orderitemProductAccoff) / 100;
				var skuPriceArr = data[i].orderitemPskuMoneystr.split(',');
				singlePrice = skuPriceArr.reduce(function (price, item) {
					return (price + parseFloat(item))
				}, originalPrice);
				// console.log(data[i])
				var singleNumPrice = singlePrice * data[i].orderitemPskuNumber;
				allOriginPrice = parseFloat((allOriginPrice + singleNumPrice).toFixed(2));
				allSubtotalPrice = parseFloat((allSubtotalPrice + singleNumPrice * discount).toFixed(2));
			}
			return {
				allOriginPrice: allOriginPrice,
				allSubtotalPrice: allSubtotalPrice
			};
		}
		/* single */
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
			}
		}
		/* 优惠券 
		 * 
		//MlbackCoupon/getOneMlbackCouponDetailByCode
		 字段，String couponCode
		 */
		var counponDataList = {};
			function renderCoupons(parent, userType, data) {
				var html = '';
				if (userType === 0) {
					html = '<div class="input-group">' +
						'<input type="text" name="productNum" class="form-control coed_inp" value="" placeholder="Please enter coupon code">' +
						'<span class="input-group-addon" id="coupon-check" onclick="checkCouponCode(event)">check it</span>' +
						'</div><div class="coupon-error"><p class="without-data">Enter coupon code to get a discount!</p></div>';
				}
				/* MlbackCoupon/getOneMlbackCouponDetailByUId
				无参数  post */
				if (userType === 1) {
					$.ajax({
						url: '${APP_PATH}/MlbackCoupon/getOneMlbackCouponDetailByUId',
						type: 'post',
						dataType: 'text',
						async: false,
						success: function (data) {
							// console.log(data);
							var resData = JSON.parse(data).extend.mlbackCouponReturnList;
							var len = resData.length
							for (var i = 0; i < len; i += 1) {
								counponDataList[resData[i].couponId] = resData[i];
								html += '<div class="coupon-item">' +
									'<input type="radio" name="coupon" data-couponid="' + resData[i].couponId +
									'" onclick="selectCoupon(event)" class="checkbox">' +
									'<span class="price">$' + resData[i].couponPrice + '</span>' +
									'<span class="name">' + resData[i].couponName + '</span>' +
									
									'</div>';
							}
						}
					});
				}
				parent.html(html);
			}
		var couponPrice = 0;
		function selectCoupon(e) {
			// console.log(counponDataList)
			var targetEl = $(e.target);
			var id = targetEl.data('couponid');
			var priceInfo = targetEl.parent().parent().parent().find('.price-info');
			if (totalPrice >= counponDataList[id].couponPriceBaseline) {
				// console.log(totalPrice, resData.couponPrice)
				var couponPrice = counponDataList[id].couponPrice;
				priceInfo.text('-$' + couponPrice);
				totalPriceText.text('$' + (totalPrice - couponPrice).toFixed(2));
				
				couponPriceText.text('-$' + couponPrice);
				subtotalPriceText.text('$' + (totalPrice - couponPrice).toFixed(2));
				couponCode = counponDataList[id].couponCode;
				couponId = counponDataList[id].couponId;
			} else {
				targetEl[0].checked = false;   
				priceInfo.text("Cann't use this Coupon!");
				totalPriceText.text('$' + (totalPrice).toFixed(2));
				
				couponPriceText.text('-$' + 0);
				subtotalPriceText.text('$' + (totalPrice).toFixed(2));
			}
		}
		function selectPay(e) {
			var targetEl = $(e.target);
			payplate = targetEl.data('payid');
		}
		function checkCouponCode(event) {
			var couponCode2 = $(event.target).prev('input').val();
			var data = {
				couponCode: couponCode2
			}; // MEGA12	couponCode	
			$.ajax({
				url: '${APP_PATH}/MlbackCoupon/getOneMlbackCouponDetailByCode',
				data: JSON.stringify({
					"couponCode": couponCode2
				}),
				type: 'post',
				dataType: 'text',
				contentType: 'application/json',
				success: function (data) {
					var resData = JSON.parse(data).extend.mlbackCouponOne;
					var couponErrorBox = $('.coupon-error');
					// console.log(resData);
					if (resData) {
						// console.log(totalPrice, totalPrice - resData.couponPriceBaseline)
						if (totalPrice >= resData.couponPriceBaseline) {
							// console.log(totalPrice, resData.couponPrice)
							totalPriceText.text('$' + (totalPrice - resData.couponPrice).toFixed(2));
							
							couponPriceText.text('-$' + resData.couponPrice);
							subtotalPriceText.text('$' + (totalPrice - resData.couponPrice).toFixed(2));
							couponId = resData.couponId;
							couponCode = couponCode2;
							renderErrorMsg(couponErrorBox, resData.couponName + '，Has been used!')
						} else {
							renderErrorMsg(couponErrorBox, resData.couponName + '，Unusable!')
						}
					} else {
						renderErrorMsg(couponErrorBox, "Coupons don't exist!");
					}
				}
			})
		}
		//MlfrontOrder/orderToPayInfo
		//这5个参数，json格式
		/* 
			private Integer orderId;  //1  都一样，随便从一条取出就行了
		    private String orderOrderitemidstr;//1 每条的orderitemId都不一样，需要拼成字段"77,78"中间逗号拼接。
		    private Integer orderCouponId  // 1  优惠码，就一个
		    private String orderCouponCode; //1，每条的产品数量，需要拼成字段"1,1"中间逗号拼接。
		    private Integer addressinfoId;//1	地址id 就一处
		 */
		$('.place-order').on('click', function () {
			
			// var val=$('input:radio[name="sex"]:checked').val();
			// var no_check_copn =radio_zt.
			if (inputCheck9()==1){
				return ;
			} else{
				
				savr_address();  // addres 保存
				var addressIdInt = $('.address-id').val();
			
				var reqData = {
					"orderId": orderId,
					"orderOrderitemidstr": orderItemArr.join(','),
					"orderCouponId": couponId,
					"orderCouponCode": (couponCode ? couponCode : null), //传递真的code码
					"orderPayPlate": payplate, //选择的付款方式,int类型   paypal传0，后来再有信用卡传1
					"orderProNumStr": productNumArr.join(','), //就这样,,zheli你传给我了，但是我接到之后，再处理的话，要同时动4张表。。所以，能早处理早处理。早处理的话，就动一张
					"orderBuyMess": $('.customer-message textarea').val(), //买家的留言
					"addressinfoId": addressIdInt,
				};
				
				var reqDataUp = {
						"orderId": orderId,
						"orderOrderitemidstr": orderItemArr.join(','),
						"orderCouponId": couponId,
						"orderCouponCode": (couponCode ? couponCode : null), //传递真的code码
						"orderPayPlate": payplate, //选择的付款方式,int类型   paypal传0，后来再有信用卡传1
						"orderProNumStr": productNumArr.join(','), //就这样,,zheli你传给我了，但是我接到之后，再处理的话，要同时动4张表。。所以，能早处理早处理。早处理的话，就动一张
						"orderBuyMess": $('.customer-message textarea').val(), //买家的留言
						"addressinfoId": addressIdInt,
					};
			
				// console.log(reqData)
			    // console.log(reqDataUp)
				// console.log(checkAddress(reqDataUp))
				if (checkAddress(reqDataUp)) {
					fbq('track', 'AddPaymentInfo');//追踪'发起结账'事件  facebook广告插件可以注释掉，但不要删除
					$.ajax({
						url: '${APP_PATH}/MlfrontOrder/orderToPayInfo',
						data: JSON.stringify(reqData),
						type: 'post',
						dataType: 'text',
						contentType: 'application/json',
						success: function (data) {
							var resData = JSON.parse(data).extend;
							// console.log(data)
							window.location.href = '${APP_PATH }/paypal/ppay';
						}
					})
				} else {
					renderSysMsg('Please fill in the shipping address ')
				 }
			}
			
		})
		function checkAddress(reqDataUp) {
			var flag = false;
			$.ajax({
				url: '${APP_PATH}/MlfrontAddress/getOneMlfrontAddressDetailByUinfo',
				type: 'post',
				async: false,
				success: function (data) {
					// console.log("/MlfrontAddress/getOneMlfrontAddressDetailByUinfo");
					// console.log(data)
					var resData = data.extend;
					//console.log(resData.mlfrontAddressOne)
					if (resData.mlfrontAddressOne) {
						flag = true;
					} else {
						falg = false;						
						var addressinfoIdss = reqDataUp.addressinfoId;
						// console.log("addressinfoIdss:"+addressinfoIdss);
						if(addressinfoIdss!=null){
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
					if (key === 'addressEmail') {
						var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
						if (!pattern.test(data[key])) {
							flag = !flag;
							renderSysMsg('Email address format is incorrect');
							break;
						}
					} else if (key === "addressId") {
						continue;
					} else {
						if (data[key].trim().length < 1) {
							flag = !flag;
							$(".save").removeClass("active")
							renderSysMsg('Required fields with an asterisk cannot be empty. Please check before submitting');
							break;
							
						}
					}
				}
				return flag;
			}
					function inputCheck9() {
				
				var flag = 0;
				var firstnamestr = $(".firstname").val();
				// console.log("firstnamestr:"+firstnamestr);
				var lastnamestr = $(".lastname").val();
				// console.log("lastnamestr:"+lastnamestr);
				var emailstr = $(".email").val();
				var phonestr = $(".phone").val();
				var addressstr = $(".addreNo").val();
				var codestr = $(".code").val();
				var citystr = $(".city").val();
				// var countrystr = $("#country").val();
				var provincestr = $(".province").val();
				var country_address = $("#country option:checked").text(); 
				// var radio_zt_copn=$(".coupons .coupon-item input[type='radio']").val();
				// if(radio_zt_copn==null||radio_zt_copn==''){
				// 	flag = 1;
				// 	alert("优惠券未使用")
				// }
				if(firstnamestr==null||firstnamestr==''){
					flag = 1;
					alert("firstnamestr is empty");
					$(".firstname").addClass("error_br");
					$(".firstname").focus(function(){
						$(this).removeClass("error_br")
					})
				}else if(lastnamestr==null||lastnamestr==''){
					flag = 1;
					alert("lastnamestr is empty");
					$(".lastname").addClass("error_br");
					$(".lastname").focus(function(){
						$(this).removeClass("error_br")
					})
				}else if(emailstr==null||emailstr==''){
					flag = 1;
					alert("emailstr is empty");
					$(".email").addClass("error_br");
					$(".email").focus(function(){
						$(this).removeClass("error_br")
					})
				}else if(phonestr==null||phonestr==''){
					flag = 1;
					alert("phonestr is empty");
					$(".phone").addClass("error_br");
					$(".phone").focus(function(){
						$(this).removeClass("error_br")
					})
				}else if(addressstr==null||addressstr==''){
					flag = 1;
					alert("addressstr is empty");
					$(".addressstr").addClass("error_br");
					$(".addressstr").focus(function(){
						$(this).removeClass("error_br")
					})
				}else if(codestr==null||codestr==''){
					flag = 1;
					alert("codestr is empty");
					$(".codestr").addClass("error_br");
					$(".codestr").focus(function(){
						$(this).removeClass("error_br")
					})
				}else if(country_address==null||country_address==''||country_address=='select the Country'){
					flag = 1;
					alert("countrystr is empty");
					$("#country").addClass("error_br");
					$("#country").focus(function(){
						$(this).removeClass("error_br")
					})
				}else if(provincestr==null||provincestr==''){
					flag = 1;
					alert("provincestr is empty");
					$(".province").addClass("error_br");
					$(".province").focus(function(){
						$(this).removeClass("error_br")
					})
				}
				
				return flag;
				
					
					
				
			}
			
	</script>
	<script src="//code.tidio.co/0rpdotjoqewxstfjahkd1ajtxrcp8phh.js"></script>
</body>

</html>