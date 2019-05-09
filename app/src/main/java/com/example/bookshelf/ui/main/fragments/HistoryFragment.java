package com.example.bookshelf.ui.main.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.BookmarkedBookList;
import com.example.bookshelf.models.NewBooksGetter;
import com.example.bookshelf.ui.main.adapters.NewBooksListAdapter;
import com.example.bookshelf.ui.main.view_models.BookmarkedBookViewModel;
import com.example.bookshelf.ui.main.view_models.HistoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends ListFragment {

    private static HistoryFragment instance;
    private NewBooksListAdapter adapter;
    private HistoryViewModel historyViewModel;
    private static List<Book> data;

    public static HistoryFragment getInstance(){
        if (instance  == null) {
            return null;
        }
        return instance;
    }
    public static HistoryFragment newInstance() {
        instance = new HistoryFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        data = new ArrayList<>();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        adapter = new NewBooksListAdapter(getActivity(), getContext(), data);
        setListAdapter(adapter);
        NewBooksGetter.getInstance().getBooks(historyViewModel);
        return root;
    }

    public void updateBooks(List<Book> data) {
        adapter.updateHistory(data);
    }
}
