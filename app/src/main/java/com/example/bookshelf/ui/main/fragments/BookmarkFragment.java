package com.example.bookshelf.ui.main.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.BookmarkedBookList;
import com.example.bookshelf.models.BooksGetter;
import com.example.bookshelf.ui.main.adapters.BooksListAdapter;
import com.example.bookshelf.ui.main.view_models.BookmarkedBookViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookmarkFragment extends ListFragment {
    private static final String TAG = BookmarkFragment.class.getName();
    private static BookmarkFragment instance;
    private BooksListAdapter adapter;
    private BookmarkedBookViewModel bookmarkedBookViewModel;
    private static List<Book> data;

    public static BookmarkFragment getInstance(){
        if (instance  == null) {
            return null;
        }
        return instance;
    }
    public static BookmarkFragment newInstance() {
        instance = new BookmarkFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookmarkedBookViewModel = ViewModelProviders.of(this).get(BookmarkedBookViewModel.class);
        data = new ArrayList<>();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        adapter = new BooksListAdapter(getActivity(), getContext(), data);
        setListAdapter(adapter);
        adapter.update(BookmarkedBookList.getInstance().getBookmarkedSet());
        return root;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        DetailBookFragment detailBookFragment = DetailBookFragment.newInstance(data.get(position), data.get(position).getisbn13());
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, detailBookFragment, DetailBookFragment.TAG) //((ViewGroup)getView().getParent()).getId()
                .addToBackStack(null)
                .commit();
    }
}
