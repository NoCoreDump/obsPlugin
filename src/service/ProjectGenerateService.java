package service;
public interface ProjectGenerateService {

    /**
     * 利用cmd运行项目
     * @param projectPath 项目名称
     * @param apiName 接口名称
     * @return 运行结果
     */
    public String runProject(String projectPath, String apiName);
}
