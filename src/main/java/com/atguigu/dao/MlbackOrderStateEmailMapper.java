package com.atguigu.dao;

import com.atguigu.bean.MlbackOrderStateEmail;
import com.atguigu.bean.MlbackOrderStateEmailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MlbackOrderStateEmailMapper {
    long countByExample(MlbackOrderStateEmailExample example);

    int deleteByExample(MlbackOrderStateEmailExample example);

    int insert(MlbackOrderStateEmail record);

    List<MlbackOrderStateEmail> selectByExample(MlbackOrderStateEmailExample example);

    MlbackOrderStateEmail selectByPrimaryKey(Integer orderstateemailId);

    int updateByExampleSelective(@Param("record") MlbackOrderStateEmail record, @Param("example") MlbackOrderStateEmailExample example);

    int updateByExample(@Param("record") MlbackOrderStateEmail record, @Param("example") MlbackOrderStateEmailExample example);

    int updateByPrimaryKey(MlbackOrderStateEmail record);
    
    
    
    //插入新类目
    int insertSelective(MlbackOrderStateEmail record);
    //删除本条
    int deleteByPrimaryKey(Integer orderstateemailId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackOrderStateEmail record);
    //通过id查询本条
    List<MlbackOrderStateEmail> selectMlbackOrderStateEmailById(MlbackOrderStateEmail example);
    //查询全部
	List<MlbackOrderStateEmail> selectMlbackOrderStateEmailGetAll();
	//通过id查询本条
	List<MlbackOrderStateEmail> selectMlbackOrderStateEmailByName(MlbackOrderStateEmail mlbackAbandonPurchase);
    
}