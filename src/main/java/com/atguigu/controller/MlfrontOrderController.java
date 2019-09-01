package com.atguigu.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontCart;
import com.atguigu.bean.MlfrontCartItem;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.Msg;
import com.atguigu.bean.PageTimeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlbackCouponService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontCartItemService;
import com.atguigu.service.MlfrontCartService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.EmailUtils;


@Controller
@RequestMapping("/MlfrontOrder")
public class MlfrontOrderController {
		
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
	
	/**
	 * 1.0	useOn	0505
	 * 前台移动端获取详情页面mfront/tomOrderDetailPage
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/tomOrderDetailOne",method=RequestMethod.GET)
	@ResponseBody
	public Msg tomOrderDetailOne(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		//接收传递进来的参数
		MlfrontUser loginUser =(MlfrontUser) session.getAttribute("loginUser");
		Integer usertype = 0;
		if(loginUser==null){
			usertype = 0;
		}else{
			usertype = 1;
		}
		Integer orderId =  (Integer) session.getAttribute("orderId");
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		mlfrontOrderItemReq.setOrderId(orderId);
		List<MlfrontOrderItem> mlfrontOrderItemList = mlfrontOrderItemService.selectMlfrontOrderItemByOrderId(mlfrontOrderItemReq);
		//返回视图
		return Msg.success().add("resMsg", "更新成功").add("mlfrontOrderItemList", mlfrontOrderItemList).add("orderId", orderId).add("usertype", usertype);
	}
	
	
	/**2.0	useOn	0505
	 * 分类MlfrontOrder列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlfrontOrderByPage")
	@ResponseBody
	public Msg getMlfrontOrderWithJson(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn
			,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderAll();
			PageInfo page = new PageInfo(mlfrontOrderList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**3.0.2.9	useOn	0505
	 * 更新order表中的，地址字段，优惠券字段，优惠券折扣。
	 * @param MlfrontOrder
	 */
	@RequestMapping(value="/earlyorderToPayInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg earlyorderToPayInfo(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrder mlfrontOrder){
		//0.0接受参数信息
		System.out.println("mlfrontOrder:"+mlfrontOrder);
		Integer originalOrderId = mlfrontOrder.getOrderId();
		String filnanyNumber = mlfrontOrder.getOrderProNumStr();
		Integer CouponId =mlfrontOrder.getOrderCouponId();
		Integer orderPayPlateInt = mlfrontOrder.getOrderPayPlate();
		String buyMessStr = mlfrontOrder.getOrderBuyMess();
		//1.0用order查orderItem,遍历orderItem,计算每个Item的价格，再加在一起；
		String Orderitemidstr = mlfrontOrder.getOrderOrderitemidstr();
		String orderitemidArr[] = Orderitemidstr.split(",");
		BigDecimal totalprice = new BigDecimal(0);
		DecimalFormat df1 = new DecimalFormat("0.00");
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemRes = new MlfrontOrderItem();
		for(int i=0;i<orderitemidArr.length;i++){
			BigDecimal oneAllprice = new BigDecimal(0);
			Integer orderItemId = Integer.parseInt(orderitemidArr[i]);
			mlfrontOrderItemReq.setOrderitemId(orderItemId);
			List<MlfrontOrderItem> mlfrontOrderItemList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
			mlfrontOrderItemRes = mlfrontOrderItemList.get(0);
			BigDecimal ItemProductOriginalprice =mlfrontOrderItemRes.getOrderitemProductOriginalprice();
			String OrderitemPskuMoneystr = mlfrontOrderItemRes.getOrderitemPskuMoneystr();
			Integer number = mlfrontOrderItemRes.getOrderitemPskuNumber();
			Integer accoff = mlfrontOrderItemRes.getOrderitemProductAccoff();
			String PskuMoneystr[] = OrderitemPskuMoneystr.split(",");
			BigDecimal pskuMoneyOne = new BigDecimal(0);
			for(int j =0;j<PskuMoneystr.length;j++){
				pskuMoneyOne = new BigDecimal(PskuMoneystr[j]);
				oneAllprice = oneAllprice.add(pskuMoneyOne);
			}
			oneAllprice=oneAllprice.add(ItemProductOriginalprice);
			//计算这一项的价格，(基础价格+每个的sku价格的和)*折扣*数量,存入orderitemPskuReamoney字段中;
			oneAllprice = oneAllprice.multiply(new BigDecimal(number));
			oneAllprice = oneAllprice.multiply(new BigDecimal(accoff));
			oneAllprice = oneAllprice.multiply(new BigDecimal(0.01));
			String str = df1.format(oneAllprice);
			System.out.println(oneAllprice);
			System.out.println(str); //13.15
			MlfrontOrderItem mlfrontOrderItemMoneyBlack = new MlfrontOrderItem();
			mlfrontOrderItemMoneyBlack.setOrderitemId(orderItemId);
			mlfrontOrderItemMoneyBlack.setOrderitemPskuReamoney(str);
			//更新本条，存入orderitemPskuReamoney字段
			mlfrontOrderItemService.updateByPrimaryKeySelective(mlfrontOrderItemMoneyBlack);
			//一个字段存储总价格
			totalprice = totalprice.add(oneAllprice);
		}
		/*		加		单个的	(基础价格+每个的sku价格的和)*折扣*数量,
		 * 		加				地址运费
		 * 		减				优惠价格
		 * */
		//2.0计算地址价格，计算优惠价格，插入order项目价格
		//2.1拿到地址ID,
		Integer AddressId = mlfrontOrder.getAddressinfoId();
		//查询英文名,查询该英文名的价格运费价格
		Integer addressMoney = 0;
		//2.2加上地区快递费
		totalprice = totalprice.add(new BigDecimal(addressMoney));
		//拿到优惠码Code,
		String CouponCode = mlfrontOrder.getOrderCouponCode();
		//查询该优惠码的优惠价格
		BigDecimal CouponCodeMoney = getCouponCodeMoney("0");
		//加上优惠券减掉的
		totalprice = totalprice.subtract(CouponCodeMoney);
		//计算该订单的实际价格
		String totalpriceStr = df1.format(totalprice);
		//2.3更新order表(地区字段,优惠券字段,总价的价格，结算方式,留言,总价的价格)
		MlfrontOrder mlfrontOrderEnd = new  MlfrontOrder();
		mlfrontOrderEnd.setOrderId(originalOrderId);
		mlfrontOrderEnd.setAddressinfoId(AddressId);//地区字段
		mlfrontOrderEnd.setOrderCouponId(CouponId);
		mlfrontOrderEnd.setOrderCouponCode(CouponCode);//优惠券字段
		mlfrontOrderEnd.setOrderCouponPrice(CouponCodeMoney);//优惠券money
		mlfrontOrderEnd.setOrderPayPlate(orderPayPlateInt);//结算方式
		mlfrontOrderEnd.setOrderProNumStr(filnanyNumber);//最终各个产品数量
		mlfrontOrderEnd.setOrderBuyMess(buyMessStr);//留言
		BigDecimal bigTotalprice = new BigDecimal(totalpriceStr);//总价的价格
		mlfrontOrderEnd.setOrderMoney(bigTotalprice);
		String nowTime = DateUtil.strTime14s();
		mlfrontOrderEnd.setOrderMotifytime(nowTime);
		//执行更新order表
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderEnd);
		//3.0将order信息写入payInfo信息中心//uid oid 支付方式，交易订单号，支付信息
		//从orderId中查询uid,地址id,buyMess字段;
		Integer payAddressinfoId = mlfrontOrderEnd.getAddressinfoId();
		
