package com.framewidget.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.http.AbFileHttpResponseListener;
import com.ab.http.HttpUtil;
import com.framewidget.R;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.mdx.framework.widget.decodecode.camera.PlanarYUVLuminanceSource;

import java.util.Hashtable;

//https://www.imooc.com/article/22919?block_id=tuijian_wz
public class PopShowShare implements OnClickListener {

    public Context context;
    private View view;
    private PopupWindow popwindow;
    private View popview;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public String url;

    public PopShowShare(Context context, View view, String url) {
        super();
        this.view = view;
        this.url = url;
        this.context = context;
        LayoutInflater flater = LayoutInflater.from(context);
        popview = flater.inflate(R.layout.item_bottom, null);
        popwindow = new PopupWindow(popview, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        mTextView_1 = (TextView) popview
                .findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) popview
                .findViewById(R.id.mTextView_2);

        mTextView_1.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        popwindow.setBackgroundDrawable(new BitmapDrawable(context
                .getResources()));
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);
        popwindow.setFocusable(true);

    }


    @SuppressLint("NewApi")
    public void show() {
        popwindow.showAsDropDown(view, 0, -25);
    }

    public void hide() {
        popwindow.dismiss();
    }

    public boolean isShow() {
        return popwindow.isShowing();
    }

    @Override
    public void onClick(View v) {
        hide();
        if (R.id.mTextView_1 == v.getId()) {
            shareText("分享", url);
        } else if (R.id.mTextView_2 == v.getId()) {
            if (url.startsWith("http")) {
                HttpUtil.downLoadFile(context, url, new AbFileHttpResponseListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFinish() {
                        //获取解析结果
                        String ret = scanImage(getFile().getAbsolutePath());
                        Toast.makeText(context, "解析结果：" + ret.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(int statusCode, String content, Throwable error) {

                    }
                });
            } else {
                String ret = scanImage(url.startsWith("file:") ? url.split("file://")[1] : url);
                Toast.makeText(context, "解析结果：" + ret.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 分享文字内容
     *
     * @param dlgTitle 分享对话框标题
     * @param content  分享内容（文字）
     */
    private void shareText(String dlgTitle, String content) {
        if (content == null || "".equals(content)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        // 设置弹出框标题
        if (dlgTitle != null && !"".equals(dlgTitle)) { // 自定义标题
            context.startActivity(Intent.createChooser(intent, dlgTitle));
        } else { // 系统默认标题
            context.startActivity(intent);
        }
    }

    static String scanImage(String bitmapPath) {
        String qrCode = "";
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(bitmapPath, options);
            options.inSampleSize = options.outHeight / 400;
            if (options.inSampleSize <= 0) {
                options.inSampleSize = 1; //防止其值小于或等于0
            }
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(bitmapPath, options);
            if (bitmap != null) {
                int bitmapWidth = bitmap.getWidth();
                int bitmapHeight = bitmap.getHeight();
                // 1.将bitmap的RGB数据转化成YUV420sp数据
                byte[] bmpYUVBytes = Bmp2YUV.getBitmapYUVBytes(bitmap);
                // 2.塞给zxing进行decode
                qrCode = decodeYUVByZxing(bmpYUVBytes, bitmapWidth, bitmapHeight);
                if (TextUtils.isEmpty(qrCode)) {
                    // 3.识别不成功，使用zbar进行decode
                    qrCode = decodeYUVByZbar(bmpYUVBytes, bitmapWidth, bitmapHeight);
                }
                bitmap.recycle();
                bitmap = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrCode;
    }

    private static String decodeYUVByZbar(byte[] bmpYUVBytes, int bmpWidth, int bmpHeight) {
        String zbarResult = "";
        if (null != bmpYUVBytes && bmpWidth > 0 && bmpHeight > 0) {
//            ZBarDecoder decoder = new ZBarDecoder();
//            zbarResult = decoder.decodeRaw(bmpYUVBytes, bmpWidth, bmpHeight);
        }
        Log.e("HtscCodeScanningUtil", "decode by zbar, result = " + zbarResult);
        return zbarResult;
    }

    private static String decodeYUVByZxing(byte[] bmpYUVBytes, int bmpWidth, int bmpHeight) {
        String zxingResult = "";
        // Both dimensions must be greater than 0
        if (null != bmpYUVBytes && bmpWidth > 0 && bmpHeight > 0) {
            try {
                Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  // 矫错级别
                PlanarYUVLuminanceSource source = new PlanarYUVLuminanceSource(bmpYUVBytes, bmpWidth,
                        bmpHeight, 0, 0, bmpWidth, bmpHeight);
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
                Reader reader = new QRCodeReader();
                Result result = reader.decode(binaryBitmap,hintMap);
                if (null != result) {
                    zxingResult = result.getText();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return zxingResult;
    }

//    //解析二维码图片,返回结果封装在Result对象中
//    private com.google.zxing.Result parseQRcodeBitmap(String bitmapPath) {
//        //解析转换类型UTF-8
//        Map<DecodeHintType, String> hints = new LinkedHashMap<DecodeHintType, String>();
//        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
//        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
//        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
//        //获取到待解析的图片
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        //如果我们把inJustDecodeBounds设为true，那么BitmapFactory.decodeFile(String path, Options opt)
//        //并不会真的返回一个Bitmap给你，它仅仅会把它的宽，高取回来给你
//        options.inJustDecodeBounds = true;
//        //此时的bitmap是null，这段代码之后，options.outWidth 和 options.outHeight就是我们想要的宽和高了
//        Bitmap bitmap = BitmapFactory.decodeFile(bitmapPath, options);
//        //我们现在想取出来的图片的边长（二维码图片是正方形的）设置为400像素
//        /**
//         options.outHeight = 400;
//         options.outWidth = 400;
//         options.inJustDecodeBounds = false;
//         bitmap = BitmapFactory.decodeFile(bitmapPath, options);
//         */
//        //以上这种做法，虽然把bitmap限定到了我们要的大小，但是并没有节约内存，如果要节约内存，我们还需要使用inSimpleSize这个属性
//        options.inSampleSize = options.outHeight / 400;
//        if (options.inSampleSize <= 0) {
//            options.inSampleSize = 1; //防止其值小于或等于0
//        }
//        /**
//         * 辅助节约内存设置
//         *
//         * options.inPreferredConfig = Bitmap.Config.ARGB_4444;  // 默认是Bitmap.Config.ARGB_8888
//         * options.inPurgeable = true;
//         * options.inInputShareable = true;
//         */
//        options.inJustDecodeBounds = false;
//        bitmap = BitmapFactory.decodeFile(bitmapPath, options);
//        //新建一个RGBLuminanceSource对象，将bitmap图片传给此对象
//        RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(bitmap);
//        //将图片转换成二进制图片
//        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(rgbLuminanceSource));
//        //初始化解析对象
//        QRCodeReader reader = new QRCodeReader();
//        //开始解析
//        Result result = null;
//        try {
//            result = reader.decode(binaryBitmap, hints);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
}
