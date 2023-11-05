package com.vladhiff.emc.gof.patterns.strategy;

public class NoABSSlowdownStrategy implements SlowdownStrategy {
    @Override
    public void slowdown() {
        // Торможение без АБС
    }
}
