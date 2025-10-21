public class ArcPT extends Arc {
    protected Transition to;
    protected Place from;

    public ArcPT(Place from, Transition to, int weight){
        super(weight);
        this.to = to;
        this.from = from;
        // Add this arc to the transition's input arcs list
        to.addArcPT(this);
    }
    
    public void consume(){
        from.removeTokens(weight);
    }

    public boolean isActive(){
        return from.getTokens() >= weight;
    }

    public Transition getTo() {
        return to;
    }

    public Place getFrom() {
        return from;
    }
    
    @Override
    public String toString() {
        return "ArcPT(weight=" + weight + ", active=" + isActive() + ")";
    }
}
