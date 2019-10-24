package com.jone.SDCard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.framewidget.view.Headlayout;
import com.mdx.framework.utility.Helper;

import java.io.File;

public class MainActivity extends Activity implements OnClickListener {
    public TextView titleFilePath;
    private ListView fileList;
    private Headlayout mHeadlayout;
    private ImageView backBtn;
    File currentFile;
    BaseAdapter fileAdapter;
    Context context;
    String mSdcardPath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        initView();
        initFile();
    }

    private void initView() {
        mHeadlayout = (Headlayout) findViewById(R.id.mHeadlayout);
        titleFilePath = (TextView) findViewById(R.id.tvpath);
        fileList = (ListView) findViewById(R.id.file_list);
        backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);
        mHeadlayout.setLeftBackground(R.drawable.arrows_left);
        mHeadlayout.setGoBack(this);
        mHeadlayout.setTitle("文件选择");

    }

    private void initFile() {
        if (isSdCardExist()) {
            mSdcardPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
            File root = null;
            if (!TextUtils.isEmpty(mSdcardPath)
                    && new File(mSdcardPath).canRead()) {
                root = new File(mSdcardPath);
            }
            currentFile = root;
            fileAdapter = new SdcardFileAdapter(context, new File(mSdcardPath));
            fileList.setAdapter(fileAdapter);
        } else {
            Helper.toast("sdk不存在", context);
            finish();
        }

    }

    /**
     * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
     *
     * @return
     */
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    @Override
    public void onClick(View v) {
        backParent();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                backParent();
                break;

            default:
                break;
        }
        return false;
    }

    private void backParent() {
        File current = ((SdcardFileAdapter) fileAdapter).getCurrentParent();
        try {
            if (!current.getCanonicalPath().equals(mSdcardPath)) {
                currentFile = current.getParentFile();
                // fileAdapter = new SdcardFileAdapter(context, currentFile);
                // fileList.setAdapter(fileAdapter);
                ((SdcardFileAdapter) fileAdapter).setData(currentFile);
                ((SdcardFileAdapter) fileAdapter).notifyDataSetChanged();
            } else {
                MainActivity.this.finish();
            }
        } catch (Exception e) {
        }
    }
}