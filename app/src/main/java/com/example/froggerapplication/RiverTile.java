package com.example.froggerapplication;

import android.content.Context;

/**
 * This class represents a river tile.
 * The player must not stand on this tile without being on top of a floating object.
 */
public class RiverTile extends Tile {
    /**
     * 1-argument RiverTile constructor.
     *
     * @param context the context of the RiverTile
     */
    public RiverTile(Context context) {
        super(context, R.drawable.river_tile);
    }
}
