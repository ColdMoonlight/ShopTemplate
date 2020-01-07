package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackFootNav;
import com.atguigu.dao.MlbackFootNavMapper;

@Service
public class MlbackFootNavService {
	
	
	@Autowired
	MlbackFootNavMapper mlbackFootNavMapper;


	
	/**1
	 * @author Shinelon
	 * @param MlbackFootNav
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlbackFootNav mlbackFootNav) {
		int intReslut = mlbackFootNavMapper.insertSelective(mlbackFootNav);
		return intReslut;
	}
	
	/**2
	 * @author Shinelon
	 * @param MlbackFootNav
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int footnavId) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackFootNavMapper.deleteByPrimaryKey(footnavId);
		return intReslut;
	}

	/**3
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackFootNav mlbackFootNav) {
		// TODO Auto-generated method stub
		int  intReslut = mlbackFootNavMapper.updateByPrimaryKeySelective(mlbackFootNav);
		return intReslut;
	}
	
	
	
	/**4
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackFootNav> selectMlbackFootNavOneAllById(MlbackFootNav mlbackFootNav) {
		List<MlbackFootNav>  mlbackAreafreightList = mlbackFootNavMapper.selectMlbackFootNavOneAllById(mlbackFootNav);
		return mlbackAreafreightList;
	}
	
	/**5
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackFootNav> selectMlbackFootNavById(MlbackFootNav mlbackFootNav) {
		List<MlbackFootNav>  mlbackFootNavList = mlbackFootNavMapper.selectMlbackFootNavById(mlbackFootNav);
		return mlbackFootNavList;
	}
	
	/**6
	 * @author Shinelon
	 * @param MlbackProduct
	 * @exception 查看全部的地址信息
	 * 
	 * */
	public List<MlbackFootNav> selectMlbackFootNavAll() {
		List<MlbackFootNav>  mlbackAreafreightList = mlbackFootNavMapper.selectMlbackFootNavGetAll();
		return mlbackAreafreightList;
	}
	
	/**7
	 * @author Shinelon
	 * @param MlbackFootNav
	 * @exception 查看全部的地址信息
	 * 
	 * */
	public List<MlbackFootNav> selectMlbackFootNavAllIfShow(MlbackFootNav mlbackFootNav) {
		List<MlbackFootNav>  mlbackAreafreightList = mlbackFootNavMapper.selectMlbackFootNavAllIfShow(mlbackFootNav);
		return mlbackAreafreightList;
	}

	public List<MlbackFootNav> selectMlbackFootNavAllSimpleIfShow(MlbackFootNav mlbackFootNav) {
		List<MlbackFootNav>  mlbackAreafreightList = mlbackFootNavMapper.selectMlbackFootNavAllSimpleIfShow(mlbackFootNav);
		return mlbackAreafreightList;
	}
	
}
