package edu.feicui.contactsupdate.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

import edu.feicui.contactsupdate.entity.TelclassInfo;
import edu.feicui.contactsupdate.entity.TelnumberInfo;
import edu.feicui.contactsupdate.utils.LogUtil;

/**
 * Created by Administrator on 2016/7/18.
 * 数据库文件的读取操作
 */
public class DBReader {
    /**通讯录大全File*/
    public static File telFile;
    static {
        //默认位置
        String dbFileDir = "data/data/edu.feicui.contactsupdate/databases/";
        /**
         * 存储卡
         * String sdcardState = Environment.getExternalStorageState()
         * if(sdcardState.equals(Environment.MEDIA_MOUNTED)){
         * dbFileDir = Environment.getExternalStorageDirectory()+"/azyphone/cache"*/
        File fileDir = new File(dbFileDir);
        fileDir.mkdirs();//文件目录的创建
        telFile = new File(dbFileDir,"commonmum.db");
        LogUtil.d("DBRead","telFile dir path:" + dbFileDir);
    }

    /**检测是否存在通讯录大全DB文件*/
    public static boolean isExistsTeldbFile(){
        //没有通讯大全File
        File toFile = DBReader.telFile;
        if (!toFile.exists()||toFile.length()<=0){
            return false;
        }
        return true;
    }

    /**读取telFile这个数据库文件中的classlist这个表内的数据*/
    public static ArrayList<TelclassInfo> readTeldbClasslist(){
        ArrayList<TelclassInfo> telclassInfos = new ArrayList<>();
        //打开db文件
        SQLiteDatabase db = null;
        //执行查询的SQL语句select * from classlist
        Cursor cursor = null;
        try {
            db = SQLiteDatabase.openOrCreateDatabase(telFile,null);
            cursor = db.rawQuery("select * from classlist",null);
            LogUtil.d("DBRead","read teldb classlist size:"+cursor.getCount());
            if (cursor.moveToFirst()){
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    //idx为classlist表中的电话的ID,根据idx值进行指定页面的跳转
                    int idx = cursor.getInt(cursor.getColumnIndex("idx"));
                    TelclassInfo telclassInfo = new TelclassInfo(name,idx);
                    telclassInfos.add(telclassInfo);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                cursor.close();
                db.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
            LogUtil.d("DBRead","read teldb classlist end:"+telclassInfos.size());
        }
        return telclassInfos;
    }

    /**读取telFile这个数据文件中的tableN这个表内的数据(商家名称和电话号码)*/
    public static ArrayList<TelnumberInfo> readTeldbTable(int idx){
        ArrayList<TelnumberInfo> numberInfos = new ArrayList<>();
        //idx为classlist表中电话的id，根据idx值进行指定页面的跳转
        String sql = "select * from table"+idx;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try{
            //打开db文件
            db = SQLiteDatabase.openOrCreateDatabase(telFile,null);
            //执行查询的SQL语句select * from table + idx
            cursor = db.rawQuery(sql,null);
            LogUtil.d("DBRead","read teldb number table size:" + cursor.getCount());
            if (cursor.moveToFirst()){
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String number = cursor.getString(cursor.getColumnIndex("number"));
                    TelnumberInfo numberInfo = new TelnumberInfo(name,number);
                    numberInfos.add(numberInfo);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                cursor.close();
                db.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
            LogUtil.d("DBRead","read teldb number table end:"+ numberInfos.size());
        }
        return numberInfos;
    }
}
