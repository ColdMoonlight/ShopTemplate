<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>付款管理</title>
	<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
	<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<link rel="stylesheet" href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/static/back/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/static/back/css/table.css">
	<%-- <link rel="stylesheet" href="${APP_PATH }/static/back/js/datepicker/datepicker.css"> --%>
</head>

<body>
	<div class="container">
		<div class="aside-bar nicescroll"></div>
		<div class="main-body">
			<div class="main-box nicescroll">
				<div class="header">
					<h2>付款页面列表</h2>
					<span class="user" id="UEmailSession">*</span>
				</div>
				<div class="content">
					<div class="table-box">
						<!-- operator -->
						<div class="op">
							<a href="#" class="btn btn-default" role="button"> <i class="glyphicon glyphicon-tasks"></i>付款页面列表</a>
							<a href="#" id="task_add_modal_btn" class="btn btn-primary" role="button"><i
									class="glyphicon glyphicon-plus"></i> 新增</a>
						</div>
						<!-- table-content -->
						<div class="table-content">
							<table class="table table-striped table-hover" id="task_table">
								<thead>
									<tr>
										<th>id</th>
										<th>订单id</th>
										<th>支付方式</th>
										<th>支付金额</th>
										<th>支付状态</th>
										<th>支付运费编号</th>
										<th>支付发起时间</th>
										<th>支付更新时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody> </tbody>
							</table>
						</div>
						<!-- 显示分页信息 -->
						<div class="row">
							<!--分页文字信息  -->
							<div class="col-md-6 col-sm-6" id="page_info_area"></div>
							<!-- 分页条信息 -->
							<div class="col-md-6 col-sm-6" id="page_nav_area"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${APP_PATH }/static/back/js/jquery-nicescroll.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/back/js/sidenav.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/back/js/nav.js"></script>

	<script type="text/javascript">
		var adminAccname = '${sessionScope.AdminUser.adminAccname}';
		// console.log("adminAccname:"+adminAccname);
		$("#UEmailSession").html(adminAccname);
	</script>
	
	<%-- <script type="text/javascript" src="${APP_PATH }/static/back/js/moment.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/back/js/datepicker/datepicker.js"></script> --%>

	<script type="text/javascript">
		$('.nicescroll').each(function(i, item) {
			$(item).niceScroll({
				cursorcolor: "rgba(0,0,0,.3)",
				cursorwidth: "4px",
				cursorborder: "none",
				horizrailenabled: false,
				enablekeyboard: false,
	    }).resize()
		});
		var totalRecord, currentPage, editid;
		var count = 1;
		//1、页面加载完成以后，直接去发送ajax请求,要到分页数据
		$(function () {
			//去首页
			to_page(1);
		});

		function to_page(pn) {
			$.ajax({
				url: "${APP_PATH}/MlfrontPayInfo/getMlfrontPayInfoByPage",
				data: "pn=" + pn,
				type: "GET",
				success: function (result) {
					//console.log(result);
					//1、解析并显示员工数据
					build_task_table(result);
					//2、解析并显示分页信息
					build_page_info(result);
					//3、解析显示分页条数据
					build_page_nav(result);
				}
			});
		}

		function build_task_table(result) {
			// console.log(result)
			//清空table表格
			$("#task_table tbody").empty();
			var task = result.extend.pageInfo.list;
			$.each(task, function (index, item) {
				var payinfoId = $("<td></td>").append(item.payinfoId);
				var payinfoOid = $("<td></td>").append(item.payinfoOid);
				var payinfoPlatform = $("<td></td>").append(item.payinfoPlatform);
				var payinfoMoney = $("<td></td>").append(parseFloat(item.payinfoMoney));
				if(item.payinfoStatus ===1){
					var payinfoStatus = $("<td class='yzf_bg'></td>").append('<b>已支付</b>');
				}else{
					var payinfoStatus = $("<td class='wzf_bg'></td>").append('<b>未支付</b>');
				}
				var payinfoPlateNum = $("<td></td>").append(item.payinfoPlateNum);
				var payinfoCreatetime = $("<td></td>").append(item.payinfoCreatetime);
				var payinfoMotifytime = $("<td></td>").append(item.payinfoMotifytime);
				var editBtn = $("<button></button>").addClass("btn btn-primary btn-xs view_btn")
					.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
				//为编辑按钮添加一个分类id
				editBtn.attr("view-id", item.payinfoId);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-xs delete_btn")
					.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
				//为删除按钮添加一个分类id
				delBtn.attr("del-id", item.payinfoId);
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn).append(" ");
				//append方法执行完成以后还是返回原来的元素
				$("<tr></tr>").append(payinfoId)
					.append(payinfoOid)
					.append(payinfoPlatform)
					.append(payinfoMoney)
					.append(payinfoStatus)
					.append(payinfoPlateNum)
					.append(payinfoCreatetime)
					.append(payinfoMotifytime)
					.append(btnTd)
					.appendTo("#task_table tbody");
			});
		}
		//解析显示分页信息
		function build_page_info(result) {
			$("#page_info_area").empty();
			$("#page_info_area").append("当前" + result.extend.pageInfo.pageNum + "页,总" +
				result.extend.pageInfo.pages + "页,总" +
				result.extend.pageInfo.total + "条记录");
			totalRecord = result.extend.pageInfo.total;
			currentPage = result.extend.pageInfo.pageNum;
		}
		//解析显示分页条，点击分页要能去下一页....
		function build_page_nav(result) {
			//page_nav_area
			$("#page_nav_area").empty();
			var ul = $("<ul></ul>").addClass("pagination");

			//构建元素
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
			if (result.extend.pageInfo.hasPreviousPage == false) {
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			} else {
				//为元素添加点击翻页的事件
				firstPageLi.click(function () {
					to_page(1);
				});
				prePageLi.click(function () {
					to_page(result.extend.pageInfo.pageNum - 1);
				});
			}

			var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
			if (result.extend.pageInfo.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			} else {
				nextPageLi.click(function () {
					to_page(result.extend.pageInfo.pageNum + 1);
				});
				lastPageLi.click(function () {
					to_page(result.extend.pageInfo.pages);
				});
			}

			//添加首页和前一页 的提示
			ul.append(firstPageLi).append(prePageLi);
			//1,2，3遍历给ul中添加页码提示
			$.each(result.extend.pageInfo.navigatepageNums, function (index, item) {

				var numLi = $("<li></li>").append($("<a></a>").append(item));
				if (result.extend.pageInfo.pageNum == item) {
					numLi.addClass("active");
				}
				numLi.click(function () {
					to_page(item);
				});
				ul.append(numLi);
			});
			//添加下一页和末页 的提示
			ul.append(nextPageLi).append(lastPageLi);

			//把ul加入到nav
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav_area");
		}
		//新建任務
		$('#task_add_modal_btn').click(function () {
			// 获取分类页面模板
			loadTpl();
		});
		//新建/编辑任務提交按钮
		$(document).on('click', '#tasksubmit', function () {
			var data = $('form').serializeArray();
			// console.log(data)
			data = data.reduce(function (obj, item) {
				obj[item.name] = item.value;
				return obj
			}, {});
			data.payinfoStatus = $(":input[name='payinfoStatus']").val();
			// console.log(data);
			$.ajax({
				url: "${APP_PATH}/MlfrontPayInfo/save",
				data: JSON.stringify(data),
				dataType: "json",
				contentType: 'application/json',
				type: "POST",
				success: function (result) {
					if (result.code == 100) {
						alert('新建/编辑成功！');
						window.location.href = "${APP_PATH}/MlfrontPayInfo/toMlbackPayInfoList";
					}
				}
			});
		});

		// 查看订单详情
		$("#task_table").on("click", ".view_btn", function () {
			// load tpl
			loadTpl($(this).attr('view-id'));
		});
		var orderId;
		var shipName;

		function loadTpl(payid) {
			$('.table-box').load('${APP_PATH}/static/tpl/addPayInfo.html', function () {
				// fetch data
				var reqData = {
					"payinfoId": payid
				};
				$.ajax({
					url: "${APP_PATH}/MlfrontPayInfo/getOneMlfrontPayInfoDetail",
					data: reqData,
					type: "POST",
					success: function (result) {
						if (result.code == 100) {
							var resData = result.extend;
							console.log(resData);
							var resDataPayInfoOne = result.extend.mlfrontPayInfoOne;
							var resDataOrderPayOne = result.extend.mlfrontOrderPayOneRes;
							var resDataAddressOne = result.extend.mlfrontAddressOne;
							var resDataOrderItemList = result.extend.mlfrontOrderItemList;
							var resDataUserOne = result.extend.mlfrontUserOne;
							/* console.log('********resDataPayInfoOne********');
							console.table(resDataPayInfoOne);
							console.log('********resDataOrderPayOne********');
							console.table(resDataOrderPayOne);
							console.log('********resDataAddressOne********');
							console.table(resDataAddressOne);
							console.log('********resDataOrderItemList********');
							console.table(resDataOrderItemList);
							console.log('********resDataUserOne********');
							console.log(resDataUserOne); */

							renderBuyerInfo(resDataUserOne)

							var orderData = resDataOrderPayOne;
							orderData.list = resDataOrderItemList;
							orderData.payinfoMoney = resDataPayInfoOne.payinfoMoney;
							orderId = orderData.orderId;
							shipName = orderData.orderLogisticsname;
							
							
							renderOrderInfo(orderData);

							var receiveData = resDataAddressOne;
							receiveData.orderCreatetime = resDataOrderPayOne.orderCreatetime;
							receiveData.payinfoPlatform = resDataPayInfoOne.payinfoPlatform;
							receiveData.payinfoCreatetime = resDataPayInfoOne.payinfoCreatetime;
							receiveData.payinfoPlatformserialcode = resDataPayInfoOne.payinfoPlatformserialcode;
							renderReceiverinfo(receiveData);
						} else {
							alert("联系管理员");
						}
					}
				});
			});
		}

		function renderBuyerInfo(data) {
			var strdetails = "";
			var html = '';
			if (data == null) {
				strdetails = "此单为游客购买,无该信息";
				html = '<div><span>用户账号：</span><span>' + strdetails + '</span></div>' +
					'<div><span>用户姓名：</span><span>' + strdetails + '</span></div>' +
					'<div><span>用户电话：</span><span>' + strdetails + '</span></div>' +
					'<div><span>用户邮箱：</span><span>' + strdetails + '</span></div>' +
					'<div><span>历史购买次数：</span><span>' + strdetails + '</span></div>';
			} else {
				html = '<div><span>用户账号：</span><span>' + data.userEmail + '</span></div>' +
					'<div><span>用户姓名：</span><span>' + data.userFirstname + " " + data.userLastname + '</span></div>' +
					'<div><span>用户电话：</span><span>' + data.userTelephone + '</span></div>' +
					'<div><span>用户邮箱：</span><span>' + data.userEmail + '</span></div>' +
					'<div><span>历史购买次数：</span><span>' + 0 + '</span></div>';
			}
			$('.buyer-info').html(html);
		}

		function renderOrderInfo(data) {
			var statusDetail = "仅发起，未付款";
			if (data.orderStatus == 0) {
				statusDetail = "仅发起，未付款";
			} else if (data.orderStatus == 1) {
				statusDetail = "付款成功，待发货";
			} else if (data.orderStatus == 2) {
				statusDetail = "付款失败";
			} else if (data.orderStatus == 3) {
				statusDetail = "已发货，待接收";
				ifsend = data.orderLogisticsid;
			}
			var headerHtml = '';
			// console.log(data)
			headerHtml += '<span class="order-id">订单id ：' + data.orderId + '</span>' +
				'<span>订单状态 ：' + statusDetail + '</span>';
			if (data.orderStatus === 1 || data.orderStatus === 3) {
				headerHtml += '<span class="shipping">';
			} else {
				headerHtml += '<span class="shipping hide">';
			}
			if (data.orderLogisticsnumber) {
				headerHtml += '<span class="ship-number">物流单号：' + data.orderLogisticsnumber + ' </span>' +
					'<span style="margin: 0 1em;"> 发货时间 ：' + data.orderSendtime + '</span>' +
					'<a class="btn btn-danger edit-shipping" onclick="editShipping(1)">编辑</a></span>';
			} else {
				headerHtml += '<span>物流单号：' + '暂无' + '</span>' +
					'<span style="margin: 0 1em;">发货时间 ：' + '暂无 ' + '</span>' +
					'<a class="btn btn-danger edit-shipping" onclick="editShipping(2)">添加</a></span>';
			}

			$('.order-info .table-header').html(headerHtml);

			var tbodyHtml = ''
			for (var i = 0, len = data.list.length; i < len; i += 1) {
				var imgurl = data.list[i].orderitemProductMainimgurl;
				var image = '<img src=' + imgurl + ' ' + 'width=40 height=40>';
				tbodyHtml += '<tr>' +
					'<td>' + image + '</td>' +
					'<td>' + data.list[i].orderitemPname + '</td>' +
					'<td>';
				var skuNameArr = data.list[i].orderitemPskuNamestr.split(',');
				var skuItemNameArr = data.list[i].orderitemPskuIdnamestr.split(',');
				for (var j = 0, len2 = skuItemNameArr.length; j < len2; j += 1) {
					tbodyHtml += '<div class="td-item"><span>' + skuItemNameArr[j] + '</span>:<span>' + skuNameArr[j] +
						'</span></div>';
				}
				tbodyHtml += '</td>' +
					'<td>' + (getPrice(data.list[i].orderitemProductOriginalprice, data.list[i].orderitemPskuMoneystr.split(','),
						data.list[i].orderitemProductAccoff).current) + '</td>' +
					'<td>' + data.list[i].orderitemPskuNumber + '</td>' +
					'<td>' + (getPrice(data.list[i].orderitemProductOriginalprice, data.list[i].orderitemPskuMoneystr.split(','),
						data.list[i].orderitemProductAccoff).current * data.list[i].orderitemPskuNumber) + '</td>' +
					'</tr>';
			}

			$('.order-info tbody').html(tbodyHtml);

			var calInfoHtml = '';

			calInfoHtml = '<div><span>预计总价：</span><span>$' + (parseFloat(data.payinfoMoney) + parseFloat(data
				.orderCouponPrice)) + '</span></div>' +
				'<div><span>优惠券抵扣：</span><span>-$' + data.orderCouponPrice + '</span></div>' +
				'<div><span>实际支付金额：</span><span>$' + data.payinfoMoney + '</span></div>';


			$('.order-info .cal-info').html(calInfoHtml);
		}

		function editShipping(type) {
			var shipId;
			if (type === 1) {
				shipId = $('.ship-number').text().split('：')[1];
			} else {
				shipId = ''
			}

			var shipBox = $('.ship-box');
			if (shipBox.length) {
				shipBox.find('.ship-id').val(shipId);
			} else {
				shipBox = $('<div class="ship-box hide"></div>');
				var html = '<div class="form-group row">' +
					'<label class="col-sm-4 control-label">物流:</label>' +
					'<div class="col-sm-6">' +
					'<select class="ship-name form-control">' +
					'<option value ="英国专线-4PX">英国专线-4PX</option>' +
					'<option value ="华磊物流通测试">华磊物流通测试</option>' +
					'<option value ="FBA">FBA</option>' +
					'<option value ="AliExpress无忧物流-标准">AliExpress无忧物流-标准</option>' +
					'<option value ="ARAMEX">ARAMEX</option>' +
					'<option value ="UPS">UPS</option>' +
					'<option value ="TNT">TNT</option>' +
					'<option value ="顺丰">顺丰</option>' +
					'<option value ="FedEx">FedEx</option>' +
					'<option value ="E邮宝">E邮宝</option>' +
					'<option value ="DPEX">DPEX</option>' +
					'<option value ="DHL">DHL</option>' +
					// '<option value ="挂号">挂号</option>' +
					// '<option value ="中国邮政挂号小包">天天</option>' +
					'</select>' +
					'</div>' +
					'</div>' +
					'<div class="form-group row">' +
					'<label class="col-sm-4 control-label">物流单号:</label>' +
					'<div class="col-sm-6">' +
					'<input type="text" class="ship-id form-control" placeholder="物流单号" value=' + shipId + '>' +
					'</div>' +
					'</div>' +
					'<div class="form-group row save">' +
					'<a class="btn btn-danger" onclick="shipCancel(event)">取消</a>' +
					'<a class="btn btn-primary" onclick="shipSave(event)">确定</a>' +
					'</div>';
				shipBox.html(html);

				$(document.body).append(shipBox);
			}
			if (shipName && shipName.trim().length) {
				$('.ship-name').val(shipName);
			}
			shipBox.removeClass('hide');
		}

		/*
		url: MlfrontOrder/updateOrderDetail
		type: POST 
		params: 
			Integer orderId	
		    String orderLogisticsname;//物流名字			//先不做下拉的话，这个字段先随便传个字符串
		    String orderLogisticsnumber;//物流单号 
		dataType: jsonSting
    */

		function shipSave(e) {
			var parent = $(e.target).parents('.ship-box');
			var shipId = parent.find('.ship-id').val();
			shipName = parent.find('.ship-name').val();
			var reqData = {
				orderId: parseInt(orderId),
				orderLogisticsname: shipName,
				orderLogisticsnumber: shipId
			}
			// console.log(reqData)
			if (shipId.trim().length) {
				$.ajax({
					url: '${APP_PATH}/MlfrontOrder/updateOrderDetail',
					data: JSON.stringify(reqData),
					type: "POST",
					dataType: "json",
					contentType: 'application/json',
					success: function (data) {
						// console.log(data)
						if (data.code === 100) {
							window.location.href = "${APP_PATH}/MlfrontPayInfo/toMlbackPayInfoList";
							// $('.ship-number').text(shipId);
							// parent.addClass('hide');
						}
					}
				});
			} else {
				alert('物流单号无效！');
			}
		}

		function shipCancel(e) {
			var parent = $(e.target).parents('.ship-box');
			parent.addClass('hide');
		}

		function renderReceiverinfo(data) {
			var html = '';
			html = '<div><span>支付方式：</span><span>' + data.payinfoPlatform + '</span></div>' +
				'<div><span>付款交易码：</span><span>' + data.payinfoPlatformserialcode + '</span></div>' +
				'<div><span>收货人firstname：</span><span>' + data.addressUserfirstname + '</span></div>' +
				'<div><span>收货人lastname：</span><span>' + data.addressUserlastname + '</span></div>' +
				'<div><span>收货人电话：</span><span>' + data.addressTelephone + '</span></div>' +
				'<div><span>收货人详细地址：</span><span>' + data.addressDetail + '</span></div>' +
				'<div><span>收货人城市：</span><span>' + data.addressCity + '</span></div>' +
				'<div><span>收货人省份：</span><span>' + data.addressProvince + '</span></div>' +
				'<div><span>收货人国家：</span><span>' + data.addressCountry + '</span></div>' +
				'<div><span>邮编：</span><span>' + data.addressPost + '</span></div>' +
				'<div><span>邮箱：</span><span>' + data.addressEmail + '</span></div>' +
				'<div><span>购买时间：</span><span>' + data.orderCreatetime + '</span></div>' +
				'<div><span>支付时间：</span><span>' + data.payinfoCreatetime + '</span></div>';
				
				
				
			$('.revceiver-info').html(html);
		}

		/* single */
		function getPrice(originalePrice, skuPriceArr, discount) {
			var singlePrice = parseFloat(originalePrice);
			for (var k = 0, len = skuPriceArr.length; k < len; k += 1) {
				singlePrice += (parseFloat(skuPriceArr[k]) ? parseFloat(skuPriceArr[k]) : 0);
			}

			return {
				origin: parseFloat(singlePrice).toFixed(2),
				current: parseFloat(singlePrice * ((parseFloat(discount) ? parseFloat(discount) : 100) / 100)).toFixed(2)
			}
		}
	</script>
</body>

</html>