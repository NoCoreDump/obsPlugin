package service;


import java.util.Map;

public interface CodeGenerateService {
    /**
     *从OBS拉取代码文件，替换参数内容
     * @param apiName 接口名称
     * @param parameter  参数内容
     * @param languageType  语言类型
     * @return
     */
    String getCodeStr(String apiName, Map<String, String> parameter, String languageType);




//    public static String getLastData(String value1, String value2, String value3) throws IOException {
//        String replaceString = CodeStrGenerateUtil.getReplaceString(value1, value2, value3);
//        System.out.println(replaceString);
//        return replaceString;
//    }
//
//    public static void main(String[] args) throws IOException {
//        getLastData("1", "2", "3");
//    }
}
