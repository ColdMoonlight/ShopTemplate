package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.Vo.SortNumTh;
import com.atguigu.bean.MlbackAddPayinfoViewDetail;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.bean.Msg;
import com.atguigu.service.MlbackAddPayinfoViewDetailService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;


@Controller
@RequestMapping("/MlbackAddPayinfoViewDetail")
public class MlbackAddPayinfoViewDetailController {
	
	@Autowired
	MlbackAddPayinfoViewDetailService mlbackAddPayinfoViewDetailService;
	
	@Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
	
	@Autowired
	MlfrontOrderService mlfrontOrderService;
	
	@Autowired
	MlfrontOrderItemService mlfrontOrderItemService;
		
	/**
	 * 1.0	UseNow	0505
	 * toMlbackAddPayinfoViewDetailPage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackAddPayinfoViewDetailPage")
	public String tologin(HttpSession session) throws Exception{
	
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/mlbackAddPayInfoViewDetail";
		}
	}
	
	/**2.0	UseNow	0505
	 * 分类MlbackActShowPro列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getAddPayinfoViewDetailSuccessNum",method=RequestMethod.POST)
	@ResponseBody
	public Msg getAddPayinfoViewDetailSuccessNum(HttpSession session,@RequestBody MlbackAddPayinfoViewDetail mlbackAddPayinfoViewDetail) {
		
		String starttime = mlbackAddPayinfoViewDetail.getAddpayinfoviewdetailStarttime();
		String endtime = mlbackAddPayinfoViewDetail.getAddpayinfoviewdetailEndtime();
		
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoCreatetime(starttime);
		mlfrontPayInfoReq.setPayinfoMotifytime(endtime);
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoByMotifyTime(mlfrontPayInfoReq);
		
		Integer toDayNum=mlfrontPayInfoList.size();
		return Msg.success().add("toDayNum", toDayNum);
	}
	
	
	/**4.0	UseNow	0505
	 * 分类MlbackProductViewDetail列表list数据
	 * @param
	 * @return
	 */
	@RequestMapping(value="/getAddPayinfoViewDetailList",method=RequestMethod.POST)
	@ResponseBody
	public Msg getAddPayinfoViewDetailList(HttpSession session,@RequestBody MlbackAddPayinfoViewDetail mlbackAddPayinfoViewDetail) {
		
		String starttime = mlbackAddPayinfoViewDetail.getAddpayinfoviewdetailStarttime();
		String endtime = mlbackAddPayinfoViewDetail.getAddpayinfoviewdetailEndtime();
		
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoCreatetime(starttime);
		mlfrontPayInfoReq.setPayinfoMotifytime(endtime);
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoByMotifyTime(mlfrontPayInfoReq);
		
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
		
		List<MlfrontOrderItem> mlfrontOrderItemResList = new ArrayList<MlfrontOrderItem>();
		
		MlfrontOrderItem mlfrontOrderItemOneReq = new MlfrontOrderItem();
		
		MlfrontOrderItem mlfrontOrderItemOne = new MlfrontOrderItem();
		
		List<MlfrontOrderItem> mlfrontOrderItemPayInfoList = new ArrayList<MlfrontOrderItem>();
		
		MlfrontOrder mlfrontOrderOne = new MlfrontOrder();
		
		List<MlfrontOrder> mlfrontOrderList = new ArrayList<MlfrontOrder>();
		
		for(MlfrontPayInfo mlfrontPayInfo:mlfrontPayInfoList){
			Integer payInfoOid = mlfrontPayInfo.getPayinfoOid();
			
			mlfrontOrderReq.setOrderId(payInfoOid);
			
			mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq);
			
			if(mlfrontOrderList.size()>0){
				
				mlfrontOrderOne=mlfrontOrderList.get(0);
				
				String orderitemidstr =  mlfrontOrderOne.getOrderOrderitemidstr();
				
				String orderItemIdStrArr[] = orderitemidstr.split(",");
				String orderItemIdStr = "";
				Integer orderItemIdInt = 0;
				for(int i =0;i<orderItemIdStrArr.length;i++){
					orderItemIdStr = orderItemIdStrArr[i];
					orderItemIdInt = Integer.parseInt(orderItemIdStr);
					mlfrontOrderItemOneReq.setOrderitemId(orderItemIdInt);
					mlfrontOrderItemResList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemOneReq);
					mlfrontOrderItemOne = mlfrontOrderItemResList.get(0);
					mlfrontOrderItemPayInfoList.add(mlfrontOrderItemOne);
				}
			}
		}
		
		String  proSeo = "";
		Integer proSeoNum = 0;
		Integer k=0;
		List<Integer> numList = new ArrayList<Integer>();
		List<String> SeoStringList = new ArrayList<String>();
		
		for(int i=0;i<mlfrontOrderItemPayInfoList.size();i++){
			k++;
			MlfrontOrderItem mlfrontOrderItemPayInfoOne = mlfrontOrderItemPayInfoList.get(i);
			if(proSeo.isEmpty()){
				System.out.println("第一次来，都不记录");
				proSeo = mlfrontOrderItemPayInfoOne.getOrderitemPname();
				SeoStringList.add(proSeo);
			}else{
				proSeo = mlfrontOrderItemPayInfoOne.getOrderitemPname();
				MlfrontOrderItem mlfrontOrderItemPayInfoOneLast =mlfrontOrderItemPayInfoList.get(i-1);
				String lastSeo = mlfrontOrderItemPayInfoOneLast.getOrderitemPname();
				Integer nowPronum = mlfrontOrderItemPayInfoOneLast.getOrderitemPskuNumber();
				if(proSeo.equals(lastSeo)){
					proSeoNum=proSeoNum+nowPronum;
				}else{
					SeoStringList.add(proSeo);
					proSeoNum = k-1;
					numList.add(proSeoNum);
					k=1;
				}
			}
		}
		numList.add(k);
		List<SortNumTh> SortNumThList = new ArrayList<SortNumTh>();
		Integer lenth = SeoStringList.size();
		SortNumTh[] arrayA = new SortNumTh[lenth];
		for(int x=0;x<lenth;x++){
			SortNumTh sortNumThOne = new SortNumTh();
			String SeoString = SeoStringList.get(x);
			Integer numCount = numList.get(x);
			sortNumThOne.setSeoString(SeoString);
			sortNumThOne.setSeoStringCount(numCount);
			SortNumThList.add(sortNumThOne);
			arrayA[x] = sortNumThOne;
		}
		//排序
		for (int m = 0; m < arrayA.length - 1; m++) {				//外循环只需要比较arr.length-1次就可以了
			for (int n = 0; n < arrayA.length - 1 - m; n++) {		//-1为了防止索引越界,-i为了提高效率
				if(arrayA[n].getSeoStringCount() < arrayA[n+1].getSeoStringCount()) {
					SortNumTh temp = arrayA[n];
					arrayA[n] = arrayA[n + 1];
					arrayA[n+1] = temp;
				}
			}
		}
		return Msg.success().add("SeoStringList", SeoStringList).add("numList", numList).add("arrayA", arrayA);
	}
}