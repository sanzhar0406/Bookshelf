package com.example.bookshelf.ui.main.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.BookmarkedBookList;
import com.example.bookshelf.models.HistoryList;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.List;

public class BooksListAdapter extends BaseAdapter {

    private final Context context;
    private final Activity activity;
    private final List<Book> data;
    private LayoutInflater inflater;

    public BooksListAdapter(Activity activity, Context context, List<Book> data){
        this.context = context;
        this.activity = activity;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public int getCount() {
        try {
            int size = data.size();
            return size;
        } catch(NullPointerException ex) {
            return 0;
        }
    }

    @Override
    public Book getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Updating row of list view with values
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.book_list_item, parent, false);
        TextView titleTextView = rowView.findViewById(R.id.title);
        TextView subTitleTextView = rowView.findViewById(R.id.subtitle);
        TextView priceTextView = rowView.findViewById(R.id.price);
        TextView urlTextView = rowView.findViewById(R.id.url);
        TextView isbn13TextView = rowView.findViewById(R.id.isbn);

        ImageView imageView = rowView.findViewById(R.id.image);

        titleTextView.setText(data.get(position).getTitle());
        subTitleTextView.setText(data.get(position).getSubTitle());
        priceTextView.setText("Price : " + data.get(position).getPrice());
        urlTextView.setText(data.get(position).getUrl());
        isbn13TextView.setText(data.get(position).getisbn13());

        // Using Picasso library for memory efficient and fast upload of the image and putting into imageview
        Picasso.get().load(data.get(position).getImage()).resize(50,50).into(imageView);
        return rowView;
    }

    public void update(List<Book> data){
        this.data.clear();
        this.data.addAll(data);
        this.notifyDataSetChanged();
    }

}
