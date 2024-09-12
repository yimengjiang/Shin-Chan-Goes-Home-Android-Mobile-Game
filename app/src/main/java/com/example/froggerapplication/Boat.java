package com.example.froggerapplication;

import android.content.Context;

/**
 * This class represents a Boat water vehicle.
 * Boats have moderate speed and a unique image.
 */
public class Boat extends Vehicle {
    /**
     * 3-argument Boat constructor.
     *
     * @param context   the context of the Boat
     * @param direction the direction of travel of the Boat
     * @param row       the row of the water lanes the Boat will travel on.
     *                  Row 0 is adjacent to the row of safe tiles where the player begins.
     */
    public Boat(Context context, Direction direction, int row) {
        super(context, direction, row, 16,
              direction == Direction.LEFT ? R.drawable.boat_travel_left
                                          : R.drawable.boat_travel_right);
    }


    /**
     * Boat overrides vehicleNearly so that they can be positioned right next to each other.
     * Returns a boolean whether this Boat is at least one tile away from another Vehicle.
     *
     * @return whether this Vehicle is at least one tile away from another Vehicle
     */
    @Override
    public boolean vehicleNearby() {
        for (int i = 0; i < VEHICLES.size(); ++i) {
            Vehicle v = VEHICLES.get(i);

            if (!(v instanceof Boat)) {
                continue;
            }

            if (!(getY() == v.getY() && direction == v.getDirection())) {
                continue;
            }

            // same row, same direction
            if (direction == Direction.LEFT) {
                if (x - 140 < v.getX()) {
                    return true;
                }
            } else if (x + 140 > v.getX()) {
                return true;
            }
        }

        return false;
    }
}
