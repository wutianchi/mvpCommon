package com.huaxing.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.huaxing.common.R;
import com.huaxing.common.utils.Check;
import com.huaxing.common.utils.statusbar.StatusBarUtil;
import com.huaxing.common.widget.CustomDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    private Unbinder unbinder;
    private CustomDialog customDialog;
    private LinearLayout llHeaderContent;
    private ImageView ivHeaderLeft;
    private TextView tvHeaderTitle;
    private ImageView ivHeaderImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //沉浸式代码配置
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);

        setContentView(getContentViewId());
        unbinder = ButterKnife.bind(this);
        llHeaderContent = findViewById(R.id.ll_header_content);
        if (!Check.isNull(llHeaderContent)){
            ivHeaderLeft = findViewById(R.id.iv_header_left);
            tvHeaderTitle = findViewById(R.id.tv_header_title);
            ivHeaderImg = findViewById(R.id.iv_header_img);
        }
        initView();
        setDatas();
    }
    //设置标题
    protected void setTitleText(String title) {
        if (!Check.isNull(llHeaderContent)) {
            llHeaderContent.setVisibility(View.VISIBLE);
        }
        if (Check.isNull(tvHeaderTitle)) {
            return;
        }
        tvHeaderTitle.setText(title);
    }
    //设置左侧点击事件
    protected void setBackClick(View.OnClickListener onClickListener) {
        if (!Check.isNull(llHeaderContent)) {
            llHeaderContent.setVisibility(View.VISIBLE);
        }
        if (Check.isNull(ivHeaderLeft)) {
            return;
        }
        if (ivHeaderLeft.getVisibility() != View.VISIBLE) {
            ivHeaderLeft.setVisibility(View.VISIBLE);
        }
        ivHeaderLeft.setOnClickListener(onClickListener);
    }
    //设置右侧点击事件
    protected void setImgRightIcon(View.OnClickListener onClickListener) {
        if (!Check.isNull(llHeaderContent)) {
            llHeaderContent.setVisibility(View.VISIBLE);
        }
        if (Check.isNull(ivHeaderImg)) {
            return;
        }
        if (ivHeaderImg.getVisibility() != View.VISIBLE) {
            ivHeaderImg.setVisibility(View.VISIBLE);
        }
        ivHeaderImg.setOnClickListener(onClickListener);
    }
    //设置标题右侧图标并设置点击事件
    protected void setImgRightIcon(int imgRightIcon,View.OnClickListener onClickListener) {
        if (!Check.isNull(llHeaderContent)) {
            llHeaderContent.setVisibility(View.VISIBLE);
        }
        if (Check.isNull(ivHeaderImg)) {
            return;
        }
        if (ivHeaderImg.getVisibility() != View.VISIBLE) {
            ivHeaderImg.setVisibility(View.VISIBLE);
        }
        ivHeaderImg.setImageResource(imgRightIcon);
        ivHeaderImg.setOnClickListener(onClickListener);
    }

    public void showProgress() {
        if (customDialog==null)
            customDialog=new CustomDialog(this, R.style.CustomDialog,"");
        if (!customDialog.isShowing() || !isFinishing())
            customDialog.show();
    }
    public void showProgress(String str) {
        if (customDialog==null)
            customDialog=new CustomDialog(this, R.style.CustomDialog,str);
        if (!customDialog.isShowing() || !isFinishing())
            customDialog.show();
    }

    public void hideProgress() {
        if(customDialog != null && customDialog.isShowing()){
            customDialog.cancel();
        }
    }
    public boolean isProgress() {
        return customDialog.isShowing();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
        hideProgress();
    }

    protected abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void setDatas();
}
