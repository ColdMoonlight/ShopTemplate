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
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackProductViewDetail;
import com.atguigu.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlbackProductViewDetailService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.IfMobileUtils;

@Controller
@RequestMapping("/MlbackProduct")
public class MlbackProductController {
		
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackProductViewDetailService mlbackProductViewDetailService;
	
	/**
	 * 1.0	onuse	200225	check
	 * to-Product-list页
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackProductPage")
	public String toMlbackProductPage(HttpSession session) throws Exception{
	
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		if(mlbackAdmin==null){
			return "back/mlbackAdminLogin";
		}else{
			return "back/mlbackProductPage";
		}
	}
	
	/**
	 * 2.0	UseOff	0505
	 * to分类MlbackProduct列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackProductSearchPage")
	public String toMlbackProductSearchPage() throws Exception{
	
		return "back/high/mlbackProducthighPage";
	}
	
	/**
	 * 3.1.1	UseNow	0505
	 * 前台移动端获取详情页面mfront/productDetails
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/tomProductDetailPage",method=RequestMethod.GET)
	public String tomProductDetailPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "productId") Integer productId) throws Exception{
		//接收传递进来的参数
		Integer productIdReq = productId;
		//放回响应域中
		res.setAttribute("productId", productIdReq);
		//放回session域中
		session.setAttribute("productDetailId", productIdReq);
		
		if(productIdReq!=null){
			MlbackProduct mlbackProductReq = new MlbackProduct();
			mlbackProductReq.setProductId(productId);
			List<MlbackProduct> mlbackProductResList =  mlbackProductService.selectMlbackProductSimple(mlbackProductReq);
			MlbackProduct mlbackProductRes = mlbackProductResList.get(0);
			session.setAttribute("mlbackProductMetaTitle", mlbackProductRes.getProductMetaTitle());
			session.setAttribute("mlbackProductMetaKeyWords", mlbackProductRes.getProductMetaKeyWords());
			session.setAttribute("mlbackProductMeteDesc", mlbackProductRes.getProductMetaDesc());
		}
		//返回视图
		return "mfront/productDetails";
	}
	
	/**
	 * 3.2.1	UseNow	0505
	 * 前台PC端获取详情页面front/pcproductDetails
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/topcProductDetailPage",method=RequestMethod.GET)
	public String topcProductDetailPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "productId") Integer productId) throws Exception{
		//接收传递进来的参数
		Integer productIdReq = productId;
		//放回响应域中
		res.setAttribute("productId", productIdReq);
		//放回session域中
		session.setAttribute("productDetailId", productIdReq);
		
		if(productIdReq!=null){
			MlbackProduct mlbackProductReq = new MlbackProduct();
			mlbackProductReq.setProductId(productId);
			List<MlbackProduct> mlbackProductResList =  mlbackProductService.selectMlbackProductSimple(mlbackProductReq);
			MlbackProduct mlbackProductRes = mlbackProductResList.get(0);
			session.setAttribute("mlbackProductMetaTitle", mlbackProductRes.getProductMetaTitle());
			session.setAttribute("mlbackProductMetaKeyWords", mlbackProductRes.getProductMetaKeyWords());
			session.setAttribute("mlbackProductMeteDesc", mlbackProductRes.getProductMetaDesc());
		}
		
		//返回视图
		return "front/pcproductDetails";
	}
	
	/**
	 * 3.0	onuse	200103
	 * 前台详情页面wap/pc的productDetails
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/tofbProductDetailPageByhtml",method=RequestMethod.GET)
	 public String tomfbProductDetailPageByhtml(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "productSeo") String productSeo) throws Exception{
	  
	  String nowTime = DateUtil.strTime14s();
	  //System.out.println("访客通过productSeo进入落地页-productSeo:"+productSeo+" ,nowTime:"+nowTime);
	  //准备封装参数
	  MlbackProduct mlbackProductrepBySeo = new MlbackProduct();
	  mlbackProductrepBySeo.setProductSeo(productSeo);
	  //3.0.1记录落地页信息insertProView
	  insertProView(productSeo,session);

	  MlbackProduct mlbackProductRes = mlbackProductService.selectMlbackProductBySeo(mlbackProductrepBySeo);
	  
	  String ifMobile = IfMobileUtils.isMobileOrPc(rep, res);
	  
	  if(mlbackProductRes==null){
		  if(ifMobile.equals("1")){
			  return "mfront/index";
		  }else{
			  return "front/index";
		  }
	  }else{
		  Integer productIdReq = mlbackProductRes.getProductId();
		  //放回响应域中
		  res.setAttribute("productId", productIdReq);
		  //放回session域中
		  session.setAttribute("productDetailId", productIdReq);
		  session.setAttribute("mlbackProductMetaTitle", mlbackProductRes.getProductMetaTitle());
		  session.setAttribute("mlbackProductMetaKeyWords", mlbackProductRes.getProductMetaKeyWords());
		  session.setAttribute("mlbackProductMeteDesc", mlbackProductRes.getProductMetaDesc());
		  //返回视图
		  if(ifMobile.equals("1")){
			  return "mfront/productDetails";
		  }else{
			  return "front/pcproductDetails";
		  }
	  	}
	 }
	/**3.0.1	onuse	200103
	 * 记录落地页信息insertProView
	 * @param productSeo,session
	 * @return
	 */
	private void insertProView(String productSeo,HttpSession session) {
		//准备参数信息
		MlbackProductViewDetail mlbackProductViewDetailreq = new MlbackProductViewDetail();
		//浏览对象
		mlbackProductViewDetailreq.setProviewdetailSeoname(productSeo);
		//sessionID
		String sessionId =  session.getId();
		mlbackProductViewDetailreq.setProviewdetailSessionid(sessionId);
		//时间信息
		String nowTime = DateUtil.strTime14s();
		mlbackProductViewDetailreq.setProviewdetailCreatetime(nowTime);
		mlbackProductViewDetailreq.setProviewdetailMotifytime(nowTime);
		mlbackProductViewDetailService.insertSelective(mlbackProductViewDetailreq);
	}

