package com.systemtest.bookssearchapplication;

import java.util.ArrayList;

public interface BooksSearchQueryResponseListener {
    public void publishProgress(String message);
    public void onSuccessfulResponse(ArrayList<BookResponse> bookResponses);
}
