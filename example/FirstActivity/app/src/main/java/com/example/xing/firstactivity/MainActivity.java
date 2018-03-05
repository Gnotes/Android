package com.example.xing.firstactivity;

import android.content.Intent;
import android.net.Uri;
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
                // Intent对象有过个构造
                // 创建Intent对象，第一个参数是启动活动的上下文环境，第二个参数指定想要启动的目标活动
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                // 通过startActivity启动活动
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent的另一个构造，通过指定AndroidManifest.xml中activity里边的intent-filter action属性，来匹配要启动的活动
                startActivity(new Intent("com.example.xing.firstactivity.ACTION_START"));
            }
        });

        Button button4 = (Button) findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.xing.firstactivity.ACTION_START");
                // 指定分类category,没有定义该category，点击会导致程序崩溃！
                intent.addCategory("com.example.xing.firstactivity.MY_CATEGORY");
                startActivity(intent);
            }
        });

        Button button5 = (Button) findViewById(R.id.button_5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.xing.firstactivity.ACTION_START");
                intent.addCategory("com.example.xing.firstactivity.SECOND_CATEGORY");
                startActivity(intent);
            }
        });

        Button button6 = (Button) findViewById(R.id.button_6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/"));
                startActivity(intent);
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
