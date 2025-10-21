public interface PetriNetInterface {
    
    // Place management
    void addPlace(Place place);
    void removePlace(Place place);
    
    // Transition management  
    void addTransition(Transition transition);
    void removeTransition(Transition transition);
    
    // Arc management
    void addArcTP(ArcTP arcTP);
    void removeArcTP(ArcTP arcTP);
    void addArcPT(ArcPT arcPT);
    void removeArcPT(ArcPT arcPT);
    
    // Execution
    void step();
    
    // Token management
    void addTokens(Place place, int tokens);
    void removeTokens(Place place, int tokens);
    
    // Arc weight management
    void setWeight(Arc arc, int weight);
}