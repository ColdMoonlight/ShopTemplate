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

import com.atguigu.Vo.StartNum;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackReviewImg;
import com.atguigu.bean.MlfrontReview;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackReviewImgService;
import com.atguigu.service.MlfrontReviewService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.IfMobileUtils;


@Controller
@RequestMapping("/MlfrontReview")
public class MlfrontReviewController {
		
	@Autowired
	MlfrontReviewService mlfrontReviewService;
	
	@Autowired
	MlbackReviewImgService mlbackReviewImgService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	useOn	0505
	 * 后台MlfrontReview列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlfrontReviewPage")
	public String tologin() throws Exception{
	
		return "back/mlbackReviewPage";
	}
	
	/**
	 * 1.1	useOn	0505
	 * 后台toMlfrontReviewManage高级管理页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlfrontReviewManage")
	public String toMlfrontReviewManage() throws Exception{
	
		return "back/toMlfrontReviewManage";
	}
	
	
	/**2.0	useOn	0505
	 * 分类MlbackCoupon列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlfrontReviewByPage")
	@ResponseBody
	public Msg getMlfrontReviewWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 50;
			PageHelper.startPage(pn, PagNum);
			List<MlfrontReview> mlfrontReviewList = mlfrontReviewService.selectMlfrontReviewAll();
			
			PageInfo page = new PageInfo(mlfrontReviewList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}

	/**3.0	useOn	0505
	 * MlfrontReview	insert
	 * @param MlfrontReview
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlfrontReview mlfrontReview){
		//接受参数信息
		System.out.println("mlfrontReview:"+mlfrontReview);
		//取出id
		Integer reviewId = mlfrontReview.getReviewId();
		String nowTime = DateUtil.strTime14s();
		mlfrontReview.setReviewMotifytime(nowTime);
		
		//获取用户名,判断头像图片()
		String reviewUname = mlfrontReview.getReviewUname();
		if(reviewUname==""){
			mlfrontReview.setReviewUname("Megelook Customer");
			reviewUname =mlfrontReview.getReviewUname();
		}
		
		String uimgurl = GetUImgUrlByUname(res,reviewUname);
		mlfrontReview.setReviewUimgurl(uimgurl);
		System.out.println("uimgurl:"+uimgurl);
		//判断时间信息
		String ReviewCreatetime = mlfrontReview.getReviewCreatetime();
		if(ReviewCreatetime ==""){
			mlfrontReview.setReviewCreatetime(nowTime);
		}
		String ReviewConfirmtime = mlfrontReview.getReviewConfirmtime();
		if(ReviewConfirmtime==""){
			mlfrontReview.setReviewConfirmtime(nowTime);
		}
		mlfrontReview.setReviewStarttime(nowTime);
		mlfrontReview.setReviewEndtime(nowTime);
		if(reviewId==null){
			//无id，insert
			mlfrontReviewService.insertSelective(mlfrontReview);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			mlfrontReviewService.updateByPrimaryKeySelective(mlfrontReview);
			return Msg.success().add("resMsg", "更新成功");
			
		}		
	}

	/**3.1	useOn	0505
	 * MlfrontReview	GetUImgUrlByUname
	 * @param MlfrontReview
	 */
	private String GetUImgUrlByUname(HttpServletRequest res,String reviewUname) {
		
		String contextPathStr = res.getContextPath();    
        System.out.println("contextPathStr:"+contextPathStr);
        
        String realPathStr = res.getSession().getServletContext().getRealPath("/");    
        System.out.println("realPathStr:"+realPathStr);
        
        String basePathStr = res.getScheme()+"://"+res.getServerName()+":"+res.getServerPort()+contextPathStr+"/";
        System.out.println("basePathStr:"+basePathStr);
		
		String pathBig = basePathStr;
		
		String path="static/img/ReviewUImg/";
		
		String returnReaUrl = "";
		
		String firstName = reviewUname.substring(0, 1);
		String firstNameUp = firstName.toUpperCase();
		if(firstNameUp.equals("A")){
			returnReaUrl="A.png";
		}else if(firstNameUp.equals("B")){
			returnReaUrl="B.png";
		}else if(firstNameUp.equals("C")){
			returnReaUrl="C.png";
		}else if(firstNameUp.equals("D")){
			returnReaUrl="D.png";
		}else if(firstNameUp.equals("E")){
			returnReaUrl="E.png";
		}else if(firstNameUp.equals("F")){
			returnReaUrl="F.png";
		}else if(firstNameUp.equals("G")){
			returnReaUrl="G.png";
		}else if(firstNameUp.equals("H")){
			returnReaUrl="H.png";
		}else if(firstNameUp.equals("I")){
			returnReaUrl="I.png";
		}else if(firstNameUp.equals("J")){
			returnReaUrl="J.png";
		}else if(firstNameUp.equals("K")){
			returnReaUrl="K.png";
		}else if(firstNameUp.equals("L")){
			returnReaUrl="L.png";
		}else if(firstNameUp.equals("M")){
			returnReaUrl="M.png";
		}else if(firstNameUp.equals("N")){
			returnReaUrl="N.png";
		}else if(firstNameUp.equals("O")){
			returnReaUrl="O.png";
		}else if(firstNameUp.equals("P")){
			returnReaUrl="P.png";
		}else if(firstNameUp.equals("Q")){
			returnReaUrl="Q.png";
		}else if(firstNameUp.equals("R")){
			returnReaUrl="R.png";
		}else if(firstNameUp.equals("S")){
			returnReaUrl="S.png";
		}else if(firstNameUp.equals("T")){
			returnReaUrl="T.png";
		}else if(firstNameUp.equals("U")){
			returnReaUrl="U.png";
		}else if(firstNameUp.equals("V")){
			returnReaUrl="V.png";
		}else if(firstNameUp.equals("W")){
			returnReaUrl="W.png";
		}else if(firstNameUp.equals("X")){
			returnReaUrl="X.png";
		}else if(firstNameUp.equals("Y")){
			returnReaUrl="Y.png";
		}else if(firstNameUp.equals("Z")){
			returnReaUrl="Z.png";
		}
		String returnReaUrlAll = pathBig+path+returnReaUrl;
		return returnReaUrlAll;
	}


