<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
  <link rel="stylesheet" href="${APP_PATH }/static/m/css/main.css">
  <script src="${APP_PATH }/static/m/js/zepto.min.js"></script>
  <script>
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
	  <!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-145178467-1"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'UA-145178467-1');
	</script>
</head>

<body>
  <!-- info -->
  <!--header  -->
  <div class="header bd-b">
			<div class="info">
			<p style="text-align: center;">Free Shipping World Wide.</p>
		  </div>
    <div class="container">
      <span id="menu" class="icon menu"></span>
      <span class="icon person"></span>
      <a href="${APP_PATH}/index.html">
	      <div class="logo">
	        <img src="${APP_PATH }/static/m/img/index/logo.png" alt="">
	      </div>
      </a>
      <span class="icon cart"><span class="num hide">0</span></span>
      <span class="icon search"></span>
    </div>
  </div>
  <div class="category">
    <i class="icon close close2"></i>
    <ul class="main-category bd-b"></ul>
    <div class="product-box bd-l bd-b">
      <div class="sub-category">
        <div class="title bd-b">
          <span class="name">二级分类</span>
          <a class="close" href="#"><i class="icon close"></i></a>
        </div>
        <div class="body"></div>
      </div>
    </div>
  </div>
  


  <script>
    var categoryActiveNum = 1;
    var iCart = $('.icon.cart');
    var iPerson = $('.icon.person');
	var mask = $('<div class="mask"></div>');
	var sysFlag = 0;

    function renderErrorMsg(parent, msg) {
      parent.html('<p class="without-data">' + msg + '</p>');
    }

    function renderSysMsg(msg) {
    	var elBox = $('<div class="modal sys-box" style="display: block;"></div>');
    	
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
        if (categoryActiveNum === i) {
          html += '<li class="category-item '+classimg+' active" data-id="' + data[i].categoryId + '"><a href="#">' + data[i]
            .categoryName + '</a></li>';
        } else {
          html += '<li class="category-item '+classimg+'" data-id="' + data[i].categoryId + '"><a href="#">' + data[i].categoryName +
            '</a></li>';
        }
      }
      parent.html(html);
	  
    }
	
	
    function renderSubCategory(parent, data) {
      parent.find('.title .name').text(data.categoryName);
      var html = '';
      if (data.list && data.list.length) {
        for (var i = 0; i < data.list.length; i += 1) {
			var iddata =data.categoryId
			var no_show=""
			if(iddata==7){
					no_show="hide"	
			}
          html += '<div class="sub-category-product '+no_show+'">' +
            '<a href="${APP_PATH}/MlbackCategory/toproductlist?categoryId=' + data.list[i].categoryId + '">' +
            '<div class="product-img">' +
            '<img src="' + data.list[i].categoryImgurl + '" alt="">' +
            '</div>' +
            '<span class="product-name">' + data.list[i].categoryName + '</span>' +

            '</a>' +
            '</div>';
        }
        parent.find('.body').html(html);
      } else {
        renderErrorMsg(parent.find('.body'), 'Related products have been removed！')
      }
      parent.parent().removeClass("hide");
      parent.parent().addClass('active');
    }

    var categoryBox = $('.category');
    var mainCategory = $('.main-category');
    var subCategory = $('.sub-category');
    var prodcutBox = $('.product-box');
    var categoryData = {};
    // 获取全部的category信息,文件信息
    $.ajax({
      url: '${APP_PATH}/MlbackCategory/getOneMlbackCategoryParentDetail',
      method: 'GET',
      success: function (data) {
		  var resData = data.extend.mlbackCategorydownList;
		  console.log(resData)/**************resData********/
        if (data.code === 100) {
          categoryData = data.extend.mlbackCategorydownList.reduce(function (obj, item) {
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

          renderMainCategory(mainCategory, categoryData);
          mainCategoryTrigger();
        } else {
          renderErrorMsg(prodcutBox, 'No product-related data was obtained.');
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
		  var hideno=$(".sub-category-product.hide")
		 var hidtop=hideno.parents(".product-box")
		     hidtop.addClass("hide")
			 var iddata =data.categoryId
		 if(iddata==7){
			  window.location.href = "${APP_PATH }/Category/49.html";
		 }
        })
      })
    }

    $('#menu').on('click', function () {
      categoryBox.show();
    });
    categoryBox.find('.close2').on('click', function () {
      categoryBox.hide();
    });
    prodcutBox.find('.title .close').on('click', function () {
      prodcutBox.removeClass('active');
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
  </script>
</body>

</html>