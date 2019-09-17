package com.atguigu.dao;

import com.atguigu.bean.MlbackChannel;
import com.atguigu.bean.MlbackChannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MlbackChannelMapper {
    long countByExample(MlbackChannelExample example);

    int deleteByExample(MlbackChannelExample example);

    int insert(MlbackChannel record);

    List<MlbackChannel> selectByExample(MlbackChannelExample example);

    MlbackChannel selectByPrimaryKey(Integer channelId);

    int updateByExampleSelective(@Param("record") MlbackChannel record, @Param("example") MlbackChannelExample example);

    int updateByExample(@Param("record") MlbackChannel record, @Param("example") MlbackChannelExample example);

    int updateByPrimaryKey(MlbackChannel record);
    
    
    //插入新类目
    int insertSelective(MlbackChannel record);
    //删除本条
    int deleteByPrimaryKey(Integer groupId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackChannel record);
    //查询后台登陆用户
    List<MlbackChannel> selectMlbackChannelById(MlbackChannel record);
    //查询全部登陆用户
	List<MlbackChannel> selectMlbackChannelGetAll();
}