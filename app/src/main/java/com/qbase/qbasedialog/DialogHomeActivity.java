package com.qbase.qbasedialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.qay.qbase.animationlibrary.BounceEnter.QBaseBounceTopEnter;
import com.qay.qbase.animationlibrary.QBaseAnimatorSet;
import com.qay.qbase.animationlibrary.SlideExit.QBaseSlideBottomExit;
import com.qbase.dialog.bean.QBaseDialogMenuItem;
import com.qbase.dialog.listener.OnDialogBtnClick;
import com.qbase.dialog.listener.OnItemClick;
import com.qbase.qbasedialog.normal.DialogActionSheet;
import com.qbase.qbasedialog.normal.DialogCustom;
import com.qbase.qbasedialog.normal.DialogIOSTop;
import com.qbase.qbasedialog.normal.DialogMaterial;
import com.qbase.qbasedialog.normal.DialogNormal;
import com.qbase.qbasedialog.normal.DialogNormalList;
import com.qbase.qbasedialog.normal.DialogShare;
import com.qbase.qbasedialog.normal.DialogShareTop;

import java.util.ArrayList;

public class DialogHomeActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener {
    ExpandableListView mElv;
    private Context mContext = this;
    public static String[] mGroups = {"Default Inner Dialog", "Custom Dialog", "Default Inner Anim", "Custom Anim"};
    public static String[][] mChilds = {
            /**Default Inner Dialog*/
            {
                    "NormalDialogQQ Default(two btns)",                    //0
                    "NormalDialogQQ StyleTwo",                    //1
                    "NormalDialogQQ Custom Attr",                 //2
                    "NormalDialogQQ(one btn)",                 //3
                    "NormalDialogQQ(three btns)",                 //4
                    "MaterialDialogDefault Default(two btns)",  //5
                    "MaterialDialogDefault(one btn)",           //6
                    "MaterialDialogDefault(three btns)",        //7
                    "DialogNormalList",                         //8
                    "DialogNormalList Custom Attr",             //10
                    "DialogNormalList No Title",                //11
                    "DialogNormalList DataSource String[]",     //12
                    "DialogNormalList DataSource Adapter",      //13
                    "ActionSheetDialogQBaseQBottomBottomTop",                        //14
                    "ActionSheetDialogQBaseQBottomBottomTop NoTitle"                 //15
            },
            /**Custom Dialog*/
            {
                    "Custome Dialog extends QBaseDialog", "Custome Dialog extends QBaseBottomTopDialog", "Custome Dialog extends QBaseTopDialog"
            },
            /**Default Inner Animation*/
            {
                    "Show Anim", "Dismiss Anim"
            },
            /**Custom Anim*/
            {
                    "Custom Anim like ios taobao"
            }
    };
    private ArrayList<QBaseDialogMenuItem> mMenuItems = new ArrayList<>();
    private String[] mStringItems = {"收藏", "下载", "分享", "删除", "歌手", "专辑"};
    private QBaseAnimatorSet mBasIn;
    private QBaseAnimatorSet mBasOut;

    public void setBasIn(QBaseAnimatorSet bas_in) {
        this.mBasIn = bas_in;
    }

    public void setBasOut(QBaseAnimatorSet bas_out) {
        this.mBasOut = bas_out;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_act_home);

        mElv = (ExpandableListView)findViewById(R.id.elv);

        mMenuItems.add(new QBaseDialogMenuItem("收藏", R.mipmap.ic_launcher));
        mMenuItems.add(new QBaseDialogMenuItem("下载", R.mipmap.ic_launcher));
        mMenuItems.add(new QBaseDialogMenuItem("分享", R.mipmap.ic_launcher));
        mMenuItems.add(new QBaseDialogMenuItem("删除", R.mipmap.ic_launcher));
        mMenuItems.add(new QBaseDialogMenuItem("歌手", R.mipmap.ic_launcher));
        mMenuItems.add(new QBaseDialogMenuItem("专辑", R.mipmap.ic_launcher));

        mBasIn = new QBaseBounceTopEnter();
        mBasOut = new QBaseSlideBottomExit();


        View decorView = getWindow().getDecorView();
        DialogHomeAdapter adapter = new DialogHomeAdapter(mContext);
        mElv.setAdapter(adapter);
        // extend all group
        for (int i = 0; i < mGroups.length; i++) {
            mElv.expandGroup(i);
        }

