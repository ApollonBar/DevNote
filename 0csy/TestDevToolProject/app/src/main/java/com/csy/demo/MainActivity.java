package com.csy.demo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.csy.common.base.BaseActivity;
import com.csy.demo.netlib.net.retrofit.activity.MainNetActivity;
import com.csy.demo.devtool.ui.statusbar.StatusBarActivity;
import com.csy.demo.ui.dialog.ProgressDialogActivity;
import com.csy.demo.ui.dialog.SweetAlertDialogActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.btStatusBarActivity)
    Button btStatusBarActivity;
    @BindView(R.id.btProgressDialogActivity)
    Button btProgressDialogActivity;
    @BindView(R.id.btSweetAlertDialogActivity)
    Button btSweetAlertDialogActivity;
    @BindView(R.id.btCountDownTimerActivity)
    Button btCountDownTimerActivity;
    @BindView(R.id.btNetDemo)
    Button btNetDemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setStatusBarColor();
//        StatusBarHelper helper = new StatusBarHelper(
//        /*Activity*/
//                this,
//                /**
//                 * level used in 4.4, below value can be set:
//                 * LEVEL_NONE (if you don't need tint StatusBar)
//                 * LEVEL_19_TRANSLUCENT (set TRANSLUCENT StatusBar and tint)
//                 */
//                StatusBarHelper.LEVEL_19_TRANSLUCENT,
//                /**
//                 * level used in 5.x, can be set:
//                 * LEVEL_NONE (if you don't need tint StatusBar)
//                 * LEVEL_21_NORMAL (use API in 5.x to tint StatusBar)
//                 * LEVEL_21_NORMAL_FULL (use API in 5.x to tint StatusBar and set full screen)
//                 * LEVEL_21_VIEW (use a View to tint StatusBar that like LEVEL_19_TRANSLUCENT)
//                 */
//                StatusBarHelper.LEVEL_21_NORMAL
//        );
//
//        // set background color
//        helper.setColor(getResources().getColor(R.color.colorPrimaryDark));
//        // or set background drawable
//        // helper.setDrawable(new ColorDrawable(Color.BLUE));

//        SweetAlertDialog alertDialog = showProgress("加载中...",true,false);
////        alertDialog.showCancelButton(true);
////        alertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
////            @Override
////            public void onClick(SweetAlertDialog sweetAlertDialog) {
////                showToast("取消对话框回调");
////            }
////        });
//        final CountDownTimer mCountDownTimer = new CountDownTimer(60*1000, 1000) {
//
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//                KLog.d("KLog","加载中...");
//            }
//
//            @Override
//            public void onFinish() {
//                cancelProgress();
//            }
//        }.start();
    }


    @Override
    public void goActivity(Context context, Class<?> cls) {
        super.goActivity(context, cls);
    }

    @OnClick({R.id.btStatusBarActivity, R.id.btProgressDialogActivity, R.id.btSweetAlertDialogActivity
            , R.id.btCountDownTimerActivity,R.id.btNetDemo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btStatusBarActivity:
                goActivity(MainActivity.this, StatusBarActivity.class);
                break;
            case R.id.btProgressDialogActivity:
                goActivity(MainActivity.this, ProgressDialogActivity.class);

                break;
            case R.id.btSweetAlertDialogActivity:
                goActivity(MainActivity.this, SweetAlertDialogActivity.class);
                break;
            case R.id.btCountDownTimerActivity:
                goActivity(MainActivity.this, CountDownTimerActivity.class);
                break;

            case R.id.btNetDemo:
                goActivity(MainActivity.this, MainNetActivity.class);
                break;


        }
    }

}
