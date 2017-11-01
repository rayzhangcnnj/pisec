package com.ray.pi.dao.persistence;

import com.ray.pi.dao.entity.TfBHeadImage;

/**
 * @ClassName: TfBHeadImageMapper
* @Description: tf_b_head_image表对应的dao操作Mapper映射类
* @author: Ray
 */
public interface TfBHeadImageMapper {
    /**
    * @Title TfBHeadImageMapper.deleteByPrimaryKey
    * @Description: 根据属性名称，删除tf_b_head_image
    * @param imageId imageId
    * @return int  删除个数
     */
    int deleteByPrimaryKey(Long imageId);

    /**
    * @Title TfBHeadImageMapper.insertSelective
    * @Description: 插入一个只有部分字段的tf_b_head_image
    * @param record 只含部分字段的tf_b_head_image的bean对象
    * @return int  插入个数
     */
    int insertSelective(TfBHeadImage record);

    /**
    * @Title TfBHeadImageMapper.selectByPrimaryKey
    * @Description: 根据主键类，返回tf_b_head_image
    * @param imageId imageId
    * @return TfBHeadImage bean对象
     */
    TfBHeadImage selectByPrimaryKey(Long imageId);

    /**
    * @Title TfBHeadImageMapper.updateByPrimaryKeySelective
    * @Description: 根据主键更新tf_b_head_image部分字段
    * @param record 要更新成为的TfBHeadImage对象
    * @return int 更新记录数
     */
    int updateByPrimaryKeySelective(TfBHeadImage record);

    /**
    * @Title TfBHeadImageMapper.updateByPrimaryKey
    * @Description: 根据主键更新tf_b_head_image全部字段
    * @param record 要更新成为的TfBHeadImage对象
    * @return int 更新记录数
     */
    int updateByPrimaryKey(TfBHeadImage record);

}