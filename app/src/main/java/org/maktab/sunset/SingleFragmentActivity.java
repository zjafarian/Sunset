package org.maktab.sunset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract androidx.fragment.app.Fragment createFragment();

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflate layout for activity
        DataBindingUtil.setContentView(this, R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();

        //check if fragment exists in container (configuration changes save the fragments)
        androidx.fragment.app.Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        //create an add fragment transaction for CrimeDetailFragment
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, createFragment())
                    .commit();
        }
    }
}
