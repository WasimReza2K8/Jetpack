package com.codeartist.androidpractice.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "property_table")
public class Property {
    @PrimaryKey
    @NonNull
    @SerializedName("id") @Expose
    public String id;
    @SerializedName("price") @Expose public int price;
    @SerializedName("type") @Expose public String type;
    @SerializedName("img_src") @Expose public String imgSrc;
    public Property(){}

    public Property(int price, String id, String type, String img_src) {
        this.price = price;
        this.id = id;
        this.type = type;
        this.imgSrc = img_src;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
