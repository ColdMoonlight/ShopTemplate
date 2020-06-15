package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlbackCouponService;
import com.atguigu.service.MlbackShipEmailService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontCartItemService;
import com.atguigu.service.MlfrontCartService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;

@Controller
@RequestMapping("/MlfrontOrderList")
public class MlfrontOrderListController {
		
	@Autowired
	MlfrontOrderService mlfrontOrderService;
	
	@Autowired
	MlfrontAddressService mlfrontAddressService;
	
	@Autowired
	MlbackAreafreightService mlbackAreafreightService;
	
	@Autowired
	MlfrontOrderItemService mlfrontOrderItemService;
	
	@Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
	
	@Autowired
	MlfrontCartItemService mlfrontCartItemService;
	
	@Autowired
	MlfrontCartService mlfrontCartService;
	
	@Autowired
	MlbackCouponService mlbackCouponService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackShipEmailService mlbackShipEmailService;
	
	/**
	 * 1.0	UseNow	1219
	 * to	全部订单-分状态查询
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/selectOrderlistBySearch",method=RequestMethod.POST)
	@ResponseBody
	public Msg selectOrderlistBySearch(@RequestParam(value = "pn", defaultValue = "1") Integer pn,@RequestParam(value = "orderStatus") Integer orderStatus,HttpSession session) {

		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		
		System.out.println("---------------loginUser---------------");
		System.out.println(loginUser);
		System.out.println("---------------loginUser---------------");
		Integer Uid = loginUser.getUserId();
		MlfrontOrder mlfrontOrder = new MlfrontOrder();
		mlfrontOrder.setOrderUid(Uid);
		if(orderStatus==999){
			System.out.println("orderStatus==999,不筛选状态");
		}else{
			mlfrontOrder.setOrderStatus(orderStatus);//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款
		}
		int PagNum = 5;
		PageHelper.startPage(pn, PagNum);
		List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectOrderListByUidAndStatus(mlfrontOrder);
		PageInfo page = new PageInfo(mlfrontOrderList, PagNum);
		mlfrontOrderList = page.getList();
		//2遍历mlfrontOrderList，3读取每个的orderItemIdStr,4切割，5再遍历产寻单条的获取orderItemId对象
		String orderitemidstr="";
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemRes = new MlfrontOrderItem();
		List<MlfrontOrderItem> mlfrontOrderItemList = new ArrayList<MlfrontOrderItem>();
		List<MlfrontOrderItem> mlfrontOrderItemReturnList = new ArrayList<MlfrontOrderItem>();
		List<Integer> sizeList = new ArrayList<Integer>();
		for(MlfrontOrder mlfrontOrderOne:mlfrontOrderList){
			orderitemidstr = mlfrontOrderOne.getOrderOrderitemidstr();
			String orderitemidArr[] = orderitemidstr.split(",");
			Integer size = orderitemidArr.length;
			sizeList.add(size);
			for(int i=0;i<orderitemidArr.length;i++){
				String orderitemid = orderitemidArr[i];
				Integer orderitemidInt = Integer.valueOf(orderitemid);
				mlfrontOrderItemReq.setOrderitemId(orderitemidInt); 
				mlfrontOrderItemList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
				mlfrontOrderItemRes = mlfrontOrderItemList.get(0);
				mlfrontOrderItemReturnList.add(mlfrontOrderItemRes);
			}
		}
		return Msg.success().add("pageInfo", page).add("sizeList", sizeList).add("mlfrontOrderItemReturnList", mlfrontOrderItemReturnList);
	}
}
