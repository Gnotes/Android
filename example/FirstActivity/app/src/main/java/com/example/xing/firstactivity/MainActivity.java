package com.example.xing.firstactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Button button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"I'm a Button ,you just clicked me!",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 通过getMenuInflater获取MenuInflater对象，并通过inflate方法给当前activity创建菜单
        // 第一个参数指定通过哪个资源文件创建菜单，第二个参数指定菜单项将添加到哪个菜单对象中
        // 返回true表示将菜单显示
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String toastText = "";
        // 通过item.getItemId来判断点击的哪个菜单
        switch (item.getItemId()){
            case R.id.menu_add:toastText = "你点击了添加菜单";break;
            case R.id.menu_edit:toastText = "你点击了编辑菜单";break;
            default:toastText = "未知";
        }
        Toast.makeText(MainActivity.this,toastText,Toast.LENGTH_SHORT).show();
        return true;
    }
}
