package com.example.xing.linearlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int clickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickConfirm(View view){

        Button button = (Button) findViewById(R.id.button);

        String newText = "已点击 " + ++clickTime + " 次";
        button.setText(newText);
    }
}
