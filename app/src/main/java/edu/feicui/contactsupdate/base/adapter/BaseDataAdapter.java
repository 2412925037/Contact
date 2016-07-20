package edu.feicui.contactsupdate.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 * 基础适配器，根据集合内的数据，进行适配
 */
public abstract class BaseDataAdapter<E> extends BaseAdapter{
    private ArrayList<E> adapterDatas = new ArrayList<>();
    //在BaseDataAdapter类中传递参数Context,并创建Context，用于承接上下文
    protected Context context;
    //定义布局加载器，并在构造器中实例化
    protected LayoutInflater layoutInflater;
    public BaseDataAdapter(Context context){
        this.context = context;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    /**为ArrayList<E>添加get,set方法*/
    public ArrayList<E> getDataFromAdapter(){
        return adapterDatas;
    }
    public void setDataToAdapter(List<E> e){
        adapterDatas.clear();
        if (e!=null){
            adapterDatas.addAll(e);
        }
    }
    //添加数据到当前适配器集合
    public void addDataToAdapter(E e){
        if (e!=null){
            adapterDatas.add(e);
        }
    }
    //添加数据到当前适配器集合
    public void addDataToAdapter(List<E> e){
        if (e!=null){
            adapterDatas.addAll(e);
        }
    }
    /************删除当前适配器集合***********/
    public void clearAdapter(){
        adapterDatas.clear();
    }
    public void removeDataFromAdapter(E e){
        adapterDatas.remove(e);
    }
    public void removeDataFromAdapter(int index){
        adapterDatas.remove(index);
    }
    @Override
    public int getCount() {
        return adapterDatas.size();
    }

    @Override
    public E getItem(int position) {
        return adapterDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
