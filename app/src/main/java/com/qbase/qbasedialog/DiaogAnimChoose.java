package com.qbase.qbasedialog;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.qay.qbase.animationlibrary.Attention.QBaseFlash;
import com.qay.qbase.animationlibrary.Attention.QBaseRubberBand;
import com.qay.qbase.animationlibrary.Attention.QBaseShakeHorizontal;
import com.qay.qbase.animationlibrary.Attention.QBaseShakeVertical;
import com.qay.qbase.animationlibrary.Attention.QBaseSwing;
import com.qay.qbase.animationlibrary.Attention.QBaseTada;
import com.qay.qbase.animationlibrary.BounceEnter.QBaseBounceBottomEnter;
import com.qay.qbase.animationlibrary.BounceEnter.QBaseBounceEnter;
import com.qay.qbase.animationlibrary.BounceEnter.QBaseBounceLeftEnter;
import com.qay.qbase.animationlibrary.BounceEnter.QBaseBounceRightEnter;
import com.qay.qbase.animationlibrary.BounceEnter.QBaseBounceTopEnter;
import com.qay.qbase.animationlibrary.FadeEnter.QBaseFadeEnter;
import com.qay.qbase.animationlibrary.FadeExit.QBaseFadeExit;
import com.qay.qbase.animationlibrary.FallEnter.QBaseFallEnter;
import com.qay.qbase.animationlibrary.FallEnter.QBaseFallRotateEnter;
import com.qay.qbase.animationlibrary.FlipEnter.QBaseFlipBottomEnter;
import com.qay.qbase.animationlibrary.FlipEnter.QBaseFlipHorizontalEnter;
import com.qay.qbase.animationlibrary.FlipEnter.QBaseFlipHorizontalSwingEnter;
import com.qay.qbase.animationlibrary.FlipEnter.QBaseFlipLeftEnter;
import com.qay.qbase.animationlibrary.FlipEnter.QBaseFlipRightEnter;
import com.qay.qbase.animationlibrary.FlipEnter.QBaseFlipTopEnter;
import com.qay.qbase.animationlibrary.FlipEnter.QBaseFlipVerticalEnter;
import com.qay.qbase.animationlibrary.FlipEnter.QBaseFlipVerticalSwingEnter;
import com.qay.qbase.animationlibrary.FlipExit.QBaseFlipHorizontalExit;
import com.qay.qbase.animationlibrary.FlipExit.QBaseFlipVerticalExit;
import com.qay.qbase.animationlibrary.QBaseAnimatorSet;
import com.qay.qbase.animationlibrary.QBaseJelly;
import com.qay.qbase.animationlibrary.QBaseNewsPaperEnter;
import com.qay.qbase.animationlibrary.SlideEnter.QBaseSlideBottomEnter;
import com.qay.qbase.animationlibrary.SlideEnter.QBaseSlideLeftEnter;
import com.qay.qbase.animationlibrary.SlideEnter.QBaseSlideRightEnter;
import com.qay.qbase.animationlibrary.SlideEnter.QBaseSlideTopEnter;
import com.qay.qbase.animationlibrary.SlideExit.QBaseSlideBottomExit;
import com.qay.qbase.animationlibrary.SlideExit.QBaseSlideLeftExit;
import com.qay.qbase.animationlibrary.SlideExit.QBaseSlideRightExit;
import com.qay.qbase.animationlibrary.SlideExit.QBaseSlideTopExit;
import com.qay.qbase.animationlibrary.ZoomEnter.QBaseZoomInBottomEnter;
import com.qay.qbase.animationlibrary.ZoomEnter.QBaseZoomInEnter;
import com.qay.qbase.animationlibrary.ZoomEnter.QBaseZoomInLeftEnter;
import com.qay.qbase.animationlibrary.ZoomEnter.QBaseZoomInRightEnter;
import com.qay.qbase.animationlibrary.ZoomEnter.QBaseZoomInTopEnter;
import com.qay.qbase.animationlibrary.ZoomExit.QBaseZoomInExit;
import com.qay.qbase.animationlibrary.ZoomExit.QBaseZoomOutBottomExit;
import com.qay.qbase.animationlibrary.ZoomExit.QBaseZoomOutExit;
import com.qay.qbase.animationlibrary.ZoomExit.QBaseZoomOutLeftExit;
import com.qay.qbase.animationlibrary.ZoomExit.QBaseZoomOutRightExit;
import com.qay.qbase.animationlibrary.ZoomExit.QBaseZoomOutTopExit;
import com.qbase.dialog.listener.OnItemClick;
import com.qbase.qbasedialog.normal.DialogActionSheet;

