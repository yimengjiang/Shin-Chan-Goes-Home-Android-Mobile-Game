package com.example.froggerapplication;

import android.content.Context;

/**
 * This class represents a Yacht water vehicle.
 * Yachts have slow speed and a unique image.
 */
public class Yacht extends Vehicle {
    /**
     * 3-argument Yacht constructor.
     *
     * @param context   the context of the Yacht
     * @param direction the direction of travel of the Yacht
     * @param row       the row of the water lanes the Yacht will travel on.
     *                  Row 0 is adjacent to the row of safe tiles where the player begins.
     */
    public Yacht(Context context, Direction direction, int row) {
        super(context, direction, row, 12,
              direction == Direction.LEFT ? R.drawable.yacht_expanded_travel_left
                                          : R.drawable.yacht_expanded_travel_right);
    }
}
