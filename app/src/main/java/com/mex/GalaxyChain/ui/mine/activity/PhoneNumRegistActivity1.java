package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.utils.CommonUtil;
import com.mex.GalaxyChain.utils.EditUtils;
import com.mex.GalaxyChain.utils.IsEmptyUtils;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_phone_register1)
public class PhoneNumRegistActivity1 extends BaseActivity {



    @ViewById
    EditText et_phone_number;

    @ViewById
    ImageView ll_clear_phone;

    @ViewById
    CheckBox ch_bx;

     @ViewById(R.id.back)
    ImageView back;





    @Click({R.id.tv_wanchen,R.id.ll_clear_phone,R.id.back})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_wanchen:
                wanchen();
                break;

            case R.id.ll_clear_phone:
                et_phone_number.setText("");
                EditUtils.cursorFollow(et_phone_number);
                break;

            case R.id.back:
                finish();
                break;

        }



    }

    private void wanchen() {
        String et_phone_numberString = et_phone_number.getText().toString().trim();
          if(IsEmptyUtils.isEmpty(et_phone_numberString)){
              ToastUtils.showTextInMiddle(R.string.phone_num_empty);
              return;
          }

           if(!ch_bx.isChecked()){
               CommonUtil.showSimpleInfo(getString(R.string.toast_rule_uncheck));
               return;
           }

        UIHelper.toRegistActivity2(this,et_phone_numberString);


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






}
