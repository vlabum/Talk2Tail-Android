package com.talk2tail.login.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created with IntelliJ IDEA.
 * User: Udini
 * Date: 19/03/13
 * Time: 19:10
 */
public class TalkToTailAuthenticatorService extends Service {
    @Override
    public IBinder onBind(Intent intent) {

        TalkToTailAuthenticator authenticator = new TalkToTailAuthenticator(this);
        return authenticator.getIBinder();
    }
}
