package service.impl;
import com.obs.services.ObsClient;
import com.obs.services.model.ObsObject;
import service.ProjectGenerateService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static constant.GlobalConstant.*;
import static utils.FileUtil.readFileContent;
import static utils.FileUtil.writeFileContent;
import static utils.RunUtil.buildProject;
import static utils.RunUtil.runFile;

public class ProjectGenerateServiceImpl implements ProjectGenerateService {

    @Override
    public void pomFileGenerate(String pomPath) {
        // 创建ObsClient实例
        final ObsClient obsClient = new ObsClient(ak_global, sk_global, endPoint_global);
        ObsObject obsObject = obsClient.getObject(bucket_global, "projectTemplate_java/pom.xml");
        // 读取对象内容
        System.out.println("Object content:");
        InputStream input = obsObject.getObjectContent();
        byte[] b = new byte[1024 * 1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        try {
            while ((len = input.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeFileContent(pomPath,new String(bos.toByteArray()));
    }

    @Override
    public String runProject(String projectPath,String apiName) {
        File file=new File("projectPath\\target");
        if(!file.exists()){//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        //=============================判断系统类型============================================
        int systemType=-1;
        String osName = System.getProperties().getProperty("os.name");
        if (osName.contains("Linux")) {
            systemType=1;
        } else if(osName.contains("Windows")){
            systemType=0;
        }
        //==========================根据系统类型执行编译执行脚本===================================
        switch (systemType){
            //windows系统需要执行cmd命令
            case 0:
                buildProject(projectPath,apiName);
                return runFile(projectPath,apiName);
            //linux系统需要执行shell命令
            case 1:
                break;
            default:
                break;
        }
        return null;
    }
}
