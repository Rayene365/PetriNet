public class Place {
    private int tokens;

    public Place(int tokens) {
        this.tokens = tokens;
    }

    public void addTokens(int n) {
        tokens += n;
    }

    public void removeTokens(int n) {
        tokens -= n;
    }

    public int getTokens() {
        return tokens;
    }
    
    @Override
    public String toString() {
        return "Place(tokens=" + tokens + ")";
    }
}