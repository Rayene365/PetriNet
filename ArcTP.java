public class ArcTP extends Arc {
    protected Transition from;
    protected Place to;

    public ArcTP(Transition from, Place to, int weight){
        super(weight);
        this.to = to;
        this.from = from;
        // Add this arc to the transition's output arcs list
        from.addArcTP(this);
    }
    
    public void produce(){
        to.addTokens(weight);
        }

    public Place getTo() {
        return to;
    }

    public Transition getFrom() {
        return from;
    }
    
    @Override
    public String toString() {
        return "ArcTP(weight=" + weight + ")";
    }
}
