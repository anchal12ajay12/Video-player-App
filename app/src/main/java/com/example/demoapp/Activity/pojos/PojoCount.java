package com.example.demoapp.Activity.pojos;

public class PojoCount {
    String like_count, video_comment_count;

    public PojoCount() {
    }

    public PojoCount(String like_count, String video_comment_count) {
        this.like_count = like_count;
        this.video_comment_count = video_comment_count;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getVideo_comment_count() {
        return video_comment_count;
    }

    public void setVideo_comment_count(String video_comment_count) {
        this.video_comment_count = video_comment_count;
    }
}