		MlfrontAddress mlfrontAddress =new MlfrontAddress();
		mlfrontAddress.setAddressId(payAddressinfoId);
		
		List<MlfrontAddress> mlfrontAddressToPayList = mlfrontAddressService.selectMlfrontAddressById(mlfrontAddress);
		
		MlfrontAddress mlfrontAddressToPay = mlfrontAddressToPayList.get(0);
		
		session.setAttribute("mlfrontAddressToPay", mlfrontAddressToPay);
		session.setAttribute("totalprice", totalprice);
		
		MlfrontPayInfo mlfrontPayInfoNew = new MlfrontPayInfo();
		mlfrontPayInfoNew.setPayinfoOid(originalOrderId);
		mlfrontPayInfoNew.setPayinfoStatus(0);//0未支付1已支付
		if(orderPayPlateInt==0){
			mlfrontPayInfoNew.setPayinfoPlatform("paypal");
		}else{
			mlfrontPayInfoNew.setPayinfoPlatform("bank_Card");
		}
		mlfrontPayInfoNew.setPayinfoMoney(totalprice);
		mlfrontPayInfoNew.setPayinfoCreatetime(nowTime);
		mlfrontPayInfoNew.setPayinfoMotifytime(nowTime);
		mlfrontPayInfoService.insertSelective(mlfrontPayInfoNew);
		List<MlfrontPayInfo> mlfrontPayInfoResList = mlfrontPayInfoService.selectMlfrontPayInfoAll();
		MlfrontPayInfo mlfrontPayInfoResOne = mlfrontPayInfoResList.get(0);
		Integer payinfoId = mlfrontPayInfoResOne.getPayinfoId();
		session.setAttribute("payinfoId", payinfoId);
		
