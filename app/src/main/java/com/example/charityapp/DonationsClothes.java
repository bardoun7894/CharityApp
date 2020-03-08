package com.example.charityapp;

import java.io.Serializable;
public class DonationsClothes implements Serializable {
    private int clothesId;
    private String manClothes;
    private String womenClothes;
    private String childClothes;
    public DonationsClothes(String manClothes, String womenClothes, String childClothes) {
        this.manClothes = manClothes;
        this.womenClothes = womenClothes;
        this.childClothes = childClothes;
    }
    public String getManClothes() {
        return manClothes;
    }
    public void setManClothes(String manClothes) {
        this.manClothes = manClothes;
    }
    public String getWomenClothes() {
        return womenClothes;
    }

    public void setWomenClothes(String womenClothes) {
        this.womenClothes = womenClothes;
    }

    public String getChildClothes() {
        return childClothes;
    }

    public void setChildClothes(String childClothes) {
        this.childClothes = childClothes;
    }


    public int getClothesId() {
        return clothesId;
    }

    public void setClothesId(int clothesId) {
        this.clothesId = clothesId;
    }
}
