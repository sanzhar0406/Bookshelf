package com.example.bookshelf.ui.main.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.ui.main.adapters.SearchViewAdapter;
import com.example.bookshelf.ui.main.view_models.NewBookViewModel;
import com.example.bookshelf.ui.main.view_models.SearchListViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private static final String TAG = SearchFragment.class.getName();
    private RecyclerView recyclerView;
    private SearchViewAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private SearchListViewModel searchListViewModel;
    private EditText search_query;
    private Button submitButton;
    private static SearchFragment fragment;

    protected List<Book> data;

    public static SearchFragment newInstance() {
        fragment = new SearchFragment();
        return fragment;
    }

    public static SearchFragment getInstance() {
        if (fragment == null) {
            return null;
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchListViewModel = ViewModelProviders.of(this).get(SearchListViewModel.class);
        data = new ArrayList<>();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);
        manager = new LinearLayoutManager(getActivity());
        adapter = new SearchViewAdapter(data, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        search_query = root.findViewById(R.id.search_input);
        submitButton = root.findViewById(R.id.search_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = search_query.getText().toString();
                if (!query.isEmpty()){
                    adapter.clear();
                    searchListViewModel.getBooks(query);
                }
            }
        });
        return root;
    }

    public void updateBooks(List<Book> books){
        adapter.update(books);
    }
}