        mElv.setOnChildClickListener(this);
        mElv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        /**Default Inner Dialog*/
        if (groupPosition == 0) {
            if (childPosition == 0) {
                NormalDialogStyleOne();
            } else if (childPosition == 1) {
                NormalDialogStyleTwo();
            } else if (childPosition == 2) {
                NormalDialogCustomAttr();
            } else if (childPosition == 3) {
                NormalDialogOneBtn();
            } else if (childPosition == 4) {
                NormalDialoThreeBtn();
            } else if (childPosition == 5) {
                MaterialDialogDefault();
            } else if (childPosition == 6) {
                MaterialDialogOneBtn();
            } else if (childPosition == 7) {
                MaterialDialogThreeBtns();
            } else if (childPosition == 8) {
                NormalListDialog();
            } else if (childPosition == 9) {
                NormalListDialogCustomAttr();
            } else if (childPosition == 10) {
                NormalListDialogNoTitle();
            } else if (childPosition == 11) {
                NormalListDialogStringArr();
            } else if (childPosition == 12) {
                NormalListDialogAdapter();
            } else if (childPosition == 13) {
                ActionSheetDialog();
            } else if (childPosition == 14) {
                ActionSheetDialogNoTitle();
            }
        }
        /**Custom Dialog*/
        if (groupPosition == 1) {
            if (childPosition == 0) {
                final DialogCustom dialog = new DialogCustom(mContext);
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);
            } else if (childPosition == 1) {
                final DialogShare dialog = new DialogShare(mContext, mElv);
                dialog.showAnim(mBasIn)//
                        .show();//
                // .show(0, 100);
            } else if (childPosition == 2) {
                final DialogShareTop dialog = new DialogShareTop(mContext);
                dialog.showAnim(mBasIn)//
                        .show();
                // .show(0, 100);
            }
        }
        /**Default Inner Anim*/
        if (groupPosition == 2) {
            if (childPosition == 0) {
                DiaogAnimChoose.showAnim(mContext);
            } else if (childPosition == 1) {
                DiaogAnimChoose.dissmissAnim(mContext);
            }
        }
        /**Custom Anim*/
        if (groupPosition == 3) {
            if (childPosition == 0) {
                final DialogIOSTop dialog = new DialogIOSTop(mContext, (View) mElv.getParent());
                dialog.show();
                // .show(0, 100);
            }
        }
        return false;
    }

    private void NormalDialogStyleOne() {
        final DialogNormal dialog = new DialogNormal(mContext);
        dialog.content("是否确定退出程序?")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnDialogBtnClick() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnDialogBtnClick() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                });
    }

    private void NormalDialogStyleTwo() {
        final DialogNormal dialog = new DialogNormal(mContext);
        dialog.content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。")//
                .style(DialogNormal.STYLE_TWO)//
                .titleTextSize(23)//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnDialogBtnClick() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnDialogBtnClick() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                });

    }

    private void NormalDialogCustomAttr() {
        final DialogNormal dialog = new DialogNormal(mContext);
        dialog.isTitleShow(false)//
                .bgColor(Color.parseColor("#383838"))//
                .cornerRadius(5)//
                .content("是否确定退出程序?")//
                .contentGravity(Gravity.CENTER)//
                .contentTextColor(Color.parseColor("#ffffff"))//
                .dividerColor(Color.parseColor("#222222"))//
                .btnTextSize(15.5f, 15.5f)//
                .btnTextColor(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"))//
                .btnPressColor(Color.parseColor("#2B2B2B"))//
                .widthScale(0.85f)//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnDialogBtnClick() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnDialogBtnClick() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                });
    }

    private void NormalDialogOneBtn() {
        final DialogNormal dialog = new DialogNormal(mContext);
        dialog.content("你今天的抢购名额已用完~")//
                .btnNum(1)
                .btnText("继续逛逛")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(new OnDialogBtnClick() {
            @Override
            public void onBtnClick() {
                dialog.dismiss();
            }
        });
    }

    private void NormalDialoThreeBtn() {
        final DialogNormal dialog = new DialogNormal(mContext);
        dialog.content("你今天的抢购名额已用完~")//
                .style(DialogNormal.STYLE_TWO)//
                .btnNum(3)
                .btnText("取消", "确定", "继续逛逛")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnDialogBtnClick() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnDialogBtnClick() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnDialogBtnClick() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                });
    }

    private void MaterialDialogDefault() {
        final DialogMaterial dialog = new DialogMaterial(mContext);
        dialog.content(
                "嗨！这是一个 MaterialDialogDefault. 它非常方便使用，你只需将它实例化，这个美观的对话框便会自动地显示出来。"
                        + "它简洁小巧，完全遵照 Google 2014 年发布的 Material Design 风格，希望你能喜欢它！^ ^")//
                .btnText("取消", "确定")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnDialogBtnClick() {//left btn click listener
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnDialogBtnClick() {//right btn click listener
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                }
        );
    }


    private void MaterialDialogThreeBtns() {
        final DialogMaterial dialog = new DialogMaterial(mContext);
        dialog.isTitleShow(false)//
                .btnNum(3)
                .content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。")//
                .btnText("确定", "取消", "知道了")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnDialogBtnClick() {//left btn click listener
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnDialogBtnClick() {//right btn click listener
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                }
                ,
                new OnDialogBtnClick() {//middle btn click listener
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                }
        );
    }

    private void MaterialDialogOneBtn() {
        final DialogMaterial dialog = new DialogMaterial(mContext);
        dialog//
                .btnNum(1)
                .content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。")//
                .btnText("确定")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(new OnDialogBtnClick() {
            @Override
            public void onBtnClick() {
                dialog.dismiss();
            }
        });
    }

    private void NormalListDialog() {
        final DialogNormalList dialog = new DialogNormalList(mContext, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnItemClick() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
            }
        });
    }


    private void NormalListDialogCustomAttr() {
        final DialogNormalList dialog = new DialogNormalList(mContext, mMenuItems);
        dialog.title("请选择")//
                .titleTextSize_SP(18)//
                .titleBgColor(Color.parseColor("#409ED7"))//
                .itemPressColor(Color.parseColor("#85D3EF"))//
                .itemTextColor(Color.parseColor("#303030"))//
                .itemTextSize(14)//
                .cornerRadius(0)//
                .widthScale(0.8f)//
                .show(R.style.qbase_dialog_anim);

        dialog.setOnOperItemClickL(new OnItemClick() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
            }
        });
    }


    private void NormalListDialogNoTitle() {
        final DialogNormalList dialog = new DialogNormalList(mContext, mMenuItems);
        dialog.title("请选择")//
                .isTitleShow(false)//
                .itemPressColor(Color.parseColor("#85D3EF"))//
                .itemTextColor(Color.parseColor("#303030"))//
                .itemTextSize(15)//
                .cornerRadius(2)//
                .widthScale(0.75f)//
                .show();

        dialog.setOnOperItemClickL(new OnItemClick() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
            }
        });
    }

    private void NormalListDialogStringArr() {
        final DialogNormalList dialog = new DialogNormalList(mContext, mStringItems);
        dialog.title("请选择")//
                .layoutAnimation(null)
                .show(R.style.qbase_dialog_anim);
        dialog.setOnOperItemClickL(new OnItemClick() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
            }
        });
    }

    private void NormalListDialogAdapter() {
        final DialogNormalList dialog = new DialogNormalList(mContext, new DialogTestAdapter(mContext, mMenuItems));
        dialog.title("请选择")//
                .show(R.style.qbase_dialog_anim);
        dialog.setOnOperItemClickL(new OnItemClick() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
            }
        });
    }


    private void ActionSheetDialog() {
        final String[] stringItems = {"接收消息并提醒", "接收消息但不提醒", "收进群助手且不提醒", "屏蔽群消息"};
        final DialogActionSheet dialog = new DialogActionSheet(mContext, stringItems, null);
        dialog.title("选择群消息提醒方式\r\n(该群在电脑的设置:接收消息并提醒)")//
                .titleTextSize_SP(14.5f)//
                .show();

        dialog.setOnItemClick(new OnItemClick() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
            }
        });
    }

    private void ActionSheetDialogNoTitle() {
        final String[] stringItems = {"版本更新", "帮助与反馈", "退出QQ"};
        final DialogActionSheet dialog = new DialogActionSheet(mContext, stringItems, mElv);
        dialog.isTitleShow(false).show();

        dialog.setOnItemClick(new OnItemClick() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        final DialogNormal dialog = new DialogNormal(mContext);
        dialog.content("亲,真的要走吗?再看会儿吧~(●—●)")//
                .style(DialogNormal.STYLE_TWO)//
                .titleTextSize(23)//
                .btnText("继续逛逛", "残忍退出")//
                .btnTextColor(Color.parseColor("#383838"), Color.parseColor("#D4D4D4"))//
                .btnTextSize(16f, 16f)//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnDialogBtnClick() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnDialogBtnClick() {
                    @Override
                    public void onBtnClick() {
                        dialog.superDismiss();
                        finish();
                    }
                });
    }
}