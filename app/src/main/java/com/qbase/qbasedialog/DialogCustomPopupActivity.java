package com.qbase.qbasedialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.qay.qbase.animationlibrary.BounceEnter.QBaseBounceBottomEnter;
import com.qay.qbase.animationlibrary.BounceEnter.QBaseBounceTopEnter;
import com.qay.qbase.animationlibrary.SlideEnter.QBaseSlideTopEnter;
import com.qay.qbase.animationlibrary.SlideExit.QBaseSlideBottomExit;
import com.qay.qbase.animationlibrary.SlideExit.QBaseSlideTopExit;
import com.qbase.qbasedialog.normal.QBaseInternalPopup;

public class DialogCustomPopupActivity extends AppCompatActivity {
    TextView mTvTopLeft;
    TextView mTvTopRight;
    TextView mTvBottomLeft;
    TextView mTvBottomRight;
    TextView mTvCenter;
    private Context mContext = this;
    private SimpleCustomPop mQuickCustomPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_act_custom_popup);

        mTvTopLeft = (TextView) findViewById(R.id.tv_top_left);
        mTvTopRight = (TextView) findViewById(R.id.tv_top_right);
        mTvBottomLeft = (TextView) findViewById(R.id.tv_bottom_left);
        mTvBottomRight = (TextView) findViewById(R.id.tv_bottom_right);
        mTvCenter = (TextView) findViewById(R.id.tv_center);

        mQuickCustomPopup = new SimpleCustomPop(mContext);
//        mQuickCustomPopup.setCanceledOnTouchOutside(false);


        mTvTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuickCustomPopup
                        .anchorView(mTvTopLeft)
                        .gravity(Gravity.BOTTOM)
                        .offset(0, 0)
                        .showAnim(new QBaseBounceTopEnter())
                        .dismissAnim(new QBaseSlideTopExit())
                        .dimEnabled(false)
                        .show();
            }
        });
        mTvTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuickCustomPopup
                        .anchorView(mTvTopRight)
                        .offset(-10, 5)
                        .gravity(Gravity.BOTTOM)
                        .showAnim(new QBaseBounceTopEnter())
                        .dismissAnim(new QBaseSlideTopExit())
                        .dimEnabled(false)
                        .show();
            }
        });
        mTvBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuickCustomPopup
                        .anchorView(mTvBottomLeft)
                        .offset(10, -5)
                        .gravity(Gravity.TOP)
                        .showAnim(new QBaseBounceBottomEnter())
                        .dismissAnim(new QBaseSlideBottomExit())
                        .dimEnabled(true)
                        .show();
            }
        });
        mTvBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuickCustomPopup
                        .showAnim(null)
                        .dismissAnim(null)
                        .dimEnabled(true)
                        .anchorView(mTvBottomRight)
                        .offset(-10, -5)
                        .dimEnabled(false)
                        .gravity(Gravity.TOP)
                        .show();
            }
        });
        mTvCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuickCustomPopup
                        .alignCenter(true)
                        .anchorView(mTvCenter)
                        .gravity(Gravity.BOTTOM)
                        .showAnim(new QBaseSlideTopEnter())
                        .dismissAnim(new QBaseSlideTopExit())
                        .offset(0, 0)
                        .dimEnabled(false)
                        .show();
            }
        });
    }

    class SimpleCustomPop extends QBaseInternalPopup<SimpleCustomPop> {

        TextView mTvItem1;
        TextView mTvItem2;
        TextView mTvItem3;
        TextView mTvItem4;

        public SimpleCustomPop(Context context) {
            super(context);
        }

        @Override
        public View onCreatePopupView() {
            View inflate = View.inflate(mContext, R.layout.dialog_popup_custom, null);
            return inflate;
        }

        @Override
        public void setUiBeforShow() {

            mTvItem1 = (TextView) findViewById(R.id.tv_item_1);
            mTvItem2 = (TextView) findViewById(R.id.tv_item_2);
            mTvItem3 = (TextView) findViewById(R.id.tv_item_3);
            mTvItem4 = (TextView) findViewById(R.id.tv_item_4);


            mTvItem1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            mTvItem2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            mTvItem3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            mTvItem4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
