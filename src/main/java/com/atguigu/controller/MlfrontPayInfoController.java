package com.atguigu.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.MlPaypalShipAddress;
import com.atguigu.bean.MlbackAddCheakoutViewDetail;
import com.atguigu.bean.MlbackAddOrderViewDetail;
import com.atguigu.bean.MlbackAddPayinfoViewDetail;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontCartItem;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.Msg;
import com.atguigu.bean.PageTimeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlPaypalShipAddressService;
import com.atguigu.service.MlbackAddOrderViewDetailService;
import com.atguigu.service.MlbackAddPayinfoViewDetailService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.service.MlfrontUserService;
import com.atguigu.utils.DateUtil;


@Controller
@RequestMapping("/MlfrontPayInfo")
public class MlfrontPayInfoController {
		
	@Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
	
	@Autowired
	MlfrontOrderService mlfrontOrderService;
	
	@Autowired
	MlfrontOrderItemService mlfrontOrderItemService;
	
	@Autowired
	MlfrontUserService mlfrontUserService;
	
	@Autowired
	MlbackAreafreightService mlbackAreafreightService;
	
	@Autowired
	MlfrontAddressService mlfrontAddressService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlPaypalShipAddressService mlPaypalShipAddressService;
	
	@Autowired
	MlbackAddPayinfoViewDetailService mlbackAddPayinfoViewDetailService;
	
	@Autowired
	MlbackAddOrderViewDetailService mlbackAddOrderViewDetailService;
	
