package com.example.froggerapplication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Sprint4Tests {
    @Before
    public void resetVehicles() {
        Vehicle.VEHICLES.clear();
    }

    
    
    @Test
    public void testNoWaterCollisionMiddleSafeTile() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 1010);
        assertFalse(player.hasCollision());
    }

    @Test
    public void testNoWaterCollisionTopSafeTile() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 310);

        assertFalse(player.hasCollision());
    }

    @Test
    public void testWaterCollisionFirstWaterTileFromBottom() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 870);

        assertTrue(player.hasCollision());
    }

    @Test
    public void testWaterCollisionMiddleWaterTile() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 730);

        assertTrue(player.hasCollision());
    }

    @Test
    public void testWaterCollisionLastWaterTileFromBottom() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 450);

        assertTrue(player.hasCollision());
    }



    @Test
    public void testNoVehicleCollisionBottomSafeTile() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 2130);


        Sprint4Tests.generateVehicles();

        while (Vehicle.VEHICLES.size() > 0) {
            assertFalse(player.hasCollision());
            Vehicle.moveAllVehicles(null);
        }

        assertFalse(player.hasCollision());
    }

    @Test
    public void testNoVehicleCollisionMiddleSafeTile() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 1150);

        Sprint4Tests.generateVehicles();

        while (Vehicle.VEHICLES.size() > 0) {
            assertFalse(player.hasCollision());
            Vehicle.moveAllVehicles(null);
        }

        assertFalse(player.hasCollision());
    }

    @Test
    public void testVehicleCollisionCyberTruckRow0() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 1990);

        Sprint4Tests.generateVehicles();

        boolean collision = false;
        while (Vehicle.VEHICLES.size() > 0) {
            if (player.hasCollision()) {
                collision = true;
                break;
            }

            Vehicle.moveAllVehicles(null);
        }

        assertTrue(collision);
    }

    @Test
    public void testVehicleCollisionFerrariRow1() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 1850);

        Sprint4Tests.generateVehicles();

        boolean collision = false;
        while (Vehicle.VEHICLES.size() > 0) {
            if (player.hasCollision()) {
                collision = true;
                break;
            }

            Vehicle.moveAllVehicles(null);
        }

        assertTrue(collision);
    }

    @Test
    public void testVehicleCollisionBikeRow2() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 1710);

        Sprint4Tests.generateVehicles();

        boolean collision = false;
        while (Vehicle.VEHICLES.size() > 0) {
            if (player.hasCollision()) {
                collision = true;
                break;
            }

            Vehicle.moveAllVehicles(null);
        }

        assertTrue(collision);
    }

    @Test
    public void testVehicleCollisionCyberTruckRow3() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 1570);

        Sprint4Tests.generateVehicles();

        boolean collision = false;
        while (Vehicle.VEHICLES.size() > 0) {
            if (player.hasCollision()) {
                collision = true;
                break;
            }

            Vehicle.moveAllVehicles(null);
        }

        assertTrue(collision);
    }

    @Test
    public void testVehicleCollisionFerrariRow4() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 1430);

        Sprint4Tests.generateVehicles();

        boolean collision = false;
        while (Vehicle.VEHICLES.size() > 0) {
            if (player.hasCollision()) {
                collision = true;
                break;
            }

            Vehicle.moveAllVehicles(null);
        }

        assertTrue(collision);
    }

    @Test
    public void testVehicleCollisionBikeRow5() {
        Player player = new Player(null, R.drawable.xiaoxin, 1);
        Sprint4Tests.movePlayerTo(player, 1290);

        Sprint4Tests.generateVehicles();

        boolean collision = false;
        while (Vehicle.VEHICLES.size() > 0) {
            if (player.hasCollision()) {
                collision = true;
                break;
            }

            Vehicle.moveAllVehicles(null);
        }

        assertTrue(collision);
    }



    private static void generateVehicles() {
        Vehicle vehicle = null;

        for (int row = 0; row < 6; ++row) {
            switch (row) {
            case 0:
                vehicle = new Cybertruck(null, Direction.RIGHT, 0);
                break;
            case 1:
                vehicle = new Ferrari(null, Direction.LEFT, 1);
                break;
            case 2:
                vehicle = new Bike(null, Direction.RIGHT, 2);
                break;
            case 3:
                vehicle = new Cybertruck(null, Direction.LEFT, 3);
                break;
            case 4:
                vehicle = new Ferrari(null, Direction.RIGHT, 4);
                break;
            case 5:
                vehicle = new Bike(null, Direction.LEFT, 5);
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
}
