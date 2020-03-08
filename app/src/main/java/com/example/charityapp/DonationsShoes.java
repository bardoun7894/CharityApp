package com.example.charityapp;

import java.io.Serializable;

public class DonationsShoes implements Serializable {
    private int typeId;
    private String ManShoes;
    private String womenShoes;
    private String childShoes;
    public DonationsShoes(String ManShoes, String womenShoes, String childShoes) {
        this.ManShoes = ManShoes;
        this.womenShoes = womenShoes;
        this.childShoes = childShoes;
    }
    public String getManShoes() {
        return ManShoes;
    }

    public void setManShoes(String manShoes) {
        this.ManShoes = manShoes;
    }

    public String getWomenShoes() {
        return womenShoes;
    }

    public void setWomenShoes(String womenShoes) {
        this.womenShoes = womenShoes;
    }

    public String getChildShoes() {
        return childShoes;
    }

    public void setChildShoes(String childShoes) {
        this.childShoes = childShoes;
    }
 

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