	/**4.0	onuse	200104
	 * 后台-Product-list分页数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackProductByPage")
	@ResponseBody
	public Msg getMlbackProductWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 50;
			PageHelper.startPage(pn, PagNum);
			List<MlbackProduct> mlbackProductList = mlbackProductService.selectMlbackProductGetAll();
			PageInfo page = new PageInfo(mlbackProductList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**5.0	onuse	200104
	 * Product	add/update
	 * @param MlbackProduct
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackProduct mlbackProduct){
		//取出参数信息
		Integer productId = mlbackProduct.getProductId();
		String productName = mlbackProduct.getProductName();
		Integer  CategoryId = mlbackProduct.getProductCategoryid();
		
		MlbackCategory MlbackCategoryParentNameReq = new MlbackCategory();
		MlbackCategoryParentNameReq.setCategoryId(CategoryId);
		
		List<MlbackCategory> MlbackCategoryList = mlbackCategoryService.selectMlbackCategory(MlbackCategoryParentNameReq);
		MlbackCategory MlbackCategory0 = MlbackCategoryList.get(0);
		String MlbackCategoryName =  MlbackCategory0.getCategoryDesc();
		
		mlbackProduct.setproductCategoryname(MlbackCategoryName);
		//获取折扣id
		Integer Actoffid = mlbackProduct.getProductActoffid();
		if(Actoffid==null){
			Actoffid = 10;
		}
		Integer Actoffoff= Actoffid*10;
		//获取折扣力度
		mlbackProduct.setProductActoffoff(Actoffoff);
		if(productId==null){
			//无id，insert
			mlbackProductService.insertSelective(mlbackProduct);
			//System.out.println("操作说明:管理员add产品信息mlbackProduct");
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//先对这个产品选择的一些类，进行productIdStr的清理,
			//有id，update
			String categoryIdsStr = mlbackProduct.getProductCategoryIdsStr();
			//5.0.1从中读取categoryIdsStr,切割得到每一个categoryId,遍历，把productId,填充再每个查回来的categort中的proidStr拼上
			UpdateCategoryProductIdStr(categoryIdsStr,productId,productName);
			//更新本条product数据
			mlbackProductService.updateByPrimaryKeySelective(mlbackProduct);
			//System.out.println("操作说明:管理员update产品信息mlbackProduct");
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**
	 * 5.0.1
	 * //从中读取categoryIdsStr,切割得到每一个categoryId,
	 * 遍历categoryId查询，把productId,填充再每个查回来的category中的proidStr拼上
	 * */
	private void UpdateCategoryProductIdStr(String categoryIdsStr, Integer productId,String productName) {
		
		String inproductIdStr=productId+"";
		
		if(categoryIdsStr==""){
			System.out.println("categoryIdsStr为空");
			//把该productId下的
			ProductCategoryIdsStrUpdateOld(productId,categoryIdsStr);
			//清理掉，该产品下的类
			MlbackProduct mlbackProductReq = new MlbackProduct();
			mlbackProductReq.setProductId(productId);
			mlbackProductReq.setProductCategoryIdsStr("");
			mlbackProductReq.setProductCategoryNamesStr("");
			mlbackProductService.updateByPrimaryKeySelective(mlbackProductReq);
		}else{
			//先清理老的数据
			ProductCategoryIdsStrUpdateOld(productId,categoryIdsStr);
			//从中读取categoryIdsStr,切割得到每一个categoryId,
			String categoryIdsStrArr [] = categoryIdsStr.split(",");
			String categoryIdStr = "";
			Integer categoryIdInt = 0;
			MlbackCategory mlbackCategoryReq = new MlbackCategory();
			MlbackCategory mlbackCategoryRes = new MlbackCategory();
			
			MlbackCategory mlbackCategoryReqUpdate = new MlbackCategory();
			List<MlbackCategory> mlbackCategoryList = new ArrayList<MlbackCategory>();
			String categoryProductIdsStr = "";
			String categoryproductNamesStr = "";
			
			for(int i=0;i<categoryIdsStrArr.length;i++){
				categoryIdStr=categoryIdsStrArr[i];
				categoryIdInt = Integer.parseInt(categoryIdStr);
				//遍历categoryId查询
				mlbackCategoryReq.setCategoryId(categoryIdInt);
				mlbackCategoryList = mlbackCategoryService.selectMlbackCategory(mlbackCategoryReq);
				mlbackCategoryRes = mlbackCategoryList.get(0);
 				categoryProductIdsStr = mlbackCategoryRes.getCategoryProductIds();
				categoryproductNamesStr = mlbackCategoryRes.getCategoryProductNames();
				//把productId,填充再每个查回来的categort中的proidStr拼上
				if(categoryProductIdsStr==null||categoryProductIdsStr.length()==0){
					//如果当前没有,属于第一次填充，直接替代就好
					categoryProductIdsStr =inproductIdStr;
					categoryproductNamesStr=productName;
				}else{
					//如果产品类下已经有数据
					
					int ifHave = cheakifHave(categoryProductIdsStr,inproductIdStr);
					
					//先判断是否包含本次
					if(ifHave>0){
						//只要test.indexOf('This')返回的值不是-1说明test字符串中包含字符串'This',相反如果包含返回的值必定是-1"
						//如果包含，跳过
						continue;
					}else{
						//如果不包含，拼接
						categoryProductIdsStr = categoryProductIdsStr +","+ inproductIdStr;
						categoryproductNamesStr = categoryproductNamesStr+","+productName;
					}
				}
				//操作完，执行更新
				mlbackCategoryReqUpdate.setCategoryId(categoryIdInt);
				mlbackCategoryReqUpdate.setCategoryProductIds(categoryProductIdsStr);
				mlbackCategoryReqUpdate.setCategoryProductNames(categoryproductNamesStr);
				mlbackCategoryService.updateByPrimaryKeySelective(mlbackCategoryReqUpdate);
			}
		}
	}

	
	private int cheakifHave(String categoryProductIdsStr, String inproductIdStr) {
		
		int num = 0;
		String arrStr [] = categoryProductIdsStr.split(",");
		String nowPidStr = "";
		for(int i=0;i<arrStr.length;i++){
			nowPidStr =arrStr[i];
			if(nowPidStr.equals(inproductIdStr)){
				//有相同的，所以num增加
				num++;
				break;//找到，跳出
			}
		}
		return num;
	}

