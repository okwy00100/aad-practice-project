package com.okwy.practiceproject.VolleyPicasso.Model;

public class Model {
    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public Model(String imageUrl, String creator, int likes) {
        mImageUrl = imageUrl;
        mCreator = creator;
        mLikes = likes;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getCreator() {
        return mCreator;
    }

    public int getLikes() {
        return mLikes;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public void setCreator(String creator) {
        mCreator = creator;
    }

    public void setLikes(int likes) {
        mLikes = likes;
    }
}
