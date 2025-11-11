package org.sum.calculator;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class WaveTask extends RecursiveAction {
    private final List<PairTask> tasks;

    WaveTask(List<PairTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    protected void compute() {
        invokeAll(tasks);
    }
}
