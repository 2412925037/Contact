package edu.feicui.contactsupdate.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.adapter.TelclassAdapter;
import edu.feicui.contactsupdate.base.BaseActionbarActivity;
import edu.feicui.contactsupdate.db.AssetsDBManager;
import edu.feicui.contactsupdate.db.DBReader;
import edu.feicui.contactsupdate.entity.TelclassInfo;
import edu.feicui.contactsupdate.utils.DialogUtil;
import edu.feicui.contactsupdate.utils.ToastUtil;

/**
 * Created by Administrator on 2016/7/18.
 * 通讯录大全页面
 */
public class TelmsgActivity extends BaseActionbarActivity implements AdapterView.OnItemClickListener{
    /**全局定义ListView控件*/
    private ListView listView;
    /**全局定义TelclassAdapter*/
    private TelclassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telmsg);
        setActionBar(R.string.title_activity_telmsg, R.drawable.btn_homeasup_default, NULL_ID);
        initView();
    }
    @Override
    public void initView(){
        //初始控件
        listView = _findViewById(R.id.listview);
        adapter = new TelclassAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    //初始化数据库文件
    private void initAppDBFile(){
        //检测是否存在通讯大全DB文件
        if (!DBReader.isExistsTeldbFile()){
            try {
                //将本地项目中的Assets/db/commonnum.db文件复制写到DBRead.telfile文件中
                AssetsDBManager.copyAssetsFileToFile(getApplicationContext(),"db/commonnum.db",DBReader.telFile);
            }catch (Exception e){
                ToastUtil.show(this,"初始通讯大全数据库文件异常");
            }
        }
    }

    //重写onResume方法初始化展示的数据
    @Override
    protected void onResume() {
        super.onResume();
        //适配数据
        initAppDBFile();
        adapter.clearDataToAdapter();
        adapter.addDataToAdapter(new TelclassInfo("本地电话",0));//本地电话分类
        try{
            adapter.addDataToAdapter(DBReader.readTeldbClasslist());
        }catch (Exception e){
            e.printStackTrace();
        }//db库内的电话分类
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //本地电话
        if (position == 0){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            startActivity(intent);
            return;
        }
        //取出当前选择的选项实体内容
        TelclassInfo classInfo = adapter.getItem(position);
        //跳转至电话浏览页面，且传入idx,并且根据idx跳转
        Bundle bundle = new Bundle();
        bundle.putInt("idx",classInfo.idx);
        startActivity(TellistActivity.class,bundle);
    }

    private long preTime = 0;
    private long curTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            curTime = System.currentTimeMillis();
//            if (curTime - preTime <= 800) {
//                finish();
//                System.exit(0);
//            }
//            ToastUtil.show(getApplicationContext(), "再接一次退出");
//            preTime = curTime;
//            return true;
//        }
        DialogUtil dialogUtil = new DialogUtil(this);
        dialogUtil.showDialog();
        return super.onKeyDown(keyCode, event);
    }
    public void onClick(View view){

    }
}
