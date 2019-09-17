package com.talk2tail.common.model.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.talk2tail.common.model.api.Response;
import com.talk2tail.common.model.entity.dto.TalkToTailColor;

import lombok.Getter;

public class BreedColorsResponse extends Response {

    @Getter
    @Expose
    @SerializedName("color")
    private TalkToTailColor color;

}
