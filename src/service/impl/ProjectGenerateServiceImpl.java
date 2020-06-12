package service.impl;
import service.ProjectGenerateService;

import java.io.File;

import static utils.FileUtil.readFileContent;
import static utils.FileUtil.writeFileContent;
import static utils.RunUtil.buildProject;
import static utils.RunUtil.runFile;

public class ProjectGenerateServiceImpl implements ProjectGenerateService {
    @Override
    public  void pomFileGenerate(String pomPath) {
        StringBuilder pomContent = readFileContent(pomPath);
        //插入编译版本
        if(pomContent.indexOf("<build>")==-1){
            int index = pomContent.indexOf("</version>")+"</version>".length();
            pomContent.insert(index,"\n    <build>\n" +
                    "        <plugins>\n" +
                    "            <plugin>\n" +
                    "                <groupId>org.apache.maven.plugins</groupId>\n" +
                    "                <artifactId>maven-compiler-plugin</artifactId>\n" +
                    "                <configuration>\n" +
                    "                    <source>8</source>\n" +
                    "                    <target>8</target>\n" +
                    "                </configuration>\n" +
                    "            </plugin>\n" +
                    "        </plugins>\n" +
                    "    </build>\n");
        }
        //检查是否有依赖
        if(pomContent.indexOf("<dependencies>")==-1){
            int index = pomContent.indexOf("</build>")+"</build>".length();
            pomContent.insert(index,"\n    <dependencies>\n" +
                    "        <!--obs依赖 -->\n" +
                    "        <dependency>\n" +
                    "            <groupId>com.huaweicloud</groupId>\n" +
                    "            <artifactId>esdk-obs-java</artifactId>\n" +
                    "            <version>3.19.7</version>\n" +
                    "        </dependency>\n" +
                    "    </dependencies>\n");
            //有依赖文件但是没有导入OBS包
        }else if(pomContent.indexOf("<artifactId>esdk-obs-java</artifactId>")==-1){
            int index =pomContent.indexOf("<dependencies>")+"<dependencies>".length();
            pomContent.insert(index,"\n        <!--obs依赖 -->\n" +
                    "        <dependency>\n" +
                    "            <groupId>com.huaweicloud</groupId>\n" +
                    "            <artifactId>esdk-obs-java</artifactId>\n" +
                    "            <version>3.19.7</version>\n" +
                    "        </dependency>\n");

        }
        writeFileContent(pomPath,pomContent.toString());
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
