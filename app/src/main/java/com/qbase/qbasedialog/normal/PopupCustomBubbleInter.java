package com.qbase.qbasedialog.normal;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qbase.qbasedialog.R;

public class PopupCustomBubbleInter extends QQBaseBubbleInterPopup<PopupCustomBubbleInter> {

    private ImageView mIvBubble;
    private TextView mTvBubble;

    public PopupCustomBubbleInter(Context context) {
        super(context);
    }

    @Override
    public View onCreateBubbleView() {
        View inflate = View.inflate(mContext, R.layout.dialog_popup_bubble_image, null);
        mTvBubble = (TextView) inflate.findViewById(R.id.tv_bubble);
        mIvBubble = (ImageView) inflate.findViewById(R.id.iv_bubble);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();

        mTvBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mIvBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
