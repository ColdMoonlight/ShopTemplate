package com.atguigu.dao;

import com.atguigu.bean.GroupDisplay;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MlbackCategoryMapper {
    long countByExample(MlbackCategoryExample example);

    int deleteByExample(MlbackCategoryExample example);

    int insert(MlbackCategory record);

    List<MlbackCategory> selectByExample(MlbackCategoryExample example);

    MlbackCategory selectByPrimaryKey(Integer categoryId);

    int updateByExampleSelective(@Param("record") MlbackCategory record, @Param("example") MlbackCategoryExample example);

    int updateByExample(@Param("record") MlbackCategory record, @Param("example") MlbackCategoryExample example);

    int updateByPrimaryKey(MlbackCategory record);
    
    
    
    //插入新类目
    int insertSelective(MlbackCategory record);
    //查询后台登陆用户
    List<MlbackCategory> selectMlbackCategory(MlbackCategory example);
    //查询后台登陆用户
    MlbackCategory selectMlbackCategoryById(MlbackCategory example);
    //查询全部登陆用户
	List<MlbackCategory> selectMlbackCategoryGetAll();
	//查询全部Category的parentid信息
	List<MlbackCategory> selectMlbackCategoryGetAllByParentId();
    //更新本条
    int updateByPrimaryKeySelective(MlbackCategory record);
    //删除本条
    int deleteByPrimaryKey(Integer groupId);
}