package com.luo.day18sharedpreferencedemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputEt;
    private TextView outputTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEt = (EditText) findViewById(R.id.input_et);
        outputTv = (TextView) findViewById(R.id.output_tv);

        findViewById(R.id.save_btn).setOnClickListener(this);
        findViewById(R.id.read_btn).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read_btn:
                //读取保存的内容
                SharedPreferences spRead = getSharedPreferences("save",MODE_PRIVATE);
                //通过key获取保存的Value
                //第二个参数是 默认值，即拿不到的时候给什么
                String read = spRead.getString("name", " ");
                outputTv.setText(read);

                break;
            case R.id.save_btn:
                //把ET里的内容存起来
                //通过getSharedPreferences 来获得sp对象，它接收两个参数
                //参数一：文件名，参数二：模式
                //MODE_PRIVATE:只有自己的程序才能去访问
                SharedPreferences sp = getSharedPreferences("save", MODE_PRIVATE);
                //要想对该文件进行操作，就需要拿到它的Editor对象
                SharedPreferences.Editor editor = sp.edit();
                //通过调用editor的put方法，向文件中写入数据，以key—value的形式写入数据
                editor.putString("name", inputEt.getText().toString());
                //保存
                editor.remove("name");
//                editor.clear();  一般用不到
                editor.commit();
                Toast.makeText(MainActivity.this, "保存", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
