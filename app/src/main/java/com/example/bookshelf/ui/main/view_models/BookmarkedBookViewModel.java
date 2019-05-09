package com.example.bookshelf.ui.main.view_models;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.BookmarkedBookList;
import com.example.bookshelf.ui.main.fragments.BookmarkFragment;
import com.example.bookshelf.ui.main.fragments.NewBooksFragment;

import java.util.List;

public class BookmarkedBookViewModel extends BookListViewModel{
    private static final String TAG = BookmarkedBookList.class.getName();

    public void onSuccess(List<Book> data){
        BookmarkFragment fragment = BookmarkFragment.getInstance();
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
