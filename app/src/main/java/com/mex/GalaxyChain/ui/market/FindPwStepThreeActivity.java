package com.mex.GalaxyChain.ui.market;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.ResetPWBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.CreatSignUtils;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.Date;
import java.util.HashMap;

import rx.Subscriber;

@EActivity(R.layout.activity_find_pw_three)
public class FindPwStepThreeActivity extends BaseActivity {
@Extra
String  mobile;
 @Extra
  String  verfiycode;

    @ViewById
    EditText et_newPW,et_confirm_newPS;

    @ViewById
    TextView tv_commit_changePS;


    @Click({R.id.tv_commit_changePS})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit_changePS:
                commitChangePW();
                break;
        }
    }

    private void commitChangePW() {

        if(isEmpty(mobile)){
            ToastUtils.showErrorImage("手机号码不能为空");
            return;
        }
         if(isEmpty(verfiycode)){
             ToastUtils.showErrorImage("验证码不能为空");
             return;
         }

         String newPW=et_newPW.getText().toString().trim();
         if(isEmpty(newPW)){
             ToastUtils.showErrorImage("输入的新密码不能为空");
             return;
         }

        String confirm_newPW=et_confirm_newPS.getText().toString().trim();
        if(isEmpty(confirm_newPW)){
            ToastUtils.showErrorImage("再次输入的新密码不能为空");
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        final String  country = "86";
        Date dt = new Date();
        String timeStamp = dt.getTime() + "";
        params.put("mobile", mobile);
        params.put("country", country);
        params.put("verfiycode", verfiycode);
        params.put("loginpwd", confirm_newPW);
        params.put("time", timeStamp);
        String sign = CreatSignUtils.creatSign(params);

        UserRepo.getInstance().resetPhonePW(mobile,country,verfiycode,confirm_newPW,timeStamp,sign)
                               .subscribe(new Subscriber<ResetPWBean>() {
                                   @Override
                                   public void onCompleted() { }

                                   @Override
                                   public void onError(Throwable e) {
                                       ToastUtils.showErrorImage("重置密码失败请重试"); return;
                                   }

                                   @Override
                                   public void onNext(ResetPWBean resetPWBean) {
                                          if(resetPWBean.getCode().equals("0")){
                                              ToastUtils.showCorrectImage("重置密码成功");
                                               finish();

                                          } else if(resetPWBean.getCode().equals("100004")){
                                              ToastUtils.showErrorImage("请求参数不合法"); return;
                                          }
                                          else{
                                              ToastUtils.showErrorImage("重置密码失败请重试"); return;
                                          }
                                   }
                               });



       /* UserRepo.getInstance().resetPhonePW(mobile,country,verfiycode,confirm_newPW,timeStamp,sign)
                              .subscribe(new HttpSubscriber<ReponseData<BaseBean>>() {
                                  @Override
                                  protected void onSuccess(ReponseData<BaseBean> repoData) {
                                      LogUtils.d(repoData.toString());
                                      ToastUtils.showCorrectImage(repoData.getCode()+"重置登陆密码成功");
                                      finish();
                                  }

                                  @Override
                                  protected void onFailure(ApiException e) {
                                      ToastUtils.showErrorImage(e.getCode()+"重置登陆密码失败请重试");
                                  }
                              });*/
    }
}
