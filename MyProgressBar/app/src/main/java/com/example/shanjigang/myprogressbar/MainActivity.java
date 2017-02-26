package com.example.shanjigang.myprogressbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shanjigang.myprogressbar.view.RoundProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public native String getStringFromNative();

    TextView textView;
    RoundProgressBar progressbar;
    Button btRestart;
    int i = 0;
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (i < 101) {
                i++;
                progressbar.setProgress(i);
                progressbar.setTextSize(i);
            }


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_progressbar);
        initView();

    }


    private void initView() {
        progressbar = (RoundProgressBar) findViewById(R.id.roundProgressBar2);
        btRestart = (Button) findViewById(R.id.bt_restart);
        textView = (TextView) findViewById(R.id.ndk_test);

    }

    static {

        System.loadLibrary("myNativeLib");

    }

    @Override
    protected void onResume() {
        super.onResume();
        textView.setText(getStringFromNative());
        btRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 0;
            }
        });
        timer.schedule(timerTask, 0, 100);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }
}
