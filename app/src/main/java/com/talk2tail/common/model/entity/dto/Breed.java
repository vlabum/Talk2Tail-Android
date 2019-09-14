package com.talk2tail.common.model.entity.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.talk2tail.common.model.api.Response;

import lombok.Getter;

public class Breed extends Response {

    @Getter
    @Expose
    @SerializedName("id")
    private int id;

    @Getter
    @Expose
    @SerializedName("name")
    private String name;

    @Getter
    @Expose
    @SerializedName("female_min_weight")
    private float femaleMinWeight;

    @Getter
    @Expose
    @SerializedName("female_max_weight")
    private float femaleMaxWeight;

    @Getter
    @Expose
    @SerializedName("male_min_weight")
    private float maleMinWeight;

    @Getter
    @Expose
    @SerializedName("male_max_weight")
    private float maleMaxWeight;

    @Getter
    @Expose
    @SerializedName("female_average_height")
    private float femaleAverageHeight;

    @Getter
    @Expose
    @SerializedName("male_average_height")
    private float maleAverageHeight;

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
