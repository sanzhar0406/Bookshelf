package com.example.bookshelf.models;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bookshelf.BookshelfApplication;
import com.example.bookshelf.ui.main.view_models.BookListViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BooksGetter {

    private static final String books_tag = "books";
    private static final String title_tag = "title";
    private static final String subtitle_tag = "subtitle";
    private static final String isbn13_tag = "isbn13";
    private static final String price_tag = "price";
    private static final String image_tag = "image";
    private static final String url_tag = "url";
    private static final String TAG = BooksGetter.class.getName();

    private static List<Book> cashedArray = null;
    private static BooksGetter instance = null;
    private BooksGetter(){

    }
    public static BooksGetter getInstance(){
        if (instance == null){
            instance = new BooksGetter();
        }
        return instance;
    }
    public void getBooks(final BookListViewModel bookListViewModel){
        if (cashedArray != null){
            bookListViewModel.onSuccess(cashedArray);
        }
        String url = "https://api.itbook.store/1.0/new";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cashedArray = new ArrayList<>();
                        Log.d(TAG, response.toString());
                        try{
                            Log.d(TAG, "Not null response");

                            JSONArray books = response.getJSONArray(books_tag);
                            Log.d(TAG, "json array size - " + books.length());
                            for (int i = 0; i < books.length(); ++i){
                                JSONObject object = (JSONObject) books.get(i);
                                Book book = new Book.Builder()
                                        .withTitle(object.getString(title_tag))
                                        .withSubTitle(object.getString(subtitle_tag))
                                        .withisbn13(object.getString(isbn13_tag))
                                        .withPrice(object.getString(price_tag))
                                        .withUrl(object.getString(url_tag))
                                        .withImage(object.getString(image_tag))
                                        .build();
                                cashedArray.add(book);
                            }
                            bookListViewModel.onSuccess(cashedArray);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            bookListViewModel.onError();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        cashedArray = null;
                        Log.d(TAG, error.networkResponse.toString());
                        bookListViewModel.onError();
                    }
                });
        BookshelfApplication.getInstance().addToRequestQueue(jsonObjReq, "getRequest");
    }
}
