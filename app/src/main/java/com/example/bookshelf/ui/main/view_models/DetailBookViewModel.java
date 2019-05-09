package com.example.bookshelf.ui.main.view_models;
import android.util.Log;

import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.DetailBook;
import com.example.bookshelf.models.DetailBookGetter;
import com.example.bookshelf.models.HistoryList;
import com.example.bookshelf.ui.main.fragments.DetailBookFragment;

public class DetailBookViewModel extends BookListViewModel {
    private static final String TAG = DetailBookViewModel.class.getName();

    public void getDetailBook(String isbn13) {
        DetailBookGetter detailBookGetter =  DetailBookGetter.getInstance();
        if (detailBookGetter != null) {
            detailBookGetter.getBooks(this, isbn13);
        }
        else{
            // TODO handle null case
        }
    }

    public void onSuccess(DetailBook data){
        DetailBookFragment fragment = DetailBookFragment.getInstance();
        if (fragment != null) {
            fragment.update(data);
        }
        else{
            // TODO handle null case
        }
    }

    // TODO add Error type and handling for each type
    public void onError(){
        Log.d(TAG, "ERROR RETURNED");
    }

    public void updateHistory(Book book) {
        HistoryList.getInstance().addBook(book);
    }
}