	/**4.0	useOn	0505
	 * MlfrontReview	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlfrontReview mlfrontReview){
		//接收id信息
		Integer reviewId = mlfrontReview.getReviewId();
		mlfrontReviewService.deleteByPrimaryKey(reviewId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	useOn	0505
	 * 查看单条MlfrontReview的详情细节
	 * @param MlfrontReview
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontReviewDetailById",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontReviewDetailById(@RequestParam(value = "reviewId") Integer reviewId){
		//接受信息
		MlfrontReview mlfrontReviewReq = new MlfrontReview();
		mlfrontReviewReq.setReviewId(reviewId);
		List<MlfrontReview> mlfrontReviewResList =mlfrontReviewService.selectMlfrontReviewById(mlfrontReviewReq);
		MlfrontReview mlfrontReviewOne =mlfrontReviewResList.get(0);
		return Msg.success().add("resMsg", "查看单条优惠券的详情细节完毕")
					.add("mlfrontReviewOne", mlfrontReviewOne);
	}
	
	/**
	 * 5.0	useOn	0505
	 * 查看单条MlfrontReview的详情细节
	 * @param MlfrontReview
	 * @return 
	 */
	@RequestMapping(value="/getMlfrontReviewList",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlfrontReviewList(HttpServletResponse rep,HttpServletRequest res){
		//接受信息
		List<MlfrontReview> mlfrontReviewResList =mlfrontReviewService.selectMlfrontReviewAll();
		return Msg.success().add("resMsg", "查看list完毕")
					.add("mlfrontReviewResList", mlfrontReviewResList);
	}
	
	/**
	 * 6.0	useOn	0505
	 * 查看单条MlfrontReview的详情细节
	 * @param MlfrontReview
	 * @return 
	 */
	@RequestMapping(value="/getMlfrontReviewListByPId",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlfrontReviewListByPId(HttpServletResponse rep,HttpServletRequest res,@RequestParam(value = "productId") Integer productId){
		//接受信息
		
		MlfrontReview mlfrontReview = new MlfrontReview();
		mlfrontReview.setReviewPid(productId);
		mlfrontReview.setReviewStatus(1);
		List<MlfrontReview> mlfrontReviewResList =mlfrontReviewService.selectMlfrontReviewListByPId(mlfrontReview);
		MlbackReviewImg mlbackReviewImg = new MlbackReviewImg();
		
		List<MlbackReviewImg>  mlbackReviewImgList = new ArrayList<MlbackReviewImg>();
		List<List<String>> imgUrlStrListst = new ArrayList<List<String>>();
		
		for(MlfrontReview mlfrontReviewOne:mlfrontReviewResList){
			Integer reviewId = mlfrontReviewOne.getReviewId();
			mlbackReviewImg.setReviewId(reviewId);
			mlbackReviewImgList =  mlbackReviewImgService.selectMlbackReviewImgByReviewId(reviewId);
			
			String reviewimgUrl ="";
			
			List<String> imgUrlOneList = new ArrayList<String>();
			for(MlbackReviewImg mlbackReviewImgOne:mlbackReviewImgList){
				reviewimgUrl = mlbackReviewImgOne.getReviewimgUrl();
				imgUrlOneList.add(reviewimgUrl);
			}
			imgUrlStrListst.add(imgUrlOneList);
		}
		return Msg.success().add("resMsg", "查看list完毕")
					.add("mlfrontReviewResList", mlfrontReviewResList).add("imgUrlStrListst", imgUrlStrListst);
	}
	
	/**7.0	useOn	0505
	 * review的分页接口
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlfrontReviewByProductIdAndPage",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlfrontReviewByProductIdAndPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,@RequestParam(value = "productId") Integer productId,HttpSession session) {
		
		//接受信息
		//查询该ID下的评论
		MlfrontReview mlfrontReview = new MlfrontReview();
		mlfrontReview.setReviewPid(productId);
		mlfrontReview.setReviewStatus(1);
		List<MlfrontReview> mlfrontReviewResListPage = new ArrayList<MlfrontReview>();
		
		List<MlfrontReview> mlfrontReviewResreturn = new ArrayList<MlfrontReview>();
		
		//分页数据获取完毕
		int PagNum = 5;
		PageHelper.startPage(pn, PagNum);
		List<MlfrontReview> mlfrontReviewResList =mlfrontReviewService.selectMlfrontReviewListByPId(mlfrontReview);
		PageInfo page = new PageInfo(mlfrontReviewResList, PagNum);
		
		//从中获取本次展示的5条信息
		mlfrontReviewResListPage = page.getList();
		MlbackReviewImg mlbackReviewImg = new MlbackReviewImg();
		List<MlbackReviewImg>  mlbackReviewImgList = new ArrayList<MlbackReviewImg>();
		List<List<String>> imgUrlStrListst = new ArrayList<List<String>>();
		//开始对5条信息进行遍历，获取每条的详情照片
		Integer StartIndex = (pn-1)*PagNum;
		Integer lengthAll = mlfrontReviewResListPage.size();
		Integer EndIndex = 0;
		if(StartIndex+5>lengthAll){
			EndIndex = lengthAll;
		}else{
			EndIndex = StartIndex+5;
		}
		
		for(int i=0;i<EndIndex;i++){
			MlfrontReview mlfrontReviewOne = mlfrontReviewResListPage.get(i);
			mlfrontReviewResreturn.add(mlfrontReviewOne);
			Integer reviewId = mlfrontReviewOne.getReviewId();
			mlbackReviewImg.setReviewId(reviewId);
			mlbackReviewImgList =  mlbackReviewImgService.selectMlbackReviewImgByReviewId(reviewId);
			
			String reviewimgUrl ="";
			List<String> imgUrlOneList = new ArrayList<String>();
			for(MlbackReviewImg mlbackReviewImgOne:mlbackReviewImgList){
				reviewimgUrl = mlbackReviewImgOne.getReviewimgUrl();
				imgUrlOneList.add(reviewimgUrl);
			}
			imgUrlStrListst.add(imgUrlOneList);
		}
		//return 分页信息pageInfo,5条评论内容，5条评论中的图片
		return Msg.success().add("pageInfo", page).add("mlfrontReviewResreturn", mlfrontReviewResreturn).add("imgUrlStrListst", imgUrlStrListst);
	}
	
	/**8.0	useOn	0505
	 * review的汇总接口
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlfrontReviewCount",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlfrontReviewCount(HttpServletResponse rep,HttpServletRequest res,@RequestParam(value = "productId") Integer productId){
		//封装参数
		MlfrontReview mlfrontReview = new MlfrontReview();
		mlfrontReview.setReviewPid(productId);
		mlfrontReview.setReviewStatus(1);
		//封装完毕
		List<MlfrontReview> mlfrontReviewResList =mlfrontReviewService.selectMlfrontReviewListByPId(mlfrontReview);
		//评论总数
		Integer allReviewNum = mlfrontReviewResList.size();
		//各个星级评论数	
		List<StartNum> StartNumList =  staryStartDetail(mlfrontReviewResList);
		return Msg.success().add("StartNumList", StartNumList).add("allReviewNum", allReviewNum);
//		}
	}
	
	/**8.1	useOn	0505
	 * 分类StartNum
	 * @param pn
	 * @return
	 */
	private List<StartNum> staryStartDetail(List<MlfrontReview> mlfrontReviewList) {
		List<StartNum> startNumList = new ArrayList<StartNum>();
		StartNum StartNumOne = new StartNum(1,0);
		StartNum StartNumTwo = new StartNum(2,0);
		StartNum StartNumThree = new StartNum(3,0);
		StartNum StartNumFour = new StartNum(4,0);
		StartNum StartNumFive = new StartNum(5,0);
		for(MlfrontReview mlfrontReview :mlfrontReviewList){
			Integer starnum = mlfrontReview.getReviewProstarnum();//这个是产品评分
			Integer temp = 0;
			if(starnum==1){
				temp = StartNumOne.getStartCount();
				StartNumOne.setStartCount(temp+1);
			}else if(starnum==2){
				temp = StartNumTwo.getStartCount();
				StartNumTwo.setStartCount(temp+1);
			}else if(starnum==3){
				temp = StartNumThree.getStartCount();
				StartNumThree.setStartCount(temp+1);
			}else if(starnum==4){
				temp = StartNumFour.getStartCount();
				StartNumFour.setStartCount(temp+1);
			}else if(starnum==5){
				temp = StartNumFive.getStartCount();
				StartNumFive.setStartCount(temp+1);
			}
		}
		startNumList.add(StartNumOne);
		startNumList.add(StartNumTwo);
		startNumList.add(StartNumThree);
		startNumList.add(StartNumFour);
		startNumList.add(StartNumFive);
		return startNumList;
	}
	
	/**9.0	useOn	0505
	 * MlfrontReview	insert
	 * @param MlfrontReview
	 */
	@RequestMapping(value="/saveNew",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveNewSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlfrontReview mlfrontReview){
		
		Integer Pid = mlfrontReview.getReviewPid();
		//接受参数信息
		System.out.println("mlfrontReview:"+mlfrontReview);
		String nowTime = DateUtil.strTime14s();
		mlfrontReview.setReviewMotifytime(nowTime);
		mlfrontReview.setReviewCreatetime(nowTime);
		mlfrontReview.setReviewConfirmtime(nowTime);
		mlfrontReview.setReviewStatus(0);//0待审核状态1审核完毕
		//插入本条null的reviewid
		mlfrontReviewService.insertSelective(mlfrontReview);
		//查回来插入的这一条
		MlfrontReview MlfrontReviewReq = new MlfrontReview();
		MlfrontReviewReq.setReviewPid(Pid);
		MlfrontReviewReq.setReviewStatus(0);
		List<MlfrontReview> mlfrontReviewResList =mlfrontReviewService.selectMlfrontReviewListByPId(MlfrontReviewReq);
		MlfrontReview mlfrontReviewOne = mlfrontReviewResList.get(0);
		//查回来插入的这一条，返回前台
		return Msg.success().add("resMsg", "插入成功").add("mlfrontReviewOne", mlfrontReviewOne);
		
	}
	
	
	/**10.0	useOn	0505
	 * MlfrontReview	delete
	 * @param id
	 */
	@RequestMapping(value="/deleteNew",method=RequestMethod.POST)
	@ResponseBody
	public Msg deleteNew(@RequestBody MlfrontReview mlfrontReview){
		//接收id信息,删除本条主评论
		Integer reviewId = mlfrontReview.getReviewId();
		mlfrontReviewService.deleteByPrimaryKey(reviewId);
		
		mlbackReviewImgService.deleteByreviewId(reviewId);
		
		return Msg.success().add("resMsg", "delete success");
	}
	
	
	/**11.0	useOn	0505
	 * 分类MlfrontReview列表分页list数据
	 * @param pn,
	 * Integer reviewPid;
	 * Integer reviewStatus;
	 * Integer reviewProstarnum;
	 * String reviewStarttime;
	 * String reviewEndtime;
	 * @return
	 */
	@RequestMapping(value="/selectMlblackReviewListBySearch",method=RequestMethod.POST)
	@ResponseBody
	public Msg selectMlfrontReviewListBySearch(@RequestParam(value = "pn", defaultValue = "1") Integer pn,@RequestParam(value = "reviewPid") Integer reviewPid,
			@RequestParam(value = "reviewStatus") Integer reviewStatus,@RequestParam(value = "reviewProstarnum") Integer reviewProstarnum,
			@RequestParam(value = "reviewStarttime") String reviewStarttime,@RequestParam(value = "reviewEndtime") String reviewEndtime,
			HttpSession session) {

		MlfrontReview mlfrontReviewReq = new MlfrontReview();
		if(reviewPid==999){
			System.out.println("eviewPid==999");
		}else{
			mlfrontReviewReq.setReviewPid(reviewPid);
		}
		if(reviewProstarnum==0){
			System.out.println("reviewProstarnum==0");
		}else{
			mlfrontReviewReq.setReviewProstarnum(reviewProstarnum);
		}
		if(reviewStatus==999){
			System.out.println("reviewStatus==999");
		}else{
			mlfrontReviewReq.setReviewStatus(reviewStatus);
		}
		mlfrontReviewReq.setReviewStarttime(reviewStarttime);
		mlfrontReviewReq.setReviewEndtime(reviewEndtime);
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlfrontReview> mlfrontReviewList = mlfrontReviewService.selectMlfrontReviewListBySearch(mlfrontReviewReq);
			
			PageInfo page = new PageInfo(mlfrontReviewList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**
	 * 12.0	onuse	200104
	 * Ins Review page
	 * @param jsp
	 * @return
	 * */
	@RequestMapping("/toReviewInsPage")
	public String toReviewInsPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
	
		String ifMobile = IfMobileUtils.isMobileOrPc(rep, res);
		  
		if(ifMobile.equals("1")){
			return "mfront/navActive/mreviewInsListPage";
		}else{
			return "front/navActive/pcreviewInsList";
		}
	}
	
	
	/**13.0	onuse	200104
	 * search review From Ins
	 */
	@RequestMapping(value="/selectReviewListFrom",method=RequestMethod.POST)
	@ResponseBody
	public Msg selectReviewListFrom(HttpSession session,MlfrontReview mlfrontReview) {
		
		mlfrontReview.setReviewStatus(1);
		List<MlfrontReview> mlfrontReviewList = mlfrontReviewService.selectReviewListFrom(mlfrontReview);
		return Msg.success().add("mlfrontReviewList", mlfrontReviewList);
		
	}
}
