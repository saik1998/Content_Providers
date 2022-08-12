package com.firstapp.reacycler_contact_search.Model;

import android.net.Uri;

public class ContactModel {
    Uri imageUri;
    String name,number,image;

    String alertImage,alretName;

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAlertImage() {
        return alertImage;
    }

    public void setAlertImage(String alertImage) {
        this.alertImage = alertImage;
    }

    public String getAlretName() {
        return alretName;
    }

    public void setAlretName(String alretName) {
        this.alretName = alretName;
    }

    public ContactModel(String name, String number, String image) {
        this.imageUri = imageUri;
        this.name = name;
        this.number = number;
        this.image = image;
        this.alertImage = alertImage;
        this.alretName = alretName;
    }
}
