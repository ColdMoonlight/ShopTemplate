package com.atguigu.controller;

import java.util.ArrayList;
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
import com.atguigu.bean.MlbackFootNav;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlbackFootNavService;
import com.atguigu.utils.DateUtil;


@Controller
@RequestMapping("/MlbackFootNav")
public class MlbackFootNavController {
		
	@Autowired
	MlbackFootNavService mlbackFootNavService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	UseNow	0505
	 * toMlbackFootNav列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackFootNavPage")
	public String toMlbackFootNavPage() throws Exception{
	
		return "back/mlbackFootNavPage";
	}
	
	
	/**2.0	UseNow	0505
	 * 分类MlbackFootNav列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackFootNavByPage")
	@ResponseBody
	public Msg getMlbackAreafreightWithJson(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn
			,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
		int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackFootNav> mlbackFootNavList = mlbackFootNavService.selectMlbackFootNavAll();
			PageInfo page = new PageInfo(mlbackFootNavList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**3.0	UseNow	0505
	 * MlbackAreafreight	insert
	 * @param MlbackAreafreight
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackFootNav mlbackFootNav){
		//接受参数信息
		System.out.println("mlbackFootNav:"+mlbackFootNav);
		//取出id
		System.out.println(1);
		Integer footnavId = mlbackFootNav.getFootnavId();
		String nowTime = DateUtil.strTime14s();
		mlbackFootNav.setFootnavMotifytime(nowTime);
		if(footnavId==null){
			//无id，insert
			mlbackFootNav.setFootnavCreatetime(nowTime);
			int intResult = mlbackFootNavService.insertSelective(mlbackFootNav);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			int intResult = mlbackFootNavService.updateByPrimaryKeySelective(mlbackFootNav);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "更新成功");
			
		}		
	}
	
	/**4.0	UseNow	0505
	 * MlbackAreafreight	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackFootNav mlbackFootNav){
		//接收id信息
		Integer footnavId = mlbackFootNav.getFootnavId();
		int intResult = mlbackFootNavService.deleteByPrimaryKey(footnavId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	
	/**
	 * 5.0	UseNow	0505
	 * 查看单条类目的详情细节
	 * @param MlbackAreafreight
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackFootNavDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackFootNavDetail(@RequestParam(value = "footnavId") Integer footnavId){
		
		//接受categoryId
		MlbackFootNav mlbackFootNavReq = new MlbackFootNav();
		mlbackFootNavReq.setFootnavId(footnavId);
		//查询本条
		List<MlbackFootNav> mlbackFootNavResList =mlbackFootNavService.selectMlbackFootNavById(mlbackFootNavReq);
		MlbackFootNav MlbackFootNavOne = new MlbackFootNav();
		if(mlbackFootNavResList.size()>0){
			MlbackFootNavOne =mlbackFootNavResList.get(0);
		}else{
			MlbackFootNavOne = null;
		}
		return Msg.success().add("resMsg", "查看单条MlbackFootNavOne的详情细节完毕")
					.add("MlbackFootNavOne", MlbackFootNavOne);
	}
	
	/**
	 * 6.0	UseNow	0505
	 * 查看单条类目的详情细节
	 * @param MlbackAreafreight
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackFootNavOneAllDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackFootNavOneAllDetail(@RequestParam(value = "footnavId") Integer footnavId){
		
		//接受categoryId
		MlbackFootNav mlbackFootNavReq = new MlbackFootNav();
		mlbackFootNavReq.setFootnavId(footnavId);
		//查询本条
		List<MlbackFootNav> mlbackFootNavResList =mlbackFootNavService.selectMlbackFootNavOneAllById(mlbackFootNavReq);
		MlbackFootNav MlbackFootNavOne = new MlbackFootNav();
		if(mlbackFootNavResList.size()>0){
			MlbackFootNavOne =mlbackFootNavResList.get(0);
		}else{
			MlbackFootNavOne = null;
		}
		return Msg.success().add("resMsg", "查看单条MlbackFootNavOne的详情细节完毕")
					.add("MlbackFootNavOne", MlbackFootNavOne);
	}
	
	
	/**
	 * 7.0	UseNow	0505
	 * 前台查看list		查看foot全部的接口类目的详情细节
	 * @param MlbackAreafreight
	 * @return 
	 */
	@RequestMapping(value="/getMlfrontFootNavAll",method=RequestMethod.POST)
	@ResponseBody
	public Msg selectMlbackFootNavAllIfShow(HttpServletResponse rep,HttpServletRequest res){
		
		
		MlbackFootNav mlbackFootNavReq = new MlbackFootNav();
		mlbackFootNavReq.setFootnavIfShow(1);//0不生效	1生效中 
		//查询本条
		List<MlbackFootNav> mlbackFootNavList = mlbackFootNavService.selectMlbackFootNavAllIfShow(mlbackFootNavReq);
		List<MlbackFootNav> mlbackFootNavOneList = new ArrayList<MlbackFootNav>();
		List<MlbackFootNav> mlbackFootNavTwoList = new ArrayList<MlbackFootNav>();
		List<MlbackFootNav> mlbackFootNavThreeList = new ArrayList<MlbackFootNav>();
		List<MlbackFootNav> mlbackFootNavFourList = new ArrayList<MlbackFootNav>();
		MlbackFootNav MlbackFootNavOne = new MlbackFootNav();
		
		Integer isNav = 1;
		if(mlbackFootNavList.size()>0){
			isNav = 1;
			MlbackFootNavOne =mlbackFootNavList.get(0);
			for(int i=0;i<mlbackFootNavList.size();i++){
				//获取当前
				MlbackFootNavOne = mlbackFootNavList.get(i);
				if(mlbackFootNavList.get(i).getFootnavLie()==1){
					//第1列
					mlbackFootNavOneList.add(MlbackFootNavOne);
				}else if(mlbackFootNavList.get(i).getFootnavLie()==2){
					//第2列
					mlbackFootNavTwoList.add(MlbackFootNavOne);
				}else if(mlbackFootNavList.get(i).getFootnavLie()==3){
					//第3列
					mlbackFootNavThreeList.add(MlbackFootNavOne);
				}else{
					//第4列
					mlbackFootNavFourList.add(MlbackFootNavOne);
				}
			}
		}else{
			isNav = 0;
		}
		//4footer里请求这个接口,回去的4列
		return Msg.success().add("resMsg", "查看底部导航完毕详情细节完毕").add("isNav", isNav)
				.add("mlbackFootNavOneList", mlbackFootNavOneList).add("mlbackFootNavTwoList", mlbackFootNavTwoList)
				.add("mlbackFootNavThreeList", mlbackFootNavThreeList).add("mlbackFootNavFourList", mlbackFootNavFourList);
	}
	
