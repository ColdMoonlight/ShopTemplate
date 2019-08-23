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
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlfrontCart;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.Msg;
import com.atguigu.bean.PageTimeVo;
import com.atguigu.bean.SysUser;
import com.atguigu.bean.UserWork;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.GroupDisplayService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlfrontUserService;
import com.atguigu.service.SysUserService;
import com.atguigu.service.UserWorkService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.EmailUtil;
import com.atguigu.utils.EmailUtils;
import com.atguigu.utils.ExcelUtils;
import com.atguigu.utils.HttpUtil;


@Controller
@RequestMapping("/MlfrontUser")
public class MlfrontUserController {
		
	@Autowired
	MlfrontUserService mlfrontUserService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**1.0	UseNow	0515
	 * ifSession	初始化验证是否登录
	 * @param ifSession
	 */
	@RequestMapping(value="/ifLogin",method=RequestMethod.POST)
	@ResponseBody
	public Msg ifLogin(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		//获取session中是否已经登陆
		MlfrontUser loginUser =  (MlfrontUser) session.getAttribute("loginUser");
		Integer ifLogin = 0;
		if(loginUser==null){
			ifLogin = 0;//无登录状态
		}else{
			ifLogin = 1;//有登录状态
		}
		return Msg.success().add("resMsg", "初始化验证是否登录").add("ifLogin", ifLogin);
	}
	
	/**2.0.1	UseNow	0515
	 * ifSession	wap去登录/注册页面
	 * @param ifSession
	 */
	@RequestMapping(value="/toLoginRegisterPage")
	public String toLoginRegisterPage() throws Exception{
		
		return "mfront/loginRegister";
	}
	/**2.0.2	UseNow	0515
	 * ifSession	PC去登录/注册页面
	 * @param ifSession
	 */
	@RequestMapping(value="/toPcLoginRegisterPage")
	public String toPcLoginRegisterPage() throws Exception{
		
		return "front/pcloginRegister";
	}
	
	/**2.1.1	UseNow	0515
	 * ifSession	wap重置密码
	 * @param ifSession
	 */
	@RequestMapping(value="/tomForgetPassWord")
	public String tomForgetPassWord() throws Exception{
		
		return "mfront/setpassword";
	}
	
	/**2.2.2	UseNow	0515
	 * ifSession	pc重置密码
	 * @param ifSession
	 */
	@RequestMapping(value="/topcForgetPassWord")
	public String topcForgetPassWord() throws Exception{
		
		return "front/pcsetpassword";
	}
	
	/**2.3	useOn	0505
	 * MlfrontUser	update
	 * @param id MlbackProduct
	 * @return
	 */
	@RequestMapping(value="/reSetPwd",method=RequestMethod.POST)
	@ResponseBody
	public Msg reSetPwd(@RequestBody MlfrontUser mlfrontUser){
		//接受信息
		String nowtime = DateUtil.strTime14s();
		mlfrontUser.setUserMotifytime(nowtime);
		//更新本条状态
		int intResult = mlfrontUserService.updatePwdByEmail(mlfrontUser);
		System.out.println(intResult);
		System.out.println(mlfrontUser);
		return Msg.success().add("resMsg", "更新成功").add("mlfrontUser", mlfrontUser);
	}
	
