package com.example.bookshelf.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BookmarkedBookList {
    private HashSet<String> bookmarked;
    private static BookmarkedBookList instance;

    private BookmarkedBookList(){
        bookmarked = new HashSet<>();
    }

    public static BookmarkedBookList getInstance(){
        if (instance != null){
            return instance;
        }
        instance = new BookmarkedBookList();
        return instance;
    }

    public HashSet<String> getBookmarkedSet(){
        return bookmarked;
    }

    public void addBook(String isbn){
        bookmarked.add(isbn);
    }

    public void removeBook(String isbn){
        if (bookmarked.contains(isbn)) {
            bookmarked.remove(isbn);
        }
    }

    public boolean isInSet(String isbn){
        return bookmarked.contains(isbn);
    }

}
