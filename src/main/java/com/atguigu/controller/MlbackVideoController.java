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
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackVideo;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackActShowProService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlbackVideoService;
import com.atguigu.utils.DateUtil;


@Controller
@RequestMapping("/MlbackVideo")
public class MlbackVideoController {
	
	
	@Autowired
	MlbackActShowProService mlbackActShowProService;
		
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackVideoService mlbackVideoService;
	
	/**
	 * 1.0	onuse	20200110
	 * toMlbackVideoPage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackVideoPage")
	public String tologin(HttpSession session) throws Exception{
	
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/mlbackVideoPage";
		}
	}
	
	/**2.0	onuse	20200110
	 * 分类MlbackVideo列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackVideoByPage")
	@ResponseBody
	public Msg getMlbackVideoByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
//		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackVideo> mlbackVideoList = mlbackVideoService.selectMlbackVideoGetAll();
			PageInfo page = new PageInfo(mlbackVideoList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	
	/**3.0	onuse	20200103	检查
	 * MlbackVideo	insert
	 * @param MlbackVideo
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackVideo mlbackVideo){
		//接受参数信息
		//获取类名
		
		Integer videoIfproorcateorpage = mlbackVideo.getVideoIfproorcateorpage();
		if(videoIfproorcateorpage==0){
			//产品
			Integer proId = mlbackVideo.getVideoProid();
			
			MlbackProduct mlbackProductReq = new MlbackProduct();
			MlbackProduct mlbackProductRes = new MlbackProduct();
			mlbackProductReq.setProductId(proId);
			List<MlbackProduct> mlbackProductResList = mlbackProductService.selectMlbackProduct(mlbackProductReq);
			mlbackProductRes = mlbackProductResList.get(0);
			String Pname = mlbackProductRes.getProductName();
			String Pseoname = mlbackProductRes.getProductSeo();
			mlbackVideo.setVideoProname(Pname);
			mlbackVideo.setVideoSeoname(Pseoname);//Pseoname
		}else if(videoIfproorcateorpage==1){
			//类
			Integer cId = mlbackVideo.getVideoCateid();
			MlbackCategory mlbackCategoryReq = new MlbackCategory();
			MlbackCategory mlbackCategoryRes = new MlbackCategory();
			
			mlbackCategoryReq.setCategoryId(cId);
			
			List<MlbackCategory> mlbackCategoryResList = mlbackCategoryService.selectMlbackCategory(mlbackCategoryReq);
			mlbackCategoryRes = mlbackCategoryResList.get(0);
			
			String Cname = mlbackCategoryRes.getCategoryName();
//			String CategoryDesc = mlbackCategoryRes.getCategoryDesc();
			String CategorySeo = mlbackCategoryRes.getCategorySeo();
			mlbackVideo.setVideoCatename(Cname);//Cname
			mlbackVideo.setVideoCateseoname(CategorySeo);
		}else{
			String pageSeoname = mlbackVideo.getVideoPageseoname();
			mlbackVideo.setVideoPageseoname(pageSeoname);//pageSeoname
		}
		Integer videoId = mlbackVideo.getVideoId();
		
		
		String nowtime = DateUtil.strTime14s();
		mlbackVideo.setVideoMotifytime(nowtime);
		
		if(videoId==null){
			mlbackVideo.setVideoCreatetime(nowtime);
			//无id，insert
			mlbackVideoService.insertSelective(mlbackVideo);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			mlbackVideoService.updateByPrimaryKeySelective(mlbackVideo);
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**4.0	onuse	20200103	检查
	 * MlbackActShowPro	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackVideo mlbackVideo){
		//接收id信息
		Integer videoId = mlbackVideo.getVideoId();
		mlbackVideoService.deleteByPrimaryKey(videoId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	onuse	20200103	检查
	 * 查看单条类目的详情细节
	 * @param MlbackActShowPro
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackVideoDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackVideoDetail(@RequestParam(value = "videoId") Integer videoId){
		//接受actshowproId
		MlbackVideo mlbackVideoReq = new MlbackVideo();
		mlbackVideoReq.setVideoId(videoId);
		//查询本条
		MlbackVideo mlbackVideoOne =mlbackVideoService.selectMlbackVideoById(mlbackVideoReq);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlbackVideoOne", mlbackVideoOne);
	}
	
	/**
	 * 6.0	onuse	20200103	检查
	 * 查看首页活动品区域的顺序
	 * @param MlbackActShowPro
	 * @return 
	 */
	@RequestMapping(value="/getMlbackVideoListByActnum",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackVideoListByActnum(@RequestParam(value = "actshowproActnum") Integer actshowproActnum){
		//接受actshowproId
		MlbackVideo mlbackVideoReq = new MlbackVideo();
		mlbackVideoReq.setVideoArea(actshowproActnum);
		//查询本条
		List<MlbackVideo> mlbackVideoList =mlbackVideoService.selectMlbackvideoByVideoArea(mlbackVideoReq);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlbackVideoList", mlbackVideoList);
	}

}
