package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseTest {

	Base base;
    Armory armory;
    Barrack barrack;
    SentryTower st;
    Tank tank;
    OilDrum od;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();

        armory = new Armory(base);
        base.placeTargetAt(armory, 0, 0, true);

        barrack = new Barrack(base);
        base.placeTargetAt(barrack, 0, 4, true);

        st = new SentryTower(base);
        base.placeTargetAt(st, 2, 4, true);

        tank = new Tank(base);
        base.placeTargetAt(tank, 1, 3, true);

        od = new OilDrum(base);
        base.placeTargetAt(od, 2, 1, true);
    }

    @Test
    void testBase() {
        assertEquals(10, base.getTargetsArray().length);

        // TODO: add more cases
        assertEquals(10, base.getTargetsArray()[0].length);
        assertEquals("ground", base.getTargetsArray()[9][9].getTargetName());
    }

    @Test
    void testPlaceAllTargetRandomly() {
        this.base = new Base();
        base.placeAllTargetRandomly();
        List<Target> list = new ArrayList<Target>();
        int headQuarterCount = 0;
        int armoryCount = 0;
        int barracksCount = 0;
        int sentryCount = 0;
        int tanksCount = 0;
        int odCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (base.getTargetsArray()[i][j].getTargetName() != "ground") {
                    if (!list.contains(base.getTargetsArray()[i][j])) {
                        list.add(base.getTargetsArray()[i][j]);
                        switch (base.getTargetsArray()[i][j].getTargetName().toLowerCase()) {
                        case "armory": {
                            armoryCount++;
                            break;
                        }
                        case "headquarter": {
                            headQuarterCount++;
                            break;
                        }
                        case "barrack": {
                            barracksCount++;
                            break;
                        }
                        case "sentrytower": {
                            sentryCount++;
                            break;
                        }
                        case "tank": {
                            tanksCount++;
                            break;
                        }
                        case "oildrum": {
                            odCount++;
                            break;
                        }
                        }
                    }
                }
            }
        }
        assertEquals(list.size(), 18);

        assertEquals(1, headQuarterCount);
        assertEquals(2, armoryCount);
        assertEquals(3, barracksCount);
        assertEquals(4, sentryCount);
        assertEquals(4, tanksCount);
        assertEquals(4, odCount);
    }

    @Test
    void testOkToPlaceTargetAt() {
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 7, false));
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, true));
        assertTrue(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, false));

        // TODO: add more cases
        assertTrue(this.base.okToPlaceTargetAt(new OilDrum(this.base), 2, 0, false));
        assertFalse(this.base.okToPlaceTargetAt(new Tank(this.base), 2, 1, false));
        assertFalse(this.base.okToPlaceTargetAt(new SentryTower(this.base), 2, 2, false));
        assertTrue(this.base.okToPlaceTargetAt(new Barrack(this.base), 3, 0, true));
        assertFalse(this.base.okToPlaceTargetAt(new HeadQuarter(this.base), 8, 8, true));
        
    }
    
    

    @Test
    void testPlaceTargetAt() {
        Target armory = new Armory(base);
        this.base.placeTargetAt(armory, 5, 5, false);
        assertEquals(5, armory.getCoordinate()[0]);
        assertEquals(5, armory.getCoordinate()[1]);
        assertEquals(3, armory.getHit().length);
        assertEquals(2, armory.getHit()[0].length);
        
        // TODO: add more cases
        Target hq = new HeadQuarter(base);
        this.base.placeTargetAt(hq, 9, 0, true);
        assertEquals(9, hq.getCoordinate()[0]);
        assertEquals(0, hq.getCoordinate()[1]);
        assertEquals(1, hq.getHit().length);
        assertEquals(6, hq.getHit()[0].length);
    }
    
    
    @Test
    void testIsOccupied() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 0, 0, true);
        assertTrue(base.isOccupied(0, 0));

        // TODO: add more cases
        assertTrue(base.isOccupied(0, 6));
        assertTrue(base.isOccupied(2, 1));
        assertTrue(base.isOccupied(0, 0));
        assertFalse(base.isOccupied(1, 4));
        assertFalse(base.isOccupied(2, 2));
    }

    @Test
    void testShootAt() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 5, 5, true);

        base.shootAt(5, 5);
        assertTrue(arm.isHitAt(5, 5));

        // TODO: add more cases
        base.shootAt(2, 1);
        assertTrue(barrack.isHitAt(0, 5));
        assertFalse(barrack.isHitAt(0, 6));
        assertTrue(tank.isDestroyed());
        
    }

    @Test
    void testIsGameOver() {

        assertFalse(base.isGameOver(new RocketLauncher(), new Missile()));

        // TODO: add more cases
        RocketLauncher rl = new RocketLauncher();
        Missile mis = new Missile();
        base.setDestroyedTargetCount(18);
        assertTrue(base.isGameOver(rl, mis));
        
        base.setDestroyedTargetCount(0);
        for (int i = 0; i < 20; i++) {
        	rl.shootAt(0, 0, base);
        }
        for (int i = 0; i < 3; i++) {
        	mis.shootAt(9, 9, base);
        }
        assertTrue(base.isGameOver(rl, mis));
    }

    @Test
    void testWin() {
        assertFalse(this.base.win());

        // TODO: add more cases
        base.setDestroyedTargetCount(18);
        assertTrue(base.win());
    }

    @Test
    void testIncrementAndGetShotsCount() {

        assertEquals(0, base.getShotsCount());
        base.incrementShotsCount();
        assertEquals(1, base.getShotsCount());

        // TODO: add more cases
        for (int i = 0; i < 10; i++) {
        	base.incrementShotsCount();
        }
        assertEquals(11, base.getShotsCount());
    }

    @Test
    void testSetAndGetDestroyedTargetCount() {
        base.setDestroyedTargetCount(10);
        assertEquals(10, base.getDestroyedTargetCount());

        // TODO: add more cases
        base.setDestroyedTargetCount(18);
        assertEquals(18, base.getDestroyedTargetCount());
        assertTrue(base.isGameOver(new RocketLauncher(), new Missile()));
    }

    @Test
    void testGetDestroyedTargetCount() {
        
        assertEquals(0, base.getDestroyedTargetCount());
                
        // TODO: add more cases
        base.shootAt(2, 1);
        assertEquals(4, base.getDestroyedTargetCount());
        
        base.placeTargetAt(new SentryTower(this.base), 9, 9, false);
        base.shootAt(9, 9);
        assertEquals(5, base.getDestroyedTargetCount());
    }


    @Test
    void testGetTargetsArray() {
        assertEquals(10, base.getTargetsArray().length);

        // TODO: add more cases
        assertEquals("armory", base.getTargetsArray()[0][0].getTargetName().toLowerCase());
    }


}
