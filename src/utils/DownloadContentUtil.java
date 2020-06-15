package utils;

import com.alibaba.fastjson.JSON;
import com.obs.services.ObsClient;
import com.obs.services.model.ObsObject;

import java.io.*;
import java.util.Map;
import java.util.stream.Collectors;

import static constant.GlobalConstant.*;

/**
 * 从OBS上拉取文件内容
 */
public class DownloadContentUtil {
    /**
     *  从OBS拉取代码文件，替换参数内容，生成可执行文本内容
     * @param bucketName
     * @param objectKey
     * @return
     * @throws IOException
     */
    public static String getStrContentFromOBS(String bucketName, String objectKey) throws IOException {
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak_global, sk_global, endPoint_global);
        ObsObject obsObject = obsClient.getObject(bucketName, objectKey);
        InputStream content = obsObject.getObjectContent();
        InputStreamReader fReader = new InputStreamReader(content,"UTF-8");
        String result = new BufferedReader(fReader)
        .lines().parallel().collect(Collectors.joining(System.lineSeparator()));
        return result;
    }

    /**
     * 从OBS上拉取配置文件，包含各个类别信息、每个接口的参数信息等
     * @param bucketName
     * @param objectKey
     * @return
     */
    public static Map<String,Object> getMapContentFromOBS(String bucketName, String objectKey) {
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak_global, sk_global, endPoint_global);
        ObsObject obsObject = obsClient.getObject(bucketName, objectKey);
        InputStream content = obsObject.getObjectContent();
        String result = null;
        try {
            result = new BufferedReader(new InputStreamReader(content, "utf-8"))
                    .lines().parallel().collect(Collectors.joining(System.lineSeparator()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String,Object> maps = (Map<String,Object>) JSON.parse(result);
        //System.out.println(result);
        return maps;
    }
    public static void main(String[] args) throws IOException {
         getStrContentFromOBS(bucket_global, "codeTemplate_java/ListBuckets.java");
    }

}
