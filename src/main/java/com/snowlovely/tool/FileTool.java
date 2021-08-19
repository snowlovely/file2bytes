package com.snowlovely.tool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileTool extends Thread{
    @Override
    public void run() {
        try {
            generateBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateBytes() throws IOException {
        String filePath = FileToBytesGui.getFilePath();
        byte[] buf = new byte[1024];
        FileInputStream fileInputStream = null;
        String bytesArray = "";
        try {
             fileInputStream = new FileInputStream(filePath);
            int readLen = 0;
            while(( fileInputStream.read(buf) ) != -1) {
                String tempBytesArray = Arrays.toString(buf);
                if (bytesArray != null) {
                    bytesArray.replace("]","");
                    tempBytesArray.replace("[","");
                    bytesArray = bytesArray + tempBytesArray;
                } else {
                    bytesArray = tempBytesArray;
                }
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // 打印错误
            FileToBytesGui.setResultArea("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            // 打印错误
            FileToBytesGui.setResultArea("读取文件发生错误");
        } finally {
            fileInputStream.close();
        }
        FileToBytesGui.setResultArea(bytesArray);
    }
}
