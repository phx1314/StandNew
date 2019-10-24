package com.jinqu.standardNew.view;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public abstract class SwipMoreView extends FrameLayout {
    private float mDownX;
    private float nowX;
    private boolean swipable = true;
    private boolean isParentIntouch = true;
    private boolean hasmDownX = false;
    private boolean hasmoved = false;
    private int mTouchSlop;
    public boolean isLeft;

    public SwipMoreView(Context context) {
        super(context);
        this.init(context);
    }

    public boolean isSwipable() {
        return this.swipable;
    }

    public void setSwipable(boolean swipable) {
        this.swipable = swipable;
    }

    public SwipMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public SwipMoreView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init(context);
    }

    private void init(Context context) {
        ViewConfiguration configuration = ViewConfiguration.get(this.getContext());
        this.mTouchSlop = configuration.getScaledTouchSlop();
        this.postDelayed(new Runnable() {
            public void run() {
                SwipMoreView.this.moreView().setVisibility(4);
            }
        }, 10L);
    }

    public abstract boolean swipLeftAble();

    public abstract boolean swipRightAble();

    public abstract View swipView();

    public abstract View moreView();

    public abstract void swipEnd(boolean isLeft);

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!this.swipable) {
            return super.onInterceptTouchEvent(ev);
        } else {
            int action = ev.getAction();
            switch (action & 255) {
                case 0:
                    this.mDownX = ev.getRawX();
                    this.hasmoved = false;
                    this.nowX = ViewHelper.getX(this.swipView());
                    this.hasmDownX = true;
                    break;
                case 1:
                case 3:
                    this.mDownX = 0.0F;
                    this.hasmoved = false;
                    this.hasmDownX = false;
                    break;
                case 2:
                    if (!this.hasmDownX) {
                        this.mDownX = ev.getRawX();
                        this.nowX = ViewHelper.getX(this.swipView());
                        this.hasmDownX = true;
                    }

                    if (Math.abs(ev.getRawX() - this.mDownX) > (float) this.mTouchSlop) {
                        return true;
                    }
                case 4:
                case 5:
                case 6:
            }

            return super.onInterceptTouchEvent(ev);
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.swipable) {
            return false;
        } else {
            float deltaX;
            switch (ev.getActionMasked()) {
                case 0:
                    this.mDownX = ev.getRawX();
                    this.hasmoved = false;
                    this.nowX = ViewHelper.getX(this.swipView());
                    this.hasmDownX = true;
                    break;
                case 1:
                case 3:
                    if (!this.isParentIntouch) {
                        this.requestParentDisallowInterceptTouchEvent(false);
                    }
                    deltaX = ViewHelper.getX(this.swipView());
                    isLeft = deltaX > 0;
                    float v1 = 0.0F;
                    View margs1 = this.moreView();
                    int[] mv1 = this.getSiwpViewMargin();
                    if (margs1 != null) {
                        float ishide = (float) margs1.getWidth();
                        if (ishide / 2.0F < deltaX) {
                            v1 = ishide;
                        } else if (-ishide / 2.0F > deltaX) {
                            v1 = -ishide + (float) mv1[1] + (float) mv1[0];
                        } else {
                            v1 = (float) (0 + mv1[0]);
                        }
                    }

                    final boolean ishide1 = v1 == (float) (0 + mv1[0]);
                    ViewPropertyAnimator.animate(this.swipView()).x(v1).alpha(1.0F).setDuration(250L).setListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            if (ishide1) {
                                SwipMoreView.this.moreView().setVisibility(4);
                            } else {
                                swipEnd(isLeft);
                            }

                        }
                    });
                    this.mDownX = 0.0F;
                    this.hasmoved = false;
                    this.nowX = ViewHelper.getX(this.swipView());
                    this.hasmDownX = false;
                    break;
                case 2:
                    if (Math.abs(ev.getRawX() - this.mDownX) > (float) this.mTouchSlop && this.isParentIntouch) {
                        this.requestParentDisallowInterceptTouchEvent(true);
                        if (!this.hasmoved) {
                            this.mDownX = ev.getRawX();
                            this.hasmoved = true;
                        }
                    }

                    if (this.hasmoved) {
                        deltaX = this.nowX + ev.getRawX() - this.mDownX;
                        View v = this.moreView();
                        int[] margs = this.getSiwpViewMargin();
                        if (v != null) {
                            float mv = (float) v.getWidth();
                            if (!this.swipLeftAble() && deltaX < 0.0F) {
                                deltaX = 0.0F;
                            }

                            if (!this.swipRightAble() && deltaX > 0.0F) {
                                deltaX = 0.0F;
                            }

                            if (deltaX > 0.0F) {
                                ViewHelper.setX(v, 0.0F);
                                if (deltaX > mv) {
                                    deltaX = mv;
                                }
                            }

                            if (deltaX < 0.0F) {
                                ViewHelper.setX(v, (float) this.getWidth() - mv);
                                if (deltaX < -mv + (float) margs[1] + (float) margs[0]) {
                                    deltaX = -mv + (float) margs[1] + (float) margs[0];
                                }
                            }
                        }

                        if (deltaX != 0.0F) {
                            this.moreView().setVisibility(0);
                        } else {
                            deltaX = (float) margs[0];
                        }

                        ViewHelper.setX(this.swipView(), deltaX);
                    }
            }

            super.onTouchEvent(ev);
            return true;
        }
    }

    public int[] getSiwpViewMargin() {
        int[] margs = new int[]{0, 0};
        ViewGroup.LayoutParams layout = this.swipView().getLayoutParams();
        if (layout instanceof MarginLayoutParams) {
            margs[0] = ((MarginLayoutParams) layout).leftMargin;
            margs[1] = ((MarginLayoutParams) layout).rightMargin;
        }

        return margs;
    }

    public void moveBack() {
        int[] margs = this.getSiwpViewMargin();
        ViewPropertyAnimator.animate(this.swipView()).x((float) (0 + margs[0])).alpha(1.0F).setDuration(250L).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                SwipMoreView.this.moreView().setVisibility(4);
            }
        });
    }

    public void reset() {
        ViewHelper.setX(this.swipView(), 0.0F);
        this.moreView().setVisibility(4);
    }

    private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
        this.isParentIntouch = !disallowIntercept;
        ViewParent parent = this.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }

    }
}
