package utils; /**
 * @ClassName: ObsObject
 * @Author: 13989
 * @Date: 2020/6/20 14:11
 * @Description:
 * @Version: 1.0
 */

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ObsObject
 * @Author: 13989
 * @Date: 2020/6/20 14:11
 * @Description:
 * @Version: 1.0
 */
public class ObsApi {
    public List<String> parameter;//参数列表
    public String referenceWebsite;//参考网站
    public Map<String,String> filePath;//代码文件的路径

    public ObsApi(List<String> parameter, String referenceWebsite, Map<String, String> filePath) {
        this.parameter = parameter;
        this.referenceWebsite = referenceWebsite;
        this.filePath = filePath;
    }
}