	/**
	 * 1.0	UseNow	0505
	 * toMlfrontPayInfoSuccess列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlfrontPayInfoSuccess")
	public String toMlfrontPayInfoSuccess() throws Exception{
	
		return "mfront/paySuccess";
	}
	
	/**
	 * 2.0	UseNow	0505
	 * toMlfrontPayInfofail列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlfrontPayInfofail")
	public String toMlfrontPayInfofail() throws Exception{
	
		return "mfront/payFail";
	}
	
	/**
	 * 3.0	UseNow	0505
	 * toMlfrontPayInfoList列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackPayInfoList")
	public String toMlbackPayInfoList() throws Exception{
	
		return "back/mlbackPayInfoPage";
	}
	
	/**
	 * 4.0	UseNow	0505
	 * to	MlfrontOrder列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/getMlfrontPayInfoByDate",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlfrontPayInfoByDate(HttpServletResponse rep,HttpServletRequest res,HttpSession session ,@RequestBody PageTimeVo pageTime){
	
		Integer pn = pageTime.getPn();
		if(pn==null){
			pn=1;
		}
		String beginTime = pageTime.getBeginTime();
		String endTime = pageTime.getEndTime();
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoCreatetime(beginTime);
		mlfrontPayInfoReq.setPayinfoMotifytime(endTime);
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlfrontPayInfo> mlfrontPayInfoReqList = mlfrontPayInfoService.selectMlfrontPayInfoByDate(mlfrontPayInfoReq);
			Integer countNumber =mlfrontPayInfoReqList.size();
			PageInfo page = new PageInfo(mlfrontPayInfoReqList, PagNum);
			return Msg.success().add("pageInfo", page).add("countNumber", countNumber).add("resMsg", "付款单数查询成功");
	}
	
	/**5.0	useOn	0505
	 * 分类MlfrontPayInfo列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlfrontPayInfoByPage")
	@ResponseBody
	public Msg getMlfrontPayInfoWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
		int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoAll();
			PageInfo page = new PageInfo(mlfrontPayInfoList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**6.0	useOn	0505
	 * MlfrontPayInfo	insert
	 * @param MlfrontPayInfo
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlfrontPayInfo mlfrontPayInfo){
		//接受参数信息
		System.out.println("mlfrontPayInfo:"+mlfrontPayInfo);
		//取出id
		System.out.println(1);
		Integer payinfoIdId = mlfrontPayInfo.getPayinfoId();
		String nowTime = DateUtil.strTime14s();
		mlfrontPayInfo.setPayinfoMotifytime(nowTime);
		if(payinfoIdId==null){
			//无id，insert
			mlfrontPayInfo.setPayinfoCreatetime(nowTime);;
			int intResult = mlfrontPayInfoService.insertSelective(mlfrontPayInfo);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			int intResult = mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfo);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "更新成功");
			
		}		
	}
	
	/**7.0	useOn	0505
	 * MlfrontPayInfo	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlfrontPayInfo mlfrontPayInfo){
		//接收id信息
		Integer payinfoIdId = mlfrontPayInfo.getPayinfoId();
		int intResult = mlfrontPayInfoService.deleteByPrimaryKey(payinfoIdId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	
	/**
	 * 8.0	useOn	0505
	 * 查看单条类目的详情细节
	 * @param MlfrontPayInfo
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontPayInfoDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontPayInfoDetail(@RequestParam(value = "payinfoId") Integer payinfoId){
		
		//接受payinfoId
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoId(payinfoId);
		//1.查询本条pay支付单
		List<MlfrontPayInfo> mlfrontPayInfoResList =mlfrontPayInfoService.selectMlfrontPayInfoById(mlfrontPayInfoReq);
		MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
		if(mlfrontPayInfoResList.size()>0){
			mlfrontPayInfoOne =mlfrontPayInfoResList.get(0);
			//8.01统计PayInfo成交单数
			calcAddPayInfoView(mlfrontPayInfoOne);
		}else{
			mlfrontPayInfoOne = null;
		}
		//2.获取本条单子的orderId，2.1查询本条的order详情;2.2从详情中拿到addressid;2.3orderItemIDStr;2.4uid信息，历史购买次数;
		//2.1查询本条的order详情;
		Integer payinfoOid = mlfrontPayInfoOne.getPayinfoOid();
		MlfrontOrder mlfrontOrderPay = new MlfrontOrder();
		mlfrontOrderPay.setOrderId(payinfoOid);
		List<MlfrontOrder> mlfrontOrderPayResList= mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderPay);
		MlfrontOrder mlfrontOrderPayOneRes = mlfrontOrderPayResList.get(0);
		//8.02统计PayInfo中的orderitem成交单数
		calcAddOrderView(mlfrontOrderPayOneRes);
		//2.2从详情中拿到addressid;
		Integer addressinfoId = mlfrontOrderPayOneRes.getAddressinfoId();
		MlfrontAddress MlfrontAddressReq = new MlfrontAddress();
		MlfrontAddressReq.setAddressId(addressinfoId);
		List<MlfrontAddress> MlfrontAddressList = mlfrontAddressService.selectMlfrontAddressById(MlfrontAddressReq);
		MlfrontAddress mlfrontAddressOne = MlfrontAddressList.get(0);
		//2.3从详情中拿到orderItemIDStr;
		String orderItemIdsStr = mlfrontOrderPayOneRes.getOrderOrderitemidstr();
		List<MlfrontOrderItem>  mlfrontOrderItemList = new ArrayList<MlfrontOrderItem>();
		MlfrontOrderItem mlfrontOrderItemOne = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemOneReq = new MlfrontOrderItem();
		String orderItemIdStrArr [] = orderItemIdsStr.split(",");
		String orderItemIdStr = "";
		Integer orderItemIdInt = 0;
		for(int i =0;i<orderItemIdStrArr.length;i++){
			orderItemIdStr = orderItemIdStrArr[i];
			orderItemIdInt = Integer.parseInt(orderItemIdStr);
			mlfrontOrderItemOneReq.setOrderitemId(orderItemIdInt);
			List<MlfrontOrderItem> mlfrontOrderItemResList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemOneReq);
			mlfrontOrderItemOne = mlfrontOrderItemResList.get(0);
			mlfrontOrderItemList.add(mlfrontOrderItemOne);
		}
		//2.4uid信息
		Integer uid = mlfrontOrderPayOneRes.getOrderUid();
		MlfrontUser mlfrontUserOne = new MlfrontUser();
		if(uid==null){
			mlfrontUserOne = null;
		}else{
			MlfrontUser mlfrontUserReq = new MlfrontUser();
			mlfrontUserReq.setUserId(uid);
			List<MlfrontUser> mlfrontUserList = mlfrontUserService.selectMlfrontUser(mlfrontUserReq);
			mlfrontUserOne = mlfrontUserList.get(0);
		}
		//3.获取本payinfoid的paypal_shippingaddress地址;
		MlPaypalShipAddress mlPaypalShipAddressReq = new MlPaypalShipAddress();
		String shippingaddressPayinfoid=payinfoId+"";
		mlPaypalShipAddressReq.setShippingaddressPayinfoid(shippingaddressPayinfoid);
		List<MlPaypalShipAddress> mlPaypalShipAddressResList =mlPaypalShipAddressService.selectMlPaypalShipAddressByPayinfoid(mlPaypalShipAddressReq);
		MlPaypalShipAddress mlPaypalShipAddressRes = new MlPaypalShipAddress();
		if(mlPaypalShipAddressResList.size()==0){
			System.out.println("老数据,没有payment的返回地址");
		}else{
			mlPaypalShipAddressRes = mlPaypalShipAddressResList.get(0);
		}
		//完毕回传
		return Msg.success().add("resMsg", "查看单条mlfrontPayInfoOne的详情细节完毕")
					.add("mlfrontPayInfoOne", mlfrontPayInfoOne).add("mlfrontOrderPayOneRes", mlfrontOrderPayOneRes)
					.add("mlfrontAddressOne", mlfrontAddressOne).add("mlfrontOrderItemList", mlfrontOrderItemList)
					.add("mlfrontUserOne", mlfrontUserOne).add("mlPaypalShipAddressOne", mlPaypalShipAddressRes);
	}
	
	
	
	/**9.0	useOn	0505
	 * MlfrontPayInfo	get
	 * successPayinfoId
	 * @param MlfrontPayInfo
	 */
	@RequestMapping(value="/getsuccessPayinfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg getsuccessPayinfo(HttpSession session, HttpServletResponse rep,HttpServletRequest res){
		
		Integer payinfoId = (Integer) session.getAttribute("payinfoId");
		System.out.println("****payinfoId****:"+payinfoId);
		
		//从session中取出,付款成功的参数successPayinfoId,successOrderId
		Integer successOrderId =(Integer) session.getAttribute("successOrderId");
		if(successOrderId==null){
			return Msg.success().add("resMsg", "返回成功").add("mlfrontOrderOne", null);
		}else{
			//封装参数MlfrontOrder，查回本条Order信息数据
			MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
			mlfrontOrderReq.setOrderId(successOrderId);
			List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq);
			MlfrontOrder mlfrontOrderOne = mlfrontOrderList.get(0);
			return Msg.success().add("resMsg", "更新成功").add("mlfrontOrderOne", mlfrontOrderOne).add("payinfoId", payinfoId);
		}
	}
	
	/**10.0	useOn	0505
	 * MlfrontPayInfo	insert
	 * @param MlfrontPayInfo
	 */
	@RequestMapping(value="/updateShipStatus",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateShipStatus(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlfrontPayInfo mlfrontPayInfo){
		//接受参数信息
		System.out.println("mlfrontPayInfo:"+mlfrontPayInfo);
		//取出id
		Integer payinfoIdIn = mlfrontPayInfo.getPayinfoId();
//		String nowTime = DateUtil.strTime14s();
//		mlfrontPayInfo.setPayinfoMotifytime(nowTime);
		mlfrontPayInfo.setPayinfoStatus(3);//0未支付		1已支付	2已审核	3已发货
		//有id，update
		int intResult = mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfo);
		
		MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
		mlfrontPayInfoOne.setPayinfoId(payinfoIdIn);
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoById(mlfrontPayInfoOne);
		MlfrontPayInfo mlfrontPayInfoResOne = mlfrontPayInfoList.get(0);
		System.out.println(intResult);
		return Msg.success().add("resMsg", "更新成功").add("mlfrontPayInfoOne", mlfrontPayInfoResOne);
	}
	
	
	/**11.0	useOn	0505
	 * MlfrontPayInfo	getFailTimes
	 * @param MlfrontPayInfo
	 */
	@RequestMapping(value="/getFailTimes",method=RequestMethod.POST)
	@ResponseBody
	public Msg getFailTimes(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		
		Integer payFailTimes = (Integer) session.getAttribute("payFailTimes");
		//接受参数信息
		System.out.println("session.getId():"+session.getId()+"payFailTimes:"+payFailTimes);
		
		return Msg.success().add("resMsg", "查询payFailTimes成功").add("payFailTimes", payFailTimes);
	}
	
	/**8.01
	 * 记录实际成交单的结算情况(成家单id+成交金额)
	 * */
	private void calcAddPayInfoView(MlfrontPayInfo mlfrontPayInfoOne) {
		
		String nowTime = DateUtil.strTime14s();

		Integer payinfoId = mlfrontPayInfoOne.getPayinfoId();
		String payinfoIdStr = payinfoId+"";
		BigDecimal payinfoMoney = mlfrontPayInfoOne.getPayinfoMoney();
		DecimalFormat df1 = new DecimalFormat("0.00");
		String payinfoMoneyStr = df1.format(payinfoMoney);
		//准备参数信息
		MlbackAddPayinfoViewDetail mlbackAddPayinfoViewDetailReq = new MlbackAddPayinfoViewDetail();
		
		
		//先查询该是否已经被记录过,没查到新增;查到跳过;
		mlbackAddPayinfoViewDetailReq.setAddpayinfoviewdetailPayinfoid(payinfoIdStr);
		
		List<MlbackAddPayinfoViewDetail> MlbackAddPayinfoViewDetailList = mlbackAddPayinfoViewDetailService.selectMlbackAddPayinfoViewDetailByPayinfoid(mlbackAddPayinfoViewDetailReq);
		
		if(MlbackAddPayinfoViewDetailList.size()>0){
			System.out.println("客户违规操作,说明一下,不在执行payinfo的插入操作了");
		}else{
			mlbackAddPayinfoViewDetailReq.setAddpayinfoviewdetailPayinfomoney(payinfoMoneyStr);
			//时间信息
			mlbackAddPayinfoViewDetailReq.setAddpayinfoviewdetailCreatetime(nowTime);
			mlbackAddPayinfoViewDetailReq.setAddpayinfoviewdetailMotifytime(nowTime);
			mlbackAddPayinfoViewDetailReq.setAddpayinfoviewdetailStarttime(nowTime);
			mlbackAddPayinfoViewDetailReq.setAddpayinfoviewdetailEndtime(nowTime);
			mlbackAddPayinfoViewDetailService.insertSelective(mlbackAddPayinfoViewDetailReq);
		}
	}
	
	/**8.02
	 * 记录实际计算购买品的结算情况(成交单id+成交金额)
	 * */
	private void calcAddOrderView(MlfrontOrder mlfrontOrderPayOneRes) {
		
		String nowTime = DateUtil.strTime14s();
		//从详情中拿到orderItemIDStr;
		String orderItemIdsStr = mlfrontOrderPayOneRes.getOrderOrderitemidstr();
		List<MlfrontOrderItem>  mlfrontOrderItemList = new ArrayList<MlfrontOrderItem>();
		MlfrontOrderItem mlfrontOrderItemOne = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemOneReq = new MlfrontOrderItem();
		String orderItemIdStrArr [] = orderItemIdsStr.split(",");
		String orderItemIdStr = "";
		Integer orderItemIdInt = 0;
		for(int i =0;i<orderItemIdStrArr.length;i++){
			orderItemIdStr = orderItemIdStrArr[i];
			orderItemIdInt = Integer.parseInt(orderItemIdStr);
			mlfrontOrderItemOneReq.setOrderitemId(orderItemIdInt);
			List<MlfrontOrderItem> mlfrontOrderItemResList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemOneReq);
			mlfrontOrderItemOne = mlfrontOrderItemResList.get(0);
			mlfrontOrderItemList.add(mlfrontOrderItemOne);
			
			MlbackAddOrderViewDetail mlbackAddOrderViewDetailReq = new MlbackAddOrderViewDetail();
			mlbackAddOrderViewDetailReq.setAddorderviewdetailProItemid(mlfrontOrderItemOne.getOrderitemId());//
			
			//查询接口
			List<MlbackAddOrderViewDetail> mlbackAddOrderViewDetailList = mlbackAddOrderViewDetailService.selectMlbackAddOrderViewDetailByoItemid(mlbackAddOrderViewDetailReq);
			
			if(mlbackAddOrderViewDetailList.size()>0){
				System.out.println("本条已经被插入过,无需重复insert了");
			}else{
				mlbackAddOrderViewDetailReq.setAddorderviewdetailProid(mlfrontOrderItemOne.getOrderId());
				mlbackAddOrderViewDetailReq.setAddorderviewdetailProname(mlfrontOrderItemOne.getOrderitemPname());
				mlbackAddOrderViewDetailReq.setAddorderviewdetailProskunamestr(mlfrontOrderItemOne.getOrderitemPskuNamestr());
				mlbackAddOrderViewDetailReq.setAddorderviewdetailActnum(mlfrontOrderItemOne.getOrderitemPskuNumber());
				mlbackAddOrderViewDetailReq.setAddorderviewdetailCreatetime(nowTime);
				mlbackAddOrderViewDetailReq.setAddorderviewdetailMotifytime(nowTime);
				mlbackAddOrderViewDetailReq.setAddorderviewdetailStarttime(nowTime);
				mlbackAddOrderViewDetailReq.setAddorderviewdetailEndtime(nowTime);
				
				mlbackAddOrderViewDetailService.insertSelective(mlbackAddOrderViewDetailReq);
			}
		}
		
	}
	
}
