package com.qbase.dialog;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;

import com.qay.qbase.animationlibrary.QBaseAnimatorSet;
import com.qbase.dialog.util.QBaseDialogUtils;

public abstract class QBaseDialog<T extends QBaseDialog<T>> extends Dialog {
    /** mTag(*/
    protected String mTag;
    /** mContext*/
    protected Context mContext;
    /** (DisplayMetrics) */
    protected DisplayMetrics mDisplayMetrics;
    /** enable dismiss outside dialog(*/
    protected boolean mCancel;
    /** dialog width scale( */
    protected float mWidthScale = 1;
    /** dialog height scale */
    protected float mHeightScale;
    /** mShowAnim */
    private QBaseAnimatorSet mShowAnim;
    /** mDismissAnim */
    private QBaseAnimatorSet mDismissAnim;
    /** top container*/
    protected LinearLayout mLlTop;
    /** container to control dialog height*/
    protected LinearLayout mLlControlHeight;
    /** the child of mLlControlHeight you create. */
    protected View mOnCreateView;
    /** is mShowAnim running */
    private boolean mIsShowAnim;
    /** is DismissAnim running */
    private boolean mIsDismissAnim;
    /** max heigh */
    protected float mMaxHeight;
    /** show Dialog as PopupWindow */
    private boolean mIsPopupStyle;
    /** automatic dimiss dialog after given delay*/
    private boolean mAutoDismiss;
    /** delay (milliseconds) to dimiss dialog */
    private long mAutoDismissDelay = 1500;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    /**
     * method execute order:
     * show:constrouctor---show---oncreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     */
    public QBaseDialog(Context context) {
        super(context);
        setDialogTheme();
        mContext = context;
        mTag = getClass().getSimpleName();
        setCanceledOnTouchOutside(true);
    }

    public QBaseDialog(Context context, boolean isPopupStyle) {
        this(context);
        mIsPopupStyle = isPopupStyle;
    }

    /** set dialog theme*/
    private void setDialogTheme() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// android:windowNoTitle
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// android:windowBackground
        getWindow().addFlags(LayoutParams.FLAG_DIM_BEHIND);// android:backgroundDimEnabled true
    }

    /**
     * inflate layout for dialog ui and return
     * <pre>
     * public View onCreateView() {
     *      View inflate = View.inflate(mContext, R.layout.dialog_share, null);
     *      return inflate;
     * }
     * </pre>
     */
    public abstract View onCreateView();

    public void onViewCreated(View inflate) {
    }

    /** set Ui data or logic opreation before attatched window */
    public abstract void setUiBeforShow();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDisplayMetrics = mContext.getResources().getDisplayMetrics();
        mMaxHeight = mDisplayMetrics.heightPixels - QBaseDialogUtils.getHeight(mContext);
        // mMaxHeight = mDisplayMetrics.heightPixels;

        mLlTop = new LinearLayout(mContext);
        mLlTop.setGravity(Gravity.CENTER);

        mLlControlHeight = new LinearLayout(mContext);
        mLlControlHeight.setOrientation(LinearLayout.VERTICAL);

        mOnCreateView = onCreateView();
        mLlControlHeight.addView(mOnCreateView);
        mLlTop.addView(mLlControlHeight);
        onViewCreated(mOnCreateView);

        if (mIsPopupStyle) {
            setContentView(mLlTop, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        } else {
            setContentView(mLlTop, new ViewGroup.LayoutParams(mDisplayMetrics.widthPixels, (int) mMaxHeight));
        }

        mLlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCancel) {
                    dismiss();
                }
            }
        });

        mOnCreateView.setClickable(true);
    }

    /** get actual created view */
    public View getCreateView() {
        return mOnCreateView;
    }

    /**
     * when dailog attached to window,set dialog width and height and show anim
     */
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setUiBeforShow();

        int width;
        if (mWidthScale == 0) {
            width = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            width = (int) (mDisplayMetrics.widthPixels * mWidthScale);
        }

        int height;
        if (mHeightScale == 0) {
            height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else if (mHeightScale == 1) {
//            height = ViewGroup.LayoutParams.MATCH_PARENT;
            height = (int) mMaxHeight;
        } else {
            height = (int) (mMaxHeight * mHeightScale);
        }

        mLlControlHeight.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        if (mShowAnim != null) {
            mShowAnim.listener(new QBaseAnimatorSet.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    mIsShowAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mIsShowAnim = false;
                    delayDismiss();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    mIsShowAnim = false;
                }
            }).playOn(mLlControlHeight);
        } else {
            QBaseAnimatorSet.reset(mLlControlHeight);
            delayDismiss();
        }
    }


    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        this.mCancel = cancel;
        super.setCanceledOnTouchOutside(cancel);
    }

    @Override
    public void show() {
        super.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void dismiss() {
        if (mDismissAnim != null) {
            mDismissAnim.listener(new QBaseAnimatorSet.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    mIsDismissAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mIsDismissAnim = false;
                    superDismiss();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    mIsDismissAnim = false;
                    superDismiss();
                }
            }).playOn(mLlControlHeight);
        } else {
            superDismiss();
        }
    }

    /** dismiss without anim */
    public void superDismiss() {
        super.dismiss();
    }

    /** dialog anim by styles*/
    public void show(int animStyle) {
        Window window = getWindow();
        window.setWindowAnimations(animStyle);
        show();
    }

    /** show at location only valid for mIsPopupStyle true*/
    public void showAtLocation(int gravity, int x, int y) {
        if (mIsPopupStyle) {
            Window window = getWindow();
            LayoutParams params = window.getAttributes();
            window.setGravity(gravity);
            params.x = x;
            params.y = y;
        }

        show();
    }

    /** show at location only valid for mIsPopupStyle true*/
    public void showAtLocation(int x, int y) {
        int gravity = Gravity.LEFT | Gravity.TOP;//Left Top (坐标原点为右上角)
        showAtLocation(gravity, x, y);
    }

    /** set window dim or not*/
    public T dimEnabled(boolean isDimEnabled) {
        if (isDimEnabled) {
            getWindow().addFlags(LayoutParams.FLAG_DIM_BEHIND);
        } else {
            getWindow().clearFlags(LayoutParams.FLAG_DIM_BEHIND);
        }
        return (T) this;
    }

    /** set dialog height scale:0-1*/
    public T heightScale(float heightScale) {
        mHeightScale = heightScale;
        return (T) this;
    }

    /** set dialog width scale:0-1 */
    public T widthScale(float widthScale) {
        this.mWidthScale = widthScale;
        return (T) this;
    }

    /** set show anim */
    public T showAnim(QBaseAnimatorSet showAnim) {
        mShowAnim = showAnim;
        return (T) this;
    }

    /** set dismiss anim*/
    public T dismissAnim(QBaseAnimatorSet dismissAnim) {
        mDismissAnim = dismissAnim;
        return (T) this;
    }

    /** automatic dimiss dialog after given delay*/
    public T autoDismiss(boolean autoDismiss) {
        mAutoDismiss = autoDismiss;
        return (T) this;
    }

    /** set dealy (milliseconds) to dimiss dialog*/
    public T autoDismissDelay(long autoDismissDelay) {
        mAutoDismissDelay = autoDismissDelay;
        return (T) this;
    }

    private void delayDismiss() {
        if (mAutoDismiss && mAutoDismissDelay > 0) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, mAutoDismissDelay);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mIsDismissAnim || mIsShowAnim || mAutoDismiss) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        if (mIsDismissAnim || mIsShowAnim || mAutoDismiss) {
            return;
        }
        super.onBackPressed();
    }

    /** dp to px */
    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
