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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.MlbackOrderStateEmail;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackOrderStateEmailService;
import com.atguigu.service.MlbackAdminService;

@Controller
@RequestMapping("/MlbackOrderStateEmail")
public class MlbackOrderStateEmailController {
	
	@Autowired
	MlbackOrderStateEmailService mlbackOrderStateEmailService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	onuse	200104
	 * toMlbackOrderStateEmail列表页面
	 * @param
	 * @return 
	 * */
	@RequestMapping("/toMlbackOrderStateEmailPage")
	public String tologin() throws Exception{
	
		return "back/mlbackOrderStateEmailPage";
	}
	
	/**2.0	onuse	200104
	 * 分类MlbackOrderStateEmail列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackOrderStateEmailByPage")
	@ResponseBody
	public Msg getMlbackActShowProByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackOrderStateEmail> mlbackOrderStateEmailList = mlbackOrderStateEmailService.selectMlbackOrderStateEmailGetAll();
			System.out.println("MlbackOrderStateEmailList.size:"+mlbackOrderStateEmailList.size());
			PageInfo page = new PageInfo(mlbackOrderStateEmailList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	
	/**3.0	onuse	200104
	 * MlbackOrderStateEmail	save
	 * @param MlbackOrderStateEmail
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackOrderStateEmail mlbackOrderStateEmail){
		//接受参数信息
		Integer orderStateEmailId = mlbackOrderStateEmail.getOrderstateemailId();
		
		if(orderStateEmailId==null){
			//无id，insert
			mlbackOrderStateEmailService.insertSelective(mlbackOrderStateEmail);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			mlbackOrderStateEmailService.updateByPrimaryKeySelective(mlbackOrderStateEmail);
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**4.0	UseNow	190905
	 * MlbackOrderStateEmail	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackOrderStateEmail mlbackOrderStateEmail){
		//接收id信息
		Integer orderStateEmailId = mlbackOrderStateEmail.getOrderstateemailId();
		mlbackOrderStateEmailService.deleteByPrimaryKey(orderStateEmailId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	UseNow	190905
	 * 查看单条的详情细节
	 * @param MlbackOrderStateEmail
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackOrderStateEmailDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackOrderStateEmailDetail(@RequestParam(value = "orderStateEmailId") Integer orderStateEmailId){
		//接受actshowproId
		MlbackOrderStateEmail mlbackOrderStateEmailReq = new MlbackOrderStateEmail();
		mlbackOrderStateEmailReq.setOrderstateemailId(orderStateEmailId);
		//查询本条
		List<MlbackOrderStateEmail> MlbackOrderStateEmailList =mlbackOrderStateEmailService.selectMlbackOrderStateEmailById(mlbackOrderStateEmailReq);
		MlbackOrderStateEmail MlbackOrderStateEmailOne = new MlbackOrderStateEmail();
		MlbackOrderStateEmailOne = MlbackOrderStateEmailList.get(0);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlbackOrderStateEmailOne", MlbackOrderStateEmailOne);
	}
	
	
}
