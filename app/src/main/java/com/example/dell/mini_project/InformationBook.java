package com.example.dell.mini_project;

/**
 * Created by dell on 1/31/2017.
 */
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationBook extends MainActivity {
    private TextView txtBookTitle;
    private TextView txtBookAuth;
    private TextView txtBookPublish;
    private TextView txtBookType;
    private TextView txtBookDescription;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_book);
        txtBookTitle = (TextView) findViewById(R.id.txt_book_title);
        txtBookAuth = (TextView) findViewById(R.id.txt_book_auth);
        txtBookPublish = (TextView) findViewById(R.id.txt_book_publish);
        txtBookType = (TextView) findViewById(R.id.txt_book_type);
        txtBookDescription = (TextView) findViewById(R.id.txt_book_description);
        imageView = (ImageView) findViewById(R.id.cover);
        //set Text cho cac TextView cua layout
        txtBookAuth.setText(MainActivity.display_author);
        txtBookTitle.setText(MainActivity.display_title);
        txtBookPublish.setText(MainActivity.display_publish);
        txtBookType.setText(MainActivity.display_theloai);
        txtBookDescription.setText(MainActivity.display_description);
        Uri uri = null;
        uri.parse(display_icon);
        if(uri !=null)
            imageView.setImageURI(uri);
        else
            imageView.setImageResource(R.drawable.tuanhung);
    }
}