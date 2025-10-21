import java.util.ArrayList;

public class Transition {
    private ArrayList<ArcTP> ArcTPs;
    private ArrayList<ArcPT> ArcPTs;
    
    public Transition() {
        this.ArcTPs = new ArrayList<ArcTP>();
        this.ArcPTs = new ArrayList<ArcPT>();
    }
    
    public void addArcTP(ArcTP arc){
        ArcTPs.add(arc);
    }

    public void addArcPT(ArcPT arc){
        ArcPTs.add(arc);
    }
    
    public ArrayList<ArcTP> getTPs() {
        return ArcTPs;
    }
    
    public ArrayList<ArcPT> getPTs() {
        return ArcPTs;
    }
    
    public boolean isEnabled() {
        for (ArcPT arc : ArcPTs) {
            if (!arc.isActive()) {
                return false;
            }
        }
        return true;
    }
    
    public void fire() {
        if (!isEnabled()) {
            throw new IllegalStateException("Transition is not enabled");
        }
        
        // Consume tokens from input places
        for (ArcPT arc : ArcPTs) {
            arc.consume();
        }
        
        // Produce tokens to output places
        for (ArcTP arc : ArcTPs) {
            arc.produce();
        }
    }
    
    @Override
    public String toString() {
        return "Transition(inputArcs=" + ArcPTs.size() + ", outputArcs=" + ArcTPs.size() + ", enabled=" + isEnabled() + ")";
    }
}