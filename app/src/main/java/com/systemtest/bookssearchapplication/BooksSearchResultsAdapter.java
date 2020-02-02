package com.systemtest.bookssearchapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class BooksSearchResultsAdapter extends RecyclerView.Adapter<BooksSearchResultsAdapter.MyViewHolder> {

    ArrayList<BookResponse> books;
    ImageLoader imageLoader;

    public BooksSearchResultsAdapter(ArrayList<BookResponse> books) {
        this.books = new ArrayList<>();
        this.books.addAll(books);
        imageLoader = ImageLoader.getInstance();
    }

    public void updateData(ArrayList<BookResponse> books) {
        this.books.clear();
        this.books.addAll(books);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.books_grid_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int index) {
        index = myViewHolder.getAdapterPosition();
        BookResponse bookResponse = books.get(index);
        myViewHolder.bookTitleTxtView.setText(bookResponse.bookName);
        myViewHolder.bookAuthorTxtView.setText(bookResponse.getAuthorList());
        imageLoader.displayImage(bookResponse.smallThumbNailUrl, myViewHolder.bookThumbNailImageView);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView bookTitleTxtView, bookAuthorTxtView;
        public ImageView bookThumbNailImageView;

        public MyViewHolder(View view) {
            super(view);
            bookTitleTxtView = (TextView) view.findViewById(R.id.book_title);
            bookAuthorTxtView = (TextView) view.findViewById(R.id.book_author_info);
            bookThumbNailImageView = (ImageView) view.findViewById(R.id.thumb_image);
        }
    }
}
