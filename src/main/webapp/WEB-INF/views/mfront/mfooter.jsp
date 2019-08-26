<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
</head>

<body>
  <!-- footer -->
  <div class="footer">
		<div class="footer-logo"></div>
    <div class="container">
      
      <div class="nav"></div>
    </div>
  </div>
  <script src="${APP_PATH }/static/m/js/sidenav.js"></script>
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
      // url: '${APP_PATH}/MlbackFootNav/getMlfrontFootNavAll',
      type: 'post',
      async: false,
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
									"link": "${APP_PATH}/MlbackFootNav/toMlfrontFootNavPage?footnavId=" + fData[key2].footnavId
                })
              } else {
                footerData[key].children.push({
                  "name": fData[key2].footnavName,
                  "icon": "",
                  "link": "javascritp:;"
                })
              }
            }
          }
        }
      }
    });
    $('.nav').Side(footerData);

    function getArticle(data, id) {
      var article = {};
      for (var i = 0, len = data.length; i < len; i += 1) {
        if (parseInt(data[i].footnavId) === parseInt(id)) {
          article = data[i];
          break;
        }
      }
      return article;
    }
  </script>
</body>

</html>