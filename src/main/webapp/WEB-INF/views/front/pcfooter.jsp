<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
</head>
<body>
<!-- footer -->
<footer class="nomargin">
	<div class="tt-footer-col tt-color-scheme-01">
		<div class="container">		
			<div class="row">
				<div class="tt-footer-logo col-lg-2 col-md-12">
					<img src="${APP_PATH }/static/pc/img/dblogo.png" alt="" style="max-width: 100%;">
		      <div >Our Hair Your Glory</div>
				</div>
				<div class="tt-footer-nav col-lg-10 col-md-12"></div>
			</div>
		</div>
	</div>
	<div class="tt-footer-custom">
		<div class="container">
			<div class="tt-row">
				<div class="tt-col-left tt-col-item">
					<!-- copyright -->
					<div class="tt-box-copyright">Copyright © 2019, MegaLook, All Rights Reserved</div>
					<!-- /copyright -->
				</div>
				<div class="tt-col-right tt-col-item">
					<!-- paypal -->
					<img class="tt-box-paypal" src="${APP_PATH }/static/pc/img/paylogo.jpg" />
					<!-- /paypal -->
				</div>
			</div>
		</div>
	</div>
</footer>
<script src="${APP_PATH }/static/pc/js/main.js"></script>
<script>
var footerData = [{
    "name": "Megalook_Hair",
    "icon": "plus",
    "link": "javascript:;",
    "children": []
  },
  {
    "name": "Buyer_Service",
    "icon": "plus",
    "link": "javascript:;",
    "children": []
  },
  {
    "name": "Activity",
    "icon": "plus",
    "link": "javascript:;",
    "children": []
  },
  {
    "name": "Client_Service_Support",
    "icon": "plus",
    "link": "javascript:;",
    "children": []
  }
];

var map = {
  Megalook_Hair: "mlbackFootNavOneList",
  Buyer_Service: "mlbackFootNavTwoList",
  Activity: "mlbackFootNavThreeList",
  Client_Service_Support: "mlbackFootNavFourList",
}

$.ajax({
	url: '${APP_PATH}/MlbackFootNav/getMlfrontFootNavAllSimple',
  type: 'post',
  success: function (data) {
    // console.log(data);
    var resData = data.extend;
    if (resData.isNav === 0) {
    	renderSysMsg('没获取到相关数据')
    }

    if (resData.isNav === 1) {
      for (var key in footerData) {
        var block = map[footerData[key].name];
        var fData = resData[block];
        for (var key2 in fData) {
          //加一个判断		footnavIfIncome		每条数据都有这个字段
          //0，不能点		比如有的就纯粹是，联系方式
          //1,能点进去		有富文本内容的。
          if (fData[key2].footnavIfIncome) {
            footerData[key].children.push({
              "name": fData[key2].footnavName,
              "icon": "",
								"link": "${APP_PATH}/MlbackFootNav/topcMlfrontFootNavPage?footnavId=" + fData[key2].footnavId
            })
          } else {
            footerData[key].children.push({
              "name": fData[key2].footnavName,
              "icon": "",
              "link": "javascritp:;"
            })
          }
        }
        
        renderFooterNav($('.tt-color-scheme-01 .tt-footer-nav'), footerData);
      }
    }
  }
});

function renderFooterNav(parent, data) {
	var html = '';
	html += '<div class="container"><div class="row">';
	for (var i=0, len=data.length; i<len; i+=1) {
		html += '<div class="col-md-6 col-lg-'+ Math.floor(12/len) +'">' +
			'<div class="tt-mobile-collapse">' +
			'<h4 class="tt-collapse-title">'+ data[i].name +'</h4>';
			var children = data[i].children;
			var len2 = (children && data[i].children.length) || 0;
			if (len2) {
				html += '<div class="tt-collapse-content">';

				if (data[i].name === 'Client_Service_Support') {
					html += '<address>';
						for (var j=0; j<len2; j+=1) {
							html += '<p>'+ children[j].name +'</p>'
						}
					html += '</address>';
				} else {
					html += '<ul class="tt-list">';
						for (j=0; j<len2; j+=1) {
							html += '<li><a href="'+ children[j].link +'">'+ children[j].name +'</a></li>';
						}
					html += '</ul>';
				}
				html += '</div>';
			}

			html += '</div></div>';
	}
	html += '</div></div>';
	
	parent.html(html);
}
</script>
</body>
</html>