	//清理每条的新产品信息
	private void ProductCategoryIdsStrUpdateOld(Integer productId,String categoryIdsStrNew) {
		MlbackProduct mlbackProductReq = new MlbackProduct();
		MlbackProduct mlbackProductRes = new MlbackProduct();
		mlbackProductReq.setProductId(productId);
		List<MlbackProduct> mlbackProductListOld =  mlbackProductService.selectMlbackProduct(mlbackProductReq);
		mlbackProductRes = mlbackProductListOld.get(0);
		String productCategoryIdsStr = mlbackProductRes.getProductCategoryIdsStr();
		MlbackCategory mlbackCategoryReq = new MlbackCategory();
		MlbackCategory mlbackCategoryRes = new MlbackCategory();
		List<MlbackCategory> MlbackCategoryList = new ArrayList<MlbackCategory>();
		if(productCategoryIdsStr==null||productCategoryIdsStr.length()==0){
			//没有被类管理绑定过
			System.out.println("产品的这个字段下,没有被类管理绑定过,不需要进行移除操作");
		}else{
			//被类管理绑定过
			String productCategoryIdsStrArr [] =  productCategoryIdsStr.split(",");
			for(int i=0;i<productCategoryIdsStrArr.length;i++){
				//遍历categoryIdsStr
				String  categoryIdsStr = productCategoryIdsStrArr[i];
				Integer categoryIdsInt = Integer.parseInt(categoryIdsStr);
				mlbackCategoryReq.setCategoryId(categoryIdsInt);
				MlbackCategoryList = mlbackCategoryService.selectMlbackCategory(mlbackCategoryReq);
				if(!(MlbackCategoryList.size()>0)){
					continue;
				}
				mlbackCategoryRes = MlbackCategoryList.get(0);
				
				//从该中取的老的产品串串
				String categoryProductIds = "";
				String categoryProductNames = "";
				categoryProductIds = mlbackCategoryRes.getCategoryProductIds();
				categoryProductNames = mlbackCategoryRes.getCategoryProductNames();
				
				String categoryProductIdsArr [] = categoryProductIds.split(",");
				String categoryProductNamesArr [] = categoryProductNames.split(",");
				
				List<String> categoryProductIdsStrNewList = new ArrayList<String>();
				List<String> categoryProductNamesStrNewList = new ArrayList<String>();
				
				String categoryProductIdsStrNew="";
				String categoryProductNamesStrNew="";
				//对来的产品串进行遍历
				String productIdnow = productId+"";
				//该类的产品串进行
				for(int j =0;j<categoryProductIdsArr.length;j++){
					//
					String productOldIdOneStr = categoryProductIdsArr[j];
					String productOldNamesStr = categoryProductNamesArr[j];
					
					if(productOldIdOneStr.equals(productIdnow)){
						System.out.println("一致就不存了");
					}else{
						//移除本个后的该类下全部产品
						categoryProductIdsStrNewList.add(productOldIdOneStr);
						categoryProductIdsStrNew+=productOldIdOneStr+",";
						
						categoryProductNamesStrNewList.add(productOldNamesStr);
						categoryProductNamesStrNew+=productOldNamesStr+",";
					}
				}
				//判断是不是空了，空了的话，不移除最后一个字符","
				if(categoryProductIdsStrNew.length()>0){
					categoryProductIdsStrNew=categoryProductIdsStrNew.substring(0,categoryProductIdsStrNew.length()-1);
					categoryProductNamesStrNew=categoryProductNamesStrNew.substring(0,categoryProductNamesStrNew.length()-1);
				}
				//封装参数
				MlbackCategory mlbackCategoryRemoveOld = new MlbackCategory();
				mlbackCategoryRemoveOld.setCategoryId(categoryIdsInt);
				mlbackCategoryRemoveOld.setCategoryProductIds(categoryProductIdsStrNew);
				mlbackCategoryRemoveOld.setCategoryProductNames(categoryProductNamesStrNew);
				//执行更新
				mlbackCategoryService.updateByPrimaryKeySelective(mlbackCategoryRemoveOld);
			}
		}
	}

