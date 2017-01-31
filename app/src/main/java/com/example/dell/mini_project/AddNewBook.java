package com.example.dell.mini_project;

/**
 * Created by dell on 1/31/2017.
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddNewBook extends Activity implements View.OnClickListener{
    private ImageView imgBookCover;
    public static String title_add, author_add, publish_add, description_add, theloai_add, image_add;
    String temp;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_book);

        findViewById(R.id.btn_choose_picture).setOnClickListener(this);
        imgBookCover = (ImageView) findViewById(R.id.img_book_cover);
        findViewById(R.id.btn_yes).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }
    private EditText getEdtTheLoai(){
        return (EditText)findViewById(R.id.edt_book_type);
    }

    private EditText getEdtBookTitle(){
        return (EditText) findViewById(R.id.edt_book_title);
    }

    private EditText getEdtBookAuthor(){
        return (EditText) findViewById(R.id.edt_book_auth);
    }

    private EditText getEdtBookPublish(){
        return (EditText) findViewById(R.id.edt_book_publish);
    }

//    private EditText getEdtBookPrice(){
//        return (EditText) findViewById(R.id.edt_book_price);
//    }

    private EditText getDescription(){
        return (EditText) findViewById(R.id.edt_book_type);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_choose_picture:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 101);
                break;
            case R.id.btn_yes:
                taoCSDL();
                title_add = getEdtBookTitle().getText().toString();
                author_add = getEdtBookAuthor().getText().toString();
                publish_add = getEdtBookPublish().getText().toString();
                description_add = getDescription().getText().toString();
                theloai_add = getEdtTheLoai().getText().toString();
                image_add = "cccc";
                doInsert();
                Intent intent1 = new Intent(AddNewBook.this, MainActivity.class);
                startActivity(intent1);
                //finish();
                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                finish();
                //TODO implement
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) { // && data != null
            Uri uri = data.getData();
            imgBookCover.setImageURI(uri);
            temp = uri.toString();
            //image_add = str;
            Toast.makeText(this,temp,Toast.LENGTH_LONG).show();


        } else {
                Toast.makeText(this, "No Image is selected.", Toast.LENGTH_LONG).show();
        }

    }

    public void doCreateDB(){

        database = openOrCreateDatabase(MainActivity.DATABASE,MODE_PRIVATE,null);
    }
    //tạo bảng CSDL nếu nó chưa tồn tại
    public void doCreateTable(){
        String sql="";
        sql = "CREATE TABLE IF NOT EXISTS "+ MainActivity.TABLE+
                "(id TEXT, tittle TEXT, author TEXT ,theloai TEXT ,publish TEXT, description TEXT ,icon TEXT)";
        database.execSQL(sql);
    }


    public void taoCSDL(){
        doCreateDB();
        doCreateTable();
    }

    public void doInsert(){
        //int _icon = R.drawable.tuanhung;
        ContentValues value = new ContentValues();
        //value.put("id","1");
        value.put("tittle",title_add);
        value.put("author",author_add);
        value.put("theloai",theloai_add);
        value.put("publish",publish_add);
        value.put("description",description_add);
        value.put("icon",image_add);

        String msg ="";

        if(database.insert(MainActivity.TABLE, null, value)==-1){
            msg="Failed";
        }
        else{
            msg="Bạn đã thêm thành công !";
        }
        Toast.makeText(AddNewBook.this,msg,Toast.LENGTH_LONG).show();
    }
}