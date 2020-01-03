package com.atguigu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.Msg;
import com.atguigu.service.MlbackAdminService;


@Controller
@RequestMapping("/MlbackAdmin")
public class MlbackAdminController {
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping("/toindex")
	public String toindex() throws Exception{
		System.out.println("进入toindex");
		System.out.println("toindex");
	
		return "back/mlbackAdminLogin";
	}
	
	/**
	 * 	onuse	20200103	检查
	 * 帐号注销
	 * */
	@RequestMapping("/exitIndex")
	public String exitindex(HttpSession session) throws Exception{
		session.removeAttribute("AdminUser");
		session.invalidate();
		return "sysLogin";
	}
	
	/**
	 * 	onuse	20200103	检查
	 * 登陆֤
	 * */
	@RequestMapping(value="/toCheakAdminUser",method=RequestMethod.POST)
	@ResponseBody
	public Msg checkuser(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestBody MlbackAdmin MlbackAdminReq){
		//接收参数信息 
		MlbackAdmin mlbackAdminGet = new MlbackAdmin();
		mlbackAdminGet.setAdminAccname(MlbackAdminReq.getAdminAccname());
		List<MlbackAdmin> mlbackAdminGetresList = mlbackAdminService.selectMlbackAdmin(mlbackAdminGet);
		if(!(mlbackAdminGetresList.size()>0)){
			return Msg.fail().add("resMsg", "账号不存在");
		}
		mlbackAdminGet.setAdminPassword(MlbackAdminReq.getAdminPassword());
		List<MlbackAdmin> MlbackAdminListNameAndPwd = mlbackAdminService.selectMlbackAdmin(mlbackAdminGet);
		if(MlbackAdminListNameAndPwd.size()>0){
			//将登陆状态放入session对象
			session.setAttribute("AdminUser", mlbackAdminGet);
			return Msg.success().add("resMsg", "登陆成功");
		}else{
			return Msg.fail().add("resMsg", "密码错误登录失败");
		}
	}
	
}
