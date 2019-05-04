package com.business.electr.clothes.utils;


import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pantianxiong on 2018/4/27.
 * 描述：
 */
public class DataCheckUtils {
    //判断邮箱格式
    public static boolean checkEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        String regex = "^\\s*\\w+(?:\\.?[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return email.matches(regex);
    }

    //判断手机格式
    public static boolean checkPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0-9])|(198))\\d{8}$";
        return phone.matches(regex);
    }

    /**
     * @param checkType 校验类型：0校验手机号码，1校验座机号码，2两者都校验满足其一就可
     * @param phoneNum
     */
    public static boolean validPhoneNum(String checkType, String phoneNum) {
        boolean flag = false;
        Pattern p1;
        Pattern p2;
        Matcher m;
        p1 = Pattern.compile("^(((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))+\\d{8})?$");
        p2 = Pattern.compile("^(0[0-9]{2,3}\\-)?([1-9][0-9]{6,7})$");
        if ("0".equals(checkType)) {
            System.out.println(phoneNum.length());
            if (phoneNum.length() != 11) {
                return false;
            } else {
                m = p1.matcher(phoneNum);
                flag = m.matches();
            }
        } else if ("1".equals(checkType)) {
            if (phoneNum.length() < 11 || phoneNum.length() >= 16) {
                return false;
            } else {
                m = p2.matcher(phoneNum);
                flag = m.matches();
            }
        } else if ("2".equals(checkType)) {
            if (!((phoneNum.length() == 11 && p1.matcher(phoneNum).matches()) || (phoneNum.length() < 16 && p2.matcher(phoneNum).matches()))) {
                return false;
            } else {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 验证输入的是否是电话是否是数字和-
     *
     * @param str
     * @return
     */
    public static boolean isTele(String str) {
        Pattern pattern = Pattern.compile("[0-9\\-]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }


    //判断纯数字格式
    public static boolean checkNumber(String strNum) {
        if (TextUtils.isEmpty(strNum)) {
            return false;
        }
        String regex = "[0-9]+";
        return strNum.matches(regex);
    }

    //检查密码
    public static boolean checkPwd(String pwd) {
        if (TextUtils.isEmpty(pwd) || pwd.length() < 8) {
            return false;
        }
        String regex = "^(?=.{6,20}$)(?![0-9]+$)(?![a-zA-Z]+$)(?![\\!@#\\$%\\^&\\*\\(\\)\\.\\/\\\\,\\[\\]\\{\\}\\+\\-_]+$)[0-9a-zA-Z\\!@#\\$%\\^&\\*\\(\\)\\.\\/\\\\,\\[\\]\\{\\}\\+\\-_]+$";
        return pwd.matches(regex);
    }

}
