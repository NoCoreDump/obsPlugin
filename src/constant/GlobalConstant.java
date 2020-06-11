package constant;

import java.util.Map;

public class GlobalConstant {
    //节点
    public static String endPoint_global = "https://obs.cn-southwest-2.myhuaweicloud.com";
    //密钥
    public static String ak_global = "KKIM9TEDSNDTINDVDCHW";
    public static String sk_global = "o6H6ZKOh88AVIHNN3Mci5vCo3hEtKBE1PtzYGKnI";
    //桶名
    public static String bucket_global ="explorer";
    //配置文件的名称
    public static String typeFile="globalType.json";
    //全局配置的Map
    public static Map<String,Object> globalMap;
    //操作类型Map
    public static Map<String, Object> categoryMap;
    //参数Map
    public static Map<String, Object> parametersMap;
    //操作大类
//    public static String[] BIG_TYPE;
    public static Object[] BIG_TYPE1;
    //生成文件下拉选择项
    public static String[] LANGE_TYPE = {"不生成文件", "java", "python", "js", "go"};
    public static final String PYTHON = "python";
    public static final String JAVA = "java";
    public static final String JS = "js";
    public static final String GO = "go";
    /***************** 常用参数名 **************************/
    public static final String AK = "ak";
    public static final String SK = "sk";
    public static final String ENDPOINT = "endPoint";
    public static final String REGION = "region";
    public static final String BUCKET_NAME = "bucketName";
}
