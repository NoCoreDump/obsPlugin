
import com.alibaba.fastjson.JSON;
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
import java.util.List;

import static constant.GlobalConstant.*;

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
    private JTabbedPane akPane;
    private JTextField akField;
    private JTabbedPane skPane;
    private JTextField skField;
    private JTabbedPane endpointPane;
    private JTextField endpointField;
    private JPanel prmsPanel;
    private JPanel vSpacer1;
    private JButton buttonOK;
    private JButton buttonRun;

    private JTabbedPane right;
    private JTabbedPane examplePane;
    private JScrollPane codeScrollPane;
    private JTextPane codeArea;
    private JTabbedPane documentPane;
    private JScrollPane docScrollPane;
    private JTextPane docTextArea;
    private JTabbedPane languageTypePane;
    private JComboBox languageBox;
    private JScrollPane runPane;
    private JTextPane runResultTextArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    /************* 自定义变量 ******************/
    private JFrame frame;
    private String selectedSubType;
    private String selectedLangType;
    private ArrayList<String> paramsList;
    private JTabbedPane[] paramsPane;
    private JTextField[] paramsTextFields;
    private int osType; //0 - linux; 1 - windows
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
        Object[] subTypeList = menu.get(BIG_TYPE[0]).toArray();
        selectedSubType = (String)subTypeList[0];
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
        akPane = new JTabbedPane();
        akField = new JTextField();
        skPane = new JTabbedPane();
        skField = new JTextField();
        endpointPane = new JTabbedPane();
        endpointField = new JTextField();
        prmsPanel = new JPanel();
        languageTypePane = new JTabbedPane();
        languageBox = new JComboBox(languageList.toArray());
        selectedLangType = languageList.get(0);
        vSpacer1 = new JPanel(null);
        buttonOK = new JButton();
        buttonRun = new JButton();
        //right
        right = new JTabbedPane();
        examplePane = new JTabbedPane();
        codeScrollPane = new JScrollPane();
        codeArea = new JTextPane();

        documentPane = new JTabbedPane();
        docScrollPane = new JScrollPane();
        docTextArea = new JTextPane();
        runPane = new JScrollPane();
        runResultTextArea = new JTextPane();

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

                                Object[] items = menu.get(selectedBigType).toArray();