	/**
	 * 8.0	UseNow	0505
	 * toMlbackFootNav展示页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/toMlfrontFootNavPage",method=RequestMethod.GET)
	public String toMlfrontFootNavPage(HttpSession session,@RequestParam(value = "footnavId") Integer footnavId,HttpServletResponse rep,HttpServletRequest res) throws Exception{
	
		//接收传递进来的参数
		Integer footnavIdReq = footnavId;
		//放回响应域中
		res.setAttribute("footnavId", footnavIdReq);
		
		session.setAttribute("footnavId", footnavIdReq);
		
		return "mfront/footNavPage";
	}
	
	/**
	 * 9.0	UseNow	0505
	 * 前台查看list		查看foot全部的接口类目的详情细节
	 * @param MlbackAreafreight
	 * @return 
	 */
	@RequestMapping(value="/getMlfrontFootNavAllSimple",method=RequestMethod.POST)
	@ResponseBody
	public Msg selectMlfrontFootNavAllSimpleIfShow(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackFootNav mlbackFootNavReq = new MlbackFootNav();
		mlbackFootNavReq.setFootnavIfShow(1);//0不生效	1生效中 
		//查询本条
		List<MlbackFootNav> mlbackFootNavList = mlbackFootNavService.selectMlbackFootNavAllSimpleIfShow(mlbackFootNavReq);
		List<MlbackFootNav> mlbackFootNavOneList = new ArrayList<MlbackFootNav>();
		List<MlbackFootNav> mlbackFootNavTwoList = new ArrayList<MlbackFootNav>();
		List<MlbackFootNav> mlbackFootNavThreeList = new ArrayList<MlbackFootNav>();
		List<MlbackFootNav> mlbackFootNavFourList = new ArrayList<MlbackFootNav>();
		MlbackFootNav MlbackFootNavOne = new MlbackFootNav();
		
		Integer isNav = 1;
		if(mlbackFootNavList.size()>0){
			isNav = 1;
			MlbackFootNavOne =mlbackFootNavList.get(0);
			for(int i=0;i<mlbackFootNavList.size();i++){
				//获取当前
				MlbackFootNavOne = mlbackFootNavList.get(i);
				if(mlbackFootNavList.get(i).getFootnavLie()==1){
					//第1列
					mlbackFootNavOneList.add(MlbackFootNavOne);
				}else if(mlbackFootNavList.get(i).getFootnavLie()==2){
					//第2列
					mlbackFootNavTwoList.add(MlbackFootNavOne);
				}else if(mlbackFootNavList.get(i).getFootnavLie()==3){
					//第3列
					mlbackFootNavThreeList.add(MlbackFootNavOne);
				}else{
					//第4列
					mlbackFootNavFourList.add(MlbackFootNavOne);
				}
			}
		}else{
			isNav = 0;
		}
		//4footer里请求这个接口,回去的4列
		return Msg.success().add("resMsg", "查看底部导航完毕详情细节完毕").add("isNav", isNav)
				.add("mlbackFootNavOneList", mlbackFootNavOneList).add("mlbackFootNavTwoList", mlbackFootNavTwoList)
				.add("mlbackFootNavThreeList", mlbackFootNavThreeList).add("mlbackFootNavFourList", mlbackFootNavFourList);
	}
}
