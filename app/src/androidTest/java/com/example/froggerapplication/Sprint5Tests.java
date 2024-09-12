package com.example.froggerapplication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Sprint5Tests {
    @Before
    public void resetVehicles() {
        Vehicle.VEHICLES.clear();
    }



    @Test
    public void testBoatInitialPositionCorrectLeftRow8() {
        Boat boat = new Boat(null, Direction.LEFT, 8);
        assertEquals(1970 - (8 * 140), boat.getY());
        assertEquals(1440, boat.getX());
    }

    @Test
    public void testYachtInitialPositionCorrectRightRow9() {
        Yacht yacht = new Yacht(null, Direction.RIGHT, 9);
        assertEquals(1970 - (9 * 140), yacht.getY());
        assertEquals(-280, yacht.getX());
    }

    @Test
    public void testBoatInitialPositionCorrectLeftRow10() {
        Boat boat = new Boat(null, Direction.LEFT, 10);
        assertEquals(1970 - (10 * 140), boat.getY());
        assertEquals(1440, boat.getX());
    }

    @Test
    public void testYachtInitialPositionCorrectRightRow11() {
        Yacht yacht = new Yacht(null, Direction.RIGHT, 11);
        assertEquals(1970 - (11 * 140), yacht.getY());
        assertEquals(-280, yacht.getX());
    }

    @Test
    public void testBoatOnScreenFunctionality() {
        Boat boat = new Boat(null, Direction.LEFT, 8);
        assertFalse(boat.isOffScreen());

        for (int i = 0; i < 1000; i++) {
            boat.move();
        }
        assertTrue(boat.isOffScreen());
    }

    @Test
    public void testYachtOnScreenFunctionality() {
        Yacht yacht = new Yacht(null, Direction.RIGHT, 9);
        assertFalse(yacht.isOffScreen());

        for (int i = 0; i < 1000; i++) {
            yacht.move();
        }
        assertTrue(yacht.isOffScreen());
    }

    @Test
    public void testBoatMoveLeftFunctionality() {
        Boat boat = new Boat(null, Direction.LEFT, 8);
        int movements = 0;
        while (!boat.isOffScreen()) {
            boat.move();
            assertEquals(1440 + ((long) ++movements * boat.getSpeed()), boat.getX());
        }

        assertTrue(boat.isOffScreen());
        assertTrue(boat.getX() < GameScreen.LEFT_X_BOUND - Tile.WIDTH);
    }

    @Test
    public void testYachtMoveRightFunctionality() {
        Yacht yacht = new Yacht(null, Direction.RIGHT, 8);
        int movements = 0;
        while (!yacht.isOffScreen()) {
            yacht.move();
            assertEquals((GameScreen.LEFT_X_BOUND - (2 * Tile.WIDTH))
                              + ((long) ++movements * yacht.getSpeed()),
                         yacht.getX());
        }

        assertTrue(yacht.isOffScreen());
        assertTrue(yacht.getX() > GameScreen.RIGHT_X_BOUND);
    }


    @Test
    public void testWaterCollisionRow8() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint5Tests.movePlayerTo(player, 870);

        assertTrue(player.hasCollision());
    }

    @Test
    public void testWaterCollisionRow9() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint5Tests.movePlayerTo(player, 730);

        assertTrue(player.hasCollision());
    }

    @Test
    public void testWaterCollisionRow10() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint5Tests.movePlayerTo(player, 590);

        assertTrue(player.hasCollision());
    }

    @Test
    public void testWaterCollisionRow11() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint5Tests.movePlayerTo(player, 450);

        assertTrue(player.hasCollision());
    }


    @Test
    public void testPlayersRidesOnBoatRow8() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint5Tests.generateVehicles();

        testRidingOnWaterVehicle(player, 8);
    }

    @Test
    public void testPlayersRidesOnYachtRow9() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint5Tests.generateVehicles();

        testRidingOnWaterVehicle(player, 9);
    }

    @Test
    public void testPlayersRidesOnBoatRow10() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint5Tests.generateVehicles();

        testRidingOnWaterVehicle(player, 10);
    }

    @Test
    public void testPlayersRidesOnYachtRow11() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint5Tests.generateVehicles();

        testRidingOnWaterVehicle(player, 11);
    }


    @Test
    public void testGoalTileAddsCorrectPoints() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);

        Sprint5Tests.movePlayerTo(player, 450);
        int scoreAtRow11 = player.getScore();

        Sprint5Tests.movePlayerTo(player, 170);
        int scoreAtGoalTile = player.getScore();

        int diff = scoreAtGoalTile - scoreAtRow11;
        assertEquals(50, diff);
    }



    private static void generateVehicles() {
        Vehicle vehicle = null;

        for (int row = 8; row <= 11; ++row) {
            switch (row) {
            case 8:
                vehicle = new Boat(null, Direction.LEFT, 8);
                break;
            case 9:
                vehicle = new Yacht(null, Direction.RIGHT, 9);
                break;
            case 10:
                vehicle = new Boat(null, Direction.LEFT, 10);
                break;
            case 11:
                vehicle = new Yacht(null, Direction.RIGHT, 11);
                break;
            default:
                break;
            }

            Vehicle.VEHICLES.add(vehicle);
        }
    }

    private static void movePlayerTo(Player p, int y) {
        while (p.getY() != y) {
            p.moveUp();
        }
    }

    private static Vehicle getRidingVehicle(Player player) {
        for (Vehicle v : Vehicle.VEHICLES) {
            if (!v.isWater()) {
                continue;
            }

            if (v.getY() != player.getY() - 20) {
                continue;
            }

            int x = player.getX();

            if (v instanceof Boat && (x >= v.x - 60 && x <= v.x + Tile.WIDTH - 45)) {
                return v;
            } else if (v instanceof Yacht && (x >= v.x - 60 && x <= v.x + Tile.WIDTH * 2 - 45)) {
                return v;
            }
        }

        return null;
    }

    private static void testRidingOnWaterVehicle(Player player, int row) {
        int rowY = 2130 - (140 * (row + 1));

        // Move player to specified row, verify collision with water until Player is floating
        Sprint5Tests.movePlayerTo(player, rowY);
        while (player.hasCollision()) {
            Vehicle.moveAllVehicles(null);
            Sprint5Tests.movePlayerTo(player, rowY);
        }
        Vehicle.moveAllVehicles(null);

        // Player should be on water vehicle now
        assertFalse(player.hasCollision());
        Vehicle.moveAllVehicles(null);

        Vehicle ridingVehicle = getRidingVehicle(player);
        assertNotNull(ridingVehicle);
        int diff = Math.abs(player.getX() - ridingVehicle.getX());

        // Verify that player stays on the boat while water vehicle is on screen
        while (!player.hasCollision()) {
            Vehicle.moveAllVehicles(null);

            assertEquals(diff, Math.abs(player.getX() - ridingVehicle.getX()));
        }
        Vehicle.moveAllVehicles(null);

        // Water vehicle is no longer on screen; Verify player was reset to initial position
        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());
    }
}
