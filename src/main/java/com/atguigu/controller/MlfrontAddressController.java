package com.atguigu.controller;

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
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.utils.DateUtil;


@Controller
@RequestMapping("/MlfrontAddress")
public class MlfrontAddressController {
		
	@Autowired
	MlfrontAddressService mlfrontAddressService;
	
	@Autowired
	MlbackAreafreightService mlbackAreafreightService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**1.0	UseNow	0505
	 * MlfrontAddress	insert
	 * @param MlfrontAddress
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontAddress mlfrontAddress){
		//接受参数信息
		System.out.println("mlfrontAddress:"+mlfrontAddress);
		
		String areafreightCountryEnglish = mlfrontAddress.getAddressCountry();
		
		//接受categoryId
		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
		mlbackAreafreightReq.setAreafreightCountryEnglish(areafreightCountryEnglish);
		//查询本条
		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByEng(mlbackAreafreightReq);
		Integer areafreightMoney = 0;
		if(mlbackAreafreightResList.size()>0){
			areafreightMoney =mlbackAreafreightResList.get(0).getAreafreightPrice();
		}
		//取出id
		System.out.println(1);
		Integer addressId = mlfrontAddress.getAddressId();
		String nowTime = DateUtil.strTime14s();
		mlfrontAddress.setAddressMotifytime(nowTime);
		MlfrontUser loginUser =  (MlfrontUser) session.getAttribute("loginUser");
		Integer usertype = 0;
		if(loginUser==null){
			usertype = 0;//游客
		}else{
			usertype = 1;//注册用户
		}
		if(loginUser==null){
			String sessionId = session.getId();
			//获取ip地址
			String Userip =sessionId;
			
			if(addressId==null){
				//无id，insert
				mlfrontAddress.setAddressIp(Userip);
				mlfrontAddress.setAddressCreatetime(nowTime);
				int intResult = mlfrontAddressService.insertSelective(mlfrontAddress);
				
				MlfrontAddress mlfrontAddressLast = mlfrontAddressService.selectMlfrontAddressAll().get(0);
				
				System.out.println(intResult);
				
				return Msg.success().add("resMsg", "游客地址信息插入成功").add("mlfrontAddress", mlfrontAddressLast).add("areafreightMoney", areafreightMoney).add("usertype", usertype);//新增以后，返回去的这里，有id，你从这里拿
			}else{
				//有id，update
				mlfrontAddress.setAddressMotifytime(nowTime);
				int intResult = mlfrontAddressService.updateByPrimaryKeySelective(mlfrontAddress);
				System.out.println(intResult);
				//MlfrontAddress mlfrontAddressres = mlfrontAddressService.selectMlfrontAddressByIp(Userip);
				return Msg.success().add("resMsg", "游客地址信息更新成功").add("mlfrontAddress", mlfrontAddress).add("areafreightMoney", areafreightMoney).add("usertype", usertype);
			}		
		}else{
			Integer uid = loginUser.getUserId();
			mlfrontAddress.setAddressUid(uid);
			if(addressId==null){
				//无id，insert
				mlfrontAddress.setAddressCreatetime(nowTime);
				int intResult = mlfrontAddressService.insertSelective(mlfrontAddress);
				
				MlfrontAddress mlfrontAddressLast = mlfrontAddressService.selectMlfrontAddressAll().get(0);
				
				System.out.println(intResult);
				
				return Msg.success().add("resMsg", "登录用户地址信息插入成功").add("mlfrontAddress", mlfrontAddressLast).add("areafreightMoney", areafreightMoney).add("usertype", usertype);//新增以后，返回去的这里，有id，你从这里拿
			}else{
				//有id，update
				mlfrontAddress.setAddressMotifytime(nowTime);
				int intResult = mlfrontAddressService.updateByPrimaryKeySelective(mlfrontAddress);
				System.out.println(intResult);
				//MlfrontAddress mlfrontAddressres = mlfrontAddressService.selectMlfrontAddressByIp(Userip);
				return Msg.success().add("resMsg", "登录用户地址信息更新成功").add("mlfrontAddress", mlfrontAddress).add("areafreightMoney", areafreightMoney).add("usertype", usertype);
			}	
		}
	}
	/**1.1	UseNow	0505
	 * MlfrontAddress	insert
	 * @param MlfrontAddress
	 */
	@RequestMapping(value="/getAreafreightMoney",method=RequestMethod.POST)
	@ResponseBody
	public Msg getAreafreightMoney(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontAddress mlfrontAddress){
		//接受参数信息
		System.out.println("mlfrontAddress:"+mlfrontAddress);
		
		String areafreightCountryEnglish = mlfrontAddress.getAddressCountry();
		
		//接受categoryId
		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
		mlbackAreafreightReq.setAreafreightCountryEnglish(areafreightCountryEnglish);
		//查询本条
		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByEng(mlbackAreafreightReq);
		Integer areafreightMoney = 0;
		if(mlbackAreafreightResList.size()>0){
			areafreightMoney =mlbackAreafreightResList.get(0).getAreafreightPrice();
		}
		//取出id
		System.out.println(1);
		Integer addressId = mlfrontAddress.getAddressId();
		String nowTime = DateUtil.strTime14s();
		mlfrontAddress.setAddressMotifytime(nowTime);
		MlfrontUser loginUser =  (MlfrontUser) session.getAttribute("loginUser");
		Integer usertype = 0;
		if(loginUser==null){
			usertype = 0;//游客
		}else{
			usertype = 1;//注册用户
		}
		return Msg.success().add("resMsg", "查询运费成功").add("areafreightMoney", areafreightMoney).add("usertype", usertype);//新增以后，返回去的这里，有id，你从这里拿
	}
	
	/**2.0	useOn	0505
	 * MlfrontAddress	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlfrontAddress mlfrontAddress){
		//接收id信息
		int addressId = mlfrontAddress.getAddressId();
		int intResult = mlfrontAddressService.deleteByPrimaryKey(addressId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**3.0	useOn	0505
	 * MlfrontAddress	update
	 * @param id MlfrontAddress
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Msg update(@RequestBody MlfrontAddress mlfrontAddress){
		//接受信息
		String nowtime = DateUtil.strTime14s();
		mlfrontAddress.setAddressMotifytime(nowtime);
		//更新本条状态
		int intResult = mlfrontAddressService.updateByPrimaryKeySelective(mlfrontAddress);
		System.out.println(intResult);
		return Msg.success().add("resMsg", "更新成功");
	}
	
	/**
	 * 4.0	useOn	0505
	 * 查看单条类目的详情细节
	 * @param MlbackAreafreight
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontAddressDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontAddressDetail(@RequestParam(value = "addressId") Integer addressId){
		
		//接受categoryId
		MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
		mlfrontAddressReq.setAddressId(addressId);
		//查询本条
		List<MlfrontAddress> mlfrontAddressResList =mlfrontAddressService.selectMlfrontAddressById(mlfrontAddressReq);
		MlfrontAddress mlfrontAddressOne =mlfrontAddressResList.get(0);
		//查询全部的category信息，便于下拉选择
//		List<MlbackCategory> mlbackCategorydownList = mlbackCategoryService.selectMlbackCategoryGetAllByParentId();
//		System.out.println(mlbackCategorydownList);
		return Msg.success().add("resMsg", "查看单条mlfrontAddressOne的详情细节完毕")
					.add("mlfrontAddressOne", mlfrontAddressOne);
	}
	
	/**
	 * 5.0	useOn	0505
	 * 初始化调用 此用户是否有曾经留下的地址	无参数，post请求
	 * @param MlfrontAddress
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontAddressDetailByUinfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontAddressDetailByUinfo(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		
		MlfrontUser loginUser =  (MlfrontUser) session.getAttribute("loginUser");
		Integer usertype = 0;
		Integer uid = 0;
		if(loginUser==null){
			usertype = 0;//游客
		}else{
			usertype = 1;//注册用户
			uid = loginUser.getUserId();
		}
		
		
		MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
		List<MlfrontAddress> mlfrontAddressResList = new ArrayList<MlfrontAddress>();
		if(usertype==0){
			String SessionId = session.getId();
			//接受categoryId
			mlfrontAddressReq.setAddressIp(SessionId);
			
			//查询本条
			mlfrontAddressResList =mlfrontAddressService.selectMlfrontAddressByuInfo(mlfrontAddressReq);
		}else{
			mlfrontAddressReq.setAddressUid(uid);
			
			mlfrontAddressResList =mlfrontAddressService.selectMlfrontAddressByuId(mlfrontAddressReq);
		}
		
		MlfrontAddress mlfrontAddressOne=null;
		Integer areafreightMoney = 0;
		if(mlfrontAddressResList.size()>0){
			 mlfrontAddressOne =mlfrontAddressResList.get(0);
			 
			 String areafreightCountryEnglish = mlfrontAddressOne.getAddressCountry();
				
			 //接受areafreightCountryEnglish
			 MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
			 mlbackAreafreightReq.setAreafreightCountryEnglish(areafreightCountryEnglish);
			 //查询本条
			 List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByEng(mlbackAreafreightReq);
			 
			 if(mlbackAreafreightResList.size()>0){
				areafreightMoney =mlbackAreafreightResList.get(0).getAreafreightPrice();
			 }
		}
		return Msg.success().add("resMsg", "查看单条mlfrontAddressOne的详情细节完毕")
					.add("mlfrontAddressOne", mlfrontAddressOne).add("areafreightMoney", areafreightMoney).add("usertype", usertype);
	}
	
	/**7.0
	 * @author Shinelon
	 * @exception 导出单一发布任务执行明细
	 * @param MlbackAreafreight
	 * @return 
	 * */
