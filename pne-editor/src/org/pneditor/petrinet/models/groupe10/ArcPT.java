package org.pneditor.petrinet.models.groupe10;

import java.util.Objects;

/**
 * Represents an arc from a Place to a Transition (input arc).
 * This arc type consumes tokens from a place when its connected transition fires.
 * The arc is active (can fire) when the source place has at least as many tokens
 * as the arc's weight.
 * 
 * @author ABBASSI Rayene and BOUZID Adam
 * @version 1.0
 */
public class ArcPT extends Arc {
    /** The target transition of this arc */
    protected Transition to;
    
    /** The source place of this arc */
    protected Place from;

    /**
     * Constructs an arc from a place to a transition.
     * Automatically registers this arc with the target transition.
     * 
     * @param from the source place
     * @param to the target transition
     * @param weight the number of tokens consumed when the transition fires
     */
    public ArcPT(Place from, Transition to, int weight){
        super(weight);
        this.to = to;
        this.from = from;
        // Add this arc to the transition's input arcs list
        to.addArcPT(this);
    }
    
    /**
     * Consumes tokens from the source place.
     * This method is called when the connected transition fires.
     */
    public void consume(){
        from.removeTokens(weight);
    }

    /**
     * Checks if this arc is active (can fire).
     * An arc is active when the source place has at least as many tokens
     * as the arc's weight.
     * 
     * @return true if the source place has enough tokens, false otherwise
     */
    public boolean isActive(){
        return from.getTokens() >= weight;
    }

    /**
     * Returns the target transition of this arc.
     * 
     * @return the transition this arc points to
     */
    public Transition getTo() {
        return to;
    }

    /**
     * Returns the source place of this arc.
     * 
     * @return the place this arc originates from
     */
    public Place getFrom() {
        return from;
    }
    
    /**
     * Returns a string representation of this arc.
     * 
     * @return a string containing the weight and active status
     */
    @Override
    public String toString() {
        return "ArcPT(weight=" + weight + ", active=" + isActive() + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ArcPT other = (ArcPT) obj;
        return (from == other.from   && to == other.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), from, to);
    }
}