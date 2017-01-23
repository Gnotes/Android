# Content Provider

_A content provider component supplies data from one application to others on request. Such requests are handled by the methods of the ContentResolver class. A content provider can use different ways to store its data and the data can be stored in a database, in files, or even over a network_

Content Provider内容提供者 : 主要用于在不同的应用程序之间实现数据共享

当应用继承ContentProvider类，并重写该类用于提供数据和存储数据的方法，就可以向其他应用共享其数据。虽然使用其他方法也可以对外共享数据，但数据访问方式会因数据存储的方式而不同，如：采用文件方式对外共享数据，需要进行文件操作读写数据；采用sharedpreferences共享数据，需要使用sharedpreferences API读写数据。而使用ContentProvider共享数据的好处是统一了数据访问方式

ContentProvider可以理解为一个Android应用对外开放的接口，只要是符合它所定义的`Uri`格式的请求，均可以正常访问执行操作。其他的Android应用可以使用`ContentResolver`对象通过执行与`ContentProvider`中的`同名方法`请求执行，被执行的就是ContentProvider中的同名方法。所以`ContentProvider的方法`与`ContentResolver的方法`，是`一一对应`的.

## 特点

- 为存储和读取数据提供了统一的接口
- 应用程序可以实现数据共享
- android内置的许多数据都是使用ContentProvider形式，供开发者调用的(如视频，音频，图片，通讯录等)

### Uri

Uri代表了要操作的数据,是一种比较常见的资源访问方式,格式要求: `<prefix>://<authority>/<data_type>/<id>`   

- `prefix` : 固定为`content://`
- `authority` : 唯一标识这个ContentProvider的名称，外部调用者可以根据这个标识来找到它，如：`contacts` `browser`; 第三方提供者需要全名称，如:`com.tutorialspoint.statusprovider`
- `data_type` : 请求的数据类型
- `id` : 指定请求的特定(明确的具体的)数据，如访问联系人为5的用户信息：`content://contacts/people/5`

### Content Provider方法

<img src="./image/provider.jpg" width="200">

- boolean `onCreate()` : 创建时使用，用于初始化提供者
- Cursor `query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)` : 查询数据，返回一个数据Cursor对象。其中参数selection和selectionArgs是外部程序提供的查询条件
- Uri `insert`(Uri uri, ContentValues values) : 添加一条数据,参数values是需要插入的值，返回Uri对象
- int `update`(Uri uri, ContentValues values, String selection, String[] selectionArgs) : 更新数据返回影响数据条数
- int `delete`(Uri uri, String selection, String[] selectionArgs) : 删除数据，返回影响数据条数
- String `getType`(Uri uri) : 返回指定URI的MIME类型

除了onCreate()和getType()方法外，其他的均为CRUD操作，这些方法中，Uri参数为，与ContentProvider匹配的请求Uri

### 创建Content Provider

- 创建类继承`ContentProvider`
- 定义URI
- 覆写`onCreate()`并创建数据库`SQLite`
- 实现接口方法
- AndroidManifest.xml中通过`<provider>`注册提供者

#### 创建` com.example.MyApplication.MainActivity`

```java
package com.example.MyApplication;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;

import android.content.ContentValues;
import android.content.CursorLoader;

import android.database.Cursor;

import android.view.Menu;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
   }

   // 点击添加按钮时触发此函数，activity_main.xml文件中定义的
   public void onClickAddName(View view) {
      // Add a new student record
      ContentValues values = new ContentValues();
      // 设置值
      values.put(StudentsProvider.NAME,
         ((EditText)findViewById(R.id.editText2)).getText().toString());

      values.put(StudentsProvider.GRADE,
         ((EditText)findViewById(R.id.editText3)).getText().toString());
      // 添加数据
      Uri uri = getContentResolver().insert(
         StudentsProvider.CONTENT_URI, values);
      // 吐司消息
      Toast.makeText(getBaseContext(),
         uri.toString(), Toast.LENGTH_LONG).show();
   }

   // 点击搜索按钮执行此方法，activity_main.xml文件中定义的
   public void onClickRetrieveStudents(View view) {
      // Retrieve student records
      String URL = "content://com.example.MyApplication.StudentsProvider";

      Uri students = Uri.parse(URL);
      Cursor c = managedQuery(students, null, null, null, "name");

      if (c.moveToFirst()) {
         do{
            Toast.makeText(this,
               c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                  ", " +  c.getString(c.getColumnIndex( StudentsProvider.NAME)) +
                     ", " + c.getString(c.getColumnIndex( StudentsProvider.GRADE)),
            Toast.LENGTH_SHORT).show();
         } while (c.moveToNext());
      }
   }
}
```

#### 创建`com.example.MyApplication.StudentsProvider.java`