	/**6.0	UseNow	0505
	 * MlbackProduct	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackProduct mlbackProduct){
		//接收id信息
		Integer productIdInt = mlbackProduct.getProductId();
		mlbackProductService.deleteByPrimaryKey(productIdInt);
		//System.out.println("操作说明：管理员delete success-productIdInt:"+productIdInt);
		return Msg.success().add("resMsg", "delete success");
	}
	
	
	/**
	 * 7.0	UseNow	0505
	 * 查看单个产品的详情
	 * @param productId
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackProductDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackProductDetail(@RequestParam(value = "productId") Integer productId){
		//接受信息
		MlbackProduct mlbackProductReq = new MlbackProduct();
		mlbackProductReq.setProductId(productId);
		List<MlbackProduct> mlbackProductResList =mlbackProductService.selectMlbackProduct(mlbackProductReq);
		MlbackProduct mlbackProductOne = new MlbackProduct();
		if(mlbackProductResList.size()>0){
			//如果用这个id查到,就拿出来.
			mlbackProductOne = mlbackProductResList.get(0);
		}else{
			//如果用这个id没查到,就取出当前所有产品最新上的那款.
			mlbackProductResList = mlbackProductService.selectMlbackProductGetAll();
			mlbackProductOne = mlbackProductResList.get(0);
		}
		//System.out.println("操作说明：查询-mlbackProductOne:"+mlbackProductOne);
		return Msg.success().add("resMsg", "查单个产品详情完毕").add("mlbackProductOne", mlbackProductOne);
	}
	
	/**
	 * 7.1	UseNow	0505
	 * 查询单个产品信息(无富文本描述)
	 * @param productId
	 * @return 
	 */
	@RequestMapping(value="/getOneProductSimple",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneProductSimple(@RequestParam(value = "productId") Integer productId){
		//接受信息
		MlbackProduct mlbackProductReq = new MlbackProduct();
		mlbackProductReq.setProductId(productId);
		List<MlbackProduct> mlbackProductResList =mlbackProductService.selectMlbackProductSimple(mlbackProductReq);
		MlbackProduct mlbackProductOne = new MlbackProduct();
		if(mlbackProductResList.size()>0){
			//如果用这个id查到,就拿出来.
			mlbackProductOne = mlbackProductResList.get(0);
		}else{
			//如果用这个id没查到,就取出当前所有产品最新上的那款.
			mlbackProductResList = mlbackProductService.selectMlbackProductGetAll();
			mlbackProductOne = mlbackProductResList.get(0);
		}
		//System.out.println("操作说明：查询-mlbackProductOne:"+mlbackProductOne);
		return Msg.success().add("resMsg", "查看单个产品详情完毕").add("mlbackProductOne", mlbackProductOne);
	}
	
	/**
	 * 8.0	UseNow	0505
	 * 通过产品名查看单条产品的详情
	 * @param productId
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackProductDetailByPName",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackProductDetailByPName(@RequestParam(value = "productName") String productName){
		//接受信息
		MlbackProduct mlbackProductReq = new MlbackProduct();
		mlbackProductReq.setProductName(productName);
		List<MlbackProduct> mlbackProductResList =mlbackProductService.selectMlbackProductByPName(mlbackProductReq);
		MlbackProduct mlbackProductOne = new MlbackProduct();
		if(mlbackProductResList.size()>0){
			mlbackProductOne =mlbackProductResList.get(0);
		}
		//System.out.println("操作说明：通过产品名查询单个产品-mlbackProductOne:"+mlbackProductOne);
		return Msg.success().add("resMsg", "查-单条产品详情完毕").add("mlbackProductOne", mlbackProductOne);
	}
	
	/**
	 * 9.0	useOn	0505
	 * 推荐//热销//爆款
	 * @param MlbackProduct
	 * @return 
	 */
	@RequestMapping(value="/getMlbackProductLableList",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackProductLableList(@RequestParam(value = "productLable") Integer productLable){
		
		//接受信息
		MlbackProduct mlbackProductReq = new MlbackProduct();
		mlbackProductReq.setProductLable(productLable);
		List<MlbackProduct> mlbackProductResList  = new ArrayList<MlbackProduct>();
		List<MlbackProduct> mlbackProductResLimitList =mlbackProductService.selectMlbackProductByLable(mlbackProductReq);
		if(mlbackProductResLimitList.size()>19){
			for(int i=0;i<20;i++){
				mlbackProductResList.add(mlbackProductResLimitList.get(i));
			}
		}else{
			mlbackProductResList = mlbackProductResLimitList;
		}
		return Msg.success().add("resMsg", "查-该标签下的产品List完毕")
					.add("mlbackProductResList", mlbackProductResList);
	}
	
	/**
	 * 10.0	UseNow	0505
	 * 通过，类id查询，该类下的全部产品
	 * @param MlbackProduct
	 * @return 
	 */
	@RequestMapping(value="/getMlbackProductByparentCategoryIdList",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackProductByparentCategoryIdList(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackProduct mlbackProduct){
		
		Integer productCategoryid = mlbackProduct.getProductCategoryid();
		//接受信息
		MlbackProduct mlbackProductReq = new MlbackProduct();
		mlbackProductReq.setProductCategoryid(productCategoryid);
		List<MlbackProduct> mlbackProductResList =mlbackProductService.selectMlbackProductByProductCategoryid(mlbackProductReq);
		return Msg.success().add("resMsg", "查看该母category下的全部产品")
					.add("mlbackProductResList", mlbackProductResList);
	}
	
	
	/**
	 * 10.1	UseNow	0505
	 * 通过，类id查询，该类下的全部产品
	 * @param MlbackProduct
	 * @return 
	 */
	@RequestMapping(value="/getMlbackProductByparentCategoryIdListNew",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackProductByparentCategoryIdListNew(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackProduct mlbackProduct){
		
		Integer categoryid = mlbackProduct.getProductCategoryid();
		
		MlbackCategory mlbackCategory = new MlbackCategory();
		
		MlbackCategory mlbackCategoryOne = new MlbackCategory();
		
		mlbackCategory.setCategoryId(categoryid);
		
	 	List<MlbackCategory> mlbackCategoryList = mlbackCategoryService.selectMlbackCategory(mlbackCategory);
	 	
	 	mlbackCategoryOne = mlbackCategoryList.get(0);
	 	
	 	String categoryProductIds = mlbackCategoryOne.getCategoryProductIds();

	 	List<MlbackProduct> mlbackProductResList = new ArrayList<MlbackProduct>();
	 	
	 	if(categoryProductIds==null||categoryProductIds.length()==0){
	 		System.out.println("ProductResList的长度："+mlbackProductResList.size());
	 	}else{
	 		String categoryProductIdsArr [] = categoryProductIds.split(",");
	 		//遍历查询product产品
	 		String proIdStr = "";
	 		Integer proIdInt = 0;
	 		
	 		MlbackProduct mlbackProductReq = new MlbackProduct();
	 		MlbackProduct mlbackProductResOne = new MlbackProduct();
	 		List<MlbackProduct> mlbackProductList = new ArrayList<MlbackProduct>();
	 		for(int i=0;i<categoryProductIdsArr.length;i++){
	 			proIdStr = categoryProductIdsArr[i];
	 			proIdInt = Integer.parseInt(proIdStr);
	 		 	mlbackProductReq.setProductId(proIdInt);
	 		 	mlbackProductList = mlbackProductService.selectMlbackProduct(mlbackProductReq);
	 		 	if(mlbackProductList.size()>0){
	 		 		mlbackProductResOne = mlbackProductList.get(0);
	 		 		Integer proStatus = mlbackProductResOne.getProductStatus();
	 		 		if(proStatus==1){	//0不上架	1上架
	 		 			mlbackProductResList.add(mlbackProductResOne);
	 		 		}
	 		 	}
	 		}
	 	}
		return Msg.success().add("resMsg", "查看该母category下的全部产品")
					.add("mlbackProductResList", mlbackProductResList);
	}
	
	/**
	 * 10.2	UseNow	0505
	 * 通过，类id查询，该类下的全部产品
	 * @param MlbackProduct
	 * @return 
	 */
	@RequestMapping(value="/getMlbackProductByparentCategoryIdListAndpn",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackProductByparentCategoryIdListAndpn(HttpServletResponse rep,HttpServletRequest res,@RequestParam(value = "categoryId") Integer categoryId,@RequestParam(value = "pn", defaultValue = "1") Integer pn){
		
		MlbackCategory mlbackCategoryReq = new MlbackCategory();
		
		MlbackCategory mlbackCategoryOne = new MlbackCategory();
		
		Integer categoryIdInt = categoryId;
		//System.out.println("categoryIdInt:"+categoryIdInt);
		
		mlbackCategoryReq.setCategoryId(categoryIdInt);
		
		mlbackCategoryOne = mlbackCategoryService.selectMlbackCategoryById(mlbackCategoryReq);
	 	
	 	String categoryProductIds = null;
	 	if(mlbackCategoryOne==null){
	 		categoryProductIds = null;
	 	}else{
	 		categoryProductIds = mlbackCategoryOne.getCategoryProductIds();
	 	}
	 	
	 	List<MlbackProduct> mlbackProductResList = new ArrayList<MlbackProduct>();
	 	
	 	List<MlbackProduct> returnList = new ArrayList<MlbackProduct>();
	 	
	 	PageInfo page = new PageInfo();
	 	
	 	if(categoryProductIds==null||categoryProductIds.length()==0){
	 		System.out.println("ProductResList的长度："+mlbackProductResList.size());
	 	}else{
	 		
	 		String categoryProductIdsArr [] = categoryProductIds.split(",");
	 		//遍历查询product产品
	 		String proIdStr = "";
	 		Integer proIdInt = 0;
	 		
	 		MlbackProduct mlbackProductReq = new MlbackProduct();
	 		MlbackProduct mlbackProductResOne = new MlbackProduct();
	 		List<MlbackProduct> mlbackProductList = new ArrayList<MlbackProduct>();
	 		for(int i=0;i<categoryProductIdsArr.length;i++){
	 			proIdStr = categoryProductIdsArr[i];
	 			proIdInt = Integer.parseInt(proIdStr);
	 		 	mlbackProductReq.setProductId(proIdInt);
	 		 	mlbackProductList = mlbackProductService.selectMlbackProduct(mlbackProductReq);
	 		 	if(mlbackProductList.size()>0){
	 		 		mlbackProductResOne = mlbackProductList.get(0);
	 		 		Integer proStatus = mlbackProductResOne.getProductStatus();
	 		 		if(proStatus==1){	//0不上架	1上架
	 		 			mlbackProductResList.add(mlbackProductResOne);
	 		 		}
	 		 	}
	 		}
			
	 		returnList = getPropnList(mlbackProductResList,pn);
	 		
	 		page = getPageInfo(mlbackProductResList,pn);
	 		
	 	}
		return Msg.success().add("resMsg", "查看该母category下的全部产品")
					.add("mlbackProductResList", returnList).add("pageInfo", page);
	}
	
	@SuppressWarnings("deprecation")
	private PageInfo getPageInfo(List<MlbackProduct> mlbackProductResList,Integer pn) {
		int PagNum = 25;
		PageHelper.startPage(pn, PagNum);
		PageInfo page = new PageInfo(mlbackProductResList, PagNum);
		
		int EndRow = (pn-1)*PagNum+PagNum;
		int lastPage=(mlbackProductResList.size()/PagNum)+1;
		int firstPage = 1;
		boolean hasNextPage = true;
		boolean hasPreviousPage=true;
		boolean isFirstPage = true;
		boolean isLastPage = true;
		//计算上半部分参数
		if(pn==lastPage&&lastPage>1){
			isFirstPage = false;
			isLastPage = true;
			hasPreviousPage=true;
			hasNextPage = false;
			EndRow = mlbackProductResList.size();
		}else if(pn==lastPage&&lastPage==1){
			isFirstPage = true;
			isLastPage = true;
			hasPreviousPage=false;
			hasNextPage = false;
			EndRow = mlbackProductResList.size();
		}else if(pn==1&&lastPage>1){
			isFirstPage = true;
			isLastPage = false;
			hasPreviousPage=false;
			hasNextPage = true;
			EndRow = pn*PagNum;
		}else{
			isFirstPage = false;
			isLastPage = false;
			hasPreviousPage=true;
			hasNextPage = true;
			EndRow = pn*PagNum;
		}
		
		int navigateFirstPage = 1;
		int navigateLastPage = lastPage;
		
		int navigatePages = PagNum;
		
		int[] navigatepageNums = new int[lastPage];
		for(int i=0;i<lastPage;i++){
			navigatepageNums[i]=i+1;
		}
		page.setNavigatepageNums(navigatepageNums);
		//计算下半部分参数
		int nextPage =0;
		int pageNum = 0;
		int pageSize = 0;
		int pages = 0;
		int prePage = 0;
		int size = 0;
		int startRow = 0;
		startRow= (pn-1)*PagNum+1;
		
		pageNum = pn;
		pageSize = PagNum;
		pages = lastPage;
		if(pn==lastPage&&lastPage>1){
			nextPage=0;
			prePage = pn-1;
			size = mlbackProductResList.size()-(pn-1)*PagNum;
		}else if (pn==lastPage&&lastPage==1){
			nextPage=0;
			prePage = 0;
			size = mlbackProductResList.size();
		}else if(pn==1&&lastPage>1){
			nextPage=pn+1;
			prePage=0;
			size = PagNum;
		}else{
			nextPage=pn+1;
			prePage = pn-1;
			size = PagNum;
		}
		//封装参数
		page.setEndRow(EndRow);
		page.setFirstPage(firstPage);
		page.setHasNextPage(hasNextPage);
		page.setHasPreviousPage(hasPreviousPage);
		page.setIsFirstPage(isFirstPage);
		page.setIsLastPage(isLastPage);
		page.setLastPage(lastPage);
		page.setNavigateFirstPage(navigateFirstPage);
		page.setNavigateLastPage(navigateLastPage);
		page.setNavigatePages(navigatePages);
		page.setNextPage(nextPage);
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		page.setPages(pages);
		page.setPrePage(prePage);
		page.setSize(size);
		page.setStartRow(startRow);
		page.setTotal(mlbackProductResList.size());
		//System.out.println(page);
		return page;
	}

	private List<MlbackProduct> getPropnList(List<MlbackProduct> mlbackProductResList,Integer pn) {
		
		List<MlbackProduct> mlfrontProductResListPage = new ArrayList<MlbackProduct>();
		List<MlbackProduct> mlfrontProductResreturn = new ArrayList<MlbackProduct>();
		//分页数据获取完毕
		int PagNum = 25;
		PageHelper.startPage(pn, PagNum);
		PageInfo page = new PageInfo(mlbackProductResList, PagNum);
		//从中获取本次展示的25条信息
		mlfrontProductResListPage = page.getList();

		//开始对5条信息进行遍历，获取每条的详情照片
		Integer StartIndex = (pn-1)*PagNum;
		Integer lengthAll = mlfrontProductResListPage.size();
		Integer EndIndex = 0;
		if(StartIndex+25>lengthAll){
			EndIndex = lengthAll;
		}else{
			EndIndex = StartIndex+25;
		}
		
		for(int i=StartIndex;i<EndIndex;i++){
			MlbackProduct mlfrontProductOne = mlfrontProductResListPage.get(i);
			mlfrontProductResreturn.add(mlfrontProductOne);
		}
		return mlfrontProductResreturn;
	}

	/**
	 * 11.0	UseNow	0527
	 * 通过，类id,productColor,查询满足条件的全部产品
	 * @param productCategoryid,productColor
	 * @return 
	 */
	@RequestMapping(value="/getMlbackProductByproductColorAndCategoryIdList",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackProductByproductColorList(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackProduct mlbackProduct){
		
		Integer productCategoryid = mlbackProduct.getProductCategoryid();
		
		String productColor = mlbackProduct.getProductColor();
		
		MlbackProduct mlbackProductReq = new MlbackProduct();
		//接受信息
		List<MlbackProduct> mlbackProductResList = new ArrayList<MlbackProduct>();
		if(productCategoryid>0){
			mlbackProductReq.setProductCategoryid(productCategoryid);
			if(productColor!=null) {
				System.out.println(productColor.length());
				if(productColor.length()>0){
					mlbackProductReq.setProductColor(productColor);
				}
			}
			mlbackProductResList =mlbackProductService.selectMlbackProductByProductCategoryid(mlbackProductReq);
			return Msg.success().add("resMsg", "查看该母category下的全部产品")
					.add("mlbackProductResList", mlbackProductResList);
		}else{
			return Msg.success().add("resMsg", "查看该母category下的没有产品")
					.add("mlbackProductResList", mlbackProductResList);
		}
	} 
	
	/**
	 * 12.0	UseNow	0527
	 * 通过，类id,productColor,查询满足条件的全部产品
	 * @param productCategoryid,productColor
	 * @return 
	 */
	@RequestMapping(value="/getMlbackProductAllList",method=RequestMethod.GET)
	@ResponseBody
	public Msg getMlbackProductAllList(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackProduct mlbackProductReq = new MlbackProduct();
		
		mlbackProductReq.setProductStatus(1);//'已上架' : '未上架'
		//接受信息
		List<MlbackProduct> mlbackProductResList = new ArrayList<MlbackProduct>();
		
		mlbackProductResList =mlbackProductService.selectMlbackProductByStatus(mlbackProductReq);
		return Msg.success().add("resMsg", "查看上架状态的下的全部产品")
					.add("mlbackProductResList", mlbackProductResList);
		
	}
	
	/**
	 * 13	UseNow	0505
	 * 前台移动端获取详情页面mfront/productDetails
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/toSearchPage",method=RequestMethod.GET)
	public String toSearchPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "seaProductName") String seaProductName) throws Exception{
		//接收传递进来的参数
		String seaProductNameReq = seaProductName;
		//放回响应域中
		res.setAttribute("productName", seaProductNameReq);
		//放回session域中
		session.setAttribute("productName", seaProductNameReq);
		//判断请求设备
		String ifMobile = IfMobileUtils.isMobileOrPc(rep, res);
		
		if(ifMobile.equals("1")){
			//1wap
			return "mfront/searchproductlist";
		}else{
			//0PC
			return "front/searchproductlist";
		}
	}
	
	/**
	  * 14.0 UseNow 0505
	  * 通过产品名模糊搜索
	  * @param productId
	  * @return 
	  */
	 @RequestMapping(value="/searchProductLike",method=RequestMethod.POST)
	 @ResponseBody
	 public Msg searchProductLike(@RequestParam(value = "productName") String productName){
	  //接受信息
	  MlbackProduct mlbackProductReq = new MlbackProduct();
	  mlbackProductReq.setProductName(productName);
	  //System.out.println("操作说明:客户搜索的产品名字productName:"+productName);
	  List<MlbackProduct> mlbackProductResList =mlbackProductService.selectMlbackProductLike(mlbackProductReq);
	  List<MlbackProduct> mlbackProductResListnum =mlbackProductService.selectMlbackProductLikeNum(mlbackProductReq);
	  Integer num = mlbackProductResListnum.size();
	  //System.out.println("操作说明:客户搜索的产品名,查询结果mlbackProductResListnum:"+num);
	  return Msg.success().add("resMsg", "产品名模糊搜索完毕")
	     .add("mlbackProductResList", mlbackProductResList).add("mlbackProductResListnum", num).add("productName", productName);
	 }
	
}