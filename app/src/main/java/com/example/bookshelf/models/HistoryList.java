package com.example.bookshelf.models;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HistoryList {
    private HashSet<String> set;
    private Deque<String> dq;
    private static final int N = 5;
    private static HistoryList instance;

    private HistoryList(){
        set = new HashSet<>();
        dq = new LinkedList<>();
    }

    public static HistoryList getInstance(){
        if (instance != null){
            return instance;
        }
        instance = new HistoryList();
        return instance;
    }

    public List<String> getSet(){
        List<String> result = new ArrayList<>();
        Iterator<String> itr = dq.iterator();
        while(itr.hasNext())
        {
            result.add(itr.next());
        }
        return result;
    }

    public void addBook(String isbn){
        if(!set.contains(isbn)) {
            if(dq.size() == N) {
                String last = dq.removeLast();
                set.remove(last);
            }
        }
        else {
            int index = 0;
            int i = 0;
            Iterator<String> itr = dq.iterator();
            while(itr.hasNext()) {
                if(itr.next().equals(isbn)) {
                    index = i;
                    break;
                }
                i++;
            }
            dq.remove(index);
        }
        dq.push(isbn);
        set.add(isbn);
    }

}
