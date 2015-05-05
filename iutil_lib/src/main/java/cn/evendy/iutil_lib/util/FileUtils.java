package cn.evendy.iutil_lib.util;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author evendy
 * @time: 2015/4/25 15:15
 * @mail 244085027@qq.com
 */
public class FileUtils {
    private FileUtils(){}
    /**
     * 判断SD是否装载
     *
     * @return
     */
    public static boolean isSDCardExist() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * 创建文件夹
     *
     * @param path
     *
     */
    public static boolean createDirFile(String path) {
        if(path==null||path.trim().equals(""))
            return false;
        return createDirFile(new File(path));
    }

    public static boolean createDirFile(File dir){
        return createFile(dir)!=null;
    }

    /**
     * 创建文件
     *
     * @param path
     * @return File
     */
    public static File createFile(String path) {
        if(path==null||path.trim().equals(""))
            return null;
        return createFile(new File(path));
    }

    public static File createFile(File file){
        if (!file.exists()) {
            try {
                boolean flag;
                if(file.isDirectory()){
                    flag=file.mkdirs();
                }else{
                    flag=file.createNewFile();
                }
                if(flag){
                    return file;
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file;

    }

    /**
     * 删除文件夹,包括内部的所有文件
     *
     * @param folderPath
     *
     */
    public static boolean delFolder(String folderPath) {
        if(folderPath==null||folderPath.trim().equals(""))
            return false;
        return delFolder(new File(folderPath));
    }

    public static boolean delFolder(File folder) {
        delDirFiles(folder);
        return folder.delete();
    }

    /**
     * 删除文件夹内部的所有文件
     *
     * @param folder
     */
    public static boolean delDirFiles(File folder) {
        if (!folder.exists()) {
            return true;
        }
        if (!folder.isDirectory()) {
            return true;
        }
        File[] files=folder.listFiles();
        for(File child:files){
            if(child.isDirectory()){
                delFolder(child);
            }else{
                child.delete();
            }
        }
        return true;
    }


    /**
     * 获得文件的大小
     *
     * @param size
     * @return
     */
    public static String formatFileSize(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "unknowSize";
        if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1024*1024) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1024*1024*1024) {
            fileSizeString = df.format((double) size / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 通过文件目录获取文件
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static File getFilePath(String filePath, String fileName) {
        createDirFile(filePath);
        return createFile(new File(filePath,fileName));
    }

}
