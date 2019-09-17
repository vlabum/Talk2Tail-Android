package com.talk2tail.navigation;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.talk2tail.common.AppConstants;
import com.talk2tail.dogdashboard.ui.DogAddFragment;
import com.talk2tail.dogdashboard.ui.DogDashboardFragment;
import com.talk2tail.dogvaccination.ui.DogVaccinationFragment;
import com.talk2tail.login.ui.LoginFragment;
import com.talk2tail.login.ui.RegisterFragment;
import com.talk2tail.main.ui.MainActivity;
import com.talk2tail.ownerdashboard.ui.OwnerDashEmptyFragment;
import com.talk2tail.ownerdashboard.ui.OwnerDashOneFragment;
import com.talk2tail.ownerdashboard.ui.OwnerDashboardFragment;
import com.talk2tail.userprofile.ui.UserProfileFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    public static class DogDashboardScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return DogDashboardFragment.newInstance();
        }
    }

    public static class DogAddScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return DogAddFragment.newInstance();
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
            return MainActivity.getMainIntent(context, AppConstants.OWNER_DASH_A_LOT_OF_DOG);
        }
    }

    public static class MainActivityScreenEmpty extends SupportAppScreen {
        @Override
        public Intent getActivityIntent(Context context) {
            return MainActivity.getMainIntent(context, AppConstants.OWNER_DASH_NO_DOG);
        }
    }

    public static class MainActivityScreenOne extends SupportAppScreen {
        @Override
        public Intent getActivityIntent(Context context) {
            return MainActivity.getMainIntent(context, AppConstants.OWNER_DASH_ONE_DOG);
        }
    }

    public static class RegisterFragmentScreen extends SupportAppScreen {
        public Fragment getFragment() {
            return RegisterFragment.newInstance();
        }
    }

    public static class OwnerDashEmptyFragmentScreen extends SupportAppScreen {
        public Fragment getFragment() {
            return OwnerDashEmptyFragment.newInstance();
        }
    }

    public static class OwnerDashOneFragmentScreen extends SupportAppScreen {
        public Fragment getFragment() {
            return OwnerDashOneFragment.newInstance();
        }
    }

    public static class UserProfileScreen extends SupportAppScreen {
        public Fragment getFragment() {
            return UserProfileFragment.newInstance();
        }
    }
}
