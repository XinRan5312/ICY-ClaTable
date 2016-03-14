package com.example.zane.icy_clatable.data.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Zane on 16/3/14.
 */
public class Clazz {


    @SerializedName("class")
    private List<?> classX;

    public void setClassX(List<?> classX) {
        this.classX = classX;
    }

    public List<?> getClassX() {
        return classX;
    }
}
