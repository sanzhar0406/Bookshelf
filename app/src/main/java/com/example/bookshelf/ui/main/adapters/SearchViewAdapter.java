package com.example.bookshelf.ui.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.ViewHolder> {

    private List<Book> data;

    public void update(List<Book> data){
        int idx = this.data.size();
        this.data.addAll(idx, data);
        this.notifyItemRangeInserted(idx, data.size());
    }

    @NonNull
    @Override
    public SearchViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.book_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.titleTextView.setText(data.get(i).getTitle());
        viewHolder.subTitleTextView.setText(data.get(i).getSubTitle());
        viewHolder.priceTextView.setText(data.get(i).getPrice());
        viewHolder.urlTextView.setText(data.get(i).getUrl());
        viewHolder.isbn13TextView.setText(data.get(i).getisbn13());

        Picasso.get().load(data.get(i).getImage()).resize(50,50).into(viewHolder.bookImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void clear() {
        this.data.clear();
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ImageView bookImage;
        public TextView subTitleTextView;
        public TextView priceTextView;
        public TextView urlTextView;
        public TextView isbn13TextView;


        public ViewHolder(View root) {
            super(root);
            titleTextView = root.findViewById(R.id.title);
            subTitleTextView = root.findViewById(R.id.subtitle);
            priceTextView = root.findViewById(R.id.price);
            urlTextView = root.findViewById(R.id.url);
            isbn13TextView = root.findViewById(R.id.isbn);

            bookImage = root.findViewById(R.id.image);
        }
    }

    public SearchViewAdapter(List<Book> data){
        this.data = data;
    }


}
