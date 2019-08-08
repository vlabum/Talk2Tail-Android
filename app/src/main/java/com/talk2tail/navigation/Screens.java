package com.talk2tail.navigation;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.talk2tail.dogdashboard.ui.DogDashboardFragment;
import com.talk2tail.dogvaccination.ui.DogVaccinationFragment;
import com.talk2tail.login.ui.LoginFragment;
import com.talk2tail.login.ui.RegisterFragment;
import com.talk2tail.main.ui.MainActivity;
import com.talk2tail.ownerdashboard.ui.OwnerDashboardFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    public static class DogDashboardScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return DogDashboardFragment.newInstance();
        }
    }

    public static class OwnerDashboardScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return OwnerDashboardFragment.newInstance();
        }
    }

    public static class DogVaccinationScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return DogVaccinationFragment.newInstance();
        }
    }

    public static class LoginFragmentScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return LoginFragment.newInstance();
        }
    }

    public static class MainActivityScreen extends SupportAppScreen {
        @Override
        public Intent getActivityIntent(Context context) {
            return MainActivity.getMainIntent(context);
        }
    }

    public static class RegisterFragmentScreen extends SupportAppScreen {
        public Fragment getFragment() {
            return RegisterFragment.newInstance();
        }
    }
}
