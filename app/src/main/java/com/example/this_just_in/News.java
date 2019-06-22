package com.example.this_just_in;

public class News {


    // The link to the image
    private String mUrlToImage;

    // Name of the source
    private String mName;

    // The title of the nes item
    private String mTitle;

    // Publishing date
    private String mPublishedAt;

    public News(String title, String name, String publishedAt, String urlToImage) {
        mName = name;
        mTitle = title;
        mPublishedAt = publishedAt;
        mUrlToImage = urlToImage;
    }

    // Getter methods

    public String getUrlToImage() {
        return mUrlToImage;
    }

    public String getName() {
        return mName;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

}
