import service.impl.CodeGenerateServiceImpl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static constant.GlobalConstant.*;
import static utils.DownloadContentUtil.getMapContentFromOBS;

public class Main {
    public static void main(String[] args) {

        Locale locale = Locale.getDefault();
        System.out.println(locale.getLanguage());

        //从OBS上拉取全局配置信息
        globalMap  = getMapContentFromOBS(bucket_global,typeFileEn);
        CodeGenerateServiceImpl codeGenerateService =new CodeGenerateServiceImpl();
        //测试变量
        String apiName ="列举桶";
        Map<String,String> parameters = new HashMap<>();
        parameters.put("endPoint","https://obs.cn-southwest-2.myhuaweicloud.com");
        parameters.put("ak","KKIM9TEDSNDTINDVDCHW");
        parameters.put("sk","o6H6ZKOh88AVIHNN3Mci5vCo3hEtKBE1PtzYGKnI");
        String languageType="java";
        //从OBS上拉取对应 languageType的代码文件内容，并将参数内容替换成parameters的内容
        System.out.println(codeGenerateService.getCodeStr(apiName, parameters, languageType));
    }
}
