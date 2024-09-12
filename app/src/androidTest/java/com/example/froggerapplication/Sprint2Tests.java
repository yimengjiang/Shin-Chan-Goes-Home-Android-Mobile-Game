package com.example.froggerapplication;

import static org.junit.Assert.*;
import org.junit.Test;

public class Sprint2Tests {
    @Test
    public void testMoveUp() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        player.moveUp();

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND - Player.SPEED, player.getY());
    }

    @Test
    public void testMoveUpThenDown() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        player.moveUp();

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND - Player.SPEED, player.getY());

        player.moveDown();
        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());
    }

    @Test
    public void testMoveLeft() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        player.moveLeft();

        assertEquals(Player.INITIAL_X - Player.SPEED, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());
    }

    @Test
    public void testMoveRight() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        player.moveRight();

        assertEquals(Player.INITIAL_X + Player.SPEED, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());
    }


    @Test
    public void testMoveUpOutOfBounds() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        for (int i = 0; i < 1000; i++) {
            player.moveUp();
        }

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.UPPER_Y_BOUND, player.getY());
    }

    @Test
    public void testMoveDownOutOfBounds() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        player.moveDown();

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());
    }

    @Test
    public void testMoveLeftOutOfBounds() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        for (int i = 0; i < 1000; i++) {
            player.moveLeft();
        }

        assertTrue(player.getX() > GameScreen.LEFT_X_BOUND);
        assertTrue(player.getX() < Player.SPEED);
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());
    }

    @Test
    public void testMoveRightOutOfBounds() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        for (int i = 0; i < 1000; i++) {
            player.moveRight();
        }

        assertTrue(player.getX() < GameScreen.RIGHT_X_BOUND);
        assertTrue(player.getX() > GameScreen.RIGHT_X_BOUND - (2 * Player.SPEED));
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());
    }


    @Test
    public void testMoveLeftUpRightDown() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        player.moveLeft();

        assertEquals(Player.INITIAL_X - Player.SPEED, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        player.moveUp();

        assertEquals(Player.INITIAL_X - Player.SPEED, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND - Player.SPEED, player.getY());

        player.moveRight();

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND - Player.SPEED, player.getY());

        player.moveDown();
        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());
    }

    @Test
    public void testMoveRightUpLeftDown() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        player.moveRight();

        assertEquals(Player.INITIAL_X + Player.SPEED, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());

        player.moveUp();

        assertEquals(Player.INITIAL_X + Player.SPEED, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND - Player.SPEED, player.getY());

        player.moveLeft();

        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND - Player.SPEED, player.getY());

        player.moveDown();
        assertEquals(Player.INITIAL_X, player.getX());
        assertEquals(GameScreen.LOWER_Y_BOUND, player.getY());
    }
}