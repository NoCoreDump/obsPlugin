package utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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

    public static String[] getParamsList(Map<String, Object> map, String key) {
        JSONArray js = (JSONArray) map.get(key);
        String[] paramsList = new String[js.size()];
        js.toArray(paramsList);
        return paramsList;
    }
}
