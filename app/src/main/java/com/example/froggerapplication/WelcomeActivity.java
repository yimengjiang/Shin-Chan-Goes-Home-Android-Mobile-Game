package com.example.froggerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    public static final AnimatorSet TAP_TO_PLAY_BREATHING = new AnimatorSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // hide the action bar and status bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        startBreathingInfoAnimation();
        startScreenTouchListener();
    }


    /**
     * Transitions to the next activity when the touch is released.
     */
    private void startScreenTouchListener() {
        View rootView = getWindow().getDecorView().getRootView();

        rootView.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                TAP_TO_PLAY_BREATHING.pause();

                view.performClick();
                Intent intent = new Intent(this, ConfigScreen.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }

            return true;
        });
    }

    /**
     * Defines and starts the breathing animation for the tap to play instruction.
     */
    private void startBreathingInfoAnimation() {
        TextView tapNote = findViewById(R.id.tapNote);

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(tapNote, "alpha", 0, 1, 1);
        fadeIn.setDuration(5000);

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(tapNote, "alpha", 1, 0);
        fadeOut.setDuration(1500);

        TAP_TO_PLAY_BREATHING.play(fadeOut).before(fadeIn);
        TAP_TO_PLAY_BREATHING.play(fadeIn).after(fadeOut);
        TAP_TO_PLAY_BREATHING.setStartDelay(3000);

        TAP_TO_PLAY_BREATHING.setInterpolator(new LinearInterpolator());
        TAP_TO_PLAY_BREATHING.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                TAP_TO_PLAY_BREATHING.start();
            }
        });

        TAP_TO_PLAY_BREATHING.start();
    }
}