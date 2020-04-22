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

import com.atguigu.bean.MlbackAbandonPurchase;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAbandonPurchaseService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/MlbackAbandonPurchase")
public class MlbackAbandonPurchaseController {
	
	@Autowired
	MlbackAbandonPurchaseService mlbackAbandonPurchaseService;
		
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	/**
	 * 1.0	onuse	200104
	 * toMlbackAbandonPurchase列表页面
	 * @param
	 * @return 
	 * */
	@RequestMapping("/toMlbackAbandonPurchasePage")
	public String tologin() throws Exception{
	
		return "back/mlbackAbandonPurchasePage";
	}
	
	/**2.0	onuse	200104
	 * 分类MlbackAbandonPurchase列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackAbandonPurchaseByPage")
	@ResponseBody
	public Msg getMlbackActShowProByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackAbandonPurchase> mlbackAbandonPurchaseList = mlbackAbandonPurchaseService.selectMlbackAbandonPurchaseGetAll();
			System.out.println("mlbackAbandonPurchaseList.size:"+mlbackAbandonPurchaseList.size());
			PageInfo page = new PageInfo(mlbackAbandonPurchaseList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	
	/**3.0	onuse	200104
	 * MlbackAbandonPurchase	save
	 * @param MlbackAbandonPurchase
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackAbandonPurchase mlbackAbandonPurchase){
		//接受参数信息
		Integer abandonpurchaseId = mlbackAbandonPurchase.getAbandonpurchaseId();
		
		//mlbackAbandonPurchase;
		String nowtime = DateUtil.strTime14s();
		mlbackAbandonPurchase.setAbandonpurchaseMotifytime(nowtime);
		
		if(abandonpurchaseId==null){
			mlbackAbandonPurchase.setAbandonpurchaseCreatetime(nowtime);
			//无id，insert
			mlbackAbandonPurchaseService.insertSelective(mlbackAbandonPurchase);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			mlbackAbandonPurchaseService.updateByPrimaryKeySelective(mlbackAbandonPurchase);
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**4.0	UseNow	190905
	 * MlbackAbandonPurchase	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackAbandonPurchase mlbackAbandonPurchase){
		//接收id信息
		Integer abandonpurchaseId = mlbackAbandonPurchase.getAbandonpurchaseId();
		mlbackAbandonPurchaseService.deleteByPrimaryKey(abandonpurchaseId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	UseNow	190905
	 * 查看单条的详情细节
	 * @param MlbackAbandonPurchase
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackAbandonPurchaseDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackAbandonPurchaseDetail(@RequestParam(value = "abandonpurchaseId") Integer abandonpurchaseId){
		//接受actshowproId
		MlbackAbandonPurchase mlbackAbandonPurchaseReq = new MlbackAbandonPurchase();
		mlbackAbandonPurchaseReq.setAbandonpurchaseId(abandonpurchaseId);
		//查询本条
		List<MlbackAbandonPurchase> mlbackAbandonPurchaseList =mlbackAbandonPurchaseService.selectMlbackAbandonPurchaseById(mlbackAbandonPurchaseReq);
		MlbackAbandonPurchase mlbackAbandonPurchaseOne = new MlbackAbandonPurchase();
		mlbackAbandonPurchaseOne = mlbackAbandonPurchaseList.get(0);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlbackAbandonPurchaseOne", mlbackAbandonPurchaseOne);
	}
	
	/**
	 * 6.0	UseNow	190905
	 * 查询wap不同位置的图片
	 * @param MlbackAbandonPurchase
	 * @return 
	 */
	@RequestMapping(value="/getMlbackAbandonPurchaseByStatus",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackAbandonPurchaseByStatus(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackAbandonPurchase mlbackAbandonPurchase){
		//接受slideArea
		
		MlbackAbandonPurchase mlbackAbandonPurchaseReq = new MlbackAbandonPurchase();
		mlbackAbandonPurchaseReq.setAbandonpurchaseStatus(1);
		//查询本条
		List<MlbackAbandonPurchase> mlbackAbandonPurchaseList =mlbackAbandonPurchaseService.selectMlbackAbandonPurchaseByStatus(mlbackAbandonPurchase);
		
		return Msg.success().add("resMsg", "查看该位置的轮播完毕")
					.add("mlbackAbandonPurchaseList", mlbackAbandonPurchaseList);
	}
	
}
