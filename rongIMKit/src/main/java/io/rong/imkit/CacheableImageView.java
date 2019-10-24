package io.rong.imkit;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;

import com.mdx.framework.widget.MImageView;
import com.sea_monster.cache.CacheableBitmapDrawable;

public class CacheableImageView extends MImageView {
    private static void onDrawableSet(Drawable drawable) {
        if(drawable instanceof CacheableBitmapDrawable) {
            ((CacheableBitmapDrawable)drawable).setBeingUsed(true);
        }

    }

    private static void onDrawableUnset(Drawable drawable) {
        if(drawable instanceof CacheableBitmapDrawable) {
            ((CacheableBitmapDrawable)drawable).setBeingUsed(false);
        }

    }

    public CacheableImageView(Context context) {
        super(context);
    }

    public CacheableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CacheableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setImageDrawable(Drawable drawable) {
        Drawable previousDrawable = this.getDrawable();
        super.setImageDrawable(drawable);
        if(drawable != previousDrawable) {
            onDrawableSet(drawable);
            onDrawableUnset(previousDrawable);
        }

    }

    public void setImageResource(int resId) {
        Drawable previousDrawable = this.getDrawable();
        super.setImageResource(resId);
        onDrawableUnset(previousDrawable);
    }

    public void setImageURI(Uri uri) {
        Drawable previousDrawable = this.getDrawable();
        super.setImageURI(uri);
        onDrawableUnset(previousDrawable);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.setImageDrawable((Drawable)null);
    }
}
