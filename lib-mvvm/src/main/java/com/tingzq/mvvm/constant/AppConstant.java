package com.tingzq.mvvm.constant;

public final class AppConstant {


    /** 启动指定活动类键 */
    public static final String ACTIVITY_CLASS = "ACTIVITY_CLASS";

    /** 启动碎片容器活动指定碎片名称键 */
    public static final String CONTAINER_FRAGMENT_NAME  = "CONTAINER_FRAGMENT_NAME";

    /** 进入活动所携带的数据 */
    public static final String BUNDLE = "BUNDLE";

    /** 临时保存容器活动碎片类实例,完全退出程序会消失 */
    public static final String TEMP_CONTAINER_FRAGMENT_INSTANCE_STATE = "TEMP_CONTAINER_FRAGMENT_INSTANCE_STATE";

    /** 通用权限请求码(可以自定义请求权限对应各自的请求码) */
    public static final Integer PERMISSION_REQUEST_CODE = 0;

    /** 后台持续活跃定位任务间隔时间(默认5秒) */
    public static final Long KEEP_ACTIVE_TASK_INTERVAL_TIME = 5 * 1000L;

    /** 后台持续活跃前台服务通知ID */
    public static final Integer KEEP_ACTIVE_FRONT_SERVICE_NOTIFICATION_ID = 2023;

    /** 后台持续活跃广播调度 */
    public static final String BACKGROUND_KEEP_ACTIVE_TASK_SCHEDULING = "BACKGROUND_KEEP_ACTIVE_TASK_SCHEDULING";

    /** 默认线程池数量 */
    public static final Integer DEFAULT_THREAD_POOL_SIZE = 3;

    /** 底部标签栏默认选择下标 */
    public static final  String TAB_BAR_DEFAULT_INDEX = "TAB_BAR_DEFAULT_INDEX";

    /** 访问token */
    public static final String ACCESS_TOKEN_NAME = "DOLPHIN_ACCESS_TOKEN";

    /** 刷新token */
    public static final String REFRESH_TOKEN_NAME = "DOLPHIN_REFRESH_TOKEN";

}
