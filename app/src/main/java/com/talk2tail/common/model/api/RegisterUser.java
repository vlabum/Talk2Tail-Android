package com.talk2tail.common.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.talk2tail.common.model.entity.IRegisterUser;

public class RegisterUser implements IRegisterUser {

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
