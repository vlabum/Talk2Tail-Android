package com.talk2tail.common.model.entity.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.talk2tail.common.model.api.Response;

import java.util.Date;

public class DogFull extends Response implements IDog {

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
    private String gender;

    @Expose
    @SerializedName("is_sterialized")
    private int isSterilized;

    @Expose
    @SerializedName("birth_date")
    private Date birthDate;

    @Expose
    @SerializedName("pedigree_sequence")
    private String pedigreeSequence;

    @Expose
    @SerializedName("chip_sequence")
    private String chip;

    @Expose
    @SerializedName("stigma_sequence")
    private String stigmaSequence;

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


    public DogFull(
            String shortNickname,
            String fullNickname,
            String photo,
            String gender,
            int isSterilized,
            Date birthDate,
            String pedigree,
            String chip,
            String stigma,
            Breed breed,
            TalkToTailColor color
    ) {
        this.shortNickname = shortNickname;
        this.fullNickname = fullNickname;
        this.photo = photo;
        this.gender = gender;
        this.isSterilized = isSterilized;
        this.birthDate = birthDate;
        this.pedigreeSequence = pedigree;
        this.chip = chip;
        this.stigmaSequence = stigma;
        this.breed = breed.getId();
        this.color = color.getId();
    }

    @Override
    public int getIsSterilized() {
        return isSterilized;
    }

    @Override
    public void setIsSterilized(int isSterilized) {
        this.isSterilized = isSterilized;
    }

    @Override
    public String getChip() {
        return chip;
    }

    @Override
    public void setChip(String chip) {
        this.chip = chip;
    }

    @Override
    public int getBreed() {
        return breed;
    }

    @Override
    public void setBreed(Breed breed) {
        this.breed = breed.getId();
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(TalkToTailColor color) {
        this.color = color.getId();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getShortNickname() {
        return shortNickname;
    }

    @Override
    public void setShortNickname(String shortNickname) {
        this.shortNickname = shortNickname;
    }

    @Override
    public String getFullNickname() {
        return fullNickname;
    }

    @Override
    public void setFullNickname(String fullNickname) {
        this.fullNickname = fullNickname;
    }

    @Override
    public String getPhoto() {
        return photo;
    }

    @Override
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String getPedigree() {
        return pedigreeSequence;
    }

    @Override
    public void setPedigree(String pedigreeSequence) {
        this.pedigreeSequence = pedigreeSequence;
    }

    @Override
    public String getStigma() {
        return stigmaSequence;
    }

    @Override
    public void setStigma(String stigmaSequence) {
        this.stigmaSequence = stigmaSequence;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}

