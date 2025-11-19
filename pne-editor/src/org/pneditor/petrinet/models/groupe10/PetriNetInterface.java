package org.pneditor.petrinet.models.groupe10;

/**
 * Interface defining the core operations for a Petri Net.
 * This interface provides methods for managing places, transitions, arcs,
 * and executing the Petri Net simulation.
 * 
 * @author ABBASSI Rayene and BOUZID Adam
 * @version 1.0
 */
public interface PetriNetInterface {
    
    // Place management
    
    /**
     * Adds a place to the Petri Net.
     * 
     * @param place the place to add
     */
    void addPlace(Place place);
    
    /**
     * Removes a place from the Petri Net.
     * 
     * @param place the place to remove
     */
    void removePlace(Place place);
    
    // Transition management  
    
    /**
     * Adds a transition to the Petri Net.
     * 
     * @param transition the transition to add
     */
    void addTransition(Transition transition);
    
    /**
     * Removes a transition from the Petri Net.
     * 
     * @param transition the transition to remove
     */
    void removeTransition(Transition transition);
    
    // Arc management
    
    /**
     * Adds an arc from a transition to a place (output arc).
     * 
     * @param arcTP the transition-to-place arc to add
     */
    void addArcTP(ArcTP arcTP);
    
    /**
     * Removes an arc from a transition to a place.
     * 
     * @param arcTP the transition-to-place arc to remove
     */
    void removeArcTP(ArcTP arcTP);
    
    /**
     * Adds an arc from a place to a transition (input arc).
     * 
     * @param arcPT the place-to-transition arc to add
     */
    void addArcPT(ArcPT arcPT);
    
    /**
     * Removes an arc from a place to a transition.
     * 
     * @param arcPT the place-to-transition arc to remove
     */
    void removeArcPT(ArcPT arcPT);
    
    // Execution
    
    /**
     * Executes one step of the Petri Net simulation.
     * Finds all enabled transitions and allows the user to select one to fire.
     * If only one transition is enabled, it fires automatically.
     * If no transitions are enabled, reports a deadlock state.
     */
    void step();
    
    // Token management
    
    /**
     * Adds tokens to a specific place.
     * 
     * @param place the place to add tokens to
     * @param tokens the number of tokens to add
     */
    void addTokens(Place place, int tokens);
    
    /**
     * Removes tokens from a specific place.
     * 
     * @param place the place to remove tokens from
     * @param tokens the number of tokens to remove
     * @throws IllegalArgumentException if the removal would make the place negative
     */
    void removeTokens(Place place, int tokens);
    
    // Arc weight management
    
    /**
     * Sets the weight of an arc.
     * 
     * @param arc the arc to modify
     * @param weight the new weight for the arc
     */
    void setWeight(Arc arc, int weight);

    // Visualization

    /**
     * Produces a textual representation of the current Petri Net state.
     *
     * @return a human readable description of the Petri Net
     */
    String describe();
}
