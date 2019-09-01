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

import com.atguigu.bean.MlbackActShowPro;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackActShowProService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.utils.DateUtil;


@Controller
@RequestMapping("/MlbackActShowPro")
public class MlbackActShowProController {
	
	
	@Autowired
	MlbackActShowProService mlbackActShowProService;
		
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	UseNow	0505
	 * toMlbackActShowPro列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackActShowProPage")
	public String tologin() throws Exception{
	
		return "back/mlbackActShowProPage";
	}
	
	/**2.0	UseNow	0505
	 * 分类MlbackActShowPro列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackActShowProByPage")
	@ResponseBody
	public Msg getMlbackActShowProByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackActShowPro> mlbackActShowProList = mlbackActShowProService.selectMlbackActShowProGetAll();
			PageInfo page = new PageInfo(mlbackActShowProList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	
	/**3.0	UseNow	0505
	 * MlbackActShowPro	insert
	 * @param MlbackActShowPro
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackActShowPro mlbackActShowPro){
		//接受参数信息
		//获取类名
		Integer actshowproId = mlbackActShowPro.getActshowproId();
		Integer proId = mlbackActShowPro.getActshowproProid();
		
		MlbackProduct mlbackProductReq = new MlbackProduct();
		MlbackProduct mlbackProductRes = new MlbackProduct();
		mlbackProductReq.setProductId(proId);
		List<MlbackProduct> mlbackProductResList = mlbackProductService.selectMlbackProduct(mlbackProductReq);
		mlbackProductRes = mlbackProductResList.get(0);
		String Pname = mlbackProductRes.getProductName();
		String Pseoname = mlbackProductRes.getProductSeo();
		
		//mlbackProductService;
		String nowtime = DateUtil.strTime14s();
		mlbackActShowPro.setActshowproMotifytime(nowtime);
		mlbackActShowPro.setActshowproProname(Pname);//Pseoname
		mlbackActShowPro.setActshowproSeoname(Pseoname);
		if(actshowproId==null){
			mlbackActShowPro.setActshowproCreatetime(nowtime);
			//无id，insert
			System.out.println(3);
			int intResult = mlbackActShowProService.insertSelective(mlbackActShowPro);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			System.out.println(4);
			int intResult = mlbackActShowProService.updateByPrimaryKeySelective(mlbackActShowPro);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**4.0	UseNow	0505
	 * MlbackActShowPro	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackActShowPro mlbackActShowPro){
		//接收id信息
		Integer actshowproId = mlbackActShowPro.getActshowproId();
		int intResult = mlbackActShowProService.deleteByPrimaryKey(actshowproId);
		System.out.println(intResult);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	UseNow	0505
	 * 查看单条类目的详情细节
	 * @param MlbackActShowPro
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackActShowProDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackActShowProDetail(@RequestParam(value = "actshowproId") Integer actshowproId){
		//接受actshowproId
		MlbackActShowPro mlbackActShowProReq = new MlbackActShowPro();
		mlbackActShowProReq.setActshowproId(actshowproId);
		//查询本条
		MlbackActShowPro mlbackActShowProOne =mlbackActShowProService.selectMlbackActShowProById(mlbackActShowProReq);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlbackActShowProOne", mlbackActShowProOne);
	}
	
	/**
	 * 5.0	UseNow	0505
	 * 查看首页活动品区域的顺序
	 * @param MlbackActShowPro
	 * @return 
	 */
	@RequestMapping(value="/getMlbackActShowProListByActnum",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackActShowProListByActnum(@RequestParam(value = "actshowproActnum") Integer actshowproActnum){
		//接受actshowproId
		MlbackActShowPro mlbackActShowProReq = new MlbackActShowPro();
		mlbackActShowProReq.setActshowproStatus(1);
		mlbackActShowProReq.setActshowproActnum(actshowproActnum);
		//查询本条
		List<MlbackActShowPro> mlbackActShowProList =mlbackActShowProService.selectMlbackActShowProByActnum(mlbackActShowProReq);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlbackActShowProList", mlbackActShowProList);
	}

}
