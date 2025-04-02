public class Pair {
    private K first; 
    private V second; 

    public Pair(K first, V second) {
        this.first = first; 
        this.second = second; 
    }

    public K getFirst() {
        return first; 
    }

    public V getSecond() {
        return second;
    }

    public void setFirst() {
        this.first = first;
    }

    public void setSecond(V second) {
        this.second = second; 
    }

    public String toString() {
        return "(" + first + ", " + second + ")"; 
    }

    @Override 
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return first.equals(pair.first) && second.equals(pair.second);
    }

}
