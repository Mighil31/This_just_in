package com.example.this_just_in;
import java.io.Serializable;
@SuppressWarnings("serial")
public class News implements Serializable {


    // The link to the image
    private String mUrlToImage;

    // Name of the source
    private String mName;

    // The title of the nes item
    private String mTitle;

    // Publishing date
    private String mPublishedAt;

    private String mcontent;

    public News(String title, String name, String publishedAt, String urlToImage, String content) {
        mName = name;
        mTitle = title;
        mPublishedAt = publishedAt;
        mUrlToImage = urlToImage;
        mcontent = content;
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

    public String getContent() {
        return mcontent;
    }

}
