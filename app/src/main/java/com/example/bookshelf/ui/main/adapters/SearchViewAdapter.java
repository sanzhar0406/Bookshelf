package com.example.bookshelf.ui.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.ui.main.fragments.DetailBookFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.ViewHolder> {
    private static final String TAG = SearchViewAdapter.class.getName();
    private FragmentActivity activity;
    private List<Book> data;

    // Updating the list, using notifyItemRangeInserted instead of notifyDataSetChanged for efficiency
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

    // Binds new row to list
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.titleTextView.setText(data.get(i).getTitle());
        viewHolder.subTitleTextView.setText(data.get(i).getSubTitle());
        viewHolder.priceTextView.setText(data.get(i).getPrice());
        viewHolder.urlTextView.setText(data.get(i).getUrl());
        viewHolder.isbn13TextView.setText(data.get(i).getisbn13());
        final int finalI = i;
        viewHolder.bookRowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailBookFragment detailBookFragment = DetailBookFragment.newInstance(data.get(finalI), data.get(finalI).getisbn13());
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(android.R.id.content, detailBookFragment, DetailBookFragment.TAG) //((ViewGroup)getView().getParent()).getId()
                        .addToBackStack(null)
                        .commit();
            }
        });
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

    // Custom View Holder for recycler view
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ImageView bookImage;
        public TextView subTitleTextView;
        public TextView priceTextView;
        public TextView urlTextView;
        public TextView isbn13TextView;
        public RelativeLayout bookRowLayout;

        public ViewHolder(View root) {
            super(root);
            titleTextView = root.findViewById(R.id.title);
            subTitleTextView = root.findViewById(R.id.subtitle);
            priceTextView = root.findViewById(R.id.price);
            urlTextView = root.findViewById(R.id.url);
            isbn13TextView = root.findViewById(R.id.isbn);
            bookRowLayout = root.findViewById(R.id.book_row);
            bookImage = root.findViewById(R.id.image);
        }
    }

    public SearchViewAdapter(List<Book> data, FragmentActivity activity){
        this.activity = activity;
        this.data = data;
    }


}
