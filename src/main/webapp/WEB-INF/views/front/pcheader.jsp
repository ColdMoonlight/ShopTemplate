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
						<div class="tt-multi-obj tt-dropdown-obj" style="display: none;">
							<button class="tt-dropdown-toggle " data-tooltip="Settings" data-tposition="bottom"><i
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
					<div class="tt-desctop-parent-account tt-parent-box person">
						<div class="tt-account tt-dropdown-obj">
							<button class="tt-dropdown-toggle" data-tooltip="My Account" data-tposition="bottom">
								<i class="icon-f-94"></i>
							</button>
							<div class="tt-dropdown-menu">
								<div class="tt-mobile-add">
									<button class="tt-close">Close</button>
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
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- /tt-search -->
					<!-- tt-logo -->
					<a href="${APP_PATH}/index.html">
					<div class="tt-logo">
						   <div class="logo">
							 <img src="${APP_PATH }/static/m/img/index/logo.png" alt="">
						   </div>
					</div>
					 </a>
					<!-- /tt-logo -->
					<!-- tt-cart -->
					<div class="tt-desctop-parent-cart tt-parent-box">
						<div class="tt-cart tt-dropdown-obj" data-tooltip="Cart" data-tposition="bottom" style="display: none;">
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
							</ul>
						</nav>
					</div>
				</div>
				<!-- /tt-menu -->
			</div>
		</div>
	</header>
	<div class="fix_iconnav">
		<ul class="icon_nav">
			<li id="cart_icon" class="cart_icon"><a href="###"></a><span class="cart_num">0</span></li>
			<li class="go_top"><a href="###"></a></li>
		</ul>
	</div>
	
	
	<script src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<script src="${APP_PATH }/static/pc/js/jquery.fly.min.js"></script>
	<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script>
       $(function(){
		   		$(".go_top").click(function(){
					$("body,html").animate({scrollTop:0},500);  
					   return false;
				   });
			   $(window).scroll(function(){
				var winS=$(this).scrollTop();
				if(winS>0){
					$(".go_top").show();
				}else{
					$(".go_top").hide();
				}
			   });
			   /***************************************/
	   })
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

		function renderMainCategory(parent, data) {
			
			var html = '';
			for (var i in data) {
				var categoryLableInt = data[i].categoryLable;
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
				}
				html += '<li class="dropdown megamenu '+classimg+'">' +
					'<a href="javascript:;" data-categoryid=' + data[i].categoryId + '>' + data[i].categoryName + '</a>' +
					'<div class="dropdown-menu">' +
					'<div class="row">' +
					'<div class="left col-sm-9">';

				if (data[i].list && data[i].list.length) {
					html += '<ul class="tt-megamenu-submenu">';
					for (var j = 0; j < data[i].list.length; j += 1) {
						html += '<li class="col-sm-6 listfl6">' +
						'<a href="${APP_PATH}/MlbackCategory/toproductlist?categoryId='+ data[i].list[j].categoryId +'">' +
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
			var firstli =$(".dropdown").eq(0);
			firstli.find(".dropdown-menu").addClass("noshow")
			firstli.click(function(){
				 window.location.href = "${APP_PATH }/Category/49.html";
			})
			
			
		}
		var mainCategory = $('.tt-desctop-menu ul');
		var categoryData = {};
		// 获取全部的category信息,文件信息
		$.ajax({
			url: '${APP_PATH}/MlbackCategory/getOneMlbackCategoryParentDetail',
			method: 'GET',
			success: function (data) {
				var resData = data.extend.mlbackCategorydownList;
				// console.log(resData);
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
	 function mainCategoryTrigger() {
		  var activeItem = mainCategory.find('.active');
		  var items = mainCategory.find('.category-item');
		
		  items.each(function (i, item) {
		    $(item).on('click', function () {
		      activeItem.removeClass('active');
		      $(this).addClass('active');
		      activeItem = $(this);
		      var data = categoryData[$(this).data('id')];
		      renderSubCategory(subCategory, data);
		    })
		  })
		}
		    function renderSubCategory(parent, data) {
			  parent.find('.title .name').text(data.categoryName);
			  var html = '';
			  if (data.list && data.list.length) {
			
				for (var i = 0; i < data.list.length; i += 1) {
				  html += '<div class="sub-category-product">' +
					'<a href="${APP_PATH}/MlbackCategory/toproductlist?categoryId=' + data.list[i].categoryId + '">' +
					'<div class="product-img">' +
					'<img src="' + data.list[i].categoryImgurl + '" alt="">' +
					'</div>' +
					'<span class="product-name">' + data.list[i].categoryName + '</span>' +
					/* '<span class="product-name">' + data.list[i].categoryDesc + '</span>'+ */
			
					'</a>' +
					'</div>';
				}
				parent.find('.body').html(html);
			  } else {
				renderErrorMsg(parent.find('.body'), 'Related products have been removed！')
			  }
			
			  parent.parent().addClass('active');
			}
		
		// cart icon default number
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
		
	</script>
</body>



</html>