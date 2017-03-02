package com.onlyhiedu.mobile.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.onlyhiedu.mobile.App.App;
import com.onlyhiedu.mobile.App.AppManager;
import com.onlyhiedu.mobile.Dagger.Component.ActivityComponent;
import com.onlyhiedu.mobile.Dagger.Component.DaggerActivityComponent;
import com.onlyhiedu.mobile.Dagger.Modul.ActivityModule;
import com.onlyhiedu.mobile.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * MVP activity基类
 * Created by xuwc on 2016/11/24.
 */
public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BaseView {

    @Inject
    protected T mPresenter;
    protected Activity mContext;

    private Unbinder mUnBinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        AppManager.getAppManager().addActivity(this);
        initView();
        initData();
    }



    protected void setToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setText(title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.mipmap.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();

    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        mUnBinder.unbind();

        AppManager.getAppManager().removeActivity(this);
    }


    protected abstract void initInject();

    protected abstract int getLayout();

    protected void initView() {}

    protected void initData() {}
}
