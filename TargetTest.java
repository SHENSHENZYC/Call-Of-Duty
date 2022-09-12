package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TargetTest {
	
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
    void testTarget() {

        // Armory
        assertEquals(2, armory.getHit().length);
        assertEquals(3, armory.getHit()[0].length);

        // Barrack
        assertEquals(1, barrack.getHit().length);
        assertEquals(3, barrack.getHit()[0].length);


        // TODO: add more cases
        assertEquals(1, st.getHit().length);
        assertEquals(1, st.getHit()[0].length);
        
        assertEquals(1, tank.getHit().length);
        assertEquals(1, tank.getHit()[0].length);
        
        assertEquals(1, od.getHit().length);
        assertEquals(1, od.getHit()[0].length);
    }

    @Test
    void testToString() {
        assertEquals("O", st.toString());
        assertEquals("T", tank.toString());

        // TODO: add more cases
        tank.getShot(1, 3);
        assertEquals("T", tank.toString());
        tank.getShot(1, 3);
        assertEquals("X", tank.toString());
        assertEquals("X", armory.toString());
        assertEquals("X", od.toString());
        assertEquals("X", st.toString());
        assertEquals("O", barrack.toString());
    }

    @Test
    void testGetTargetName() {
        assertEquals("tank", tank.getTargetName().toLowerCase());
        assertEquals("sentrytower", st.getTargetName().toLowerCase());
        assertEquals("oildrum", od.getTargetName().toLowerCase());

        // TODO: add more cases
        assertEquals("armory", armory.getTargetName().toLowerCase());
        assertEquals("barrack", barrack.getTargetName().toLowerCase());
    }

    @Test
    void testExplode() {
        assertFalse(armory.isDestroyed());
        od.explode();
        assertTrue(armory.isDestroyed());

        // TODO: add more cases
        assertFalse(barrack.isDestroyed());
        assertTrue(st.isDestroyed());
    }


    @Test
    void testGetShot() {
        Target am = new Armory(this.base);
        assertTrue(this.base.okToPlaceTargetAt(am, 5, 5, false));
        this.base.placeTargetAt(am, 5, 5, false);
        am.getShot(5, 6);
        assertEquals(1, am.getHit()[0][1]);
        
        // TODO: add more cases
        Target od2 = new OilDrum(this.base);
        assertTrue(this.base.okToPlaceTargetAt(od2, 6, 3, true));
        this.base.placeTargetAt(od2, 6, 3, true);
        od2.getShot(6, 3);
        assertEquals(1, od2.getHit()[0][0]);
        assertEquals(1, am.getHit()[0][0]);
        assertEquals(1, am.getHit()[1][0]);
        assertEquals(1, am.getHit()[2][0]);
        assertEquals(0, am.getHit()[1][1]);
        assertEquals(0, am.getHit()[2][1]);
    }

    @Test
    void testIsDestroyed() {
        assertFalse(od.isDestroyed());
        od.getShot(2, 1);
        assertTrue(od.isDestroyed());
        assertTrue(tank.isDestroyed());

        // TODO: add more cases
        assertFalse(barrack.isDestroyed());
        barrack.getShot(0, 6);
        assertTrue(barrack.isDestroyed());

    }

    @Test
    void testIsHitAt() {
        Target am = new Armory(this.base);
        assertTrue(this.base.okToPlaceTargetAt(am, 5, 5, false));
        this.base.placeTargetAt(am, 5, 5, true);
        assertFalse(am.isHitAt(5, 5));
        am.getShot(5, 5);
        assertTrue(am.isHitAt(5, 5));

        // TODO: add more cases
        od.getShot(2, 1);
        assertTrue(barrack.isHitAt(0, 4));
        assertTrue(barrack.isHitAt(0, 5));
        assertTrue(st.isHitAt(2, 4));
    }
}
