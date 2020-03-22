package com.example.charityapp.model;

import java.io.Serializable;

public class BabyToolsModel implements Serializable {
    String bedChild ;
    String babyWalk ;
    String babyChair ;
    String babyCar ;
    String babyOthers ;
    public BabyToolsModel(String bedChild, String babyWalk, String babyChair, String babyCar, String babyOthers) {
        this.bedChild = bedChild;
        this.babyWalk = babyWalk;
        this.babyChair = babyChair;
        this.babyCar = babyCar;
        this.babyOthers = babyOthers;
    }

    public String getBedChild() {
        return bedChild;
    }

    public void setBedChild(String bedChild) {
        this.bedChild = bedChild;
    }

    public String getBabyWalk() {
        return babyWalk;
    }

    public void setBabyWalk(String babyWalk) {
        this.babyWalk = babyWalk;
    }

    public String getBabyChair() {
        return babyChair;
    }

    public void setBabyChair(String babyChair) {
        this.babyChair = babyChair;
    }

    public String getBabyCar() {
        return babyCar;
    }

    public void setBabyCar(String babyCar) {
        this.babyCar = babyCar;
    }

    public String getBabyOthers() {
        return babyOthers;
    }

    public void setBabyOthers(String babyOthers) {
        this.babyOthers = babyOthers;
    }
}
