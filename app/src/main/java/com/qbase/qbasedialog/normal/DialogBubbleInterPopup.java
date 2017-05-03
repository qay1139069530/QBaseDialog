package com.qbase.qbasedialog.normal;

import android.content.Context;
import android.view.View;

/**
 * 利用Dialog实现泡泡样式的弹窗
 */
public class DialogBubbleInterPopup extends QQBaseBubbleInterPopup<DialogBubbleInterPopup> {

    public DialogBubbleInterPopup(Context context, View wrappedView) {
        super(context, wrappedView);
    }

    @Override
    public View onCreateBubbleView() {
        return null;
    }
}