```java
package com.example.MyApplication;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;
import android.text.TextUtils;

public class StudentsProvider extends ContentProvider {
   // 定义常量
   static final String PROVIDER_NAME = "com.example.MyApplication.StudentsProvider";
   static final String URL = "content://" + PROVIDER_NAME + "/students";
   static final Uri CONTENT_URI = Uri.parse(URL);

   static final String _ID = "_id";
   static final String NAME = "name";
   static final String GRADE = "grade";

   private static HashMap<String, String> STUDENTS_PROJECTION_MAP;

   static final int STUDENTS = 1;
   static final int STUDENT_ID = 2;

   static final UriMatcher uriMatcher;

   static{
      uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
      uriMatcher.addURI(PROVIDER_NAME, "students", STUDENTS);
      uriMatcher.addURI(PROVIDER_NAME, "students/#", STUDENT_ID);
   }

   /**
   * 数据库常量
   */
   
   private SQLiteDatabase db;
   static final String DATABASE_NAME        = "College";
   static final String STUDENTS_TABLE_NAME  = "students";
   static final int DATABASE_VERSION        = 1;
   // 只能支持基本数据类型:varchar int long float boolean text blob clob
   static final String CREATE_DB_TABLE      = " CREATE TABLE " + STUDENTS_TABLE_NAME +
         " (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL,grade TEXT NOT NULL);";

   /**
    * DBHelper 操作数据库，内部类
   */
   
   private static class DatabaseHelper extends SQLiteOpenHelper {
      DatabaseHelper(Context context){
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
      }
      // 创建时初始化数据表
      @Override
      public void onCreate(SQLiteDatabase db) {
         db.execSQL(CREATE_DB_TABLE);
      }
      // 更新表
      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS " +  STUDENTS_TABLE_NAME);
         onCreate(db);
      }
   }

   // 创建提供者StudentProvider时，初始化DBHelper
   @Override
   public boolean onCreate() {
      Context context = getContext();
      DatabaseHelper dbHelper = new DatabaseHelper(context);

      /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
      */
         
      db = dbHelper.getWritableDatabase();
      return (db == null)? false:true;
   }

   // 添加方法
   @Override
   public Uri insert(Uri uri, ContentValues values) {
      /**
      * long型的数据？？添加成功行数，用于判断是否添加成功
      */
      long rowID = db.insert( STUDENTS_TABLE_NAME, "", values);

      /**
      * 判断是否添加成功 
      */
      if (rowID > 0) {
         Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
         getContext().getContentResolver().notifyChange(_uri, null);
         return _uri;
      }
      // 如果失败，抛出异常  
      throw new SQLException("Failed to add a record into " + uri);
   }

   @Override
   public Cursor query(Uri uri, String[] projection, 
      String selection,String[] selectionArgs, String sortOrder) {
      SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
      qb.setTables(STUDENTS_TABLE_NAME);

      switch (uriMatcher.match(uri)) {
         case STUDENTS:
            qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
         break;

         case STUDENT_ID:
            qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
         break;
         
         default:   
      }

      if (sortOrder == null || sortOrder == ""){
         /**
            * By default sort on student names
         */
         sortOrder = NAME;
      }
      
      Cursor c = qb.query(db, projection, selection, 
         selectionArgs,null, null, sortOrder);
      /**
         * register to watch a content URI for changes
      */
      c.setNotificationUri(getContext().getContentResolver(), uri);
      return c;
   }

   @Override
   public int delete(Uri uri, String selection, String[] selectionArgs) {
      int count = 0;
      switch (uriMatcher.match(uri)){
         case STUDENTS:
            count = db.delete(STUDENTS_TABLE_NAME, selection, selectionArgs);
         break;

         case STUDENT_ID:
            String id = uri.getPathSegments().get(1);
            count = db.delete( STUDENTS_TABLE_NAME, _ID +  " = " + id +
               (!TextUtils.isEmpty(selection) ? " 
               AND (" + selection + ')' : ""), selectionArgs);
            break;
         default:
            throw new IllegalArgumentException("Unknown URI " + uri);
      }

      getContext().getContentResolver().notifyChange(uri, null);
      return count;
   }

   @Override
   public int update(Uri uri, ContentValues values, 
      String selection, String[] selectionArgs) {
      int count = 0;
      switch (uriMatcher.match(uri)) {
         case STUDENTS:
            count = db.update(STUDENTS_TABLE_NAME, values, selection, selectionArgs);
         break;

         case STUDENT_ID:
            count = db.update(STUDENTS_TABLE_NAME, values, 
               _ID + " = " + uri.getPathSegments().get(1) +
               (!TextUtils.isEmpty(selection) ? " 
               AND (" +selection + ')' : ""), selectionArgs);
            break;
         default:
            throw new IllegalArgumentException("Unknown URI " + uri );
      }
        
      getContext().getContentResolver().notifyChange(uri, null);
      return count;
   }

   @Override
   public String getType(Uri uri) {
      switch (uriMatcher.match(uri)){
         /**
            * Get all student records
         */
         case STUDENTS:
            return "vnd.android.cursor.dir/vnd.example.students";
         /**
            * Get a particular student
         */
         case STUDENT_ID:
            return "vnd.android.cursor.item/vnd.example.students";
         default:
            throw new IllegalArgumentException("Unsupported URI: " + uri);
      }
   }
}
```

