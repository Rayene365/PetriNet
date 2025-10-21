public class ResetArc extends ArcPT{
    public ResetArc(Place from, Transition to, int weight){
        super(from, to, 1); // Weight is always 1 for ResetArc
        this.weight = 1; // Ensure weight is always 1
    } 

    @Override
    public boolean isActive(){
        return from.getTokens() >= weight;
    }
    
    @Override
    public void consume(){
        int allTokens = from.getTokens();
        from.removeTokens(allTokens);
    }
    
    @Override
    public String toString() {
        return "ResetArc(weight=" + weight + ", active=" + isActive() + ")";
    }
}
