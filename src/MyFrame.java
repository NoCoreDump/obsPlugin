
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import constant.GlobalConstant;
import org.jdesktop.swingx.VerticalLayout;
import service.ProjectGenerateService;
import service.impl.CodeGenerateServiceImpl;
import service.impl.ProjectGenerateServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.*;
import java.util.*;

import static constant.GlobalConstant.*;
import static utils.DownloadContentUtil.getMapContentFromOBS;
import static utils.JsonUtil.getParamsList;
import static utils.JsonUtil.getSubTypeList;

/**
 * @program: MyPlugin
 * @description: my frame
 * @author: sunwb
 * @create: 2020-05-10 14:47
 */
public class MyFrame implements SwingConstants{
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel center;
    private JTabbedPane leftParent;
    private JPanel left;
    private JTabbedPane typePane;
    private JComboBox bigType;
    private JTabbedPane smallTypePane;
    private JComboBox smallType;
    private JTabbedPane middle;
    private JPanel mid;
    private JPanel prmsPanel;
    private JPanel vSpacer1;
    private JButton buttonOK;
    private JButton buttonRun;

    private JTabbedPane right;
    private JTabbedPane examplePane;
    private JTabbedPane javaPane;
    private JScrollPane javaScrollPane;
    private JTextArea javaCodeArea;
    private JTabbedPane pythonPane;
    private JScrollPane pythonScrollPane;
    private JTextArea pythonCodeArea;
    private JTabbedPane jsPane;
    private JScrollPane jsScrollPane;
    private JTextArea jsCodeArea;
    private JTabbedPane goPane;
    private JScrollPane goScrollPane;
    private JTextArea goCodeArea;
    private JTabbedPane documentPane;
    private JScrollPane docScrollPane;
    private JTextArea docTextArea;
    private JTabbedPane languageTypePane;
    private JComboBox languageBox;
    private JScrollPane runPane;
    private JTextArea runResultTextArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    /************* 自定义变量 ******************/
    private JFrame frame;
    private String selectedSubType;
    private int languageTypeIndex;
    private String[] paramsList;
    private JTabbedPane[] paramsPane;
    private JTextField[] paramsTextFields;
    private int osType;
    private String generatedFilePath; //本地路径
    private String pomPath;
    private String runResult = "";
    private ResourceBundle message;
    private Locale locale;

    public MyFrame() {
        init();
        placePanel();
    }

