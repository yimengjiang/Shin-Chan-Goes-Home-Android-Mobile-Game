package com.example.froggerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Random;

public class ConfigScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        // hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setupHideKeyboard(getWindow().getDecorView());
        setupHideCursor();
        setupPlayListener();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void setupHideKeyboard(View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                hideSoftKeyboard();
                return false;
            });
        }

        // If a layout container, iterate over children and call setupHideKeyboard recursively.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupHideKeyboard(innerView);
            }
        }

        setupHideCursor();
    }

    private void hideSoftKeyboard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private void setupHideCursor() {
        EditText editText = findViewById(R.id.name_input);
        findViewById(R.id.name_input).setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                editText.setCursorVisible(false);
            }
        });
    }


    public void setupPlayListener() {
        Context context = getApplicationContext();
        Button playButton = findViewById(R.id.play);

        // Send all the take-in values when CONTINUE button is clicked
        playButton.setOnClickListener(view -> {
            Intent intent = new Intent(ConfigScreen.this, GameScreen.class);

            String name =
                    ((EditText) findViewById(R.id.name_input)).getText().toString();
            String difficultyLevel =
                    ((Button) findViewById(R.id.difficulty_button)).getText().toString();
            String character =
                    ((Button) findViewById(R.id.character_button)).getText().toString();

            if (name.isBlank()) {
                Toast.makeText(context, "Please enter a valid name!",
                               Toast.LENGTH_SHORT).show();
                return;
            } else if (difficultyLevel.equals("Difficulty")) {
                Toast.makeText(context, "Please select a difficulty level!",
                               Toast.LENGTH_SHORT).show();
                return;
            } else if (character.equals("Character")) {
                Toast.makeText(context, "Please select a character!",
                               Toast.LENGTH_SHORT).show();
                return;
            }

            intent.putExtra("nameKey", name);
            intent.putExtra("characterKey", character);
            intent.putExtra("difficultyKey", difficultyLevel);

            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        });
    }


    /**
     * Updates the views related to the difficulty option, which include the image and button text.
     *
     * @param clickedDifficultyButton the difficulty button that was clicked
     */
    public void updateDifficultyViews(View clickedDifficultyButton) {
        ImageView difficultyIcon = findViewById(R.id.difficulty_icon);
        Button difficultyButton = (Button) clickedDifficultyButton;

        String difficultyButtonText = difficultyButton.getText().toString();
        switch (difficultyButtonText) {
        case "Easy":
            difficultyIcon.setImageResource(R.drawable.level2);
            difficultyIcon.setContentDescription(getString(R.string.medium));

            difficultyButton.setText(R.string.medium);
            break;
        case "Medium":
            difficultyIcon.setImageResource(R.drawable.level3);
            difficultyIcon.setContentDescription(getString(R.string.hard));

            difficultyButton.setText(R.string.hard);
            break;
        default:
            difficultyIcon.setImageResource(R.drawable.level1);
            difficultyIcon.setContentDescription(getString(R.string.easy));

            difficultyButton.setText(R.string.easy);
            break;
        }
    }

    /**
     * Updates the views related to the character option, which include the image and button text.
     *
     * @param clickedCharacterButton the character button that was clicked
     */
    public void updateCharacterViews(View clickedCharacterButton) {
        ImageView characterIcon = findViewById(R.id.character_icon);
        Button characterButton = (Button) clickedCharacterButton;

        String characterButtonText = characterButton.getText().toString();
        switch (characterButtonText) {
        case "Xiaoxin":
            characterIcon.setImageResource(R.drawable.xiaobai3_large);
            characterIcon.setContentDescription(getString(R.string.xiaobai));

            characterButton.setText(R.string.xiaobai);
            break;
        case "Xiaobai":
            characterIcon.setImageResource(R.drawable.nini_large_sharpened);
            characterIcon.setContentDescription(getString(R.string.nini));

            characterButton.setText(R.string.nini);
            break;
        default:
            characterIcon.setImageResource(R.drawable.xiaoxin_large_sharpened);
            characterIcon.setContentDescription(getString(R.string.xiaoxin));

            characterButton.setText(R.string.xiaoxin);
            break;
        }
    }

    /**
     * Randomly updates the values for name, difficulty, and character.
     *
     * @param clickedRandomizeButton the randomize button that was clicked
     */
    public void randomizeInputs(View clickedRandomizeButton) {
        Random random = new Random();

        // Update the entered name to a random name.
        EditText nameInput = findViewById(R.id.name_input);
        Name[] names = getPossibleNames();
        nameInput.setText(names[random.nextInt(names.length)].toString());


        // Update the toggled difficulty to a random difficulty.
        int randomDifficulty = random.nextInt(3);
        ImageView difficultyIcon = findViewById(R.id.difficulty_icon);
        difficultyIcon.setImageResource(getPossibleDifficultyIcons()[randomDifficulty]);

        Button difficultyButton = findViewById(R.id.difficulty_button);
        difficultyButton.setText(getPossibleDifficultyTexts()[randomDifficulty]);


        // Update the toggled character to a random character.
        int randomCharacter = random.nextInt(3);
        ImageView characterIcon = findViewById(R.id.character_icon);
        characterIcon.setImageResource(getPossibleCharacterIcons()[randomCharacter]);

        Button characterButton = findViewById(R.id.character_button);
        characterButton.setText(getPossibleCharacterTexts()[randomCharacter]);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        WelcomeActivity.TAP_TO_PLAY_BREATHING.start();
    }


    //////////////////////// Getters \\\\\\\\\\\\\\\\\\\\\\\\
    /**
     * Returns a Name array of all the possible values of random
     * Name in the order they are listed in the Name enum.
     *
     * @return Name array of all possible values of Name
     */
    private Name[] getPossibleNames() {
        return Name.values();
    }

    /**
     * Returns an int array of possible difficulty icons in the order
     * the difficulty button changes the image resource of the ImageView.
     *
     * @return int array of possible difficulty icons
     */
    private int[] getPossibleDifficultyIcons() {
        return new int[]{
            R.drawable.level1,
            R.drawable.level2,
            R.drawable.level3
        };
    }

    /**
     * Returns a String array of possible difficulty texts in the
     * order the difficulty button cycles through the difficulties.
     *
     * @return String array of possible difficulty texts
     */
    private String[] getPossibleDifficultyTexts() {
        return new String[] {
            getString(R.string.easy),
            getString(R.string.medium),
            getString(R.string.hard)
        };
    }

    /**
     * Returns a String array of possible character texts in the
     * order the character button cycles through the characters.
     *
     * @return String array of possible character texts
     */
    private String[] getPossibleCharacterTexts() {
        return new String[] {
            getString(R.string.xiaoxin),
            getString(R.string.xiaobai),
            getString(R.string.nini)
        };
    }

    /**
     * Returns an int array of possible character icons in the order
     * the character button changes the image resource of the ImageView.
     *
     * @return int array of possible character icons
     */
    private int[] getPossibleCharacterIcons() {
        return new int[]{
            R.drawable.xiaoxin_large_sharpened,
            R.drawable.xiaobai3_large,
            R.drawable.nini_large_sharpened
        };
    }
}