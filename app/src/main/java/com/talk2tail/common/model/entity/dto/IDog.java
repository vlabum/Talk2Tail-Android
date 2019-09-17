package com.talk2tail.common.model.entity.dto;

import androidx.annotation.Nullable;

import java.util.Date;

public interface IDog {

    int getId();

    void setId(int id);

    String getShortNickname();

    void setShortNickname(String shortNickname);

    String getFullNickname();

    void setFullNickname(String fullNickname);

    @Nullable
    String getPhoto();

    void setPhoto(String photo);

    String getGender();

    void setGender(String gender);

    int getIsSterilized();

    void setIsSterilized(int isSterilized);

    Date getBirthDate();

    void setBirthDate(Date birthDate);

    String getPedigree();

    void setPedigree(String pedigree);

    String getChip();

    void setChip(String chip);

    String getStigma();

    void setStigma(String stigma);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    Date getUpdatedAt();

    void setUpdatedAt(Date updatedAt);

    int getBreed();

    void setBreed(Breed breed);

    int getColor();

    void setColor(TalkToTailColor color);

}
