package com.example.bookshelf;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class BookshelfApplication extends Application {

    private static final String TAG = BookshelfApplication.class.getName();
    private RequestQueue requestQueue;
    private static BookshelfApplication mInstance;

    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized BookshelfApplication getInstance() {
        if (mInstance == null){
            Log.d(TAG, "INSTANCE NULL");
        }
        return mInstance;
    }

    // Returns current Volley Request Queue
    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        return requestQueue;
    }

    // adds HTTP request to Volley request Queue
    public void addToRequestQueue(Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    public void cancelAllRequests(String tag) {
        getRequestQueue().cancelAll(tag);
    }
}
