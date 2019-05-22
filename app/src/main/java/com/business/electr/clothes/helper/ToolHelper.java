package com.business.electr.clothes.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.ProvinceBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.DateFormatUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.core.content.ContextCompat;

/**
 * Created by zenghaiqiang on 2019/1/22.
 */

public class ToolHelper {


    /**
     * 选择时间
     * return 返回选择的时间
     */
    public static void selectTime(BaseActivity activity, TextView textView, String dateType) {
        selectTime(activity,textView,dateType,null);
    }


    /**
     * 选择时间
     * return 返回选择的时间
     */
    public static void selectTime(BaseActivity activity, TextView textView, String dateType,Handler handler) {
        boolean isHM = Constant.DATE_FORMAT_1.equals(dateType);
        boolean isDay = !Constant.DATE_FORMAT_7.equals(dateType);
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        selectedDate.setTimeInMillis(System.currentTimeMillis());
        startDate.set(1960, 0, 1);
        endDate.set(2050, 11, 31);
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, final View v) {
                SimpleDateFormat formatter = new SimpleDateFormat(dateType, Locale.getDefault());
                String dateString = formatter.format(date);
                textView.setText(dateString);
                textView.setTextColor(ContextCompat.getColor(activity, R.color.color_8c919b));
                if(handler != null){
                    Message msg = new Message();
                    msg.what = Constant.SELECT_TIME;
                    msg.obj = dateString;
                    handler.handleMessage(msg);
                }
            }

        }).setType(new boolean[]{true, true, isDay, isHM, isHM, false})
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setTitleText(activity.getResources().getString(R.string.select_birthday))
                .setSubmitText(activity.getResources().getString(R.string.confirm))
                .setCancelText(activity.getResources().getString(R.string.cancel))
                .setCancelColor(activity.getResources().getColor(R.color.color_8c919b))
                .setSubmitColor(activity.getResources().getColor(R.color.color_353535))
                .build();
        pvTime.show();
    }


    /**
     * 选择时间
     *
     * @param activity    上下文
     * @param isStartTime 是否是开始时间
     * @param tvStartTime tvStartTime
     * @param tvEndTime   tvEndTime
     * @param showType    0:开始结束时间   1：截止提醒时间
     */
    public static void selectTime(BaseActivity activity, boolean isStartTime, final TextView tvStartTime, final TextView tvEndTime, int showType) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        selectedDate.setTimeInMillis(System.currentTimeMillis());
        startDate.set(1960, 0, 1);
        endDate.set(2050, 11, 31);
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, final View v) {
                SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT_1, Locale.getDefault());
                String dateString = formatter.format(date);
                if (isStartTime) {
                    try {
                        if (DateFormatUtil.stringToLong(tvEndTime.getText().toString(), Constant.DATE_FORMAT_1) < DateFormatUtil.stringToLong(dateString, Constant.DATE_FORMAT_1)) {
                            if (showType == 0) {
                                activity.toastMessage(R.string.input_start_time_error);
                            } else {
                                activity.toastMessage(R.string.input_hint_time_error);
                            }
                            return;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    tvStartTime.setTextColor(ContextCompat.getColor(activity, R.color.color_252631));
                    tvStartTime.setText(dateString);
                } else {
                    try {
                        if (DateFormatUtil.stringToLong(tvStartTime.getText().toString(), Constant.DATE_FORMAT_1) >= DateFormatUtil.stringToLong(dateString, Constant.DATE_FORMAT_1)) {
                            if (showType == 0) {

                                activity.toastMessage(R.string.input_end_time_error);
                            } else {
                                activity.toastMessage(R.string.input_close_time_error);
                            }
                            return;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    tvEndTime.setTextColor(ContextCompat.getColor(activity, R.color.color_252631));
                    tvEndTime.setText(dateString);
                }
            }

        }).setType(new boolean[]{true, true, true, true, true, false})
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }


    public static void selectTime(BaseActivity activity, boolean isStartTime, final TextView tvStartTime, final TextView tvEndTime) {
        selectTime(activity, isStartTime, tvStartTime, tvEndTime, 0);
    }


    static ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    static ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    static ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    public static void showCitySelect(BaseActivity activity, boolean isStartCity, final TextView tvStartCity, final TextView tvTargetCity) {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;
                if (isStartCity) {
                    tvStartCity.setTextColor(ContextCompat.getColor(activity, R.color.color_252631));
                    tvStartCity.setText(tx);
                } else {
                    tvTargetCity.setTextColor(ContextCompat.getColor(activity, R.color.color_252631));
                    tvTargetCity.setText(tx);
                }
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }


    /**
     * 初始化assets中的数据
     *
     * @param context
     */
    public static void initProvinceData(Context context, Handler handler) {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         * */
        String JsonData = getJson("province.json", context);//获取assets目录下的json文件数据
        ArrayList<ProvinceBean> jsonBean = parseData(JsonData, handler);
        /**添加省份数据*/
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            int size = jsonBean.get(i).getCityList().size();
            for (int c = 0; c < size; c++) {//遍历该省份的所有城市
                ProvinceBean.CityBean bean = jsonBean.get(i).getCityList().get(c);
                String cityName = bean.getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                city_AreaList.addAll(bean.getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }
            /**添加城市数据*/
            options2Items.add(cityList);
            /**添加地区数据*/
            options3Items.add(province_AreaList);
        }
//        handler.sendEmptyMessage(TravelActivity.MSG_LOAD_SUCCESS);

    }


    /**
     * 解析数据
     *
     * @param result
     * @param handler
     * @return
     */
    public static ArrayList<ProvinceBean> parseData(String result, Handler handler) {//fastJson 解析
        ArrayList<ProvinceBean> detail = new ArrayList<>();
        try {
            JSONArray jsonArray = JSONArray.parseArray(result);
            int size = jsonArray.size();
            for (int i = 0; i < size; i++) {
                ProvinceBean provinceBean = new ProvinceBean();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                ArrayList<ProvinceBean.CityBean> cityBeans = new ArrayList<>();
                JSONArray cityArray = JSONArray.parseArray(jsonObject.getString("city"));
                int citySize = cityArray.size();
                for (int j = 0; j < citySize; j++) {
                    ProvinceBean.CityBean cityBean = new ProvinceBean.CityBean();

                    JSONObject cityObject = cityArray.getJSONObject(j);
                    String cityName = cityObject.getString("name");
                    ArrayList<String> areaList = new ArrayList<>();

                    JSONArray areaArray = JSONArray.parseArray(cityObject.getString("area"));
                    int areaSize = areaArray.size();
                    for (int k = 0; k < areaSize; k++) {
                        String area = (String) areaArray.get(k);
                        areaList.add(area);
                    }
                    cityBean.setName(cityName);
                    cityBean.setArea(areaList);
                    cityBeans.add(cityBean);
                }
                provinceBean.setName(name);
                provinceBean.setCityList(cityBeans);
                detail.add(provinceBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            handler.sendEmptyMessage(TravelActivity.MSG_LOAD_FAILED);
        }
        return detail;
    }


    /**
     * 解析字符
     *
     * @param fileName 文件名
     * @param context  上下文
     * @return
     */
    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
