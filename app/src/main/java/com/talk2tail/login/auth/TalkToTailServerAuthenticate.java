package com.talk2tail.login.auth;

import com.talk2tail.common.model.entity.api.LoginUser;
import com.talk2tail.common.model.entity.api.LoginUserResponse;
import com.talk2tail.common.model.repo.IRepo;

import javax.inject.Inject;

public class TalkToTailServerAuthenticate implements IServerAuthenticate {

    @Inject
    protected IRepo repo;

    @Override
    public String userSignIn(String email, String pass) throws Exception {
        try {
            LoginUser loginUser = new LoginUser(email, pass);
            LoginUserResponse loginUserResponse = repo.loginUserAuth(loginUser);
            return loginUserResponse.getKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String userSignUp(String user, String pass, String authType) throws Exception {
        return null;
    }
}
