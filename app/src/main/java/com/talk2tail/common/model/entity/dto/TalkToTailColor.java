package com.talk2tail.common.model.entity.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class TalkToTailColor {

    @Expose
    @SerializedName("id")
    public int id;

    @Expose
    @SerializedName("name")
    public String name;

    public TalkToTailColor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
