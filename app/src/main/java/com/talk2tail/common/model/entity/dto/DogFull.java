package com.talk2tail.common.model.entity.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.talk2tail.common.model.api.Response;

import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public class DogFull extends Response {

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
    @SerializedName("photo")
    private String photo;

    @Expose
    @SerializedName("gender")
    private int gender;

    @Expose
    @SerializedName("is_sterialized")
    private boolean isSterialized; //TODO: class BooleanTypeAdapter implements JsonDeserializer<Boolean>

    @Expose
    @SerializedName("birth_date")
    private Date birthDate;

    @Expose
    @SerializedName("pedigree_sequence")
    private List<Object> pedigreeSequence;

    @Expose
    @SerializedName("chip_sequence")
    private List<Object> chipSequence;

    @Expose
    @SerializedName("stigma_sequence")
    private List<Object> stigmaSequence;

    @Expose
    @SerializedName("created_at")
    private Date createdAt;

    @Expose
    @SerializedName("updated_at")
    private Date updatedAt;

    @Expose
    @SerializedName("breed")
    private String breed;

}

