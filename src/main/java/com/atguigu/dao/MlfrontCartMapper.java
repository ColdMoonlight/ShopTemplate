package com.atguigu.dao;

import com.atguigu.bean.MlfrontCart;
import com.atguigu.bean.MlfrontCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MlfrontCartMapper {
    long countByExample(MlfrontCartExample example);

    int deleteByExample(MlfrontCartExample example);

    int insert(MlfrontCart record);

    List<MlfrontCart> selectByExample(MlfrontCartExample example);

    MlfrontCart selectByPrimaryKey(Integer cartId);

    int updateByExampleSelective(@Param("record") MlfrontCart record, @Param("example") MlfrontCartExample example);

    int updateByExample(@Param("record") MlfrontCart record, @Param("example") MlfrontCartExample example);

    int updateByPrimaryKey(MlfrontCart record);
    
    
    //插入新类目00
    int insertSelective(MlfrontCart record);
    //删除本条00
    int deleteByPrimaryKey(Integer cartId);
    //更新本条00
    int updateByPrimaryKeySelective(MlfrontCart record);
    //查询后台登陆用户00
    List<MlfrontCart> selectMlfrontCartByConditionS(MlfrontCart record);
    //查询全部登陆用户00
	List<MlfrontCart> selectMlfrontCartGetAll();
	//查询ip查询购物车
	List<MlfrontCart> selectMlfrontCartByIp(MlfrontCart record);
	//查询UidAndStatus查询购物车
	List<MlfrontCart> selectMlfrontCartByUidAndStatus(MlfrontCart record);
	//查询id查询购物车
	MlfrontCart selectMlfrontCartByCartId(Integer cartId);
	//通过时间查询全部登陆用户00
	List<MlfrontCart> selectMlfrontCartByDate(MlfrontCart mlfrontCart);

	List<MlfrontCart> selectMlfrontCartGetAllHundred();
    
}