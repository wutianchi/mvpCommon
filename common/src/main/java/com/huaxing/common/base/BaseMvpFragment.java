package com.huaxing.common.base;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

public abstract class BaseMvpFragment<T extends BasePresenter>  extends BaseFragment implements BaseView{
    protected T mPresenter;

    @Override
    protected void initData() {
        if (null!=mPresenter)
            mPresenter.attachView(this);
        else {
            mPresenter = (T) new DefultPresenter();
            mPresenter.attachView(this);
        }
        initDatas();
    }
    public void setPresenter(@Nullable T presenter) {
        this.mPresenter = presenter;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
    /**
     * 初始化数据
     */
    protected abstract void initDatas();
    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T>
     * @return
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
