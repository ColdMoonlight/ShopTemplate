<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
  <link rel="stylesheet" href="${APP_PATH }/static/m/css/main.css">
  <script src="${APP_PATH }/static/m/js/zepto.min.js"></script>
</head>

<body>
  <!-- info -->
  <div class="info">
    <p class="container">Get Free Pro Flate Iron Over $199</p>
  </div>
  <!--header  -->
  <div class="header bd-b">
    <div class="container">
      <span id="menu" class="icon menu"></span>
      <span class="icon person"></span>
      <div class="logo">
        <img src="${APP_PATH }/static/m/img/index/logo.png" alt="">
      </div>
      <span class="icon cart"><span class="num hide">0</span></span>
      <span class="icon search"></span>
    </div>
  </div>
  <div class="category">
    <i class="icon close2"></i>
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

    function renderErrorMsg(parent, msg) {
      parent.html('<p>' + msg + '</p>');
    }

    function toNumber(val) {
      var n = parseFloat(val);
      return isNaN(n) ? 0 : n
    }

    function renderMainCategory(parent, data) {
      var html = '';
      for (var i in data) {
        if (categoryActiveNum === i) {
          html += '<li class="category-item active" data-id="' + data[i].categoryId + '"><a href="#">' + data[i]
            .categoryName + '</a></li>';
        } else {
          html += '<li class="category-item" data-id="' + data[i].categoryId + '"><a href="#">' + data[i].categoryName +
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
        renderErrorMsg(parent.find('.body'), '相关产品已下架！')
      }

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