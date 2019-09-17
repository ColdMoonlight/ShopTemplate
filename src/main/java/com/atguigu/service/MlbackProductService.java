package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.GroupDisplay;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.UserWork;
import com.atguigu.dao.MlbackAdminMapper;
import com.atguigu.dao.MlbackCategoryMapper;
import com.atguigu.dao.MlbackProductMapper;


@Service
public class MlbackProductService {
	
	
	@Autowired
	MlbackProductMapper mlbackProductMapper;

	/**
	 * @author Shinelon
	 * @param MlbackProduct
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public List<MlbackProduct> selectMlbackProduct(MlbackProduct mlbackProduct) {
		List<MlbackProduct>  mlbackCategoryList = mlbackProductMapper.selectMlbackProduct(mlbackProduct);
		return mlbackCategoryList;
	}
	/**
	 * @author Shinelon
	 * @param MlbackProduct
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public List<MlbackProduct> selectMlbackProductByPName(MlbackProduct mlbackProduct) {
		List<MlbackProduct>  mlbackCategoryList = mlbackProductMapper.selectMlbackProductByPName(mlbackProduct);
		return mlbackCategoryList;
	}
	/**
	 * @author Shinelon
	 * @param MlbackProduct
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackProduct mlbackProduct) {
		int intReslut = mlbackProductMapper.insertSelective(mlbackProduct);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackProduct> selectMlbackProductGetAll() {
		List<MlbackProduct>  mlbackProductList = mlbackProductMapper.selectMlbackProductGetAll();
		return mlbackProductList;
	}
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int productId) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackProductMapper.deleteByPrimaryKey(productId);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackProduct mlbackProduct) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackProductMapper.updateByPrimaryKeySelective(mlbackProduct);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateMainFuImgById(MlbackProduct mlbackProduct) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackProductMapper.updateMainFuImgById(mlbackProduct);
		return intReslut;
	}
	
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public List<MlbackProduct> selectMlbackProductByLable(MlbackProduct mlbackProduct) {
		// TODO Auto-generated method stub
		List<MlbackProduct>  mlbackProductList = mlbackProductMapper.selectMlbackProductByLable(mlbackProduct);
		return mlbackProductList;
	}
	
	public List<MlbackProduct> selectMlbackProductByProductCategoryid(MlbackProduct mlbackProduct) {
		// TODO Auto-generated method stub
		List<MlbackProduct>  mlbackProductList = mlbackProductMapper.selectMlbackProductByProductCategoryid(mlbackProduct);
		return mlbackProductList;
	}
	
	public List<MlbackProduct> selectMlbackProductByStatus(MlbackProduct mlbackProduct) {
		// TODO Auto-generated method stub
		List<MlbackProduct>  mlbackProductList = mlbackProductMapper.selectMlbackProductByStatus(mlbackProduct);
		return mlbackProductList;
	}
	public MlbackProduct selectMlbackProductBySeo(MlbackProduct mlbackProductrepBySeo) {
		// TODO Auto-generated method stub
		MlbackProduct  mlbackProduct = mlbackProductMapper.selectMlbackProductBySeo(mlbackProductrepBySeo);
		return mlbackProduct;
	}
	public List<MlbackProduct> selectMlbackProductLike(MlbackProduct mlbackProduct) {
		// TODO Auto-generated method stub
		List<MlbackProduct>  mlbackProductList = mlbackProductMapper.selectMlbackProductLike(mlbackProduct);
		return mlbackProductList;
	}
	public List<MlbackProduct> selectMlbackProductLikeNum(MlbackProduct mlbackProduct) {
		List<MlbackProduct>  mlbackProductList = mlbackProductMapper.selectMlbackProductLikeNum(mlbackProduct);
		return mlbackProductList;
	}
	
	
	

}
