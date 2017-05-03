package com.qbase.qbasedialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.qay.qbase.animationlibrary.BounceEnter.QBaseBounceRightEnter;
import com.qay.qbase.animationlibrary.SlideEnter.QBaseSlideBottomEnter;
import com.qay.qbase.animationlibrary.SlideExit.QBaseSlideBottomExit;
import com.qay.qbase.animationlibrary.SlideExit.QBaseSlideLeftExit;
import com.qbase.qbasedialog.normal.DialogBubbleInterPopup;
import com.qbase.qbasedialog.normal.PopupCustomBubbleInter;

public class DialogBubblePopupActivity extends AppCompatActivity {
    TextView mTvTopLeft;
    TextView mTvTopRight;
    TextView mTvBottomLeft;
    TextView mTvBottomRight;
    TextView mTvCenter;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_act_bubble_popup);


        mTvTopLeft = (TextView) findViewById(R.id.tv_top_left);
        mTvTopRight = (TextView) findViewById(R.id.tv_top_right);
        mTvBottomLeft = (TextView) findViewById(R.id.tv_bottom_left);
        mTvBottomRight = (TextView) findViewById(R.id.tv_bottom_right);
        mTvCenter = (TextView) findViewById(R.id.tv_center);


        mTvTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate = View.inflate(mContext, R.layout.dialog_popup_bubble_text, null);
                TextView tv = (TextView) inflate.findViewById(R.id.tv_bubble);
                DialogBubbleInterPopup bubblePopup = new DialogBubbleInterPopup(mContext, inflate);
                tv.setText("最美的不是下雨天,是曾与你躲过雨的屋檐~");
                bubblePopup.anchorView(mTvTopLeft)
                        .gravity(Gravity.BOTTOM)
                        .show();

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        });
        mTvTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupCustomBubbleInter customBubblePopup = new PopupCustomBubbleInter(mContext);
//        customBubblePopup.setCanceledOnTouchOutside(false);
                customBubblePopup
                        .gravity(Gravity.BOTTOM)
                        .anchorView(mTvTopRight)
                        .triangleWidth(20)
                        .triangleHeight(10)
                        .showAnim(null)
                        .dismissAnim(null)
                        .show();
            }
        });
        mTvBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate = View.inflate(mContext, R.layout.dialog_popup_bubble_text, null);
                new DialogBubbleInterPopup(mContext, inflate)
                        .anchorView(mTvBottomLeft)
                        .showAnim(null)
                        .dismissAnim(null)
                        .show();
            }
        });
        mTvBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate = View.inflate(mContext, R.layout.dialog_popup_bubble_image, null);
                new DialogBubbleInterPopup(mContext, inflate).anchorView(mTvBottomRight)
                        .bubbleColor(Color.parseColor("#8BC34A"))
                        .showAnim(new QBaseSlideBottomEnter())
                        .dismissAnim(new QBaseSlideBottomExit())
                        .show();
            }
        });
        mTvCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate = View.inflate(mContext, R.layout.dialog_popup_bubble_image, null);
                DialogBubbleInterPopup bubblePopup = new DialogBubbleInterPopup(mContext, inflate);
                bubblePopup.anchorView(mTvCenter)
                        .showAnim(new QBaseBounceRightEnter())
                        .dismissAnim(new QBaseSlideLeftExit())
                        .autoDismiss(true)
                        .show();
            }
        });
    }
}
