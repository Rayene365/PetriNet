public class ZeroArc extends ArcPT{
    public ZeroArc(Place from, Transition to, int weight){
        super(from, to, 0); // Weight is always 0 for ZeroArc
        this.weight = 0; // Ensure weight is always 0
    }

    @Override
    public boolean isActive(){
        return from.getTokens() == 0; // ZeroArc is active when place has 0 tokens
    }
    
    @Override
    public void consume(){
    }
    
    @Override
    public String toString() {
        return "ZeroArc(weight=" + weight + ", active=" + isActive() + ")";
    }
}
