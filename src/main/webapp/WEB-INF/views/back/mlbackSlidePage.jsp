<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>活动品管理</title>
	<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
	<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<link rel="stylesheet" href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/static/back/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/static/back/css/table.css">
	<%-- <link rel="stylesheet" href="${APP_PATH }/static/back/js/datepicker/datepicker.css"> --%>
	<style>
		tr>td:nth-last-of-type(2),
		th>td:nth-last-of-type(2) {
			max-width: 100px;
		}
	</style>
</head>

<body>
	<div class="container">
		<div class="aside-bar nicescroll"></div>
		<div class="main-body">
			<div class="main-box nicescroll">
				<div class="header">
					<h2>轮播管理</h2>
					<span class="user" id="UEmailSession">*</span>
				</div>
				<div class="content">
					<div class="table-box">
						<!-- operator -->
						<div class="op">
							<a href="#" class="btn btn-default" role="button"> <i class="glyphicon glyphicon-tasks"></i> 活动品列表</a>
							<a href="#" id="task_add_modal_btn" class="btn btn-primary" role="button"><i
									class="glyphicon glyphicon-plus"></i> 新增</a>
						</div>
						<!-- table-content -->
						<div class="table-content">
							<table class="table table-striped table-hover" id="task_table">
								<thead>
									<tr>
										<th>id</th>
										<th>轮播name</th>
										<th>展示位</th>
										<th>同级次序</th>
										<th>手机图</th>
										<th>wap状态</th>
										<th>PC图</th>
										<th>pc状态</th>
										<th>产品id</th>
										<th>产品名字</th>
										<th>产品Seo</th>
										<th>if可点进</th>
										<th>修改时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody> </tbody>
							</table>
						</div>
						<!-- 显示分页信息 -->
						<div class="row">
							<!--分页文字信息  -->
							<div class="col-md-6" id="page_info_area"></div>
							<!-- 分页条信息 -->
							<div class="col-md-6" id="page_nav_area">

							</div>
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
		console.log("adminAccname:" + adminAccname);
		$("#UEmailSession").html(adminAccname);
	</script>

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
				url: "${APP_PATH}/MlbackSlide/getMlbackSlideByPage",
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
			//清空table表格
			$("#task_table tbody").empty();
			var task = result.extend.pageInfo.list;
			$.each(task, function (index, item) {
				
				var slideAreaStr = "";
				if (item.slideArea == 0) {
					slideAreaStr = '未选择位置';
				} else if (item.slideArea == 1) {
					slideAreaStr = '首页活动';
				} else if (item.slideArea == 2) {
					slideAreaStr = '第2活动';
				} else if (item.slideArea == 3) {
					slideAreaStr = '第3活动';
				} else if (item.slideArea == 4) {
					slideAreaStr = '第4活动';
				} else if (item.slideArea == 5) {
					slideAreaStr = '第5活动';
				};
				
				var slideId = $("<td></td>").append(item.slideId);
				var slideName = $("<td></td>").append(item.slideName);
				var slideArea = $("<td></td>").append(slideAreaStr);
				var slideFirthNum = $("<td></td>").append(item.slideFirthNum);
				
				var imgurl = item.slideWapimgurl;
				var image = '<img src=' + imgurl + ' ' + 'width=50 height=50>';
				var slideWapimgurl = $("<td></td>").append(image);
				var slideWapstatus = $("<td></td>").append((item.slideWapstatus === 1 ? '展示' : '不展示'));
				var imgurlpc = item.slidePcimgurl;
				var imagepc = '<img src=' + imgurlpc + ' ' + 'width=50 height=50>';
				var slidePcimgurl = $("<td></td>").append(imagepc);
				var slidePcstatus = $("<td></td>").append((item.slidePcstatus === 1 ? '展示' : '不展示'));
				
				var slideProid = $("<td></td>").append(item.slideProid);
				var slideProname = $("<td width=400></td>").append(item.slideProname);
				var slideSeoname = $("<td></td>").append(item.slideSeoname);
				var slideIfinto = $("<td></td>").append((item.slideIfinto === 1 ? '可点进' : '不可点进'));
				var slideMotifytime = $("<td></td>").append(item.slideMotifytime);
				var editBtn = $("<button></button>").addClass("btn btn-primary btn-xs edit_btn")
					.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
				//为编辑按钮添加一个分类id
				editBtn.attr("edit-id", item.slideId);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-xs delete_btn")
					.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
				//为删除按钮添加一个分类id
				delBtn.attr("del-id", item.slideId);
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn).append(" ");
				//append方法执行完成以后还是返回原来的元素
				$("<tr></tr>").append(slideId)
					.append(slideName)
					.append(slideArea)
					.append(slideFirthNum)
					.append(slideWapimgurl)
					.append(slideWapstatus)
					.append(slidePcimgurl)
					.append(slidePcstatus)
					.append(slideProid)
					.append(slideProname)
					.append(slideSeoname)
					.append(slideIfinto)
					.append(slideMotifytime)
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
			loadTpl()
		});
		//新建/编辑任務提交按钮
		$(document).on('click', '#tasksubmit', function () {
			var data = $('form').serializeArray();
			data = JSON.stringify(data.reduce(function (obj, item) {
				obj[item.name] = item.value;
				return obj
			}, {}));
			$.ajax({
				url: "${APP_PATH}/MlbackSlide/save",
				data: data,
				dataType: "json",
				contentType: 'application/json',
				type: "POST",
				success: function (result) {
					if (result.code == 100) {
						alert('添加成功！');
						window.location.href = "${APP_PATH}/MlbackSlide/toMlbackSlidePage";
					}
				}
			});
		});
		//删除任務
		$("#task_table").on("click", ".btn-danger", function () {
			var data = {
					slideId: $(this).attr('del-id')
			};
			$.ajax({
				url: "${APP_PATH}/MlbackSlide/delete",
				data: JSON.stringify(data),
				dataType: "json",
				contentType: 'application/json',
				type: "post",
				success: function (result) {
					if (result.code == 100) {
						alert('删除成功！');
						to_page(1);
					}
				}
			});
		});
		var CategoryHash = {}
		// 获取归属类下拉
		function getCategoryDown() {
			$.ajax({
				url: "${APP_PATH}/MlbackProduct/getMlbackProductAllList",
				type: "GET",
				async: false,
				success: function (result) {
					if (result.code == 100) {
						function setCategoryDescSelect(el, data) {
							var html = '<option value="-1">---无绑定产品---</option>';
							for (var i = 0; i < data.length; i += 1) {
								html += '<option value="' + data[i].productId + '">'+ data[i].productId+"    " + data[i].productName + '</option>';
							}
							el.html(html);
						}
						objparentList = result.extend.mlbackProductResList;
						// console.log(objparentList);
						var categoryIdSelect = $('#slideProid');
						setCategoryDescSelect(categoryIdSelect, objparentList);
					} else {
						alert("联系管理员");
					}
				}
			});
		}

		function loadTpl() {
			$('.table-box').load('${APP_PATH}/static/tpl/addSlide.html', function () {
				// 设置归属类
				getCategoryDown();
			});
		}

		//编辑任务
		$("#task_table").on("click", ".edit_btn", function () {
			// tab tpl
			loadTpl()
			// 设置归属类
			getCategoryDown();
			// fetch data
			data = {
				"slideId": $(this).attr('edit-id')
			};
			$.ajax({
				url: "${APP_PATH}/MlbackSlide/getOneMlbackSlideDetail",
				data: data,
				type: "POST",
				success: function (result) {
					if (result.code == 100) {
						obj = result.extend.mlbackSlideOne;
						// console.log(obj);
						tianchong(obj);
					} else {
						alert("联系管理员");
					}
				}
			});

			function tianchong(data) {
				$(":input[name='slideId']").val(data.slideId);
				$(":input[name='slideName']").val(data.slideName);
				$(":input[name='slideArea']").val(data.slideArea);
				$(":input[name='slideFirthNum']").val(data.slideFirthNum);
				if (data.slideWapimgurl && data.slideWapimgurl.length) {
					var el = $(".upload-img-btn.img");
					el.attr("style", "background-repeat: no-repeat; background-position: center; background-size: 100%;");
					setImage(el, data.slideWapimgurl);
				}
				$(":input[name='slideWapstatus']").val(data.slideWapstatus);
				if (data.slidePcimgurl && data.slidePcimgurl.length) {
					var el2 = $(".upload-img-btn.img2");
					el2.attr("style", "background-repeat: no-repeat; background-position: center; background-size: 100%;");
					setImage(el2, data.slidePcimgurl);
				}
				$(":input[name='slidePcstatus']").val(data.slidePcstatus);
				$(":input[name='slideProid']").val(data.slideProid);
				$(":input[name='slideIfinto']").val(data.slideIfinto);
				$(":input[name='slideMotifytime']").val(data.slideMotifytime);
				
				var slideProid = $("<td></td>").append(data.slideProid);
				var slideIfinto = $("<td></td>").append((data.slideIfinto === 1 ? '可点进' : '不可点进'));
				var slideMotifytime = $("<td></td>").append(data.slideMotifytime);
			}

		});

		$(document.body).on("change", "#file1", upload);
		$(document.body).on("change", "#file2", uploadMainFu);

		function upload() {
			var self = this;
			var obj = new FormData();
			obj.append('file', $(this)[0].files[0]);
			var slideIdUP = $(":input[name='slideId']").val();
			//console.log("categoryIdUP:"+categoryIdUP);
			if (slideIdUP == '') {
				//如果没有pid,弹出"请先输入产品名，保存后再次进入"
				// console.log("productIdUP:"+productIdUP);
				alert("请先输入类名，保存后从编辑进入");
			} else {
				obj.append('slideId', slideIdUP);
				$.ajax({
					url: "${APP_PATH}/UpImg/uploadSlideWapImg",
					type: "post",
					dataType: "json",
					cache: false,
					data: obj,
					processData: false, // 不处理数据
					contentType: false, // 不设置内容类型
					success: function (data) {
						// 设置背景为我们选择的图片
						// console.log(data);
						var returl = data.extend.uploadUrl;
						// $(self).parent().css({ "background-image": "url("+'${APP_PATH }/static/img/category/'+returl+")" });  
						setImage($(self).parent(), returl);
					}
				});
			}
		}

		function setImage(el, url) {
			var img = new Image();
			url = url.indexOf('://') > -1 ? url : '${APP_PATH }/static/img/Slide/' + url;
			img.src = url;
			img.onload = function () {
				var winW = $('#categoryTabContent').width();
				var imgW = img.width;
				var imgH = img.height;

				if (imgW >= winW) {
					el.css({
						'width': '100%',
						'height': Math.floor(img.height * $('#categoryTabContent').width() / img.width) + 'px',
						'backgroundImage': 'url(' + url + ')',
						'backgroundSize': '100%'
					});
				} else {
					el.css({
						'width': imgW + 'px',
						'height': imgH + 'px',
						'backgroundImage': 'url(' + url + ')',
						'backgroundSize': '100%'
					});
				}
			}
		}

		function uploadMainFu() {
			var self = this;
			//实例化一个FormData
			var obj = new FormData();
			obj.append('file', $(this)[0].files[0]);
			// console.log($(this)[0].files[0])
			var slideIdUP = $(":input[name='slideId']").val();
			if (slideIdUP == "") {
				// 如果没有pid,弹出"请先输入产品名，保存后再次进入"
				// console.log("productIdUP:"+productIdUP);
				alert("请先输入产品名，保存后从编辑进入");
			} else {
				obj.append('slideId', slideIdUP);
				$.ajax({
					url: "${APP_PATH}/UpImg/uploadSlidePcImg",
					type: "post",
					dataType: "json",
					cache: false,
					data: obj,
					processData: false, // 不处理数据
					contentType: false, // 不设置内容类型
					success: function (data) {
						//设置背景为我们选择的图片
						// console.log(data);
						var returl = data.extend.uploadUrl;
						// $(self).parent().css({ "background-image": "url("+'${APP_PATH }/static/img/category/'+returl+")" });
						setImage($(self).parent(), returl);
					}
				});
			}
		}
	</script>
</body>

</html>