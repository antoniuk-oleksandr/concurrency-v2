package org.example.sum.strategy;

public enum Strategy {
    STREAM("stream"),
    EXECUTOR("executor"),
    FORK_JOIN("fork-join"),
    SINGLE("single");

    private final String value;

    Strategy(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
