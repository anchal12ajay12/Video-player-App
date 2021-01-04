package com.example.demoapp.Activity.pojos;

public class PojoUser {
    String id, fb_id;
    PojoUserInfo user_info;
    PojoCount count;
    String liked, video, thum, gif, is_block, description;
    PojoSound sound;
    String created;

    public PojoUser() {
    }

    public PojoUser(String id, String fb_id, PojoUserInfo user_info, PojoCount count, String liked, String video, String thum, String gif, String is_block, String description, PojoSound sound, String created) {
        this.id = id;
        this.fb_id = fb_id;
        this.user_info = user_info;
        this.count = count;
        this.liked = liked;
        this.video = video;
        this.thum = thum;
        this.gif = gif;
        this.is_block = is_block;
        this.description = description;
        this.sound = sound;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFb_id() {
        return fb_id;
    }

    public void setFb_id(String fb_id) {
        this.fb_id = fb_id;
    }

    public PojoUserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(PojoUserInfo user_info) {
        this.user_info = user_info;
    }

    public PojoCount getCount() {
        return count;
    }

    public void setCount(PojoCount count) {
        this.count = count;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getThum() {
        return thum;
    }

    public void setThum(String thum) {
        this.thum = thum;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }

    public String getIs_block() {
        return is_block;
    }

    public void setIs_block(String is_block) {
        this.is_block = is_block;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PojoSound getSound() {
        return sound;
    }

    public void setSound(PojoSound sound) {
        this.sound = sound;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
