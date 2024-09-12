package com.example.froggerapplication;

import android.content.Context;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract superclass represents a vehicle in general.
 */
public abstract class Vehicle {
    protected final Direction direction; // the direction of travel
    protected final ImageView image; // the image corresponding to the vehicle

    private final int speed; // delta x per time interval

    protected int x; // the x position of the vehicle
    private final int y; // the y position of the vehicle


    // list of all vehicles being processed; ArrayList because more access, less modification
    public static final List<Vehicle> VEHICLES = new ArrayList<>();



    /**
     * 4-argument Vehicle constructor.
     * An ImageView for the vehicle is created only if the context is not null.
     *
     * @param context   the context of the Vehicle
     * @param direction the direction of travel of the Vehicle
     * @param row       the row of the road lanes the Vehicle will travel on.
     *                  Row 0 is adjacent to the row of safe tiles where the player begins.
     * @param speed     delta x per time interval
     * @param resID     the ID of the image for the Vehicle
     */
    public Vehicle(Context context, Direction direction, int row, int speed, int resID) {
        this.direction = direction;
        this.speed = this.direction == Direction.LEFT ? -speed : speed;

        x = this.direction == Direction.LEFT ? GameScreen.RIGHT_X_BOUND
                                             : isLarge() ? -(2 * Tile.WIDTH) : -Tile.WIDTH;
        y = 1970 - (row * 140);

        if (context != null) {
            image = new ImageView(context);
            image.setImageResource(resID);
            image.setX(x);
            image.setY(y);
        } else {
            image = null;
        }
    }


    /**
     * Moves the Vehicle in the x-coordinate by amount speed in the direction of travel.
     * More specific functionality is defined by each subclass.
     */
    public void move() {
        x += speed;

        if (image != null) {
            image.setX(x);
        }
    }

    /**
     * Returns a boolean whether this Vehicle is not visible on the screen.
     *
     * @return whether this Vehicle is not visible on the screen
     */
    public boolean isOffScreen() {
        return isSmall() ? (x < GameScreen.LEFT_X_BOUND - Tile.WIDTH
                                || x > GameScreen.RIGHT_X_BOUND)
                         : (x < GameScreen.LEFT_X_BOUND - (2 * Tile.WIDTH)
                                || x > GameScreen.RIGHT_X_BOUND);
    }

    /**
     * Returns a boolean whether this Vehicle is at least one tile away from another Vehicle.
     *
     * @return whether this Vehicle is at least one tile away from another Vehicle
     */
    public boolean vehicleNearby() {
        for (int i = 0; i < VEHICLES.size(); ++i) {
            Vehicle v = VEHICLES.get(i);

            if (!(getY() == v.getY() && direction == v.getDirection())) {
                continue;
            }

            // same row, same direction
            if (direction == Direction.LEFT) {
                if (isSmall() && x - 280 < v.getX()) {
                    return true;
                } else if (isLarge() && x - 420 < v.getX()) {
                    return true;
                }
            } else if (x + 420 > v.getX()) {
                return true;
            }
        }

        return false;
    }


    /**
     * Returns a boolean representing whether this Vehicle travels on land
     *
     * @return whether this Vehicle travels on land
     */
    public boolean isLand() {
        return this instanceof Bike || this instanceof Cybertruck || this instanceof Ferrari;
    }

    /**
     * Returns a boolean representing whether this Vehicle travels on water.
     *
     * @return whether this Vehicle travels on water
     */
    public boolean isWater() {
        return this instanceof Boat || this instanceof Yacht;
    }

    /**
     * Returns a boolean representing whether this Vehicle is a small vehicle.
     *
     * @return whether this Vehicle is a small vehicle
     */

    public boolean isSmall() {
        return this instanceof Bike || this instanceof Ferrari || this instanceof Boat;
    }

    /**
     * Returns a boolean representing whether this Vehicle is a large vehicle.
     *
     * @return whether this Vehicle is a large vehicle
     */

    public boolean isLarge() {
        return this instanceof Cybertruck || this instanceof Yacht;
    }


    /**
     * Getter for direction of Vehicle.
     *
     * @return the direction of the Vehicle
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Getter for the image.
     *
     * @return the ImageView associated with the Vehicle
     */
    public ImageView getImage() {
        return image;
    }

    /**
     * Getter for speed.
     *
     * @return the speed of the Vehicle
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Getter for the x-coordinate of the Vehicle.
     *
     * @return the x-coordinate of the Vehicle
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y-coordinate of the Vehicle.
     *
     * @return the y-coordinate of the Vehicle
     */
    public int getY() {
        return y;
    }



    /**
     * Moves all vehicles according to their speed in their direction of travel.
     * If a Vehicle travels off the screen, they are removed from the region.
     *
     * @param backingRegion the region in which Vehicle images are added to
     */
    public static void moveAllVehicles(ConstraintLayout backingRegion) {
        for (int i = 0; i < VEHICLES.size(); ++i) {
            Vehicle v = VEHICLES.get(i);

            v.move();

            if (v.isOffScreen()) {
                VEHICLES.remove(v); // equals not overridden to maintain reference equality
                --i;

                if (backingRegion != null) {
                    backingRegion.removeView(v.image); // remove from the ConstraintLayout
                }
            }
        }
    }
}
