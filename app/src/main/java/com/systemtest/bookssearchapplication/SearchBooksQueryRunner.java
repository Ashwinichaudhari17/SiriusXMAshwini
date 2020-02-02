package com.systemtest.bookssearchapplication;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchBooksQueryRunner extends AsyncTask<Void, String, Void> {

    BooksSearchQueryResponseListener booksSearchQueryResponseListener;
    ArrayList<BookResponse> bookResponses;
    String searchQuery;

    public SearchBooksQueryRunner(BooksSearchQueryResponseListener booksSearchQueryResponseListener,
                                  String searchQuery) {
        this.booksSearchQueryResponseListener = booksSearchQueryResponseListener;
        bookResponses = new ArrayList<>();
        this.searchQuery = searchQuery;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (booksSearchQueryResponseListener != null)
            booksSearchQueryResponseListener.publishProgress("Searching for books--");
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        if (booksSearchQueryResponseListener != null)
            booksSearchQueryResponseListener.publishProgress(values[0]);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(String.format("https://www.googleapis.com/books/v1/volumes?q=%s", searchQuery))
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            String resMsg = new String(response.body().bytes());
            publishProgress("Parsing response--");
            parseResponse(resMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (booksSearchQueryResponseListener != null)
            booksSearchQueryResponseListener.onSuccessfulResponse(bookResponses);
    }

    private void parseResponse(String response) {
        try {
            JSONObject resJsonObj = new JSONObject(response);
            JSONArray jsonArray = resJsonObj.getJSONArray("items");
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject bookResJson = jsonArray.getJSONObject(index);
                String id = bookResJson.getString("id");
                JSONObject volumeJsonObj = bookResJson.getJSONObject("volumeInfo");
                String title = volumeJsonObj.getString("title");
                JSONArray authorInfo = volumeJsonObj.getJSONArray("authors");
                ArrayList<String> authors = new ArrayList<>();
                for (int i = 0; i < authorInfo.length(); i++)
                    authors.add(authorInfo.getString(i));
                JSONArray categoriesJsonArr = volumeJsonObj.getJSONArray("categories");
                ArrayList<String> categories = new ArrayList<>();
                for (int i = 0; i < categoriesJsonArr.length(); i++)
                    categories.add(categoriesJsonArr.getString(i));
                int pageCount = volumeJsonObj.getInt("pageCount");
                String language = volumeJsonObj.getString("language");
                JSONObject imageLinks = volumeJsonObj.getJSONObject("imageLinks");
                String smallThumbUrl = imageLinks.getString("smallThumbnail");
                String nrmlThumbUrl = imageLinks.getString("thumbnail");
                BookResponse bookResponse = new BookResponse(
                        id, title, smallThumbUrl, nrmlThumbUrl, "", language, authors, categories, pageCount);
                bookResponses.add(bookResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
