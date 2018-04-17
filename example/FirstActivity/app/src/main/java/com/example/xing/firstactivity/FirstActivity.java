package com.example.xing.firstactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    private final int REQ_CODE = 10086;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Button button = findViewById(R.id.btn_first_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 显示Intent定义
                 * Intent lancherSecIntent = new Intent(FirstActivity.this,SecondActivity.class);
                 */

                /**
                 * 隐式Intent定义，通过action和category来过滤
                 */
                Intent lancherSecIntent = new Intent("com.example.xing.firstactivity.ACTION_START");
                lancherSecIntent.addCategory("com.example.xing.firstactivity.MY_CATEGORY");
                // 设置传递参数
                lancherSecIntent.putExtra("name","xing.he");
                /**
                 * startActivity(lancherSecIntent);
                 */
                startActivityForResult(lancherSecIntent,REQ_CODE);
            }
        });
        Button buttonFinsh = findViewById(R.id.btn_finish_activity);
        buttonFinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnOpenWebsite = findViewById(R.id.btn_open_website);
        btnOpenWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
                websiteIntent.setData(Uri.parse("https://github.com/xing-he"));
                startActivity(websiteIntent);
            }
        });

        Button btnCall = findViewById(R.id.btn_make_a_call);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:10086"));
                startActivity(callIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_item:Toast.makeText(FirstActivity.this,"添加",Toast.LENGTH_SHORT).show(); break;
            case R.id.menu_edit_item: Toast.makeText(FirstActivity.this,"编辑",Toast.LENGTH_SHORT).show(); break;
            default:break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQ_CODE:
                if(resultCode == SecondActivity.RESULT_CODE){
                    String dataStr = data.getStringExtra("message");
                    Toast.makeText(this,dataStr,Toast.LENGTH_SHORT).show();
                }
        }
    }
}
