package com.atguigu.dao;

import com.atguigu.bean.MlbackSlide;
import com.atguigu.bean.MlbackSlideExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MlbackSlideMapper {
    long countByExample(MlbackSlideExample example);

    int deleteByExample(MlbackSlideExample example);

    int insert(MlbackSlide record);

    List<MlbackSlide> selectByExample(MlbackSlideExample example);

    MlbackSlide selectByPrimaryKey(Integer slideId);

    int updateByExampleSelective(@Param("record") MlbackSlide record, @Param("example") MlbackSlideExample example);

    int updateByExample(@Param("record") MlbackSlide record, @Param("example") MlbackSlideExample example);

    int updateByPrimaryKey(MlbackSlide record);
    
    
    //插入新类目
    int insertSelective(MlbackSlide record);
    //删除本条
    int deleteByPrimaryKey(Integer slideId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackSlide record);
    //通过id查询本条
    MlbackSlide selectMlbackSlideById(MlbackSlide example);
    //查询全部
	List<MlbackSlide> selectMlbackSlideGetAll();

	List<MlbackSlide> selectMlbackSlidewapListByArea(MlbackSlide mlbackSlide);
	
	List<MlbackSlide> selectMlbackSlidepcListByArea(MlbackSlide mlbackSlide);

}