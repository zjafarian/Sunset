package org.maktab.sunset;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.maktab.sunset.databinding.FragmentSunsetBinding;

public class SunsetFragment extends Fragment {

    private FragmentSunsetBinding mBinding;

    public SunsetFragment() {
        // Required empty public constructor
    }

    public static SunsetFragment newInstance() {
        SunsetFragment fragment = new SunsetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_sunset,
                container,
                false);

        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        return mBinding.getRoot();
    }

    private void startAnimation() {
        float sunStartY = mBinding.sun.getTop(); //10.000
        float sunEndY = mBinding.sea.getTop(); //100.000

        ObjectAnimator heightAnimator = ObjectAnimator
                .ofFloat(mBinding.sun, "y", sunStartY, sunEndY)
                .setDuration(4000);
        heightAnimator.setInterpolator(new AccelerateInterpolator());
        heightAnimator.start();
    }
}