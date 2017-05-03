package com.qbase.dialog;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.qay.qbase.animationlibrary.QBaseAnimatorSet;

public abstract class QBaseBottomDialog<T extends QBaseBottomDialog<T>> extends QBaseDialog<T> {
    protected View mAnimateView;
    private QBaseAnimatorSet mWindowInAs;
    private QBaseAnimatorSet mWindowOutAs;
    protected Animation mInnerShowAnim;
    protected Animation mInnerDismissAnim;
    protected long mInnerAnimDuration = 350;
    protected boolean mIsInnerShowAnim;
    protected boolean mIsInnerDismissAnim;
    protected int mLeft, mTop, mRight, mBottom;

    public QBaseBottomDialog(Context context) {
        super(context);
    }

    /** set duration for inner of mAnimateView(set animateView time) */
    public T innerAnimDuration(long innerAnimDuration) {
        mInnerAnimDuration = innerAnimDuration;
        return (T) this;
    }

    public T padding(int left, int top, int right, int bottom) {
        mLeft = left;
        mTop = top;
        mRight = right;
        mBottom = bottom;
        return (T) this;
    }

    /** show dialog and mAnimateView with inner show*/
    protected void showWithAnim() {
        if (mInnerShowAnim != null) {
            mInnerShowAnim.setDuration(mInnerAnimDuration);
            mInnerShowAnim.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mIsInnerShowAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mIsInnerShowAnim = false;
                }
            });
            mLlControlHeight.startAnimation(mInnerShowAnim);
        }

        if (mAnimateView != null) {
            if (getWindowInAs() != null) {
                mWindowInAs = getWindowInAs();
            }
            mWindowInAs.duration(mInnerAnimDuration).playOn(mAnimateView);
        }
    }

    /** dimiss dialog and mAnimateView with inner dismiss  */
    protected void dismissWithAnim() {
        if (mInnerDismissAnim != null) {
            mInnerDismissAnim.setDuration(mInnerAnimDuration);
            mInnerDismissAnim.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mIsInnerDismissAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mIsInnerDismissAnim = false;
                    superDismiss();
                }
            });

            mLlControlHeight.startAnimation(mInnerDismissAnim);
        } else {
            superDismiss();
        }

        if (mAnimateView != null) {
            if (getWindowOutAs() != null) {
                mWindowOutAs = getWindowOutAs();
            }
            mWindowOutAs.duration(mInnerAnimDuration).playOn(mAnimateView);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mIsInnerDismissAnim || mIsInnerShowAnim) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        if (mIsInnerDismissAnim || mIsInnerShowAnim) {
            return;
        }
        super.onBackPressed();
    }

    protected abstract QBaseAnimatorSet getWindowInAs();

    protected abstract QBaseAnimatorSet getWindowOutAs();
}
