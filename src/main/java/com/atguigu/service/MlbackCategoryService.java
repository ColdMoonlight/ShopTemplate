package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.GroupDisplay;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.UserWork;
import com.atguigu.dao.MlbackAdminMapper;
import com.atguigu.dao.MlbackCategoryMapper;


@Service
public class MlbackCategoryService {
	
	
	@Autowired
	MlbackCategoryMapper mlbackCategoryMapper;

	/**
	 * @author Shinelon
	 * @param MlbackCategory
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public List<MlbackCategory> selectMlbackCategory(MlbackCategory mlbackCategory) {
		List<MlbackCategory>  mlbackCategoryList = mlbackCategoryMapper.selectMlbackCategory(mlbackCategory);
		return mlbackCategoryList;
	}
	
	/**
	 * @author Shinelon
	 * @param MlbackCategory
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackCategory mlbackCategory) {
		int intReslut = mlbackCategoryMapper.insertSelective(mlbackCategory);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackCategory> selectMlbackCategoryGetAll() {
		List<MlbackCategory>  mlbackCategoryList = mlbackCategoryMapper.selectMlbackCategoryGetAll();
		return mlbackCategoryList;
	}
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackCategory> selectMlbackCategoryGetAllByParentId() {
		List<MlbackCategory>  mlbackCategoryList = mlbackCategoryMapper.selectMlbackCategoryGetAllByParentId();
		return mlbackCategoryList;
	}
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int categoryId) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackCategoryMapper.deleteByPrimaryKey(categoryId);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackCategory mlbackCategory) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackCategoryMapper.updateByPrimaryKeySelective(mlbackCategory);
		return intReslut;
	}

}