	/**3.0	UseNow	0515
	 * MlfrontUser	登录login
	 * @param MlfrontUser
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Msg frontUserLogin(HttpServletResponse rep,HttpServletRequest res,HttpSession session ,@RequestBody MlfrontUser mlfrontUser){
		//接受账号密码
		String userEmail = mlfrontUser.getUserEmail();
		String userPassword = mlfrontUser.getUserPassword();
		//查询拿着账号去查询
		MlfrontUser mlfrontUserreq = new MlfrontUser();
		mlfrontUserreq.setUserEmail(userEmail);
		Integer loginYes = 0;
		
		List<MlfrontUser> mlfrontUserList= mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserreq);
		if(mlfrontUserList.size()>0){
			//此账号邮箱存在，返回即可
			MlfrontUser mlfrontUserres = mlfrontUserList.get(0);
			String alreadyPwd= mlfrontUserres.getUserPassword();
			if(alreadyPwd.equals(userPassword)){
				System.out.println("密码正确,登陆成功");
				session.setAttribute("loginUser", mlfrontUserres);
				loginYes = 1;
				return Msg.success().add("resMsg", "Successfully").add("loginYes", loginYes);
			}else{
				System.out.println("密码错误,登陆失败");
				return Msg.success().add("resMsg", "PassWord Error").add("loginYes", loginYes);
			}
		}else{
			return Msg.success().add("resMsg", "Account does not exist").add("loginYes", loginYes);
		}
	}
	
	/**4.0	UseNow	0608
	 * MlfrontUser	注册register
	 * @param MlfrontUser
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public Msg frontUserRegister(HttpServletResponse rep,HttpServletRequest res,HttpSession session ,@RequestBody MlfrontUser mlfrontUser){
		//接受账号密码
		String userEmail = mlfrontUser.getUserEmail();
		String userPassword = mlfrontUser.getUserPassword();
		//查询拿着账号去查询
		MlfrontUser mlfrontUserreq = new MlfrontUser();
		mlfrontUserreq.setUserEmail(userEmail);
		Integer registerYes = 0;
		List<MlfrontUser> mlfrontUserList= mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserreq);
		if(mlfrontUserList.size()>0){
			//此账号邮箱已经注册，返回即可
			return Msg.success().add("resMsg", "Registration failed, the email has been registered").add("registerYes", registerYes);//0的话取出resMsg
		}else{
			//此账号邮箱未注册，继续封装参数,插入即可
			mlfrontUserreq.setUserPassword(userPassword);
			//给user的Coupon字段加入优惠券的idStr
			mlfrontUserreq.setUserCouponidstr("1,2,3");
			int intResult = mlfrontUserService.insertSelective(mlfrontUserreq);
			System.out.println(intResult);
			//用账号把它查回来
			MlfrontUser mlfrontUserInAfterreq = new MlfrontUser();
			mlfrontUserInAfterreq.setUserEmail(userEmail);
			List<MlfrontUser> mlfrontUserInafterList= mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserInAfterreq);
			MlfrontUser mlfrontUserafterIn =mlfrontUserInafterList.get(0);
			session.setAttribute("loginUser", mlfrontUserafterIn);
			registerYes = 1;
			try {
				//测试方法
				String getToEmail = userEmail;
				String Message = "Welcome to Register In Megalook.";
				EmailUtils.readyEmailRegister(getToEmail, Message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Msg.success().add("resMsg", "Registration Success").add("registerYes", registerYes);//1的话,成功
		}
	}
	
	/**5.0	UseNow	0608
	 * MlfrontUser	注销exit
	 * @param MlfrontUser
	 */
	@RequestMapping(value="/exit",method=RequestMethod.POST)
	@ResponseBody
	public Msg mfrontUserexit(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		//接受账号密码
		Integer exitInt= 0;
		MlfrontUser mlfrontUserLogin  = (MlfrontUser) session.getAttribute("loginUser");
		if(mlfrontUserLogin==null){
			exitInt = 0; 
		}else{
			session.removeAttribute("loginUser");
			exitInt = 0; 
		}
		return Msg.success().add("resMsg", "Sign Out").add("exitInt", exitInt);//1的话,成功
	}
	
	/**6.0	UseNow	0515
	 * ifSession	去个人中心
	 * @param ifSession
	 */
	@RequestMapping(value="/toUserCenter")
	public String toUserCenter(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		
		return "mfront/userCenter";
	}
	
	/**6.1	UseNow	0515
	 * ifSession	去个人中心
	 * @param ifSession
	 */
	@RequestMapping(value="/topcUserCenter")
	public String topcUserCenter(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		
		return "front/pcuserCenter";
	}
	
	/**
	 * 7.0	UseNow	0505
	 * to	MlfrontUser列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/getMlfrontUserByDate",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlfrontUserByDate(HttpServletResponse rep,HttpServletRequest res,HttpSession session ,@RequestBody PageTimeVo pageTime){
		
		Integer pn = pageTime.getPn();
		if(pn==null){
			pn=1;
		}
		String beginTime = pageTime.getBeginTime();
		String endTime = pageTime.getEndTime();
		
		MlfrontUser mlfrontUserReq = new MlfrontUser();
		mlfrontUserReq.setUserCreatetime(beginTime);
		mlfrontUserReq.setUserMotifytime(endTime);
		
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
		int PagNum = 20;
		PageHelper.startPage(pn, PagNum);
		List<MlfrontUser> mlfrontUserList = mlfrontUserService.selectMlfrontUserByDate(mlfrontUserReq);
		Integer countNumber = mlfrontUserList.size();
		PageInfo page = new PageInfo(mlfrontUserList, PagNum);
		return Msg.success().add("pageInfo", page).add("countNumber", countNumber).add("resMsg", "注册用户查询成功");
	}
	
	/**
	 * 8.0	useOn	0505
	 * to	MlfrontUser列表页面
	 * @param jsp
	 * @return  
	 * */
	@RequestMapping("/toMlbackUserPage")
	public String toMlfrontUserPage() throws Exception{
	
		return "back/mlbackUserPage";
	}
	
