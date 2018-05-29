package com.mex.GalaxyChain.ui.mine.activity;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.RealNameC1Bean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.ConfigManager;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.CheckUtils;
import com.mex.GalaxyChain.utils.CreatSignUtils;
import com.mex.GalaxyChain.utils.ToastUtils;
import com.mex.GalaxyChain.view.pickerview.OptionsPickerView;
import com.mex.GalaxyChain.view.pickerview.TimePickerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

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

    @ViewById(R.id.ed_inputXing)
    TextView ed_inputXing;
    @ViewById(R.id.ed_inputMing)
    TextView ed_inputMing;
    @ViewById(R.id.et_idNum)
    TextView et_idNum;


    @ViewById(R.id.tv_commit_id_certification)
    TextView tv_commit_id_certification;
    @ViewById(R.id.tv_birthday)
    TextView tv_birthday;
    @ViewById(R.id.tv_IDcard_statTime)
    TextView tv_IDcard_statTime;
    @ViewById(R.id.tv_IDcard_endTime)
    TextView tv_IDcard_endTime;



   // int tag = 0;
    int year = -1;
    int month = -1;
    int day = -1;
    private OptionsPickerView pvOptions;
    private TimePickerView pvTime;
    private Button bt_upload;

    @Click({R.id.back, R.id.tv_birthday, R.id.tv_commit_id_certification,R.id.tv_IDcard_statTime,R.id.tv_IDcard_endTime})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_birthday:
               // tag = 1;
                //showDateDialog(tag);
                showTimePickerView("出生日期",tv_birthday);
                break;

            case R.id.tv_IDcard_statTime:
                showTimePickerView("证件颁发日期",tv_IDcard_statTime);
                break;
            case R.id.tv_IDcard_endTime:
                showTimePickerView("证件到期日期",tv_IDcard_endTime);
                break;



            case R.id.tv_commit_id_certification:
                commitCertification(); // 提交认证
                break;

        }
    }




    @AfterViews
    void init() {
        mTitle.setText("实名认证");
        back.setVisibility(View.VISIBLE);
    }


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



    private void showDateDialog(final int tag) {
        Calendar date = Calendar.getInstance();
        if (day == -1) {
            day = date.get(Calendar.DAY_OF_MONTH) + 1;
            month = date.get(Calendar.MONTH) + 1;
            year = date.get(Calendar.YEAR)+1;
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthOfYear, int dayOfMonth) {
                        day = dayOfMonth;
                        month = monthOfYear + 1;
                        year = year1;
                        if (tag == 1) {
                            tv_birthday.setText(String.format("%d-%02d-%02d", year, month, day));
                        }
                    }
                }, year-1, month - 1, day);
        datePickerDialog.show();

    }


    private void commitCertification() {

        String xing = ed_inputXing.getText().toString().trim();

        if (isEmpty(xing)) {
            ToastUtils.showTextInMiddle("姓不能为空");
            return;
        }

        String ming = ed_inputMing.getText().toString().trim();

        if (isEmpty(ming)) {
            ToastUtils.showTextInMiddle("名不能为空");
            return;
        }


        String idNum = et_idNum.getText().toString().trim();
        if (isEmpty(idNum)) {
            ToastUtils.showTextInMiddle("身份证号码不能为空");
            return;
        }


        if (!CheckUtils.isIDCard(idNum)) {
            ToastUtils.showTextInMiddle("身份证号码非法");
            return;
        }



        String birthday = tv_birthday.getText().toString().trim();
        if (isEmpty(birthday)) {
            ToastUtils.showTextInMiddle("出生日期不能为空");
            return;
        }


        String idcard_statTime = tv_IDcard_statTime.getText().toString().trim();
        if (isEmpty(idcard_statTime)) {
            ToastUtils.showTextInMiddle("证件颁发日期不能为空");
            return;
        }


        String idcard_endTime = tv_IDcard_endTime.getText().toString().trim();
        if (isEmpty(idcard_endTime)) {
            ToastUtils.showTextInMiddle("证件到期日期不能为空");
            return;
        }


        HashMap<String, Object> params = new HashMap<>();
        String country_code=Constants.COUNTRY_CODE;
        params.put("country_code",country_code);
        int id_type=Constants.IDCARD;
        params.put("type", id_type);
        params.put("number",idNum);
        params.put("first_name",xing);
        params.put("second_name",ming);
        params.put("birthday",birthday);
        params.put("begin",idcard_statTime);
        params.put("end",idcard_endTime);
      //  String token=UserGolbal.getInstance().getUserToken();
        String token= ConfigManager.getUserToken();
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
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(RealNameC1Bean realNameC1Bean) {
                            if(realNameC1Bean.getCode().equals("0")){
                                ToastUtils.showTextInMiddle("C1认证提交成功");
                                }
                    }
                });
    }


}








