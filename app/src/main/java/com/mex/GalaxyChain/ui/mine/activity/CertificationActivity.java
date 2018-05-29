package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.RealNameAuthBean;
import com.mex.GalaxyChain.bean.RealNameC1Bean;
import com.mex.GalaxyChain.bean.requestbean.CertificationBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.CheckUtils;
import com.mex.GalaxyChain.utils.CreatSignUtils;
import com.mex.GalaxyChain.utils.ToastUtils;
import com.mex.GalaxyChain.view.pickerview.TimePickerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

/*
*
*  实名认证(王皓)
*
* */
@EActivity(R.layout.activity_certification)
public class CertificationActivity  extends BaseActivity {

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

    @ViewById(R.id.ll_otherLL)
    LinearLayout ll_otherLL;



    @ViewById(R.id.tv_birthday)
    TextView tv_birthday;
    @ViewById(R.id.tv_IDcard_statTime)
    TextView tv_IDcard_statTime;
    @ViewById(R.id.tv_IDcard_endTime)
    TextView tv_IDcard_endTime;



    @AfterViews
    void init(){
        mTitle.setText("实名认证");
        back.setVisibility(View.VISIBLE);


    }


    @Click({R.id.back,R.id.tv_commit_id_certification,R.id.tv_confirm_certification, R.id.tv_confirm_certification,
            R.id.tv_birthday,R.id.tv_IDcard_statTime,R.id.tv_IDcard_endTime})
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

            case R.id.tv_birthday:

                showTimePickerView("出生日期",tv_birthday);
                break;

            case R.id.tv_IDcard_statTime:
                showTimePickerView("证件颁发日期",tv_IDcard_statTime);
                break;
            case R.id.tv_IDcard_endTime:
                showTimePickerView("证件到期日期",tv_IDcard_endTime);
                break;


        }
    }



    private void commitCertification() {

          String  name =ed_inputName.getText().toString().trim();
          if(isEmpty(name)){
              ToastUtils.showTextInMiddle("姓名不能为空");
              return;
          }


          final String idNum=et_idNum.getText().toString().trim();
          if(isEmpty(idNum)){
              ToastUtils.showTextInMiddle("身份证号码不能为空");
              return;
          }

          if(!CheckUtils.isIDCard(idNum)){
              ToastUtils.showTextInMiddle("身份证号码非法");
              return;
          }

        final  String birthday = tv_birthday.getText().toString().trim();
        if (isEmpty(birthday)) {
            ToastUtils.showTextInMiddle("出生日期不能为空");
            return;
        }

        final String idcard_statTime = tv_IDcard_statTime.getText().toString().trim();
        if (isEmpty(idcard_statTime)) {
            ToastUtils.showTextInMiddle("证件颁发日期不能为空");
            return;
        }


        final String idcard_endTime = tv_IDcard_endTime.getText().toString().trim();
        if (isEmpty(idcard_endTime)) {
            ToastUtils.showTextInMiddle("证件到期日期不能为空");
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
                    public void onError(Throwable e) {
                        dismissLoading();
                        return;}

                    @Override
                    public void onNext(RealNameAuthBean realNameAuthBean) {
                         dismissLoading();
                        //LogUtils.d("TAG",new Gson().toJson(realNameAuthBean));
                         if(realNameAuthBean.getCode()==200){//王皓的网络请求成功
                               RealNameAuthBean.DataBean  realNameAuthBeanData = realNameAuthBean.getData();
                             String verifystatus=realNameAuthBeanData.getVerifystatus();
                             String verifymsg=realNameAuthBeanData.getVerifymsg();
                                  if(verifystatus.equals("0")){ //0 王皓的实名认证成功通过,
                                      realNameAuthenticationByC1(idNum, birthday,idcard_statTime,idcard_endTime,realNameAuthBeanData); //做高杰的c1实名认证请求
                                  }else {
                                      return;
                                  }
                                  }else{ //失败
                             ToastUtils.showTextInMiddle(realNameAuthBean.getMsg());
                           //   tv_showSuccess.setVisibility(View.GONE);
                            return;
                         }



                    }
                });


    }


    private TimePickerView pvTime;
    private void showTimePickerView(String title, final TextView view) {
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setRange(1900,2500);
        pvTime.setTime(new Date());
        pvTime.setCyclic(true);
        pvTime.setCancelable(true);
        pvTime.setTitle(title);
        //选择具体的时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                view.setText(format.format(date));
            }
        });
        pvTime.show();  //点击rl_occurrencetime  弹出时间选择器

    }








    private void realNameAuthenticationByC1(String idNum, String birthday, String idcard_statTime, String idcard_endTime,
                                            final RealNameAuthBean.DataBean  realNameAuthBeanData) {
        HashMap<String, Object> params = new HashMap<>();
        String country_code= Constants.COUNTRY_CODE;
        params.put("country_code",country_code);
        int id_type=Constants.IDCARD;
        params.put("type", id_type);
        params.put("number",idNum);
        String xing="方";
        params.put("first_name",xing);//？？？
        String ming ="明飞";
        params.put("second_name",ming);//???
        params.put("birthday",birthday);
        params.put("begin",idcard_statTime);
        params.put("end",idcard_endTime);
        String token=UserGolbal.getInstance().getUserToken();
        params.put("token",token);
        Date dt = new Date();
        String timeStamp = dt.getTime() + "";
        params.put("time", timeStamp);
        String sign = CreatSignUtils.creatSign(params);
        UserRepo.getInstance().postRealNameC1(country_code,id_type,idNum,xing,ming,birthday,
                idcard_statTime,idcard_endTime,token,timeStamp,sign)
                .subscribe(new Subscriber<RealNameC1Bean>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {return;}

                    @Override
                    public void onNext(RealNameC1Bean realNameC1Bean) {
                        if(realNameC1Bean.getCode().equals("0")){//高杰C1认证提交成功
                            ToastUtils.showTextInMiddle("认证提交成功");
                            tv_showSuccess.setVisibility(View.VISIBLE);
                            tv_show_certificationName.setVisibility(View.VISIBLE);
                            tv_show_certificationName.setText(realNameAuthBeanData.getRealname());
                            tv_show_idNum.setVisibility(View.VISIBLE);
                            tv_show_idNum.setText(realNameAuthBeanData.getIdcard());
                            tv_confirm_certification.setVisibility(View.VISIBLE);
                            ed_inputName.setVisibility(View.GONE);
                            et_idNum.setVisibility(View.GONE);
                            tv_commit_id_certification.setVisibility(View.GONE);
                            ll_otherLL.setVisibility(View.GONE);
                        }else{
                            ToastUtils.showTextInMiddle(realNameC1Bean.getMsg());
                            return;
                        }
                    }
                });
    }





}
