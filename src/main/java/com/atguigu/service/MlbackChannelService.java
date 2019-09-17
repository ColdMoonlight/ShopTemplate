package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.GroupDisplay;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackChannel;
import com.atguigu.bean.UserWork;
import com.atguigu.dao.MlbackAdminMapper;
import com.atguigu.dao.MlbackChannelMapper;


@Service
public class MlbackChannelService {
	
	
	@Autowired
	MlbackChannelMapper MlbackChannelMapper;

	/**
	 * @author Shinelon
	 * @param MlbackChannel
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public List<MlbackChannel> selectMlbackChannelById(MlbackChannel MlbackChannel) {
		List<MlbackChannel>  MlbackChannelList = MlbackChannelMapper.selectMlbackChannelById(MlbackChannel);
		return MlbackChannelList;
	}
	
	/**
	 * @author Shinelon
	 * @param MlbackChannel
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackChannel MlbackChannel) {
		int intReslut = MlbackChannelMapper.insertSelective(MlbackChannel);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackChannel> selectMlbackChannelGetAll() {
		List<MlbackChannel>  MlbackChannelList = MlbackChannelMapper.selectMlbackChannelGetAll();
		return MlbackChannelList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int categoryId) {
		// TODO Auto-generated method stub
		int  intReslut = MlbackChannelMapper.deleteByPrimaryKey(categoryId);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackChannel MlbackChannel) {
		// TODO Auto-generated method stub
		int  intReslut = MlbackChannelMapper.updateByPrimaryKeySelective(MlbackChannel);
		return intReslut;
	}

}
