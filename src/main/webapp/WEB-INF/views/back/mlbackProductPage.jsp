<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>产品管理</title>
	<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
	<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<link rel="stylesheet" href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" >
	<script type="text/javascript" src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${APP_PATH }/static/back/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/static/back/css/table.css">

	<%-- <link rel="stylesheet" href="${APP_PATH }/static/back/js/datepicker/datepicker.css"> --%>

	<!-- summernote css -->
	<link rel="stylesheet" type="text/css" href="${APP_PATH }/static/back/js/summernote/codemirror.min.css" />
	<link rel="stylesheet" type="text/css" href="${APP_PATH }/static/back/js/summernote/monokai.min.css">
	<link rel="stylesheet" type="text/css" href="${APP_PATH }/static/back/js/summernote/summernote.css">
	<style>
		tr>td:nth-of-type(3),
		th>td:nth-of-type(3) {
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
					<h2>产品列表</h2>
					<span class="user" id="UEmailSession">*</span>
				</div>
				<div class="content">
					<div class="table-box">
						<!-- operator -->
						<div class="op">
							<a href="#" class="btn btn-default" role="button"> <i class="glyphicon glyphicon-tasks"></i> 产品列表</a>
							<a href="#" id="task_add_modal_btn" class="btn btn-primary" role="button"><i class="glyphicon glyphicon-plus"></i> 新增</a>
						</div>
						<!-- table-content -->
						<div class="table-content">
							<table class="table table-striped table-hover" id="task_table">
								<thead>
									<tr>
										<th>id</th>
										<th>产品图</th>
										<th>产品名</th>
										<th>定价</th>
										<th>历史销量</th>
										<th>上架状态</th>
										<th>标签</th>
										<th>同级别位置</th>
										<th>折扣</th>
										<th>折扣图</th>
										<th>评论num</th>
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

	<script type="text/javascript" src="${APP_PATH }/static/back/js/summernote/codemirror.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/back/js/summernote/xml.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/back/js/summernote/summernote.min.js"></script>

	<script type="text/javascript" src="${APP_PATH }/static/back/js/session.js"></script>
	
	<%-- <script type="text/javascript" src="${APP_PATH }/static/back/js/moment.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/back/js/datepicker/datepicker.js"></script> --%>

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
		//1、页面加载完成以后，直接去发送ajax请求,要到分页数据
		$(function () {
			//去首页
			to_page(1);
		});

		function to_page(pn) {
			$.ajax({
				url: "${APP_PATH}/MlbackProduct/getMlbackProductByPage",
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
				if (item.productLable == 1) {
					productLablestate = '第1展区';
				} else if (item.productLable == 2) {
					productLablestate = '第2展区';
				} else if (item.productLable == 3) {
					productLablestate = '第3展区';
				} else if (item.productLable == 4) {
					productLablestate = '第4展区';
				} else if (item.productLable == 5) {
					productLablestate = '第5展区';
				} else if (item.productLable == 99) {
					productLablestate = '第99展区';
				};
				var productId = $("<td></td>").append(item.productId);
				var imgurl = item.productMainimgurl;
				/*var oraLength = item.productName;
				
				 if(oraLength.length>50){
					oraLength = oraLength.substring(0, 50);
				}
				var productName=$("<td></td>").append(oraLength+'...'); */

				var productName = $("<td></td>").append(item.productName);
				var productCategoryname = $("<td></td>").append(item.productCategoryname);
				var imgurl = item.productMainimgurl;
				var image = '<img src=' + imgurl + ' ' + 'width=40 height=40>';
				var productMainimgurl = $("<td></td>").append(image);


				var productOriginalprice = $("<td></td>").append(item.productOriginalprice);
				var productHavesalenum = $("<td></td>").append(item.productHavesalenum);
				var productStatus = $("<td></td>").append((item.productStatus ? '已上架' : '未上架'));
				var productLable = $("<td></td>").append(productLablestate);
				var productFirsth = $("<td></td>").append(item.productFirsth);
				/* var productActoffid = $("<td></td>").append(item.productActoffid); */
				var productActoffoff = $("<td></td>").append(item.productActoffoff);
				var productActoffIf = $("<td></td>").append((item.productActoffIf ? '展示' : '不展示'));
				var productReviewnum = $("<td></td>").append(item.productReviewnum);
				var productDesc = $("<td></td>").append(item.productDesc);

				var editBtn = $("<button></button>").addClass("btn btn-primary btn-xs edit_btn")
					.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
				//为编辑按钮添加一个自定义的属性，来表示当前productId
				editBtn.attr("edit-id", item.productId);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-xs delete_btn")
					.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
				//为删除按钮添加一个自定义的属性来表示当前删除的productId
				delBtn.attr("del-id", item.productId);
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn).append(" ");
				//var delBtn = 
				//append方法执行完成以后还是返回原来的元素
				$("<tr></tr>").append(productId)
					.append(productMainimgurl)
					.append(productName)
					//.append(productCategoryname)
					.append(productOriginalprice)
					.append(productHavesalenum)
					.append(productStatus)
					.append(productLable)
					.append(productFirsth)
					.append(productActoffoff)
					.append(productActoffIf)
					.append(productReviewnum)
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
		var summernote = null;
		$('#task_add_modal_btn').click(function () {
			$('.table-box').load(
				'${APP_PATH}/static/tpl/addProduct.html',
				function () {
					// 设置归属类
					getCategoryDown();
					// productDesc
					$('.summer-note').summernote();
					// option
					$('.add-item').each(function (i, item) {
						$(item).on('click', function () {
							var table = $(this).parent().parent().parent().parent().find('tbody');
							addHairStyle(table, {})
						})
					});
					saveOption(null);
				})
		});
		//新建编辑任務提交按钮
		$(document).on('click', '#tasksubmit', function () {
			var data = $('form').serializeArray();
			reqData = data.reduce(function (obj, item) {
				obj[item.name] = item.value;
				return obj
			}, {});
			// console.log(reqData)
			//alert(data.productDesc);
			$.ajax({
				url: "${APP_PATH}/MlbackProduct/save",
				data: JSON.stringify(reqData),
				dataType: "json",
				contentType: 'application/json',
				type: "POST",
				success: function (result) {
					if (result.code == 100) {
						alert('操作成功！');
						window.location.href = "${APP_PATH}/MlbackProduct/toMlbackProductPage";
					}
				}
			});
		});
		// 删除该条（id）分类信息
		$("#task_table").on("click", ".btn-danger", function () {
			var data = {
				productId: $(this).attr('del-id')
			};
			$.ajax({
				url: "${APP_PATH}/MlbackProduct/delete",
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
		// fetch all category infos
		var categoryData;

		function getCategoryDown() {
			$.ajax({
				url: "${APP_PATH}/MlbackCategory/getOneMlbackCategoryParentDetail",
				type: "GET",
				success: function (result) {
					if (result.code == 100) {
						function setCategoryDescSelect(el, data) {
							/* var html = '<option value="999">---none---</option>'; */
							var html = '';
							for (var i = 0; i < data.length; i += 1) {
								html += '<option value="' + data[i].categoryId + '">' + data[i].categoryDesc + '</option>';
							}
							el.html(html);
						}
						categoryData = result.extend.mlbackCategorydownList;
						var categoryIdSelect = $('#productCategoryid');
						setCategoryDescSelect(categoryIdSelect, categoryData);
					} else {
						alert("联系管理员");
					}
				}
			});
		}
		// 跳转到编辑页面/并做编辑页面的相关处理
		$("#task_table").on("click", ".edit_btn", function () {
			var productId = parseInt($(this).attr('edit-id'))
			// tab tpl
			$('.table-box').load('${APP_PATH}/static/tpl/addProduct.html', function () {
				// 获取分类列表
				getCategoryDown();
				// init
				initOtherInfo(productId)
				initImgList(productId);
				initOption(productId);

				// cedit
				$('.btn.cedit').on('click', function () {
					// console.log(categoryData);
					var checkBox = $('.check-box');

					if (checkBox.length === 0) {
						checkBox = $('<div class="check-box hide"></div>');
						var html = '';
						html += '<div class="check-container"><div class="check-list">';
						for (var i = 0, len = categoryData.length; i < len; i += 1) {
							var dataId = categoryData[i].categoryId;
							var checkedStatus = productCategoryIdArr.indexOf(String(dataId)) > -1 ? "checked" : "";
							// console.log(checkedStatus);
							html += '<div class="check-item">' +
								'<input type="checkbox" data-id="' + dataId + '" data-name="' + categoryData[i].categoryDesc +
								'" ' + checkedStatus + ' class="checkbox">' +
								'<label>' + "No." + categoryData[i].categoryId + "&nbsp;&nbsp;&nbsp;&nbsp;" + categoryData[i]
								.categoryDesc + '</label>' +
								'</div>';
						}
						html += '</div></div>' +
							'<div class="check-save">' +
							'<a class="btn btn-danger" onclick="boxCancel(event)">取消</a>' +
							'<a class="btn btn-primary" onclick="categorySave(event)">确定</a>' +
							'</div>';
						checkBox.html(html).appendTo($(document.body));

						$('.check-container').niceScroll({
							cursorcolor: "rgba(0,0,0,.3)",
							cursorwidth: "4px",
							cursorborder: "none",
							horizrailenabled: false,
							enablekeyboard: false,
				    });
					}
					checkBox.removeClass('hide');
				});
				// boot upload img
				$('#upload-img-main').on("change", upload);
				$('#upload-img-mainFu').on("change", uploadMainFu);
				$('.upload-img-fu').each(function (i, item) {
					$(item).on("change", function () {
						uploadfu($(this), i + 1);
					})
				});
				$('#upload-img-fu1').on("change", uploadfu);
				// option
				$('.add-item').each(function (i, item) {
					$(item).on('click', function () {
						var table = $(this).parent().parent().parent().parent().find('tbody');
						addHairStyle(table, {})
					});
				});
				saveOption(productId);
			});
		});

		function categorySave(e) {
			var categoryIdArr = [];
			var categoryNameArr = [];

			$(e.target).parents('.check-box').find('input:checked').each(function (index, item) {
				categoryIdArr.push($(item).data('id'));
				categoryNameArr.push($(item).data('name'));
			});
			$(":input[name='productCategoryIdsStr']").val(categoryIdArr.join(','));
			$(":input[name='productCategoryNamesStr']").val(categoryNameArr.join(','));
			$(".product-cate-text").html((categoryNameArr.length ? categoryNameArr.join('\n') : '无'));
			$('.check-box').addClass('hide');
		}

		function boxCancel(e) {
			$(e.target).parents('.check-box').addClass('hide');
		}

		// fetch data about imgs-list
		function initImgList(id) {
			$.ajax({
				url: "${APP_PATH}/MlbackProductImg/getMlbackProductImgListByProductId",
				data: {
					"productId": id
				},
				type: "POST",
				success: function (result) {
					if (result.code == 100) {
						// render data
						tianchongImg(result.extend.mbackProductImgResList);
					} else {
						alert("联系管理员");
					}
				}
			});
		}

		// options fetch data
		function initOption(id) {
			$.ajax({
				url: "${APP_PATH}/MlbackProductSku/getOneProductAllSku",
				data: {
					"productId": id
				},
				type: "POST",
				success: function (result) {
					if (result.code == 100) {
						// render data
						var data = result.extend.mlbackProductSkuResList;
						// console.log(data);
						for (var i = 0; i < data.length; i += 1) {
							var len = 1;
							for (var key in data[i]) {
								if (typeof data[i][key] === 'string' && data[i][key].indexOf(',') > -1) {
									data[i][key] = data[i][key].split(',');
									if (len === 1) {
										len = data[i][key].length;
									}
								}
							}
							// console.log('/////////////', len)
							for (var k = 0; k < len; k += 1) {
								var obj = {};
								for (var key in data[i]) {
									if (Object.prototype.toString.apply(data[i][key]) === "[object Array]") {
										obj[key] = data[i][key][k];
									} else {
										obj[key] = data[i][key];
										// console.log('********');
									}
								}
								// console.log(obj, '*********');
								var tabPane = $('.tab-pane.' + obj.productskuName);
								var productskuIdEl = tabPane.find('.productskuId');
								if (productskuIdEl.val() !== obj.productskuId) {
									productskuIdEl.val(obj.productskuId)
								}
								// console.log($('.tab-pane.' + obj.productskuName))
								tabPane.find('.productskuStatus').val(obj.productskuStatus);
								addHairStyle(tabPane.find('tbody'), obj);
							}
						}
					} else {
						alert("联系管理员");
					}
				}
			});
		}

		// fetch data about other
		function initOtherInfo(id) {
			$.ajax({
				url: "${APP_PATH}/MlbackProduct/getOneMlbackProductDetail",
				data: {
					"productId": id
				},
				type: "POST",
				success: function (result) {
					if (result.code == 100) {
						var obj = result.extend.mlbackProductOne;
						// console.log(obj)
						// render data
						tianchong(obj);

						// boot editor for productDesc
						summernote = $('.summer-note').summernote({
							height: 300,
							codemirror: {
								// codemirror options
								mode: 'text/html',
								htmlMode: true,
								lineNumbers: true,
								theme: 'monokai'
							}
						});
					} else {
						alert("联系管理员");
					}
				}
			});
		}

		var productCategoryIdArr = [];
		// 编辑-回显-数据（不带图片）
		function tianchong(data) {
			$(":input[name='productId']").val(data.productId);
			$(":input[name='productName']").val(data.productName);
			$(":input[name='productColor']").val(data.productColor);
			$(":input[name='productSeo']").val(data.productSeo);
			$(":input[name='productCategoryid']").val(data.productCategoryid);
			$(":input[name='productCategoryIdsStr']").val(data.productCategoryIdsStr);
			$(":input[name='productCategoryNamesStr']").val(data.productCategoryNamesStr);
			$(":input[name='productMetaDesc']").val(data.productMetaDesc);
			$(".product-cate-text").html((data.productCategoryNamesStr ? data.productCategoryNamesStr.split(',').join('\n') : '无'));
			if (data.productCategoryIdsStr && data.productCategoryIdsStr.length) {
				productCategoryIdArr = data.productCategoryIdsStr.split(',');
			}
			if (data.productMainimgurl && data.productMainimgurl.length) {
				$("#upload-img-main").parent().css("background-image", "url(" + data.productMainimgurl + ")");
			}
			if (data.productMainFuimgurl && data.productMainFuimgurl.length) {
				$("#upload-img-mainFu").parent().css("background-image", "url(" + data.productMainFuimgurl + ")");
			}
			//$(":input[name='productMainimgurl']").val(data.productMainimgurl);
			$(":input[name='productOriginalprice']").val(parseFloat(data.productOriginalprice));
			$(":input[name='productHavesalenum']").val(parseFloat(data.productHavesalenum));
			$(":input[name='productStatus']").val(data.productStatus);
			$(":input[name='productLable']").val(data.productLable);
			if (data.productFirsth) {
				$(":input[name='productFirsth']").val(data.productFirsth);
			}
			$(":input[name='productActoffid']").val(data.productActoffid);
			$(":input[name='productActoffoff']").val(data.productActoffoff);
			$(":input[name='productActoffIf']").val(data.productActoffIf);
			$(":input[name='productReviewnum']").val(data.productReviewnum);
			$(".summer-note").html(data.productDesc);
		}


		// 编辑-回显-数据（仅图片）
		function tianchongImg(data) {
			// console.log(data);
			var elImgs = $('.sub-img').find('.upload-img-btn');
			for (var i = 0; i < data.length; i += 1) {
				$(elImgs[data[i].productimgSortOrder - 1]).css("background-image", "url(" + data[i].productimgUrl + ")");
			}

			// productimgUrl  productimgSortOrder
		}

		// upload img 
		function upload() {
			var self = this;
			//实例化一个FormData
			var obj = new FormData();
			obj.append('file', $(this)[0].files[0]);
			// console.log($(this)[0].files[0])
			var productIdUP = $(":input[name='productId']").val();
			if (productIdUP == null) {
				//如果没有pid,弹出"请先输入产品名，保存后再次进入"
				// console.log("productIdUP:"+productIdUP);
				alert("请先输入产品名，保存后从编辑进入");
			} else {
				obj.append('productId', productIdUP);
				$.ajax({
					url: "${APP_PATH}/UpImg/uploadProductImg",
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
						$(self).parent().css({
							"background-image": "url(" + '${APP_PATH }/static/img/product/' + returl + ")"
						});
					}
				});
			}

		}
		// upload img 1-1
		function uploadMainFu() {
			var self = this;
			//实例化一个FormData
			var obj = new FormData();
			obj.append('file', $(this)[0].files[0]);
			// console.log($(this)[0].files[0])
			var productIdUP = $(":input[name='productId']").val();
			if (productIdUP == null) {
				//如果没有pid,弹出"请先输入产品名，保存后再次进入"
				// console.log("productIdUP:"+productIdUP);
				alert("请先输入产品名，保存后从编辑进入");
			} else {
				obj.append('productId', productIdUP);
				$.ajax({
					url: "${APP_PATH}/UpImg/toUploadProductFuImg",
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
						$(self).parent().css({
							"background-image": "url(" + '${APP_PATH }/static/img/product/' + returl + ")"
						});
					}
				});
			}
		}

		// upload img 1-2
		function uploadfu(item, index) {
			var self = this;
			// console.log(self)
			//实例化一个FormData
			var obj = new FormData();
			obj.append('file', item[0].files[0]);
			// console.log($(this)[0].files[0])
			var productIdUP = $(":input[name='productId']").val();
			if (productIdUP == null) {
				//如果没有pid,弹出"请先输入产品名，保存后再次进入"
				// console.log("productIdUP:"+productIdUP);
				alert("请先输入产品名，保存后从编辑进入");
			} else {
				obj.append('productId', productIdUP);
				obj.append('sort', index);
				$.ajax({
					url: "${APP_PATH}/UpImg/uploadProductAllImg",
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
						item.parent().css({
							"background-image": "url(" + '${APP_PATH }/static/img/productAll/' + returl + ")"
						});
					}
				});
			}

		}

		/* option */
		function addHairStyle(parent, data) {
			var tr = $('<tr/>');
			var trHtml = '';
			trHtml += '<td>' +
				'<input class="form-control" data-id="productskuNameDetails" type="text" value="' + (data.productskuNameDetails ?
					data.productskuNameDetails : '') + '">' +
				'</td>' +
				'<td>' +
				'<input class="form-control" data-id="productskuStockDetails" type="text" value="' + (data
					.productskuStockDetails ? data.productskuStockDetails : '') + '">' +
				'</td>' +
				'<td>' +
				'<input class="form-control" data-id="productskuMoneyDetails" type="text" value="' + (data
					.productskuMoneyDetails ? data.productskuMoneyDetails : '') + '">' +
				'</td>' +
				'<td><a class="btn btn-danger" onclick="removeHairStyle(event)"><i class="glyphicon glyphicon-minus"></i></a></td>';

			parent.append(tr.html(trHtml));
		}

		function removeHairStyle(e) {
			var target = e.target;
			var tr = null;
			if (target.nodeName === 'I') {
				tr = $(target).parent().parent().parent();
			}

			if (target.nodeName === 'A') {
				tr = $(target).parent().parent();
			}
			tr.remove();

			/*  $.ajax({}) */
		}

		// option save
		function saveOption(productId) {
			$('#option').find('.btn.save').each(function (i, item) {
				$(item).on('click', function () {
					var productskuName = $(this).data('name');
					var tData = [];
					var productskuStatus = $(this).parent().find('.productskuStatus').val();
					var productskuId = $(this).parent().find('.productskuId').val() || null;
					var trs = $(this).parent().find('tbody tr');
					trs.each(function (i, item) {
						var inputs = $(item).find('input');
						var obj = {};
						for (var i = 0; i < inputs.length; i += 1) {
							obj[$(inputs[i]).data('id')] = $(inputs[i]).val()
						}
						tData.push(obj);
					});
					var nData = tData.reduce(function (obj, item) {
						for (var key in item) {
							obj[key] = obj[key] ? obj[key] + ',' + item[key] : item[key];
						}
						return obj;
					}, {});
					nData.productId = productId;
					nData.productskuId = productskuId;
					nData.productskuName = productskuName;
					nData.productskuStatus = productskuStatus;
					console.log(nData);
					$.ajax({
						url: "${APP_PATH}/MlbackProductSku/save",
						data: JSON.stringify(nData),
						type: "POST",
						dataType: "json",
						contentType: 'application/json',
						success: function (result) {
							if (result.code == 100) {
								alert('MlbackProductSku 保存成功！')
								window.location.href = "${APP_PATH}/MlbackProduct/toMlbackProductPage";
							} else {}
						}
					});
				});
			});
		}
	</script>
</body>

</html>