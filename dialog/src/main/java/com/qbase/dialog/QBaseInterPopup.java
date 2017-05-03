package com.qbase.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.qbase.dialog.util.QBaseDialogUtils;

/** Base class to help create PopupWindow Style Dialog */
public abstract class QBaseInterPopup<T extends QBaseInterPopup<T>> extends QBaseDialog<T> {
    protected View mAnchorView;
    protected int mX;
    protected int mY;
    /** BubblePopup Gravity.Top Gravity.Bottom */
    protected int mGravity;
    protected float mXOffset;
    protected float mYOffset;
    protected boolean isLayoutObtain;

    public QBaseInterPopup(Context context) {
        super(context);
        heightScale(1);
        dimEnabled(false);
    }

    @Override
    public void onViewCreated(final View inflate) {
        mLlControlHeight.setClipChildren(false);
        if (inflate != null) {
            inflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    isLayoutObtain = true;
                    onLayoutObtain();
                }
            });
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mOnCreateView.setClickable(false);
        if (isLayoutObtain) {
            onLayoutObtain();
        }
    }

    /** At this time, we can get view size in dialog */
    public abstract void onLayoutObtain();

    public abstract T anchorView(View anchorView);

    /** Gravity.Top or Gravity.Bottom of given positon */
    public T gravity(int gravity) {
        if (gravity != Gravity.TOP && gravity != Gravity.BOTTOM) {
            throw new IllegalArgumentException("Gravity must be either Gravity.TOP or Gravity.BOTTOM");
        }
        mGravity = gravity;
        anchorView(mAnchorView);
        return (T) this;
    }

    public T location(int x, int y) {
        mX = x;
        mY = y - QBaseDialogUtils.getHeight(mContext);
        return (T) this;
    }
}
