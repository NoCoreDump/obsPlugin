
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import constant.GlobalConstant;
import org.jdesktop.swingx.VerticalLayout;
import service.impl.CodeGenerateServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    private JPanel vSpacer1;
    private JButton buttonOK;

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
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    /************* 自定义变量 ******************/
    private JFrame frame;
    private String selectedSubType;
    private int languageTypeIndex;
    private String[] subTypeList;
    public MyFrame() {
        init();
        placePanel();
    }

    public void init() {
        frame = new JFrame("obs");

        //初始化小类
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

        vSpacer1 = new JPanel(null);
        buttonOK = new JButton();

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
        languageTypePane = new JTabbedPane();
        languageBox = new JComboBox(LANGE_TYPE);
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
                                smallType.removeAllItems();
                                String[] items = getSubTypeList(categoryMap, selectedBigType);
                                for (String item : items) {
                                    smallType.addItem(item);
                                }
                            }
                        });
                        bigType.setMinimumSize(new Dimension(30, 15));
                        bigType.setPreferredSize(new Dimension(30, 12));
                        typePane.addTab("\u7c7b\u578b", bigType);
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
                        smallType.setMinimumSize(new Dimension(99, 15));
                        smallType.setPreferredSize(new Dimension(93, 12));
                        smallTypePane.addTab("\u5c0f\u7c7b", smallType);
                    }
                    left.add(smallTypePane);
                }
                left.setSize(new Dimension(200, 200));
                leftParent.addTab("\u64cd\u4f5c\u7c7b\u578b", left);
            }

            //======== middle ========
            {
                middle.setAutoscrolls(true);

                //======== mid ========
                {
                    mid.setLayout(new VerticalLayout());

                    updateParamPanes();
                }
                middle.addTab("\u5173\u952e\u53c2\u6570", mid);
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
                right.addTab("\u793a\u4f8b\u4ee3\u7801", examplePane);


                //======== documentPane ========
                {

                    //======== docScrollPane ========
                    {

                        //---- docTextArea ----
                        docTextArea.setText("https://support.huaweicloud.com/sdk-java-devg-obs/obs_21_0401.html");
                        docScrollPane.setViewportView(docTextArea);
                    }
                    documentPane.addTab("OBS\u5b98\u7f51\u53c2\u8003\u6587\u6863", docScrollPane);
                }
                right.addTab("\u67e5\u770b\u6587\u6863", documentPane);
            }
            GroupLayout centerLayout = new GroupLayout(center);
            center.setLayout(centerLayout);

            centerLayout.setHorizontalGroup(
                    centerLayout.createParallelGroup()
                            .addGroup(centerLayout.createSequentialGroup()
//                                    .addContainerGap()
                                    .addComponent(leftParent, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(middle, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(right, GroupLayout.PREFERRED_SIZE, 648, GroupLayout.PREFERRED_SIZE)
                                    .addGap(15, 15, 15))
                            .addGroup(GroupLayout.Alignment.TRAILING, centerLayout.createSequentialGroup()
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(348, 348, 348))
            );
            centerLayout.setVerticalGroup(
                    centerLayout.createParallelGroup()
                            .addGroup(centerLayout.createSequentialGroup()
                                    .addGroup(centerLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(leftParent, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(right, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                                            .addComponent(middle, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addContainerGap(10, Short.MAX_VALUE))
            );
        }
        contentPane.add(center, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void generateCode(JTextField[] paramsTextFields) {
        if (paramsTextFields == null || paramsTextFields.length == 0) return;
        CodeGenerateServiceImpl codeGenerateService =new CodeGenerateServiceImpl();
        Map<String, String> params = new HashMap<>();
        String[] paramsList = getParamsList(parametersMap, selectedSubType);
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
    private void generateFile(String path, String fileType) {
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
            File file = new File(path + "\\" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            bufferedWriter.write(code);
            bufferedWriter.newLine();
            bufferedWriter.close();
            System.out.println(path + fileName + "文件已生成");
        } catch (IOException e) {
            System.out.println("Fail: create file!");
        }
    }

    private void generateReferenct() {
        Map<String, Object> referenceMap = (Map<String, Object>) JSON.parse(globalMap.get("referenceWebsite").toString());
        String referenceUrl = (String)referenceMap.get(selectedSubType);
        docTextArea.append(referenceUrl);
    }

    private void updateParamPanes() {
        String[] paramsList = getParamsList(parametersMap, selectedSubType);
        JTabbedPane[] paramsPane;
        JTextField[] paramsTextFields;
        if (paramsList != null && paramsList.length > 0) {
            int len = paramsList.length;
            paramsPane = new JTabbedPane[len];
            paramsTextFields = new JTextField[len];
            System.out.println("paramList: " + Arrays.toString(paramsList));
            for (int i = 0; i < len; i++) {
                paramsTextFields[i].setPreferredSize(new Dimension(49, 40));
                paramsPane[i].addTab(paramsList[i], paramsTextFields[i]);
                mid.add(paramsPane[i]);
            }
        } else {
            paramsTextFields = null;
        }

        //============= languageBox ========
        languageBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                languageTypeIndex = languageBox.getSelectedIndex();
            }
        });
        languageBox.setMinimumSize(new Dimension(30, 40));
        languageBox.setPreferredSize(new Dimension(30, 40));
        languageTypePane.addTab("要生成的示例代码类型", languageBox);

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
            generateCode(paramsTextFields);
            if (languageTypeIndex != 0) {
                String filePath = "";
                JFileChooser fileChooser = new JFileChooser("D:\\");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fileChooser.showOpenDialog(fileChooser);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    filePath= fileChooser.getSelectedFile().getAbsolutePath();
                }
                generateFile(filePath, languageBox.getSelectedItem().toString());
            }
            generateReferenct();
        });
        mid.add(buttonOK);
    }

    public static void main(String[] args) {
        CodeGenerator.init();
        new MyFrame();
    }
}
