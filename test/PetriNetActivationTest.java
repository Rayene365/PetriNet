import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PetriNetActivationTest {

    @Test
    void simpleTransitionRequiresSufficientTokens() {
        Place source = new Place(1);
        Transition transition = new Transition();
        ArcPT arcPT = new ArcPT(source, transition, 2);

        assertFalse(transition.isEnabled());

        source.addTokens(1);
        assertTrue(transition.isEnabled());

        arcPT.consume();
        assertEquals(0, source.getTokens());
    }

    @Test
    void transitionFireMovesTokensToOutputs() {
        Place input = new Place(3);
        Place output = new Place(0);

        Transition transition = new Transition();
        new ArcPT(input, transition, 2);
        new ArcTP(transition, output, 1);

        assertTrue(transition.isEnabled());
        transition.fire();

        assertEquals(1, input.getTokens());
        assertEquals(1, output.getTokens());
    }

    @Test
    void fireFailsWhenTransitionNotEnabled() {
        Place input = new Place(0);
        Transition transition = new Transition();
        new ArcPT(input, transition, 1);

        assertFalse(transition.isEnabled());
        assertThrows(IllegalStateException.class, transition::fire);
    }

    @Test
    void multiInputTransitionNeedsAllPlaces() {
        Place p0 = new Place(1);
        Place p1 = new Place(0);
        Place output = new Place(0);

        Transition transition = new Transition();
        new ArcPT(p0, transition, 1);
        new ArcPT(p1, transition, 2);
        new ArcTP(transition, output, 3);

        assertFalse(transition.isEnabled());

        p1.addTokens(1);
        assertFalse(transition.isEnabled());

        p1.addTokens(1);
        assertTrue(transition.isEnabled());

        transition.fire();

        assertEquals(0, p0.getTokens());
        assertEquals(0, p1.getTokens());
        assertEquals(3, output.getTokens());
    }

    @Test
    void zeroAndResetArcsAffectEnabling() {
        Place zeroGuard = new Place(1);
        Place resetSource = new Place(2);
        Place output = new Place(0);

        Transition transition = new Transition();
        ZeroArc zeroArc = new ZeroArc(zeroGuard, transition, 0);
        ResetArc resetArc = new ResetArc(resetSource, transition, 0);
        new ArcTP(transition, output, 1);

        assertFalse(transition.isEnabled());

        zeroGuard.removeTokens(1);
        assertTrue(transition.isEnabled());

        transition.fire();

        assertEquals(0, zeroGuard.getTokens());
        assertEquals(0, resetSource.getTokens());
        assertEquals(1, output.getTokens());

        assertTrue(zeroArc.isActive());
        assertFalse(resetArc.isActive());
    }
}