//                                System.out.println("subtype : " + Arrays.toString(items));
                                smallType.removeAllItems();
                                for (Object item : items) {
                                    smallType.addItem(item);
                                }
                                selectedSubType = (String)items[0];
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
                    //======== akPane ========
                    {

                        //---- akField ----
                        akField.setPreferredSize(new Dimension(49, 40));
                        akPane.addTab("ak", akField);
                    }
                    mid.add(akPane);

                    //======== skPane ========
                    {

                        //---- skField ----
                        skField.setPreferredSize(new Dimension(49, 40));
                        skPane.addTab("sk", skField);
                    }
                    mid.add(skPane);

                    //======== endpointPane ========
                    {

                        //---- endpointField ----
                        endpointField.setPreferredSize(new Dimension(49, 40));
                        endpointPane.addTab("endPoint", endpointField);
                    }
                    mid.add(endpointPane);
                    prmsPanel.setLayout(new VerticalLayout());
                    updateParamPanes();
                    mid.add(prmsPanel);
                    //============= languageBox ========
                    languageBox.addItemListener(e -> {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            selectedLangType = (String) languageBox.getSelectedItem();
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
                        codeArea.setText("");
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
                        runResultTextArea.setText("");
                        codeArea.setText("");
                        generateCode(paramsTextFields, paramsList);
                        generateReferenct();
                        String filePath = "";
                        JFileChooser fileChooser;
                        String defaultPath = (osType == 0) ? LINUX_PATH : WIN_PATH;
                        fileChooser = new JFileChooser(defaultPath);
                        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        int returnVal = fileChooser.showOpenDialog(fileChooser);
                        if(returnVal == JFileChooser.APPROVE_OPTION) {
                            filePath= fileChooser.getSelectedFile().getAbsolutePath();
                        }
                        System.out.println("************* file path; " + filePath);
                        String targetPath = (osType == 1) ? filePath + "\\target" : filePath + "/target";
                        File file = new File(targetPath);
                        if (!file.exists()) {
                            file.mkdir();
                        }
                        String apiName = generateFile(filePath, languageBox.getSelectedItem().toString());
                        System.out.println("********** apiName: " + apiName);
                        runFile(filePath, languageBox.getSelectedItem().toString(), apiName);

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
                            codeScrollPane.setViewportView(codeArea);
                        }
                    }
                    examplePane.addTab("code", codeScrollPane);


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
    private void runFile(String filePath, String fileType, String apiName) {

        apiName = apiName.substring(0, apiName.indexOf(".java"));
        ProjectGenerateService projectGenerateService = new ProjectGenerateServiceImpl();

        //==============================利用cmd命令编译项目，执行class文件，生成结果返回至resString==========
        System.out.println("filePath: " + filePath + "apiName: " + apiName);
        runResult = projectGenerateService.runProject(filePath,apiName);
        if (runResult == null || runResult.length() == 0) {
            runResult = "null";
        }
        runResultTextArea.setText(runResult);
        System.out.println(runResult);
    }

    private void generateCode(JTextField[] paramsTextFields, ArrayList<String> paramsList) {
        if (paramsTextFields == null || paramsTextFields.length == 0) return;
        CodeGenerateServiceImpl codeGenerateService =new CodeGenerateServiceImpl();
        Map<String, String> params = new HashMap<>();
        params.put(AK, akField.getText());
        params.put(SK, skField.getText());
        params.put(ENDPOINT, endpointField.getText());
        if (paramsList != null && paramsTextFields != null) {
            for (int i = 0; i < paramsList.size(); i++) {
                if (paramsList.get(i).equals("null")) break;
                String s = paramsList.get(i);
                params.put(s, paramsTextFields[i].getText());

            }
        }
        try {
            codeArea.setText(codeGenerateService.getCodeStr(selectedSubType, params, selectedLangType));
        } catch (Exception e) {
            codeArea.setText(e.getMessage());
            e.printStackTrace();
        }

    }


    /*
     * @Author sunwb
     * @Description 将生成的代码文件保存到目标工程/src/main/java/下
     * @Date 23:02 2020/6/7
     * @Param path 工程目录
     * @Param fileType 文件类型：java python go ...
     * @return void
     **/
    private String generateFile(String path, String fileType) {
        String code = codeArea.getText();
        String fileName = globalMap.get(selectedSubType).filePath.get(fileType);
        System.out.println("---------fileName:" + fileName);
        int x = fileName.indexOf('/');
        fileName = fileName.substring(x+1);
        try {
//            区分Windows和Linux
            if (osType == 0) {
                path = path + "/src/main/java/" + fileName;
            } else {
                path = path + "\\src\\main\\java\\" + fileName;
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
        String referenceUrl = globalMap.get(selectedSubType).referenceWebsite;
        docTextArea.setText(referenceUrl);
    }

    private void updateParamPanes() {
        prmsPanel.removeAll();
//        System.out.println("global Map: " + globalMap.keySet());
//        System.out.println("selectedSubType:" + selectedSubType);
        paramsList = null;
        paramsList = new ArrayList<>(globalMap.get(selectedSubType).parameter);
//        System.out.println("params: " + paramsList);
        if (paramsList != null && paramsList.size() > 0) {
            int len = paramsList.size();
            paramsPane = new JTabbedPane[len];
            paramsTextFields = new JTextField[len];
            for (int i = 0; i < len; i++) {
                if (paramsList.get(i).equals("null")) {
                    break;
                }
                paramsPane[i] = new JTabbedPane();
                paramsTextFields[i] = new JTextField();
                paramsTextFields[i].setPreferredSize(new Dimension(49, 40));
                paramsPane[i].addTab(paramsList.get(i), paramsTextFields[i]);
                prmsPanel.add(paramsPane[i]);
            }
        } else {
            paramsTextFields = null;
        }

    }

    public static void main(String[] args) {
        new CodeGenerator().init();
        new MyFrame();
    }
}
