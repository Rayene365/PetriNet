package petrinet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PetriNetCreationTest {

    @Test
    void placeKeepsInitialTokens() {
        Place place = new Place(5);
        assertEquals(5, place.getTokens());
    }

    @Test
    void arcPTRegistersOnTransition() {
        PetriNet net = new PetriNet();
        Place place = new Place(3);
        Transition transition = new Transition();

        net.addPlace(place);
        net.addTransition(transition);

        ArcPT arcPT = new ArcPT(place, transition, 2);
        net.addArcPT(arcPT);

        assertEquals(1, transition.getPTs().size());
        assertSame(arcPT, transition.getPTs().get(0));
    }

    @Test
    void arcTPRegistersOnTransition() {
        PetriNet net = new PetriNet();
        Place place = new Place(0);
        Transition transition = new Transition();

        net.addPlace(place);
        net.addTransition(transition);

        ArcTP arcTP = new ArcTP(transition, place, 4);
        net.addArcTP(arcTP);

        assertEquals(1, transition.getTPs().size());
        assertSame(arcTP, transition.getTPs().get(0));
    }

    @Test
    void duplicateArcPTIsRejectedAndNotKeptOnTransition() {
        PetriNet net = new PetriNet();
        Place place = new Place(1);
        Transition transition = new Transition();

        net.addPlace(place);
        net.addTransition(transition);

        ArcPT first = new ArcPT(place, transition, 1);
        net.addArcPT(first);
        ArcPT duplicate = new ArcPT(place, transition, 1);
        net.addArcPT(duplicate);

        assertEquals(1, transition.getPTs().size());
        assertSame(first, transition.getPTs().get(0));

        String inputSection = String.join("\n",
            "  Input:",
            "    P0 -[ArcPT w=1]-> T0"
        );
        assertTrue(net.describe().contains(inputSection));
    }

    @Test
    void duplicateArcTPIsRejectedAndNotKeptOnTransition() {
        PetriNet net = new PetriNet();
        Place place = new Place(0);
        Transition transition = new Transition();

        net.addPlace(place);
        net.addTransition(transition);

        ArcTP first = new ArcTP(transition, place, 1);
        net.addArcTP(first);
        ArcTP duplicate = new ArcTP(transition, place, 1);
        net.addArcTP(duplicate);

        assertEquals(1, transition.getTPs().size());
        assertSame(first, transition.getTPs().get(0));

        String outputSection = String.join("\n",
            "  Output:",
            "    T0 -[ArcTP w=1]-> P0"
        );
        assertTrue(net.describe().contains(outputSection));
    }
}