		session.setAttribute("sendAddressinfoId", payAddressinfoId);
		//4.0传入orderid,查询其中的orderItemID，、找到cartID 找到cartid,移除购物车中的
		Integer IsUpdate = updateCart(mlfrontOrder);
		//5.0发起支付
		Integer isSuccess = 0;//返回0，跳支付成功页面
		//Integer isSuccess = 1;//返回1，跳支付失败页面
		return Msg.success().add("resMsg", "更新成功").add("isSuccess", isSuccess);
		//return "redirect:/paypal/pay";
	}
	
	
	/**3.0	useOn	0505
	 * 更新order表中的，地址字段，优惠券字段，优惠券折扣。
	 * @param MlfrontOrder
	 */
	@RequestMapping(value="/orderToPayInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg orderToPayInfo(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrder mlfrontOrder){
//	public String orderToPayInfo(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrder mlfrontOrder){
		//0.0接受参数信息
		System.out.println("mlfrontOrder:"+mlfrontOrder);
		Integer originalOrderId = mlfrontOrder.getOrderId();
		String filnanyNumber = mlfrontOrder.getOrderProNumStr();
		Integer CouponId =mlfrontOrder.getOrderCouponId();
		Integer orderPayPlateInt = mlfrontOrder.getOrderPayPlate();
		String buyMessStr = mlfrontOrder.getOrderBuyMess();
		//1.0用order查orderItem,遍历orderItem,计算每个Item的价格，再加在一起；
		String Orderitemidstr = mlfrontOrder.getOrderOrderitemidstr();
		String orderitemidArr[] = Orderitemidstr.split(",");
		BigDecimal totalprice = new BigDecimal(0);
		DecimalFormat df1 = new DecimalFormat("0.00");
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemRes = new MlfrontOrderItem();
		for(int i=0;i<orderitemidArr.length;i++){
			BigDecimal oneAllprice = new BigDecimal(0);
			Integer orderItemId = Integer.parseInt(orderitemidArr[i]);
			mlfrontOrderItemReq.setOrderitemId(orderItemId);
			List<MlfrontOrderItem> mlfrontOrderItemList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
			mlfrontOrderItemRes = mlfrontOrderItemList.get(0);
			BigDecimal ItemProductOriginalprice =mlfrontOrderItemRes.getOrderitemProductOriginalprice();
			String OrderitemPskuMoneystr = mlfrontOrderItemRes.getOrderitemPskuMoneystr();
			Integer number = mlfrontOrderItemRes.getOrderitemPskuNumber();
			Integer accoff = mlfrontOrderItemRes.getOrderitemProductAccoff();
			String PskuMoneystr[] = OrderitemPskuMoneystr.split(",");
			BigDecimal pskuMoneyOne = new BigDecimal(0);
			for(int j =0;j<PskuMoneystr.length;j++){
				pskuMoneyOne = new BigDecimal(PskuMoneystr[j]);
				oneAllprice = oneAllprice.add(pskuMoneyOne);
			}
			oneAllprice=oneAllprice.add(ItemProductOriginalprice);
			//计算这一项的价格，(基础价格+每个的sku价格的和)*折扣*数量,存入orderitemPskuReamoney字段中;
			oneAllprice = oneAllprice.multiply(new BigDecimal(number));
			oneAllprice = oneAllprice.multiply(new BigDecimal(accoff));
			oneAllprice = oneAllprice.multiply(new BigDecimal(0.01));
			String str = df1.format(oneAllprice);
			System.out.println(oneAllprice);
			System.out.println(str); //13.15
			MlfrontOrderItem mlfrontOrderItemMoneyBlack = new MlfrontOrderItem();
			mlfrontOrderItemMoneyBlack.setOrderitemId(orderItemId);
			mlfrontOrderItemMoneyBlack.setOrderitemPskuReamoney(str);
			//更新本条，存入orderitemPskuReamoney字段
			mlfrontOrderItemService.updateByPrimaryKeySelective(mlfrontOrderItemMoneyBlack);
			//一个字段存储总价格
			totalprice = totalprice.add(oneAllprice);
		}
		/*		加		单个的	(基础价格+每个的sku价格的和)*折扣*数量,
		 * 		加				地址运费
		 * 		减				优惠价格
		 * */
		//2.0计算地址价格，计算优惠价格，插入order项目价格
		//2.1拿到地址ID,
		Integer AddressId = mlfrontOrder.getAddressinfoId();
		//查询英文名,查询该英文名的价格运费价格
		Integer addressMoney = getAddressMoney(AddressId);
		//2.2加上地区快递费
		totalprice = totalprice.add(new BigDecimal(addressMoney));
		//拿到优惠码Code,
		String CouponCode = mlfrontOrder.getOrderCouponCode();
		//查询该优惠码的优惠价格
		BigDecimal CouponCodeMoney = getCouponCodeMoney(CouponCode);
		//加上优惠券减掉的
		totalprice = totalprice.subtract(CouponCodeMoney);
		//计算该订单的实际价格
		String totalpriceStr = df1.format(totalprice);
		//2.3更新order表(地区字段,优惠券字段,总价的价格，结算方式,留言,总价的价格)
		MlfrontOrder mlfrontOrderEnd = new  MlfrontOrder();
		mlfrontOrderEnd.setOrderId(originalOrderId);
		mlfrontOrderEnd.setAddressinfoId(AddressId);//地区字段
		mlfrontOrderEnd.setOrderCouponId(CouponId);
		mlfrontOrderEnd.setOrderCouponCode(CouponCode);//优惠券字段
		mlfrontOrderEnd.setOrderCouponPrice(CouponCodeMoney);//优惠券money
		mlfrontOrderEnd.setOrderPayPlate(orderPayPlateInt);//结算方式
		mlfrontOrderEnd.setOrderProNumStr(filnanyNumber);//最终各个产品数量
		mlfrontOrderEnd.setOrderBuyMess(buyMessStr);//留言
		BigDecimal bigTotalprice = new BigDecimal(totalpriceStr);//总价的价格
		mlfrontOrderEnd.setOrderMoney(bigTotalprice);
		String nowTime = DateUtil.strTime14s();
		mlfrontOrderEnd.setOrderMotifytime(nowTime);
		//执行更新order表
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderEnd);
		//3.0将order信息写入payInfo信息中心//uid oid 支付方式，交易订单号，支付信息
		//从orderId中查询uid,地址id,buyMess字段;
		Integer payAddressinfoId = mlfrontOrderEnd.getAddressinfoId();
		
		MlfrontAddress mlfrontAddress =new MlfrontAddress();
		mlfrontAddress.setAddressId(payAddressinfoId);
		
		List<MlfrontAddress> mlfrontAddressToPayList = mlfrontAddressService.selectMlfrontAddressById(mlfrontAddress);
		
		MlfrontAddress mlfrontAddressToPay = mlfrontAddressToPayList.get(0);
		
		session.setAttribute("mlfrontAddressToPay", mlfrontAddressToPay);
		session.setAttribute("totalprice", totalprice);
		
		MlfrontPayInfo mlfrontPayInfoNew = new MlfrontPayInfo();
		mlfrontPayInfoNew.setPayinfoOid(originalOrderId);
		mlfrontPayInfoNew.setPayinfoStatus(0);//0未支付1已支付
		if(orderPayPlateInt==0){
			mlfrontPayInfoNew.setPayinfoPlatform("paypal");
		}else{
			mlfrontPayInfoNew.setPayinfoPlatform("bank_Card");
		}
		mlfrontPayInfoNew.setPayinfoMoney(totalprice);
		mlfrontPayInfoNew.setPayinfoCreatetime(nowTime);
		mlfrontPayInfoNew.setPayinfoMotifytime(nowTime);
		mlfrontPayInfoService.insertSelective(mlfrontPayInfoNew);
		List<MlfrontPayInfo> mlfrontPayInfoResList = mlfrontPayInfoService.selectMlfrontPayInfoAll();
		MlfrontPayInfo mlfrontPayInfoResOne = mlfrontPayInfoResList.get(0);
		Integer payinfoId = mlfrontPayInfoResOne.getPayinfoId();
		session.setAttribute("payinfoId", payinfoId);
		
		session.setAttribute("sendAddressinfoId", payAddressinfoId);
		//4.0传入orderid,查询其中的orderItemID，、找到cartID 找到cartid,移除购物车中的
		Integer IsUpdate = updateCart(mlfrontOrder);
		//5.0发起支付
		Integer isSuccess = 0;//返回0，跳支付成功页面
		//Integer isSuccess = 1;//返回1，跳支付失败页面
		return Msg.success().add("resMsg", "更新成功").add("isSuccess", isSuccess);
		//return "redirect:/paypal/pay";
	}

	/**
	 * 3.1查询英文名,查询该英文名的价格运费价格
	 * getAddressMoney
	 * */
	private Integer getAddressMoney(Integer addressId) {
		MlfrontAddress MlfrontAddressReq = new MlfrontAddress();
		MlfrontAddressReq.setAddressId(addressId);
		List<MlfrontAddress> mlfrontAddressList = mlfrontAddressService.selectMlfrontAddressById(MlfrontAddressReq);
		String addressCountry = mlfrontAddressList.get(0).getAddressCountry();
		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
		mlbackAreafreightReq.setAreafreightCountryEnglish(addressCountry);
		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByEng(mlbackAreafreightReq);
		MlbackAreafreight mlbackAreafreightOne = new MlbackAreafreight();
		Integer areafreightPrice = 0;
		if(mlbackAreafreightResList.size()>0){
			mlbackAreafreightOne =mlbackAreafreightResList.get(0);
			areafreightPrice = mlbackAreafreightOne.getAreafreightPrice();
		}
		return areafreightPrice;
	}
	
	/**
	 * 3.2查询该优惠码的优惠价格
	 * getCouponCodeMoney
	 * */
	private BigDecimal getCouponCodeMoney(String couponCode) {
		
		MlbackCoupon mlbackCouponReq = new MlbackCoupon();
		mlbackCouponReq.setCouponCode(couponCode);
		List<MlbackCoupon> mlbackCouponResList =mlbackCouponService.selectMlbackCouponBYCode(mlbackCouponReq);
		
		BigDecimal mlbackCouponPrice = new BigDecimal(0.00);
		if(mlbackCouponResList.size()>0){
			MlbackCoupon mlbackCouponOne =mlbackCouponResList.get(0);
			mlbackCouponPrice = mlbackCouponOne.getCouponPrice();
		}
		return mlbackCouponPrice;
	}

	/**
	 * 3.3更新表字段
	 * updateCart
	 * */
	private Integer updateCart(MlfrontOrder mlfrontOrder) {
		Integer orderId = mlfrontOrder.getOrderId();
		Integer cartId = 0;
		Integer cartItemtestId= 0;
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder ();
		mlfrontOrderReq.setOrderId(orderId);
		List<MlfrontOrder>  mlfrontOrderResList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq);
		MlfrontOrder  mlfrontOrderRes = mlfrontOrderResList.get(0);
		//拿到orderItemStr
		String orderitemidstr = mlfrontOrderRes.getOrderOrderitemidstr();
		String orderitemidArr[] = orderitemidstr.split(",");
		//遍历orderitemidArr ，读取每个的orderItemid 取出本条，中的order_cartItemId
		List<String> cartitemIdNew =new ArrayList<String>();
		for(int x=0;x<orderitemidArr.length;x++){
			MlfrontOrderItem mlfrontOrderItem = new MlfrontOrderItem();
			int orderItemId = Integer .parseInt(orderitemidArr[x]);
			mlfrontOrderItem.setOrderitemId(orderItemId);
			List<MlfrontOrderItem>  mlfrontOrderItemList= mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItem);
			MlfrontOrderItem MlfrontOrderItemRes = mlfrontOrderItemList.get(0);
			Integer orderCartItemIdInt = MlfrontOrderItemRes.getOrderCartItemId();
			String orderCartItemIdStr = orderCartItemIdInt+"";
			cartitemIdNew.add(orderCartItemIdStr);
			cartItemtestId = orderCartItemIdInt;
		}
		//通过cartItemID找到cartid，在找到cartID中的cartItemIdStr
		MlfrontCartItem mlfrontCartItemReq = new MlfrontCartItem();
		mlfrontCartItemReq.setCartitemId(cartItemtestId);
		List<MlfrontCartItem> mlfrontCartItemList= mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemReq);
		MlfrontCartItem mlfrontCartItemRes = mlfrontCartItemList.get(0);
		cartId = mlfrontCartItemRes.getCartitemCartId();
		MlfrontCart mlfrontCart = new MlfrontCart();
		mlfrontCart.setCartId(cartId);
		MlfrontCart mlfrontCartRes = mlfrontCartService.selectMlfrontCartByCartId(cartId);
		String cartitemIdstrOld= mlfrontCartRes.getCartitemIdstr();
		String cartitemIdArr[] = cartitemIdstrOld.split(",");
		String pkStr = "";
		String flag = "0";
		String filallyCartitemStr = "";
		for(int i=0;i<cartitemIdArr.length;i++){
			pkStr=cartitemIdArr[i];
			for(int j=0;j<cartitemIdNew.size();j++){
				if(cartitemIdNew.get(j).equals(pkStr)){
					//包含
					flag = "1";
					break;
				}else{
					flag = "0";
					continue;
				}
			}
			if(flag.equals("1")){
				//删掉
				System.out.println("*****************");
			}else{
				//留下
				filallyCartitemStr=filallyCartitemStr+","+pkStr;
			}
		}
		if(filallyCartitemStr.length()>0){
			filallyCartitemStr = filallyCartitemStr.substring(1);
			mlfrontCart.setCartStatus(0);
		}else{
			mlfrontCart.setCartStatus(1);
		}
		mlfrontCart.setCartitemIdstr(filallyCartitemStr);
		mlfrontCartService.updateByPrimaryKeySelective(mlfrontCart);
		return null;
	}
	
	/**
	 * 4.0	UseNow	0505
	 * to	MlfrontOrder列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/getMlfrontOrderByDate",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlfrontOrderByDate(HttpServletResponse rep,HttpServletRequest res,HttpSession session ,@RequestBody PageTimeVo pageTime){
		
		Integer pn = pageTime.getPn();
		if(pn==null){
			pn=1;
		}
		String beginTime = pageTime.getBeginTime();
		String endTime = pageTime.getEndTime();
		
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
		mlfrontOrderReq.setOrderCreatetime(beginTime);
		mlfrontOrderReq.setOrderMotifytime(endTime);
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlfrontOrder> mlfrontOrderReqList = mlfrontOrderService.selectMlfrontOrderByDate(mlfrontOrderReq);
			Integer countNumber =mlfrontOrderReqList.size();
			PageInfo page = new PageInfo(mlfrontOrderReqList, PagNum);
			return Msg.success().add("pageInfo", page).add("countNumber", countNumber).add("resMsg", "订单查询成功");
	}
	
	/**
	 * 5.0	useOn	0505
	 * to	MlfrontCart列表页面
	 * @param jsp
	 * @return  
	 * */
	@RequestMapping("/toMlbackOrderPage")
	public String toMlbackOrderPage() throws Exception{
	
		return "back/mlbackOrderPage";
	}
	
	/**
	 * 6.0.0	useOn	0505
	 * to	MlfrontCart列表页面
	 * @param jsp
	 * @return  
	 * */	
	@RequestMapping(value="/getMlfrontOrderDetailPage",method=RequestMethod.GET)
	public String tomProductDetailPage() throws Exception{
		//接收传递进来的参数
		/*Integer orderIdReq = orderId;
		//放回响应域中
		res.setAttribute("orderId", orderIdReq);*/
		//返回视图
		return "mfront/myOrderDetail";
	}
	
	/**
	 * 6.0.1	useOn	0505
	 * to	MlfrontCart列表页面
	 * @param jsp
	 * @return  
	 * */	
	@RequestMapping(value="/getMlfrontOrderDetailpcPage",method=RequestMethod.GET)
	public String topcProductDetailPage() throws Exception{
		//接收传递进来的参数
		/*Integer orderIdReq = orderId;
		//放回响应域中
		res.setAttribute("orderId", orderIdReq);*/
		//返回视图
		return "front/pcmyOrderDetail";
	}
	
	/**
	 * 6.0	useOn	0505
	 * 查看单条类目的详情细节
	 * @param mlfrontOrderOne
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontOrderDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontOrderDetail(@RequestParam(value = "orderId") Integer orderId){
		
		//接受categoryId
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
		mlfrontOrderReq.setOrderId(orderId);
		//查询本条
		List<MlfrontOrder> mlfrontOrderResList =mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq);
		MlfrontOrder mlfrontOrderOne = new MlfrontOrder();
		List<MlfrontOrderItem>  mlfrontOrderItemList = new ArrayList<MlfrontOrderItem>();
		Integer addressinfoId =0;
		MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
		MlfrontAddress mlfrontAddressRes = new MlfrontAddress();
		List<MlfrontAddress> mlfrontAddressList = new ArrayList<MlfrontAddress>();
		if(mlfrontOrderResList.size()>0){
			mlfrontOrderOne =mlfrontOrderResList.get(0);

			addressinfoId = mlfrontOrderOne.getAddressinfoId();
			mlfrontAddressReq.setAddressId(addressinfoId);
			mlfrontAddressList =mlfrontAddressService.selectMlfrontAddressById(mlfrontAddressReq);
			if(mlfrontAddressList.size()>0){
				mlfrontAddressRes = mlfrontAddressList.get(0);
			}
			//2.3从详情中拿到orderItemIDStr;
			String orderItemIdsStr = mlfrontOrderOne.getOrderOrderitemidstr();
			//取出mlfrontOrderOne的数量对象
			//如果对象那个中的详情为空
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
		}else{
			mlfrontOrderOne = null;
		}
		return Msg.success().add("resMsg", "查看单条mlfrontOrderOne的详情细节完毕")
					.add("mlfrontOrderOne", mlfrontOrderOne).add("mlfrontOrderItemList", mlfrontOrderItemList)
					.add("addressInfo", mlfrontAddressRes);
	}
	
	
	/**
	 * 7.0	UseNow	0505
	 * mToMyOrderPage	个人详情，查看历史订单，全部订单MlfrontOrder列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/mToMyOrderPage",method=RequestMethod.POST)
	@ResponseBody
	public String mToMyOrderPage(HttpSession session) {

		return "mToMyOrderPage";
//		}
	}
	
	
	
	/**
	 * 8.0	UseNow	0505
	 * to	全部订单
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/getmOrderByUidPage",method=RequestMethod.POST)
	@ResponseBody
	public Msg getmOrderByUidPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {

		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		Integer Uid = loginUser.getUserId();
		MlfrontOrder mlfrontOrder = new MlfrontOrder();
		mlfrontOrder.setOrderUid(Uid);
		int PagNum = 20;
		PageHelper.startPage(pn, PagNum);
		List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderByUidOnly(mlfrontOrder);
		PageInfo page = new PageInfo(mlfrontOrderList, PagNum);
		mlfrontOrderList = page.getList();
		//2遍历mlfrontOrderList，3读取每个的orderItemIdStr,4切割，5再遍历产寻单条的获取orderItemId对象
		String orderitemidstr="";
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemRes = new MlfrontOrderItem();
		List<MlfrontOrderItem> mlfrontOrderItemList = new ArrayList<MlfrontOrderItem>();
		List<MlfrontOrderItem> mlfrontOrderItemReturn = new ArrayList<MlfrontOrderItem>();
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
				mlfrontOrderItemReturn.add(mlfrontOrderItemRes);
			}
		}
		return Msg.success().add("pageInfo", page).add("sizeList", sizeList).add("mlfrontOrderItemReturn", mlfrontOrderItemReturn);
//		}
	}
	
	
	/**
	 * 9.0	UseNow	0505
	 * to	全部已付款—订单
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/getmMlfrontOrderPayEndByPage",method=RequestMethod.POST)
	@ResponseBody
	public Msg getmMlfrontOrderPayEndByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {

		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		Integer Uid = loginUser.getUserId();
		MlfrontOrder mlfrontOrder = new MlfrontOrder();
		mlfrontOrder.setOrderUid(Uid);
		mlfrontOrder.setOrderStatus(1);
		int PagNum = 20;
		PageHelper.startPage(pn, PagNum);
		List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderByUidAndStatus(mlfrontOrder);
		PageInfo page = new PageInfo(mlfrontOrderList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 10.0	UseNow	0505
	 * to	全部待付款—订单
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/getmMlfrontOrderPayBeginByPage",method=RequestMethod.POST)
	@ResponseBody
	public Msg getmMlfrontOrderPayBeginByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {

		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		Integer Uid = loginUser.getUserId();
		MlfrontOrder mlfrontOrder = new MlfrontOrder();
		mlfrontOrder.setOrderUid(Uid);
		mlfrontOrder.setOrderStatus(0);
		int PagNum = 20;
		PageHelper.startPage(pn, PagNum);
		List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderByUidAndStatus(mlfrontOrder);
		PageInfo page = new PageInfo(mlfrontOrderList, PagNum);
		return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**
	 * 10.0	UseNow	0505
	 * to	全部待付款—订单
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/updateOrderDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateOrderDetail(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrder mlfrontOrder) {

		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		
//		Integer addressId = (Integer) session.getAttribute("sendAddressinfoId");
		//Integer Uid = loginUser.getUserId();
		Integer orderId = mlfrontOrder.getOrderId();
		String orderLogisticsname =mlfrontOrder.getOrderLogisticsname();	//物流名字
		String orderLogisticsnumber =  mlfrontOrder.getOrderLogisticsnumber();//物流单号
		
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
		MlfrontOrder mlfrontOrderReq2 = new MlfrontOrder();
		mlfrontOrderReq.setOrderId(orderId);
		mlfrontOrderReq.setOrderLogisticsname(orderLogisticsname);
		mlfrontOrderReq.setOrderLogisticsnumber(orderLogisticsnumber);
		String nowTime = DateUtil.strTime14s();
		mlfrontOrderReq.setOrderStatus(3);
		mlfrontOrderReq.setOrderSendtime(nowTime);
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
		
		//通过orderId查询文件
		mlfrontOrderReq2.setOrderId(orderId);
		List<MlfrontOrder> mlfrontOrderResList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq2);
		MlfrontOrder mlfrontOrderRes = mlfrontOrderResList.get(0);
		Integer addressId = mlfrontOrderRes.getAddressinfoId();
		
		sendLogisticsnumberEmail(addressId,orderLogisticsname,orderLogisticsnumber);
		
		return Msg.success().add("Msg", "更新成功");
	}


	private void sendLogisticsnumberEmail(Integer addressId, String orderLogisticsname, String orderLogisticsnumber) {
		
		MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
		MlfrontAddress mlfrontAddressRes = new MlfrontAddress();
		mlfrontAddressReq.setAddressId(addressId);
		List<MlfrontAddress> mlfrontAddressResList = mlfrontAddressService.selectMlfrontAddressById(mlfrontAddressReq);
		mlfrontAddressRes = mlfrontAddressResList.get(0);
		System.out.println(mlfrontAddressRes.toString());
		String userEmail = mlfrontAddressRes.getAddressEmail();
		
		try {
			//测试方法
			String getToEmail = userEmail;
			String Message = "您在Megalook购买的秀发已经发货,请留意关注订单号为"+orderLogisticsnumber+"的,"+orderLogisticsname+"快件.";
			EmailUtils.readyEmailRegister(getToEmail, Message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
