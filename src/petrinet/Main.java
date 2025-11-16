package petrinet;

public class Main {
    public static void main(String[] args) {
        PetriNet net = new PetriNet();
        
        Place p1 = new Place(5);
        Place p2 = new Place(0);
        Transition t = new Transition();
        
        net.addPlace(p1);
        net.addPlace(p2);
        net.addTransition(t);
        
        new ArcPT(p1, t, 1);
        new ArcTP(t, p2, 1);
        
        net.step(); // Exécution interactive
    }
}