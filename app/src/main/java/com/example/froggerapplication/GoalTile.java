package com.example.froggerapplication;

import android.content.Context;

/**
 * This class represents a goal tile. The player's objective is to reach this tile.
 */
public class GoalTile extends Tile {
    /**
     * 1-argument GoalTile constructor.
     *
     * @param context the context of the Tile
     */
    public GoalTile(Context context) {
        super(context, R.drawable.goal_tile);
    }
}
