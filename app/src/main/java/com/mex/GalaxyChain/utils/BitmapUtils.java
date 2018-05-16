package com.mex.GalaxyChain.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 *
 * 对图片处理的一个工具类 BitmapUtils
 */

public class BitmapUtils {

    // 把原生图片 加工成圆形图片
    public static Bitmap circleBitmap(Bitmap bitmap) {
        //获取Bitmap的宽度
        int width =bitmap.getWidth();
        //以Bitmap的宽度值作为新的bitmap的宽高值。
        /*Config.ARGB_8888  更清晰不失真
        *Config.ARGB_4444  节省一半内存 但是图片失帧严重
        *RGB_565   节省一半内存  图片失帧小   无透明度A
         *
        * */
        Bitmap newBitmap = Bitmap.createBitmap(width,width,Bitmap.Config.ARGB_4444);

        //以此newBitmap为基准，创建一个画布
        Canvas canvas =new Canvas(newBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //在画布上画一个圆
        canvas.drawCircle(width/2,width/2,width/2, paint);

        //设置图片相交情况下的处理方式
        //setXfermode：设置当绘制的图像出现相交情况时候的处理方式的,它包含的常用模式有：
        //PorterDuff.Mode.SRC_IN 取两层图像交集部分,只显示上层图像
        //PorterDuff.Mode.DST_IN 取两层图像交集部分,只显示下层图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //在画布上绘制bitmap
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return  newBitmap;
    }




    //实现图片的压缩处理
    //设置宽高必须使用浮点型，否则导致压缩的比例：0
 // 压缩以后的宽width   和 高height
    public static Bitmap  zoom(Bitmap bitmap,float width ,float height){
   Matrix matrix = new Matrix();
        //图片的压缩处理比例postScale
      matrix.postScale(width/bitmap.getWidth(),height/bitmap.getHeight());
        Bitmap bitmapp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,false);
        return bitmapp;
    }
}
