package com.example.froggerapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

import java.util.Locale;

public class GameOverScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);

        // hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Get the final score from the previous activity
        int score = getIntent().getIntExtra("score", 0);

        // Display the final score on the screen
        TextView scoreText = findViewById(R.id.score_text_lose);
        scoreText.setText(String.format(Locale.US, "Final Score\n%d", score));

        // Set up the restart game button
        Button restartButton = findViewById(R.id.restart_button_lose);
        restartButton.setOnClickListener(view -> {
            // Start the game configuration activity to restart the game
            Intent intent = new Intent(GameOverScreen.this, ConfigScreen.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        });

        // Set up the exit game button
        Button exitButton = findViewById(R.id.exit_button_lose);
        exitButton.setOnClickListener(view -> {
            // Display a confirmation dialog before exiting the game
            AlertDialog.Builder builder = new AlertDialog.Builder(GameOverScreen.this);
            builder.setMessage("Are you sure you want to exit the game?")
                   .setCancelable(false)    // Exit the game
                   .setPositiveButton("Yes", (dialog, id) -> finishAffinity())
                   // Dismiss the dialog and continue playing the game
                   .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ConfigScreen.class));
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}