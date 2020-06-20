package utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author sunwb
 * @version 1.0.0
 * @ClassName JsonUtil.java
 * @Description TODO
 * @createTime 2020年06月10日
 */
public class JsonUtil {
    public static String[] getSubTypeList(Map<String, Object> cateoryMap, String bigType) {
        JSONArray js = (JSONArray) cateoryMap.get(bigType);
        String[] subTypeList = new String[js.size()];
        js.toArray(subTypeList);
        return subTypeList;
    }


    public static String parse2Ascii(String str) {
        try {
            byte[] arr = str.getBytes();
            return new String(arr, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
