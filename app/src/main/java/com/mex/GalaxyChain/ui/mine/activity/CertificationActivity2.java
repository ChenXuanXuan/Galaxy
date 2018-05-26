package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.RealNameAuthBean;
import com.mex.GalaxyChain.bean.requestbean.CertificationBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.CheckUtils;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

/*
*
*  实名认证(高杰)
*
* */
@EActivity(R.layout.activity_certification2)
public class CertificationActivity2 extends BaseActivity {

    @ViewById(R.id.back)
    ImageView back;


    @ViewById(R.id.mTitle)
    TextView mTitle;

    @ViewById(R.id.tv_commit_id_certification)
    TextView tv_commit_id_certification;

    @ViewById(R.id.ed_inputName)
    EditText ed_inputName; //身份证姓名


    @ViewById(R.id.et_idNum)
    EditText et_idNum; //身份证号码


    @ViewById(R.id.tv_showSuccess)
    TextView tv_showSuccess; //成功条目


    //显示姓名     tv_show_certificationName
    @ViewById(R.id.tv_show_certificationName)
     TextView tv_show_certificationName; //姓名tv

    @ViewById(R.id.tv_show_idNum)
    TextView tv_show_idNum;  //省份证 tv

    @ViewById(R.id.tv_confirm_certification)
    TextView tv_confirm_certification;


    @Click({R.id.back,R.id.tv_commit_id_certification,R.id.tv_confirm_certification,R.id.tv_confirm_certification})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_commit_id_certification:
                 commitCertification(); // 提交
                break;

            case R.id.tv_confirm_certification:
                        finish();
                break;     // 确定

        }
    }

    private void commitCertification() {

          String  name =ed_inputName.getText().toString().trim();
          if(isEmpty(name)){
              ToastUtils.showTextInMiddle("姓名不能为空");
              return;
          }


          String idNum=et_idNum.getText().toString().trim();
          if(isEmpty(idNum)){
              ToastUtils.showTextInMiddle("身份证号码不能为空");
              return;
          }

          if(!CheckUtils.isIDCard(idNum)){
              ToastUtils.showTextInMiddle("身份证号码非法");
              return;
          }

        mShowDialog();
        CertificationBean  certificationBean = new  CertificationBean();
           certificationBean.setUsertoken(UserGolbal.getInstance().getUserToken());
           certificationBean.setRealname(name);
          certificationBean.setIdcard(idNum);

         Gson gson =new Gson();
        String jsonStr = gson.toJson(certificationBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
        UserRepo.getInstance().checkIDNum(requestBody)
                .subscribe(new Subscriber<RealNameAuthBean>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {dismissLoading();}

                    @Override
                    public void onNext(RealNameAuthBean realNameAuthBean) {
                        dismissLoading();
                        //LogUtils.d("TAG",new Gson().toJson(realNameAuthBean));
                         if(realNameAuthBean.getCode()==200){//成功
                               RealNameAuthBean.DataBean  realNameAuthBeanData = realNameAuthBean.getData();
                             String verifystatus=realNameAuthBeanData.getVerifystatus();
                             String verifymsg=realNameAuthBeanData.getVerifymsg();
                                  if(verifystatus.equals("0")){
                                      ToastUtils.showTextInMiddle(verifymsg); //实名认证成功通过
                                      tv_showSuccess.setVisibility(View.VISIBLE);
                                      tv_show_certificationName.setVisibility(View.VISIBLE);
                                      tv_show_certificationName.setText(realNameAuthBeanData.getRealname());
                                      tv_show_idNum.setVisibility(View.VISIBLE);
                                      tv_show_idNum.setText(realNameAuthBeanData.getIdcard());
                                      tv_confirm_certification.setVisibility(View.VISIBLE);
                                      ed_inputName.setVisibility(View.GONE);
                                      et_idNum.setVisibility(View.GONE);
                                      tv_commit_id_certification.setVisibility(View.GONE);
                                  }else {
                                      ToastUtils.showTextInMiddle(verifymsg+",请重试!"); //实名认证失败未通过
                                     //  tv_showSuccess.setVisibility(View.GONE);
                                      return;
                                  }


                         }else{//失败
                            ToastUtils.showTextInMiddle(realNameAuthBean.getMsg());
                          //   tv_showSuccess.setVisibility(View.GONE);
                            return;
                         }



                    }
                });


    }


    @AfterViews
    void init(){
        mTitle.setText("实名认证");
        back.setVisibility(View.VISIBLE);
        tv_show_certificationName.setVisibility(View.GONE);

    }

}
