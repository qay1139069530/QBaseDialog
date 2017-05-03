package com.qbase.dialog;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qbase.dialog.listener.OnDialogBtnClick;

public abstract class QQBaseAlertDialog<T extends QQBaseAlertDialog<T>> extends QBaseDialog<T> {
    /** container */
    protected LinearLayout mLlContainer;
    //title
    /** title */
    protected TextView mTvTitle;
    /** title content */
    protected String mTitle;
    /** title textcolor( */
    protected int mTitleTextColor;
    /** title textsize */
    protected float mTitleTextSize;
    /** enable title show( */
    protected boolean mIsTitleShow = true;

    //content
    /** content */
    protected TextView mTvContent;
    /** content text */
    protected String mContent;
    /** show gravity of content */
    protected int mContentGravity = Gravity.CENTER_VERTICAL;
    /** content textcolor */
    protected int mContentTextColor;
    /** content textsize*/
    protected float mContentTextSize;

    //btns
    /** num of btns, [1,3] */
    protected int mBtnNum = 2;
    /** btn container */
    protected LinearLayout mLlBtns;
    /** btns */
    protected TextView mTvBtnLeft;
    protected TextView mTvBtnRight;
    protected TextView mTvBtnMiddle;
    /** btn text*/
    protected String mBtnLeftText = "cancel";
    protected String mBtnRightText = "ok";
    protected String mBtnMiddleText = "continue";
    /** btn textcolor */
    protected int mLeftBtnTextColor;
    protected int mRightBtnTextColor;
    protected int mMiddleBtnTextColor;
    /** btn textsize */
    protected float mLeftBtnTextSize = 15f;
    protected float mRightBtnTextSize = 15f;
    protected float mMiddleBtnTextSize = 15f;
    /** btn press color */
    protected int mBtnPressColor = Color.parseColor("#E3E3E3");// #85D3EF,#ffcccccc,#E3E3E3
    /** left btn click listener */
    protected OnDialogBtnClick mOnBtnLeftClickL;
    /** right btn click listener(*/
    protected OnDialogBtnClick mOnBtnRightClickL;
    /** middle btn click listener */
    protected OnDialogBtnClick mOnBtnMiddleClickL;

    /** corner radius,dp */
    protected float mCornerRadius = 3;
    /** background color*/
    protected int mBgColor = Color.parseColor("#ffffff");

    /**
     * method execute order:
     * show:constrouctor---show---oncreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     *
     * @param context
     */
    public QQBaseAlertDialog(Context context) {
        super(context);
        widthScale(0.88f);

        mLlContainer = new LinearLayout(context);
        mLlContainer.setOrientation(LinearLayout.VERTICAL);

        /** title */
        mTvTitle = new TextView(context);

        /** content */
        mTvContent = new TextView(context);

        /**btns*/
        mLlBtns = new LinearLayout(context);
        mLlBtns.setOrientation(LinearLayout.HORIZONTAL);

        mTvBtnLeft = new TextView(context);
        mTvBtnLeft.setGravity(Gravity.CENTER);

        mTvBtnMiddle = new TextView(context);
        mTvBtnMiddle.setGravity(Gravity.CENTER);

        mTvBtnRight = new TextView(context);
        mTvBtnRight.setGravity(Gravity.CENTER);
    }

    @Override
    public void setUiBeforShow() {
        /** title */
        mTvTitle.setVisibility(mIsTitleShow ? View.VISIBLE : View.GONE);

        mTvTitle.setText(TextUtils.isEmpty(mTitle) ? "Prompt" : mTitle);
        mTvTitle.setTextColor(mTitleTextColor);
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTitleTextSize);

