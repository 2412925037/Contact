package edu.feicui.contactsupdate.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.base.adapter.BaseDataAdapter;
import edu.feicui.contactsupdate.entity.TelclassInfo;

/**
 * Created by Administrator on 2016/7/19.
 * 通讯大全电话分类适配器
 */
public class TelclassAdapter extends BaseDataAdapter<TelclassInfo>{

    //当前适配器内的数据集合(当前适配器适配工作只认此集合)
    private ArrayList<TelclassInfo> adapterDatas = new ArrayList<>();
    //添加数据到当前适配器集合
    public void addDataToAdapter(TelclassInfo e){
        if (e!=null){
            adapterDatas.add(e);
        }
    }
    //添加数据到当前适配器集合
    public void addDataToAdapter(List<TelclassInfo> e){
        if (e!=null){
            adapterDatas.addAll(e);
        }
    }
    //从适配器中获取数据
    public ArrayList<TelclassInfo> getDataFromAdapter(){
        return adapterDatas;
    }
    //删除当前适配器集合内数据
    public void clearDataToAdapter(){
        adapterDatas.clear();
    }

    //添加构造方法
    public TelclassAdapter(Context context){
        super(context);
    }

    @Override
    public int getCount() {
        return adapterDatas.size();
    }

    @Override
    public TelclassInfo getItem(int position) {
        return adapterDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.inflate_telmgr_listitem,null);
        }
        TextView tv_text = (TextView)convertView.findViewById(R.id.textview);
        tv_text.setText(getItem(position).name);
        return convertView;
    }
}
