package com.firstapp.call_log_appilication;

import android.net.Uri;

public class CallLogModel {
    String name,number,time,duration,type,image;
    Uri imageUri;

    public CallLogModel(String name, String number,
                        String time, String duration, String type, String image, Uri imageUri) {
        this.name = name;
        this.number = number;
        this.time = time;
        this.duration = duration;
        this.type = type;
        this.image = image;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
