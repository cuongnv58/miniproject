package com.example.dell.mini_project;

/**
 * Created by dell on 1/22/2017.
 */

import android.app.LauncherActivity;
import android.app.Service;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sev_user on 1/20/2017.
 */

public class BookView extends LinearLayout {
    public ImageView anhsach;
    public TextView title;
    public TextView author;
    public TextView description;
    public TextView duration;

    public BookView(Context context){
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater)((MainActivity) context).getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.item_layout,this);
        this.anhsach = (ImageView)findViewById(R.id.list_image);
        this.title = (TextView)findViewById(R.id.title);
        this.author = (TextView)findViewById(R.id.singer);
        this.duration = (TextView)findViewById(R.id.duration);
    }

    public void setListItem(Book book){
        this.anhsach.setImageURI(Uri.parse(book.getIcon()));
        this.title.setText(book.getTitle());
        this.author.setText(book.getAuthor());
        this.duration.setText(book.getTheloai());
    }
}
