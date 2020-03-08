package com.example.charityapp;

public class User {
   private String  fullname;
    private String email;
    private String password;
    private String numberPhone;
    private DonationsClothes donationsClothes;

    public DonationsClothes getDonationsClothes() {
        return donationsClothes;
    }

    public void setDonationsClothes(DonationsClothes donationsClothes) {
        this.donationsClothes = donationsClothes;
    }

    public User(){

    }
    public User(String fullname, String email,String  password, String numberPhone) {
        this.fullname = fullname;
        this.email = email;
       this.password=password;
        this.numberPhone = numberPhone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {

        this.numberPhone = numberPhone;
    }
}
