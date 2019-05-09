package com.example.bookshelf.ui.main.view_models;

import android.util.Log;

import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.SearchResultGetter;
import com.example.bookshelf.ui.main.fragments.SearchFragment;

import java.util.List;

public class SearchListViewModel extends BookListViewModel {
    private static final String TAG = NewBookViewModel.class.getName();
    private String query;
    public void getBooks(String query) {
        this.query = query;
        SearchResultGetter searchResultGetter =  SearchResultGetter.getInstance();
        if (searchResultGetter != null) {
            searchResultGetter.getTotal(this, query);
        }
        else{
            // TODO handle null case
        }
    }

    public void onSuccess(List<Book> data){
        SearchFragment fragment = SearchFragment.getInstance();
        if (fragment != null) {
            fragment.updateBooks(data);
        }
        else{
            // TODO handle null case
        }
    }
    public void setTotal(int total) {
        SearchResultGetter searchResultGetter =  SearchResultGetter.getInstance();
        searchResultGetter.getBooks(this, query, total);
    }

    // TODO add Error type and handling for each type
    public void onError(){
        Log.d(TAG, "ERROR RETURNED");
    }
}
