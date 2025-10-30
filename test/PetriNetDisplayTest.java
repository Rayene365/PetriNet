import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PetriNetDisplayTest {

    @Test
    void describeListsPlacesTransitionsAndArcs() {
        PetriNet net = new PetriNet();
        Place p0 = new Place(2);
        Place p1 = new Place(0);
        net.addPlace(p0);
        net.addPlace(p1);

        Transition t0 = new Transition();
        net.addTransition(t0);

        ArcPT arcPT = new ArcPT(p0, t0, 1);
        net.addArcPT(arcPT);
        ArcTP arcTP = new ArcTP(t0, p1, 2);
        net.addArcTP(arcTP);

        String expected = String.join("\n",
            "Places:",
            "  P0 tokens=2",
            "  P1 tokens=0",
            "Transitions:",
            "  T0 enabled=true in=1 out=1",
            "Arcs:",
            "  Input:",
            "    P0 -[ArcPT w=1]-> T0",
            "  Output:",
            "    T0 -[ArcTP w=2]-> P1"
        );

        assertEquals(expected, net.describe());
    }

    @Test
    void describeHonoursSpecialArcTypes() {
        PetriNet net = new PetriNet();
        Place source = new Place(0);
        Place sink = new Place(3);
        net.addPlace(source);
        net.addPlace(sink);

        Transition t = new Transition();
        net.addTransition(t);

        ZeroArc zeroArc = new ZeroArc(source, t, 99);
        net.addArcPT(zeroArc);
        ResetArc resetArc = new ResetArc(sink, t, 99);
        net.addArcPT(resetArc);
        ArcTP arcTP = new ArcTP(t, source, 1);
        net.addArcTP(arcTP);

        String description = net.describe();

        String expectedInput = String.join("\n",
            "  Input:",
            "    P0 -[ZeroArc w=0]-> T0",
            "    P1 -[ResetArc w=1]-> T0"
        );

        String expectedOutput = String.join("\n",
            "  Output:",
            "    T0 -[ArcTP w=1]-> P0"
        );

        assertTrue(description.contains(expectedInput));
        assertTrue(description.contains(expectedOutput));
    }
}
