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
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.bean.MlbackShowArea;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlbackShowAreaService;
import com.atguigu.utils.DateUtil;


@Controller
@RequestMapping("/MlbackShowArea")
public class MlbackShowAreaController {
		
	@Autowired
	MlbackShowAreaService mlbackShowAreaService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	useOn	0505
	 * to_MlbackShowArea列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackShowAreaPage")
	public String toMlbackShowAreaPage() throws Exception{
	
		return "back/mlbackShowAreaPage";
	}
	
	/**2.0	useOn	0505
	 * 分类MlbackShowArea列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackShowAreaListByPage")
	@ResponseBody
	public Msg getMlbackShowAreaListByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
		int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackShowArea> mlbackShowAreaList = mlbackShowAreaService.selectMlbackShowAreaAll();
			PageInfo page = new PageInfo(mlbackShowAreaList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**3.0	useOn	0505
	 * MlbackShowArea	insert
	 * @param MlbackShowArea
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackShowArea mlbackShowArea){
		//接受参数信息
		System.out.println("mlbackShowArea:"+mlbackShowArea);
		//取出id
		System.out.println(1);
		Integer showareaIdId = mlbackShowArea.getShowareaId();
		String nowTime = DateUtil.strTime14s();
		mlbackShowArea.setShowareaMotifytime(nowTime);
		if(showareaIdId==null){
			//无id，insert
			mlbackShowArea.setShowareaCreatetime(nowTime);
			int intResult = mlbackShowAreaService.insertSelective(mlbackShowArea);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			int intResult = mlbackShowAreaService.updateByPrimaryKeySelective(mlbackShowArea);
			System.out.println(intResult);
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**4.0	useOn	0505
	 * MlbackShowArea	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackShowArea mlbackShowArea){
		//接收id信息
		int showareaIdId = mlbackShowArea.getShowareaId();
		int intResult = mlbackShowAreaService.deleteByPrimaryKey(showareaIdId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	useOn	0505
	 * 查看单条详情
	 * @param getOneMlbackShowAreaDetail
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackShowAreaDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackShowAreaDetail(@RequestParam(value = "showareaId") Integer showareaId){
		
		//接受showareaId
		MlbackShowArea mlbackShowAreaReq = new MlbackShowArea();
		mlbackShowAreaReq.setShowareaId(showareaId);
		//查询本条
		List<MlbackShowArea> mlbackShowAreaResList =mlbackShowAreaService.selectMlbackShowAreaById(mlbackShowAreaReq);
		MlbackShowArea mlbackShowAreaOne = new MlbackShowArea();
		if(mlbackShowAreaResList.size()>0){
			mlbackShowAreaOne =mlbackShowAreaResList.get(0);
		}else{
			mlbackShowAreaOne = null;
		}
		return Msg.success().add("resMsg", "查看单条mlbackShowAreaOne的详情细节完毕")
					.add("mlbackShowAreaOne", mlbackShowAreaOne);
	}
	
}
