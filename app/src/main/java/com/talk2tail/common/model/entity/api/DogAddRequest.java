package com.talk2tail.common.model.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DogAddRequest {

    @Expose
    @SerializedName("short_nickname")
    public String shortNickname;

    @Expose
    @SerializedName("full_nickname")
    public String fullNickname;

    @Expose
    @SerializedName("photo")
    public String photo;

    @Expose
    @SerializedName("gender")
    public String gender;

    @Expose
    @SerializedName("is_sterialized")
    public boolean isSterialized;

    @Expose
    @SerializedName("birth_date")
    public Date birthDate;

    @Expose
    @SerializedName("pedigree_sequence")
    public String pedigree;

    @Expose
    @SerializedName("chip_sequence")
    public String chip;

    @Expose
    @SerializedName("stigma_sequence")
    public String stigma;

    @Expose
    @SerializedName("breed")
    public int breed;

    @Expose
    @SerializedName("color")
    public int color;

    public DogAddRequest(
            String shortNickname,
            String fullNickname,
            String photo,
            String gender,
            boolean isSterialized,
            Date birthDate,
            String pedigree,
            String chip,
            String stigma,
            int breed,
            int color

    ) {
        this.shortNickname = shortNickname;
        this.fullNickname = fullNickname;
        this.photo = photo;
        this.gender = gender;
        this.isSterialized = isSterialized;
        this.birthDate = birthDate;
        this.pedigree = pedigree;
        this.chip = chip;
        this.stigma = stigma;
        this.breed = breed;
        this.color = color;
    }

}
