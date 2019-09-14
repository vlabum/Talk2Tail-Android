package com.talk2tail.common.model.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.talk2tail.common.model.api.Response;

import java.util.Date;

public class DogAddResponse extends Response {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("short_nickname")
    private String shortNickname;

    @Expose
    @SerializedName("full_nickname")
    private String fullNickname;

    @Expose
    @SerializedName("gender")
    private String gender;

    @Expose
    @SerializedName("is_sterialized")
    private boolean isSterialized;

    @Expose
    @SerializedName("birth_date")
    private Date birthDate;

    @Expose
    @SerializedName("pedigree_sequence")
    private String pedigree;

    @Expose
    @SerializedName("chip_sequence")
    private String chip;

    @Expose
    @SerializedName("stigma_sequence")
    private String stigma;

    @Expose
    @SerializedName("created_at")
    private Date createdAt;

    @Expose
    @SerializedName("updated_at")
    private Date updatedAt;

    @Expose
    @SerializedName("breed")
    private int breed;

    @Expose
    @SerializedName("color")
    private int color;

}
