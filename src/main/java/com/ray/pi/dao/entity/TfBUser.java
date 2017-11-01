package com.ray.pi.dao.entity;

import com.ray.pi.common.entity.StringAndEqualsObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: TfBUser
* @Description: tf_b_user表对应的java bean类
* @author: Ray
 */
public class TfBUser extends StringAndEqualsObject implements Serializable {
    /**
     * @Fields serialVersionUID : 自动生成默认序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields tf_b_user.user_id :用户id
     */
    private Long userId;

    /**
     * @Fields tf_b_user.user_name :用户名称
     */
    private String userName;

    /**
     * @Fields tf_b_user.last_drive_start_time :最近驾驶开始时间
     */
    private Date lastDriveStartTime;

    /**
     * @Fields tf_b_user.last_drive_end_time :最近驾驶结束时间
     */
    private Date lastDriveEndTime;

    /**
     * @Fields tf_b_user.drive_state :当前驾驶状态
     */
    private String driveState;

    /**
     * @Fields tf_b_user.create_id :创建人
     */
    private String createId;

    /**
     * @Fields tf_b_user.create_time :创建时间
     */
    private Date createTime;

    /**
     * @return tf_b_user.user_id : 返回 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId of tf_b_user : 设置 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return tf_b_user.user_name : 返回 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName of tf_b_user : 设置 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return tf_b_user.last_drive_start_time : 返回 最近驾驶开始时间
     */
    public Date getLastDriveStartTime() {
        return lastDriveStartTime;
    }

    /**
     * @param lastDriveStartTime of tf_b_user : 设置 最近驾驶开始时间
     */
    public void setLastDriveStartTime(Date lastDriveStartTime) {
        this.lastDriveStartTime = lastDriveStartTime;
    }

    /**
     * @return tf_b_user.last_drive_end_time : 返回 最近驾驶结束时间
     */
    public Date getLastDriveEndTime() {
        return lastDriveEndTime;
    }

    /**
     * @param lastDriveEndTime of tf_b_user : 设置 最近驾驶结束时间
     */
    public void setLastDriveEndTime(Date lastDriveEndTime) {
        this.lastDriveEndTime = lastDriveEndTime;
    }

    /**
     * @return tf_b_user.drive_state : 返回 当前驾驶状态
     */
    public String getDriveState() {
        return driveState;
    }

    /**
     * @param driveState of tf_b_user : 设置 当前驾驶状态
     */
    public void setDriveState(String driveState) {
        this.driveState = driveState == null ? null : driveState.trim();
    }

    /**
     * @return tf_b_user.create_id : 返回 创建人
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * @param createId of tf_b_user : 设置 创建人
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * @return tf_b_user.create_time : 返回 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime of tf_b_user : 设置 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}