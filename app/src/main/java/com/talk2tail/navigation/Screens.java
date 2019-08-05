package com.talk2tail.navigation;

import androidx.fragment.app.Fragment;

import com.talk2tail.dogdashboard.ui.DogDashboardFragment;
import com.talk2tail.dogvaccination.ui.DogVaccinationFragment;
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

}
