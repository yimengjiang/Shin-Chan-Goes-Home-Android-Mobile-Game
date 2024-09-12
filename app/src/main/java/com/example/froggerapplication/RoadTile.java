package com.example.froggerapplication;

import android.content.Context;

/**
 * This class represents a road tile. The player can stand on top of this tile but must watch
 * out for Vehicles that share the road tile with the player and avoid collision.
 */
public class RoadTile extends Tile {
    /**
     * 1-argument RoadTile constructor that defaults the road tile to a middle tile.
     *
     * @param context the context of the RoadTile
     */
    public RoadTile(Context context) {
        super(context, R.drawable.road_tile);
    }

    /**
     * 2-argument RoadTile constructor to create a top road tile or bottom road tile.
     *
     * @param context the context of the RoadTile
     * @param top whether the RoadTile will be on top or bottom of the road
     */
    public RoadTile(Context context, boolean top) {
        super(context, top ? R.drawable.road_tile_top : R.drawable.road_tile_bottom);
    }
}
