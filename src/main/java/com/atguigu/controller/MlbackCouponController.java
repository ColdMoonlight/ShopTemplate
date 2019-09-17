package com.atguigu.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.GroupDisplay;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.Msg;
import com.atguigu.bean.SysUser;
import com.atguigu.bean.UserWork;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.GroupDisplayService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackCouponService;
import com.atguigu.service.MlfrontUserService;
import com.atguigu.service.SysUserService;
import com.atguigu.service.UserWorkService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.ExcelUtils;
import com.atguigu.utils.HttpUtil;


@Controller
@RequestMapping("/MlbackCoupon")
public class MlbackCouponController {
		
	@Autowired
	MlbackCouponService mlbackCouponService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlfrontUserService mlfrontUserService;
	
	/**
	 * 1.0	useOn	0505
	 * to分类MlbackCoupon列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackCouponPage")
	public String tologin() throws Exception{
	
		return "back/mlbackCouponPage";
	}
	
	
	/**2.0	useOn	0505
	 * 分类MlbackCoupon列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackCouponByPage")
	@ResponseBody
	public Msg getMlbackCouponWithJson(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn
			,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackCoupon> mlbackCouponList = mlbackCouponService.selectMlbackCouponGetAll();
			PageInfo page = new PageInfo(mlbackCouponList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	
	/**3.0	useOn	0505
	 * MlbackCoupon	insert
	 * @param MlbackCoupon
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackCoupon mlbackCoupon){
		//接受参数信息
		System.out.println("mlbackCoupon:"+mlbackCoupon);
		//取出id
		System.out.println(1);
		Integer couponId = mlbackCoupon.getCouponId();
		String nowTime = DateUtil.strTime14s();
		mlbackCoupon.setCouponMotifytime(nowTime);
		if(couponId==null){
			//无id，insert
			mlbackCoupon.setCouponCreatetime(nowTime);
			int intResult = mlbackCouponService.insertSelective(mlbackCoupon);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			int intResult = mlbackCouponService.updateByPrimaryKeySelective(mlbackCoupon);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "更新成功");
			
		}		
	}
	
	/**4.0	useOn	0505
	 * MlbackCategory	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackCoupon mlbackCoupon){
		//接收id信息
		int couponIdInt = mlbackCoupon.getCouponId();
		int intResult = mlbackCouponService.deleteByPrimaryKey(couponIdInt);
		System.out.println(intResult);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	useOn	0505
	 * 查看单条类目的详情细节
	 * @param MlbackCoupon
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackCouponDetailById",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackCouponDetailById(@RequestParam(value = "couponId") Integer couponId){
		
		//接受信息
		MlbackCoupon mlbackCouponReq = new MlbackCoupon();
		mlbackCouponReq.setCouponId(couponId);
		List<MlbackCoupon> mlbackCouponResList =mlbackCouponService.selectMlbackCoupon(mlbackCouponReq);
		MlbackCoupon mlbackCouponOne =mlbackCouponResList.get(0);
		return Msg.success().add("resMsg", "查看单条优惠券的详情细节完毕")
					.add("mlbackCouponOne", mlbackCouponOne);
	}
	
	/**
	 * 6.0	useOn	0505
	 * 查看单条类目的详情细节
	 * @param MlbackCoupon
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackCouponDetailByCode",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackCouponDetailByCode(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackCoupon mlbackCoupon){
		
		
		String couponCode = mlbackCoupon.getCouponCode();
		//接受信息
		MlbackCoupon mlbackCouponReq = new MlbackCoupon();
		mlbackCouponReq.setCouponCode(couponCode);
		List<MlbackCoupon> mlbackCouponResList =mlbackCouponService.selectMlbackCouponBYCode(mlbackCouponReq);
		MlbackCoupon mlbackCouponOne = null;
		if(mlbackCouponResList.size()>0){
			mlbackCouponOne =mlbackCouponResList.get(0);
		}
		
		return Msg.success().add("resMsg", "查看单条优惠券的详情细节完毕")
					.add("mlbackCouponOne", mlbackCouponOne);
	}
	
	/**
	 * 7.0	useOn	0505
	 * 查询该用户名下所有优惠券
	 * @param MlbackCoupon
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackCouponDetailByUId",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackCouponDetailByUId(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		Integer userType= 0;
		MlfrontUser mlfrontUserLogin  = (MlfrontUser) session.getAttribute("loginUser");
		List<MlbackCoupon> mlbackCouponReturnList = new ArrayList<MlbackCoupon>();
		if(mlfrontUserLogin==null){
			//非登录状态
			userType =0;
			return Msg.success().add("resMsg", "查询该用户名下所有优惠券完毕").add("userType", userType);
		}else{
			//登录状态
			userType =1;
			MlfrontUser mlfrontUserreq =new MlfrontUser();
			String userEmail = mlfrontUserLogin.getUserEmail();
			mlfrontUserreq.setUserEmail(userEmail);
			List<MlfrontUser> mlfrontUserList= mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserreq);
			if(mlfrontUserList.size()>0){
				//此账号邮箱已经注册，返回即可
				//0的话取出resMsg
				MlfrontUser mlfrontUserres =mlfrontUserList.get(0);
				String couponidstr = mlfrontUserres.getUserCouponidstr();
				
				String couponidstrArr[] = couponidstr.split(",");
				String couponIdStr =""; 
				Integer couponIdInt =0;
				
				for(int i=0;i<couponidstrArr.length;i++){
					couponIdStr = couponidstrArr[i];
					couponIdInt = Integer.parseInt(couponIdStr);
					//拿到couponId，封装参数，查回本条优惠券的详情
					MlbackCoupon mlbackCouponReq = new MlbackCoupon();
					mlbackCouponReq.setCouponId(couponIdInt);
					List<MlbackCoupon> mlbackCouponResList =mlbackCouponService.selectMlbackCoupon(mlbackCouponReq);
					MlbackCoupon mlbackCouponOne =mlbackCouponResList.get(0);
					mlbackCouponReturnList.add(mlbackCouponOne);
				}
				return Msg.success().add("resMsg", "查询该用户名下所有优惠券完毕").add("mlbackCouponReturnList", mlbackCouponReturnList).add("userType", userType);
			}else{
				userType =0;
				return Msg.success().add("resMsg", "查询该用户名下所有优惠券完毕").add("userType", userType);
			}
		}
	}
	
	/**
	 * 8.0	useOn	0505
	 * 某页面展示的优惠券列表查询
	 * @param MlbackCoupon
	 * @return 
	 */
	@RequestMapping(value="/getMlbackCouponShowByAreaNum",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackCouponShowByAreaNum(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackCoupon mlbackCoupon){
		
		Integer couponAreaNum = mlbackCoupon.getCouponAreaNum();
		//接受信息
		MlbackCoupon mlbackCouponReq = new MlbackCoupon();
		mlbackCouponReq.setCouponAreaNum(couponAreaNum);
		List<MlbackCoupon> mlbackCouponResList =mlbackCouponService.selectMlbackCouponBYAreaNum(mlbackCouponReq);
		
		return Msg.success().add("resMsg", "某页面展示的优惠券列表查询成功").add("mlbackCouponResList", mlbackCouponResList);
	}
	
}
