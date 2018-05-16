package com.mex.GalaxyChain.mychart.chart.linechat.stockchart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import rx.subscriptions.CompositeSubscription;
//import butterknife.ButterKnife;


public class BaseActivity extends Activity {
    public final  String TAG =this.getClass().getSimpleName();
    protected CompositeSubscription mCompositeSubscription;
    protected Activity activity;
    protected Toast mToast = null;
    /*@Inject
    public final ThreadLocal<ClientApi> clientApi = new ThreadLocal<ClientApi>();
    @Inject
    public SharedPreferences sharedPreferences;*/
    /*@Inject
    Activity activity;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        mCompositeSubscription = new CompositeSubscription();
//        activityComponent = DaggerActivityComponent.builder().appComponent(((App) getApplication()).getApplicationComponent())
//                .activityModule(new ActivityModule(this))
//                .build();
//        activityComponent.inject(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
//        ButterKnife.bind(this);

    }

    public void showToast(String content) {
        if (mToast == null) {
            mToast = Toast.makeText(this, content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.unbind(BaseActivity.this);
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
