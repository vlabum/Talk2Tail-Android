package com.talk2tail.common.model.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class UserInfoFull {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("username")
    private String userName;

    @Expose
    @SerializedName("first_name")
    private String firstName;

    @Expose
    @SerializedName("last_name")
    private String lastName;

    @Expose
    @SerializedName("phone")
    private String phone;

    @Expose
    @SerializedName("city_id")
    private Long cityId;

    @Expose
    @SerializedName("country_id")
    private Long countryId;

    @Expose
    @SerializedName("photo")
    private String photo;

    @Expose
    @SerializedName("created_at")
    private String createdAt;

}
