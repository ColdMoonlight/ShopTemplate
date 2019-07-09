package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.GroupDisplay;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackShowArea;
import com.atguigu.bean.UserWork;
import com.atguigu.dao.MlbackAdminMapper;
import com.atguigu.dao.MlbackAreafreightMapper;
import com.atguigu.dao.MlbackCategoryMapper;
import com.atguigu.dao.MlbackProductMapper;
import com.atguigu.dao.MlbackShowAreaMapper;


@Service
public class MlbackShowAreaService {
	
	
	@Autowired
	MlbackShowAreaMapper mlbackShowAreaMapper;

	/**1
	 * @author Shinelon
	 * @param MlbackShowArea
	 * @exception 查看全部的地址信息
	 * 
	 * */
	public int insertSelective(MlbackShowArea mlbackShowArea) {
		int intReslut = mlbackShowAreaMapper.insertSelective(mlbackShowArea);
		return intReslut;
	}
	
	/**2
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int showareaId) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackShowAreaMapper.deleteByPrimaryKey(showareaId);
		return intReslut;
	}
	
	
	/**3
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public int updateByPrimaryKeySelective(MlbackShowArea mlbackShowArea) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackShowAreaMapper.updateByPrimaryKeySelective(mlbackShowArea);
		return intReslut;
	}
	
	/**4
	 * @author Shinelon
	 * @param MlbackProduct
	 * @exception add方法用户信息是否存在
	 * */
	public List<MlbackShowArea> selectMlbackShowAreaAll() {
		List<MlbackShowArea>  mlbackShowAreaList = mlbackShowAreaMapper.selectMlbackShowAreaGetAll();
		return mlbackShowAreaList;
	}
	
	/**5
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackShowArea> selectMlbackShowAreaById(MlbackShowArea mlbackShowArea) {
		List<MlbackShowArea>  mlbackShowAreaList = mlbackShowAreaMapper.selectMlbackShowAreaById(mlbackShowArea);
		return mlbackShowAreaList;
	}
	
}
