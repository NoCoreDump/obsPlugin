package utils;

import com.alibaba.fastjson.JSON;
import com.obs.services.ObsClient;
import com.obs.services.model.ObsObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

import static constant.GlobalConstant.*;

/**
 * 从OBS上拉取文件内容
 */
public class DownloadContentUtil {
    /**
     *  从OBS拉取代码文件，替换参数内容，生成可执行文本内容
     * @param url
     * @param param
     * @return
     * @throws IOException
     */
    public static String getStrContentFromOBS(String url, String param) throws IOException {
        String result = "";
        InputStream in = null;
        try {
            String urlNameString = url;
            if (param != null && param.length() > 0) {
                urlNameString += "?" + param;
            }
            URL realUrl = new URL(urlNameString);
            System.out.println("URL -- " + realUrl);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            in = connection.getInputStream();

            try {
                result = new BufferedReader(new InputStreamReader(in, "utf-8"))
                        .lines().parallel().collect(Collectors.joining(System.lineSeparator()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Get error！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 从OBS上拉取配置文件，包含各个类别信息、每个接口的参数信息等
     * @param url Json配置文件的URL
     * @param param get请求的参数
     * @return
     */
    public static Map<String,Object> getMapContentFromOBS(String url, String param) {
        String result = "";
        InputStream in = null;
        try {
            String urlNameString = url;
            if (param != null && param.length() > 0) {
                urlNameString += "?" + param;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            in = connection.getInputStream();

            try {
                result = new BufferedReader(new InputStreamReader(in, "utf-8"))
                        .lines().parallel().collect(Collectors.joining(System.lineSeparator()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Get error！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        Map<String,Object> maps = (Map<String,Object>) JSON.parse(result);
        return maps;
    }


    public static void getGlobalConfig(String content){
        content=content.replace("\r","");
        String[] lines=content.split("\n");
        int idx=0;
        //找到第一行
        while(lines[idx].length()==0||lines[idx].charAt(0)=='#')
            idx++;
        String[] head = lines[idx++].split(" ");
        //添加语言类别
        for (int i = 4; i <head.length; i++) {
            languageList.add(head[i]);
        }
        for (;idx <lines.length ; idx++) {
            //跳过无效行
            if(lines[idx].length()==0||lines[idx].charAt(0)=='#')
                continue;
            String[] element = lines[idx].split(" ");
            ObsApi obsApi=new ObsApi(new ArrayList<>(),element[3],new HashMap<>());
            String[] par = element[2].split(",");
            obsApi.parameter.addAll(Arrays.asList(par));
            //所有语言
            for (int i = 0; i <languageList.size() ; i++) {
                if(i+4<element.length)
                    obsApi.filePath.put(languageList.get(i),element[i+4]);
                else
                    obsApi.filePath.put(languageList.get(i),"");
            }
            globalMap.put(element[1],obsApi);
            if(menu.get(element[0])==null)
                menu.put(element[0],new ArrayList<>());
            menu.get(element[0]).add(element[1]);
        }
    }
    public static void main(String[] args) throws IOException {
        String url = "https://" + bucket_global + "." + endPoint_global + "/" + typeFileZh;
//        String url = "http://www.baidu.com";
        System.out.println(url);
//        Map<String, Object> result = sendGet(url, "");
        String res = getStrContentFromOBS(url, null);
        System.out.println("---------------------------------");
        System.out.println(res.toString());
    }


}
