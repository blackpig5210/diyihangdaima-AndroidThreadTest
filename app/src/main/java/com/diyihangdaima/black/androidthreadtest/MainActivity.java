package com.diyihangdaima.black.androidthreadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button changeText;
    private TextView text;
    public static final int UPDATE_TEXT = 1;


    private Handler handler = new Handler() {
        public void handleMessage(Message message){
            switch (message.what) {
                case UPDATE_TEXT:
                    //在这里进行UI操作
                    text.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();    
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);//将Message发送出去
                    }
                }).start();

        }
    }
}
