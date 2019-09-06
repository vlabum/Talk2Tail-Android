package com.talk2tail.common.model.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterUser {

    @Expose
    @SerializedName("email")
    public String email;

    @Expose
    @SerializedName("password1")
    public String password1;

    @Expose
    @SerializedName("password2")
    public String password2;

    public RegisterUser(String email, String password1, String password2) {
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
    }

}
