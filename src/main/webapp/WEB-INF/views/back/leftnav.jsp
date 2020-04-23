<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<ul>
  <li>
    <div class="item">
      <a href="${APP_PATH }/HomePage/toHomePage">
        <i class="glyphicon glyphicon-home"></i>
        <span class="text">首页</span></a>
    </div>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">分类管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackCategory/toMlbackCategoryPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">分类列表</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">产品管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackProduct/toMlbackProductPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">产品列表</span></a>
        </div>
      </li>
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackProduct/toMlbackProductSearchPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">产品查询</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">营销管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackShowArea/toMlbackShowAreaPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">展区列表</span></a>
        </div>
      </li>
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackActShowPro/toMlbackActShowProPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">活动品管理</span></a>
        </div>
      </li>
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackCountDown/toMlbackCountDownPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">倒计时管理</span></a>
        </div>
      </li>
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackSlide/toMlbackSlidePage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">轮播管理</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">video管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackVideoShowArea/toMlbackVideoShowAreaPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">video展区列表</span></a>
        </div>
      </li>
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackVideo/toMlbackVideoPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">video管理</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">地域运费定价管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackAreafreight/toMlbackAreafreightPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">地域运费列表</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">优惠券管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackCoupon/toMlbackCouponPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">优惠券列表</span></a>
        </div>
      </li>
    </ul>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackCouponDescTitle/toMlbackCouponDescTitlePage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">优惠券Title列表</span></a>
        </div>
      </li>
    </ul>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackCouponDescDetail/toMlbackCouponDescDetailPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">优惠Code简述</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">底部导航管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackFootNav/toMlbackFootNavPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">底导航管理列表</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">PayInfo单管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlfrontPayInfo/toMlbackPayInfoList">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">PayInfo单列表</span></a>
        </div>
      </li>
    </ul>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlfrontPayInfo/toMlbackPayInfohighList">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">Pay高级管理</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">order单管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlfrontOrder/toMlbackOrderPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">order单管理</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">加购管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackCart/toMlbackCartPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">加购管理列表</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">用户管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlfrontUser/toMlbackUserPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">用户管理列表</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">渠道管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackChannel/toMlbackChannelPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">渠道管理列表</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">评论管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlfrontReview/toMlfrontReviewPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">评论管理列表</span></a>
        </div>
        <div class="item">
          <a href="${APP_PATH }/MlfrontReview/toMlfrontReviewManage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">评论管理2</span></a>
        </div>
      </li>
    </ul>
  </li>
  <li>
    <div class="item">
      <a href="javascript:;">
        <i class="glyphicon glyphicon-chevron-right"></i>
        <span class="text">通知管理</span></a>
    </div>
    <ul class="children">
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackEmail/toMlbackEmailPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">邮件管理列表</span></a>
        </div>
      </li>
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackShipEmail/toMlbackShipEmailPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">物流管理列表</span></a>
        </div>
      </li>
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackAbandonPurchase/toMlbackAbandonPurchasePage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">弃购邮件列表</span></a>
        </div>
      </li>
      <li>
        <div class="item">
          <a href="${APP_PATH }/MlbackOrderStateEmail/toMlbackOrderStateEmailPage">
            <i class="glyphicon glyphicon-stats"></i>
            <span class="text">后台通知邮件列表</span></a>
        </div>
      </li>
    </ul>
  </li>
</ul>

<script>
	$(function(){
		$(".aside-bar .item").click(function(){
			$(this).toggleClass("active")
			$(this).parents("li").find(".children").css("display","block")
			
		})
	})
</script>



		