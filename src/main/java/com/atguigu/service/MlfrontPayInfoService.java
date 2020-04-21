package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.dao.MlfrontPayInfoMapper;


@Service
public class MlfrontPayInfoService {
	
	@Autowired
	MlfrontPayInfoMapper mlfrontPayInfoMapper;
	
	/**1
	 * @author Shinelon
	 * @param MlfrontAddress
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlfrontPayInfo mlfrontPayInfo) {
		int intReslut = mlfrontPayInfoMapper.insertSelective(mlfrontPayInfo);
		return intReslut;
	}
	
	/**2
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int payinfoId) {
		// TODO Auto-generated method stub
		int  intReslut = mlfrontPayInfoMapper.deleteByPrimaryKey(payinfoId);
		return intReslut;
	}
	
	/**3
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlfrontPayInfo mlfrontPayInfo) {
		// TODO Auto-generated method stub
		int  intReslut = mlfrontPayInfoMapper.updateByPrimaryKeySelective(mlfrontPayInfo);
		return intReslut;
	}
	
	/**4
	 * @author Shinelon
	 * @param MlbackProduct
	 * @exception 查看全部的地址信息
	 * 
	 * */
	public List<MlfrontPayInfo> selectMlfrontPayInfoAll() {
		List<MlfrontPayInfo>  mlfrontPayInfoList = mlfrontPayInfoMapper.selectMlfrontPayInfoGetAll();
		return mlfrontPayInfoList;
	}
	
	/**5
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlfrontPayInfo> selectMlfrontPayInfoById(MlfrontPayInfo mlfrontPayInfo) {
		List<MlfrontPayInfo>  mlfrontPayInfoList = mlfrontPayInfoMapper.selectMlfrontPayInfoById(mlfrontPayInfo);
		return mlfrontPayInfoList;
	}

	public List<MlfrontPayInfo> selectMlfrontPayInfoByDate(MlfrontPayInfo mlfrontPayInfo) {
		List<MlfrontPayInfo>  mlfrontPayInfoList = mlfrontPayInfoMapper.selectMlfrontPayInfoByDate(mlfrontPayInfo);
		return mlfrontPayInfoList;
	}
	
	public List<MlfrontPayInfo> selectHighPayInfoListBySearch(MlfrontPayInfo mlfrontPayInfoReq) {
	    List<MlfrontPayInfo>  mlfrontPayInfoList = mlfrontPayInfoMapper.selectHighPayInfoListBySearch(mlfrontPayInfoReq);
	    return mlfrontPayInfoList;
	}

	public List<MlfrontPayInfo> selectMlfrontPayInfoByMotifyTime(MlfrontPayInfo mlfrontPayInfoReq) {
		List<MlfrontPayInfo>  mlfrontPayInfoList = mlfrontPayInfoMapper.selectMlfrontPayInfoByMotifyTime(mlfrontPayInfoReq);
	    return mlfrontPayInfoList;
	}
	
}
