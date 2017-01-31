package com.example.dell.mini_project;

/**
 * Created by dell on 1/22/2017.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by sev_user on 1/20/2017.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    private List<Book> _listItems;
    private Context _context;
    public BookAdapter(Context context, int textViewResourceId,List<Book> objects) {
        super(context, textViewResourceId, objects);
        this._context   = context;
        this._listItems = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookView view = new BookView(this._context);
        view.setListItem(this._listItems.get(position));
        return view;
    }
}