package com.example.bookshelf.ui.main.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.BookmarkedBookList;
import com.example.bookshelf.models.NewBooksGetter;
import com.example.bookshelf.ui.main.adapters.NewBooksListAdapter;
import com.example.bookshelf.ui.main.view_models.BookListViewModel;
import com.example.bookshelf.ui.main.view_models.BookmarkedBookViewModel;
import com.example.bookshelf.ui.main.view_models.NewBookViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BookmarkFragment extends ListFragment {
    private static final String TAG = BookmarkFragment.class.getName();
    private static BookmarkFragment instance;
    private NewBooksListAdapter adapter;
    private BookmarkedBookViewModel bookmarkedBookViewModel;
    private static List<Book> data;
    private BookmarkedBookList bookmarkedBookList;

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
        View root = inflater.inflate(R.layout.fragment_new, container, false);
        adapter = new NewBooksListAdapter(getActivity(), getContext(), data);
        setListAdapter(adapter);
        NewBooksGetter.getInstance().getBooks(bookmarkedBookViewModel);
        return root;
    }

    public void updateBooks(List<Book> data) {
        if (adapter == null){
            Log.d(TAG, "ADAPTER is null");
        }
        adapter.updateBookmarked(data);
    }
}
