package org.example;

public class Pair<FirstType, SecondType> {
    private FirstType first;
    private SecondType second;

    public Pair(FirstType first, SecondType second) {
        this.first = first;
        this.second = second;
    }

    public FirstType getFirst() {
        return first;
    }

    public SecondType getSecond() {
        return second;
    }

    public void setFirst(FirstType first) {
        this.first = first;
    }

    public void setSecond(SecondType second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pair<?, ?> other = (Pair<?, ?>) obj;
        return first.equals(other.first) && second.equals(other.second);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + first.hashCode();
        hash = 31 * hash + second.hashCode();
        return hash;
    }
}
