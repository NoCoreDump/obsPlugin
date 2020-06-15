import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import constant.GlobalConstant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static constant.GlobalConstant.*;
import static utils.DownloadContentUtil.getMapContentFromOBS;

public class CodeGenerator extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        init();
        new MyFrame();
    }
    public static void init() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        //从OBS上拉取全局配置信息
        if ("zh".equals(language)) {
            globalMap = getMapContentFromOBS(bucket_global, typeFileZh);
        } else {
            globalMap  = getMapContentFromOBS(bucket_global,typeFileEn);
        }

        /*********** 操作类型map ***************/
        JSONArray jsonArray = (JSONArray) globalMap.get("globalCategory");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            for (Map.Entry<String, Object> e : jsonObject.entrySet()) {
                categoryMap.put(e.getKey(), e.getValue());
            }
        }

        //BIG_TYPE
        BIG_TYPE = new String[categoryMap.size()];
        categoryMap.keySet().toArray(BIG_TYPE);
        //parametersMap
        parametersMap = (Map<String, Object>) JSON.parse(globalMap.get("parameter").toString());

    }

}
