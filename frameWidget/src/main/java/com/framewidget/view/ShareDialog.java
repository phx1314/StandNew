package com.framewidget.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.framewidget.F;
import com.framewidget.R;

/**
 * 分享
 *
 * @author wchj
 */
public class ShareDialog extends Dialog implements
        android.view.View.OnClickListener {
    public Context context;
    public String url;
    public String content;
    public String title;
    public Object obj;
    private String imageUrl;
    private LinearLayout lin_qq, lin_xinlang, lin_qqkongjian, lin_weixin,
            lin_pengyouquan, lin_youjian, lin_duanxin, lin_copy;

    public ShareDialog(Context context) {
        super(context);
        this.context = context;
    }

    public ShareDialog(Context context, int theme, String imageUrl, String url, String content,
                       String title) {
        super(context, theme);
        this.context = context;
        this.imageUrl = imageUrl;
        this.url = url;
        this.content = TextUtils.isEmpty(content) ? " " : content;
        this.title = TextUtils.isEmpty(title) ? " " : title;
    }

    public ShareDialog(Context context, int theme, String imageUrl, String url, String content,
                       String title, Object obj) {
        super(context, theme);
        this.context = context;
        this.url = url;
        this.imageUrl = imageUrl;
        this.content = TextUtils.isEmpty(content) ? " " : content;
        this.title = TextUtils.isEmpty(title) ? " " : title;
        this.obj = obj;
    }

    public ShareDialog(Context context, int theme, String imageUrl, String url) {
        super(context, theme);
        this.context = context;
        this.url = url;
        this.imageUrl = imageUrl;
        this.content = " ";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);
        initview();
        setShare(url, content, title);
    }

    private void initview() {
        lin_qq = (LinearLayout) findViewById(R.id.lin_qq);
        lin_xinlang = (LinearLayout) findViewById(R.id.lin_xinlang);
        lin_qqkongjian = (LinearLayout) findViewById(R.id.lin_qqkongjian);
        lin_weixin = (LinearLayout) findViewById(R.id.lin_weixin);
        lin_pengyouquan = (LinearLayout) findViewById(R.id.lin_pengyouquan);
        lin_youjian = (LinearLayout) findViewById(R.id.lin_youjian);
        lin_duanxin = (LinearLayout) findViewById(R.id.lin_duanxin);
        lin_copy = (LinearLayout) findViewById(R.id.lin_copy);
        lin_qq.setOnClickListener(this);
        lin_xinlang.setOnClickListener(this);
        lin_qqkongjian.setOnClickListener(this);
        lin_weixin.setOnClickListener(this);
        lin_pengyouquan.setOnClickListener(this);
        lin_youjian.setOnClickListener(this);
        lin_duanxin.setOnClickListener(this);
        lin_copy.setOnClickListener(this);
    }

    private void setShare(String url, String content, String title) {
        // Frontia.init(context.getApplicationContext(),
        // "2GYSP4jEmn4TigW466ZS5EyO");
        // socialShare = Frontia.getSocialShare();
        // socialShare.setContext(getContext());
        // socialShare.setClientId(MediaType.WEIXIN.toString(), F.WEIXINKEY);
        // socialShare.setClientId(MediaType.QQFRIEND.toString(), "1104610443");
        // socialShare.setClientName(MediaType.QQFRIEND.toString(), F.APPNAME);
        // mImageContent.setQQRequestType(FrontiaIQQReqestType.TYPE_DEFAULT);
        // mImageContent.setTitle(title);
        // mImageContent.setContent(content);
        // mImageContent.setLinkUrl(url);
        // mImageContent.setImageData(BitmapFactory.decodeResource(this
        // .getContext().getResources(), F.ICON_SHARE));
        // mImageContent.setImageUri(Uri.parse("http://apps.bdimg.com/developer/static/04171450/developer/images/icon/terminal_adapter.png"));
        // mImageContent.setContent(content);
    }

    @Override
    public void onClick(View arg0) {
        com.framewidget.F.isShare = 2;
//        ShareAction mShareAction = new ShareAction((mactivityactionbar) context);
//        mshareaction.withtext(content);
//        mshareaction.withtitle(title);
//        mshareaction.withtargeturl(url);
//        mshareaction.setcallback(new umsharelistener() {
//            @override
//            public void onresult(share_media share_media) {
//                getsharesuccess();
//            }
//
//            @override
//            public void onerror(share_media share_media, throwable throwable) {
//                toast.maketext(context, "分享失败", toast.length_long)
//                        .show();
//            }
//
//            @override
//            public void oncancel(share_media share_media) {
//
//            }
//        });
//        if (textutils.isempty(imageurl)) {
//            mshareaction.withmedia(new umimage(context, f.icon_share));
//        } else {
//            mshareaction.withmedia(new umimage(context, imageurl));
////            f.icon_share_url = "";
//        }
//        if (arg0.getid() == r.id.lin_qq) {
//            mshareaction.setplatform(share_media.qq);
//        } else if (arg0.getid() == r.id.lin_weixin) {
//            mshareaction.setplatform(share_media.weixin);
//        } else if (arg0.getid() == r.id.lin_pengyouquan) {
////            mshareaction.withtitle(title + "\r\n<br>" +content);
//            mshareaction.setplatform(share_media.weixin_circle);
//        } else if (arg0.getid() == r.id.lin_xinlang) {
//            mshareaction.setplatform(share_media.sina);
//        } else if (arg0.getid() == r.id.lin_youjian) {
//            mshareaction.setplatform(share_media.email);
//        } else if (arg0.getid() == r.id.lin_duanxin) {
//            mshareaction.setplatform(share_media.sms);
//        } else if (arg0.getid() == r.id.lin_qqkongjian) {
//            mshareaction.setplatform(share_media.qzone);
//        } else if (arg0.getid() == r.id.lin_copy) {
//            clipboardmanager clip = (clipboardmanager) ((mactivityactionbar) context)
//                    .getsystemservice(context.clipboard_service);
//            // clip.gettext(); // 粘贴
//            clip.settext(url); // 复制
//            toast.maketext(context, "复制成功", toast.length_long).show();
//            return;
//        }
//        this.dismiss();
//        mShareAction.share();
    }

    /**
     * 分享成功
     */
    private void getShareSuccess() {
//		Toast.makeText(context, "分享成功", Toast.LENGTH_LONG)
//				.show();
        try {
            F.mCallBackShareJieKou.goReturnDo(obj);
        } catch (Exception e) {
        }
        // ApisFactory.getApiMShareSuccess().load(context, ShareDialog.this,
        // "ShareSuccess");
    }

}
