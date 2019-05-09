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
import com.example.bookshelf.ui.main.adapters.BooksListAdapter;
import com.example.bookshelf.ui.main.view_models.NewBookViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewBooksFragment extends ListFragment {

    private static final String TAG = NewBooksFragment.class.getName();
    private NewBookViewModel newBookViewModel;
    private static NewBooksFragment fragment;
    private BooksListAdapter adapter;
    private List<Book> data;

    public static NewBooksFragment newInstance() {
        fragment = new NewBooksFragment();
        return fragment;
    }

    public static NewBooksFragment getInstance(){
        if (fragment == null) {
            return null;
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newBookViewModel = ViewModelProviders.of(this).get(NewBookViewModel.class);
        data = new ArrayList<>();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        adapter = new BooksListAdapter(getActivity(), getContext(), data);
        setListAdapter(adapter);
        newBookViewModel.getBooks();
        return root;
    }

    public void updateBooks(List<Book> books){
        adapter.update(books);
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
