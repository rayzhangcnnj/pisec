package com.ray.pi.dao.entity;

import com.ray.pi.common.entity.StringAndEqualsObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: TfBHeadImage
* @Description: tf_b_head_image表对应的java bean类
* @author: Ray
 */
public class TfBHeadImage extends StringAndEqualsObject implements Serializable {
    /**
     * @Fields serialVersionUID : 自动生成默认序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields tf_b_head_image.image_id :图片id
     */
    private Long imageId;

    /**
     * @Fields tf_b_head_image.user_id :图片id
     */
    private Long userId;

    /**
     * @Fields tf_b_head_image.md5 :MD5码
     */
    private String md5;

    /**
     * @Fields tf_b_head_image.create_id :创建人
     */
    private String createId;

    /**
     * @Fields tf_b_head_image.create_time :创建时间
     */
    private Date createTime;

    /**
     * @return tf_b_head_image.image_id : 返回 图片id
     */
    public Long getImageId() {
        return imageId;
    }

    /**
     * @param imageId of tf_b_head_image : 设置 图片id
     */
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    /**
     * @return tf_b_head_image.user_id : 返回 图片id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId of tf_b_head_image : 设置 图片id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return tf_b_head_image.md5 : 返回 MD5码
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @param md5 of tf_b_head_image : 设置 MD5码
     */
    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    /**
     * @return tf_b_head_image.create_id : 返回 创建人
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * @param createId of tf_b_head_image : 设置 创建人
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * @return tf_b_head_image.create_time : 返回 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime of tf_b_head_image : 设置 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}