	/**9.0	UseNow	0505
	 * 分类MlfrontUser列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlfrontUserByPage")
	@ResponseBody
	public Msg getMlfrontUserWithJson(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn
			,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlfrontUser> mlfrontUserList = mlfrontUserService.selectMlfrontUserGetAll();
			PageInfo page = new PageInfo(mlfrontUserList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	
	/**10.0	useOn	0505
	 * MlfrontUser	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlfrontUser mlfrontUser){
		//接收id信息
		Integer UserIdInt = mlfrontUser.getUserId();
		int intResult = mlfrontUserService.deleteByPrimaryKey(UserIdInt);
		System.out.println(intResult);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**11.0	useOn	0505
	 * MlfrontUser	update
	 * @param id MlbackProduct
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Msg update(@RequestBody MlfrontUser mlfrontUser){
		//接受信息
		String nowtime = DateUtil.strTime14s();
		mlfrontUser.setUserMotifytime(nowtime);
		//更新本条状态
		int intResult = mlfrontUserService.updateByPrimaryKeySelective(mlfrontUser);
		System.out.println(intResult);
		System.out.println(mlfrontUser);
		return Msg.success().add("resMsg", "更新成功").add("mlfrontUser", mlfrontUser);
	}
	
	/**
	 * 12.0	useOn	0505
	 * 查看单条MlfrontUser的详情细节
	 * @param MlfrontUser
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontUserDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontUserDetail(@RequestParam(value = "userId") Integer userId){
		
		//接受信息
		MlfrontUser mlfrontUserReq = new MlfrontUser();
		mlfrontUserReq.setUserId(userId);
		List<MlfrontUser> mlfrontUserResList =mlfrontUserService.selectMlfrontUser(mlfrontUserReq);
		MlfrontUser mlfrontUserOne =mlfrontUserResList.get(0);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlfrontUserOne", mlfrontUserOne);
	}	
	
	/**
	 * 13.0	useOn	0505
	 * to	toPersonInfoPage列表页面
	 * @param jsp
	 * @return  
	 * */
	@RequestMapping("/toPersonInfoPage")
	public String toPersonInfoPage() throws Exception{
	
		return "mfront/PersonInfo";
	}
	/**
	 * 13.1	useOn	0505
	 * to	toPersonInfoPage列表页面
	 * @param jsp
	 * @return  
	 * */
	@RequestMapping("/topcPersonInfoPage")
	public String topcPersonInfoPage() throws Exception{
	
		return "front/pcPersonInfo";
	}
	
	
	/**
	 * 14.0	useOn	0505
	 * 查看单条MlfrontUser的详情细节
	 * @param MlfrontUser
	 * @return 
	 */
	@RequestMapping(value="/getLoginUserDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getLoginUserDetail(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		//获取session中是否已经登陆
		MlfrontUser loginUser =  (MlfrontUser) session.getAttribute("loginUser");
		
		Integer userId = loginUser.getUserId();
		//接受信息
		MlfrontUser mlfrontUserReq = new MlfrontUser();
		mlfrontUserReq.setUserId(userId);
		List<MlfrontUser> mlfrontUserResList =mlfrontUserService.selectMlfrontUser(mlfrontUserReq);
		MlfrontUser mlfrontUserOne =mlfrontUserResList.get(0);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlfrontUserOne", mlfrontUserOne);
	}	
	
	/**
	 * 15.0	useOn	0505
	 * to	tomCouponPage列表页面
	 * @param jsp
	 * @return  
	 * */
	@RequestMapping("/tomCouponPage")
	public String tomCouponPage() throws Exception{
	
		return "mfront/myCoupon";
	}
	/**
	 * 15.1	useOn	0505
	 * to	topcCouponPage列表页面
	 * @param jsp
	 * @return  
	 * */
	@RequestMapping("/topcCouponPage")
	public String topcCouponPage() throws Exception{
	
		return "front/pcmyCoupon";
	}
	
	/**
	 * 16.0	useOn	0505
	 * to	tommyOrderPage列表页面
	 * @param jsp
	 * @return  
	 * */
	@RequestMapping("/tommyOrderPage")
	public String tommyOrderPage() throws Exception{
	
		return "mfront/myOrderList";
	}
	/**
	 * 16.1	useOn	0505
	 * to	tommyOrderPage列表页面
	 * @param jsp
	 * @return  
	 * */
	@RequestMapping("/topcmyOrderPage")
	public String topcmyOrderPage() throws Exception{
	
		return "front/pcmyOrderList";
	}
}
