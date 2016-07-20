package edu.feicui.contactsupdate.main;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.base.BaseActivity;

/**
 * Created by Administrator on 2016/7/15.
 */
public class LogoActivity extends BaseActivity{
    ImageView img_logo;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        initView();
    }

    private Animation.AnimationListener animationListener = new Animation.AnimationListener(){
        //动画开始
        @Override
        public void onAnimationStart(Animation animation) {}

        //动画重复
        @Override
        public void onAnimationRepeat(Animation animation) {}

        //动画结束
        @Override
        public void onAnimationEnd(Animation animation) {
            startActivity(TelmsgActivity.class);
            finish();
        }
    };

    @Override
    protected void initView() {
        // 初始控件及动画
        img_logo = (ImageView) findViewById(R.id.iv_logo);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_logo);
        animation.setAnimationListener(animationListener);
        // logo图像控件开始动画
        img_logo.startAnimation(animation);
    }
}
