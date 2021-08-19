package com.snowlovely.tool;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FileToBytesGui extends JFrame{
    static JTextField filePathText = new JTextField("C:\\HelloWorld.txt");
    JButton chooseFileBtn = new JButton("选择文件");
    JButton generateBytesBtn = new JButton("生成");
    JLabel filePathLabel = new JLabel("FilePath:");
    FileDialog fileLoadDialog = new FileDialog(this, "选择需要加载的文件", FileDialog.LOAD);
    static JTextArea resultArea = new JTextArea("",17,60);

    private void init() {
        this.setTitle("File2Bytes（将文件转化为字节数组）");
        // 设置 layout 为 GridBagLayout
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        //  weithtx = 0 表示窗口放大时, 该组件扩展系数为 0, 即不扩展
        gbc.weightx = 0;
        // inset 表设置组件之间的空隙
        gbc.insets = new Insets(2,3,4,5);
        // addComponet 函数用来添加组件到panel中
        addComponent(this, filePathLabel, gbl, gbc);

        // gridwidth 设置表示设置组件占几个组件宽度
        gbc.gridwidth = 8;
        // weithtx = 0 表示窗口放大时, 该组件扩展系数为 0, 即不扩展
        gbc.weightx = 1;
        addComponent(this, filePathText, gbl, gbc);

        gbc.gridwidth = 1;
        gbc.weightx = 0;
        addComponent(this, chooseFileBtn, gbl, gbc);

        //  gbc.gridwidth=GridBagConstraints.REMAINDER; 表示下面添加的组件是这一行的最后一个组件
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        addComponent(this, generateBytesBtn, gbl, gbc);

        gbc.gridheight = 10;
        gbc.weighty = 3;
        JScrollPane resultAreaJScrollPane = new JScrollPane(resultArea);//添加滚动条
        addComponent(this, resultAreaJScrollPane, gbl, gbc);

        // 设置自动换行
        resultArea.setLineWrap(true);

        //给按钮添加事件  选择文件
        chooseFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileLoadDialog.setVisible(true);
                //打印用户选择的文件路径和名称
                String directoryPath = fileLoadDialog.getDirectory();
                String file = fileLoadDialog.getFile();
                String filePath = directoryPath + file;
                filePathText.setText(filePath);
                System.out.println("用户选择的文件路径:" + directoryPath);
                System.out.println("用户选择的文件名称:" + file);
            }
        });

        generateBytesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileTool().run();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置frame最佳大小并可见
        this.pack();
        this.setVisible(true);
    }

    public static String getFilePath() {
        return filePathText.getText().trim();
    }

    public static void setResultArea(String resultBytes) {
        resultArea.setText(resultBytes);
    }

    private void addComponent(Container container, Component c, GridBagLayout gridBagLayout, GridBagConstraints gridBagConstraints){
        gridBagLayout.setConstraints(c,gridBagConstraints);
        container.add(c);
    }

    public FileToBytesGui() {
        init();
    }


    public static void main(String[] args) {
        JFrame frame = new FileToBytesGui();
        try {
            UIManager.setLookAndFeel(new FlatLightLaf() );
            SwingUtilities.updateComponentTreeUI(frame.getContentPane());
            FlatLaf.updateUI();
            frame.setVisible(true);
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }
}
