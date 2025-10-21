import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main implementation of a Petri Net simulation system.
 * This class manages places, transitions, arcs, and provides methods
 * to execute the Petri Net step by step. It supports interactive
 * transition firing and automatic deadlock detection.
 * 
 * @author ABBASSI Rayene and BOUZID Adam
 * @version 1.0
 */
public class PetriNet implements PetriNetInterface{

    /** List of all places in the Petri Net */
    private ArrayList<Place> places;
    
    /** List of all arcs in the Petri Net */
    private ArrayList<Arc> arcs;
    
    /** List of all transitions in the Petri Net */
    private ArrayList<Transition> transitions;
    
    /** Scanner for reading user input during interactive execution */
    private Scanner scanner;
    
    /**
     * Constructs an empty Petri Net with no places, transitions, or arcs.
     */
    public PetriNet(){
        this.places = new ArrayList<Place>();
        this.arcs = new ArrayList<Arc>();
        this.transitions = new ArrayList<Transition>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Adds an arc from a place to a transition to the Petri Net.
     * Prevents duplicate arcs from being added.
     * 
     * @param arcPT the place-to-transition arc to add
     */
    public void addArcPT(ArcPT arcPT) {
        if(arcs.contains(arcPT)){
            System.out.println("ArcPT already exists ");
        }else{
            arcs.add(arcPT);
        }
    }

    /**
     * Adds an arc from a transition to a place to the Petri Net.
     * Prevents duplicate arcs from being added.
     * 
     * @param arcTP the transition-to-place arc to add
     */
    public void addArcTP(ArcTP arcTP) {
        if(arcs.contains(arcTP)){
            System.out.println("ArcTP already exists ");
        }else{
            arcs.add(arcTP);   
        }
    }

    /**
     * Adds a place to the Petri Net.
     * 
     * @param place the place to add
     */
    public void addPlace(Place place) {
        places.add(place);
    }

    /**
     * Adds tokens to a specific place.
     * 
     * @param place the place to add tokens to
     * @param tokens the number of tokens to add
     */
    public void addTokens(Place place, int tokens) {
        place.addTokens(tokens);
    }

    /**
     * Adds a transition to the Petri Net.
     * 
     * @param transition the transition to add
     */
    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    /**
     * Removes an arc from a place to a transition from the Petri Net.
     * 
     * @param arcPT the place-to-transition arc to remove
     */
    public void removeArcPT(ArcPT arcPT) {
        Transition to = arcPT.getTo();
        arcs.remove(arcPT);
        to.removePT(arcPT);
    }

    /**
     * Removes an arc from a transition to a place from the Petri Net.
     * 
     * @param arcTP the transition-to-place arc to remove
     */
    public void removeArcTP(ArcTP arcTP) {
        Transition from = arcTP.getFrom();
        arcs.remove(arcTP);
        from.removeTP(arcTP);
    }

    /**
     * Removes a place from the Petri Net.
     * 
     * @param place the place to remove
     */
    public void removePlace(Place place) {
        places.remove(place);
    }

    /**
     * Removes tokens from a specific place.
     * 
     * @param place the place to remove tokens from
     * @param tokens the number of tokens to remove
     */
    public void removeTokens(Place place, int tokens) {
        place.removeTokens(tokens);
    }

    /**
     * Removes a transition from the Petri Net.
     * 
     * @param transition the transition to remove
     */
    public void removeTransition(Transition transition) {
        transitions.remove(transition);
    }

    /**
     * Sets the weight of an arc.
     * 
     * @param arc the arc to modify
     * @param weight the new weight for the arc
     */
    public void setWeight(Arc arc, int weight) {
        arc.setWeight(weight);
    }

    /**
     * Executes one step of the Petri Net simulation.
     * This method:
     * 1. Finds all enabled transitions
     * 2. If no transitions are enabled, reports a deadlock
     * 3. If one transition is enabled, fires it automatically
     * 4. If multiple transitions are enabled, prompts the user to choose one
     * 
     * The method provides interactive feedback and handles invalid user input.
     */
    public void step() {
        // Find all enabled transitions
        ArrayList<Transition> enabled = new ArrayList<Transition>();
        for (Transition t : transitions){
            if (t.isEnabled()){
                enabled.add(t);
            }
        }
        
        // Handle different cases based on number of enabled transitions
        if (enabled.size() == 0){
            // No transitions can fire - deadlock
            System.out.println("Reached deadlock - no transitions are enabled");
        } else if (enabled.size() == 1){
            // Only one choice - fire automatically
            System.out.println("Only one transition is enabled, firing it automatically...");
            enabled.get(0).fire();
        } else {
            // Multiple choices - ask user to select
            System.out.println("Choose a transition to fire:");
            for (int i = 0; i < enabled.size(); i++) {
                Transition t = enabled.get(i);
                System.out.println((i + 1) + ". " + t.toString());
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
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
    
}
