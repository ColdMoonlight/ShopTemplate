package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.GroupDisplay;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.UserWork;
import com.atguigu.dao.MlbackAdminMapper;
import com.atguigu.dao.MlbackCategoryMapper;
import com.atguigu.dao.MlbackCouponMapper;
import com.atguigu.dao.MlbackProductMapper;
import com.atguigu.dao.MlfrontUserMapper;


@Service
public class MlbackCouponService {
	
	@Autowired
	MlbackCouponMapper mlbackCouponMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackCoupon
	 * @exception add方法mlbackCoupon
	 * 
	 * */
	public int insertSelective(MlbackCoupon mlbackCoupon) {
		int intReslut = mlbackCouponMapper.insertSelective(mlbackCoupon);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackCoupon
	 * @exception 删除本条mlbackCoupon
	 * 
	 * */
	public int deleteByPrimaryKey(int couponId) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackCouponMapper.deleteByPrimaryKey(couponId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条mlbackCoupon
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackCoupon mlbackCoupon) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackCouponMapper.updateByPrimaryKeySelective(mlbackCoupon);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param MlfrontUser
	 * @exception 查看mlbackCoupon是否存在
	 * 
	 * */
	public List<MlbackCoupon> selectMlbackCoupon(MlbackCoupon mlbackCoupon) {
		List<MlbackCoupon>  mlbackCouponList = mlbackCouponMapper.selectMlbackCouponByConditionS(mlbackCoupon);
		return mlbackCouponList;
	}
	/**
	 * @author Shinelon
	 * @param MlfrontUser
	 * @exception 查看mlbackCoupon是否存在
	 * 
	 * */
	public List<MlbackCoupon> selectMlbackCouponBYCode(MlbackCoupon mlbackCoupon) {
		List<MlbackCoupon>  mlbackCouponList = mlbackCouponMapper.selectMlbackCouponBYCode(mlbackCoupon);
		return mlbackCouponList;
	}
	

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部mlbackCoupon信息
	 * 
	 * */
	public List<MlbackCoupon> selectMlbackCouponGetAll() {
		List<MlbackCoupon>  mlbackCouponList = mlbackCouponMapper.selectMlbackCouponGetAll();
		return mlbackCouponList;
	}

	public List<MlbackCoupon> selectMlbackCouponBYAreaNum(MlbackCoupon mlbackCoupon) {
		List<MlbackCoupon>  mlbackCouponList = mlbackCouponMapper.selectMlbackCouponBYAreaNum(mlbackCoupon);
		return mlbackCouponList;
	}

}
