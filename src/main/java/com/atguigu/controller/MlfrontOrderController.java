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

import com.atguigu.bean.MlbackAbandonPurchase;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.bean.MlbackOrderStateEmail;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackShipEmail;
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
import com.atguigu.service.MlbackAbandonPurchaseService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlbackCouponService;
import com.atguigu.service.MlbackOrderStateEmailService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlbackShipEmailService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontCartItemService;
import com.atguigu.service.MlfrontCartService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.EmailUtilshtml;
import com.atguigu.utils.EmailUtilshtmlCustomer;
//import com.atguigu.utils.app.shipInformation;
import com.atguigu.utils.PropertiesUtil;

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
	
	@Autowired
	MlbackShipEmailService mlbackShipEmailService;
	
	@Autowired
	MlbackAbandonPurchaseService mlbackAbandonPurchaseService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackOrderStateEmailService mlbackOrderStateEmailService;
	
	/**
	 * 1.0	onuse	20191225	检查
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
	
	/**3.0	useOn	200323
	 * Cheekout页面,加减更新order表中的OrderItem数量。
	 * @param MlfrontOrder
	 */
	@RequestMapping(value="/updateOrderItemNum",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateOrderItemNum(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrderItem mlfrontOrderItem){
		
		Integer orderitemId =  mlfrontOrderItem.getOrderitemId();
		Integer orderitemPskuNumber = mlfrontOrderItem.getOrderitemPskuNumber();
		
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		mlfrontOrderItemReq.setOrderitemId(orderitemId);
		mlfrontOrderItemReq.setOrderitemPskuNumber(orderitemPskuNumber);
		
		mlfrontOrderItemService.updateByPrimaryKeySelective(mlfrontOrderItemReq);
		
		return Msg.success().add("resMsg", "更新成功"); 
	}
	
	
	/**
	 * 4.0	zsh 0615
	 * 删除购物车中的项delCartItem
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/delOrderItem",method=RequestMethod.POST)
	@ResponseBody
	public Msg delOrderItem(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrderItem mlfrontOrderItem) throws Exception{
		//接收传递进来的参数
		//System.out.println(mlfrontOrderItem);
		Integer orderitemIdOriginal = mlfrontOrderItem.getOrderitemId();
		int isDelSuccess = 0;
		if(orderitemIdOriginal==null){
			//前台传递异常,没有把订单项的id值传进来
			return Msg.success().add("resMsg", "删除失败,主建为null,请联系管理员").add("isDelSuccess", isDelSuccess);//isDelSuccess0删除失败，alert	resMsg/1,删除成功
		}else{
			//通过cartitem的之间查回该条信息，从中取出cartId
			MlfrontOrderItem mlfrontOrderItemReq  =new MlfrontOrderItem();
			mlfrontOrderItemReq.setOrderitemId(orderitemIdOriginal);
			List<MlfrontOrderItem> mlfrontOrderItemResList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
			if(mlfrontOrderItemResList.size()>0){
				Integer orderId = mlfrontOrderItemResList.get(0).getOrderId();
				String orderitemIdOriginalStr = orderitemIdOriginal+"";
				String nowTime = DateUtil.strTime14s();
				MlfrontOrder MlfrontOrderReq = new MlfrontOrder();
				MlfrontOrderReq.setOrderId(orderId);
				List<MlfrontOrder> mlfrontOrderListRes  = mlfrontOrderService.selectMlfrontOrderById(MlfrontOrderReq);
				MlfrontOrder mlfrontOrderRes = mlfrontOrderListRes.get(0);
				//1根据cart该购物车中的本条cartItemStr中的字段
				String orderitemidstr = mlfrontOrderRes.getOrderOrderitemidstr();
				String orderItemArr[] =orderitemidstr.split(",");
				String orderitemStrEnd = "";
				int orginalItemNum = orderItemArr.length;
				if(orderItemArr.length>1){
					//1.1如果有多个删除字段中的该项
					for(int i =0;i<orderItemArr.length;i++){
						String tem = orderItemArr[i];
						if(tem.equals(orderitemIdOriginalStr)){
							continue;
						}else{
							orderitemStrEnd=orderitemStrEnd+tem+",";
						}
					}
					int lastIN = orderitemStrEnd.length();
					orderitemStrEnd = orderitemStrEnd.substring(0, (lastIN-1));
					//更新cart中的cartitemStr字段
					MlfrontOrder mlfrontOrderreqE = new MlfrontOrder();
					mlfrontOrderreqE.setOrderId(orderId);
					mlfrontOrderreqE.setOrderOrderitemidstr(orderitemStrEnd);
					mlfrontOrderreqE.setOrderMotifytime(nowTime);
					mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderreqE);
				}else{
					//1.2如果只有一个，删掉购物车
					mlfrontOrderService.deleteByPrimaryKey(orderId);
				}
				//2删除该条CartItem信息
				mlfrontOrderItemService.deleteByPrimaryKey(orderitemIdOriginal);
				orginalItemNum=orginalItemNum-1;
				isDelSuccess = 1;
				return Msg.success().add("resMsg", "删除成功").add("isDelSuccess", isDelSuccess).add("orginalItemNum", orginalItemNum);//isDelSuccess0删除失败，alert	resMsg/1,删除成功
			}else{
				return Msg.success().add("resMsg", "删除失败，该cartitem项不存在，请联系管理员").add("isDelSuccess", isDelSuccess);//isDelSuccess0删除失败，alert	resMsg/1,删除成功
			}
		}			
	}
	
	/**5.0	useOn	200323
	 * 更新order表中的:地址字段,优惠券字段,优惠券折扣。
	 * @param MlfrontOrder
	 */
	@RequestMapping(value="/orderToPayInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg orderToPayInfo(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrder mlfrontOrder){
		//0.0接受参数信息
		//System.out.println("mlfrontOrder:"+mlfrontOrder);
		Integer originalOrderId = mlfrontOrder.getOrderId();
		String filnanyNumber = mlfrontOrder.getOrderProNumStr();
		Integer CouponId =mlfrontOrder.getOrderCouponId();
		//System.out.println("点结算按钮的时候,接收到的CouponId:"+CouponId);
		Integer orderPayPlateInt = mlfrontOrder.getOrderPayPlate();//客户选择的支付方式，0paypal,1待定
		String buyMessStr = mlfrontOrder.getOrderBuyMess();
		//1.0用order查orderItem,遍历orderItem,计算每个Item的价格，再加在一起；
		String Orderitemidstr = mlfrontOrder.getOrderOrderitemidstr();
		String orderitemidArr[] = Orderitemidstr.split(",");
		BigDecimal totalprice = new BigDecimal(0);	//初始化最终价格参数
		DecimalFormat df1 = new DecimalFormat("0.00");
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemRes = new MlfrontOrderItem();
		String orderitemidArri="";
		for(int i=0;i<orderitemidArr.length;i++){
			BigDecimal oneAllprice = new BigDecimal(0);//01初始化字段，用来存本sku的钱
			System.out.println("orderitemidArr[i]:"+orderitemidArr[i]);
			orderitemidArri = orderitemidArr[i].trim();
			Integer orderItemId = Integer.parseInt(orderitemidArri);
			mlfrontOrderItemReq.setOrderitemId(orderItemId);
			List<MlfrontOrderItem> mlfrontOrderItemList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
			mlfrontOrderItemRes = mlfrontOrderItemList.get(0);
			BigDecimal ItemProductOriginalprice =mlfrontOrderItemRes.getOrderitemProductOriginalprice();
			String OrderitemPskuMoneystr = mlfrontOrderItemRes.getOrderitemPskuMoneystr();
			Integer number = mlfrontOrderItemRes.getOrderitemPskuNumber();
			Integer accoff = mlfrontOrderItemRes.getOrderitemProductAccoff();
			String PskuMoneystr[] = OrderitemPskuMoneystr.split(",");
			BigDecimal pskuMoneyOne = new BigDecimal(0);
			String pskuTrimStr="";
			for(int j =0;j<PskuMoneystr.length;j++){
				pskuTrimStr = PskuMoneystr[j].trim();
				pskuMoneyOne = new BigDecimal(pskuTrimStr);
				oneAllprice = oneAllprice.add(pskuMoneyOne);//02计算本orderItem下的所有sku项的钱
			}
			oneAllprice=oneAllprice.add(ItemProductOriginalprice);//03叠加本品基础价的钱(now：该条的allPskumoney+该条Pro的基础价)
			//计算这一项的价格，(基础价格+每个的sku价格的和)*折扣*数量,存入orderitemPskuReamoney字段中;
			oneAllprice = oneAllprice.multiply(new BigDecimal(number));//04乘本品的个数得到总价
			oneAllprice = oneAllprice.multiply(new BigDecimal(accoff));//05乘本品的折扣
			oneAllprice = oneAllprice.multiply(new BigDecimal(0.01));//06还原本品+sku集合的最终价
			String str = df1.format(oneAllprice);
			//System.out.println("OrderitemPskuReamoney原始值:"+oneAllprice);
			System.out.println("存进去的OrderitemPskuReamoney:"+str); //13.15
			MlfrontOrderItem mlfrontOrderItemMoneyBlack = new MlfrontOrderItem();
			mlfrontOrderItemMoneyBlack.setOrderitemId(orderItemId);
			mlfrontOrderItemMoneyBlack.setOrderitemPskuReamoney(str);
			//更新本条，存入orderitemPskuReamoney字段
			mlfrontOrderItemService.updateByPrimaryKeySelective(mlfrontOrderItemMoneyBlack);
			//一个字段存储总价格
			totalprice = totalprice.add(oneAllprice);//07总价字段累加该条的全部价格
		}
		/*		加		单个的	(基础价格+每个的sku价格的和)*折扣*数量,
		 * 		加				地址运费
		 * 		减				优惠价格
		 * */
		//2.0计算地址价格，计算优惠价格，插入order项目价格
		//2.1拿到地址ID,
		Integer AddressId = mlfrontOrder.getAddressinfoId();
		
		if(AddressId==null){
			AddressId= (Integer) session.getAttribute("realAddressId");
		}
		//2.2查询英文名,查询该英文名的价格运费价格
		Integer addressMoney = getAddressMoney(AddressId);
		
		String addressMoneyStr= df1.format(addressMoney);
		session.setAttribute("addressMoney", addressMoneyStr);
		//2.2加上地区快递费
		totalprice = totalprice.add(new BigDecimal(addressMoney));
		//拿到优惠码Code,
		String CouponCode = mlfrontOrder.getOrderCouponCode();
		//查询该优惠码的优惠价格
		BigDecimal CouponCodeMoney = getCouponCodeMoney(CouponCode, totalprice);
		String CouponCodeMoneyStr= df1.format(CouponCodeMoney);
		session.setAttribute("CouponCodeMoney", CouponCodeMoneyStr);
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

		//6获取returnmoney
		String orderAllMoney = GetOrderTopayinfoMoney(Orderitemidstr);
		
		String addressMoneyendStr = addressMoney+"";
		
		String amTotal = getamountTotal(orderAllMoney,CouponCodeMoneyStr,addressMoneyendStr);
		
		BigDecimal amTotalBig=new BigDecimal(amTotal);  
		
		mlfrontPayInfoNew.setPayinfoMoney(amTotalBig);//总钱数排除一分钱后的计算结果
		mlfrontPayInfoNew.setPayinfoCreatetime(nowTime);
		mlfrontPayInfoNew.setPayinfoMotifytime(nowTime);
		mlfrontPayInfoService.insertSelective(mlfrontPayInfoNew);
		List<MlfrontPayInfo> mlfrontPayInfoResList = mlfrontPayInfoService.selectMlfrontPayInfoAll();
		MlfrontPayInfo mlfrontPayInfoResOne = mlfrontPayInfoResList.get(0);
		Integer payinfoId = mlfrontPayInfoResOne.getPayinfoId();
		session.setAttribute("payinfoId", payinfoId);
		
		session.setAttribute("sendAddressinfoId", payAddressinfoId);
		
		//取出sessionid，再次放入
		Integer orderIdFinally = (Integer) session.getAttribute("orderId");
		session.setAttribute("orderId", orderIdFinally);
		//4.0传入orderid,查询其中的orderItemID,找到cartID 找到cartid,移除购物车中的
		updateCart(mlfrontOrder);
		//5.0发起支付
		Integer isSuccess = 0;//返回0，跳支付成功页面;返回1，跳支付失败页面
		return Msg.success().add("resMsg", "更新成功").add("isSuccess", isSuccess);
	}

	/**
	 * 6.1计算折后的筹款价格
	 * returnMoney
	 * */
	private String GetOrderTopayinfoMoney(String orderitemidstr) {
		
		String orderitemidArr[] = orderitemidstr.split(",");
		MlfrontOrderItem mlfrontOrderItemMoneyReq = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemMoneyRes = new MlfrontOrderItem();
		List<MlfrontOrderItem> mlfrontOrderItemList = new ArrayList<MlfrontOrderItem>();
		String orderitemidArriReturn="";
		for(int i=0;i<orderitemidArr.length;i++){
			//获取便利orderItemId
			orderitemidArriReturn = orderitemidArr[i].trim();
			Integer orderItemId = Integer.parseInt(orderitemidArriReturn);
			mlfrontOrderItemMoneyReq.setOrderitemId(orderItemId);
			List<MlfrontOrderItem> mlfrontOrderItemMoneyOneList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemMoneyReq);
			mlfrontOrderItemMoneyRes = mlfrontOrderItemMoneyOneList.get(0);
			mlfrontOrderItemList.add(mlfrontOrderItemMoneyRes);
		}
		String subMoney = "";
		if(mlfrontOrderItemList.size()>1){
  			subMoney = getItemListsMoney(mlfrontOrderItemList);
  		}else{
  			MlfrontOrderItem mlfrontOrderItem = mlfrontOrderItemList.get(0);

  			Integer skuNum=mlfrontOrderItem.getOrderitemPskuNumber();
  			String money = mlfrontOrderItem.getOrderitemPskuReamoney();
  			String oneMoney = getOnemoney(skuNum,money);
  			money = getOneAllMoney(skuNum,oneMoney);
  			subMoney = money;
  		}
		//此时subMoney为orderItemList的全部价格
		return subMoney;
	}

	/**
	 * 上面方法的子方法
	 * 获取getItemListsMoney的全部价格
	 * */
	private String getItemListsMoney(List<MlfrontOrderItem> mlfrontOrderItemList) {
		Double MoneyDuball=new Double("0.00");
		for(MlfrontOrderItem mlfrontOrderItem:mlfrontOrderItemList){
			Integer skuNum=mlfrontOrderItem.getOrderitemPskuNumber();
			String money = mlfrontOrderItem.getOrderitemPskuReamoney();
			String oneMoney =getOnemoney(skuNum,money);
			money = getOneAllMoney(skuNum,oneMoney);
			Double MoneyDub=new Double(money);
			MoneyDuball=MoneyDuball+ MoneyDub;
        }
		String OneOrderAllMoney = String.format("%.2f", MoneyDuball);
		return OneOrderAllMoney;
	}

	/**
	 * 上面方法的子方法
	 * 获取单条OrderItemMoney*num后的总价格
	 * */
	private String getOneAllMoney(Integer skuNum, String oneMoney) {
		Double oneMoneyDou = new Double(oneMoney);
		Double OneAllM = oneMoneyDou*skuNum;
		String OneAllMoney = String.format("%.2f", OneAllM);
		return OneAllMoney;
	}

	/**
	 * 上面方法的子方法
	 * 获取单条OrderItemMoney的总价格
	 * */
	private String getOnemoney(Integer skuNum, String money) {
		Double moneyAll = new Double(money);
		Double oneM = moneyAll/skuNum;
		String Onemoney = String.format("%.2f", oneM);
		return Onemoney;
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
	 * @param totalprice 
	 * */
	private BigDecimal getCouponCodeMoney(String couponCode, BigDecimal totalprice) {
		
		MlbackCoupon mlbackCouponReq = new MlbackCoupon();
		mlbackCouponReq.setCouponCode(couponCode);
		List<MlbackCoupon> mlbackCouponResList =mlbackCouponService.selectMlbackCouponBYCode(mlbackCouponReq);
		
		BigDecimal mlbackCouponPrice = new BigDecimal(0.00);
		if(mlbackCouponResList.size()>0){
			MlbackCoupon mlbackCouponOne =mlbackCouponResList.get(0);
			String couponType = mlbackCouponOne.getCouponType();
			if("0".equals(couponType)){
				//如果是0类满减券,直接取出;
				mlbackCouponPrice = mlbackCouponOne.getCouponPrice();
			}else{
				//如果是1类折扣券,计算完取出取出;
				BigDecimal mlbackCouponPriceOff = mlbackCouponOne.getCouponPriceOff();
				BigDecimal mlbackCouponPricebaifenbi = new BigDecimal(0.01);
				mlbackCouponPrice = totalprice.multiply(mlbackCouponPriceOff);
				mlbackCouponPrice = mlbackCouponPrice.multiply(mlbackCouponPricebaifenbi);
			}
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
	 * 3.4处理一分钱问题
	 * updateCart
	 * */
	private String getamountTotal(String subMoney, String shopdiscount, String addressMoney) {
		
		Double subMoneyDou = Double.parseDouble(subMoney);
		
		Double shopdiscountDou = Double.parseDouble(shopdiscount);
		
		Double addressMoneyDou = Double.parseDouble(addressMoney);
		
		Double amountTotalDou = subMoneyDou - shopdiscountDou + addressMoneyDou;
		
		String amountTotalDouStr = (String.format("%.2f", amountTotalDou));
		
		return amountTotalDouStr;
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
		Integer areafreightMoney = 0;
		if(mlfrontOrderResList.size()>0){
			mlfrontOrderOne =mlfrontOrderResList.get(0);

			addressinfoId = mlfrontOrderOne.getAddressinfoId();
			mlfrontAddressReq.setAddressId(addressinfoId);
			mlfrontAddressList =mlfrontAddressService.selectMlfrontAddressById(mlfrontAddressReq);
			if(mlfrontAddressList.size()>0){
				mlfrontAddressRes = mlfrontAddressList.get(0);
				//从中取出国家字段
				String areafreightCountryEnglish = mlfrontAddressRes.getAddressCountry();
				//接受categoryId
				MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
				mlbackAreafreightReq.setAreafreightCountryEnglish(areafreightCountryEnglish);
				//查询本条
				List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByEng(mlbackAreafreightReq);
				if(mlbackAreafreightResList.size()>0){
					areafreightMoney =mlbackAreafreightResList.get(0).getAreafreightPrice();
				}
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
					.add("addressInfo", mlfrontAddressRes).add("areafreightMoney", areafreightMoney);
	}
	
	/**
	 * 11.0 onuse  20200101  检查
	 * to	填写物流单号-更改为发货状态
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/updateOrderDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateOrderDetail(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrder mlfrontOrder) {

//		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		
		Integer orderId = mlfrontOrder.getOrderId();
		String orderLogisticsname =mlfrontOrder.getOrderLogisticsname();	//物流名字
		String orderLogisticsnumber =  mlfrontOrder.getOrderLogisticsnumber();//物流单号
		Integer payInfoId = mlfrontOrder.getOrderCouponId();
		
		String payinfoPlateNum = mlfrontOrder.getOrderCouponCode();//物流单号
		
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
		MlfrontOrder mlfrontOrderReq2 = new MlfrontOrder();
		mlfrontOrderReq.setOrderId(orderId);
		mlfrontOrderReq.setOrderLogisticsname(orderLogisticsname);
		mlfrontOrderReq.setOrderLogisticsnumber(orderLogisticsnumber);
		String nowTime = DateUtil.strTime14s();
		mlfrontOrderReq.setOrderStatus(4);//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款
		mlfrontOrderReq.setOrderSendtime(nowTime);
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
		
		//通过orderId查询文件
		mlfrontOrderReq2.setOrderId(orderId);
		List<MlfrontOrder> mlfrontOrderResList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq2);
		MlfrontOrder mlfrontOrderRes = mlfrontOrderResList.get(0);
		Integer addressId = mlfrontOrderRes.getAddressinfoId();
		
		//10.1向afterShip官方发送物流添加按钮
//		try {
//			//向物流中插入物流单号，订单号，Item,价格，
//			String resultStr =  shipInformation.addTrackingNumberIntoAfterShip(orderLogisticsnumber,payinfoPlateNum);
//			System.out.println(resultStr);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("有异常");
//			System.out.println(e.getMessage());
//		}
		
		//10.2
		sendLogisticsnumberEmail(addressId,orderLogisticsname,orderLogisticsnumber,orderId,payInfoId);
		
		return Msg.success().add("Msg", "更新成功");
	}

	//10.2
	private void sendLogisticsnumberEmail(Integer addressId, String orderLogisticsname, String orderLogisticsnumber,Integer orderId,Integer payInfoId) {
		
		MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
		MlfrontAddress mlfrontAddressRes = new MlfrontAddress();
		mlfrontAddressReq.setAddressId(addressId);
		List<MlfrontAddress> mlfrontAddressResList = mlfrontAddressService.selectMlfrontAddressById(mlfrontAddressReq);
		mlfrontAddressRes = mlfrontAddressResList.get(0);
		System.out.println(mlfrontAddressRes.toString());
		String userEmail = mlfrontAddressRes.getAddressEmail();
		
		//通过payInfoId查询订单号如:ML201910250000321
		MlfrontPayInfo mlfrontPayInfo = new MlfrontPayInfo();
		MlfrontPayInfo mlfrontPayInfoRes = new MlfrontPayInfo();
		List<MlfrontPayInfo> mlfrontPayInfoResList = new ArrayList<MlfrontPayInfo>();
		mlfrontPayInfo.setPayinfoId(payInfoId);
		
		mlfrontPayInfoResList = mlfrontPayInfoService.selectMlfrontPayInfoById(mlfrontPayInfo);
		mlfrontPayInfoRes = mlfrontPayInfoResList.get(0);
		String payinfoPlateNum = mlfrontPayInfoRes.getPayinfoPlateNum();
		
		//10.1.1
		String toCustomerInfoStr = getToCustomerDriverInfo(orderLogisticsname,orderLogisticsnumber,orderId,payinfoPlateNum);
		try {
			//测试方法
			String getToEmail = userEmail;
			String Message = "您在Megalook购买的秀发已经发货,请留意关注订单号为"+orderLogisticsnumber+"的,"+orderLogisticsname+"快件.";
			EmailUtilshtml.readyEmailSendSuccess(getToEmail, Message,toCustomerInfoStr,orderId,payinfoPlateNum);
			EmailUtilshtmlCustomer.readyEmailSendSuccessCustomer(getToEmail, Message,toCustomerInfoStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//10.1.1
	private String getToCustomerDriverInfo(String orderLogisticsname, String orderLogisticsnumber, Integer orderId,String payinfoPlateNum) {
		
		String Message ="";
		MlbackShipEmail mlbackShipEmail = new MlbackShipEmail();
		mlbackShipEmail.setShipemailNameth(Integer.parseInt(orderLogisticsname));
		List<MlbackShipEmail> mlbackShipEmailList = mlbackShipEmailService.selectMlbackShipEmailByshipemailNameth(mlbackShipEmail);
		MlbackShipEmail mlbackShipEmailOne = mlbackShipEmailList.get(0);
		
		String shipemailName = mlbackShipEmailOne.getShipemailName();
		String shipemailDay = mlbackShipEmailOne.getShipemailDay();
		String shipemailWwwlink = mlbackShipEmailOne.getShipemailWwwlink();
		String shipemailTeam = mlbackShipEmailOne.getShipemailTeam();
		String shipemailTeamemail = mlbackShipEmailOne.getShipemailTeamemail();
		String shipemailTeamwhatsapp = mlbackShipEmailOne.getShipemailTeamwhatsapp();
		String shipemailTeamtelphone = mlbackShipEmailOne.getShipemailTeamtelphone();
		Message =Message+"Hi,"+"<br><br>";
		Message=Message+"This is Megalook Hair. Your order # ("+payinfoPlateNum+") has been shipped, And the tracking number is "+shipemailName+" : ("+orderLogisticsnumber+").<br><br><br>";
		Message=Message+" "+shipemailDay+" .<br><br>";
		Message=Message+"You can track the parcel through this link on "+shipemailName+"( "+shipemailWwwlink+" )<br><br><br>";
		Message=Message+"Please don't hesitate to call me if you need help. We still here behind Megalook Hair.<br><br>";
		Message=Message+"Nice Day!<br><br>";
		Message=Message+"-----------------------------------<br><br>";
		Message=Message+shipemailTeam+"<br>";
		Message=Message+"Email : "+shipemailTeamemail+"<br>";
		Message=Message+"Whatsapp : "+shipemailTeamwhatsapp+"<br>";
		Message=Message+"Telephone/SMS : "+shipemailTeamtelphone+"<br>";
		return Message;
	}
	
	/**
	 * 11.0 onuse  20200101
	 * to	审核订单updateOrderVerifyed
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/updateOrderReady",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateOrderReady(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrder mlfrontOrder) {
		
		Integer orderId = mlfrontOrder.getOrderId();
		
		Integer payInfoId = mlfrontOrder.getOrderCouponId();
		
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
		MlfrontOrder mlfrontOrderReq2 = new MlfrontOrder();
		mlfrontOrderReq.setOrderId(orderId);
		String nowTime = DateUtil.strTime14s();
		mlfrontOrderReq.setOrderStatus(3);//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款
		mlfrontOrderReq.setOrderSendtime(nowTime);
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
		
		//通过orderId查询用户地址信息
		mlfrontOrderReq2.setOrderId(orderId);
		List<MlfrontOrder> mlfrontOrderResList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq2);
		MlfrontOrder mlfrontOrderRes = mlfrontOrderResList.get(0);
		Integer addressId = mlfrontOrderRes.getAddressinfoId();
		//通过用户地址信息查询Email
		MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
		MlfrontAddress mlfrontAddressRes = new MlfrontAddress();
		mlfrontAddressReq.setAddressId(addressId);
		List<MlfrontAddress> mlfrontAddressResList = mlfrontAddressService.selectMlfrontAddressById(mlfrontAddressReq);
		mlfrontAddressRes = mlfrontAddressResList.get(0);
		String userEmail = mlfrontAddressRes.getAddressEmail();
		
		//通过payInfoId查询订单号如:ML201910250000321
		MlfrontPayInfo mlfrontPayInfo = new MlfrontPayInfo();
		MlfrontPayInfo mlfrontPayInfoRes = new MlfrontPayInfo();
		List<MlfrontPayInfo> mlfrontPayInfoResList = new ArrayList<MlfrontPayInfo>();
		mlfrontPayInfo.setPayinfoId(payInfoId);
		
		mlfrontPayInfoResList = mlfrontPayInfoService.selectMlfrontPayInfoById(mlfrontPayInfo);
		mlfrontPayInfoRes = mlfrontPayInfoResList.get(0);
		String payinfoPlateNum = mlfrontPayInfoRes.getPayinfoPlateNum();
		
		String toCustomerVerifyInfoStr = "";
		
		//查询
		MlbackOrderStateEmail mlbackOrderStateEmailReq = new MlbackOrderStateEmail();
		mlbackOrderStateEmailReq.setOrderstateemailName("Verifyed");
		//查询本条
		List<MlbackOrderStateEmail> mlbackOrderStateEmailList =mlbackOrderStateEmailService.selectMlbackOrderStateEmailByName(mlbackOrderStateEmailReq);
		if(mlbackOrderStateEmailList.size()>0){
			
			MlbackOrderStateEmail mlbackOrderStateEmailOne =mlbackOrderStateEmailList.get(0);
			
			toCustomerVerifyInfoStr = getToCustomerVerifyEmailManage(mlbackOrderStateEmailOne,payinfoPlateNum);
		}else{
			
			toCustomerVerifyInfoStr = getToCustomerVerifyEmail(payinfoPlateNum);
		}
		//11.1
		try {
			//提醒客户准备发货
			String getToEmail = userEmail;
			EmailUtilshtml.readyEmailVerifySuccess(getToEmail, toCustomerVerifyInfoStr,payinfoPlateNum);
			EmailUtilshtmlCustomer.readyEmailVerifyCustomer(getToEmail, toCustomerVerifyInfoStr,payinfoPlateNum);
			//11.2
			updatePayInfoVerifyedstatus(payInfoId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Msg.success().add("Msg", "更新成功");
	}


	private String getToCustomerVerifyEmailManage(MlbackOrderStateEmail mlbackOrderStateEmailOne,String payinfoPlateNum) {
		
		String emailOneStr =  mlbackOrderStateEmailOne.getOrderstateemailOne();
		String emailTwoStr =  mlbackOrderStateEmailOne.getOrderstateemailTwo();
		String emailThreeStr =  mlbackOrderStateEmailOne.getOrderstateemailThree();
		String emailFourStr =  mlbackOrderStateEmailOne.getOrderstateemailFour();
		String emailFiveStr =  mlbackOrderStateEmailOne.getOrderstateemailFive();
		String Message ="";
		Message =Message+"Hi gorgeous girl ,"+"<br><br>";
		Message=Message+emailOneStr+" # ("+payinfoPlateNum+") "+emailTwoStr+"<br><br>";
		Message=Message+emailThreeStr+".<br><br>";
		Message=Message+emailFourStr+"<br><br>";
		if("".equals(emailFiveStr)){
			System.out.println("emailFiveStr:"+emailFiveStr+"这句话为空");
		}else{
			Message=Message+emailFiveStr+"<br><br>";
		}
		Message=Message+"Best Regards,<br>";
		Message=Message+"-----------------------------------<br>";
		String team = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.team");
		String email = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.email");
		String whatsapp = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.whatsapp");
		String Telephone = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.Telephone");
		//读取配置文件
		Message=Message+team+"<br>";
		Message=Message+"Email:"+email+"<br>";
		Message=Message+"Whatsapp:"+whatsapp+"<br>";
		Message=Message+"Telephone/SMS:"+Telephone+"<br>";
		return Message;
	}

	//11.1
	private String getToCustomerVerifyEmail(String payinfoPlateNum) {
		String Message ="";
		Message =Message+"Hi gorgeous girl ,"+"<br><br>";
		Message=Message+"This is Megalook Hair  <br>. ";
		Message=Message+"We have received your order # ("+payinfoPlateNum+")  and confirmed your payment. <br><br><br>";
		Message=Message+"The hair you ordered is in stock and is expected to be shipped within 24-48 hours .<br><br>";
		Message=Message+"We will send the parcel tracking number to you through email & SMS after delivery, and you can also view it on the PayPal bill.<br><br><br>";
		Message=Message+"Please don't hesitate to call me if you need help. We still here behind Megalook Hair.<br><br>";
		Message=Message+"Best Regards,<br>";
		Message=Message+"-----------------------------------<br>";
		String team = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.team");
		String email = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.email");
		String whatsapp = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.whatsapp");
		String Telephone = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.Telephone");
		//读取配置文件
		Message=Message+team+"<br>";
		Message=Message+"Email:"+email+"<br>";
		Message=Message+"Whatsapp:"+whatsapp+"<br>";
		Message=Message+"Telephone/SMS:"+Telephone+"<br>";
		return Message;
	}
	
	//11.1节假日版本
//	private String getToCustomerVerifyEmail(String payinfoPlateNum) {
//	String Message ="";
//	Message=Message+"Dear valued clients ,"+"<br><br>";
//	Message=Message+"This is Megalook Hair . We have received your order # ("+payinfoPlateNum+")  and confirmed your payment. <br><br>";
//	Message=Message+"We would like to inform u that accordance with the law of China ,Megalook hair team will have a holiday of The Spring Festival from January 23rd to January 30th.";
//	Message=Message+"We are sorry can't reply u timely and can't ship out the package for u before January 30th, the package will ship out ASAP when we back.<br><br>";
//	Message=Message+"Hope to understand and best wish for u!<br><br>";
//	Message=Message+"Best Regards,<br><br><br>";
//	Message=Message+"-----------------------------------<br>";
//	Message=Message+"Megalook hair <br>";
//	Message=Message+"Email:service@megalook.com <br>";
//	Message=Message+"Whatsapp:+86 18903740682 <br>";
//	Message=Message+"Telephone/SMS:+1 5017226336<br>";
//	return Message;
//}
	
	//11.2	更改payinfo为审核状态
	private void updatePayInfoVerifyedstatus(Integer payInfoId) {
		//取出id
		MlfrontPayInfo mlfrontPayInfo = new MlfrontPayInfo();
		mlfrontPayInfo.setPayinfoId(payInfoId);
		String nowTime = DateUtil.strTime14s();
		mlfrontPayInfo.setPayinfoMotifytime(nowTime);
		mlfrontPayInfo.setPayinfoStatus(2);//0未支付//1支付成功//2审单完毕//3发货完毕 //4已退款
		//更新状态未已发货状态
		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfo);
		
	}
	
	/**
	 * 12.0	UseNow	0505
	 * to	订单-退款
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/updateOrderRefund",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateOrderRefund(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrder mlfrontOrder) {
		//接手参数
		Integer orderId = mlfrontOrder.getOrderId();
		Integer payInfoId = mlfrontOrder.getOrderCouponId();//此处使用OrderCouponId字段暂时存储的payInfoId
		//更新order为退款状态
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
		mlfrontOrderReq.setOrderId(orderId);
		String nowTime = DateUtil.strTime14s();
		mlfrontOrderReq.setOrderStatus(5);//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款
		mlfrontOrderReq.setOrderSendtime(nowTime);
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
		//更新order为退款状态
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoId(payInfoId);
		mlfrontPayInfoReq.setPayinfoStatus(4);//0未支付//1支付成功//2审单完毕//3发货完毕 //4已退款
		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoReq);

		return Msg.success().add("Msg", "更新成功");
	}
	
	/**
	 * 13.0	UseNow	200309
	 * to	订单-发送弃购按钮
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/updateOrderAbandoningPurchase",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateOrderAbandoningPurchase(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrder mlfrontOrder) {
		//接手参数
		Integer orderId = mlfrontOrder.getOrderId();
		Integer payInfoId = mlfrontOrder.getOrderCouponId();//此处使用OrderCouponId字段暂时存储的payInfoId
		String userEmail = mlfrontOrder.getOrderBuyMess();
		String orderitemidstr = mlfrontOrder.getOrderOrderitemidstr();
		//更新order为退款状态
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
		mlfrontOrderReq.setOrderId(orderId);
		String nowTime = DateUtil.strTime14s();
		mlfrontOrderReq.setOrderStatus(6);//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款//6发送弃购//7重复单关闭
		mlfrontOrderReq.setOrderSendtime(nowTime);
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
		//更新PayInfo为弃购状态
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoId(payInfoId);
		mlfrontPayInfoReq.setPayinfoStatus(5);//0未支付//1支付成功//2审单完毕//3发货完毕 //4已退款//5发送弃购//6重复单关闭
		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoReq);
		
		//获取订单信息，OrderItemList
		
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		
		String[] orderitemidstrArr = orderitemidstr.split(",");
		
		List<MlfrontOrderItem> mlfrontOrderItemDetailList = new ArrayList<MlfrontOrderItem>();
		
		MlbackProduct mlbackProductReq = new MlbackProduct();
		
		List<MlbackProduct> mlbackProductResList = new ArrayList<MlbackProduct>();
		
		MlbackProduct mlbackProductRes = new MlbackProduct();
		
		List<MlbackProduct> mlbackProductDetailList = new ArrayList<MlbackProduct>();
		
		for(int i=0;i<orderitemidstrArr.length;i++){
			
			String orderitemIdStr = orderitemidstrArr[i];
			Integer orderitemIdInt = Integer.parseInt(orderitemIdStr);
			mlfrontOrderItemReq.setOrderitemId(orderitemIdInt);
			List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
			MlfrontOrderItem mlfrontOrderItemOne = mlfrontOrderItemList.get(0);
			mlfrontOrderItemDetailList.add(mlfrontOrderItemOne);
			
			Integer Pid = mlfrontOrderItemOne.getOrderitemPid();
			mlbackProductReq.setProductId(Pid);
			mlbackProductResList = mlbackProductService.selectMlbackProduct(mlbackProductReq);
			mlbackProductRes = mlbackProductResList.get(0);
			
			mlbackProductDetailList.add(mlbackProductRes);
		}
		
		//查回弃购客服问话
		MlbackAbandonPurchase mlbackAbandonPurchaseReq = new MlbackAbandonPurchase();
		mlbackAbandonPurchaseReq.setAbandonpurchaseStatus(1);//生效中
		List<MlbackAbandonPurchase> mlbackAbandonPurchaseList = mlbackAbandonPurchaseService.selectMlbackAbandonPurchaseByStatus(mlbackAbandonPurchaseReq);
		MlbackAbandonPurchase mlbackAbandonPurchaseOne = new MlbackAbandonPurchase();
		if(mlbackAbandonPurchaseList.size()>0){
			
			mlbackAbandonPurchaseOne = mlbackAbandonPurchaseList.get(0);
		}else{
			mlbackAbandonPurchaseOne.setAbandonpurchaseActivedetail("null,null");
			mlbackAbandonPurchaseOne.setAbandonpurchaseCoupondetail("null,null");
		}
		
		//11.1
		String toCustomerAbandoningPurchaseStr = getToCustomerAbandoningPurchaseEmail(mlfrontOrderItemDetailList,mlbackAbandonPurchaseOne,mlbackProductDetailList);
		try {
			//提醒客户准备发货
			String getToEmail = userEmail;
			EmailUtilshtml.readyEmailAbandoningPurchaseSuccess(getToEmail, toCustomerAbandoningPurchaseStr);
			EmailUtilshtmlCustomer.readyEmailAbandoningPurchaseCustomer(getToEmail, toCustomerAbandoningPurchaseStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Msg.success().add("Msg", "更新成功");
	}
	
	private String getToCustomerAbandoningPurchaseEmail(List<MlfrontOrderItem> mlfrontOrderItemDetailList,MlbackAbandonPurchase mlbackAbandonPurchase,List<MlbackProduct> mlbackProductDetailList) {
		
		
		String activeStr = mlbackAbandonPurchase.getAbandonpurchaseActivedetail();
		String couponStr = mlbackAbandonPurchase.getAbandonpurchaseCoupondetail();
		
		String Message ="";
		Message=Message+"Hi gorgeous girl"+"<br>";
		Message=Message+"This is Megalook Hair.<br>";
		Message=Message+"We have noticed you have placed an order before but you gave it up.  Is there anything i can help you?<br><br>";
		Message=Message+activeStr+"."+couponStr+".<br><br>";
		
		String proName = "";
		String proSeoName = "";
		
		for(int i=0;i<mlfrontOrderItemDetailList.size();i++){
			proName = mlbackProductDetailList.get(i).getProductName();
			Message=Message+""+proName+"<br>";
			
			proSeoName = mlbackProductDetailList.get(i).getProductSeo();
			//从配置文件中读取weblink字段(https://www.megalook.com/)
			String weblink = (String) PropertiesUtil.getProperty("megalook.properties", "weblink");
			
			Message=Message+weblink+proSeoName+".html"+"<br><br>";
		}
		
		Message=Message+"We'll be there if you need help<br>";
		Message=Message+"Best Regards,<br>";
		Message=Message+"-----------------------------------<br>";
		String team = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.team");
		String email = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.email");
		String whatsapp = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.whatsapp");
		String Telephone = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.Telephone");
		//读取配置文件
		Message=Message+team+"<br>";
		Message=Message+"Email:"+email+"<br>";
		Message=Message+"Whatsapp:"+whatsapp+"<br>";
		Message=Message+"Telephone/SMS:"+Telephone+"<br>";
		return Message;
	}

	/**
	 * 14.0	UseNow	200309
	 * to	订单-退款
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/updateOrderClose",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateOrderClose(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrder mlfrontOrder) {
		//接手参数
		Integer orderId = mlfrontOrder.getOrderId();
		Integer payInfoId = mlfrontOrder.getOrderCouponId();//此处使用OrderCouponId字段暂时存储的payInfoId
		//更新order为退款状态
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
		mlfrontOrderReq.setOrderId(orderId);
		String nowTime = DateUtil.strTime14s();
		mlfrontOrderReq.setOrderStatus(7);//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款//6发送弃购//7重复单关闭
		mlfrontOrderReq.setOrderSendtime(nowTime);
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
		//更新PayInfo为重复单关闭状态
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoId(payInfoId);
		mlfrontPayInfoReq.setPayinfoStatus(6);//0未支付//1支付成功//2审单完毕//3发货完毕 //4已退款//5发送弃购//6重复单关闭
		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoReq);

		return Msg.success().add("Msg", "更新成功");
	}
	
	/**
	 * 14.0	UseNow	200309
	 * to	订单-退款
	 * @param jsp
	 * @return 
	 * */
//	@RequestMapping(value="/getCheckpointByTrackingNumber",method=RequestMethod.GET)
//	@ResponseBody
//	public Msg getCheckpointByTrackingNumber(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
//			@RequestParam(value = "trackingNumber") String trackingNumber) {
//		//接收参数
//		
//		String trackingNumCheckpoint = shipInformation.getCheckpointByTrackingNumberFromAfterShip(trackingNumber);
//		System.out.println("trackingNumCheckpoint:"+trackingNumCheckpoint);
//		
//		return Msg.success().add("Msg", "更新成功");
//	}

}