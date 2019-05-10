package com.example.bookshelf.models;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bookshelf.BookshelfApplication;
import com.example.bookshelf.ui.main.view_models.DetailBookViewModel;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailBookGetter {
    private static final String books_tag = "books";
    private static final String title_tag = "title";
    private static final String subtitle_tag = "subtitle";
    private static final String isbn10_tag = "isbn10";
    private static final String price_tag = "price";
    private static final String image_tag = "image";
    private static final String url_tag = "url";
    private static final String desc_tag = "desc";
    private static final String publisher_tag = "publisher";
    private static final String year_tag = "year";
    private static final String pages_tag = "pages";
    private static final String language_tag = "language";
    private static final String authors_tag = "authors";
    private static final String TAG = DetailBookGetter.class.getName();
    private static final String rating_tag = "rating";

    private static DetailBook cache = null;
    private static DetailBookGetter instance = null;

    private DetailBookGetter(){

    }
    public static DetailBookGetter getInstance(){
        if (instance == null){
            instance = new DetailBookGetter();
        }
        return instance;
    }

    // Gets details of the book by isbn
    public void getBooks(final DetailBookViewModel detailBookViewModel, String isbn13){
        if (cache != null){
            detailBookViewModel.onSuccess(cache);
        }
        String url = "https://api.itbook.store/1.0/books/" + isbn13;
        Log.d(TAG, "url - " + url);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try{
                            Log.d(TAG, "response - " + object.toString());
                            DetailBook detailBook = new DetailBook.Builder()
                                    .withTitle(object.getString(title_tag))
                                    .withSubTitle(object.getString(subtitle_tag))
                                    .withisbn13(object.getString(isbn10_tag))
                                    .withPrice(object.getString(price_tag))
                                    .withUrl(object.getString(url_tag))
                                    .withImage(object.getString(image_tag))
                                    .withAuthors(object.getString(authors_tag))
                                    .withDesc(object.getString(desc_tag))
                                    .withLanguage(object.getString(language_tag))
                                    .withPages(object.getString(pages_tag))
                                    .withPublisher(object.getString(publisher_tag))
                                    .withYear(object.getString(year_tag))
                                    .withRating(object.getString(rating_tag))
                                    .build();

                            detailBookViewModel.onSuccess(detailBook);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            detailBookViewModel.onError();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                        Log.d(TAG, "ERROR response");
                        Log.d(TAG, error.networkResponse.toString());
                        detailBookViewModel.onError();
                    }
                });
        BookshelfApplication.getInstance().addToRequestQueue(jsonObjReq, "getRequest");
    }
}
