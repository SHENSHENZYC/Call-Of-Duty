package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeaponTest {

	Base base;
    Missile mis;
    RocketLauncher rl;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();
        

        mis = new Missile();
        rl = new RocketLauncher();
    }

    @Test
    void testWeapon() {
        assertEquals(3, mis.getShotLeft());

        // TODO: add more cases
        assertEquals(20, rl.getShotLeft());
    }

    @Test
    void testGetWeaponType() {
        assertEquals("missile", mis.getWeaponType().toLowerCase());

        // TODO: add more cases
        assertEquals("rocketlauncher", rl.getWeaponType().toLowerCase());
    }

    
    @Test
    void testGetShotLeft() {
        mis.shootAt(0, 0, this.base);
        assertEquals(2, mis.getShotLeft());

        // TODO: add more cases
        for (int i = 0; i < 10; i++) {
        	rl.shootAt(1, 1, this.base);
        }
        assertEquals(10, rl.getShotLeft());
    }

    @Test
    void testDecrementShotleft() {
        mis.decrementShotLeft();
        assertEquals(2, mis.getShotLeft());

        // TODO: add more cases
        rl.decrementShotLeft();
        assertEquals(19, rl.getShotLeft());
    }

    @Test
    void testShootAt() {
        mis.shootAt(0, 0, this.base);
        assertTrue(base.getTargetsArray()[0][0].isHitAt(0, 0));
        assertEquals(1, base.getShotsCount());
        
        // TODO: add more cases
        Armory armory = new Armory(base);
        base.placeTargetAt(armory, 0, 0, true);

        Barrack barrack = new Barrack(base);
        base.placeTargetAt(barrack, 0, 4, true);

        SentryTower st = new SentryTower(base);
        base.placeTargetAt(st, 2, 4, true);

        Tank tank = new Tank(base);
        base.placeTargetAt(tank, 1, 3, true);

        OilDrum od = new OilDrum(base);
        base.placeTargetAt(od, 2, 1, true);
        
        mis.shootAt(3, 1, this.base);
        assertTrue(armory.isDestroyed());
        assertTrue(tank.isDestroyed());
        assertEquals(2, base.getShotsCount());
        assertEquals(1, mis.getShotLeft());
    }
}
