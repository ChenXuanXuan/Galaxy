package com.mex.GalaxyChain.ui.mine;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.QuitEvent;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.ui.mine.activity.AssetCenterAct_;
import com.mex.GalaxyChain.ui.mine.activity.InpourActivity_;
import com.mex.GalaxyChain.ui.mine.activity.QuestionActivity_;
import com.mex.GalaxyChain.ui.mine.activity.SettingActivity_;
import com.mex.GalaxyChain.utils.BitmapUtils;
import com.mex.GalaxyChain.utils.IsEmptyUtils;
import com.mex.GalaxyChain.utils.RequestPermissionUtils;
import com.mex.GalaxyChain.view.PhotoPopupWindow;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * name：
 * describe:个人中心
 * author: LSJ
 * time 22/3/18 下午2:28
 */
@EFragment(R.layout.fragment_mine)
public class MineFragment extends BaseFragment {
    @ViewById
    TextView mTitle;

    @ViewById
    ImageView iv_user_icon;


    private PhotoPopupWindow popupWindow;

    @AfterViews
    void init() {
        initView();
    }

    private void initView() {
        // 直接加载显示本地存储的头像(经过处理的头像)
        Bitmap localImagBitmap=getImageBitmapFromLocal();
        if(localImagBitmap!=null){
            iv_user_icon.setImageBitmap(localImagBitmap);
        }

        popupWindow = new PhotoPopupWindow(getActivity());

        initListenerForPopu();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(QuitEvent event) {
        // 直接加载显示本地存储的头像(经过处理的头像)
        Bitmap localImagBitmap=getImageBitmapFromLocal();
        if(localImagBitmap!=null){
            iv_user_icon.setImageBitmap(localImagBitmap);
        }

    }







    @Click({R.id.inpour, R.id.withDraw, R.id.assetCenter, R.id.normalQa,
            R.id.about_us, R.id.setting, R.id.iv_user_icon})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.inpour:
                InpourActivity_.intent(this).start();
                break;

            case R.id.withDraw:

                break;

            case R.id.assetCenter://资产中心
                AssetCenterAct_.intent(getActivity()).start();
                break;

            case R.id.normalQa://常见问题
                QuestionActivity_.intent(getActivity()).start();
                break;

            case R.id.about_us://关于我们
                break;

            case R.id.setting://设置
                SettingActivity_.intent(getActivity()).start();
                break;

            case R.id.iv_user_icon:  // 更换用户头像
                    changeUserPhoto();
                //  RegisActivity_.intent(getActivity()).start();
                break;
        }
    }

    private void changeUserPhoto() {
        RequestPermissionUtils.requestPermission(new Runnable() {
            @Override
            public void run() {
                onTakePhoto();
            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
    }



    private void onTakePhoto() {

        if (popupWindow != null) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }

            popupWindow.showAsDropDown((ScrollView)inflate(R.layout.fragment_mine));

        }
    }


    private void initListenerForPopu() {
        popupWindow.setOnItemClickListener(new PhotoPopupWindow.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {  // 拍照
                    case 0:
                        startOpenCamera();
                        break;
                    case 1: // 图库
                        startOpenPictureStorage();
                        break;
                    case 2: // 取消

                        break;
                }
            }
        });
    }


    private static final int REQUEST_PICTURE = 100;
    private static final int REQUEST_CAMERA = 200;
    private void startOpenCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    private void startOpenPictureStorage() {

        //启动其他应用的activity:使用隐式意图
        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picture, REQUEST_PICTURE);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap dealedBitmap;
         if(resultCode==RESULT_OK&&!IsEmptyUtils.isEmpty(data)){
               switch(requestCode){
                   case REQUEST_CAMERA:
                       //获取intent中的图片对象
                       Bundle extras = data.getExtras();
                       // 相机拍照的原生图片(没裁剪没压缩 像素很大)
                       Bitmap originalBitmap = (Bitmap) extras.get("data");
                       //对获取到的bitmap进行压缩、圆形处理
                       dealedBitmap = BitmapUtils.zoom(originalBitmap, iv_user_icon.getWidth(), iv_user_icon.getHeight());
                       dealedBitmap = BitmapUtils.circleBitmap(dealedBitmap);


                       // 加载显示  相机拍照的头像(压缩处圆后)
                       iv_user_icon.setImageBitmap(dealedBitmap);

                       //todo 将压缩过的图片 上传到服务器 存储
                       //将压缩过的图片 保存到本地
                       saveImageInLocal(dealedBitmap);
                       break;

                   case  REQUEST_PICTURE:
                       Uri selectedImage = data.getData();
                       //android各个不同的系统版本,对于获取外部存储上的资源，返回的Uri对象都可能各不一样,
                       // 所以要保证无论是哪个系统版本都能正确获取到图片资源的话就需要针对各种情况进行一个处理了
                       //这里返回的uri情况就有点多了
                       //在4.4.2之前返回的uri是:content://media/external/images/media/3951或者file://....
                       // 在4.4.2返回的是content://com.android.providers.media.documents/document/image
                       String selectedImagePath = getPath(selectedImage);
                       Bitmap defaultBitmap= BitmapFactory.decodeFile(selectedImagePath);
                       Bitmap zoomedBitmap = BitmapUtils.zoom(defaultBitmap,iv_user_icon.getWidth(), iv_user_icon.getHeight());
                       Bitmap cicledBitmap = BitmapUtils.circleBitmap(zoomedBitmap);
                       // 加载显示 从相册选择的头像
                       iv_user_icon.setImageBitmap(cicledBitmap);
                       //todo  将图片 上传到服务器存储
                       //保存到本地
                       saveImageInLocal(cicledBitmap);
                       break;
               }
         }

    }

    /**
     * 根据系统相册选择的文件获取路径
     *
     * @param uri
     */
    @SuppressLint("NewApi")
    private String getPath(Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        //高于4.4.2的版本
        if (sdkVersion >= 19) {
            Log.e("TAG", "uri auth: " + uri.getAuthority());
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(getActivity(), contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(getActivity(), contentUri, selection, selectionArgs);
            } else if (isMedia(uri)) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = getActivity().managedQuery(uri, proj, null, null, null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                return actualimagecursor.getString(actual_image_column_index);
            }


        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(getActivity(), uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }



    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    public static boolean isMedia(Uri uri) {
        return "media".equals(uri.getAuthority());
    }


    /**
     * uri路径查询字段
     *
     * @param context
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * 数据的存储。（5种）
     * Bimap:内存层面的图片对象。
     *
     * Bimap存储到内存：
     *      BitmapFactory.decodeFile(String filePath); // 文件路径
     *      BitmapFactory.decodeStream(InputStream is); // 文件的输入流
     * Bimap存储到本地持久化：
     *  // CompressFormat存储的图片格式     quality存储的压缩比例
     *    OutputStream os  把bitmap 往外写到存储本地的流对象OutputStream(需要文件 文件需要路径)里
     compress(Bitmap.CompressFormat format, int quality, OutputStream stream)
     bitmap.compress(Bitmap.CompressFormat.PNG,100,OutputStream os);
     */
    private void saveImageInLocal(Bitmap bitmap) {
        File filesDir;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //bitmap存储到本地的手机sd卡路径1：storage/sdcard/Android/data/包名/files
            filesDir = getActivity().getExternalFilesDir("");

        }else{
            //bitmap存储到本地的手机内部的路径2：data/data/包名/files
            filesDir = getActivity().getFilesDir();
        }

        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(filesDir,"icon.png");
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private Bitmap getImageBitmapFromLocal() {
        File fileDirPath;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)){
            fileDirPath = getActivity().getExternalFilesDir("");
        }else{
            fileDirPath=getActivity().getFilesDir();
        }

        File  file = new File(fileDirPath,"icon.png");

        if(file.exists()){
            Bitmap localImagBitmap=    BitmapFactory.decodeFile(file.getAbsolutePath());
            return  localImagBitmap;
        }

        return  null;
    }


}
