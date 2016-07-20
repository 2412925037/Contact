package edu.feicui.contactsupdate.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.base.adapter.BaseDataAdapter;
import edu.feicui.contactsupdate.entity.TelnumberInfo;

/**
 * Created by Administrator on 2016/7/19.
 * 通讯大全电话列表适配器
 */
public class TellistAdapter extends BaseDataAdapter<TelnumberInfo>{

    //当前适配器内的数据集合(当前适配器工作只认此集合)
    private ArrayList<TelnumberInfo> adapterDatas = new ArrayList<>();
    //添加数据到当前适配器集合
    public void addDataToAdapter(TelnumberInfo e){
        if (e!=null){
            adapterDatas.add(e);
        }
    }
    //添加数据到当前适配器集合
    public void addDataToAdapter(List<TelnumberInfo> e){
        if (e!=null){
            adapterDatas.addAll(e);
        }
    }
    //添加数据到当前适配器集合
    public void replaceDataAdapter(List<TelnumberInfo> e){
        if (e!=null){
            adapterDatas.clear();
            adapterDatas.addAll(e);
        }
    }
    //获取当前适配器的集合
    public ArrayList<TelnumberInfo> getDataFromAdapter(){
        return adapterDatas;
    }

    public TellistAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCount() {
        return adapterDatas.size();
    }

    @Override
    public TelnumberInfo getItem(int position) {
        return adapterDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.inflate_tellist_listitem,null);
        }
        TextView tv_name = (TextView)convertView.findViewById(R.id.tv_name);
        TextView tv_number = (TextView)convertView.findViewById(R.id.tv_number);
        tv_name.setText(getItem(position).name);
        tv_number.setText(getItem(position).number);
        return convertView;
    }
}
