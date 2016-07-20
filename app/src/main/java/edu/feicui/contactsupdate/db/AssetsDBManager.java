package edu.feicui.contactsupdate.db;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import edu.feicui.contactsupdate.utils.LogUtil;

/**
 * Created by Administrator on 2016/7/18.
 * 用来操作本地Assets文件夹内db数据的管理类
 */
public class AssetsDBManager {
    /**
     * 复制本地assets中的db文件到指定目录中
     * @param context
     * @param path
     * @param toFile
     */
    public static void copyAssetsFileToFile(Context context,String path,File toFile){
        LogUtil.d("AssetsDBManager","copyAssetsFiletoFile start");
        LogUtil.d("AssetsDbManager","file path:" + path);
        LogUtil.d("AssetsDBManager","toFile path:" + toFile.getAbsolutePath());
        InputStream inputStream = null;
        //输入流(用来读取当前项目中的Assets内的db文本)
        BufferedInputStream bufferedInputStream = null;
        //输出流
        BufferedOutputStream bufferedOutputStream = null;
        try {
            inputStream = context.getAssets().open(path);
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(toFile,false));
            //IO操作
            int len = 0;
            byte[] buff = new byte[2*1024];
            while ((len = bufferedInputStream.read(buff))!=-1){
                bufferedOutputStream.write(buff,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                bufferedInputStream.close();
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            LogUtil.d("AssetsDBManager","copyAssetsFiletoFile end");
        }

    }

}
