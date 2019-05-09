package com.example.bookshelf.ui.main.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.NewBooksGetter;
import com.example.bookshelf.ui.main.fragments.NewBooksFragment;

import java.util.List;

public class NewBookViewModel extends BookListViewModel{

    private static final String TAG = NewBookViewModel.class.getName();

    public void getBooks() {
        NewBooksGetter newBooksGetter =  NewBooksGetter.getInstance();
        if (newBooksGetter != null) {
            newBooksGetter.getBooks(this);
        }
        else{
            // TODO handle null case
        }
    }

    public void onSuccess(List<Book> data){
        NewBooksFragment fragment = NewBooksFragment.getInstance();
        if (fragment != null) {
            fragment.updateBooks(data);
        }
        else{
            // TODO handle null case
        }
    }

    // TODO add Error type and handling for each type
    public void onError(){
        Log.d(TAG, "ERROR RETURNED");
    }
}
