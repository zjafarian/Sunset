package org.maktab.sunset;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.transition.platform.MaterialContainerTransform;

import org.maktab.sunset.databinding.FragmentSunsetBinding;

public class SunsetFragment extends Fragment {

    private FragmentSunsetBinding mBinding;

    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private int mNightSkyColor;
    private boolean mCheckAnimationStart = false;
    private boolean mCheckAnimationOneEnd = false;
    private boolean mCheckAnimationTwoEnd = true;
    private AnimatorSet animatorSetOne = new AnimatorSet();
    ;
    private AnimatorSet animatorSetTwo = new AnimatorSet();
    ;
    private long totolDuration;

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

        Resources resources = getResources();
        mBlueSkyColor = resources.getColor(R.color.blue_sky);
        mSunsetSkyColor = resources.getColor(R.color.sunset_sky);
        mNightSkyColor = resources.getColor(R.color.night_sky);

        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                animatorSetOne.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mCheckAnimationOneEnd = false;

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mCheckAnimationStart = true;
                        mCheckAnimationOneEnd = true;

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                animatorSetTwo.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mCheckAnimationTwoEnd=false;


                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mCheckAnimationStart=false;
                        mCheckAnimationTwoEnd=true;

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                mCheckAnimationStart = !mCheckAnimationStart;
                if (mCheckAnimationStart && mCheckAnimationTwoEnd) {
                    startAnimationOne();
                } else if (animatorSetOne.isRunning())
                    animatorSetOne.resume();
                else if (mCheckAnimationOneEnd &&  !mCheckAnimationStart)
                    startAnimationTwo();
                else if (animatorSetTwo.isRunning())
                    animatorSetTwo.resume();

            }


        });

        return mBinding.getRoot();
    }


    private void startAnimationOne() {
        float sunStartY = mBinding.sun.getTop(); //10.000
        float sunEndY = mBinding.sea.getTop(); //100.000
        float sunSeaStartY = mBinding.sunSea.getTop();
        float sunSeaEndY = mBinding.sky.getTop();


        ObjectAnimator heightAnimator = ObjectAnimator
                .ofFloat(mBinding.sun, "y", sunStartY, sunEndY)
                .setDuration(4000);
        heightAnimator.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator sunsetAnimator = ObjectAnimator
                .ofInt(mBinding.sky, "backgroundColor", mBlueSkyColor, mSunsetSkyColor)
                .setDuration(4000);
        sunsetAnimator.setEvaluator(new ArgbEvaluator());

        ObjectAnimator nightAnimator = ObjectAnimator
                .ofInt(mBinding.sky, "backgroundColor", mSunsetSkyColor, mNightSkyColor)
                .setDuration(4000);
        nightAnimator.setEvaluator(new ArgbEvaluator());


        ObjectAnimator sunSeaAnimator = ObjectAnimator
                .ofFloat(mBinding.sunSea,"y",sunSeaStartY,sunSeaEndY)
                .setDuration(4000);
        sunSeaAnimator.setInterpolator(new AccelerateInterpolator());






        /*heightAnimator.start();
        sunsetAnimator.start();
        nightAnimator.start();*/


        animatorSetOne
                .play(heightAnimator)
                .with(sunsetAnimator)
                .before(nightAnimator);

        animatorSetOne.play(heightAnimator)
                .with(sunSeaAnimator);




        animatorSetOne.start();




    }


    private void startAnimationTwo() {
        float sunStartY = mBinding.sun.getTop(); //10.000
        float sunEndY = mBinding.sea.getTop(); //100.000
        float sunSeaStartY = mBinding.sunSea.getTop();
        float sunSeaEndY = mBinding.sky.getTop();


        ObjectAnimator reverseHeightAnimator = ObjectAnimator
                .ofFloat(mBinding.sun, "y", sunEndY, sunStartY)
                .setDuration(4000);
        reverseHeightAnimator.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator sunriseAnimator = ObjectAnimator
                .ofInt(mBinding.sky, "backgroundColor", mNightSkyColor, mSunsetSkyColor, mBlueSkyColor)
                .setDuration(4000);
        sunriseAnimator.setEvaluator(new ArgbEvaluator());


        ObjectAnimator sunSeaAnimator = ObjectAnimator
                .ofFloat(mBinding.sunSea,"y",sunSeaEndY,sunSeaStartY)
                .setDuration(4000);
        sunSeaAnimator.setInterpolator(new AccelerateInterpolator());


        animatorSetTwo
                .play(sunriseAnimator)
                .with(reverseHeightAnimator);


        animatorSetTwo.play(sunriseAnimator)
                .with(sunSeaAnimator);

        animatorSetTwo.start();


    }
}