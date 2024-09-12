package com.example.froggerapplication;

import android.content.Context;

/**
 * This abstract class represents a tile in general and is a subtype of AppCombatImageView.
 */
public abstract class Tile extends androidx.appcompat.widget.AppCompatImageView {
    public static final int WIDTH = 140;


    /**
     * 2-argument Tile constructor.
     *
     * @param context the context of the Tile
     * @param resID   the ID of the image for the Tile
     */
    public Tile(Context context, int resID) {
        super(context);
        setImageResource(resID);
    }
}