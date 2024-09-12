package com.example.froggerapplication;

import android.content.Context;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * This abstract class represents the sprite
 */
public class Player {
    private int x = Player.INITIAL_X;
    private int y = GameScreen.LOWER_Y_BOUND;
    private int maxYReached = GameScreen.LOWER_Y_BOUND;
    private boolean onBoat = false;

    private int score = 0;
    private int lives;

    private ImageView image;


    public static final int INITIAL_X = 575;
    public static final int SPEED = Tile.WIDTH;
    public static final int WIDTH = 105;
    public static final int HEIGHT = 105;



    /**
     * 2-argument Player constructor.
     * The position defaults to the initial position.
     * An ImageView for the Player is created only if the context is not null.
     *
     * @param context the context of the Player
     * @param resID   the ID of the image for the Player
     * @param lives   the number of lives remaining of the Player
     */
    public Player(Context context, int resID, int lives) {
        this.lives = lives;

        if (context != null) {
            image = new ImageView(context);
            image.setImageResource(resID);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setLayoutParams(new ConstraintLayout.LayoutParams(Player.WIDTH, Player.HEIGHT));

            image.setX(x);
            image.setY(y);
        }
    }

    /**
     * Returns a boolean whether this Player is not visible on the screen.
     *
     * @return whether this Player is not visible on the screen
     */
    public boolean isOffScreen() {
        return x + Player.WIDTH < GameScreen.LEFT_X_BOUND || x > GameScreen.RIGHT_X_BOUND;
    }


    /**
     * Returns a boolean representing whether a collision has occurred.
     * If a collision has occurred, the score is reset to 0, lives decremented, and position reset.
     *
     * @return whether a water collision has occurred.
     */
    public boolean hasCollision() {
        if (!(hasLandVehicleCollision() || hasWaterCollision())) {
            return false;
        }

        // reset position and decrement lives
        if (lives > 0) {
            --lives;
        }

        this.x = Player.INITIAL_X;
        this.y = GameScreen.LOWER_Y_BOUND;

        if (image != null) {
            image.setX(x);
            image.setY(y);
        }

        score = 0;

        return true;
    }

    /**
     * Returns a boolean representing whether a land vehicle collision has occurred.
     *
     * @return whether a land vehicle collision has occurred.
     */
    private boolean hasLandVehicleCollision() {
        if (!onLand()) {
            return false;
        }

        for (Vehicle v : Vehicle.VEHICLES) {
            if (!v.isLand()) { // not land vehicle
                continue;
            }

            if (v.getY() != y - 20) { // not in the row
                continue;
            }

            int vehicleWidth = v.isSmall() ? Tile.WIDTH : Tile.WIDTH * 2;
            if (x < v.getX() + vehicleWidth - 20 // left of P past right of V
                && x - 20 + Player.WIDTH > v.getX()) { // right of P past left of V
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a boolean representing whether a water collision has occurred.
     *
     * @return whether a water collision has occurred.
     */
    private boolean hasWaterCollision() {
        if (!onWater()) {
            return false;
        }

        for (Vehicle v : Vehicle.VEHICLES) {
            if (!v.isWater()) {
                continue;
            }

            if (v.getY() != y - 20) {
                continue;
            }

            if (v instanceof Boat) {
                if (x >= v.x - 60 && x <= v.x + Tile.WIDTH - 45) {
                    moveWithVehicle(v);     // Player can hang 60px off left, 60px off right
                    return false;
                }
            } else if (v instanceof Yacht) {
                if (x >= v.x - 60 && x <= v.x + Tile.WIDTH * 2 - 45) {
                    moveWithVehicle(v);     // Player can hang 60px off left, 60px off right
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Adjusts the Player's position by the speed of the vehicle.
     *
     * @param vehicle the vehicle to move with
     */
    private void moveWithVehicle(Vehicle vehicle) {
        if (onBoat) {
            this.x += vehicle.getSpeed();
        } else {
            onBoat = true;
        }

        if (image != null) {
            image.setX(x);
        }
    }


    /**
     * Moves the player up by one tile and ensures it remains visible on the screen.
     */
    public void moveUp() {
        if (y - SPEED >= GameScreen.UPPER_Y_BOUND) {
            y -= SPEED;

            if (image != null) {
                image.setY(y);
            }

            updateMaxY();
        }
    }

    /**
     * Moves the player down by one tile and ensures it remains visible on the screen.
     */
    public void moveDown() {
        if (y + SPEED <= GameScreen.LOWER_Y_BOUND) {
            y += SPEED;

            if (image != null) {
                image.setY(y);
            }
        }
    }

    /**
     * Moves the player left by one tile and ensures it remains visible on the screen.
     */
    public void moveLeft() {
        if (x - SPEED >= GameScreen.LEFT_X_BOUND) {
            x -= SPEED;

            if (image != null) {
                image.setX(x);
            }
        }
    }

    /**
     * Moves the player right by one tile and ensures it remains visible on the screen.
     */
    public void moveRight() {
        if (x + SPEED + Player.WIDTH < GameScreen.RIGHT_X_BOUND) {
            x += SPEED;
            if (image != null) {
                image.setX(x);
            }
        }
    }

    private void updateMaxY() {
        if (y < maxYReached) {
            maxYReached = y;

            increaseScore();
        }
    }

    /**
     * Increases the score depending on the obstacle that the Player passed.
     */
    private void increaseScore() {
        switch (y) {        // case formula: 1990 - (row * 140)
        case 1990:          // row 0 - the first row of vehicles
        case 1570:          // row 3
        case 870:           // row 8 / Boat
        case 590:           // row 10 / Boat
            score += 20;    // Cybertruck
            break;
        case 1850:          // row 1
        case 1430:          // row 4
            score += 25;    // Ferrari
            break;
        case 1710:          // row 2
        case 1290:          // row 5
        case 730:           // row 9 / Yacht
        case 450:           // row 11 / Yacht
            score += 10;    // Bike
            break;
        case 170:           // row 13
            score += 50;    // GoalTile
            break;
        default:            // row 6, 7, 12: safe tiles, no points
            break;
        }
    }


    /**
     * Returns a boolean representing whether the Player is on land.
     *
     * @return whether the Player is on land
     */

    private boolean onLand() {
        return y < 2130 && y >= 1290;
    }
    /**
     * Returns a boolean representing whether the Player is on water.
     *
     * @return whether the Player is on water
     */
    private boolean onWater() {
        return y >= 450 && y < 1010;
    }

    /**
     * Returns a boolean representing whether the Player has reached the goal tile.
     *
     * @return whether the Player has reached the goal tile
     */
    public boolean reachedGoalTile() {
        return y < 310;
    }


    /**
     * Getter for the x-coordinate of the Player.
     *
     * @return the x-coordinate of the Player
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y-coordinate of the Player.
     *
     * @return the y-coordinate of the Player
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for score.
     *
     * @return the Player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter for lives.
     *
     * @return the Player's remaining lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Getter for the image.
     *
     * @return the ImageView associated with the Player
     */
    public ImageView getImage() {
        return image;
    }
}