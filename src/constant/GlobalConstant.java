package constant;

import java.util.HashMap;
import java.util.Map;

public class GlobalConstant {
    //节点
    public static String endPoint_global = "obs.cn-southwest-2.myhuaweicloud.com";
    //密钥
    public static String ak_global = "KKIM9TEDSNDTINDVDCHW";
    public static String sk_global = "o6H6ZKOh88AVIHNN3Mci5vCo3hEtKBE1PtzYGKnI";
    //桶名
    public static String bucket_global ="explorer";
    //配置文件的名称
    public static String typeFileZh="globalTypeZh.json";
    public static String typeFileEn = "globalTypeEn.json";
    //URL
    public static String URL = "https://" + bucket_global + "." + endPoint_global + "/";
    //全局配置的Map
    public static Map<String,Object> globalMap;
    //操作类型Map
    public static Map<String, Object> categoryMap = new HashMap<>();
    //参数Map
    public static Map<String, Object> parametersMap = new HashMap<>();
    //操作大类
    public static String[] BIG_TYPE;
    //生成文件下拉选择项
    public static String[] LANGE_TYPE = {"Don't generate file", "java", "python", "js", "go"};
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
    /*****************OS name **************/
    public static final String LINUX = "Linux";
    public static final String WINDOWS = "Windows";
    /*****************default JFileChooser path**********/
    public static final String LINUX_PATH = "/home/";
    public static final String WIN_PATH = "D:\\";
}
