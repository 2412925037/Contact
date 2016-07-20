package edu.feicui.contactsupdate.base;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;

import edu.feicui.contactsupdate.utils.LogUtil;

/**
 * Created by Administrator on 2016/7/18.
 * 基础Activity，用于定义基础方法，用于继承
 */
public abstract class BaseActivity extends Activity{
    private static final String TAG = "ActivityLife";
    public <T extends View> T _findViewById(int id){
        return (T)findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
    }

    /****************分割线管理Activity的生命周期*****************/
    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG,getClass().getSimpleName()+"onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG,getClass().getSimpleName()+"onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TAG,getClass().getSimpleName()+"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TAG,getClass().getSimpleName()+"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空Activity
        if (onlineActivityList.contains(this)){
            onlineActivityList.remove(this);
        }
        LogUtil.d(TAG,getClass().getSimpleName()+"onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(TAG,getClass().getSimpleName()+"onRestart()");
    }

    /**
     * 关闭Activity
     */
    @Override
    public void finish() {
        super.finish();
    }
    /**用来保存所在存在的Activity*/
    private static ArrayList<BaseActivity> onlineActivityList = new ArrayList<>();
    /**依次退出当前存在的所有Activity*/
    public static void finishAll(){
        Iterator<BaseActivity> iterator = onlineActivityList.iterator();
        while (iterator.hasNext()){
            iterator.next().finish();
        }
    }
    //普通跳转
    protected void startActivity(Class<?> targetClass){
        Intent intent = new Intent(this,targetClass);
        startActivity(intent);
    }
    //传递参数的跳转
    protected void startActivity(Class<?> targetClass,Bundle bundle){
        Intent intent = new Intent(this,targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //带动画的跳转
    protected void startActivity(Class<?> targetClass,int inAnimID,int outAnimID){
        Intent intent = new Intent(this,targetClass);
        startActivity(intent);
        overridePendingTransition(inAnimID,outAnimID);
    }
    //带动画跳转，并传递参数
    protected void startActivity(Class<?> targetClass,int inAnimID,int outAnimID,Bundle bundle){
        Intent intent = new Intent(this,targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(inAnimID,outAnimID);
    }

    protected abstract void initView();

}
