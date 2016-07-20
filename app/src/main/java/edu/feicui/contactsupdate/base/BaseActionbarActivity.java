package edu.feicui.contactsupdate.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.utils.LogUtil;

/**
 * Created by Administrator on 2016/7/20.
 * 带ActionBar控件数据初始化方法及onClick监听的基础Activity
 */
public abstract class BaseActionbarActivity extends BaseActivity{
    public static final int NULL_ID = -1;

    /**
     * 设置ActionBar控件上的数据
     * @param resIdTitle 中间主标题文本id，没有标题时可使用null_id
     * @param resIdLeft 左侧图标资源id,没有图标时可使用null_id
     * @param resIdRight 右侧图标资源id,没有图标时可使用null_id
     */
    public void setActionBar(int resIdTitle,int resIdLeft,int resIdRight){
        try {
            TextView tv_action_title = (TextView)findViewById(R.id.tv_actionbar_title);
            ImageView iv_action_left = _findViewById(R.id.iv_action_left);
            ImageView iv_action_right = _findViewById(R.id.iv_action_right);
            if (resIdLeft!=NULL_ID){
                iv_action_left.setImageResource(resIdLeft);
            }else{
                iv_action_left.setVisibility(View.INVISIBLE);
            }
            if (resIdRight!=NULL_ID){
                iv_action_right.setImageResource(resIdRight);
            }else{
                iv_action_right.setVisibility(View.INVISIBLE);
            }
            if (resIdTitle!=NULL_ID){
                tv_action_title.setText(resIdTitle);
            }
        }catch (Exception e){
            LogUtil.w("BaseActionBarActivity","ActionBar有异常,是否当前页面没有" +
                    "include==> include_actionbar.xml布局");
        }
    }
}
