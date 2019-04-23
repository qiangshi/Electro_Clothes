package com.business.electr.clothes.net.exception;

/**
 * 类名称：ErrorCode 类描述：
 * <p>
 * 创建人：zhangshanguo 创建时间：2017年1月5日 上午10:00:10
 */
public enum ErrorCode {

    /**
     * API&APP接口必须字段类
     */
    TOKEN_NULL("101", "TOKEN缺失"),

    TOKEN_ERROR("102", "TOKEN错误"),

    DEVICEID_NULL("103", "设备主键缺失"),

    DEVICEID_ERROR("104", "设备主键错误"),

    TIMESTAMP_NULL("105", "时间戳缺失"),

    TIMESTAMP_ERROR("106", "时间戳错误"),

    APP_VERSION_NULL("107", "app版本缺失"),

    APP_VERSION_ERROR("108", "app版本错误"),

    APP_VERSION_OBSOLETE("109", "app版本过时需要强制更新"),

    OSTYPE_NULL("110", "系统类型缺失"),

    OSTYPE_ERROR("111", "系统类型错误"),

    LANGUAGE_NULL("112", "语言类型缺失"),

    LANGUAGE_ERROR("113", "语言类型错误"),

    /***** 登陆注册类 ****/
    ACCOUNT_NULL("121", "手机号或邮箱为空"),

    ACCOUNT_REG_ERROR("122", "手机或邮箱格式错误"),

    ACCOUNT_UNREGISTERED("123", "手机或邮箱未注册"),

    PWD_NULL("124", "密码为空"),

    PWD_REG_ERROR("125", "密码格式错误"),

    CONFIRM_PWD_NULL("126", "确认密码为空"),

    CONFIRM_PWD_DIFFER("127", "密码不一致"),

    PHONE_CODE_NULL("128", "手机验证码为空"),

    PHONE_CODE_ERROR("129", "手机验证码错误"),

    IMG_CODE_NULL("130", "图片验证码为空"),

    IMG_CODE_ERROR("131", "图片验证码错误"),

    ACCOUNT_PWD_ERROR("132", "账号密码错误"),

    ACCOUNT_RET_ERROR("133", "手机验证码60秒内不能重新发送 "),

    OLD_PWD_NULL("135", "原来密码缺失"),

    ACCOUNT_REGISTERED("136", "手机或邮箱账号已注册"),

    THIRD_LOGIN_ERROR("137", "第三方登陆错误"),

    CHANGE_DEVICE("138", "更换设备"),

    VIP_BE_OVERDUE("150", "会员已过期"),

    TEST_VIP_BE_OVERDUE("151", "试用会员已过期"),

    VIP_NO_EFFECT("152", "会员未生效"),

    SUCCESS("200", "成功"),

    FAIL_SERVICE("201", "服务异常"),

    NO_RESULT("202", "请求正确，未返回数据"),

    PARAMS_ERROR("203", "请求参数错误"),

    CPYNAME_ERROR("204", "企业名称错误"),

    ALLOW_PUSH_ERROR("225", "允许推送参数类型错误"),

    SEARCH_QUALIFIERS("226", "搜索限制词"),

    NET_ERROR("333","网络错误"),

    /**
     * 所有用户操作错误并且不需要处理只需要提示，必须使用此错误码
     */
    FAIL_MSG("300", "接口请求失败");

    private String code;

    private String typeView;

    ErrorCode(String code, String typeView) {
        this.code = code;
        this.typeView = typeView;
    }

    public String getCode() {
        return code;
    }

    public String getTypeView() {
        return typeView;
    }

    /**
     * 功能描述： 根据CODE码获取枚举类型
     * 创建人：     liyingwei
     * 创建时间：2018年5月26日 下午2:31:54
     * 修改人                        修改时间                          修改内容
     *
     * @param code CODE码
     * @return OsType
     */
    public static ErrorCode requestTypeByCode(String code) {
        if (null == code || code.trim().equals("")) {
            return null;
        }
        for (ErrorCode type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

}
