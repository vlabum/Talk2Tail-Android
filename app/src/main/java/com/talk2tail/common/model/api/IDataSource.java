package com.talk2tail.common.model.api;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IDataSource {

    @POST("/api/v1/rest-auth/registration/")
    Single<RegisterUserResponse> restAuthLogin(@Body RegisterUser registerUser);

}
