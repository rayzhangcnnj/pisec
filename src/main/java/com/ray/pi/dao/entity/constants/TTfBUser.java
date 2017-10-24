package com.ray.pi.dao.entity.constants;

/**
 * @ClassName: TTfBUser
* @Description: tf_b_user表结构对应的常量类，定义字段名常量
* @author: Ray
 */
public abstract class TTfBUser {
    /**
     * @Fields tf_b_user.USER_ID: 用户id
     */
    public static final String USER_ID = "user_id";

    /**
     * @Fields tf_b_user.USER_NAME: 用户名称
     */
    public static final String USER_NAME = "user_name";

    /**
     * @Fields tf_b_user.LAST_DRIVE_START_TIME: 最近驾驶开始时间
     */
    public static final String LAST_DRIVE_START_TIME = "last_drive_start_time";

    /**
     * @Fields tf_b_user.LAST_DRIVE_END_TIME: 最近驾驶结束时间
     */
    public static final String LAST_DRIVE_END_TIME = "last_drive_end_time";

    /**
     * @Fields tf_b_user.CREATE_ID: 创建人
     */
    public static final String CREATE_ID = "create_id";

    /**
     * @Fields tf_b_user.CREATE_TIME: 创建时间
     */
    public static final String CREATE_TIME = "create_time";
}