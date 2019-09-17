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

import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackSlide;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlbackSlideService;
import com.atguigu.utils.DateUtil;


@Controller
@RequestMapping("/MlbackSlide")
public class MlbackSlideController {
	
	@Autowired
	MlbackSlideService mlbackSlideService;
		
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
	@RequestMapping("/toMlbackSlidePage")
	public String tologin() throws Exception{
	
		return "back/mlbackSlidePage";
	}
	
	/**2.0	UseNow	0505
	 * 分类MlbackActShowPro列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackSlideByPage")
	@ResponseBody
	public Msg getMlbackActShowProByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackSlide> mlbackSlideList = mlbackSlideService.selectMlbackSlideGetAll();
			System.out.println("mlbackSlideList.size:"+mlbackSlideList.size());
			PageInfo page = new PageInfo(mlbackSlideList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	
	/**3.0	UseNow	0505
	 * MlbackActShowPro	insert
	 * @param MlbackActShowPro
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackSlide mlbackSlide){
		//接受参数信息
		//获取类名
		Integer slideId = mlbackSlide.getSlideId();
		Integer proId = mlbackSlide.getSlideProid();
		
		MlbackProduct mlbackProductReq = new MlbackProduct();
		MlbackProduct mlbackProductRes = new MlbackProduct();
		mlbackProductReq.setProductId(proId);
		List<MlbackProduct> mlbackProductResList = mlbackProductService.selectMlbackProduct(mlbackProductReq);
		mlbackProductRes = mlbackProductResList.get(0);
		String Pname = mlbackProductRes.getProductName();
		String Pseoname = mlbackProductRes.getProductSeo();
		
		//mlbackProductService;
		String nowtime = DateUtil.strTime14s();
		mlbackSlide.setSlideMotifytime(nowtime);
		mlbackSlide.setSlideProname(Pname);//Pseoname
		mlbackSlide.setSlideSeoname(Pseoname);
		if(slideId==null){
			mlbackSlide.setSlideCreatetime(nowtime);
			//无id，insert
			System.out.println(3);
			int intResult = mlbackSlideService.insertSelective(mlbackSlide);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			System.out.println(4);
			int intResult = mlbackSlideService.updateByPrimaryKeySelective(mlbackSlide);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**4.0	UseNow	0906
	 * MlbackSlide	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackSlide mlbackSlide){
		//接收id信息
		Integer slideId = mlbackSlide.getSlideId();
		int intResult = mlbackSlideService.deleteByPrimaryKey(slideId);
		System.out.println(intResult);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	UseNow	190905
	 * 查看单条的详情细节
	 * @param MlbackSlide
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackSlideDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackSlideDetail(@RequestParam(value = "slideId") Integer slideId){
		//接受actshowproId
		MlbackSlide mlbackSlideReq = new MlbackSlide();
		mlbackSlideReq.setSlideId(slideId);
		//查询本条
		MlbackSlide mlbackSlideOne =mlbackSlideService.selectMlbackSlideById(mlbackSlideReq);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlbackSlideOne", mlbackSlideOne);
	}
	
	/**
	 * 6.0	UseNow	0505
	 * 查询wap不同位置的图片
	 * @param MlbackSlide
	 * @return 
	 */
	@RequestMapping(value="/getMlbackSlidewapListByArea",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackSlidewapListByArea(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackSlide mlbackSlide){
		//接受slideArea
		
		Integer slideArea = mlbackSlide.getSlideArea();
		
		MlbackSlide mlbackSlideReq = new MlbackSlide();
		mlbackSlideReq.setSlideArea(slideArea);
		mlbackSlideReq.setSlideWapstatus(1);
		//查询本条
		List<MlbackSlide> mlbackSlideList =mlbackSlideService.selectMlbackSlidewapListByArea(mlbackSlideReq);
		return Msg.success().add("resMsg", "查看该位置的轮播完毕")
					.add("mlbackSlideList", mlbackSlideList);
	}
	
	/**
	 * 7.0	UseNow	0505
	 * 查询pc不同位置的图片
	 * @param MlbackSlide
	 * @return 
	 */
	@RequestMapping(value="/getMlbackSlidepcListByArea",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackSlidepcListByArea(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackSlide mlbackSlide){
		//接受slideArea
		Integer slideArea = mlbackSlide.getSlideArea();
		MlbackSlide mlbackSlideReq = new MlbackSlide();
		mlbackSlideReq.setSlideArea(slideArea);
		mlbackSlideReq.setSlidePcstatus(1);
		//查询本条
		List<MlbackSlide> mlbackSlideList =mlbackSlideService.selectMlbackSlidepcListByArea(mlbackSlideReq);
		return Msg.success().add("resMsg", "查看该位置的轮播完毕")
					.add("mlbackSlideList", mlbackSlideList);
	}

}
