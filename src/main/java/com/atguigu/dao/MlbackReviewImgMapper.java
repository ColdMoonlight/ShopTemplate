package com.atguigu.dao;

import com.atguigu.bean.MlbackReviewImg;
import com.atguigu.bean.MlbackReviewImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MlbackReviewImgMapper {
    long countByExample(MlbackReviewImgExample example);

    int deleteByExample(MlbackReviewImgExample example);

    int insert(MlbackReviewImg record);

    List<MlbackReviewImg> selectByExample(MlbackReviewImgExample example);

    MlbackReviewImg selectByPrimaryKey(Integer reviewimgId);

    int updateByExampleSelective(@Param("record") MlbackReviewImg record, @Param("example") MlbackReviewImgExample example);

    int updateByExample(@Param("record") MlbackReviewImg record, @Param("example") MlbackReviewImgExample example);

    int updateByPrimaryKey(MlbackReviewImg record);
    
    
    //插入新图片00
    int insertSelective(MlbackReviewImg record);
    //删除本条图片00
    int deleteByPrimaryKey(Integer reviewimgId);
    //更新本条图片00
    int updateByPrimaryKeySelective(MlbackReviewImg record);
    //查询所有图片00
	List<MlbackReviewImg> selectMlbackReviewImgGetAll();
	//查询本产品下的多张图00
	List<MlbackReviewImg> selectMlbackReviewImgByReviewId(Integer reviewimgId);
	//查询本条图片否存在
	List<MlbackReviewImg> selectMlbackReviewImgByRIdAndImgSort(MlbackReviewImg record);

	int deleteByreviewId(int reviewId);
}