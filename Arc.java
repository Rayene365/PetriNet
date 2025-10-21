public class Arc {
    protected int weight;
    public Arc(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be >= 0");
        }
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be >= 0");
        }
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return "Arc(weight=" + weight + ")";
    }
}
