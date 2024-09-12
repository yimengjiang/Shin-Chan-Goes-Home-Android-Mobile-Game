package com.example.froggerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Random;

public class GameScreen extends AppCompatActivity {
    private Context context;
    private Player sprite;

    private ConstraintLayout vehicleRegion;

    private final Handler handler = new Handler(Looper.myLooper());
    private int timeIntervalsPassed = 0;

    private int spawnFactor;
    private boolean onGameScreen = true;


    public static final int UPPER_Y_BOUND = 170;
    public static final int LOWER_Y_BOUND = 2130;
    public static final int LEFT_X_BOUND = 0;
    public static final int RIGHT_X_BOUND = 1440;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        context = findViewById(R.id.game_screen_layout).getContext();
        vehicleRegion = findViewById(R.id.vehicle_region);

        initializeGameDetails();
        initializePlayerAndDetails();
        initializeSpawnFactor();
        initializeAndSetupButtons();
        setupGameBoard();

        initiateLooper();
    }


    /**
     * Retrieves and displays the name, difficult, and number of remaining lives.
     */
    private void initializeGameDetails() {
        TextView nameView = findViewById(R.id.name_value);
        nameView.setText(getIntent().getStringExtra("nameKey"));

        String level = getIntent().getStringExtra("difficultyKey");
        TextView levelView = findViewById(R.id.level_value);
        levelView.setText(level);

        TextView livesView = findViewById(R.id.num_lives);

        switch (level) {
        case "Easy":
            livesView.setText("7");
            break;
        case "Medium":
            livesView.setText("5");
            break;
        case "Hard":
            livesView.setText("3");
            break;
        default:
        }
    }


    /**
     * Retrieves and selected sprite, constructs the Player, and displays the sprite.
     */
    private void initializePlayerAndDetails() {
        this.sprite = new Player(context,
             getSpriteResID(getIntent().getStringExtra("characterKey")),
             Integer.parseInt(((TextView) findViewById(R.id.num_lives)).getText().toString()));

        ConstraintLayout view = findViewById(R.id.game_screen_top_layer);
        view.addView(this.sprite.getImage());
    }

    /**
     * Returns the ID representing the selected sprite.
     *
     * @param sprite the String representation of the selected sprite.
     * @return the resource ID of the selected sprite
     */
    private int getSpriteResID(String sprite) {
        int resID = 0;

        ImageView selectedCharacter = findViewById(R.id.selected_character);

        switch (sprite) {
        case "Xiaoxin":
            resID = R.drawable.xiaoxin;
            selectedCharacter.setImageResource(R.drawable.xiaoxin);
            break;
        case "Xiaobai":
            resID = R.drawable.xiaobai;
            selectedCharacter.setImageResource(R.drawable.xiaobai);
            break;
        case "Nini":
            resID = R.drawable.nini;
            selectedCharacter.setImageResource(R.drawable.nini);
            break;
        default:
            break;
        }

        return resID;
    }


    /**
     * Initializes the spawn factor to determine the rate of vehicle spawning.
     */
    private void initializeSpawnFactor() {
        if (sprite.getLives() == 7) {
            spawnFactor = 12;
        } else if (sprite.getLives() == 5) {
            spawnFactor = 10;
        } else {
            spawnFactor = 8;
        }
    }


    /**
     * Sets the onClick listeners for the four movement buttons.
     */
    private void initializeAndSetupButtons() {
        findViewById(R.id.upButton).setOnClickListener(view -> {
            sprite.moveUp();
            updateDisplayedScore();
        });
        findViewById(R.id.downButton).setOnClickListener(view -> sprite.moveDown());
        findViewById(R.id.leftButton).setOnClickListener(view -> sprite.moveLeft());
        findViewById(R.id.rightButton).setOnClickListener(view -> sprite.moveRight());
    }


    /**
     * Displays the tiles on the screen according to the row.
     */
    private void setupGameBoard() {
        Tile[][] tiles = new Tile[15][10];

        for (int row = 0; row < 15; ++row) {
            if (row == 0) {
                for (int col = 0; col < 10; ++col) {
                    tiles[row][col] = new GoalTile(context);
                }
            } else if (row >= 2 && row <= 5) {
                for (int col = 0; col < 10; ++col) {
                    tiles[row][col] = new RiverTile(context);
                }
            } else if (row == 8) {
                for (int col = 0; col < 10; ++col) {
                    tiles[row][col] = new RoadTile(context, true);
                }
            } else if (row >= 9 && row <= 12) {
                for (int col = 0; col < 10; ++col) {
                    tiles[row][col] = new RoadTile(context);
                }
            } else if (row == 13) {
                for (int col = 0; col < 10; ++col) {
                    tiles[row][col] = new RoadTile(context, false);
                }
            } else { // rows 1, 6, 7, 14
                for (int col = 0; col < 10; ++col) {
                    tiles[row][col] = new SafeTile(context);
                }
            }
        }

        GridLayout tileLayout = findViewById(R.id.tile_layout);
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 10; ++col) {
                tileLayout.addView(tiles[row][col]);
            }
        }
    }


    /**
     * Initiates the looper that is responsible for generating vehicles, moving vehicles,
     * checking for collisions, and delegating tasks accordingly in response to collisions.
     */
    private void initiateLooper() {
        Vehicle.VEHICLES.clear();

        handler.post(new Runnable() {
            @Override
            public void run() {
                generateVehicles();
                Vehicle.moveAllVehicles(vehicleRegion);

                checkForCollision();
                checkGameWon();

                ++timeIntervalsPassed;

                if (onGameScreen) {
                    handler.postDelayed(this, 50);
                }
            }
        });
    }

    /**
     * Generate a land vehicle and a water vehicle and determines
     * whether to add them to the screen based on the spawn factor.
     */
    private void generateVehicles() {
        Vehicle landVehicle = generateLandVehicle();
        Vehicle waterVehicle = generateWaterVehicle();

        if (landVehicle != null) {
            if (timeIntervalsPassed % spawnFactor == 0) {
                vehicleRegion.addView(landVehicle.getImage());
                Vehicle.VEHICLES.add(landVehicle);
            }
        }

        if (waterVehicle != null) {
            if (timeIntervalsPassed % spawnFactor == 0) {
                vehicleRegion.addView(waterVehicle.getImage());
                Vehicle.VEHICLES.add(waterVehicle);
            }
        }
    }

    /**
     * Generates a random land vehicle. If the vehicle generated is too close to another vehicle
     * that is already on the screen, one will be regenerated until the one generated is not too
     * close to another vehicle that is already on the screen. If such a generation is not possible,
     * null is returned and no vehicle should be added to the screen.
     *
     * @return a random land vehicle
     */
    private Vehicle generateLandVehicle() {
        Random random = new Random();
        Vehicle landVehicle = null;

        int generated = 0;
        do {
            switch (random.nextInt(6)) {
            case 0:
                landVehicle = new Cybertruck(context, Direction.RIGHT, 0);
                break;
            case 1:
                landVehicle = new Ferrari(context, Direction.LEFT, 1);
                break;
            case 2:
                landVehicle = new Bike(context, Direction.RIGHT, 2);
                break;
            case 3:
                landVehicle = new Cybertruck(context, Direction.LEFT, 3);
                break;
            case 4:
                landVehicle = new Ferrari(context, Direction.RIGHT, 4);
                break;
            case 5:
                landVehicle = new Bike(context, Direction.LEFT, 5);
                break;
            default:
                break;
            }

            if (generated++ == 36) {
                return null;
            }
        } while (landVehicle.vehicleNearby());

        return landVehicle;
    }

    /**
     * Generates a random water vehicle. If the vehicle generated is too close to another vehicle
     * that is already on the screen, one will be regenerated until the one generated is not too
     * close to another vehicle that is already on the screen. If such a generation is not possible,
     * null will be returned and no vehicle should be added to the screen.
     *
     * @return a random water vehicle
     */
    private Vehicle generateWaterVehicle() {
        Random random = new Random();
        Vehicle waterVehicle = null;

        int generated = 0;
        do {
            switch (random.nextInt(4)) {
            case 0:
                waterVehicle = new Boat(context, Direction.LEFT, 8);
                break;
            case 1:
                waterVehicle = new Yacht(context, Direction.RIGHT, 9);
                break;
            case 2:
                waterVehicle = new Boat(context, Direction.LEFT, 10);
                break;
            case 3:
                waterVehicle = new Yacht(context, Direction.RIGHT, 11);
                break;
            default:
                break;
            }

            if (generated++ == 16) {
                return null;
            }
        } while (waterVehicle.vehicleNearby());

        return waterVehicle;
    }

    /**
     * Checks whether the sprite has had a collision with a land vehicle or the water
     * or if it is off the screen. If it is, the displayed score and lives will be updated
     * accordingly. If there are no remaining lives, the game is over.
     */
    private void checkForCollision() {
        if (sprite.hasCollision() || sprite.isOffScreen()) {
            updateDisplayedScore();
            updateDisplayedLives();

            if (sprite.getLives() == 0) {
                Intent intent = new Intent(this, GameOverScreen.class);
                intent.putExtra("score", sprite.getScore());
                startActivity(intent);
                onGameScreen = false;
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        }
    }

    /**
     * Start GameWinScreen activity if the player has reached one of the goal tiles.
     */
    private void checkGameWon() {
        if (sprite.reachedGoalTile()) {
            Intent intent = new Intent(this, GameWinScreen.class);
            intent.putExtra("score", sprite.getScore());
            intent.putExtra("lives", sprite.getLives());
            startActivity(intent);
            onGameScreen = false;
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }


    /**
     * Updates the displayed score.
     */
    private void updateDisplayedScore() {
        ((TextView) findViewById(R.id.score_value)).setText(String.valueOf(sprite.getScore()));
    }

    /**
     * Updates the displayed number of lives.
     */
    private void updateDisplayedLives() {
        ((TextView) findViewById(R.id.num_lives)).setText(String.valueOf(sprite.getLives()));
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ConfigScreen.class));
        onGameScreen = false;
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}