package com.mex.GalaxyChain.ui.mine.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.ConfigManager;
import com.mex.GalaxyChain.utils.ToastUtils;
import com.mex.GalaxyChain.view.X5WebView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_coinscoinsrecharge_h5)
public class JSAndAndroidTestDemoActivity extends BaseActivity {





    @ViewById(R.id.back)
    ImageView back;
    @ViewById(R.id.mTitle)
    TextView mTitle;
    @ViewById
    X5WebView mWebView;
    private String mUrl;


    @Click(R.id.back)
    void  onClick(View view){
        finish();
    }


    @AfterViews
    void init(){
        mTitle.setText("币币充值");
        back.setVisibility(View.VISIBLE);
        mUrl = getIntent().getStringExtra("url");
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mWebView.loadUrl(mUrl);
//===========================================
        /*JS端  调用java安卓端:  JS端 传递数据  给   安卓端
         * 例如:在H5 输入一个数据    传递给安卓原生端  安卓原生端 能够获取此数据 进行显示   */

//JS端 调用安卓端:案例一:
        mWebView.addJavascriptInterface(new AndroidAndJSInterface(){
            @JavascriptInterface
            public void showToast() {
                ToastUtils.showTextInMiddle("安卓代码  被 JS调用");
            }

            @JavascriptInterface
            public void showToast2(String s, int type) {
                ToastUtils.showTextInMiddle("安卓代码 被 JS调用"+s+type);
            }
        },"Android");





     /*此时H5的代码如何写?
      <input type="button" value="点击Android被调用"
       onclick="window.Android.showToast()" />

       <input type="button2" value="点击Android被调用"
       onclick="window.Android.showToast(str,type)"
        */
//------------------------------------
        //JS端 调用安卓端:案例二:
        mWebView.addJavascriptInterface(new JSCallAndroidPalyVide(){
            @JavascriptInterface
            public void playVideo(int id, String videoUrl, String title) {
                ToastUtils.showTextInMiddle("itemid="+id+"  videourl="+videoUrl+"  itemtitle="+title);
                //获取JS传递过来的几个参数(url title),使用安卓本地的播放器进行播放视频
                Intent intent = new Intent();
                intent.setDataAndType(Uri.parse(videoUrl),"video/*"); //"video/*" 匹配所有的视频格式
                startActivity(intent);
            }
        },"android");




/*
*  H5的代码如何写?
*
*
    var videourl = "http://10.0.2.2:8080/yellow.mp4";
    var itemid = "65411";
    var itemdesc = "1级单杀小龙，5级单杀峡谷先锋！";
    var itempic = "http://avatar.anzogame.com/pic_v1/lol/news/20160507/spic65411h572d6eaf.jpg";
    var itemtitle = "6.9玛尔扎哈OP套路教程";
    if(obj_play != null) {
        obj_play.ontouchstart = function() {
            this.className = 'inter_click';
            javascript:android.playVideo(itemid, videourl, itemtitle);
            ajaxrequest('/stat/item', "POST", true, {type:'play', id:itemid});
        }
        obj_play.ontouchend = function() {
            this.className = 'inter';
        }
    }

 */

        //==================================================================
        /* java安卓  调用JS:  安卓端 给 js 端 传递数据(参数),如果不需要就把参数去掉  */
        String uid = "y89raejosdq09reajcmjq90wefu8";
        String name="fmf";
        String password="123456";
     //   String token = UserGolbal.getInstance().getUserToken();
        String token = ConfigManager.getUserToken();
        mWebView.loadUrl(  "javascript:javaCallJs("+
                "'"+uid+"',"+
                "'"+name+"',"+
                "'"+password+"',"+
                "'"+token+"',"+
                ")"
        );


        /*  此时的H5 应该如何写代码:

            funciton javaCallJs(arg){
              document.getElementById("content").innerHTML=("欢迎: "+arg);
             }
        */



    }





    public  interface AndroidAndJSInterface {
        void showToast();
        void showToast2(String str,int type);
    }




    public interface   JSCallAndroidPalyVide{

        void playVideo(int id,String videoUrl,String title);
    }

}
