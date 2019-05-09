package com.example.bookshelf.models;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bookshelf.BookshelfApplication;
import com.example.bookshelf.ui.main.view_models.BookListViewModel;
import com.example.bookshelf.ui.main.view_models.SearchListViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchResultGetter {
    private static final String books_tag = "books";
    private static final String title_tag = "title";
    private static final String subtitle_tag = "subtitle";
    private static final String isbn13_tag = "isbn13";
    private static final String price_tag = "price";
    private static final String image_tag = "image";
    private static final String url_tag = "url";
    private static final String total_tag = "total";
    private static final String TAG = SearchResultGetter.class.getName();
    private boolean stopFlag = false;
    private int currentPage = 0;

    private static List<Book> cashedArray = null;
    private static SearchResultGetter instance = null;
    private SearchResultGetter(){

    }
    public static SearchResultGetter getInstance(){
        if (instance == null){
            instance = new SearchResultGetter();
        }
        return instance;
    }
    public void getTotal(final SearchListViewModel searchListViewModel, String query){
        String url = "https://api.itbook.store/1.0/search/" + query ;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            searchListViewModel.setTotal(response.getInt(total_tag));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            searchListViewModel.onError();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        cashedArray = null;
                        //Failure Callback
                        Log.d(TAG, "ERROR response");
                        Log.d(TAG, error.networkResponse.toString());
                        searchListViewModel.onError();
                    }
                });
        BookshelfApplication.getInstance().addToRequestQueue(jsonObjReq, "getRequest");
    }
    public void getBooks(final BookListViewModel bookListViewModel, String query, int total){
        int N = total / 10;
        if (total % 10 != 0){
            N++;
        }
        for (int i = 1; i <= N; ++i) {
            String url = "https://api.itbook.store/1.0/search/" + query + "/" + i;
            Log.d(TAG, url);
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                cashedArray = new ArrayList<>();
                                JSONArray books = response.getJSONArray(books_tag);
                                for (int i = 0; i < books.length(); ++i) {
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

                            } catch (JSONException e) {
                                e.printStackTrace();
                                bookListViewModel.onError();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            cashedArray = null;
                            //Failure Callback
                            Log.d(TAG, "ERROR response");
                            Log.d(TAG, error.networkResponse.toString());
                            bookListViewModel.onError();
                        }
                    });
            BookshelfApplication.getInstance().addToRequestQueue(jsonObjReq, "getRequest");
        }
    }
}
