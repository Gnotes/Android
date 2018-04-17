package com.example.xing.firstactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int RESULT_CODE = 1900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        /**
         * 通过getIntent()获取传递的参数
         */
        Intent paramIntent = getIntent();
        String name = paramIntent.getStringExtra("name");
        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();

        Button btnGoback = findViewById(R.id.btn_goback);
        btnGoback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_goback: handleClickGoBack(); break;
            default:
        }
    }

    private void handleClickGoBack(){
        Intent result = new Intent();
        result.putExtra("code",200);
        result.putExtra("message","success");
        setResult(RESULT_CODE,result);
        finish();
    }
}