import java.util.ArrayList;

public class DiaogAnimChoose {
    public static void showAnim(final Context context) {
        final Class<?> cs[] = {
                QBaseBounceEnter.class,//
                QBaseBounceTopEnter.class,//
                QBaseBounceBottomEnter.class,//
                QBaseBounceLeftEnter.class,//
                QBaseBounceRightEnter.class,//
                QBaseFlipHorizontalEnter.class,//
                QBaseFlipHorizontalSwingEnter.class,//
                QBaseFlipVerticalEnter.class,//
                QBaseFlipVerticalSwingEnter.class,//
                QBaseFlipTopEnter.class,//
                QBaseFlipBottomEnter.class,//
                QBaseFlipLeftEnter.class,//
                QBaseFlipRightEnter.class,//
                QBaseFadeEnter.class, //
                QBaseFallEnter.class,//
                QBaseFallRotateEnter.class,//
                QBaseSlideTopEnter.class,//
                QBaseSlideBottomEnter.class,//
                QBaseSlideLeftEnter.class, //
                QBaseSlideRightEnter.class,//
                QBaseZoomInEnter.class,//
                QBaseZoomInTopEnter.class,//
                QBaseZoomInBottomEnter.class,//
                QBaseZoomInLeftEnter.class,//
                QBaseZoomInRightEnter.class,//

                QBaseNewsPaperEnter.class,//
                QBaseFlash.class,//
                QBaseShakeHorizontal.class,//
                QBaseShakeVertical.class,//
                QBaseJelly.class,//
                QBaseRubberBand.class,//
                QBaseSwing.class,//
                QBaseTada.class,//
        };

        ArrayList<String> itemList = new ArrayList<>();
        for (Class<?> c : cs) {
            itemList.add(c.getSimpleName());
        }

        final String[] contents = new String[itemList.size()];
        final DialogActionSheet dialog = new DialogActionSheet(context, //
                itemList.toArray(contents), null);
        dialog.title("使用内置show动画设置对话框显示动画\r\n指定对话框将显示效果")//
                .titleTextSize_SP(14.5f)//
                .layoutAnimation(null)//
                .cancelText("cancel")
                .show();
        dialog.setCanceledOnTouchOutside(false);

        dialog.setOnItemClick(new OnItemClick() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String animType = contents[position];
                    ((DialogHomeActivity) context).setBasIn((QBaseAnimatorSet) cs[position].newInstance());
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void dissmissAnim(final Context context) {
        final Class<?> cs[] = {QBaseFlipHorizontalExit.class,//
                QBaseFlipVerticalExit.class,//
                QBaseFadeExit.class,//
                QBaseSlideTopExit.class,//
                QBaseSlideBottomExit.class,//
                QBaseSlideLeftExit.class, //
                QBaseSlideRightExit.class,//
                QBaseZoomOutExit.class,//
                QBaseZoomOutTopExit.class,//
                QBaseZoomOutBottomExit.class,//
                QBaseZoomOutLeftExit.class,//
                QBaseZoomOutRightExit.class,//
                QBaseZoomInExit.class,//
        };

        ArrayList<String> itemList = new ArrayList<String>();
        for (Class<?> c : cs) {
            itemList.add(c.getSimpleName());
        }

        final String[] contents = new String[itemList.size()];
        final DialogActionSheet dialog = new DialogActionSheet(context, //
                itemList.toArray(contents), null);
        dialog.title("使用内置dismiss动画设置对话框消失动画\r\n指定对话框将消失效果")//
                .titleTextSize_SP(14.5f)//
                .cancelText("cancel")
                .show();

        dialog.setOnItemClick(new OnItemClick() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String animType = contents[position];
                    ((DialogHomeActivity) context).setBasOut((QBaseAnimatorSet) cs[position].newInstance());
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
