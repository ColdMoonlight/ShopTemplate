<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>MEGALOOK</title>
	<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
	<link rel="shortcut icon" href="${APP_PATH }/static/pc/img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${APP_PATH }/static/pc/css/theme.css">
	<link rel="stylesheet" href="${APP_PATH }/static/pc/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/static/pc/css/new.css">
</head>

<body>
	<header>
		<!-- tt-desktop-header -->
		<div class="tt-desktop-header">
			<!-- account tip language -->
			<div class="tt-status">
				<div class="container">
					<!-- tt-langue and tt-currency -->
					<div class="tt-desctop-parent-multi tt-parent-box">
						<div class="tt-multi-obj tt-dropdown-obj">
							<button class="tt-dropdown-toggle" data-tooltip="Settings" data-tposition="bottom"><i
									class="icon-f-79"></i></button>
							<div class="tt-dropdown-menu">
								<div class="tt-mobile-add">
									<button class="tt-close">Close</button>
								</div>
								<div class="tt-dropdown-inner">
									<ul>
										<li class="active"><a href="#">English</a></li>
										<li><a href="#">Deutsch</a></li>
										<li><a href="#">Español</a></li>
										<li><a href="#">Français</a></li>
									</ul>
									<ul>
										<li class="active"><a href="#"><i class="icon-h-59"></i>USD - US Dollar</a></li>
										<li><a href="#"><i class="icon-h-60"></i>EUR - Euro</a></li>
										<li><a href="#"><i class="icon-h-61"></i>GBP - British Pound Sterling</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- /tt-langue and tt-currency -->
					<!-- tt-tip -->
					<div class="tt-tip">Free Shipping World Wide.</div>
					<!-- /tt-tip -->
					<!-- tt-account -->
					<div class="tt-desctop-parent-account tt-parent-box">
						<div class="tt-account tt-dropdown-obj">
							<button class="tt-dropdown-toggle" data-tooltip="My Account" data-tposition="bottom">
								<i class="icon-f-94"></i>
							</button>
							<div class="tt-dropdown-menu">
								<div class="tt-mobile-add">
									<button class="tt-close">Close</button>
								</div>
								<div class="tt-dropdown-inner">
									<ul>
										<li><a href="login.html"><i class="icon-f-94"></i>Account</a></li>
										<li><a href="wishlist.html"><i class="icon-n-072"></i>Wishlist</a></li>
										<li><a href="compare.html"><i class="icon-n-08"></i>Compare</a></li>
										<li><a href="page404.html"><i class="icon-f-68"></i>Check Out</a></li>
										<li><a href="login.html"><i class="icon-f-76"></i>Sign In</a></li>
										<li><a href="page404.html"><i class="icon-f-77"></i>Sign Out</a></li>
										<li><a href="create-account.html"><i class="icon-f-94"></i>Register</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- /tt-account -->
				</div>
			</div>
			<div class="container">
				<!-- search logo cart -->
				<div class="tt-header-holder">
					<!-- tt-search -->
					<div class="tt-desctop-parent-search tt-parent-box">
						<div class="tt-search tt-dropdown-obj">
							<button class="tt-dropdown-toggle" data-tooltip="Search" data-tposition="bottom">
								<i class="icon-f-85"></i>
							</button>
							<div class="tt-dropdown-menu">
								<div class="container">
									<form>
										<div class="tt-col">
											<input type="text" class="tt-search-input" placeholder="Search Products...">
											<button class="tt-btn-search" type="submit"></button>
										</div>
										<div class="tt-col">
											<button class="tt-btn-close icon-g-80"></button>
										</div>
										<div class="tt-info-text">
											What are you Looking for?
										</div>
										<div class="search-results">
											<ul>
												<li>
													<a href="product.html">
														<div class="thumbnail"><img src="images/loader.svg" data-src="images/product/product-03.jpg"
																alt=""></div>
														<div class="tt-description">
															<div class="tt-title">Flared Shift Bag</div>
															<div class="tt-price">
																<span class="new-price">$14</span>
																<span class="old-price">$24</span>
															</div>
														</div>
													</a>
												</li>
												<li>
													<a href="product.html">
														<div class="thumbnail"><img src="images/loader.svg" data-src="images/product/product-02.jpg"
																alt=""></div>
														<div class="tt-description">
															<div class="tt-title">Flared Shift Bag</div>
															<div class="tt-price">
																$24
															</div>
														</div>
													</a>
												</li>
												<li>
													<a href="product.html">
														<div class="thumbnail"><img src="images/loader.svg" data-src="images/product/product-01.jpg"
																alt=""></div>
														<div class="tt-description">
															<div class="tt-title">Flared Shift Bag</div>
															<div class="tt-price">
																$14
															</div>
														</div>
													</a>
												</li>
												<li>
													<a href="product.html">
														<div class="thumbnail"><img src="images/loader.svg" data-src="images/product/product-04.jpg"
																alt=""></div>
														<div class="tt-description">
															<div class="tt-title">Flared Shift Bag</div>
															<div class="tt-price">
																$24
															</div>
														</div>
													</a>
												</li>
												<li>
													<a href="product.html">
														<div class="thumbnail"><img src="images/loader.svg" data-src="images/product/product-05.jpg"
																alt=""></div>
														<div class="tt-description">
															<div class="tt-title">Flared Shift Bag</div>
															<div class="tt-price">
																$17
															</div>
														</div>
													</a>
												</li>
												<li>
													<a href="product.html">
														<div class="thumbnail"><img src="images/loader.svg" data-src="images/product/product-06.jpg"
																alt=""></div>
														<div class="tt-description">
															<div class="tt-title">Flared Shift Bag</div>
															<div class="tt-price">
																$20
															</div>
														</div>
													</a>
												</li>
											</ul>
											<button type="button" class="tt-view-all">View all products</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- /tt-search -->
					<!-- tt-logo -->
					<a href="${APP_PATH}/index/isMobileOrPc">
					<div class="tt-logo">
						   <div class="logo">
							 <img src="${APP_PATH }/static/m/img/index/logo.png" alt="">
						   </div>
						<!-- <img src="${APP_PATH }/static/m/img/index/logo.png" alt="megalook"> -->
					</div>
					 </a>
					<!-- /tt-logo -->
					<!-- tt-cart -->
					<div class="tt-desctop-parent-cart tt-parent-box">
						<div class="tt-cart tt-dropdown-obj" data-tooltip="Cart" data-tposition="bottom">
							<button class="tt-dropdown-toggle">
								<i class="icon-f-39"></i>
								<span class="tt-badge-cart">3</span>
							</button>
							<div class="tt-dropdown-menu">
								<div class="tt-mobile-add">
									<h6 class="tt-title">SHOPPING CART</h6>
									<button class="tt-close">Close</button>
								</div>
								<div class="tt-dropdown-inner">
									<div class="tt-cart-layout">
										<!-- layout emty cart -->
										<div class="tt-cart-content">
											<div class="tt-cart-list">
												<div class="tt-item">
													<a href="product.html">
														<div class="tt-item-img">
															<img src="images/loader.svg" data-src="images/product/product-01.jpg" alt="">
														</div>
														<div class="tt-item-descriptions">
															<h2 class="tt-title">Flared Shift Dress</h2>
															<ul class="tt-add-info">
																<li>Yellow, Material 2, Size 58,</li>
																<li>Vendor: Addidas</li>
															</ul>
															<div class="tt-quantity">1 X</div>
															<div class="tt-price">$12</div>
														</div>
													</a>
													<div class="tt-item-close">
														<a href="#" class="tt-btn-close"></a>
													</div>
												</div>
												<div class="tt-item">
													<a href="product.html">
														<div class="tt-item-img">
															<img src="images/loader.svg" data-src="images/product/product-02.jpg" alt="">
														</div>
														<div class="tt-item-descriptions">
															<h2 class="tt-title">Flared Shift Dress</h2>
															<ul class="tt-add-info">
																<li>Yellow, Material 2, Size 58,</li>
																<li>Vendor: Addidas</li>
															</ul>
															<div class="tt-quantity">1 X</div>
															<div class="tt-price">$18</div>
														</div>
													</a>
													<div class="tt-item-close">
														<a href="#" class="tt-btn-close"></a>
													</div>
												</div>
											</div>
											<div class="tt-cart-total-row">
												<div class="tt-cart-total-title">SUBTOTAL:</div>
												<div class="tt-cart-total-price">$324</div>
											</div>
											<div class="tt-cart-btn">
												<div class="tt-item">
													<a href="#" class="btn">PROCEED TO CHECKOUT</a>
												</div>
												<div class="tt-item">
													<a href="shopping_cart_02.html" class="btn-link-02 tt-hidden-mobile">View Cart</a>
													<a href="shopping_cart_02.html" class="btn btn-border tt-hidden-desctope">VIEW CART</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /tt-cart -->
				</div>

				<!-- tt-menu -->
				<div class="tt-desctop-parent-menu tt-parent-box">
					<div class="tt-desctop-menu">
						<nav>
							<ul>
								<!-- <li class="dropdown megamenu">
									<a href="listing-left-column.html">WOMEN</a>
									<div class="dropdown-menu">
										<div class="row">
											<div class="left col-sm-9">
												<div class="row tt-col-list">
													<div class="col-sm-3">
														<a class="tt-title-submenu" href="listing-left-column.html"> TOPS </a>
														<ul class="tt-megamenu-submenu">
															<li><a href="listing-left-column.html">Blouses &amp; Shirts</a></li>
															<li>
																<a href="listing-left-column.html">Dresses <span class="tt-badge tt-new">New</span></a></li>
															<li>
																<a href="listing-left-column.html">Tops &amp; T-shirts</a>
																<ul>
																	<li><a href="listing-left-column.html">Link Level 1</a></li>
																	<li>
																		<a href="listing-left-column.html">Link Level 1</a>
																		<ul>
																			<li><a href="listing-left-column.html">Link Level 2</a></li>
																			<li>
																				<a href="listing-left-column.html">Link Level 2</a>
																				<ul>
																					<li><a href="listing-left-column.html">Link Level 3</a></li>
																					<li><a href="listing-left-column.html">Link Level 3</a></li>
																					<li><a href="listing-left-column.html">Link Level 3</a></li>
																					<li>
																						<a href="listing-left-column.html">Link Level 3</a>
																						<ul>
																							<li>
																								<a href="listing-left-column.html">Link Level 4</a>
																								<ul>
																									<li><a href="listing-left-column.html">Link Level 5</a></li>
																									<li><a href="listing-left-column.html">Link Level 5</a></li>
																									<li><a href="listing-left-column.html">Link Level 5</a></li>
																									<li><a href="listing-left-column.html">Link Level 5</a></li>
																									<li><a href="listing-left-column.html">Link Level 5</a></li>
																								</ul>
																							</li>
																							<li><a href="listing-left-column.html">Link Level 4</a></li>
																						</ul>
																					</li>
																					<li><a href="listing-left-column.html">Link Level 3</a></li>
																				</ul>
																			</li>
																			<li><a href="listing-left-column.html">Link Level 2</a></li>
																			<li><a href="listing-left-column.html">Link Level 2</a></li>
																		</ul>
																	</li>
																	<li><a href="listing-left-column.html">Link Level 1</a></li>
																	<li><a href="listing-left-column.html">Link Level 1</a></li>
																	<li><a href="listing-left-column.html">Link Level 1</a></li>
																</ul>
															</li>
															<li><a href="listing-left-column.html">Sleeveless Tops</a></li>
															<li><a href="listing-left-column.html">Sweaters</a></li>
															<li><a href="listing-left-column.html">Jackets</a></li>
															<li><a href="listing-left-column.html">Outerwear</a></li>
														</ul>
													</div>
													<div class="col-sm-3">
														<a class="tt-title-submenu" href="listing-left-column.html">
															BOTTOMS
														</a>
														<ul class="tt-megamenu-submenu">
															<li><a href="listing-left-column.html">Trousers <span
																		class="tt-badge tt-fatured">Fatured</span></a></li>
															<li>
																<a href="listing-left-column.html">Jeans</a>
															</li>
															<li><a href="listing-left-column.html">Leggings</a></li>
															<li><a href="listing-left-column.html">Jumpsuit &amp; Shorts</a></li>
															<li><a href="listing-left-column.html">Skirts</a></li>
															<li><a href="listing-left-column.html">Tights</a></li>
														</ul>
													</div>
													<div class="col-sm-3">
														<a class="tt-title-submenu" href="listing-left-column.html">
															ACCESSORIES
														</a>
														<ul class="tt-megamenu-submenu">
															<li><a href="listing-left-column.html">Jewellery</a></li>
															<li><a href="listing-left-column.html">Hats</a></li>
															<li><a href="listing-left-column.html">Scarves &amp; Snoods</a></li>
															<li><a href="listing-left-column.html">Belts</a></li>
															<li><a href="listing-left-column.html">Bags</a></li>
															<li><a href="listing-left-column.html">Shoes <span class="tt-badge tt-sale">Sale
																		15%</span></a></li>
															<li><a href="listing-left-column.html">Sunglasses</a></li>
														</ul>
													</div>
												</div>
											</div>
											<div class="right col-sm-3">ssssssss</div>
										</div>
									</div>
								</li> -->
							</ul>
						</nav>
					</div>
				</div>
				<!-- /tt-menu -->
			</div>
		</div>
	</header>
	<script src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script>
		
		var categoryActiveNum = 1;
		var iCart = $('.icon.cart');
		var iPerson = $('.icon.person');
		var mask = $('<div class="mask"></div>');
		var sysFlag = 0;
		
		
		function renderErrorMsg(parent, msg) {
			parent.html('<p>' + msg + '</p>');
		}
			function renderSysMsg(msg) {
				var elBox = $('<div class="pcmodal sys-box" style="display: block;"></div>');
				
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


		function renderMainCategory(parent, data) {
			var html = '';
			for (var i in data) {
				html += '<li class="dropdown megamenu">' +
					'<a href="javascript:;" data-categoryid=' + data[i].categoryId + '>' + data[i].categoryName + '</a>' +
					'<div class="dropdown-menu">' +
					'<div class="row">' +
					'<div class="left col-sm-9">';

				if (data[i].list && data[i].list.length) {
					html += '<ul class="tt-megamenu-submenu">';
					for (var j = 0; j < data[i].list.length; j += 1) {
						html += '<li class="col-sm-6 listfl6">' +
						'<a href="${APP_PATH}/MlbackCategory/topcproductlist?categoryId='+ data[i].list[j].categoryId +'">' +
								'<img src="'+ data[i].list[j].categoryImgurl +'" />' +
								'<span>'+ data[i].list[j].categoryName +'</span>' + 
						'</a></li>';
					}
					html += '</ul>';
				} else {
					html += '<div>相关分类产品未上架</div>';
				}

				html += '</div>' +
					'<div class="left col-sm-3">' +
					'<img src="' + data[i].categoryImgurl + '" alt="">' +
					'</div>' +
					'</div>';
			}
			parent.html(html);
		}

		var mainCategory = $('.tt-desctop-menu ul');
		var categoryData = {};
		// 获取全部的category信息,文件信息
		$.ajax({
			url: '${APP_PATH}/MlbackCategory/getOneMlbackCategoryParentDetail',
			method: 'GET',
			success: function (data) {
				var resData = data.extend.mlbackCategorydownList;
				console.log(resData);
				if (data.code === 100) {
					categoryData = resData.reduce(function (obj, item) {
						if (item.categoryParentId === -1) {
							item.list = obj.hasOwnProperty(item.categoryId) && obj[item.categoryId].hasOwnProperty('list') ?
								obj[item.categoryId].list : [];
							obj[item.categoryId] = item;
							return obj;
						} else {
							if (!obj[item.categoryParentId]) {
								obj[item.categoryParentId] = {}
							}
							if (!obj[item.categoryParentId].list) {
								obj[item.categoryParentId].list = []
							}
							obj[item.categoryParentId].list.push(item);
							return obj;
						}
					}, {});
					//console.log(categoryData)
					renderMainCategory(mainCategory, categoryData);
				} else {
					renderErrorMsg(prodcutBox, '未获取到目录相关的数据');
				}
			}
		})
		
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
		  window.location.href = "${APP_PATH }/MlbackCart/toCartList";
		})
		
		
		iPerson.on('click', function () {
		  if ($(this).hasClass('active')) {
		    window.location.href = "${APP_PATH }/MlfrontUser/toUserCenter";
		  } else {
		    window.location.href = "${APP_PATH }/MlfrontUser/toLoginRegisterPage";
		  }
		})
		
		/* check user is login or not  */
		
		
	</script>
</body>

</html>