        /** content */
        mTvContent.setGravity(mContentGravity);
        mTvContent.setText(mContent);
        mTvContent.setTextColor(mContentTextColor);
        mTvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, mContentTextSize);
        mTvContent.setLineSpacing(0, 1.3f);

        /**btns*/
        mTvBtnLeft.setText(mBtnLeftText);
        mTvBtnRight.setText(mBtnRightText);
        mTvBtnMiddle.setText(mBtnMiddleText);

        mTvBtnLeft.setTextColor(mLeftBtnTextColor);
        mTvBtnRight.setTextColor(mRightBtnTextColor);
        mTvBtnMiddle.setTextColor(mMiddleBtnTextColor);

        mTvBtnLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, mLeftBtnTextSize);
        mTvBtnRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, mRightBtnTextSize);
        mTvBtnMiddle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mMiddleBtnTextSize);

        if (mBtnNum == 1) {
            mTvBtnLeft.setVisibility(View.GONE);
            mTvBtnRight.setVisibility(View.GONE);
        } else if (mBtnNum == 2) {
            mTvBtnMiddle.setVisibility(View.GONE);
        }

        mTvBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBtnLeftClickL != null) {
                    mOnBtnLeftClickL.onBtnClick();
                } else {
                    dismiss();
                }
            }
        });

        mTvBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBtnRightClickL != null) {
                    mOnBtnRightClickL.onBtnClick();
                } else {
                    dismiss();
                }
            }
        });

        mTvBtnMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBtnMiddleClickL != null) {
                    mOnBtnMiddleClickL.onBtnClick();
                } else {
                    dismiss();
                }
            }
        });
    }

    /** set title text @return MaterialDialogQQ */
    public T title(String title) {
        mTitle = title;
        return (T) this;
    }

    /** set title textcolor */
    public T titleTextColor(int titleTextColor) {
        mTitleTextColor = titleTextColor;
        return (T) this;
    }

    /** set title textsize*/
    public T titleTextSize(float titleTextSize_SP) {
        mTitleTextSize = titleTextSize_SP;
        return (T) this;
    }

    /** enable title show */
    public T isTitleShow(boolean isTitleShow) {
        mIsTitleShow = isTitleShow;
        return (T) this;
    }

    /** set content text */
    public T content(String content) {
        mContent = content;
        return (T) this;
    }

    /** set content gravity*/
    public T contentGravity(int contentGravity) {
        mContentGravity = contentGravity;
        return (T) this;
    }

    /** set content textcolor*/
    public T contentTextColor(int contentTextColor) {
        mContentTextColor = contentTextColor;
        return (T) this;
    }

    /** set content textsize*/
    public T contentTextSize(float contentTextSize_SP) {
        mContentTextSize = contentTextSize_SP;
        return (T) this;
    }

    /**
     * set btn text
     * btnTexts size 1, middle
     * btnTexts size 2, left right
     * btnTexts size 3, left right middle
     */
    public T btnNum(int btnNum) {
        if (btnNum < 1 || btnNum > 3) {
            throw new IllegalStateException("btnNum is [1,3]!");
        }
        mBtnNum = btnNum;

        return (T) this;
    }

    /**
     * set btn text(
     * btnTexts size 1, middle
     * btnTexts size 2, left right
     * btnTexts size 3, left right middle
     */
    public T btnText(String... btnTexts) {
        if (btnTexts.length < 1 || btnTexts.length > 3) {
            throw new IllegalStateException(" range of param btnTexts length is [1,3]!");
        }

        if (btnTexts.length == 1) {
            mBtnMiddleText = btnTexts[0];
        } else if (btnTexts.length == 2) {
            mBtnLeftText = btnTexts[0];
            mBtnRightText = btnTexts[1];
        } else if (btnTexts.length == 3) {
            mBtnLeftText = btnTexts[0];
            mBtnRightText = btnTexts[1];
            mBtnMiddleText = btnTexts[2];
        }

        return (T) this;
    }

    /**
     * set btn textcolor
     * btnTextColors size 1, middle
     * btnTextColors size 2, left right
     * btnTextColors size 3, left right middle
     */
    public T btnTextColor(int... btnTextColors) {
        if (btnTextColors.length < 1 || btnTextColors.length > 3) {
            throw new IllegalStateException(" range of param textColors length is [1,3]!");
        }

        if (btnTextColors.length == 1) {
            mMiddleBtnTextColor = btnTextColors[0];
        } else if (btnTextColors.length == 2) {
            mLeftBtnTextColor = btnTextColors[0];
            mRightBtnTextColor = btnTextColors[1];
        } else if (btnTextColors.length == 3) {
            mLeftBtnTextColor = btnTextColors[0];
            mRightBtnTextColor = btnTextColors[1];
            mMiddleBtnTextColor = btnTextColors[2];
        }

        return (T) this;
    }

    /**
     * set btn textsize
     * btnTextSizes size 1, middle
     * btnTextSizes size 2, left right
     * btnTextSizes size 3, left right middle
     */
    public T btnTextSize(float... btnTextSizes) {
        if (btnTextSizes.length < 1 || btnTextSizes.length > 3) {
            throw new IllegalStateException(" range of param btnTextSizes length is [1,3]!");
        }

        if (btnTextSizes.length == 1) {
            mMiddleBtnTextSize = btnTextSizes[0];
        } else if (btnTextSizes.length == 2) {
            mLeftBtnTextSize = btnTextSizes[0];
            mRightBtnTextSize = btnTextSizes[1];
        } else if (btnTextSizes.length == 3) {
            mLeftBtnTextSize = btnTextSizes[0];
            mRightBtnTextSize = btnTextSizes[1];
            mMiddleBtnTextSize = btnTextSizes[2];
        }

        return (T) this;
    }

    /** set btn press color*/
    public T btnPressColor(int btnPressColor) {
        mBtnPressColor = btnPressColor;
        return (T) this;
    }

    /** set corner radius */
    public T cornerRadius(float cornerRadius_DP) {
        mCornerRadius = cornerRadius_DP;
        return (T) this;
    }

    /** set backgroud color*/
    public T bgColor(int bgColor) {
        mBgColor = bgColor;
        return (T) this;
    }

    /**
     * set btn click listener
     * onDialogBtnClickLs size 1, middle
     * onDialogBtnClickLs size 2, left right
     * onDialogBtnClickLs size 3, left right middle
     */
    public void setOnBtnClickL(OnDialogBtnClick... onDialogBtnClickLs) {
        if (onDialogBtnClickLs.length < 1 || onDialogBtnClickLs.length > 3) {
            throw new IllegalStateException(" range of param onDialogBtnClickLs length is [1,3]!");
        }

        if (onDialogBtnClickLs.length == 1) {
            mOnBtnMiddleClickL = onDialogBtnClickLs[0];
        } else if (onDialogBtnClickLs.length == 2) {
            mOnBtnLeftClickL = onDialogBtnClickLs[0];
            mOnBtnRightClickL = onDialogBtnClickLs[1];
        } else if (onDialogBtnClickLs.length == 3) {
            mOnBtnLeftClickL = onDialogBtnClickLs[0];
            mOnBtnRightClickL = onDialogBtnClickLs[1];
            mOnBtnMiddleClickL = onDialogBtnClickLs[2];
        }
    }
}