    public void init() {
        Locale locale0 = Locale.getDefault();
        String language = locale0.getLanguage();
        String lan;
        if ("zh".equals(language)) {
            lan = "zh";
        } else {
            lan = "en";
        }
        locale = new Locale(lan);
        message = ResourceBundle.getBundle("message", locale);
        String osName = System.getProperties().getProperty("os.name");
        System.out.println(osName);
        if (LINUX.equals(osName)) {
            osType = 0;
        } else {
            osType = 1;
        }
        frame = new JFrame("obs");

        //初始化小类
        String[] subTypeList;
        subTypeList = getSubTypeList(categoryMap, BIG_TYPE[0]);
        selectedSubType = subTypeList[0];
        System.out.println(selectedSubType);
        /******************************************************************/
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        center = new JPanel();
        leftParent = new JTabbedPane();
        left = new JPanel();
        typePane = new JTabbedPane();
        bigType = new JComboBox(BIG_TYPE);
        smallTypePane = new JTabbedPane();
        smallType = new JComboBox(subTypeList);

        //mid
        middle = new JTabbedPane();
        mid = new JPanel();
        prmsPanel = new JPanel();
        languageTypePane = new JTabbedPane();
        languageBox = new JComboBox(LANGE_TYPE);
        vSpacer1 = new JPanel(null);
        buttonOK = new JButton();
        buttonRun = new JButton();
        //right
        right = new JTabbedPane();
        examplePane = new JTabbedPane();
        javaPane = new JTabbedPane();
        javaScrollPane = new JScrollPane();
        javaCodeArea = new JTextArea();
        pythonPane = new JTabbedPane();
        pythonScrollPane = new JScrollPane();
        pythonCodeArea = new JTextArea();
        jsPane = new JTabbedPane();
        jsScrollPane = new JScrollPane();
        jsCodeArea = new JTextArea();
        goPane = new JTabbedPane();
        goScrollPane = new JScrollPane();
        goCodeArea = new JTextArea();
        documentPane = new JTabbedPane();
        docScrollPane = new JScrollPane();
        docTextArea = new JTextArea();
        runPane = new JScrollPane();
        runResultTextArea = new JTextArea();

    }
    public void placePanel(){
        int xSize = 1100;
        int ySize = 800;
        frame.setSize(new Dimension(xSize, ySize));
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));
        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());
        }
        contentPane.add(panel1, BorderLayout.NORTH);

        //======== panel2 ========
        {
            panel2.setLayout(new BorderLayout());
        }
        contentPane.add(panel2, BorderLayout.WEST);

        //======== panel3 ========
        {
            panel3.setLayout(new BorderLayout());
        }
        contentPane.add(panel3, BorderLayout.EAST);


        //======== center ========
        {

            //======== leftParent ========
            {

                //======== left ========
                {
                    left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

                    //======== typePane ========
                    {

                        //---- bigType ----
                        bigType.addItemListener(e -> {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                String selectedBigType = (String) bigType.getSelectedItem();

                                String[] items = getSubTypeList(categoryMap, selectedBigType);
//                                System.out.println("subtype : " + Arrays.toString(items));
                                smallType.removeAllItems();
                                for (String item : items) {
                                    smallType.addItem(item);
                                }
                                selectedSubType = items[0];
                            }
                        });
                        bigType.setMinimumSize(new Dimension(30, 15));
                        bigType.setPreferredSize(new Dimension(30, 12));
                        typePane.addTab(message.getString("big_type"), bigType);
                    }
                    left.add(typePane);

                    //======== smallTypePane ========
                    {

                        //---- smallType ----
                        smallType.addItemListener(e->{
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                selectedSubType = (String)smallType.getSelectedItem();
                                updateParamPanes();
                            }
                        });
                        smallType.setMinimumSize(new Dimension(30, 15));
                        smallType.setPreferredSize(new Dimension(30, 12));
                        smallTypePane.addTab(message.getString("sub_type"), smallType);
                    }
                    left.add(smallTypePane);
                }
                left.setSize(new Dimension(200, 200));
                leftParent.addTab(message.getString("operation_type"), left);
            }

            //======== middle ========
            {
                middle.setAutoscrolls(true);

                //======== mid ========
                {
                    mid.setLayout(new VerticalLayout());
                    prmsPanel.setLayout(new VerticalLayout());
                    updateParamPanes();
                    mid.add(prmsPanel);
                    //============= languageBox ========
                    languageBox.addItemListener(e -> {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            languageTypeIndex = languageBox.getSelectedIndex();
                        }
                    });
                    languageBox.setMinimumSize(new Dimension(30, 40));
                    languageBox.setPreferredSize(new Dimension(30, 40));
                    languageTypePane.addTab(message.getString("file_type"), languageBox);

                    mid.add(languageTypePane);

                    //---- vSpacer1 ----
                    vSpacer1.setPreferredSize(new Dimension(10, 30));
                    mid.add(vSpacer1);

                    //---- buttonOK ----
                    buttonOK.setText("generate");
                    buttonOK.setAlignmentY(1.0F);
                    buttonOK.setAlignmentX(0.5F);
                    buttonOK.setMargin(new Insets(2, 2, 0, 0));
                    buttonOK.setPreferredSize(new Dimension(78, 40));
                    buttonOK.addActionListener(e-> {
                        javaCodeArea.setText("");
                        pythonCodeArea.setText("");
                        jsCodeArea.setText("");
                        goCodeArea.setText("");
                        generateCode(paramsTextFields, paramsList);
                        generateReferenct();
                    });
                    mid.add(buttonOK);

                    //---- buttonRun ----
                    buttonRun.setText("RUN");
                    buttonRun.setAlignmentY(1.0F);
                    buttonRun.setAlignmentX(0.5F);
                    buttonRun.setMargin(new Insets(2, 2, 0, 0));
                    buttonRun.setPreferredSize(new Dimension(78, 40));
                    buttonRun.addActionListener(e -> {
                        runResultTextArea.removeAll();
                        if (languageTypeIndex != 0) {
                            String filePath;
                            JFileChooser fileChooser;
                            String defaultPath = (osType == 0) ? LINUX_PATH : WIN_PATH;
                            fileChooser = new JFileChooser(defaultPath);
                            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                            int returnVal = fileChooser.showOpenDialog(fileChooser);
                            if(returnVal == JFileChooser.APPROVE_OPTION) {
                                pomPath= fileChooser.getSelectedFile().getAbsolutePath();
                            }
                            filePath = pomPath.substring(0, pomPath.length()  - 7);
                            String apiName = generateFile(filePath, languageBox.getSelectedItem().toString());
                            runFile(pomPath, languageBox.getSelectedItem().toString(), apiName);
                        }
                    });
                    mid.add(buttonRun);
                }
                middle.addTab(message.getString("key_parameters"), mid);
            }

            //======== right ========
            {

                //======== examplePane ========
                {

                    //======== javaPane ========
                    {

                        //======== javaScrollPane ========
                        {
                            javaScrollPane.setViewportView(javaCodeArea);
                        }
                        javaPane.addTab("", javaScrollPane);
                    }
                    examplePane.addTab("java", javaPane);

                    //======== pythonPane ========
                    {

                        //======== pythonScrollPane ========
                        {
                            pythonScrollPane.setViewportView(pythonCodeArea);
                        }
                        pythonPane.addTab("", pythonScrollPane);
                    }
                    examplePane.addTab("python", pythonPane);

                    //======== jsPane ========
                    {

                        //======== jsScrollPane ========
                        {
                            jsScrollPane.setViewportView(jsCodeArea);
                        }
                        jsPane.addTab("", jsScrollPane);
                    }
                    examplePane.addTab("js", jsPane);

                    //======== goPane ========
                    {

                        //======== goScrollPane ========
                        {
                            goScrollPane.setViewportView(goCodeArea);
                        }
                        goPane.addTab("", goScrollPane);
                    }
                    examplePane.addTab("go", goPane);
                }
                right.addTab(message.getString("sample_code"), examplePane);


                //======== documentPane ========
                {

                    //======== docScrollPane ========
                    {

                        //---- docTextArea ----
                        docTextArea.setText("https://support.huaweicloud.com/sdk-java-devg-obs/obs_21_0401.html");
                        docScrollPane.setViewportView(docTextArea);
                    }
                    documentPane.addTab(message.getString("reference"), docScrollPane);
                }
                right.addTab(message.getString("documents"), documentPane);
            }

            //======== runPane ========
            {
                runPane.setViewportView(runResultTextArea);
            }


            GroupLayout centerLayout = new GroupLayout(center);
            center.setLayout(centerLayout);
            centerLayout.setHorizontalGroup(
                    centerLayout.createParallelGroup()
                            .addGroup(centerLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(leftParent, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(middle, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(centerLayout.createParallelGroup()
                                            .addGroup(centerLayout.createSequentialGroup()
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(right, GroupLayout.PREFERRED_SIZE, 648, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(15, 15, 15))
                                            .addGroup(centerLayout.createSequentialGroup()
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(runPane)
                                                    .addContainerGap())))
            );
            centerLayout.setVerticalGroup(
                    centerLayout.createParallelGroup()
                            .addGroup(centerLayout.createSequentialGroup()
                                    .addGroup(centerLayout.createParallelGroup()
                                            .addComponent(leftParent, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(middle, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(centerLayout.createSequentialGroup()
                                                    .addComponent(right, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(runPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(149, Short.MAX_VALUE))
            );
        }
        contentPane.add(center, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * @title
     * @description popPath: pom.xml path; fileType:java python go js; apiName:selectedSubType
     * @author sunwb
     * @updateTime 20-6-15 上午9:49
     * @throws
     */
    private void runFile(String pomPath, String fileType, String apiName) {
        apiName = apiName.substring(0, apiName.indexOf(".java"));
        runResultTextArea.removeAll();
        ProjectGenerateService projectGenerateService = new ProjectGenerateServiceImpl();
        File file = new File(pomPath);
        if(!file.exists()){
            System.out.println("文件不存在，请检查工程目录");
            return;
        }
        projectGenerateService.pomFileGenerate(pomPath);
        //**************************************此处需要手工创建target文件夹**************************
        //==============================利用cmd命令编译项目，执行class文件，生成结果返回至resString==========
        pomPath = pomPath.substring(0, pomPath.length()-8);
        System.out.println("pomPath: " + pomPath + "apiName: " + apiName);
        runResult = projectGenerateService.runProject(pomPath,apiName);
        if (runResult == null || runResult.length() == 0) {
            runResult = "null";
        }
        runResultTextArea.append(runResult);
        System.out.println(runResult);
    }

    private void generateCode(JTextField[] paramsTextFields, String[] paramsList) {
        if (paramsTextFields == null || paramsTextFields.length == 0) return;
        CodeGenerateServiceImpl codeGenerateService =new CodeGenerateServiceImpl();
        Map<String, String> params = new HashMap<>();
//        System.out.println("paramList: " + Arrays.toString(paramsList));
        for (int i = 0; i < paramsList.length; i++) {
            String s = paramsList[i];
            params.put(s, paramsTextFields[i].getText());

        }

        try {
            javaCodeArea.append(codeGenerateService.getCodeStr(selectedSubType, params, JAVA));
        } catch (Exception e) {
            javaCodeArea.append(e.getMessage());
            e.printStackTrace();
        }
        try {
            pythonCodeArea.append(codeGenerateService.getCodeStr(selectedSubType, params, PYTHON));
        } catch (Exception e) {
            pythonCodeArea.append(e.getMessage());
            e.printStackTrace();
        }
        try {
            goCodeArea.append(codeGenerateService.getCodeStr(selectedSubType, params, GO));
        } catch (Exception e) {
            goCodeArea.append(e.getMessage());
            e.printStackTrace();
        }
        try {
            jsCodeArea.append(codeGenerateService.getCodeStr(selectedSubType, params, JS));
        } catch (Exception e) {
            jsCodeArea.append(e.getMessage());
            e.printStackTrace();
        }
    }


    /*
     * @Author sunwb
     * @Description 将生成的代码文件保存到目标文件路径
     * @Date 23:02 2020/6/7
     * @Param []
     * @return void
     **/
    private String generateFile(String path, String fileType) {
        String code = "";
        String tmpFilePath = "templateFilePath_";
        switch(fileType) {
            case JAVA: tmpFilePath += "Java"; code = javaCodeArea.getText(); break;
            case PYTHON: tmpFilePath += "Python"; code = pythonCodeArea.getText(); break;
            case JS: tmpFilePath += "Js"; code = jsCodeArea.getText(); break;
            case GO: tmpFilePath += "Go"; code = goCodeArea.getText(); break;
        }
        Map<String, Object> fileNameMap = (Map<String, Object>) JSON.parse(globalMap.get(tmpFilePath).toString());
        String fileName = (String)fileNameMap.get(selectedSubType);
        int x = fileName.indexOf('/');
        fileName = fileName.substring(x+1);
        try {
//            区分Windows和Linux
            if (osType == 0) {
                path = path + "src/main/java/" + fileName;
            } else {
                path = path + "src\\main\\java\\" + fileName;
            }
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            bufferedWriter.write(code);
            bufferedWriter.newLine();
            bufferedWriter.close();
            System.out.println(path + "文件已生成");
            return fileName;
        } catch (IOException e) {
            System.out.println("Fail: create file!");
        }
        return "";
    }

    private void generateReferenct() {
        Map<String, Object> referenceMap = (Map<String, Object>) JSON.parse(globalMap.get("referenceWebsite").toString());
        String referenceUrl = (String)referenceMap.get(selectedSubType);
        docTextArea.append(referenceUrl);
    }

    private void updateParamPanes() {
        prmsPanel.removeAll();
        paramsList = getParamsList(parametersMap, selectedSubType);
        if (paramsList != null && paramsList.length > 0) {
            int len = paramsList.length;
            paramsPane = new JTabbedPane[len];
            paramsTextFields = new JTextField[len];
            System.out.println("paramList: " + Arrays.toString(paramsList));
            for (int i = 0; i < len; i++) {
                paramsPane[i] = new JTabbedPane();
                paramsTextFields[i] = new JTextField();
                paramsTextFields[i].setPreferredSize(new Dimension(49, 40));
                paramsPane[i].addTab(paramsList[i], paramsTextFields[i]);
                prmsPanel.add(paramsPane[i]);
            }
        } else {
            paramsTextFields = null;
        }

    }

    public static void main(String[] args) {
        CodeGenerator.init();
        new MyFrame();
    }
}
