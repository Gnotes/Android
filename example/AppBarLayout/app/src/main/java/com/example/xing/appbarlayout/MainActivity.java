package com.example.xing.appbarlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 参考
 *
 * https://www.jianshu.com/p/91a56cf5e865
 *
 * https://blog.csdn.net/mffandxx/article/details/69223021
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }
}
