package com.qbase.dialog;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.qay.qbase.animationlibrary.QBaseAnimatorSet;

public abstract class QBaseBottomTopDialog<T extends QBaseBottomTopDialog<T>> extends QBaseBottomDialog<T> {
    public QBaseBottomTopDialog(Context context, View animateView) {
        super(context);
        mAnimateView = animateView;

        mInnerShowAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0);

        mInnerDismissAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
    }

    public QBaseBottomTopDialog(Context context) {
        this(context, null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLlTop.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        mLlTop.setGravity(Gravity.BOTTOM);
        getWindow().setGravity(Gravity.BOTTOM);
        mLlTop.setPadding(mLeft, mTop, mRight, mBottom);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        showWithAnim();
    }

    @Override
    public void dismiss() {
        dismissWithAnim();
    }

    private QBaseAnimatorSet mWindowInAs;
    private QBaseAnimatorSet mWindowOutAs;

    @Override
    protected QBaseAnimatorSet getWindowInAs() {
        if (mWindowInAs == null) {
            mWindowInAs = new WindowInAs();
        }
        return mWindowInAs;
    }

    @Override
    protected QBaseAnimatorSet getWindowOutAs() {
        if (mWindowOutAs == null) {
            mWindowOutAs = new WindowOutAs();
        }
        return mWindowOutAs;
    }


    private class WindowInAs extends QBaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator oa1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.9f);
            ObjectAnimator oa2 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.9f);
            animatorSet.playTogether(oa1, oa2);
        }
    }

    private class WindowOutAs extends QBaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator oa1 = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1f);
            ObjectAnimator oa2 = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1f);
            animatorSet.playTogether(oa1, oa2);
        }
    }
}
