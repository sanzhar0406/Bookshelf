package com.example.bookshelf.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BookmarkedBookList {
    private static final String TAG = BookmarkedBookList.class.getName();
    private List<Book> bookmarked;
    private static BookmarkedBookList instance;

    private BookmarkedBookList(){
        bookmarked = new ArrayList<>();
    }

    public static BookmarkedBookList getInstance(){
        if (instance != null){
            return instance;
        }
        instance = new BookmarkedBookList();
        return instance;
    }

    public List<Book> getBookmarkedSet(){
        return bookmarked;
    }

    public void addBook(Book book){
        bookmarked.add(book);
    }

    public void removeBook(Book book){
        bookmarked.remove(book);
    }

    public boolean isInSet(Book book){
        return bookmarked.contains(book);
    }

}
