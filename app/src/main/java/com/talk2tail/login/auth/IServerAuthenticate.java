package com.talk2tail.login.auth;

public interface IServerAuthenticate {
    public String userSignIn(final String email, final String pass) throws Exception;

    public String userSignUp(final String user, final String pass, String authType) throws Exception;
}
