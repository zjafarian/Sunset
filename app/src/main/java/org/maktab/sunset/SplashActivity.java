package org.maktab.sunset;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LottieAnimationView lottieAnimationView = findViewById(R.id.lottie_success_work);
        //request from server using retrofit. [play animation]
        lottieAnimationView.playAnimation();


        //get the result in observer livedata.[go to home activity and finish this one]
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(SunsetActivity.newIntent(SplashActivity.this));
                finish();
            }
        }, 6000);
    }

    @Override
    public void onBackPressed() {
    }
}