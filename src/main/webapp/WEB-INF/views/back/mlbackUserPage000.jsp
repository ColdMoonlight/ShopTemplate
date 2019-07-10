<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${APP_PATH }/static/css/main.css">
<link rel="stylesheet" href="${APP_PATH }/static/css/daterangepicker.css">
<link rel="stylesheet" href="${APP_PATH }/static/css/table.css">
</head>
<body>
<div class="container">
	<div class="aside-bar"></div>
	<div class="main-body">
	    <div class="main-box">
	        <div class="header">
	            <h2>付款页面列表</h2>
	            <span class="user">admin</span>
	        </div>
	        <div class="content">
	            <div class="table-box">
	                <!-- operator -->
	                <div class="op">
	                    <a href="#" class="btn btn-default" role="button"> <i class="glyphicon glyphicon-tasks"></i>付款页面列表</a>
	                    <a href="#" id="task_add_modal_btn" class="btn btn-primary" role="button"><i class="glyphicon glyphicon-plus"></i> 新增</a>
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

<script src="${APP_PATH }/static/js/moment.min.js"></script>
<script src="${APP_PATH }/static/js/daterangepicker.js"></script>
<script src="${APP_PATH }/static/js/sidenav.js"></script>
<script src="${APP_PATH }/static/js/nav.js"></script>

</script>
<!-- <script>
	$(function() {
	       //定义locale汉化插件
        var locale = {
            "format": 'YYYY-MM-DD HH:mm:ss',
            "separator": "",
            "applyLabel": "确定",
            "cancelLabel": "取消",
            "fromLabel": "起始时间",
            "toLabel": "结束时间'",
            "customRangeLabel": "自定义",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        };
        //初始化显示当前时间
        $('#startTime').val(moment(new Date()).format('YYYY-MM-DD HH:mm:ss'));
		$('#startTime').daterangepicker(
	            {
	                singleDatePicker: true,
	                showDropdowns: false,
	                autoUpdateInput: true,
	                timePicker24Hour : true,
	                timePicker : true,
	                drops:'up',
	                'locale': locale,
	            },
	            function (start, end) {
	               // $('#startTime').val(start.format('YYYY-MM-DD'));
	            }
	       );
	    $('#add-modal').on('show.bs.modal', function (event) {
	        var button = $(event.relatedTarget)
	        var modal = $(this)
	    })
	})
