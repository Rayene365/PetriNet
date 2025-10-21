/**
 * Represents a zero-test arc in a Petri Net.
 * A ZeroArc is a special type of input arc that is only active when the source
 * place has exactly zero tokens. It does not consume any tokens when the transition fires.
 * 
 * @author ABBASSI Rayene and BOUZID Adam
 * @version 1.0
 */
public class ZeroArc extends ArcPT{
    /**
     * Constructs a zero-test arc from a place to a transition.
     * The weight parameter is ignored as ZeroArcs always have weight 0.
     * 
     * @param from the source place to test
     * @param to the target transition
     * @param weight ignored parameter (weight is always 0)
     */
    public ZeroArc(Place from, Transition to, int weight){
        super(from, to, 0); // Weight is always 0 for ZeroArc
        this.weight = 0; // Ensure weight is always 0
    }

    /**
     * Checks if this arc is active.
     * A ZeroArc is active only when the source place has exactly 0 tokens.
     * 
     * @return true if the source place is empty, false otherwise
     */
    @Override
    public boolean isActive(){
        return from.getTokens() == 0; // ZeroArc is active when place has 0 tokens
    }
    
    /**
     * Consumes tokens from the source place.
     * ZeroArcs do not consume any tokens, so this method does nothing.
     */
    @Override
    public void consume(){
        // ZeroArc doesn't consume tokens
    }
    
    /**
     * Returns a string representation of this zero arc.
     * 
     * @return a string containing the weight and active status
     */
    @Override
    public String toString() {
        return "ZeroArc(weight=" + weight + ", active=" + isActive() + ")";
    }
}
