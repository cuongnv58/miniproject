package com.example.dell.mini_project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    public static final String TABLE = "mybooklist4";
    public static final String DATABASE = "book4.db";
    public static String display_id, display_title, display_author, display_theloai, display_publish, display_description, display_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //load tab Host
        loadTab();
        loadListBook();
    }

    public void loadTab(){
        final TabHost tab = (TabHost)findViewById(android.R.id.tabhost);
        //goi lenh setup
        tab.setup();

        //tao tab 1
        TabHost.TabSpec spec ;
        spec = tab.newTabSpec("t1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Books List");
        tab.addTab(spec);

        //tao tab 2
        spec = tab.newTabSpec("t2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Alarm");
        tab.addTab(spec);
        //thiet lap tab ban dau duoc chon mac dinh la tab0
        tab.setCurrentTab(0);
        //sự kiện change tab này để các bạn tùy xử lý
        //Ví dụ tab1 chưa nhập thông tin xong mà lại qua tab 2 thì báo...

        /*
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                String str = "Tab tag =" + s + "; index =" + tab.getCurrentTabTag().toString();
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
        */

    }

    public void loadListBook(){
        ArrayList<Book> celebrities = new ArrayList<>();
        ArrayList<Book> list = new ArrayList<Book>();
        //(id TEXT, tittle TEXT, author TEXT ,duration TEXT ,description TEXT ,icon INT)
        String[] column = {"id","tittle","author","theloai","publish","description","icon"};
        taoCSDL();

        Cursor c = database.query(TABLE, column, null, null,
                null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Book item = new Book();
            item.setId(c.getString(0));
            item.setTitle(c.getString(1));
            item.setAuthor(c.getString(2));
            item.setTheloai(c.getString(3));
            item.setPublish(c.getString(4));
            item.setDescription(c.getString(5));
            item.setIcon(c.getString(6));
            //Toast.makeText(this, c.getString(4),Toast.LENGTH_LONG);
            celebrities.add(item);
            //list = celebrities;
            c.moveToNext();
        }
        //lấy về ListView đã đặt trên activity_lv1.xml
        final ListView listViewBook = (ListView) findViewById(R.id.lv1);

        //tạo một đối tượng adapter mới tạo
        BookAdapter newAdapter = new BookAdapter(this, listViewBook.getId(), celebrities);
        //sử dụng newAdapter cho listViewVietnamese
        listViewBook.setAdapter(newAdapter);

        final ArrayList<Book> finalList = celebrities;
        //final ArrayList<Book> finalList1 = list;
        listViewBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book item = finalList.get(position);
                display_id = item.getId();
                display_author = item.getAuthor();
                display_title = item.getTitle();
                display_description = item.getDescription();
                display_theloai = item.getTheloai();
                display_publish = item.getPublish();
                display_icon  = item.getIcon();

                Intent informationIntent = new Intent(MainActivity.this, InformationBook.class);
                startActivity(informationIntent);
            }
        });
    }

    //tao hoặc mở CSDL
    public void doCreateDB(){

        database = openOrCreateDatabase(DATABASE,MODE_PRIVATE,null);
    }
    //tạo bảng CSDL nếu nó chưa tồn tại
    public void doCreateTable(){
        String sql="";
        sql = "CREATE TABLE IF NOT EXISTS "+ TABLE+
                "(id TEXT, tittle TEXT, author TEXT ,theloai TEXT ,publish TEXT, description TEXT ,icon TEXT)";
        database.execSQL(sql);
    }

    public void taoCSDL(){
        doCreateDB();
        doCreateTable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent addIntent = new Intent(this, AddNewBook.class);
                startActivity(addIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}



