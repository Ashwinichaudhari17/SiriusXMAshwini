package com.systemtest.bookssearchapplication;

import java.util.ArrayList;

public class BookResponse {
    String bookName, smallThumbNailUrl, nrmlThumbnailUrl, publisherInfo, language, id;
    ArrayList<String> authorInfo, categoriesList;
    int pageCount;


    public BookResponse(String id, String bookName, String smallThumbNailUrl, String nrmlThumbnailUrl,
                        String publisherInfo, String language, ArrayList<String> authorInfo,
                        ArrayList<String> categoriesList, int pageCount) {
        this.bookName = bookName;
        this.id = id;
        this.smallThumbNailUrl = smallThumbNailUrl;
        this.nrmlThumbnailUrl = nrmlThumbnailUrl;
        this.publisherInfo = publisherInfo;
        this.language = language;
        this.authorInfo = authorInfo;
        this.categoriesList = categoriesList;
        this.pageCount = pageCount;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSmallThumbNailUrl() {
        return smallThumbNailUrl;
    }

    public void setSmallThumbNailUrl(String smallThumbNailUrl) {
        this.smallThumbNailUrl = smallThumbNailUrl;
    }

    public String getNrmlThumbnailUrl() {
        return nrmlThumbnailUrl;
    }

    public void setNrmlThumbnailUrl(String nrmlThumbnailUrl) {
        this.nrmlThumbnailUrl = nrmlThumbnailUrl;
    }

    public String getPublisherInfo() {
        return publisherInfo;
    }

    public void setPublisherInfo(String publisherInfo) {
        this.publisherInfo = publisherInfo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<String> getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(ArrayList<String> authorInfo) {
        this.authorInfo = authorInfo;
    }

    public ArrayList<String> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getCategoryList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String categy : categoriesList)
            stringBuilder.append(categy + ",");
        if (stringBuilder.length() > 0)
            stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }


    public String getAuthorList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String categy : authorInfo)
            stringBuilder.append(categy + ",");
        if (stringBuilder.length() > 0)
            stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
