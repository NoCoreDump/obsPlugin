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
        //====================从全局map中拉取代码存放的路径===========================

        //====================获取需要生成的接口路径===================================
        System.out.println(globalMap.get(apiName));
        String filePath = globalMap.get(apiName).filePath.get(languageType);
        System.out.println("filePath: " + filePath);
        String codeContent=new String();
        //====================从路径对应的文件中获取代码内容============================
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
