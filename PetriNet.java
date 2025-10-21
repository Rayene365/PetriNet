import java.util.ArrayList;
import java.util.Scanner;

public class PetriNet implements PetriNetInterface{

    private ArrayList<Place> places;
    private ArrayList<Arc> arcs;
    private ArrayList<Transition> transitions;
    private Scanner scanner;
    
    public PetriNet(){
        this.places = new ArrayList<Place>();
        this.arcs = new ArrayList<Arc>();
        this.transitions = new ArrayList<Transition>();
        this.scanner = new Scanner(System.in);
    }


    public void addArcPT(ArcPT arcPT) {
        if(arcs.contains(arcPT)){
            System.out.println("ArcPT already exists ");
        }else{
            arcs.add(arcPT);
        }
    }

    public void addArcTP(ArcTP arcTP) {
        if(arcs.contains(arcTP)){
            System.out.println("ArcPT already exists ");
        }else{
            arcs.add(arcTP);   
        }
    }

    public void addPlace(Place place) {
        places.add(place);
    }

    public void addTokens(Place place, int tokens) {
        place.addTokens(tokens);
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public void removeArcPT(ArcPT arcPT) {
        arcs.remove(arcPT);
    }

    public void removeArcTP(ArcTP arcTP) {
        arcs.remove(arcTP);
    }

    public void removePlace(Place place) {
        places.remove(place);
    }

    public void removeTokens(Place place, int tokens) {
        place.removeTokens(tokens);
    }

    public void removeTransition(Transition transition) {
        transitions.remove(transition);
    }

    public void setWeight(Arc arc, int weight) {
        arc.setWeight(weight);
    }

    public void step() {
        ArrayList<Transition> enabled = new ArrayList<Transition>();
        for (Transition t : transitions){
            if (t.isEnabled()){
                enabled.add(t);
            }
        }
        
        if (enabled.size() == 0){
            System.out.println("Reached deadlock - no transitions are enabled");
        } else if (enabled.size() == 1){
            System.out.println("Only one transition is enabled, firing it automatically...");
            enabled.get(0).fire();
        } else {
            System.out.println("Choose a transition to fire:");
            for (int i = 0; i < enabled.size(); i++) {
                Transition t = enabled.get(i);
                System.out.println((i + 1)+t.toString());
            }
            
            System.out.print("Enter your choice (1-" + enabled.size() + "): ");
            
            try {
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= enabled.size()) {
                    enabled.get(choice - 1).fire();
                    System.out.println("Transition " + (choice - 1) + " fired successfully!");
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + enabled.size());
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    
}
