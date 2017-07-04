package com.codecool.firstaidcompetition.model;

/**
 * Created by keli on 2017.07.04..
 */
public enum TeamCategory {
    CHILD("Gyermek"), JUNIOR("Ifjúsági"), SENIOR("Felnőtt");
    private String value;

    private TeamCategory(String value){
        this.value = value;
    }

    public String getCategory() {
        return value;
    }
}
