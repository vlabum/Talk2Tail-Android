package com.talk2tail.common.model.entity.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.talk2tail.common.model.api.Response;

import java.util.Date;

import lombok.Getter;

public class DogShort extends Response {

    @Getter
    @Expose
    @SerializedName("short_nickname")
    private String shortNickname;

    @Getter
    @Expose
    @SerializedName("photo")
    private String photo;

    @Getter
    @Expose
    @SerializedName("birth_date")
    private Date birthDate;

}