#### AndroidManifest.xml中注册提供者

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.MyApplication">

   <application
      android:allowBackup="true"
      android:icon="@mipmap/ic_launcher"
      android:label="@string/app_name"
      android:supportsRtl="true"
      android:theme="@style/AppTheme">
         <activity android:name=".MainActivity">
            <intent-filter>
               <action android:name="android.intent.action.MAIN" />
               <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
         </activity>
      <!-- 注册提供者 -->
      <provider android:name=".StudentsProvider"
         android:authorities="com.example.MyApplication.StudentsProvider"/>
   </application>
</manifest>
```

#### res/layout/activity_main.xml 主Activity布局文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
   xmlns:tools="http://schemas.android.com/tools"
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:paddingBottom="@dimen/activity_vertical_margin"
   android:paddingLeft="@dimen/activity_horizontal_margin"
   android:paddingRight="@dimen/activity_horizontal_margin"
   android:paddingTop="@dimen/activity_vertical_margin"
   tools:context="com.example.MyApplication.MainActivity">

   <TextView
      android:id="@+id/textView1"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="Content provider"
      android:layout_alignParentTop="true"
      android:layout_centerHorizontal="true"
      android:textSize="30dp" />

   <TextView
      android:id="@+id/textView2"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="Tutorials point "
      android:textColor="#ff87ff09"
      android:textSize="30dp"
      android:layout_below="@+id/textView1"
      android:layout_centerHorizontal="true" />

   <ImageButton
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:id="@+id/imageButton"
      android:src="@drawable/abc"
      android:layout_below="@+id/textView2"
      android:layout_centerHorizontal="true" />
   <!-- 添加按钮 -->
   <Button
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:id="@+id/button2"
      android:text="Add Name"
      android:layout_below="@+id/editText3"
      android:layout_alignRight="@+id/textView2"
      android:layout_alignEnd="@+id/textView2"
      android:layout_alignLeft="@+id/textView2"
      android:layout_alignStart="@+id/textView2"
      android:onClick="onClickAddName"/>

   <EditText
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:id="@+id/editText"
      android:layout_below="@+id/imageButton"
      android:layout_alignRight="@+id/imageButton"
      android:layout_alignEnd="@+id/imageButton" />

   <EditText
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:id="@+id/editText2"
      android:layout_alignTop="@+id/editText"
      android:layout_alignLeft="@+id/textView1"
      android:layout_alignStart="@+id/textView1"
      android:layout_alignRight="@+id/textView1"
      android:layout_alignEnd="@+id/textView1"
      android:hint="Name"
      android:textColorHint="@android:color/holo_blue_light" />

   <EditText
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:id="@+id/editText3"
      android:layout_below="@+id/editText"
      android:layout_alignLeft="@+id/editText2"
      android:layout_alignStart="@+id/editText2"
      android:layout_alignRight="@+id/editText2"
      android:layout_alignEnd="@+id/editText2"
      android:hint="Grade"
      android:textColorHint="@android:color/holo_blue_bright" />

   <Button
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="Retrive student"
      android:id="@+id/button"
      android:layout_below="@+id/button2"
      android:layout_alignRight="@+id/editText3"
      android:layout_alignEnd="@+id/editText3"
      android:layout_alignLeft="@+id/button2"
      android:layout_alignStart="@+id/button2"
      android:onClick="onClickRetrieveStudents"/>
</RelativeLayout>
```

#### res/values/strings.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">My Application</string>
</resources>;
```

### UriMatcher

在ContentProvider的CRUD操作，均会传递一个Uri对象，通过这个对象来匹配对应的请求。那么如何确定一个Uri执行哪项操作呢？需要用到一个UriMatcher对象，这个对象用来帮助内容提供者匹配Uri。它所提供的方法非常简单，仅有两个：     

- void `addURI`(String authority,String path,int code) : 添加一个Uri匹配项，authority为AndroidManifest.xml中注册的ContentProvider中的authority属性；path为一个路径，可以设置通配符，`#表示任意数字`，`*表示任意字符`；code为自定义的一个Uri代码
- int `match`(Uri uri) : 匹配传递的Uri，返回addURI()传递的code参数
