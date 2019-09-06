package com.talk2tail.common.model.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.talk2tail.common.model.api.Response;

import lombok.Getter;

public class RegisterUserResponse extends Response {

    @Getter
    @Expose
    @SerializedName("key")
    private String key;

}
