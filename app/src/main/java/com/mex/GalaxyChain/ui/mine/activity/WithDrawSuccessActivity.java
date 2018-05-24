package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_withdraw_success)
public class WithDrawSuccessActivity extends BaseActivity {

    @ViewById(R.id.mTitle)
    TextView mTitle;

    @ViewById(R.id.tv_confirm)
    TextView tv_confirm;



    @ViewById(R.id.back)
    ImageView back;

    @ViewById(R.id.tv_drawed_YYCount)
    TextView tv_drawed_YYCount;



    @Click({R.id.back,R.id.tv_confirm})
    void onClick(View view){
      switch (view.getId()){
          case  R.id.back:
              finish();
              break;
          case  R.id.tv_confirm:
              int type= Constants.MONEYFLOW_WITHDRAW;
               AccountMoneyFlowActivity_.intent(getActivity()).type(2).start();
             // UIHelper.ToAccountMoneyFlowActivity(WithDrawSuccessActivity.this,type);

              break;
      }

    }

    @AfterViews
    void init(){
        mTitle.setText("提现审核");
        back.setVisibility(View.VISIBLE);
        tv_drawed_YYCount.setText(UserGolbal.getInstance().drawedYYAcount+"YY");//已提现的YY币
    }


}
