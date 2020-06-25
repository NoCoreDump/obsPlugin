package constant;

import utils.ObsApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalConstant {
    //节点
    public static String endPoint_global = "";
    //桶名
    public static String bucket_global ="";
    //配置文件的名称
    public static String typeFileZh="";
    public static String typeFileEn = "";
    public static final String TYPE_FILE_ZH = "typeFileZh";
    public static final String TYPE_FILE_EN = "typeFileEn";
    //obs URL,根据config.properties生成，使用的时候，在后面加上对象名
    public static String URL = "";
    //全局配置的Map
    public static List<String> languageList = new ArrayList<>();//语言的种类
    //大类,小类||api 格式:大类,小类->api信息
    public static Map<String, ObsApi> globalMap =new HashMap<>();
    //格式：大类->小类list
    public static Map<String,List<String>> menu =new HashMap<>();
    //操作大类
    public static String[] BIG_TYPE;
    //默认的web 地址
    public static String defaultWebsite = "https://support.huaweicloud.com/qs-obs/obs_qs_1000.html";
    /***************** 常用参数名 **************************/
    public static final String AK = "ak";
    public static final String SK = "sk";
    public static final String ENDPOINT = "endPoint";
    public static final String BUCKET = "bucket";
    /*****************OS name **************/
    public static final String LINUX = "Linux";
    public static final String WINDOWS = "Windows";
    /*****************default JFileChooser path**********/
    public static final String LINUX_PATH = "/home/";
    public static final String WIN_PATH = "D:\\";

}
