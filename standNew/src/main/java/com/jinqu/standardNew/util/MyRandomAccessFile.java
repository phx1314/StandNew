package com.jinqu.standardNew.util;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * 文件分割、合并
 *
 * @author Administrator
 */
public class MyRandomAccessFile {

    public int size = 1024 * 1024; //要分割的大小 1M
    public long frist = 0; //上一次分割的节点位置


    public  MyRandomAccessFile() {

    }


    /**
     * 文件分割
     *
     * @param inputFile 要分割的文件
     * @throws IOException
     */
    public   void segmentation(File inputFile, File outFileDirectory) throws IOException {
        if (inputFile != null && !inputFile.exists()) {
            return;
        }
        if (inputFile.isDirectory()) {
            return;
        }
        try (
                RandomAccessFile radaf = new RandomAccessFile(inputFile, "r");
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outFileDirectory));
        ) {
            radaf.seek(frist);
            byte[] b = new byte[size];
            int last;
            while ((last = radaf.read(b)) != -1) {
                outputStream.write(b, 0, last);
                outputStream.flush();
                frist += last;
                break;
            }
        }
    }


    /**
     * 根据要分割文件的大小除于分割大小，得到要分割成几份，
     * 并得到分割几份后的文件名称
     *
     * @param inputFile    要分割文件
     * @param fileInitName 分割几份后要叫的文件名称
     * @return 分割几份后的文件名称
     */
    public   List<String> getFileName(File inputFile, String fileInitName) {
        long length = inputFile.length();
        double ceil = Math.ceil(length * 1.0 / size);
        List<String> name = new ArrayList<>((int) ceil);
        for (int i = 0; i < (int) ceil; i++) {
//这里加上盘，只是为了方便
            name.add(Environment.getExternalStorageDirectory() + "/" + i + fileInitName);
        }
        return name;
    }


    /**
     * 合并文件
     *
     * @param listName 要合并的文件名
     * @param file     输出的文件地址
     * @throws IOException
     */
    public void mergeFile(List<String> listName, File file) throws IOException {

        Vector<InputStream> v = new Vector<>();
        for (String string : listName) {
            v.add(new BufferedInputStream(new FileInputStream(new File(string))));
        }
        try (
                // true = 文件输出的追加
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file, true));
                //栈
                SequenceInputStream sequence = new SequenceInputStream(v.elements());
        ) {
            byte[] b = new byte[size];
            int len = 0;
            while ((len = sequence.read(b)) != -1) {
                bos.write(b, 0, len);
                bos.flush();
            }
        }
    }

//    public static void main(String[] args) {
//        MyRandomAccessFile my = new MyRandomAccessFile();
////分割文件
//        File inputFile = new File("G:/123456789.mp4");
////文本分割过后，可直接打开，但视频、音频、压缩文件等，分割后，不能直接打开。
////= new File("G:/123456789.txt");
////分割后的文件名
//        List<String> fileName = my.getFileName(inputFile, "zsf.mp4");
////文件分割，并输出
//        for (String string : fileName) {
//            try {
//                my.segmentation(inputFile, new File(string));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
////文件合并
//        try {
//            my.mergeFile(fileName, new File("G:/98aa.mp4"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}