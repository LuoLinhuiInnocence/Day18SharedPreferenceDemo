package com.luo.day18sharedpreferencedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by dllo on 16/1/29.
 */
public class OtherActivity extends AppCompatActivity {

    private TextView timeTv;
    //用来记录是否是第一次启动的文件
    private SharedPreferences sp;
    private Handler otherHandler;
    private boolean isLife = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_item);
        findViewById(R.id.other_tv);
        timeTv = (TextView) findViewById(R.id.other_time_tv);
        sp = getSharedPreferences("welcome", MODE_PRIVATE);

        if (sp.getBoolean("isFirst", true)) {
            MyToast.myToast("第一次启动");
            //是第一次启动,开启线程，进行倒计时，倒计时结束时跳转
            // 向sp记录 将isFirst值记录为false
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFirst", false);
            editor.commit();

            otherHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    switch (msg.what){
                        case 1:
                        timeTv.setText(String.valueOf(msg.arg1));
                            break;
                        case 2:
                            jump();
                            break;


                    }
                    return false;
                }
            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //每隔一秒倒计时，结束跳转
                    for (int i = 5; i > 0; i--) {
                        //发送给主线程刷新UI
                        Message message = new Message();
                        message.arg1=i;
                        message.what=1;
                        otherHandler.sendMessage(message);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //跳转
//                    jump();
                    if (isLife){
                        otherHandler.sendEmptyMessage(2);
                    }


                }
            }).start();

        } else {
            MyToast.myToast("不是第一次");
            //不是第一次启动，直接跳转
            jump();

        }
    }

    //点击返回键不能返回一直吐丝
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK){
//            Toast.makeText(OtherActivity.this, "不能返回", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    private void jump(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        isLife = false;
        super.onDestroy();
    }
}
