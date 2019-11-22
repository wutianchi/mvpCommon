package com.huaxing.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.huaxing.common.R;
import com.huaxing.common.utils.Check;
import com.huaxing.common.widget.CustomDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private Unbinder unBinder;
    private CustomDialog customDialog;
    private LinearLayout llHeaderContent;
    private ImageView ivHeaderLeft;
    private TextView tvHeaderTitle;
    private ImageView ivHeaderImg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(this.getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, view);
        llHeaderContent = view.findViewById(R.id.ll_header_content);
        if (!Check.isNull(llHeaderContent)){
            ivHeaderLeft = view.findViewById(R.id.iv_header_left);
            tvHeaderTitle = view.findViewById(R.id.tv_header_title);
            ivHeaderImg = view.findViewById(R.id.iv_header_img);
        }
        initView(view);
        initData();
        return view;
    }
    //设置透明度
    protected int getTitleHeight() {
        if (!Check.isNull(llHeaderContent)) {
            llHeaderContent.setVisibility(View.VISIBLE);
        }
        return llHeaderContent.getHeight();
    }
    //设置透明度
    protected void setTitleAlpha(float alpha) {
        if (!Check.isNull(llHeaderContent)) {
            llHeaderContent.setVisibility(View.VISIBLE);
        }
        llHeaderContent.setAlpha(alpha);
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
    //隐藏左侧返回键
    protected void hideBackLeft() {
        if (!Check.isNull(llHeaderContent)) {
            llHeaderContent.setVisibility(View.VISIBLE);
        }
        if (Check.isNull(ivHeaderLeft)) {
            return;
        }
        ivHeaderLeft.setVisibility(View.GONE);
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
    public void showLoading() {
        if (customDialog==null)
            customDialog=new CustomDialog(getContext(), R.style.CustomDialog,"");
        if (!customDialog.isShowing()||!isHidden())
            customDialog.show();
    }
    public void showLoading(String str) {
        if (customDialog==null)
            customDialog=new CustomDialog(getContext(), R.style.CustomDialog,str);
        if (!customDialog.isShowing() || !isHidden())
            customDialog.show();
    }

    public void hideLoading() {
        if(customDialog != null && customDialog.isShowing()){
            customDialog.cancel();
        }
    }
    public boolean isLoading() {
        return customDialog.isShowing();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }


    protected abstract int getLayoutId();
    /**
     * 初始化视图
     *
     * @param view
     */
    protected abstract void initView(View view);
    /**
     * 初始化数据
     */
    protected abstract void initData();

}
