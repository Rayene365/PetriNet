/**
 * Represents a basic arc in a Petri Net.
 * Arcs connect places and transitions with an associated weight that determines
 * the number of tokens consumed or produced during a transition firing.
 * 
 * @author ABBASSI Rayene and BOUZID Adam
 * @version 1.0
 */
public class Arc {
    /** The weight of the arc, representing the number of tokens transferred */
    protected int weight;
    
    /**
     * Constructs an arc with the specified weight.
     * 
     * @param weight the weight of the arc (must be non-negative)
     * @throws IllegalArgumentException if weight is negative
     */
    public Arc(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be >= 0");
        }
        this.weight = weight;
    }
    
    /**
     * Returns the weight of this arc.
     * 
     * @return the current weight of the arc
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Sets the weight of this arc.
     * 
     * @param weight the new weight for the arc (must be non-negative)
     * @throws IllegalArgumentException if weight is negative
     */
    public void setWeight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be >= 0");
        }
        this.weight = weight;
    }
    
    /**
     * Returns a string representation of this arc.
     * 
     * @return a string containing the arc's weight
     */
    @Override
    public String toString() {
        return "Arc(weight=" + weight + ")";
    }
}
