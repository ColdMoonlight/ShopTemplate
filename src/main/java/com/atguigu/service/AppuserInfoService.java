package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.AppuserInfo;
import com.atguigu.bean.Department;
import com.atguigu.bean.GroupDisplay;
import com.atguigu.dao.AppuserInfoMapper;
import com.atguigu.dao.DepartmentMapper;

@Service
public class AppuserInfoService {
	
	@Autowired
	private AppuserInfoMapper appuserInfoMapper;

	/**1.0
	 * @author Shinelon
	 * @param udid
	 * @exception ��ѯ�û��˺Ŷ���
	 * */
	public List<AppuserInfo> getAppuserInfoByConditions(AppuserInfo appuserInfo) {
		List<AppuserInfo>  appuserInfoListALl = appuserInfoMapper.getAppuserInfoByConditions(appuserInfo);
		return appuserInfoListALl;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception ��ѯ�˺��Ƿ����
	 * 
	 * */
	
	public List<AppuserInfo> getAppuserInfoAll() {
		// TODO Auto-generated method stub
		List<AppuserInfo>  appuserInfoListALl = appuserInfoMapper.getAppuserInfoAll();
		return appuserInfoListALl;
	}
	
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception ��ѯ�˺��Ƿ����
	 * */
	public int insertSelective(AppuserInfo appuserInfo) {
		// TODO Auto-generated method stub
		int  intReslut = appuserInfoMapper.insertSelective(appuserInfo);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception ��ѯ�˺��Ƿ����
	 * 
	 * */
//	public int deleteByPrimaryKey(int taskId) {
//		// TODO Auto-generated method stub
//		int  intReslut = groupDisplayMapper.deleteByPrimaryKey(taskId);
//		return intReslut;
//	}


//	public int updateByPrimaryKeySelective(GroupDisplay groupDisplay) {
//		// TODO Auto-generated method stub
//		int  intReslut = groupDisplayMapper.updateByPrimaryKeySelective(groupDisplay);
//		return intReslut;
//	}

}
