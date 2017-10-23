package com.ray.pi.dao.persistence;

import com.ray.pi.dao.entity.TfBUser;

/**
 * @ClassName: TfBUserMapper
* @Description: tf_b_user表对应的dao操作Mapper映射类
* @author: Ray
 */
public interface TfBUserMapper {
    /**
    * @Title TfBUserMapper.deleteByPrimaryKey
    * @Description: 根据属性名称，删除tf_b_user
    * @param userId userId
    * @return int  删除个数
     */
    int deleteByPrimaryKey(Long userId);

    /**
    * @Title TfBUserMapper.insertSelective
    * @Description: 插入一个只有部分字段的tf_b_user
    * @param record 只含部分字段的tf_b_user的bean对象
    * @return int  插入个数
     */
    int insertSelective(TfBUser record);

    /**
    * @Title TfBUserMapper.selectByPrimaryKey
    * @Description: 根据主键类，返回tf_b_user
    * @param userId userId
    * @return TfBUser bean对象
     */
    TfBUser selectByPrimaryKey(Long userId);

    /**
    * @Title TfBUserMapper.updateByPrimaryKeySelective
    * @Description: 根据主键更新tf_b_user部分字段
    * @param record 要更新成为的TfBUser对象
    * @return int 更新记录数
     */
    int updateByPrimaryKeySelective(TfBUser record);

    /**
    * @Title TfBUserMapper.updateByPrimaryKey
    * @Description: 根据主键更新tf_b_user全部字段
    * @param record 要更新成为的TfBUser对象
    * @return int 更新记录数
     */
    int updateByPrimaryKey(TfBUser record);
}