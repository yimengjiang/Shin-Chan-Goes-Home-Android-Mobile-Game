package com.example.froggerapplication;

import android.content.Context;

/**
 * This class represents a Cybertruck land vehicle.
 * Cybertrucks have moderate speed and a unique image.
 */
public class Cybertruck extends Vehicle {
    /**
     * 3-argument Cybertruck constructor.
     *
     * @param context   the context of the Cybertruck
     * @param direction the direction of travel of the Cybertruck
     * @param row       the row of the road lanes the Cybertruck will travel on.
     *                  Row 0 is adjacent to the row of safe tiles where the player begins.
     */
    public Cybertruck(Context context, Direction direction, int row) {
        super(context, direction, row, 16,
              direction == Direction.LEFT ? R.drawable.cybertruck_expanded_travel_left
                                          : R.drawable.cybertruck_expanded_travel_right);
    }
}