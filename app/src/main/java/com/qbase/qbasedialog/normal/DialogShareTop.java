package com.qbase.qbasedialog.normal;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.qay.qbase.animationlibrary.FlipEnter.QBaseFlipVerticalSwingEnter;
import com.qbase.dialog.QBaseTopDialog;
import com.qbase.qbasedialog.R;

public class DialogShareTop extends QBaseTopDialog<DialogShareTop> {
    LinearLayout mLlWechatFriendCircle;
    LinearLayout mLlWechatFriend;
    LinearLayout mLlQq;
    LinearLayout mLlSms;

    public DialogShareTop(Context context, View animateView) {
        super(context, animateView);
    }

    public DialogShareTop(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        showAnim(new QBaseFlipVerticalSwingEnter());
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_share, null);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        mLlWechatFriendCircle = (LinearLayout) findViewById(R.id.ll_wechat_friend_circle);
        mLlWechatFriend = (LinearLayout) findViewById(R.id.ll_wechat_friend);
        mLlQq = (LinearLayout) findViewById(R.id.ll_qq);
        mLlSms = (LinearLayout) findViewById(R.id.ll_sms);
        mLlWechatFriendCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mLlWechatFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mLlQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mLlSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
