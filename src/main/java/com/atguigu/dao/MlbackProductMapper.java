package com.atguigu.dao;

import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MlbackProductMapper {
    long countByExample(MlbackProductExample example);

    int deleteByExample(MlbackProductExample example);

    int insert(MlbackProduct record);

    List<MlbackProduct> selectByExampleWithBLOBs(MlbackProductExample example);

    List<MlbackProduct> selectByExample(MlbackProductExample example);

    MlbackProduct selectByPrimaryKey(Integer productId);

    int updateByExampleSelective(@Param("record") MlbackProduct record, @Param("example") MlbackProductExample example);

    int updateByExampleWithBLOBs(@Param("record") MlbackProduct record, @Param("example") MlbackProductExample example);

    int updateByExample(@Param("record") MlbackProduct record, @Param("example") MlbackProductExample example);

//    int updateByPrimaryKeySelective(MlbackProduct record);

    int updateByPrimaryKeyWithBLOBs(MlbackProduct record);

    int updateByPrimaryKey(MlbackProduct record);
    
    
    
    //插入新类目00
    int insertSelective(MlbackProduct record);
    //查询后台登陆用户00
    List<MlbackProduct> selectMlbackProduct(MlbackProduct example);
    //查询后台登陆用户00
    List<MlbackProduct> selectMlbackProductByPName(MlbackProduct example);
    //查询全部登陆用户00
	List<MlbackProduct> selectMlbackProductGetAll();
    //更新本条00
    int updateByPrimaryKeySelective(MlbackProduct record);
    
    int updateMainFuImgById(MlbackProduct record);
    
    //删除本条00
    int deleteByPrimaryKey(Integer groupId);
    //查询本lable的信息记录
    List<MlbackProduct> selectMlbackProductByLable(MlbackProduct example);
    
    List<MlbackProduct> selectMlbackProductByProductCategoryid (MlbackProduct example);

	List<MlbackProduct> selectMlbackProductByStatus(MlbackProduct mlbackProduct);

	MlbackProduct selectMlbackProductBySeo(MlbackProduct mlbackProductrepBySeo);

	List<MlbackProduct> selectMlbackProductLike(MlbackProduct mlbackProduct);

	List<MlbackProduct> selectMlbackProductLikeNum(MlbackProduct mlbackProduct);
    
}