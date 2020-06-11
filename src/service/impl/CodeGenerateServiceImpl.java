package service.impl;

import service.CodeGenerateService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static constant.GlobalConstant.bucket_global;
import static constant.GlobalConstant.globalMap;
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
        //====================从全局map中拉取代码存放的路径===========================
        Map<String, Object> templatePath=new HashMap<>();
        switch (languageType) {
            case "java":
                templatePath = (Map<String, Object>) globalMap.get("templateFilePath_Java");
                break;
            case "python":
                templatePath = (Map<String, Object>) globalMap.get("templateFilePath_Python");
                break;
            case "go":
                templatePath = (Map<String, Object>) globalMap.get("templateFilePath_Go");
                break;
        }
        //====================获取需要生成的接口路径===================================
        String filePath = (String) templatePath.get(apiName);
        String codeContent=new String();
        //====================从路径对应的文件中获取代码内容============================
        try {
            codeContent = getStrContentFromOBS(bucket_global,filePath);
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
