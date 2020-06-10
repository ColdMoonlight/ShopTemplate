package com.atguigu.dao;

import com.atguigu.bean.MlfrontCartItem;
import com.atguigu.bean.MlfrontCartItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MlfrontCartItemMapper {
    long countByExample(MlfrontCartItemExample example);

    int deleteByExample(MlfrontCartItemExample example);

    int insert(MlfrontCartItem record);

    List<MlfrontCartItem> selectByExample(MlfrontCartItemExample example);

    MlfrontCartItem selectByPrimaryKey(Integer cartitemId);

    int updateByExampleSelective(@Param("record") MlfrontCartItem record, @Param("example") MlfrontCartItemExample example);

    int updateByExample(@Param("record") MlfrontCartItem record, @Param("example") MlfrontCartItemExample example);

    int updateByPrimaryKey(MlfrontCartItem record);
    
    //插入新类目00
    int insertSelective(MlfrontCartItem record);
    //删除本条00
    int deleteByPrimaryKey(Integer cartitemId);
    //更新本条00
    int updateByPrimaryKeySelective(MlfrontCartItem record);
    //查询后台登陆用户00
    List<MlfrontCartItem> selectMlfrontCartItemByConditionS(MlfrontCartItem record);
    //查询单条后台登陆用户00
    List<MlfrontCartItem> selectMlfrontCartItemOne(MlfrontCartItem record);
    //查询全部登陆用户00
	List<MlfrontCartItem> selectMlfrontCartItemGetAll();
	
	//查询全部登陆用户00
	List<MlfrontCartItem> selectMlfrontCartItemById(MlfrontCartItem record);

	List<MlfrontCartItem> selectMlfrontCartItemGetAllHundred();
    
}