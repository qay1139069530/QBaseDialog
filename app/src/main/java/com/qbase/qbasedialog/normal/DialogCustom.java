package com.qbase.qbasedialog.normal;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.qay.qbase.animationlibrary.Attention.QBaseSwing;
import com.qbase.dialog.R;
import com.qbase.dialog.QBaseDialog;
import com.qbase.dialog.util.QBaseDialogUtils;

public class DialogCustom extends QBaseDialog<DialogCustom> {
    TextView mTvCancel;
    TextView mTvExit;

    public DialogCustom(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        showAnim(new QBaseSwing());

        // dismissAnim(this, new QBaseZoomOutExit());
        View inflate = View.inflate(mContext, R.layout.dialog_custom_base, null);


        inflate.setBackgroundDrawable(
                QBaseDialogUtils.cornerDrawable(Color.parseColor("#ffffff"), dp2px(5)));

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        mTvCancel = (TextView)findViewById(R.id.tv_cancel);
        mTvExit = (TextView)findViewById(R.id.tv_exit);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
