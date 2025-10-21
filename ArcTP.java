/**
 * Represents an arc from a Transition to a Place (output arc).
 * This arc type produces tokens to a place when its connected transition fires.
 * Output arcs always allow the transition to fire (they don't impose conditions).
 * 
 * @author PetriNet Project
 * @version 1.0
 */
public class ArcTP extends Arc {
    /** The source transition of this arc */
    protected Transition from;
    
    /** The target place of this arc */
    protected Place to;

    /**
     * Constructs an arc from a transition to a place.
     * Automatically registers this arc with the source transition.
     * 
     * @param from the source transition
     * @param to the target place
     * @param weight the number of tokens produced when the transition fires
     */
    public ArcTP(Transition from, Place to, int weight){
        super(weight);
        this.to = to;
        this.from = from;
        // Add this arc to the transition's output arcs list
        from.addArcTP(this);
    }
    
    /**
     * Produces tokens to the target place.
     * This method is called when the connected transition fires.
     */
    public void produce(){
        to.addTokens(weight);
    }

    /**
     * Returns the target place of this arc.
     * 
     * @return the place this arc points to
     */
    public Place getTo() {
        return to;
    }

    /**
     * Returns the source transition of this arc.
     * 
     * @return the transition this arc originates from
     */
    public Transition getFrom() {
        return from;
    }
    
    /**
     * Returns a string representation of this arc.
     * 
     * @return a string containing the arc's weight
     */
    @Override
    public String toString() {
        return "ArcTP(weight=" + weight + ")";
    }
}
