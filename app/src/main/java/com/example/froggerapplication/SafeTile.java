package com.example.froggerapplication;

import android.content.Context;

/**
 * This class represents a safe tile. The player cannot lose a life while on this tile.
 */
public class SafeTile extends Tile {
    /**
     * 1-argument SafeTile constructor.
     *
     * @param context the context of the SafeTile
     */
    public SafeTile(Context context) {
        super(context, R.drawable.safe_tile);
    }
}
