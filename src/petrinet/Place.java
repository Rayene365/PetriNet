package petrinet;

/**
 * Represents a place in a Petri Net.
 * A place can hold tokens and is connected to transitions via arcs.
 * Places are the passive elements in a Petri Net that represent conditions or resources.
 * 
 * @author ABBASSI Rayene and BOUZID Adam
 * @version 1.0
 */
public class Place {
    /** The current number of tokens in this place */
    private int tokens;

    /**
     * Constructs a place with the specified initial number of tokens.
     * 
     * @param tokens the initial number of tokens in this place
     */
    public Place(int tokens) {
        this.tokens = tokens;
    }

    /**
     * Adds the specified number of tokens to this place.
     * This is typically called when a transition fires and produces tokens.
     * 
     * @param n the number of tokens to add
     */
    public void addTokens(int n) {
        tokens += n;
    }

    /**
     * Removes the specified number of tokens from this place.
     * This is typically called when a transition fires and consumes tokens.
     * 
     * @param n the number of tokens to remove
     */
    public void removeTokens(int n) {
        tokens -= n;
    }

    /**
     * Returns the current number of tokens in this place.
     * 
     * @return the number of tokens currently in this place
     */
    public int getTokens() {
        return tokens;
    }
    
    /**
     * Returns a string representation of this place.
     * 
     * @return a string containing the current number of tokens
     */
    @Override
    public String toString() {
        return "Place(tokens=" + tokens + ")";
    }
}