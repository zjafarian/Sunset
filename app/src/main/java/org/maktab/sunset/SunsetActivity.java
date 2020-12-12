package org.maktab.sunset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SunsetActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, SunsetActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return SunsetFragment.newInstance();
    }
}