package com.example.froggerapplication;

import static org.junit.Assert.*;

import org.junit.Test;

public class Sprint3Tests {
    @Test
    public void testScoreCorrectRow0() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 0);
        assertEquals(20, player.getScore());
    }

    @Test
    public void testScoreCorrectRow1() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 1);
        assertEquals(45, player.getScore());
    }

    @Test
    public void testScoreCorrectRow2() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 2);
        assertEquals(55, player.getScore());
    }

    @Test
    public void testScoreCorrectRow3() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 3);
        assertEquals(75, player.getScore());
    }

    @Test
    public void testScoreCorrectRow4() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 4);
        assertEquals(100, player.getScore());
    }

    @Test
    public void testScoreCorrectRow5() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 5);
        assertEquals(110, player.getScore());
    }

    @Test
    public void testScoreCorrectRow6() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 6);
        assertEquals(110, player.getScore());
    }

    @Test
    public void testScoreCorrectRow7() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 7);
        assertEquals(110, player.getScore());
    }

    @Test
    public void testScoreCorrectRow8() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 8);
        assertEquals(130, player.getScore());
    }

    @Test
    public void testScoreCorrectRow9() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 9);
        assertEquals(140, player.getScore());
    }

    @Test
    public void testScoreCorrectRow10() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 10);
        assertEquals(160, player.getScore());
    }

    @Test
    public void testScoreCorrectRow11() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 11);
        assertEquals(170, player.getScore());
    }

    @Test
    public void testScoreCorrectRow12() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 12);
        assertEquals(170, player.getScore());
    }

    @Test
    public void testScoreCorrectRow13() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint3Tests.movePlayerToRow(player, 13);
        assertEquals(220, player.getScore());
    }


    @Test
    public void testCybertruckMoveDirectionLeftRow0() {
        Vehicle cybertruck = new Cybertruck(null, Direction.LEFT, 0);
        int expectedInitialX = GameScreen.RIGHT_X_BOUND;
        assertEquals(expectedInitialX, cybertruck.getX());
        assertEquals(1970, cybertruck.getY());

        testVehicleMovement(cybertruck, expectedInitialX);
    }

    @Test
    public void testCybertruckMoveDirectionRightRow0() {
        Vehicle cybertruck = new Cybertruck(null, Direction.RIGHT, 0);
        int expectedInitialX = GameScreen.LEFT_X_BOUND - (2 * Tile.WIDTH);
        assertEquals(expectedInitialX, cybertruck.getX());
        assertEquals(1970, cybertruck.getY());

        testVehicleMovement(cybertruck, expectedInitialX);
    }

    @Test
    public void testCybertruckMoveDirectionLeftRow3() {
        Vehicle cybertruck = new Cybertruck(null, Direction.LEFT, 3);
        int expectedInitialX = GameScreen.RIGHT_X_BOUND;
        assertEquals(expectedInitialX, cybertruck.getX());
        assertEquals(1550, cybertruck.getY());

        testVehicleMovement(cybertruck, expectedInitialX);
    }

    @Test
    public void testCybertruckMoveDirectionRightRow3() {
        Vehicle cybertruck = new Cybertruck(null, Direction.RIGHT, 3);
        int expectedInitialX = GameScreen.LEFT_X_BOUND - (2 * Tile.WIDTH);
        assertEquals(expectedInitialX, cybertruck.getX());
        assertEquals(1550, cybertruck.getY());

        testVehicleMovement(cybertruck, expectedInitialX);
    }


    @Test
    public void testFerrariMoveDirectionLeftRow1() {
        Vehicle ferrari = new Ferrari(null, Direction.LEFT, 0);
        int expectedInitialX = GameScreen.RIGHT_X_BOUND;
        assertEquals(expectedInitialX, ferrari.getX());
        assertEquals(1970, ferrari.getY());

        testVehicleMovement(ferrari, expectedInitialX);
    }

    @Test
    public void testFerrariMoveDirectionRightRow1() {
        Vehicle ferrari = new Ferrari(null, Direction.RIGHT, 0);
        int expectedInitialX = GameScreen.LEFT_X_BOUND - Tile.WIDTH;
        assertEquals(expectedInitialX, ferrari.getX());
        assertEquals(1970, ferrari.getY());

        testVehicleMovement(ferrari, expectedInitialX);
    }

    @Test
    public void testFerrariMoveDirectionLeftRow4() {
        Vehicle ferrari = new Ferrari(null, Direction.LEFT, 3);
        int expectedInitialX = GameScreen.RIGHT_X_BOUND;
        assertEquals(expectedInitialX, ferrari.getX());
        assertEquals(1550, ferrari.getY());

        testVehicleMovement(ferrari, expectedInitialX);
    }

    @Test
    public void testFerrariMoveDirectionRightRow4() {
        Vehicle ferrari = new Ferrari(null, Direction.RIGHT, 3);
        int expectedInitialX = GameScreen.LEFT_X_BOUND - Tile.WIDTH;
        assertEquals(expectedInitialX, ferrari.getX());
        assertEquals(1550, ferrari.getY());

        testVehicleMovement(ferrari, expectedInitialX);
    }


    @Test
    public void testBikeMoveDirectionLeftRow2() {
        Vehicle bike = new Bike(null, Direction.LEFT, 0);
        int expectedInitialX = GameScreen.RIGHT_X_BOUND;
        assertEquals(expectedInitialX, bike.getX());
        assertEquals(1970, bike.getY());

        testVehicleMovement(bike, expectedInitialX);
    }

    @Test
    public void testBikeMoveDirectionRightRow2() {
        Vehicle bike = new Bike(null, Direction.RIGHT, 0);
        int expectedInitialX = GameScreen.LEFT_X_BOUND - Tile.WIDTH;
        assertEquals(expectedInitialX, bike.getX());
        assertEquals(1970, bike.getY());

        testVehicleMovement(bike, expectedInitialX);
    }

    @Test
    public void testBikeMoveDirectionLeftRow5() {
        Vehicle bike = new Bike(null, Direction.LEFT, 3);
        int expectedInitialX = GameScreen.RIGHT_X_BOUND;
        assertEquals(expectedInitialX, bike.getX());
        assertEquals(1550, bike.getY());

        testVehicleMovement(bike, expectedInitialX);
    }

    @Test
    public void testBikeMoveDirectionRightRow5() {
        Vehicle bike = new Bike(null, Direction.RIGHT, 3);
        int expectedInitialX = GameScreen.LEFT_X_BOUND - Tile.WIDTH;
        assertEquals(expectedInitialX, bike.getX());
        assertEquals(1550, bike.getY());

        testVehicleMovement(bike, expectedInitialX);
    }



    private static void movePlayerToRow(Player player, int row) {
        for (int i = 0; i < row + 1; ++i) {
            player.moveUp();
        }
    }

    private static void testVehicleMovement(Vehicle vehicle, int expectedInitialX) {
        int movements = 0;
        while (!vehicle.isOffScreen()) {
            vehicle.move();
            assertEquals(expectedInitialX + (++movements * vehicle.getSpeed()), vehicle.getX());
        }
    }
}
