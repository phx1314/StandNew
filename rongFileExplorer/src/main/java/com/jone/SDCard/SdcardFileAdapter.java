package com.jone.SDCard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.server.api.Son;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SdcardFileAdapter extends BaseAdapter {

    Context context;
    File current;
    File[] files;
    String name;
    String fileSize;
    ProgressDialog mProgressDialog;

    public SdcardFileAdapter(Context context, File current) {
        this.context = context;
        this.current = current;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("文件上传中,请稍候...");
        setData(current);
    }

    public File getCurrentParent() {
        return current;
    }

    public void setData(File current) {

        files = current.listFiles();
        this.current = current;
    }

    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public Object getItem(int position) {
        return files[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView fileName;
        ImageView fileIcon;
        LinearLayout clickView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item, null);
        } else {
            view = convertView;
        }

        fileIcon = (ImageView) view.findViewById(R.id.icon);
        fileName = (TextView) view.findViewById(R.id.file_name);
        clickView = (LinearLayout) view.findViewById(R.id.itemView);

        File file = (File) getItem(position);
        if (file.isDirectory()) {
            fileIcon.setBackgroundDrawable(context.getResources().getDrawable(
                    R.drawable.folder));
        } else {
            fileIcon.setBackgroundDrawable(context.getResources().getDrawable(
                    R.drawable.file));
        }
        fileName.setText(file.getName());
        try {
            ((MainActivity) context).titleFilePath.setText("当前路径为:"
                    + current.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        clickView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // if (files[position].canRead()) {
                // if (files[position].isFile()) {
                //
                // } else
                if (files[position].isDirectory()) {
                    current = files[position];
                    files = files[position].listFiles();
                    if (files == null) {
                        File[] files2 = {};
                        files = files2;
                    }
                    notifyDataSetChanged();
                } else {
                    name = files[position].getName();
                    fileSize = FileSizeUtil
                            .getAutoFileOrFilesSize(files[position]);
                    Intent mIntent = new Intent();
                    mIntent.putExtra(
                            "data",
                            new DataFile(
                                    fileSize,
                                    name,
                                    "", files[position]));
                    ((MainActivity) context)
                            .setResult(1, mIntent);
                    ((MainActivity) context)
                            .finish();
//                    new Thread(new Runnable() {
//                        public void run() {
//                            name = files[position].getName();
//                            fileSize = FileSizeUtil
//                                    .getAutoFileOrFilesSize(files[position]);
//                            ((MainActivity) context)
//                                    .runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            if (!mProgressDialog.isShowing())
//                                                mProgressDialog.show();
//                                        }
//                                    });
//                            InputStream in;
//                            try {
//                                in = new FileInputStream(files[position]);
//                                final JSONObject mJSONObject = new JSONObject(
//                                        uploadFile(in, name));
//                                ((MainActivity) context)
//                                        .runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                if (mProgressDialog.isShowing())
//                                                    mProgressDialog.dismiss();
//                                                Intent mIntent = new Intent();
//                                                if (mProgressDialog.isShowing())
//                                                    mProgressDialog.dismiss();
//                                                mIntent.putExtra(
//                                                        "data",
//                                                        new DataFile(
//                                                                fileSize,
//                                                                name,
//                                                                mJSONObject
//                                                                        .optString("url"), files[position]));
//                                                ((MainActivity) context)
//                                                        .setResult(1, mIntent);
//                                                ((MainActivity) context)
//                                                        .finish();
//                                            }
//                                        });
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }).start();

                }
                try {
                    ((MainActivity) context).titleFilePath.setText("当前路径为:"
                            + current.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    public String uploadFile(byte[] bytes, String fileName) throws Exception {
        InputStream is = new ByteArrayInputStream(bytes);
        String fname = null;
        try {
            fname = uploadFile(is, fileName);
        } catch (Exception e) {
            try {
                if (is != null)
                    is.close();
            } catch (Exception e2) {
            }
        }
        return fname;
    }

    @SuppressWarnings("deprecation")
    public String uploadFile(InputStream is, String fileName) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(BaseConfig.getUri() + "/fileUpload");
        InputStreamBody isb = new InputStreamBody(is,
                java.net.URLEncoder.encode(fileName, "utf-8"));
        MultipartEntity multipartEntity = new MultipartEntity();
        multipartEntity.addPart("MyFile", isb);
        post.setEntity(multipartEntity);
        HttpResponse response = client.execute(post);
        System.out.println(response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            is = response.getEntity().getContent();
            String result = inStream2String(is);
            // Assert.assertEquals(result, "UPLOAD_SUCCESS");
            System.out.println(result);
            return result;
        }

        return null;
    }

    // 将输入流转换成字符串
    private static String inStream2String(InputStream is) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = is.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        return new String(baos.toByteArray());
    }

    public void MUploadFile(Son s) {
//		MRet mMRet = (MRet) s.getBuild();
//		Intent mIntent = new Intent();
//		if (mProgressDialog.isShowing())
//			mProgressDialog.dismiss();
//		mIntent.putExtra("data", new DataFile(fileSize, name, mMRet.msg));
//		((MainActivity) context).setResult(1, mIntent);
//		((MainActivity) context).finish();
    }



    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file
     */
    private String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        // 获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "")
            return type;
        // 在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) { // MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    private final String[][] MIME_MapTable = {
            // {后缀名，MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx",
                    "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"}, {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"}, {".rtf", "application/rtf"},
            {".sh", "text/plain"}, {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"}, {".txt", "text/plain"},
            {".wav", "audio/x-wav"}, {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"}, {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"}, {"", "*/*"}};
}
