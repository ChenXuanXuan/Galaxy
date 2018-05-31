package com.mex.GalaxyChain.ui.market;


import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.event.MainEvent;
import com.mex.GalaxyChain.utils.EditUtils;
import com.mex.GalaxyChain.utils.IsEmptyUtils;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


@EActivity(R.layout.activity_find_pw_one)
public class FindPwStepOneActivity extends BaseActivity {


    @ViewById
    EditText et_phone_number;

    @ViewById
    ImageView ll_clear_phone;
    @ViewById(R.id.back)
    ImageView back;




    @Click({R.id.ll_clear_phone,R.id.tv_findPS_oneNext,R.id.back})
    void onClick(View view ){
        switch(view.getId()){
            case R.id.ll_clear_phone:
                et_phone_number.setText("");
                EditUtils.cursorFollow(et_phone_number);
                break;

                case R.id.tv_findPS_oneNext:
                 enterTwoStep();
                break;

            case R.id.back:
                finish();
                break;
        }
    }

    private void enterTwoStep() {
        String et_phone_numberString = et_phone_number.getText().toString().trim();
        if(IsEmptyUtils.isEmpty(et_phone_numberString)){
            ToastUtils.showTextInMiddle(R.string.phone_num_empty);
            return;
        }

       UIHelper.jumptoFindPwStepTwoActivity(this,et_phone_numberString);
       // finish();
    }


    @AfterTextChange
    protected void et_phone_number() {
        String et_phone_numberString = et_phone_number.getText().toString().trim();
        if (et_phone_numberString.length() > 0) {
            ll_clear_phone.setVisibility(View.VISIBLE);
        } else {
            ll_clear_phone.setVisibility(View.GONE);
        }
    }




    @FocusChange(R.id.et_phone_number)
    protected void onEditTextFocusListener(EditText e, boolean hasFocus){
        switch (e.getId()){
            case R.id.et_phone_number:
                if(hasFocus){
                    if(!isEmpty(e.getText().toString().trim())){
                        ll_clear_phone.setVisibility(View.VISIBLE);
                    }else{
                        ll_clear_phone.setVisibility(View.GONE);
                    }
                }
                break;
        }
    }



    @AfterViews
    void init(){
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(MainEvent event) {
        finish();
    }





}
