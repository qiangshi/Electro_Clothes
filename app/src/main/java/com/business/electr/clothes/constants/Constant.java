package com.business.electr.clothes.constants;

/**
 * Created by zenghaiqiang on 2018/11/16.
 * 描述：
 */

public class Constant {



    public static final int PAGE_COUNT = 5;
    public static final int DEFAULT_PAGE_LIMIT = 20;
    public static final String WEBSOCKET_URL = "ws://47.93.7.27:8282";

    public static final String URL = "url";
    public static final String DIALOG_HINT = "hint";
    public static final String DIALOG_CONTENT = "content";
    public static final String DIALOG_LEFT = "left";
    public static final String DIALOG_RIGHT = "right";
    public static final String IS_LOGIN = "is_login";
    public static final String CLOCK_TYPE = "clock_type";
    public static final String CURRENT_TIME = "current_time";
    public static final String IS_TIME_BEAN_RECEIVE = "is_time_bean_receive";

    public static final int PERMISSIONS_RESULT_CODE = 1001;
    public static final int COMMON_CODE = 110;

    public static final String DATE_FORMAT_0 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_3 = "yyyy";
    public static final String DATE_FORMAT_4 = "HH:mm";
    public static final String DATE_FORMAT_5 = "MM月dd日";
    public static final String DATE_FORMAT_6 = "yyyy/MM/dd";
    public static final String DATE_FORMAT_7 = "yyyy/MM";

    public static final String BAIDU_AK = "8MHzqiUwn85aYLLFl35bItIo7QC41OUS";

    public static final String EXTRA_USER_ID = "user_id";//用户id
    public static final String EXTRA_IS_SELECT = "is_select";//是否是选择状态
    public static final String EXTRA_IS_MORE_SELECT = "is_more_select";//是否是选择状态
    public static final String EXTRA_COMPANY_ID = "company_id";//传递的公司id
    public static final String EXTRA_IS_UPDATE_MANAGER = "is_update_manager";//是否是更换管理员
    public static final String EXTRA_IS_MEMBER_MANAGER = "is_member_manager";//是否是员工管理
    public static final String EXTRA_RESULT = "result";//返回的结果


    public static final int REQUEST_CODE_APPROVAL = 0x1000;
    public static final int REQUEST_CODE_COPY_FOR = 0x1001;
    public static final int REQUEST_CODE_TRAVEL_REASON = 0x1002;
    public static final int REQUEST_CODE_USER_NICK_NAME = 0x1003;
    public static final int REQUEST_CODE_PHONE = 0x1004;
    public static final int REQUEST_CODE_MODIFY_MANAGER = 0x1005;//修改管理员
    public static final int REQUEST_CODE_MEMBER_NAME = 0x1006;//员工姓名
    public static final int REQUEST_CODE_MEMBER_DEPARTMENT = 0x1007;//部门
    public static final int REQUEST_CODE_MEMBER_EMAIL = 0x1008;//邮箱
    public static final int REQUEST_CODE_MEMBER_REMARK = 0x1009;//备注
    public static final int REQUEST_CODE_MODIFY_MEMBER_INFO = 0x1010;//修改企业员工信息
    public static final int SELECT_TIME = 0x0086;

    // 消息类型 初始化
    public static final String NOTICE_TYPE_INIT = "init";
    // 消息类型 邀请用户加入公司
    public static final String NOTICE_TYPE_INVITE_TO_TEAM = "1";
    // 消息类型 用户统一加入公司
    public static final String NOTICE_TYPE_AGREE_ADD_COMPANY = "10";
    public static final String NOTICE_TYPE_REFUSE_ADD_COMPANY = "11";

    // 消息类型 周报
    public static final String NOTICE_TYPE_WEEKLY = "7";
    // 消息类型 请假
    public static final String NOTICE_TYPE_LEAVE = "2";
    // 消息类型 加班
    public static final String NOTICE_TYPE_OVERTIME = "3";
    // 消息类型 出差
    public static final String NOTICE_TYPE_TRIP = "4";
    // 消息类型 外出
    public static final String NOTICE_TYPE_FIELD = "5";
    // 消息类型 任务
    public static final String NOTICE_TYPE_TASK = "6";
    // 消息类型 公告
    public static final String NOTICE_TYPE_ANNOUNCEMENT = "9";
    // 消息类型 聊天室外 一对一聊天
    public static final String NOTICE_TYPE_CHAT_PUBLIC = "20";
    // 消息类型 聊天室内 一对一聊天
    public static final String NOTICE_TYPE_CHAT_ROOM = "21";
}
