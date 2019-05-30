package com.business.electr.clothes.utils;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.http.HTTP;

/**
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/5/30 16:54
 */
public class HeadUploadUtil {
//    public static HttpResponse post(Map<String, Object> params, String url) {
//
//        OkHttpClient client = new OkHttpClient();
//        Ok httpPost = new HttpPost(url);
//        httpPost.addHeader("charset", HTTP.UTF_8);
//        httpPost.setHeader("Content-Type",
//                "application/x-www-form-urlencoded; charset=utf-8");
//        HttpResponse response = null;
//        if (params != null && params.size() > 0) {
//            List<NameValuePair> nameValuepairs = new ArrayList<NameValuePair>();
//            for (String key : params.keySet()) {
//                nameValuepairs.add(new BasicNameValuePair(key, (String) params
//                        .get(key)));
//            }
//            try {
//                httpPost.setEntity(new UrlEncodedFormEntity(nameValuepairs,
//                        HTTP.UTF_8));
//                response = client.execute(httpPost);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (RuntimeException e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                response = client.execute(httpPost);
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return response;
//
//    }
//    public static Object getValues(Map<String, Object> params, String url) {
//        String token = "";
//        HttpResponse response = post(params, url);
//        if (response != null) {
//            try {
//                token = EntityUtils.toString(response.getEntity());
//                response.removeHeaders("operator");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return token;
//    }
//    public static Object setImgByStr(String imgStr,String imgName){
//        String url =  host+"channel-uploadImage.action";
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("imgStr", imgStr);
//        params.put("imgName", imgName);
//        return getValues(params, url);
//    }

//    public String Bitmap2StrByBase64(Bitmap bit){
//        ByteArrayOutputStream bos=new ByteArrayOutputStream();
//        bit.compress(CompressFormat.JPEG, 40, bos);//参数100表示不压缩
//        byte[] bytes=bos.toByteArray();
//        return Base64.encodeToString(bytes, Base64.DEFAULT);
//    }
}
