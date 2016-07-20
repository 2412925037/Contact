package edu.feicui.contactsupdate.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/19.
 * Toast管理工具类，有且只有一个Toast对象进行展示操作
 */
public class ToastUtil {
    private static Toast toast;
    public static void show(Context context,String text){
        if (toast == null){
            toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }
}
