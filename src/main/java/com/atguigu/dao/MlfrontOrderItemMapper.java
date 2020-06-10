package com.atguigu.dao;

import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MlfrontOrderItemMapper {
    long countByExample(MlfrontOrderItemExample example);

    int deleteByExample(MlfrontOrderItemExample example);

    int insert(MlfrontOrderItem record);

    List<MlfrontOrderItem> selectByExample(MlfrontOrderItemExample example);

    MlfrontOrderItem selectByPrimaryKey(Integer orderitemId);

    int updateByExampleSelective(@Param("record") MlfrontOrderItem record, @Param("example") MlfrontOrderItemExample example);

    int updateByExample(@Param("record") MlfrontOrderItem record, @Param("example") MlfrontOrderItemExample example);

    int updateByPrimaryKey(MlfrontOrderItem record);
    
    
    //插入新地区价格模板
    int insertSelective(MlfrontOrderItem record);
    //删除本条地区价格模板
    int deleteByPrimaryKey(Integer orderitemId);
    //更新本条地区价格模板
    int updateByPrimaryKeySelective(MlfrontOrderItem record);
    //查询后台登陆用户00
    List<MlfrontOrderItem> selectMlfrontOrderItemById(MlfrontOrderItem example);
    //查询后台登陆用户00
    List<MlfrontOrderItem> selectMlfrontOrderItemByOrderId(MlfrontOrderItem example);
    
    List<MlfrontOrderItem> selectMlfrontOrderItemByNowTime(String nowTime);
    //查询全部登陆用户00
	List<MlfrontOrderItem> selectMlfrontOrderItemGetAll();
	//时间筛选
	List<MlfrontOrderItem> selectMlfrontOrderItemByMotifyTime(MlfrontOrderItem mlfrontOrderItem);

	List<MlfrontOrderItem> selectMlfrontOrderItemAllHundred();
    
}