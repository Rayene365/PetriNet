import java.util.ArrayList;

/**
 * Represents a transition in a Petri Net.
 * A transition is an active component that can fire when all of its input arcs are active.
 * When a transition fires, it consumes tokens from input places and produces tokens
 * to output places according to the arc weights.
 * 
 * @author PetriNet Project
 * @version 1.0
 */
public class Transition {
    /** List of output arcs (from this transition to places) */
    private ArrayList<ArcTP> ArcTPs;
    
    /** List of input arcs (from places to this transition) */
    private ArrayList<ArcPT> ArcPTs;
    
    /**
     * Constructs a new transition with empty arc lists.
     */
    public Transition() {
        this.ArcTPs = new ArrayList<ArcTP>();
        this.ArcPTs = new ArrayList<ArcPT>();
    }
    
    /**
     * Adds an output arc to this transition.
     * 
     * @param arc the arc from this transition to a place
     */
    public void addArcTP(ArcTP arc){
        ArcTPs.add(arc);
    }

    /**
     * Adds an input arc to this transition.
     * 
     * @param arc the arc from a place to this transition
     */
    public void addArcPT(ArcPT arc){
        ArcPTs.add(arc);
    }
    
    /**
     * Returns the list of output arcs (transition to place).
     * 
     * @return list of output arcs
     */
    public ArrayList<ArcTP> getTPs() {
        return ArcTPs;
    }
    
    /**
     * Returns the list of input arcs (place to transition).
     * 
     * @return list of input arcs
     */
    public ArrayList<ArcPT> getPTs() {
        return ArcPTs;
    }
    
    /**
     * Checks if this transition is enabled (can fire).
     * A transition is enabled when all of its input arcs are active,
     * meaning all source places have enough tokens.
     * 
     * @return true if the transition can fire, false otherwise
     */
    public boolean isEnabled() {
        for (ArcPT arc : ArcPTs) {
            if (!arc.isActive()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Fires this transition if it is enabled.
     * First consumes tokens from all input places, then produces tokens
     * to all output places according to the arc weights.
     * 
     * @throws IllegalStateException if the transition is not enabled
     */
    public void fire() {
        if (!isEnabled()) {
            throw new IllegalStateException("Transition is not enabled");
        }
        
        // Consume tokens from input places
        for (ArcPT arc : ArcPTs) {
            arc.consume();
        }
        
        // Produce tokens to output places
        for (ArcTP arc : ArcTPs) {
            arc.produce();
        }
    }
    
    /**
     * Returns a string representation of this transition.
     * 
     * @return a string containing the number of input/output arcs and enabled status
     */
    @Override
    public String toString() {
        return "Transition(inputArcs=" + ArcPTs.size() + ", outputArcs=" + ArcTPs.size() + ", enabled=" + isEnabled() + ")";
    }
}