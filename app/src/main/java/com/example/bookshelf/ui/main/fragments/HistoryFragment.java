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
import com.example.bookshelf.models.HistoryList;
import com.example.bookshelf.ui.main.adapters.BooksListAdapter;
import com.example.bookshelf.ui.main.view_models.HistoryViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryFragment extends ListFragment {

    private static HistoryFragment instance;
    private BooksListAdapter adapter;
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
        adapter = new BooksListAdapter(getActivity(), getContext(), data);
        setListAdapter(adapter);
        adapter.update(HistoryList.getInstance().getSet());
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
