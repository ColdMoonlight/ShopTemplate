<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
  %>
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
</head>
<body>

<%-- <jsp:include page="mheader.jsp"></jsp:include> --%>

<!-- main -->
 <div class="main">
 <div class="condition select">
 	<select class="select-item select-category" data-type="category"></select>
 	<select class="select-item select-color" data-type="color">
 		<option value="">all color</option>
 		<option value="white">white</option>
 		<option value="blue">blue</option>
 		<option value="black">black</option>
 	</select>
 </div>
 <div class="product-list"></div>
</div>
 
<%-- <jsp:include page="mfooter.jsp"></jsp:include> --%>

<script>
var condition = $('.select');
var productList = $('.product-list');
var cidA = window.location.href.split('?')[1].split('=');

//default codition
getProductList({
	"productCategoryid": cidA[1],
	"productColor": null
});

/* category condition */
$.ajax({
  url: '${APP_PATH}/MlbackCategory/getOneMlbackCategoryParentDetail',
  type:"GET",
  success: function(data) {
    if(data.code === 100) {
     // console.log(data.extend);
      renderCondition($('.select-item.select-category'), data.extend.mlbackCategorydownEr)
      $('.select-item').each(function(i, item) {
		  if ($('.select-category').val() && $('.select-category').val().trim().length>0) {
			  	$(item).on('change', function() {
				//console.log($('.select-category').val(), $('.select-color').val());
					getProductList({
			    		"productCategoryid": $('.select-category').val() || cidA[1],
			    		"productColor": $('.select-color').val()
			    	});
				})
	
			}
	   })
  	
    } else {
      renderErrorMsg(prodcutBox, '未获取到产品相关的数据');
    }
  }
});
/* product list for category */
function getProductList(data) {
	$.ajax({
	  url: '${APP_PATH}/MlbackProduct/getMlbackProductByproductColorAndCategoryIdList',
	  data: JSON.stringify(data),
	  dataType:"JSON",
	  contentType: 'application/json',
	  type:"POST",
	  success: function(data) {
	    // console.log(data)
	    var data = JSON.parse(data);
	    if(data.code === 100) {
	      rednerProduct(productList, data.extend.mlbackProductResList);
	    } else {
	      renderErrorMsg(productList, '未获取到产品相关的数据');
	    }
	  },
	  error: function(error) {
		 if(error.status === 400) {
		 	renderErrorMsg(productList, '不存在相关产品，页面将在3s后跳转至首页！');
		 	setTimeout(function() {
				window.location.href="${APP_PATH}/index/isMobileOrPc";
		 	}, 3000);
		 }
	  }
	});
}


function renderErrorMsg(parent, msg) {
  parent.html('<p>' + msg + '</p>');
}

function rednerProduct(parent, data) {
  var html='';
  if (data.length>0) {
	  for(var i=0; i<data.length; i+=1) {
	    html += '<div class="product-item">' +
	      '<div class="product-img">' +      
	      	'<a href="${APP_PATH}/MlbackProduct/tomProductDetailPage?productId=' + data[i].productId +'">' +
	        '<img src="' + data[i].productMainimgurl + '" alt="">' +
	        '</a>' +
	      '</div>' +
	      '<div class="product-desc">' +
	        '<div class="product-title">' + data[i].productName + '</div>' +
	        '<div class="product-type"></div>' +
	        '<div class="product-data">' +
	            '<span class="pay-num">' + (data[i].productHavesalenum ? data[i].productHavesalenum : 0)  +' Payment</span>' +
	            '<span class="review-num">' + (data[i].productReviewnum ? data[i].productReviewnum : 0) +' Review(s)</span>' +
	        '</div>' +
	        '<div class="product-price">' +
	          '<span class="product-now-price">$' + (data[i].productOriginalprice && data[i].productActoffoff ? (data[i].productOriginalprice*data[i].productActoffoff/100).toFixed(2) : 0) +'</span>' +
	          '<span class="product-define-price">$' + (data[i].productOriginalprice ? data[i].productOriginalprice : 0) +'</span>' +
	          '<span class="product-to-cart" data-id="' + data[i].productId + '"><i class="icon cart2"></i></span>' +
	        '</div>' +
	      '</div>' +
	    '</div>';
	  }
	
	  parent.html(html);
  } else {
	  renderErrorMsg(parent, '相关产品分类的产品已下架！');
  }
}

function renderCondition(parent, data, defaultHtml) {
	var html= defaultHtml || '';
		html += ''
		
	for (var i=0, len = data.length; i<len; i+=1) {
		if (data[i].categoryId === parseInt(cidA[1])) {
			console.log("*********")
			html = '<option value="'+ data[i].categoryId +'">'+ data[i].categoryName +'</option>' + html;
		} else {
			html += '<option value="'+ data[i].categoryId +'">'+ data[i].categoryName +'</option>';
		}
	}
	
	parent.html(html);
}
</script>
</body>
</html>