</script> -->
<script type="text/javascript">
	var totalRecord,currentPage,editid;
	var count = 1;
	//1、页面加载完成以后，直接去发送ajax请求,要到分页数据
	$(function(){
		//去首页
		to_page(1);
	});
	function to_page(pn){
		$.ajax({
			url:"${APP_PATH}/MlfrontPayInfo/getMlfrontPayInfoByPage",
			data:"pn="+pn,
			type:"GET",
			success:function(result){
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
	function build_task_table(result){
		console.log(result)
		//清空table表格
		$("#task_table tbody").empty();
		var task = result.extend.pageInfo.list;
		$.each(task,function(index,item){
            var payinfoId = $("<td></td>").append(item.payinfoId);
			var payinfoOid=$("<td></td>").append(item.payinfoOid);
			var payinfoPlatform = $("<td></td>").append(item.payinfoPlatform);
			var payinfoMoney = $("<td></td>").append(parseFloat(item.payinfoMoney));
			var payinfoStatus = $("<td></td>").append((item.payinfoStatus === 1 ? '已支付' : '未支付'));
			var payinfoCreatetime = $("<td></td>").append(item.payinfoCreatetime);
			var payinfoMotifytime = $("<td></td>").append(item.payinfoMotifytime);
			var editBtn = $("<button></button>").addClass("btn btn-primary btn-xs edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
			//为编辑按钮添加一个分类id
			editBtn.attr("edit-id",item.payinfoId);
			var delBtn =  $("<button></button>").addClass("btn btn-danger btn-xs delete_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
			//为删除按钮添加一个分类id
			delBtn.attr("del-id",item.payinfoId);
			var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn).append(" ");
			//append方法执行完成以后还是返回原来的元素
			$("<tr></tr>").append(payinfoId)
				.append(payinfoOid)
				.append(payinfoPlatform)
				.append(payinfoMoney)
				.append(payinfoStatus)
				.append(payinfoCreatetime)
				.append(payinfoMotifytime)
				.append(btnTd)
				.appendTo("#task_table tbody");
		});
	}
	//解析显示分页信息
	function build_page_info(result){
		$("#page_info_area").empty();
		$("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页,总"+
				result.extend.pageInfo.pages+"页,总"+
				result.extend.pageInfo.total+"条记录");
		totalRecord = result.extend.pageInfo.total;
		currentPage = result.extend.pageInfo.pageNum;
	}
	//解析显示分页条，点击分页要能去下一页....
	function build_page_nav(result){
		//page_nav_area
		$("#page_nav_area").empty();
		var ul = $("<ul></ul>").addClass("pagination");
		
		//构建元素
		var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
		var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
		if(result.extend.pageInfo.hasPreviousPage == false){
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled");
		}else{
			//为元素添加点击翻页的事件
			firstPageLi.click(function(){
				to_page(1);
			});
			prePageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum -1);
			});
		}
		
		var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
		var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
		if(result.extend.pageInfo.hasNextPage == false){
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		}else{
			nextPageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum +1);
			});
			lastPageLi.click(function(){
				to_page(result.extend.pageInfo.pages);
			});
		}
		
		//添加首页和前一页 的提示
		ul.append(firstPageLi).append(prePageLi);
		//1,2，3遍历给ul中添加页码提示
		$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
			
			var numLi = $("<li></li>").append($("<a></a>").append(item));
			if(result.extend.pageInfo.pageNum == item){
				numLi.addClass("active");
			}
			numLi.click(function(){
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
	$('#task_add_modal_btn').click(function(){
		// 获取分类页面模板
 		loadTpl();
	});
	//新建/编辑任務提交按钮
	$(document).on('click', '#tasksubmit', function(){
		var data = $('form').serializeArray();
		// console.log(data)
		data = data.reduce(function(obj, item) { obj[item.name] = item.value; return obj }, {});
		data.payinfoStatus =  $(":input[name='payinfoStatus']").val();
		console.log(data);
		$.ajax({
			url:"${APP_PATH}/MlfrontPayInfo/save",
			data: JSON.stringify(data),
			dataType:"json",
			contentType: 'application/json',
			type:"POST",
			success:function(result){
				if(result.code == 100){
					alert('新建/编辑成功！');
					window.location.href="${APP_PATH}/MlfrontPayInfo/toMlbackPayInfoList";
				}
			}
		}); 
	});
	//删除任務
 	$("#task_table").on("click",".btn-danger", function() {
 		var data = {payinfoId: $(this).attr('del-id') };
 		$.ajax({
 				url:"${APP_PATH}/MlfrontPayInfo/delete",
 				data: JSON.stringify(data),
				dataType:"json",
				contentType: 'application/json',
 				type:"post",
 				success:function(result){
 					if(result.code == 100){
 						alert('删除成功！');
 						to_page(1);
 					}
 				}
 			});
 	 });
 	 //编辑任务
 	 $("#task_table").on("click",".edit_btn", function() {
		// tab tpl
 		loadTpl();
		// fetch data
		data = {"payinfoId": $(this).attr('edit-id') };
		$.ajax({
			url:"${APP_PATH}/MlfrontPayInfo/getOneMlfrontPayInfoDetail",
			data: data,
			type:"POST",
			success:function(result){
				if(result.code == 100){
					obj = result.extend.mlfrontPayInfoOne;
					tianchong(obj);
				}else{
					alert("联系管理员");
				}
			}
		}); 
		function tianchong(data){

			$(":input[name='payinfoId']").val(data.payinfoId);
			$(":input[name='payinfoOid']").val(data.payinfoOid);
	 		$(":input[name='payinfoPlatform']").val(data.payinfoPlatform);
	 		$(":input[name='payinfoMoney']").val(data.payinfoMoney);
	 		//$(":input[name='payinfoStatus']").prop('checked', (data.payinfoStatus === 1 ? true : false));
	 		$(":input[name='payinfoStatus']").parent().find('span').text((data.payinfoStatus === 1 ? '已支付' : '未支付'));
	 		$(":input[name='payinfoCreatetime']").val(data.payinfoCreatetime);
	 		$(":input[name='payinfoMotifytime']").val(data.payinfoMotifytime);
		}
 	 });

	
	function loadTpl() {
		$('.table-box').load('${APP_PATH}/static/tpl/addPayInfo.html', function() {			
			$('.coupon-status').find('input').on('change', function() {
				var spanEl = $(this).parent().find('span');
				$(this).is(':checked') ? $(this).val(1) && spanEl.text('已支付') : $(this).val(0) && spanEl.text('未支付');
			});
		});
	}
</script>
</body>
</html>