package com.example.demoapp.Activity.pojos;

public class PojoUserInfo {
    String first_name, last_name, profile_pic, username, verified, bio, gender;

    public PojoUserInfo() {
    }

    public PojoUserInfo(String first_name, String last_name, String profile_pic, String username, String verified, String bio, String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.profile_pic = profile_pic;
        this.username = username;
        this.verified = verified;
        this.bio = bio;
        this.gender = gender;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
