import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.*;
import org.jdesktop.swingx.*;
/*
 * Created by JFormDesigner on Sat Apr 25 07:45:17 CST 2020
 */



/**
 * @author sunwb uestc
 */
public class mainFrame extends JFrame implements Runnable{
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
    private JTabbedPane regionPane;
    private JTextField regionField;
    private JTabbedPane bucketPane;
    private JTextField bucketField;
    private JTabbedPane localpathPane;
    private JTextField localpathField;
    private JTabbedPane dstpatPane;
    private JTextField dstpathField;
    private JPanel vSpacer1;
    private JButton buttonOK;
    private JButton runButton;
    private JTabbedPane right;
    private JTabbedPane documentPane;
    private JScrollPane docScrollPane;
    private JTextArea docTextArea;
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
    private JScrollPane scrollPane2;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    /************* 自定义变量 ******************/
    private String bucket;
    private String localPath;
    private String destPath;
    private String code;
    private int bigTypeIndex = 0;
    private int subTypeIndex = 0;
    public static Map<Integer, String[]> typeMap;
    public mainFrame() {
//        initComponents();

    }

    @Override
    public void run() {
        initComponents();
    }

    public void init() {
//        typeMap = new HashMap<>();
//        typeMap.put(0, Const.TYPE1);
    }

    private void generationCode() {
        code = bucket + "\n" + localPath +"\n"+ destPath + "\n假装这里有代码！！！";
//        codeArea.append(code);
    }

    private void buttonOKMouseEntered(MouseEvent e) {
        // TODO add your code here
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        center = new JPanel();
        leftParent = new JTabbedPane();
        left = new JPanel();
        typePane = new JTabbedPane();
        bigType = new JComboBox();
        smallTypePane = new JTabbedPane();
        smallType = new JComboBox();
        middle = new JTabbedPane();
        mid = new JPanel();
        akPane = new JTabbedPane();
        akField = new JTextField();
        skPane = new JTabbedPane();
        skField = new JTextField();
        endpointPane = new JTabbedPane();
        endpointField = new JTextField();
        regionPane = new JTabbedPane();
        regionField = new JTextField();
        bucketPane = new JTabbedPane();
        bucketField = new JTextField();
        localpathPane = new JTabbedPane();
        localpathField = new JTextField();
        dstpatPane = new JTabbedPane();
        dstpathField = new JTextField();
        vSpacer1 = new JPanel(null);
        buttonOK = new JButton();
        runButton = new JButton();
        right = new JTabbedPane();
        documentPane = new JTabbedPane();
        docScrollPane = new JScrollPane();
        docTextArea = new JTextArea();
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
        scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();

        //======== this ========
        setMinimumSize(new Dimension(1000, 800));
        Container contentPane = getContentPane();
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
                        bigType.setMinimumSize(new Dimension(99, 15));
                        bigType.setPreferredSize(new Dimension(93, 12));
                        typePane.addTab("\u7c7b\u578b", bigType);
                    }
                    left.add(typePane);

                    //======== smallTypePane ========
                    {

                        //---- smallType ----
                        smallType.setMinimumSize(new Dimension(99, 15));
                        smallType.setPreferredSize(new Dimension(93, 12));
                        smallTypePane.addTab("\u5c0f\u7c7b", smallType);
                    }
                    left.add(smallTypePane);
                }
                leftParent.addTab("\u64cd\u4f5c\u7c7b\u578b", left);
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
                        endpointPane.addTab("endpoint", endpointField);
                    }
                    mid.add(endpointPane);

                    //======== regionPane ========
                    {

                        //---- regionField ----
                        regionField.setPreferredSize(new Dimension(49, 40));
                        regionPane.addTab("\u533a\u57df", regionField);
                    }
                    mid.add(regionPane);

                    //======== bucketPane ========
                    {

                        //---- bucketField ----
                        bucketField.setMinimumSize(new Dimension(49, 40));
                        bucketField.setPreferredSize(new Dimension(49, 40));
                        bucketPane.addTab("\u6876\u540d", bucketField);
                    }
                    mid.add(bucketPane);

                    //======== localpathPane ========
                    {

                        //---- localpathField ----
                        localpathField.setMinimumSize(new Dimension(49, 40));
                        localpathField.setPreferredSize(new Dimension(49, 40));
                        localpathPane.addTab("\u672c\u5730\u6587\u4ef6\u8def\u5f84", localpathField);
                    }
                    mid.add(localpathPane);

                    //======== dstpatPane ========
                    {

                        //---- dstpathField ----
                        dstpathField.setMinimumSize(new Dimension(49, 40));
                        dstpathField.setPreferredSize(new Dimension(49, 40));
                        dstpatPane.addTab("\u76ee\u6807\u8def\u5f84", dstpathField);
                    }
                    mid.add(dstpatPane);

                    //---- vSpacer1 ----
                    vSpacer1.setPreferredSize(new Dimension(10, 30));
                    mid.add(vSpacer1);

                    //---- buttonOK ----
                    buttonOK.setText("OK");
                    buttonOK.setAlignmentY(1.0F);
                    buttonOK.setAlignmentX(0.5F);
                    buttonOK.setMargin(new Insets(2, 2, 0, 0));
                    buttonOK.setPreferredSize(new Dimension(78, 40));
                    buttonOK.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            buttonOKMouseEntered(e);
                        }
                    });
                    mid.add(buttonOK);

                    //---- runButton ----
                    runButton.setText("run");
                    mid.add(runButton);
                }
                middle.addTab("\u5173\u952e\u53c2\u6570", mid);
            }

            //======== right ========
            {

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
                        pythonPane.addTab("\u586b\u5199\u5de6\u4fa7\u7684\u53c2\u6570\uff0c\u4f1a\u751f\u6210\u5bf9\u5e94SDK\u7684\u793a\u4f8b\u4ee3\u7801", pythonScrollPane);
                    }
                    examplePane.addTab("python", pythonPane);

                    //======== jsPane ========
                    {

                        //======== jsScrollPane ========
                        {
                            jsScrollPane.setViewportView(jsCodeArea);
                        }
                        jsPane.addTab("\u586b\u5199\u5de6\u4fa7\u7684\u53c2\u6570\uff0c\u4f1a\u751f\u6210\u5bf9\u5e94SDK\u7684\u793a\u4f8b\u4ee3\u7801", jsScrollPane);
                    }
                    examplePane.addTab("js", jsPane);

                    //======== goPane ========
                    {

                        //======== goScrollPane ========
                        {
                            goScrollPane.setViewportView(goCodeArea);
                        }
                        goPane.addTab("\u586b\u5199\u5de6\u4fa7\u7684\u53c2\u6570\uff0c\u4f1a\u751f\u6210\u5bf9\u5e94SDK\u7684\u793a\u4f8b\u4ee3\u7801", goScrollPane);
                    }
                    examplePane.addTab("go", goPane);
                }
                right.addTab("\u793a\u4f8b\u4ee3\u7801", examplePane);
            }

            //======== scrollPane2 ========
            {

                //---- textArea1 ----
                textArea1.setText("fff");
                scrollPane2.setViewportView(textArea1);
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
                                .addGap(15, 8, 8))
                            .addGroup(centerLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane2)
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
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(149, Short.MAX_VALUE))
            );
        }
        contentPane.add(center, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


}
