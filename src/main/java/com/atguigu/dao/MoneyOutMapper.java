package com.atguigu.dao;

import com.atguigu.bean.MoneyOut;
import com.atguigu.bean.MoneyOutExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MoneyOutMapper {
    long countByExample(MoneyOutExample example);

    int deleteByExample(MoneyOutExample example);

    int deleteByPrimaryKey(Integer moneyoutId);

    int insert(MoneyOut record);

    List<MoneyOut> selectByExample(MoneyOutExample example);

    MoneyOut selectByPrimaryKey(Integer moneyoutId);

    int updateByExampleSelective(@Param("record") MoneyOut record, @Param("example") MoneyOutExample example);

    int updateByExample(@Param("record") MoneyOut record, @Param("example") MoneyOutExample example);


    int updateByPrimaryKey(MoneyOut record);
    //����
    int insertSelective(MoneyOut record);
    //������ѯ
    List<MoneyOut> getMoneyOutByConditions(MoneyOut record);
    //������������
    int updateByPrimaryKeySelective(MoneyOut record);
}