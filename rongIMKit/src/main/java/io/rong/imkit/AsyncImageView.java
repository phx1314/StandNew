package io.rong.imkit;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;

import com.sea_monster.cache.CacheableBitmapDrawable;
import com.sea_monster.common.BackgroundThread;
import com.sea_monster.resource.Resource;
import com.sea_monster.resource.ResourceHandler;
import com.sea_monster.resource.ResourceHandler.RequestCallback;
import com.sea_monster.widget.CircleBitmapDrawable;

import java.lang.ref.WeakReference;
import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import io.rong.imkit.R.styleable;
import io.rong.imkit.widget.RoundRectDrawable;

public class AsyncImageView extends CacheableImageView implements Observer {
    static final ExecutorService mMultiThreadExecutor;
    static final boolean DEBUG = true;
    Resource mResource;
    Future<?> mCurrentRunnable;
    Drawable mDefaultDrawable;
    Runnable mAttachedRunnable;
    static final int STATUS_DISPLAY = 1;
    static final int STATUS_EMPTY = 0;
    boolean isAttached;
    private boolean isCircle;
    private int status;
    private float minShortSideSize = 0.0F;
    public int mCornerRadius = 0;

    public void update(Observable observable, Object data) {
        if (this.mResource != null) {
            if (data instanceof RequestCallback) {
                RequestCallback callback = (RequestCallback) data;
                if (callback != null && callback.isSuccess() && this.mResource.equals(callback.getResource())) {
                    this.post(new Runnable() {
                        public void run() {
                            AsyncImageView.this.refreshResource();
                        }
                    });
                }
            }

        }
    }

    public AsyncImageView(Context context) {
        super(context);
    }

