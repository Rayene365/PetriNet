/**
 * Represents a reset arc in a Petri Net.
 * A ResetArc is a special type of input arc that removes all tokens from the source
 * place when the transition fires, regardless of how many tokens are present.
 * The arc is active when the source place has at least one token.
 * 
 * @author ABBASSI Rayene and BOUZID Adam
 * @version 1.0
 */
public class ResetArc extends ArcPT{
    /**
     * Constructs a reset arc from a place to a transition.
     * The weight parameter is ignored as ResetArcs always check for at least 1 token
     * but consume all tokens when firing.
     * 
     * @param from the source place to reset
     * @param to the target transition
     * @param weight ignored parameter (internally set to 1 for activation check)
     */
    public ResetArc(Place from, Transition to, int weight){
        super(from, to, 1); // Weight is always 1 for ResetArc
        this.weight = 1; // Ensure weight is always 1
    } 

    /**
     * Checks if this arc is active.
     * A ResetArc is active when the source place has at least one token.
     * 
     * @return true if the source place has at least one token, false otherwise
     */
    @Override
    public boolean isActive(){
        return from.getTokens() >= weight;
    }
    
    /**
     * Consumes all tokens from the source place.
     * Unlike normal arcs, this removes all tokens regardless of the weight.
     */
    @Override
    public void consume(){
        int allTokens = from.getTokens();
        from.removeTokens(allTokens);
    }
    
    /**
     * Returns a string representation of this reset arc.
     * 
     * @return a string containing the weight and active status
     */
    @Override
    public String toString() {
        return "ResetArc(weight=" + weight + ", active=" + isActive() + ")";
    }
}
