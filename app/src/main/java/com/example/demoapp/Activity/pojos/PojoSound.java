package com.example.demoapp.Activity.pojos;

public class PojoSound {
    String id;
    PojoAudioPath audio_path;
    String sound_name, description, thum, section, created;

    public PojoSound() {
    }

    public PojoSound(String id, PojoAudioPath audio_path, String sound_name, String description, String thum, String section, String created) {
        this.id = id;
        this.audio_path = audio_path;
        this.sound_name = sound_name;
        this.description = description;
        this.thum = thum;
        this.section = section;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PojoAudioPath getAudio_path() {
        return audio_path;
    }

    public void setAudio_path(PojoAudioPath audio_path) {
        this.audio_path = audio_path;
    }

    public String getSound_name() {
        return sound_name;
    }

    public void setSound_name(String sound_name) {
        this.sound_name = sound_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThum() {
        return thum;
    }

    public void setThum(String thum) {
        this.thum = thum;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
