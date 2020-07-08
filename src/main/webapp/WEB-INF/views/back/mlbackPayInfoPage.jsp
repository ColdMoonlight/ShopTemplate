<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>付款管理</title>
	<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
	<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/js/clipboard.min.js"></script>
	<link rel="stylesheet" href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/static/back/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/static/back/css/table.css">
	<%-- <link rel="stylesheet" href="${APP_PATH }/static/back/js/datepicker/datepicker.css"> --%>
</head>

<body>
	<div class="container">
		<div class="aside-bar nicescroll">
			<jsp:include page="leftnav.jsp"></jsp:include>
		</div>
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
	<!-- <script type="text/javascript" src="${APP_PATH }/static/back/js/nav.js"></script> -->

	<script type="text/javascript">
		var adminAccname = '${sessionScope.AdminUser.adminAccname}';
		// console.log("adminAccname:"+adminAccname);
		$("#UEmailSession").html(adminAccname);
	</script>
	
	

	<script type="text/javascript">
		$('.nicescroll').each(function(i, item) {
			$(item).niceScroll({
				cursorcolor: "rgba(0,0,0,.3)",
				cursorwidth: "14px",
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
					// console.log(result);
					//1、解析并显示员工数据
					build_task_table(result);
					//2、解析并显示分页信息
					build_page_info(result);
					//3、解析显示分页条数据
					build_page_nav(result);
				}
			});
		}
		// var payinfoIdcd;
		// var payinfoStatuscd=0;
		function build_task_table(result) {
			// console.log(result)
			//清空table表格
			$("#task_table tbody").empty();
			var task = result.extend.pageInfo.list;
			// console.log(task);
			$.each(task, function (index, item) {
				var payinfoId = $("<td></td>").append(item.payinfoId);
				
				// console.log(payinfoStatuscd);
				var payinfoOid = $("<td></td>").append(item.payinfoOid);
				var payinfoPlatform = $("<td></td>").append(item.payinfoPlatform);
				var payinfoMoney = $("<td></td>").append(parseFloat(item.payinfoMoney));
				if(item.payinfoStatus ===0){
					var payinfoStatus = $("<td class='wzf_bg'></td>").append('<b>未支付</b>');//红
				}else if(item.payinfoStatus ===1){
					var payinfoStatus = $("<td class='yzf_bg'></td>").append('<b>已支付</b>');//黄
				}else if(item.payinfoStatus ===2){
					var payinfoStatus = $("<td class='ysh_bg'></td>").append('<b>已审核</b>');//绿
				}else if(item.payinfoStatus ===3){
					var payinfoStatus = $("<td class='yfh_bg'></td>").append('<b>已发货</b>');//蓝
				}else if(item.payinfoStatus ===4){
					var payinfoStatus = $("<td class='ytk_bg'></td>").append('<b>已退款</b>');//紫
				}else if(item.payinfoStatus ===5){
				  var payinfoStatus = $("<td class='fg_bg'></td>").append('<b>已通知复购</b>');//紫
				}else if(item.payinfoStatus ===6){
				  var payinfoStatus = $("<td class='ygb_bg'></td>").append('<b>已关闭</b>');//紫
				}

				
				// console.log(payinfoStatuscd)/**/
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
		var payinfoIdcd;
		var payinfoStatus;
		var orderOrderitemidstradd;
		var orderBuymail; /*取到orderBuymail*/
		var userid; /*取到addressUid*/
		function loadTpl(payid) {
			$('.table-box').load('${APP_PATH}/static/tpl/addPayInfo.html', function () {
				// fetch data
				var reqData = {
					"payinfoId": payid
				};
				
				// console.log(reqData)/***2222222222******/
				$.ajax({
					url: "${APP_PATH}/MlfrontPayInfo/getOneMlfrontPayInfoDetail",
					data: reqData,
					type: "POST",
					success: function (result) {
						if (result.code == 100) {
							var resData = result.extend;
							// console.log(resData);
							var resDataPayInfoOne = result.extend.mlfrontPayInfoOne;
							// console.log(resDataPayInfoOne)/**2222*/
							payinfoIdcd =resDataPayInfoOne.payinfoId;
							// console.log(payinfoIdcd);/****/
							payinfoStatus =resDataPayInfoOne.payinfoStatus;
							console.log(payinfoStatus)/**3333**/
							var resDataOrderPayOne = result.extend.mlfrontOrderPayOneRes;
							var resDataAddressOne = result.extend.mlfrontAddressOne;
							var resDataOrderItemList = result.extend.mlfrontOrderItemList;
							var resDataUserOne = result.extend.mlfrontUserOne;
							// console.log(resDataUserOne)
							var mlPaypalShipAddressOne = result.extend.mlPaypalShipAddressOne;
							
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

							renderBuyerInfo(resDataUserOne);
                            returnshow(resDataUserOne);
							var orderData = resDataOrderPayOne;
							orderData.list = resDataOrderItemList;
							orderData.payinfoMoney = resDataPayInfoOne.payinfoMoney;
							orderData.areafreightMoney = result.extend.areafreightMoney;
							orderData.payinfoPlateNum = resDataPayInfoOne.payinfoPlateNum;
							orderId = orderData.orderId;
							orderOrderitemidstradd = orderData.orderOrderitemidstr;
							shipName = orderData.orderLogisticsname;
							var receiveDataaddress = mlPaypalShipAddressOne;
							renderPaypaladdress(receiveDataaddress);
							renderOrderInfo(orderData);
                            
							var receiveData = resDataAddressOne;
							console.log(receiveData)
							receiveData.addressUid = resDataAddressOne.addressUid;
							userid = receiveData.addressUid;
							receiveData.addressEmail = resDataAddressOne.addressEmail;
							orderBuymail = receiveData.addressEmail;
							
							receiveData.orderCreatetime = resDataOrderPayOne.orderCreatetime;
							receiveData.orderBuyMess = resDataOrderPayOne.orderBuyMess;
							receiveData.orderCouponCode = resDataOrderPayOne.orderCouponCode;	//**优惠码****
							
							receiveData.payinfoPlatform = resDataPayInfoOne.payinfoPlatform;
							receiveData.payinfoCreatetime = resDataPayInfoOne.payinfoCreatetime;
							
							receiveData.payinfoPlatformserialcode = resDataPayInfoOne.payinfoPlatformserialcode;
							renderReceiverinfo(receiveData);
						} else {
							alert("联系管理员");
						}
					}
				});
				/*****/
				var myid;
				function returnshow(data){
					if(data==null){
						$(".remark_info1").hide();
						$(".userstart").hide();
					}else{
						$(".user_rating").html(data.userStr)
						$(".userstart").show();
						$(".remark_info1 textarea").val(data.userStr);
						var userodid = data.userId;
						myid =userodid;
					}
				}
				$(".remark_info1 input").click(function(){
					var haveData = {
						"userStr":$(".remark_info1 textarea").val(),
						"userId":myid,
					}
					console.log(haveData);
					$.ajax({
						url: "${APP_PATH}/MlfrontUser/update",
						data: JSON.stringify(haveData),
						type: "POST",
						dataType: 'json',
						contentType: 'application/json',
						success: function (data) {
							if (data.code === 100) {
								var textw=$(".remark_info1 textarea").val();
								$(".remark_info1").hide();
								$(".mask").hide();
								$(".user_rating").html(textw)
							}
						}
					})
				});
				$(".closetc").click(function(){
					$(".remark_info1").hide();
					$(".remark_info2").hide();
					$(".remark_info3").hide();
					$(".mask").hide();
				});
				$(".userstart b").click(function(){
					$(".remark_info1").show();
					$(".mask").show();
				})
				
				
				
				
			});
		}

		function renderBuyerInfo(data) {
			var strdetails = "";
			var html = '';
			var myid;
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
					'<div class="vipdiv"><span>用户vip等级：</span><span class="uservip">' + data.userVipLevel + '</span><em></em><b class="vipnum">编辑</b></div>' +
					'<div class="timediv"><span>历史购买次数：</span><span class="usertime">' + data.userTimes + '</span><em></em><b class="timenum">编辑</b></div>';
			var userodid = data.userId;
			myid =userodid;
			}
			$('.buyer-info').html(html);
			/***************/
				
			$(".vipnum").click(function(){
				$(".remark_info2").show();
				$(".mask").show();
			})
			$(".timenum").click(function(){
				$(".remark_info3").show();
				$(".mask").show();
			})
			
			$(".remark_info2 input").click(function(){
				var haveData2 = {
					"userVipLevel":$(".remark_info2 textarea").val(),
					"userId":myid,
				}
				console.log(haveData2);
				$.ajax({
					url: "${APP_PATH}/MlfrontUser/update",
					data: JSON.stringify(haveData2),
					type: "POST",
					dataType: 'json',
					contentType: 'application/json',
					success: function (data) {
						if (data.code === 100) {
							var textw=$(".remark_info2 textarea").val();
							$(".remark_info2").hide();
							$(".mask").hide();
							$(".vipdiv .uservip") .html(textw)
						}
					}
				})
			});
			$(".remark_info3 input").click(function(){
				var haveData2 = {
					"userTimes":$(".remark_info3 textarea").val(),
					"userId":myid,
				}
				console.log(haveData2);
				$.ajax({
					url: "${APP_PATH}/MlfrontUser/update",
					data: JSON.stringify(haveData2),
					type: "POST",
					dataType: 'json',
					contentType: 'application/json',
					success: function (data) {
						if (data.code === 100) {
							var textw=$(".remark_info3 textarea").val();
							$(".remark_info3").hide();
							$(".mask").hide();
							$(".timediv .usertime") .html(textw)
						}
					}
				})
			});
		}

		function renderOrderInfo(data) {//红
			var statusDetail = "仅发起，未付款";
			var colorspan=""
			if (data.orderStatus == 0) {//红
				statusDetail = "仅发起，未付款";
				// colorspan ="colorspan1"
			} else if (data.orderStatus == 1) {//黄现出来一个按钮，ajax 返回后刷新页面
				statusDetail = "付款成功，待审单";
				// colorspan ="colorspan2"
			} else if (data.orderStatus == 2) {
				statusDetail = "付款失败";
				// colorspan ="colorspan3"
			} else if (data.orderStatus == 3) {//绿		
				statusDetail = "已审单，待发货";
				// colorspan ="colorspan4"
				ifsend = data.orderLogisticsid;
			} else if (data.orderStatus == 4) {//紫
				statusDetail = "已发货，待接收";
				// colorspan ="colorspan4"
				ifsend = data.orderLogisticsid;
			}else if(data.orderStatus == 5){
				statusDetail = "已退款";
				// colorspan ="colorspan4"
				ifsend = data.orderLogisticsid;
			}else if(data.orderStatus == 6){
				statusDetail = "弃购已发";
				// colorspan ="colorspan4"
				ifsend = data.orderLogisticsid;
			}else if(data.orderStatus == 7){
				statusDetail = "本单重复-已关闭";
				// colorspan ="colorspan4"
				ifsend = data.orderLogisticsid;
			}
			
			var headerHtml = '';
			// console.log(data)
			headerHtml += '<span class="order-id">订单id ：' + data.orderId + '</span>' +
//			    '<span>支付运费编号 ：' + data.payinfoPlateNum + '</span>'+
			    '<span class="payyufei_num" num-id="'+ data.payinfoPlateNum + '">支付运费编号 ：' + data.payinfoPlateNum + '</span>'+
				'<span class="'+colorspan+'">订单状态 ：' + statusDetail + '</span>';
				if(data.orderStatus == 0){
					headerHtml += '<span class="btn btn-info check_order" onclick="purchase_send()">弃购发送</span><span class="btn btn-danger return_order" onclick="close_order()" >交易关闭</span>';
				}
			if(data.orderStatus == 1){
				headerHtml += '<span class="btn btn-success check_order" onclick="check_order()">审核</span><span class="btn btn-danger return_order" onclick="return_order()" >退款</span>';
			}
			if (data.orderStatus === 1 || data.orderStatus === 3) {
				headerHtml += '<span class="shipping">';
			} else {
				headerHtml += '<span class="shipping hide">';
			}
			if( data.orderStatus === 1){
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
						data.list[i].orderitemProductAccoff).current * data.list[i].orderitemPskuNumber).toFixed(2) + '</td>' +
					'</tr>';
			}

			$('.order-info tbody').html(tbodyHtml);

			var calInfoHtml = '';

			calInfoHtml = '<div><span>预计总价（含运费 $'+  data.areafreightMoney +'）：</span><span>$' + (parseFloat(data.payinfoMoney) + parseFloat(data.orderCouponPrice)).toFixed(2) + '</span></div>' +
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
					'<option value ="1">UPS</option>' +
					'<option value ="2">FEDEX</option>' +
					'<option value ="3">4PX</option>' +
					'<option value ="4">DHL</option>' +
					'<option value ="5">ARAMEX</option>' +
					'<option value ="6">TNT</option>' +
					'<option value ="7">DPEX</option>' +
					'<option value ="8">华磊</option>' +
					'<option value ="9">EMS</option>' +
					'<option value ="10">DHL_eCommerce</option>' +
					'<option value ="11">其他(联系技术人员添加)</option>' +
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
		
		// payinfoIdcd=payinfoId;
		// console.log(payinfoIdcd)/*eeee*/
		orderCouponId =payinfoIdcd;
		/**已审核*/
        function check_order(){
			var reqData = {
				"orderId":orderId,
				"orderCouponId":payinfoIdcd,
			}
			// console.log(reqData);
			$.ajax({
				url: '${APP_PATH}/MlfrontOrder/updateOrderReady',
				data: JSON.stringify(reqData),
				type: "POST",
				dataType: "json",
				contentType: 'application/json',
					success: function (reqData) {
						alert("已审核")
						window.location.href = "${APP_PATH}/MlfrontPayInfo/toMlbackPayInfoList";
					}
			});
			
		}
		 /***退款***/
		     function return_order(){
				  var msg = "您真的确定要退款?";
				   if (confirm(msg)==true){
					  var reqData = {
					    "orderId":orderId,
					    "orderCouponId":payinfoIdcd,
					  }
					  $.ajax({
					    url: '${APP_PATH}/MlfrontOrder/updateOrderRefund',
					    data: JSON.stringify(reqData),
					    type: "POST",
					    dataType: "json",
					    contentType: 'application/json',
					      success: function (reqData) {
					        // alert("确认退款")
					        window.location.href = "${APP_PATH}/MlfrontPayInfo/toMlbackPayInfoList";
					      }
					  });
					   return true;
					  }else{
					  return false;
				  }
		     }
			 
			 /**purchase_send()弃购发送****/
			  function purchase_send(){
				var reqData = {
				  "orderId":orderId,
				  "orderCouponId":payinfoIdcd,
				  "orderOrderitemidstr":orderOrderitemidstradd,
				  "orderBuyMess":orderBuymail,
				}
				console.log(reqData)
				$.ajax({
				  url: '${APP_PATH}/MlfrontOrder/updateOrderAbandoningPurchase',
				  data: JSON.stringify(reqData),
				  type: "POST",
				  dataType: "json",
				  contentType: 'application/json',
					success: function (reqData) {
					  alert("已发送")
					  window.location.href = "${APP_PATH}/MlfrontPayInfo/toMlbackPayInfoList";
					}
				});
			  }
			  /*****close_order交易关闭****************/
			  function close_order(){
				  var msg = "您真的确定要关闭?";
				   if (confirm(msg)==true){
					   var reqData = {
					     "orderId":orderId,
					     "orderCouponId":payinfoIdcd,
					   }
					   $.ajax({
					     url: '${APP_PATH}/MlfrontOrder/updateOrderClose',
					     data: JSON.stringify(reqData),
					     type: "POST",
					     dataType: "json",
					     contentType: 'application/json',
					   	success: function (reqData) {
					   	  // alert("已关闭")
					   	  window.location.href = "${APP_PATH}/MlfrontPayInfo/toMlbackPayInfoList";
					   	}
					   });
					   return true;
					   }else{
					   return false;
				  }
				  
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
			var shiprealid = parent.find('.ship-name').val();
			shipName = parent.find('.ship-name option:selected').text();
			var payinfoPlateNum = $('.payyufei_num').attr('num-id');
			//console.log(payinfoPlateNum)
			var reqData = {
				orderCouponId:payinfoIdcd,
				orderCouponCode:payinfoPlateNum,
				orderId: parseInt(orderId),
				orderLogisticsid: parseInt(shiprealid),
				orderLogisticsname: shipName,
				orderLogisticsnumber: shipId
				
			}
			// console.log(reqData)/*******/
			if (shipId.trim().length) {
				$.ajax({
					url: '${APP_PATH}/MlfrontOrder/updateOrderDetail',
					data: JSON.stringify(reqData),
					type: "POST",
					dataType: "json",
					contentType: 'application/json',
					success: function (data) {
						console.log(data)
						if (data.code === 100) {
							var postData={
									"payinfoId":payinfoIdcd,
									"payinfoStatus":payinfoStatus
								}
								// console.log(postData)/**********/
								
							function updatepayinfostu(postData){
								$.ajax({
									url: '${APP_PATH}/MlfrontPayInfo/updateShipStatus',
									data: JSON.stringify(postData),
									type: "POST",
									dataType: "json",
									contentType: 'application/json',
										success: function (postData) {
											alert("物流信息发送完毕")
										}
								});
							}
							 updatepayinfostu(postData)
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
			html ='<h3>Shipping Address</h3>' +
		    	'<div><span>留言：</span><span class="wordly">' + data.orderBuyMess + '</span></div>' +
			    '<div><span>支付方式：</span><span>' + data.payinfoPlatform + '</span></div>' +
			    '<div><span>优惠码：</span><span>' + data.orderCouponCode + '</span></div>' +
				'<div><span>付款交易码：</span><span>' + data.payinfoPlatformserialcode + '</span></div>' +
				'<div><span>收货人firstname：</span><span id="fza_txt">' + data.addressUserfirstname + '</span><input class="btn_fz btn btn-info" type="button" name="" id="fza" value="复制文本" /></div>' +
				'<div><span>收货人lastname：</span><span id="fzb_txt">' + data.addressUserlastname + '</span><input class="btn_fz btn btn-info" type="button" name="" id="fzb" value="复制文本" /></div>' +
				'<div><span>收货人电话：</span><span id="fzc_txt">' + data.addressTelephone + '</span><input class="btn_fz btn btn-info" type="button" name="" id="fzc" value="复制文本" /></div>' +
				'<div><span>收货人详细地址：</span><span id="fzd_txt">' + data.addressDetail + '</span><input class="btn_fz btn btn-info" type="button" name="" id="fzd" value="复制文本" /></div>' +
				'<div><span>收货人城市：</span><span id="fze_txt">' + data.addressCity + '</span><input class="btn_fz btn btn-info" type="button" name="" id="fze" value="复制文本" /></div>' +
				'<div><span>收货人省份：</span><span id="fzf_txt">' + (data.addressProvince ? data.addressProvince : '<i>不存在</i>') + '</span><input ' + (data.addressProvince ? '' : 'disabled') +' class="btn_fz btn btn-info" type="button" name="" id="fzf" value="复制文本" /></div>' +
				'<div><span>收货人国家：</span><span id="fzg_txt">' + data.addressCountryAll + '</span> 简称：' + data.addressCountry +'  <input class="btn_fz btn btn-info" type="button" name="" id="fzg" value="复制文本" /></div>' +
				'<div><span>邮编：</span><span id="fzh_txt">' + data.addressPost + '</span><input class="btn_fz btn btn-info" type="button" name="" id="fzh" value="复制文本" /></div>' +
				'<div><span>邮箱：</span><span id="fzi_txt">' + data.addressEmail + '</span><input class="btn_fz btn btn-info" type="button" name="" id="fzi" value="复制文本" /></div>' +
				'<div><span>购买时间：</span><span>' + data.orderCreatetime + '</span></div>' +
				'<div><span>支付时间：</span><span>' + data.payinfoCreatetime + '</span></div>';
			$('.revceiver-info').html(html);
			/*************/
			var fza_btn = $("#fza_txt");
			var fza_cont=fza_btn.html();
			var clipboard = new Clipboard('#fza', {
			    text: function() {
			        return fza_cont;
			    }
			});
			clipboard.on('success', function(e) {
			    // alert("复制成功");
				fza_btn.css("color","#999")
				
			});
			clipboard.on('error', function(e) {
			    console.log(e);
			});
			/*************/
			var fzb_btn = $("#fzb_txt");
			var fzb_cont=fzb_btn.html();
			var clipboard = new Clipboard('#fzb', {
			    text: function() {
			        return fzb_cont;
			    }
			});
			clipboard.on('success', function(e) {
			    // alert("复制成功");
				fzb_btn.css("color","#999")
			});
			clipboard.on('error', function(e) {
			    console.log(e);
			});
			/*************/
			var fzc_btn = $("#fzc_txt");
			var fzc_cont=fzc_btn.html();
			var clipboard = new Clipboard('#fzc', {
			    text: function() {
			        return fzc_cont;
			    }
			});
			clipboard.on('success', function(e) {
			   fzc_btn.css("color","#999")
			});
			clipboard.on('error', function(e) {
			    console.log(e);
			});
			/*************/
			var fzd_btn = $("#fzd_txt");
			var fzd_cont=fzd_btn.html();
			var clipboard = new Clipboard('#fzd', {
			    text: function() {
			        return fzd_cont;
			    }
			});
			clipboard.on('success', function(e) {
			  fzd_btn.css("color","#999")
			});
			clipboard.on('error', function(e) {
			    console.log(e);
			});
			/*************/
			var fze_btn = $("#fze_txt");
			var fze_cont=fze_btn.html();
			var clipboard = new Clipboard('#fze', {
			    text: function() {
			        return fze_cont;
			    }
			});
			clipboard.on('success', function(e) {
			     fze_btn.css("color","#999")
			});
			clipboard.on('error', function(e) {
			    console.log(e);
			});
			/*************/
			var fzf_btn = $("#fzf_txt");
			var fzf_cont=fzf_btn.html();
			var clipboard = new Clipboard('#fzf', {
			    text: function() {
			        return fzf_cont;
			    }
			});
			clipboard.on('success', function(e) {
			   fzf_btn.css("color","#999")
			});
			clipboard.on('error', function(e) {
			    console.log(e);
			});
			/*************/
			var fzg_btn = $("#fzg_txt");
			var fzg_cont=fzg_btn.html();
			var clipboard = new Clipboard('#fzg', {
			    text: function() {
			        return fzg_cont;
			    }
			});
			clipboard.on('success', function(e) {
			    fzg_btn.css("color","#999")
			});
			clipboard.on('error', function(e) {
			    console.log(e);
			});
			/*************/
			var fzh_btn = $("#fzh_txt");
			var fzh_cont=fzh_btn.html();
			var clipboard = new Clipboard('#fzh', {
			    text: function() {
			        return fzh_cont;
			    }
			});
			clipboard.on('success', function(e) {
			    fzh_btn.css("color","#999")
			});
			clipboard.on('error', function(e) {
			    console.log(e);
			});
			/*************/
			var fzi_btn = $("#fzi_txt");
			var fzi_cont=fzi_btn.html();
			var clipboard = new Clipboard('#fzi', {
			    text: function() {
			        return fzi_cont;
			    }
			});
			clipboard.on('success', function(e) {
			    // alert("复制成功");
				 fzi_btn.css("color","#999")
			});
			clipboard.on('error', function(e) {
			    console.log(e);
			});
		}
		/************************************************************************/
		function renderPaypaladdress(data){
			var html="";
			html= '<h3>Billing Address</h3>' +
				        '<ul>'+
						   '<li>shippingaddressCity : '+data.shippingaddressCity+'</li>'+
						    '<li>shippingaddressState : '+data.shippingaddressState+'</li>'+
						   '<li>shippingaddressCountryCode : '+data.shippingaddressCountryCode+'</li>'+
						   '<li>shippingaddressLine1 : '+data.shippingaddressLine1+'</li>'+
						    '<li>shippingaddressLine2 : '+data.shippingaddressLine2+'</li>'+
						    '<li>shippingaddressEmail : '+data.shippingaddressEmail+'</li>'+
							  '<li>shippingaddressPaymentid : '+data.shippingaddressPaymentid+'</li>'+
							   '<li>shippingaddressPostalCode : '+data.shippingaddressPostalCode+'</li>'+
								 '<li>shippingaddressRecipientName : '+data.shippingaddressRecipientName+'</li>'+
						'</ul>';
			$(".info_two").html(html);		
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
		/******************************/

		
	</script>
</body>

</html>