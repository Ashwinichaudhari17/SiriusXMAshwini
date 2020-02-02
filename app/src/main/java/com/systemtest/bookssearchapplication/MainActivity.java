package com.systemtest.bookssearchapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BooksSearchQueryResponseListener {

    EditText searchQueryEdtTxt;
    LinearLayout progressLayout;
    TextView progressMsg;
    RecyclerView booksResultResponse;
    BooksSearchResultsAdapter booksSearchResultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        searchQueryEdtTxt = (EditText) findViewById(R.id.search_box);
        booksResultResponse = (RecyclerView) findViewById(R.id.bookslist);
        progressLayout = (LinearLayout) findViewById(R.id.progress_layout);
        progressMsg = (TextView) findViewById(R.id.message_txt);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        booksSearchResultsAdapter = new BooksSearchResultsAdapter(new ArrayList<BookResponse>());
        booksResultResponse.setLayoutManager(gridLayoutManager);
        booksResultResponse.setAdapter(booksSearchResultsAdapter);

        searchQueryEdtTxt.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    searchForBooks();
                    return true;
                }
                return false;
            }
        });
    }

    private void searchForBooks() {
        String searchQuery = searchQueryEdtTxt.getText().toString();
        if (!searchQuery.isEmpty()) {
            booksSearchResultsAdapter.updateData(new ArrayList<BookResponse>());
            progressLayout.setVisibility(View.VISIBLE);
            new SearchBooksQueryRunner(this, searchQuery).execute();
        }
    }

    @Override
    public void publishProgress(String message) {
        progressMsg.setText(message);
    }

    @Override
    public void onSuccessfulResponse(ArrayList<BookResponse> bookResponses) {
        progressLayout.setVisibility(View.GONE);
        booksSearchResultsAdapter.updateData(bookResponses);

    }
}
