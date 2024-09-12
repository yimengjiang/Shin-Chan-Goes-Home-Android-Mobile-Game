package com.example.froggerapplication;

import android.content.Context;

/**
 * This class represents a Ferrari land vehicle.
 * Ferraris have fast speed and a unique image.
 */
public class Ferrari extends Vehicle {
    /**
     * 3-argument Ferrari constructor.
     *
     * @param context   the context of the Ferrari
     * @param direction the direction of travel of the Ferrari
     * @param row       the row of the road lanes the Ferrari will travel on.
     *                  Row 0 is adjacent to the row of safe tiles where the player begins.
     */
    public Ferrari(Context context, Direction direction, int row) {
        super(context, direction, row, 20,
              direction == Direction.LEFT ? R.drawable.ferrari_travel_left
                                          : R.drawable.ferrari_travel_right);
    }
}
