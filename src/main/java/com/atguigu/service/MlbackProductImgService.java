package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.GroupDisplay;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackProductImg;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.UserWork;
import com.atguigu.dao.MlbackAdminMapper;
import com.atguigu.dao.MlbackCategoryMapper;
import com.atguigu.dao.MlbackCouponMapper;
import com.atguigu.dao.MlbackProductImgMapper;
import com.atguigu.dao.MlbackProductMapper;
import com.atguigu.dao.MlfrontUserMapper;


@Service
public class MlbackProductImgService {
	
	@Autowired
	MlbackProductImgMapper mlbackProductImgMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackProductImg
	 * @exception add方法mlbackProductImg
	 * 
	 * */
	public int insertSelective(MlbackProductImg mlbackProductImg) {
		int intReslut = mlbackProductImgMapper.insertSelective(mlbackProductImg);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackProductImg
	 * @exception 删除本条MlbackProductImg
	 * 
	 * */
	public int deleteByPrimaryKey(int productimgId) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackProductImgMapper.deleteByPrimaryKey(productimgId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条MlbackProductImg
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackProductImg mlbackProductImg) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackProductImgMapper.updateByPrimaryKeySelective(mlbackProductImg);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param MlfrontUser
	 * @exception 查看MlbackProductImg是否存在
	 * 
	 * */
	public List<MlbackProductImg> selectMlbackProductImg(MlbackProductImg mlbackProductImg) {
		List<MlbackProductImg>  mlbackProductImgList = mlbackProductImgMapper.selectMlbackProductImgByConditionS(mlbackProductImg);
		return mlbackProductImgList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部MlbackProductImg信息
	 * 
	 * */
	public List<MlbackProductImg> selectMlbackProductImgGetAll() {
		List<MlbackProductImg>  mlbackProductImgList = mlbackProductImgMapper.selectMlbackProductImgGetAll();
		return mlbackProductImgList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部MlbackProductImg信息
	 * 
	 * */
	public List<MlbackProductImg> selectMlbackProductImgById(Integer productId) {
		List<MlbackProductImg>  mlbackProductImgList = mlbackProductImgMapper.selectMlbackProductImgByProductId(productId);
		return mlbackProductImgList;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部MlbackProductImg信息
	 * 
	 * */
	public List<MlbackProductImg> selectMlbackProductImgByPIdAndImgSort(MlbackProductImg mlbackProductImg) {
		List<MlbackProductImg>  mlbackProductImgList = mlbackProductImgMapper.selectMlbackProductImgByPIdAndImgSort(mlbackProductImg);
		return mlbackProductImgList;
	}
	
	
}
