package com.onlyhiedu.mobile.UI.Setting.activity;

import com.onlyhiedu.mobile.Base.SimpleActivity;
import com.onlyhiedu.mobile.R;

/**
 * Created by pengpeng on 2017/3/2.
 */

public class AboutActivity extends SimpleActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void initEventAndData() {
        setToolBar("关于我们");
    }
}
