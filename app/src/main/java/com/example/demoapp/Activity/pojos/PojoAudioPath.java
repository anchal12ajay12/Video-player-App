package com.example.demoapp.Activity.pojos;

public class PojoAudioPath {
    String mp3, acc;

    public PojoAudioPath() {
    }

    public PojoAudioPath(String mp3, String acc) {
        this.mp3 = mp3;
        this.acc = acc;
    }

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }
}
