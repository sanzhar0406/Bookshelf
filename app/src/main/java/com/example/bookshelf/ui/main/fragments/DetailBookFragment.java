package com.example.bookshelf.ui.main.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.BookmarkedBookList;
import com.example.bookshelf.models.DetailBook;
import com.example.bookshelf.models.PersistantStorage;
import com.example.bookshelf.ui.main.view_models.DetailBookViewModel;
import com.squareup.picasso.Picasso;


public class DetailBookFragment extends Fragment{

    public static final String TAG = DetailBookFragment.class.getName();
    private static final String isbn_tag = "ISBN13";
    private String isbn;
    private static DetailBookFragment instance;
    private DetailBookViewModel detailBookViewModel;
    private View view;
    private EditText note;
    private Book book;

    public static DetailBookFragment newInstance(Book book, String isbn) {
        DetailBookFragment fragment = new DetailBookFragment();
        Bundle bundle = new Bundle();
        bundle.putString(isbn_tag, isbn);
        fragment.book = book;
        fragment.setArguments(bundle);
        instance = fragment;
        return fragment;
    }

    public static DetailBookFragment getInstance(){
        if (instance == null) {
            return null;
        }
        return instance;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.isbn = getArguments().getString(isbn_tag);
        detailBookViewModel = ViewModelProviders.of(this).get(DetailBookViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_book, container, false);
        detailBookViewModel.getDetailBook(getArguments().getString(isbn_tag));
        final SharedPreferences sharedPreferences= getContext().getSharedPreferences(PersistantStorage.STORAGE_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        note = view.findViewById(R.id.note);
        // Saving note to shared preferences for isbn13
        String savedNote = sharedPreferences.getString(isbn, "");
        if (savedNote != null && !savedNote.equals("")){
            note.setText(savedNote);
        }
        note.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                editor.putString(isbn, s.toString());
                editor.apply();
            }
        });

        // updating the bookmarked list by utilizing checkbox
        CheckBox checkBox = view.findViewById(R.id.bookmarked);
        if (BookmarkedBookList.getInstance().isInSet(book)){
            checkBox.setChecked(true);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked){
                    BookmarkedBookList.getInstance().addBook(book);
                }
                else{
                    BookmarkedBookList.getInstance().removeBook(book);
                }
            }
        });


        return view;
    }

    public void update(DetailBook detailBook){
        detailBookViewModel.updateHistory(book);

        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        TextView titleTextView = view.findViewById(R.id.title);
        TextView subTitleTextView = view.findViewById(R.id.subtitle);
        TextView priceTextView = view.findViewById(R.id.price);
        TextView urlTextView = view.findViewById(R.id.url);
        TextView isbn13TextView = view.findViewById(R.id.isbn);
        TextView yearTextView = view.findViewById(R.id.year);
        TextView publisherTextView = view.findViewById(R.id.publisher);
        TextView authorsTextView = view.findViewById(R.id.authors);
        TextView pagesTextView = view.findViewById(R.id.pages);
        TextView languageTextView = view.findViewById(R.id.language);
        TextView ratingTextView = view.findViewById(R.id.rating);
        TextView descTextView = view.findViewById(R.id.desc);

        ImageView imageView = view.findViewById(R.id.image);

        titleTextView.setText(detailBook.getTitle());
        subTitleTextView.setText(detailBook.getSubTitle());
        priceTextView.setText("Price : " + detailBook.getPrice());
        urlTextView.setText(detailBook.getUrl());
        isbn13TextView.setText(detailBook.getisbn10());
        yearTextView.setText("Year : " + detailBook.getYear());
        publisherTextView.setText("Publisher : " + detailBook.getPublisher());
        authorsTextView.setText("Authors : " + detailBook.getAuthors());
        pagesTextView.setText("Pages : " + detailBook.getPages());
        languageTextView.setText("Language : " + detailBook.getLanguage());
        ratingTextView.setText("Rating : " + detailBook.getRating());
        descTextView.setText("Description : " + detailBook.getDesc());


        Picasso.get().load(detailBook.getImage()).resize(150,150).into(imageView);

    }
}
