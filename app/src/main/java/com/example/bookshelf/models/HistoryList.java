package com.example.bookshelf.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HistoryList {
    private static final String TAG = HistoryList.class.getName();
    private List<Book> data;
    private static HistoryList instance;

    private HistoryList(){
        data = new ArrayList<>();
    }

    public static HistoryList getInstance(){
        if (instance != null){
            return instance;
        }
        instance = new HistoryList();
        return instance;
    }

    public List<Book> getSet(){
        return data;
    }

    public void addBook(Book book){
        data.add(0, book);
    }

}
