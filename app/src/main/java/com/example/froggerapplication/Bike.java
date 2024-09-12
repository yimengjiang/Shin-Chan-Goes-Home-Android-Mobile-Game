package com.example.froggerapplication;

import android.content.Context;

/**
 * This class represents a Bike land vehicle.
 * Bikes have fast speed and a unique image.
 */
public class Bike extends Vehicle {
    /**
     * 3-argument Bike constructor.
     *
     * @param context   the context of the Bike
     * @param direction the direction of travel of the Bike
     * @param row       the row of the road lanes the Bike will travel on.
     *                  Row 0 is adjacent to the row of safe tiles where the player begins.
     *
     */
    public Bike(Context context, Direction direction, int row) {
        super(context, direction, row, 12,
              direction == Direction.LEFT ? R.drawable.bike_travel_left
                                          : R.drawable.bike_travel_right);
    }
}
