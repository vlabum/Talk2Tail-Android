package com.talk2tail.common.model.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.talk2tail.common.model.api.Response;

import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public class DogFullResponse extends Response {

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
/*
     "id": 4,
     "short_nickname": "secong dog",
     "full_nickname": "tested2",
     "photo": null,
     "gender": "3",
     "is_sterialized": 1,
     "birth_date": "2019-08-29T12:27:53.949783+03:00",
     "pedigree_sequence": null,
     "chip_sequence": null,
     "stigma_sequence": null,
     "created_at": "2019-08-29T12:27:53.949783+03:00",
     "updated_at": "2019-08-29T12:27:53.949783+03:00",
     "breed": null
*/
