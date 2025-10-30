package petrinet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PetriNetDestructionTest {

    @Test
    void removeTokensUpdatesPlace() {
        PetriNet net = new PetriNet();
        Place place = new Place(4);
        net.addPlace(place);

        net.removeTokens(place, 3);

        assertEquals(1, place.getTokens());
    }

    @Test
    void removeArcPTUpdatesTransition() {
        PetriNet net = new PetriNet();
        Place place = new Place(2);
        Transition transition = new Transition();
        net.addPlace(place);
        net.addTransition(transition);

        ArcPT arcPT = new ArcPT(place, transition, 1);
        net.addArcPT(arcPT);
        assertTrue(transition.getPTs().contains(arcPT));

        net.removeArcPT(arcPT);

        assertFalse(transition.getPTs().contains(arcPT));
        assertFalse(net.describe().contains("P0 -[ArcPT w=1]-> T0"));
    }

    @Test
    void removeArcTPUpdatesTransition() {
        PetriNet net = new PetriNet();
        Place place = new Place(0);
        Transition transition = new Transition();
        net.addPlace(place);
        net.addTransition(transition);

        ArcTP arcTP = new ArcTP(transition, place, 2);
        net.addArcTP(arcTP);
        assertTrue(transition.getTPs().contains(arcTP));

        net.removeArcTP(arcTP);

        assertFalse(transition.getTPs().contains(arcTP));
        assertFalse(net.describe().contains("T0 -[ArcTP w=2]-> P0"));
    }

    @Test
    void removePlaceOrTransitionAfterDetachingArcs() {
        PetriNet net = new PetriNet();
        Place place = new Place(1);
        Transition transition = new Transition();
        net.addPlace(place);
        net.addTransition(transition);

        ArcPT arcPT = new ArcPT(place, transition, 1);
        net.addArcPT(arcPT);
        net.removeArcPT(arcPT);
        net.removePlace(place);

        String snapshot = net.describe();
        assertFalse(snapshot.contains("P0 tokens=1"));

        Place output = new Place(0);
        net.addPlace(output);
        ArcTP arcTP = new ArcTP(transition, output, 1);
        net.addArcTP(arcTP);
        net.removeArcTP(arcTP);
        net.removeTransition(transition);

        snapshot = net.describe();
        assertFalse(snapshot.contains("T0 enabled"));
    }
}