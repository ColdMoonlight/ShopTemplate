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
import com.atguigu.bean.MlbackVideoShowArea;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlbackVideoShowAreaService;
import com.atguigu.utils.DateUtil;


@Controller
@RequestMapping("/MlbackVideoShowArea")
public class MlbackVideoShowAreaController {
	
	@Autowired
	MlbackVideoShowAreaService mlbackVideoShowAreaService;
		
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	/**
	 * 1.0	onuse	200104
	 * MlbackVideoShowArea列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackVideoShowAreaPage")
	public String tologin() throws Exception{
	
		return "back/mlbackVideoShowAreaPage";
	}
	
	/**2.0	onuse	200104
	 * 分类MlbackVideoShowArea列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackVideoShowAreaByPage")
	@ResponseBody
	public Msg getMlbackActShowProByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackVideoShowArea> mlbackVideoShowAreaList = mlbackVideoShowAreaService.selectMlbackVideoShowAreaGetAll();
			System.out.println("mlbackVideoShowAreaList.size:"+mlbackVideoShowAreaList.size());
			PageInfo page = new PageInfo(mlbackVideoShowAreaList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	
	/**3.0	onuse	200104
	 * MlbackVideoShowArea	save
	 * @param MlbackVideoShowArea
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackVideoShowArea mlbackVideoShowArea){
		//接受参数信息
		//获取类名
		Integer videoshowareaId = mlbackVideoShowArea.getVideoshowareaId();

		
		//mlbackProductService;
		String nowtime = DateUtil.strTime14s();
		mlbackVideoShowArea.setVideoshowareaMotifytime(nowtime);
		
		if(videoshowareaId==null){
			mlbackVideoShowArea.setVideoshowareaCreatetime(nowtime);
			//无id，insert
			mlbackVideoShowAreaService.insertSelective(mlbackVideoShowArea);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			mlbackVideoShowAreaService.updateByPrimaryKeySelective(mlbackVideoShowArea);
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**4.0	UseNow	190905
	 * MlbackVideoShowArea	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackVideoShowArea mlbackVideoShowArea){
		//接收id信息
		Integer videoshowareaId = mlbackVideoShowArea.getVideoshowareaId();
		mlbackVideoShowAreaService.deleteByPrimaryKey(videoshowareaId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	UseNow	190905
	 * 查看单条的详情细节
	 * @param MlbackVideoShowArea
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackVideoShowAreaDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackVideoShowAreaDetail(@RequestParam(value = "videoshowareaId") Integer videoshowareaId){
		//接受actshowproId
		MlbackVideoShowArea mlbackVideoShowAreaReq = new MlbackVideoShowArea();
		mlbackVideoShowAreaReq.setVideoshowareaId(videoshowareaId);
		//查询本条
		MlbackVideoShowArea mlbackVideoShowAreaOne =mlbackVideoShowAreaService.selectMlbackVideoShowAreaById(mlbackVideoShowAreaReq);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlbackVideoShowAreaOne", mlbackVideoShowAreaOne);
	}
	
//	/**
//	 * 6.0	UseNow	190905
//	 * 查询wap不同位置的图片
//	 * @param mlbackVideoShowArea
//	 * @return 
//	 */
//	@RequestMapping(value="/getMlbackVideoShowAreawapListByArea",method=RequestMethod.POST)
//	@ResponseBody
//	public Msg getMlbackVideoShowAreawapListByArea(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackVideoShowArea mlbackVideoShowArea){
//		//接受slideArea
//		
//		Integer videoshowareaAreanum = mlbackVideoShowArea.getVideoshowareaAreanum();
//		
//		MlbackVideoShowArea mlbackVideoShowAreaReq = new MlbackVideoShowArea();
//		mlbackVideoShowAreaReq.setVideoshowareaAreanum(videoshowareaAreanum);
//		mlbackVideoShowAreaReq.setVideoshowareaWapstatus(1);
//		//查询本条
//		List<MlbackVideoShowArea> mlbackVideoShowAreaList =mlbackVideoShowAreaService.selectMlbackVideoShowAreawapListByArea(mlbackVideoShowAreaReq);
//		MlbackVideoShowArea mlbackVideoShowAreaOne = new MlbackVideoShowArea();
//		MlbackProduct mlbackProductOne = new MlbackProduct();
//		List<MlbackProduct> mlbackProductResList = new ArrayList<MlbackProduct>();
//		//MlbackProductService
//		if(mlbackVideoShowAreaList.size()>0){
//			for(int i=0;i<mlbackVideoShowAreaList.size();i++){
//				mlbackVideoShowAreaOne = mlbackVideoShowAreaList.get(i);
//				Integer proId = mlbackVideoShowAreaOne.getSlideProid();
//				mlbackProductOne.setProductId(proId);
//				List<MlbackProduct> mlbackProductList  = mlbackProductService.selectMlbackProduct(mlbackProductOne);
//				if(mlbackProductList.size()>0){
//					mlbackProductResList.add(mlbackProductList.get(0));
//				}
//			}
//		}
//		return Msg.success().add("resMsg", "查看该位置的轮播完毕")
//					.add("mlbackVideoShowAreaList", mlbackVideoShowAreaList).add("mlbackProductResList", mlbackProductResList);
//	}
//	
//	/**
//	 * 7.0	UseNow	190905
//	 * 查询pc不同位置的图片
//	 * @param MlbackVideoShowArea
//	 * @return 
//	 */
//	@RequestMapping(value="/getMlbackVideoShowAreapcListByArea",method=RequestMethod.POST)
//	@ResponseBody
//	public Msg getMlbackVideoShowAreapcListByArea(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackVideoShowArea mlbackVideoShowArea){
//		//接受slideArea
//		Integer slideArea = mlbackVideoShowArea.getSlideArea();
//		MlbackVideoShowArea mlbackVideoShowAreaReq = new MlbackVideoShowArea();
//		mlbackVideoShowAreaReq.setSlideArea(slideArea);
//		mlbackVideoShowAreaReq.setSlidePcstatus(1);
//		//查询本条
//		List<MlbackVideoShowArea> mlbackVideoShowAreaList =mlbackVideoShowAreaService.selectMlbackVideoShowAreapcListByArea(mlbackVideoShowAreaReq);
//		MlbackVideoShowArea mlbackVideoShowAreaOne = new MlbackVideoShowArea();
//		MlbackProduct mlbackProductOne = new MlbackProduct();
//		List<MlbackProduct> mlbackProductResList = new ArrayList<MlbackProduct>();
//		//MlbackProductService
//		if(mlbackVideoShowAreaList.size()>0){
//			for(int i=0;i<mlbackVideoShowAreaList.size();i++){
//				mlbackVideoShowAreaOne = mlbackVideoShowAreaList.get(i);
//				Integer proId = mlbackVideoShowAreaOne.getSlideProid();
//				mlbackProductOne.setProductId(proId);
//				List<MlbackProduct> mlbackProductList  = mlbackProductService.selectMlbackProduct(mlbackProductOne);
//				if(mlbackProductList.size()>0){
//					mlbackProductResList.add(mlbackProductList.get(0));
//				}
//			}
//		}
//		return Msg.success().add("resMsg", "查看该位置的轮播完毕")
//					.add("mlbackVideoShowAreaList", mlbackVideoShowAreaList).add("mlbackProductResList", mlbackProductResList);
//	}

}
