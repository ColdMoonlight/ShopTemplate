var aside_data = [
 	{ url: '/ShopTemplate/HomePage/toHomePage', name:'首页', icon: "glyphicon-home" },
 	{
		url: 'javascript:;',
		name:'分类管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlbackCategory/toMlbackCategoryPage',
				name:'分类列表',
				icon: "glyphicon-stats"
			}
		]
	},
	{
		url: 'javascript:;',
		name:'产品管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlbackProduct/toMlbackProductPage',
				name:'产品列表',
				icon: "glyphicon-stats"
			},
			{
				url: '/ShopTemplate/MlbackProduct/toMlbackProductSearchPage',
				name:'产品查询',
				icon: "glyphicon-stats"
			}
		]
	},
	{
		url: 'javascript:;',
		name:'营销管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlbackShowArea/toMlbackShowAreaPage',
				name:'展区列表',
				icon: "glyphicon-stats"
			},
			{
				url: '/ShopTemplate/MlbackActShowPro/toMlbackActShowProPage',
				name:'活动品管理',
				icon: "glyphicon-stats"
			},
			{
				url: '/ShopTemplate/MlbackCountDown/toMlbackCountDownPage',
				name:'倒计时管理',
				icon: "glyphicon-stats"
			},
			{
				url: '/ShopTemplate/MlbackSlide/toMlbackSlidePage',
				name:'轮播管理',
				icon: "glyphicon-stats"
			}
			
		]
	},
	{
		url: 'javascript:;',
		name:'地域运费定价管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlbackAreafreight/toMlbackAreafreightPage',
				name:'地域运费列表',
				icon: "glyphicon-stats"
			}
		]
	},
	{
		url: 'javascript:;',
		name:'优惠券管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlbackCoupon/toMlbackCouponPage',
				name:'优惠券列表',
				icon: "glyphicon-stats"
			}
		]
	},
	{
		url: 'javascript:;',
		name:'底部导航管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlbackFootNav/toMlbackFootNavPage',
				name:'底导航管理列表',
				icon: "glyphicon-stats"
			}
		]
	},
	{
		url: 'javascript:;',
		name:'PayInfo单管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlfrontPayInfo/toMlbackPayInfoList',
				name:'PayInfo单列表',
				icon: "glyphicon-stats"
			}
		]
	},
	{
		url: 'javascript:;',
		name:'order单管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlfrontOrder/toMlbackOrderPage',
				name:'order单管理',
				icon: "glyphicon-stats"
			}
		]
	},
	{
		url: 'javascript:;',
		name:'加购管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlbackCart/toMlbackCartPage',
				name:'加购管理列表',
				icon: "glyphicon-stats"
			}
		]
	},
	{
		url: 'javascript:;',
		name:'用户管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlfrontUser/toMlbackUserPage',
				name:'用户管理列表',
				icon: "glyphicon-stats"
			}
		]
	},
	{
		url: 'javascript:;',
		name:'渠道管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlbackChannel/toMlbackChannelPage',
				name:'渠道管理列表',
				icon: "glyphicon-stats"
			}
		]
	},
	{
		url: 'javascript:;',
		name:'评论管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlfrontReview/toMlfrontReviewPage',
				name:'评论管理列表',
				icon: "glyphicon-stats"
			}
		]
	},
	{
		url: 'javascript:;',
		name:'邮件管理',
		icon: "glyphicon-chevron-right",
		children: [
			{
				url: '/ShopTemplate/MlbackEmail/toMlbackEmailPage',
				name:'邮件管理列表',
				icon: "glyphicon-stats"
			}
		]
	}/*,
 	{ url: '/ShopTemplate//HomePage/toAllEndTaskPage', name:'完结列表', icon: "glyphicon-stats" }*/
 ];
 
$('.aside-bar').Side(aside_data);