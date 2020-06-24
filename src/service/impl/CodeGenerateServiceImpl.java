package service.impl;

import service.CodeGenerateService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static constant.GlobalConstant.*;
import static utils.DownloadContentUtil.getStrContentFromOBS;

public class CodeGenerateServiceImpl implements CodeGenerateService {
    /**
     * 从OBS拉取代码文件，替换参数内容
     *
     * @param apiName      接口名称
     * @param parameters    参数内容
     * @param languageType 语言类型
     * @return
     */
    @Override
    public String getCodeStr(String apiName, Map<String, String> parameters, String languageType) {
        String filePath = globalMap.get(apiName).filePath.get(languageType);
        String codeContent=new String();
        try {
            codeContent = getStrContentFromOBS(URL + filePath, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //====================替换代码中的参数内容============================
        for(String parameter:parameters.keySet()){
            codeContent=codeContent.replace("$"+parameter+"$",parameters.get(parameter));
        }
        return codeContent;
    }
}
