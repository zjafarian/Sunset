package org.maktab.sunset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class SunsetActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return SunsetFragment.newInstance();
    }
}