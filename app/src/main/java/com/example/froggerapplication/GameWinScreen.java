package com.example.froggerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class GameWinScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_win_screen);

        // hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Get the final score from the previous activity
        int score = getIntent().getIntExtra("score", 0);
        int lives = getIntent().getIntExtra("lives", 0);

        // Display the final score on the screen
        TextView scoreText = findViewById(R.id.score_text_win);
        scoreText.setText(String.format(Locale.US, "Final Score\n%d", score));

        // display congratulatory message
        TextView congratsTextView = findViewById(R.id.congrats_text_view);
        if (lives >= 2) {
            congratsTextView.setText(R.string.champion);
        } else {
            congratsTextView.setText(R.string.winner);
        }

        // Set up the ObjectAnimators for the animation
        ObjectAnimator scaleXAnimator =
                ObjectAnimator.ofFloat(congratsTextView, View.SCALE_X, 0.5f, 1);
        ObjectAnimator scaleYAnimator =
                ObjectAnimator.ofFloat(congratsTextView, View.SCALE_Y, 0.5f, 1);
        ObjectAnimator alphaAnimator =
                ObjectAnimator.ofFloat(congratsTextView, View.ALPHA, 0, 1);

        // Set up the AnimatorSet to play the animations together
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator);
        animatorSet.setDuration(1000); // Set the duration of the animation in milliseconds

        // Start the animation
        animatorSet.start();

        // Set up the restart game button
        Button restartButton = findViewById(R.id.restart_button_win);
        restartButton.setOnClickListener(view -> {
            // Start the game configuration activity to restart the game
            Intent intent = new Intent(GameWinScreen.this, ConfigScreen.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        });

        // Set up the exit game button
        Button exitButton = findViewById(R.id.exit_button_win);
        exitButton.setOnClickListener(view -> {
            // Display a confirmation dialog before exiting the game
            AlertDialog.Builder builder = new AlertDialog.Builder(GameWinScreen.this);
            builder.setMessage("Are you sure you want to exit the game?")
                   .setCancelable(false)       // Exit the game
                   .setPositiveButton("Yes", (dialog, id) -> finishAffinity())
                   // Dismiss the dialog and continue playing the game
                   .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
            AlertDialog alert = builder.create();
            alert.show();
        });
    }
}