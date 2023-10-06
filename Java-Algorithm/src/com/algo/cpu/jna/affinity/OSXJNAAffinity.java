package com.algo.cpu.jna.affinity;

import java.lang.management.ManagementFactory;
import java.util.BitSet;

import com.algo.cpu.jna.Utilities;
import com.sun.jna.LastErrorException;
import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * 
 * ����MacOS
 *
 */
public enum OSXJNAAffinity implements IAffinity {
    INSTANCE;
    private final ThreadLocal<Integer> THREAD_ID = new ThreadLocal<>();

    @Override
    public BitSet getAffinity() {
        return new BitSet();
    }

    @Override
    public void setAffinity(final BitSet affinity) {
    	System.out.println("unable to set mask to" + Utilities.toHexString(affinity));
    }

    @Override
    public int getCpu() {
        return -1;
    }

    @Override
    public int getProcessId() {
        final String name = ManagementFactory.getRuntimeMXBean().getName(); // pid@hostname
        return Integer.parseInt(name.split("@")[0]);
    }

    @Override
    public int getThreadId() {
        Integer tid = THREAD_ID.get();
        if (tid == null) {
            tid = CLibrary.INSTANCE.pthread_self();
            //The tid assumed to be an unsigned 24 bit, see net.openhft.lang.Jvm.getMaxPid()
            tid = tid & 0xFFFFFF;
            THREAD_ID.set(tid);
        }
        return tid;
    }

    /**
     * 
     * JNA �����ں˺���
     *
     */
    interface CLibrary extends Library {
        CLibrary INSTANCE = Native.load("libpthread.dylib", CLibrary.class);

        int pthread_self() throws LastErrorException;
    }
}
