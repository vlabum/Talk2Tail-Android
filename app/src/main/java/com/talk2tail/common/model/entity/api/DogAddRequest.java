package com.talk2tail.common.model.entity.api;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.talk2tail.common.model.entity.dto.Breed;
import com.talk2tail.common.model.entity.dto.IDog;
import com.talk2tail.common.model.entity.dto.TalkToTailColor;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DogAddRequest implements IDog {

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
    public int isSterilized;

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

    public DogAddRequest(IDog dog) {
        this.shortNickname = dog.getShortNickname();
        this.fullNickname = dog.getFullNickname();
        this.photo = dog.getPhoto();
        this.gender = dog.getGender();
        this.isSterilized = dog.getIsSterilized();
        this.birthDate = dog.getBirthDate();
        this.pedigree = dog.getPedigree();
        this.chip = dog.getChip();
        this.stigma = dog.getStigma();
        this.breed = dog.getBreed();
        this.color = dog.getColor();
    }

    public DogAddRequest(
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
        this.pedigree = pedigree;
        this.chip = chip;
        this.stigma = stigma;
        this.breed = breed.getId();
        this.color = color.getId();
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

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
    public String getPedigree() {
        return pedigree;
    }

    @Override
    public void setPedigree(String pedigreeSequence) {
        this.pedigree = pedigreeSequence;
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
    public String getStigma() {
        return stigma;
    }

    @Override
    public void setStigma(String stigmaSequence) {
        this.stigma = stigmaSequence;
    }

    @Override
    @Nullable
    public Date getCreatedAt() {
        return null;
    }

    @Override
    public void setCreatedAt(Date createdAt) {

    }

    @Override
    @Nullable
    public Date getUpdatedAt() {
        return null;
    }

    @Override
    public void setUpdatedAt(Date updatedAt) {

    }

    @Override
    public void setBreed(Breed breed) {
        this.breed = breed.getId();
    }

    @Override
    public void setColor(TalkToTailColor color) {
        this.color = color.getId();
    }
}
