package edu.feicui.contactsupdate.main;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.adapter.TellistAdapter;
import edu.feicui.contactsupdate.base.BaseActionbarActivity;
import edu.feicui.contactsupdate.db.DBReader;
import edu.feicui.contactsupdate.utils.DialogUtil;

/**
 * Created by Administrator on 2016/7/19.
 * 通讯大全电话号码浏览页面
 */
public class TellistActivity extends BaseActionbarActivity implements AdapterView.OnItemClickListener {
    private ListView listView;//全局定义listview
    private TellistAdapter adapter;//全局定义TellistAdapter
    private int idx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tellist);
        initView();
    }

    @Override
    protected void initView() {
        //获取数据用来判断是显示哪一种分类的电话号码列表
        idx = getIntent().getIntExtra("idx", -1);
        //初始化控件
        listView = _findViewById(R.id.listview);
        adapter = new TellistAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = adapter.getItem(position).name;
        String number = adapter.getItem(position).number;
        DialogUtil dialogUtil = new DialogUtil(this);
        dialogUtil.showDialog(name,number);
    }

    //重写onResume，添加数据
    @Override
    protected void onResume() {
        super.onResume();
        //添加数据
        try {
            adapter.replaceDataAdapter(DBReader.readTeldbTable(idx));
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

}
