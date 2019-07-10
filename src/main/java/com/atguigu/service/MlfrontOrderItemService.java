package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.GroupDisplay;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.UserWork;
import com.atguigu.dao.MlbackAdminMapper;
import com.atguigu.dao.MlbackAreafreightMapper;
import com.atguigu.dao.MlbackCategoryMapper;
import com.atguigu.dao.MlbackProductMapper;
import com.atguigu.dao.MlfrontOrderItemMapper;
import com.atguigu.dao.MlfrontOrderMapper;


@Service
public class MlfrontOrderItemService {
	
	

	@Autowired
	MlfrontOrderItemMapper mlfrontOrderItemMapper;

	/**1
	 * @author Shinelon
	 * @param MlfrontOrderItem
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlfrontOrderItem mlfrontOrderItem) {
		int intReslut = mlfrontOrderItemMapper.insertSelective(mlfrontOrderItem);
		return intReslut;
	}
	
	/**2
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int orderitemId) {
		// TODO Auto-generated method stub
		int  intReslut = mlfrontOrderItemMapper.deleteByPrimaryKey(orderitemId);
		return intReslut;
	}

	/**3
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlfrontOrderItem mlfrontOrderItem) {
		// TODO Auto-generated method stub
		int  intReslut = mlfrontOrderItemMapper.updateByPrimaryKeySelective(mlfrontOrderItem);
		return intReslut;
	}
	
	/**4
	 * @author Shinelon
	 * @param MlfrontOrder
	 * @exception 查看全部的地址信息
	 * 
	 * */
	public List<MlfrontOrderItem> selectMlfrontOrderItemAll() {
		List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemMapper.selectMlfrontOrderItemGetAll();
		return mlfrontOrderItemList;
	}
	
	/**5
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlfrontOrderItem> selectMlfrontOrderItemById(MlfrontOrderItem mlfrontOrderItem) {
		List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemMapper.selectMlfrontOrderItemById(mlfrontOrderItem);
		return mlfrontOrderItemList;
	}
	
	/**5
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlfrontOrderItem> selectMlfrontOrderItemByOrderId(MlfrontOrderItem mlfrontOrderItem) {
		List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemMapper.selectMlfrontOrderItemByOrderId(mlfrontOrderItem);
		return mlfrontOrderItemList;
	}
	
	/**5
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlfrontOrderItem> selectMlfrontOrderItemByNowTime(String nowTime) {
		List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemMapper.selectMlfrontOrderItemByNowTime(nowTime);
		return mlfrontOrderItemList;
	}
	
	
}
