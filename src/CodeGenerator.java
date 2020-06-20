
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import constant.GlobalConstant;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static constant.GlobalConstant.*;
import static utils.DownloadContentUtil.*;

public class CodeGenerator extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        init();
        new MyFrame();
    }
    public void init() {
        Locale locale = Locale.getDefault();
        String languageType = locale.getLanguage();
        initConfig();

        //从OBS上拉取全局配置信息
        try {
            if ("zh".equals(languageType)) {
                getGlobalConfig(getStrContentFromOBS(URL + typeFileZh, null));
            } else {
                getGlobalConfig(getStrContentFromOBS(URL + typeFileZh, null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //BIG_TYPE
        BIG_TYPE = new String[menu.size()];
        menu.keySet().toArray(BIG_TYPE);
    }

    public void initConfig() {
        //读取配置文件
        InputStreamReader inputStreamReader = null;
        Properties props = new Properties();
        try {
            inputStreamReader = new InputStreamReader(this.getClass().getResourceAsStream("/config.properties"), "utf-8");
            props.load(inputStreamReader);
            ak_global = props.getProperty(AK);
            sk_global = props.getProperty(SK);
            endPoint_global = props.getProperty(ENDPOINT);
            bucket_global = props.getProperty(BUCKET);
            typeFileEn = props.getProperty(TYPE_FILE_EN);
            typeFileZh = props.getProperty(TYPE_FILE_ZH);
            URL = "https://" + bucket_global + "." + endPoint_global + "/";
            inputStreamReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