//	@ResponseBody
//	@RequestMapping("/exportFile")
//	public String exportFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		response.reset(); 
//		// 接收请求相应
//	    //准备请求头参数
//		String authstatus =request.getParameter("authStatus");
//		String userworkGroupdisplayId =request.getParameter("userworkGroupdisplayId");
//		int authStatus =Integer.parseInt(authstatus);
//		int userworkGroupdisplayIdId =Integer.parseInt(userworkGroupdisplayId);
//		Date date =new Date();
//		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
//		String time =sdf.format(date);
//		response.setHeader("Content-Disposition", "attachment;filename=File" + time +".xlsx");
//		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setDateHeader("Expires", 0);
//	    XSSFWorkbook workbook = null;
//	    try {
//	    	UserWork userWork= new UserWork();
//	    	userWork.setUserworkGroupdisplayId(userworkGroupdisplayIdId);
//	    	if(authStatus==1) {	    		
//	    		List<UserWork> UserWorkList = userWorkService.getUserWorkByConditions(userWork);
//	            workbook = ExcelUtils.exportContactsGroupDisplay(UserWorkList);
//	        }
//	        OutputStream output;
//	        try {
//	        	output = response.getOutputStream();
//	            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
//	            bufferedOutPut.flush();
//	            workbook.write(bufferedOutPut);
//	            bufferedOutPut.close();
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
//	        } catch (Exception e1) {
//	            e1.printStackTrace();
//	        }
//	        return null;
//	}
	
	/**1.1	useOn
	 * 远程调用url,name,pimageUrl
	 * @return Map
	 */
//	private Map<String, String> HttpGetPImgUrl(String id) {
//		HttpUtil httpUtil = new HttpUtil();
//		String urls="https://itunes.apple.com/cn/lookup";
//		String idParm = "id="+id;
//		String result = null;
//		Map<String, String> iosNeedMap= new HashMap<String, String>();
//		try {
//			result = httpUtil.sendPostUrl(urls,idParm,"utf-8");
//			JSONObject JSONObject = new JSONObject(result);
//			String resStr = JSONObject.get("results").toString();
//			String resStr2 =resStr.substring(1, resStr.length()-1);
//			System.out.println(resStr2);
//			JSONObject JSONObjectStr2 = new JSONObject(resStr2);
//			iosNeedMap.put("artworkUrl100", (String) JSONObjectStr2.get("artworkUrl100"));
////			iosNeedMap.put("trackName", (String) JSONObjectStr2.get("trackName"));
//			iosNeedMap.put("bundleId", (String) JSONObjectStr2.get("bundleId"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return iosNeedMap;
//	}
	
}