    public AsyncImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!this.isInEditMode()) {
            TypedArray a = context.obtainStyledAttributes(attrs, styleable.AsyncImageView);
            int resId = a.getResourceId(styleable.AsyncImageView_RCDefDrawable, 0);
            int shape = a.getInt(styleable.AsyncImageView_RCShape, 0);
            this.minShortSideSize = a.getDimension(styleable.AsyncImageView_RCMinShortSideSize, 0.0F);
            this.mCornerRadius = (int) a.getDimension(styleable.AsyncImageView_RCCornerRadius, 0.0F);
            Log.d("AsyncImageView", "minShortSideSize:" + this.minShortSideSize);
            this.isCircle = shape == 1;
            if (resId != 0) {
                this.mDefaultDrawable = this.getResources().getDrawable(resId);
            }

            a.recycle();
        }
    }

    public Resource getResource() {
        return this.mResource;
    }

    public void setDefaultDrawable(Drawable defaultDrawable) {
        if (defaultDrawable == null) {
            this.mDefaultDrawable = null;
        } else {
            if (this.isCircle && defaultDrawable instanceof BitmapDrawable) {
                this.mDefaultDrawable = new CircleBitmapDrawable(this.getResources(), ((BitmapDrawable) defaultDrawable).getBitmap());
            } else if (this.mCornerRadius > 0 && defaultDrawable instanceof BitmapDrawable) {
                this.mDefaultDrawable = new RoundRectDrawable(((BitmapDrawable) defaultDrawable).getBitmap(), this.mCornerRadius);
            } else {
                this.mDefaultDrawable = defaultDrawable;
            }

        }
    }

    public void setImageDrawable(Drawable drawable) {
        if (this.isCircle && drawable instanceof BitmapDrawable) {
            super.setImageDrawable(new CircleBitmapDrawable(this.getResources(), ((BitmapDrawable) drawable).getBitmap()));
        } else if (this.mCornerRadius > 0 && drawable instanceof BitmapDrawable) {
            super.setImageDrawable(new RoundRectDrawable(((BitmapDrawable) drawable).getBitmap(), this.mCornerRadius));
        } else {
            super.setImageDrawable(drawable);
        }

    }

    public void clean() {
        this.mResource = null;
        this.status = 0;
        this.setImageDrawable(this.mDefaultDrawable);
    }

    public void setResource(Resource resource) {
        Resource previous = this.getResource();
        this.mResource = resource;
        if (this.mResource == null) {
            this.status = 0;
            this.setImageDrawable(this.mDefaultDrawable);
        } else {
            if (!this.mResource.equals(previous)) {
                this.setImageDrawable(this.mDefaultDrawable);
                this.status = 0;
            }

            if (this.status == 0) {
                this.mAttachedRunnable = null;
                this.cancelRequest();
                if (this.mResource != null && this.mResource.getUri() != null && ResourceHandler.getInstance().containsInMemoryCache(this.mResource)) {
                    CacheableBitmapDrawable drawable = ResourceHandler.getInstance().getDrawable(this.mResource);
                    if (drawable != null && drawable.getBitmap() != null) {
                        if (this.isCircle) {
                            this.setImageDrawable(new CircleBitmapDrawable(this.getResources(), drawable.getBitmap()));
                        } else if (this.mCornerRadius > 0) {
                            this.setDefaultDrawableSize(new RoundRectDrawable(drawable.getBitmap(), this.mCornerRadius));
                        } else {
                            this.setDefaultDrawableSize(drawable);
                        }

                        this.status = 1;
                    } else {
                        this.setImageDrawable(this.mDefaultDrawable);
                    }
                } else {
                    this.mCurrentRunnable = mMultiThreadExecutor.submit(new AsyncImageView.PhotoLoadRunnable(this, ResourceHandler.getInstance(), this.mResource));
                }
            }

        }
    }

    private void setDefaultDrawableSize(Drawable drawable) {
        if (drawable != null) {
            float width = (float) drawable.getMinimumWidth();
            float height = (float) drawable.getMinimumHeight();
            float finalWidth = 0.0F;
            float finalHeight = 0.0F;
            if (this.minShortSideSize > 0.0F) {
                if (width >= this.minShortSideSize && height >= this.minShortSideSize) {
                    LayoutParams params2 = this.getLayoutParams();
                    params2.height = (int) height;
                    params2.width = (int) width;
                    this.setLayoutParams(params2);
                } else {
                    float params = width / height;
                    if (params > 1.0F) {
                        finalHeight = this.minShortSideSize;
                        finalWidth = this.minShortSideSize * params;
                    } else {
                        finalWidth = this.minShortSideSize;
                        if (params != 0.0F) {
                            finalHeight = this.minShortSideSize / params;
                        } else {
                            finalHeight = this.minShortSideSize;
                        }
                    }

                    LayoutParams params1 = this.getLayoutParams();
                    params1.height = (int) finalHeight;
                    params1.width = (int) finalWidth;
                    this.setLayoutParams(params1);
                }
            }

            this.setImageDrawable(drawable);
            this.invalidate();
        }
    }

    protected void onDetachedFromWindow() {
        ResourceHandler.getInstance().deleteObserver(this);
        this.cancelRequest();
        super.onDetachedFromWindow();
        this.isAttached = false;
        this.mAttachedRunnable = null;
    }

    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.isInEditMode()) {
            ResourceHandler.getInstance().addObserver(this);
            this.isAttached = true;
            if (this.mAttachedRunnable != null) {
                this.mAttachedRunnable.run();
                this.mAttachedRunnable = null;
            }

        }
    }

    public void cancelRequest() {
        if (null != this.mCurrentRunnable) {
            this.mCurrentRunnable.cancel(true);
            this.mCurrentRunnable = null;
        }

    }

    public void refreshResource() {
        this.setResource(this.mResource);
    }

    static {
        int coreNum = Math.round((float) Runtime.getRuntime().availableProcessors());
        boolean threadNum = true;
        int threadNum1;
        switch (coreNum) {
            case 1:
                threadNum1 = coreNum;
                break;
            case 2:
                threadNum1 = coreNum;
                break;
            default:
                threadNum1 = 3;
        }

        mMultiThreadExecutor = Executors.newFixedThreadPool(threadNum1, new AsyncImageView.PhotoThreadFactory());
    }

    static final class PhotoLoadRunnable extends BackgroundThread {
        private final WeakReference<AsyncImageView> mImageView;
        private final ResourceHandler mHandler;
        private final Resource mResource;

        public PhotoLoadRunnable(AsyncImageView imageView, ResourceHandler handler, Resource resource) {
            this.mImageView = new WeakReference(imageView);
            this.mHandler = handler;
            this.mResource = resource;
        }

        public CacheableBitmapDrawable diskDrawable;

        public void runImpl() {
            final AsyncImageView imageView = (AsyncImageView) this.mImageView.get();
            if (null != imageView) {
                diskDrawable = null;
                Resource e = this.mResource;
                synchronized (this.mResource) {
                    diskDrawable = this.mHandler.getDrawable(this.mResource);
                }

                if (diskDrawable != null && diskDrawable.getBitmap() != null) {
                    if (imageView.status == 0 && imageView.getResource().equals(this.mResource) && imageView.isAttached) {
                        imageView.post(new Runnable() {
                            public void run() {
                                if (imageView.getResource() != null && imageView.getResource().equals(PhotoLoadRunnable.this.mResource)) {
                                    if (imageView.isCircle) {
                                        imageView.setImageDrawable(new CircleBitmapDrawable(imageView.getResources(), diskDrawable.getBitmap()));
                                    } else if (imageView.mCornerRadius > 0) {
                                        imageView.setDefaultDrawableSize(new RoundRectDrawable(diskDrawable.getBitmap(), imageView.mCornerRadius));
                                    } else {
                                        imageView.setDefaultDrawableSize(diskDrawable);
                                    }

                                    imageView.status = 1;
                                }
                            }
                        });
                    } else {
                        imageView.mAttachedRunnable = new Runnable() {
                            public void run() {
                                if (imageView.getResource() != null && imageView.getResource().equals(PhotoLoadRunnable.this.mResource)) {
                                    if (imageView.isCircle) {
                                        imageView.setImageDrawable(new CircleBitmapDrawable(imageView.getResources(), diskDrawable.getBitmap()));
                                    } else if (imageView.mCornerRadius > 0) {
                                        imageView.setDefaultDrawableSize(new RoundRectDrawable(diskDrawable.getBitmap(), imageView.mCornerRadius));
                                    } else {
                                        imageView.setDefaultDrawableSize(diskDrawable);
                                    }

                                    imageView.status = 1;
                                }
                            }
                        };
                    }
                } else {
                    if (imageView.isAttached) {
                        imageView.post(new Runnable() {
                            public void run() {
                                imageView.setImageDrawable(imageView.mDefaultDrawable);
                            }
                        });
                    } else {
                        imageView.mAttachedRunnable = new Runnable() {
                            public void run() {
                                imageView.setImageDrawable(imageView.mDefaultDrawable);
                            }
                        };
                    }

                    if (this.mResource.getUri().getScheme().equals("http") || this.mResource.getUri().getScheme().equals("https")) {
                        try {
                            this.mHandler.requestResource(this.mResource);
                        } catch (URISyntaxException var5) {
                            var5.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    static final class PhotoThreadFactory implements ThreadFactory {
        private final String mThreadName;

        public PhotoThreadFactory(String threadName) {
            this.mThreadName = threadName;
        }

        public PhotoThreadFactory() {
            this("Photo");
        }

        public Thread newThread(Runnable r) {
            return null != this.mThreadName ? new Thread(r, this.mThreadName) : new Thread(r);
        }
    }
}
