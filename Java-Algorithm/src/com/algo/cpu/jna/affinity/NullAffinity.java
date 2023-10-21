package com.algo.cpu.jna.affinity;

import java.lang.management.ManagementFactory;
import java.util.BitSet;

public enum NullAffinity implements IAffinity {
    INSTANCE;

    @Override
    public BitSet getAffinity() {
        return new BitSet();
    }

    @Override
    public void setAffinity(final BitSet affinity) {
    }

    @Override
    public int getCpu() {
        return -1;
    }

    @Override
    public int getProcessId() {
        final String name = ManagementFactory.getRuntimeMXBean().getName();
        return Integer.parseInt(name.split("@")[0]);
    }

    @Override
    public int getThreadId() {
        throw new UnsupportedOperationException();
    }
}
