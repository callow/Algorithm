package com.algo.cpu.jna.affinitywraper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.BitSet;

import com.algo.cpu.jna.affinity.IAffinity;
import com.algo.cpu.jna.affinity.LinuxJNAAffinity;
import com.algo.cpu.jna.affinity.NullAffinity;
import com.algo.cpu.jna.affinity.OSXJNAAffinity;
import com.algo.cpu.jna.affinity.PosixJNAAffinity;
import com.algo.cpu.jna.affinity.SolarisJNAAffinity;
import com.algo.cpu.jna.affinity.WindowsJNAAffinity;
import com.sun.jna.Native;

/**
 * Library to wrap low level JNI or JNA calls.  Can be called without needing to know the actual
 *
 */
public enum Affinity {
	; // none
    private static final IAffinity AFFINITY_IMPL;
    private static Boolean JNAAvailable;

    static {
        String osName = System.getProperty("os.name");
        if (osName.contains("Win") && isWindowsJNAAffinityUsable()) {
        	System.out.println("Using Windows JNA-based affinity control implementation");
            AFFINITY_IMPL = WindowsJNAAffinity.INSTANCE;

        } else if (osName.contains("x")) {
            /*if (osName.startsWith("Linux") && NativeAffinity.LOADED) {
                LOGGER.trace("Using Linux JNI-based affinity control implementation");
                AFFINITY_IMPL = NativeAffinity.INSTANCE;
            } else*/
            if (osName.startsWith("Linux") && isLinuxJNAAffinityUsable()) {
            	System.out.println("Using Linux JNA-based affinity control implementation");
                AFFINITY_IMPL = LinuxJNAAffinity.INSTANCE;

            } else if (isPosixJNAAffinityUsable()) {
            	System.out.println("Using Posix JNA-based affinity control implementation");
                AFFINITY_IMPL = PosixJNAAffinity.INSTANCE;

            } else {
            	System.out.println("Using dummy affinity control implementation");
                AFFINITY_IMPL = NullAffinity.INSTANCE;
            }
        } else if (osName.contains("Mac") && isMacJNAAffinityUsable()) {
        	System.out.println("Using MAC OSX JNA-based thread id implementation");
            AFFINITY_IMPL = OSXJNAAffinity.INSTANCE;

        } else if (osName.contains("SunOS") && isSolarisJNAAffinityUsable()) {
        	System.out.println("Using Solaris JNA-based thread id implementation");
            AFFINITY_IMPL = SolarisJNAAffinity.INSTANCE;

        } else {
            AFFINITY_IMPL = NullAffinity.INSTANCE;
        }
    }

    public static IAffinity getAffinityImpl() {
        return AFFINITY_IMPL;
    }

    private static boolean isWindowsJNAAffinityUsable() {
        if (isJNAAvailable()) {
            try {
                return WindowsJNAAffinity.LOADED;
            } catch (Throwable t) {
                logThrowable(t, "Windows JNA-based affinity not usable because it failed to load!");
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean isPosixJNAAffinityUsable() {
        if (isJNAAvailable()) {
            try {
                return PosixJNAAffinity.LOADED;
            } catch (Throwable t) {
                logThrowable(t, "Posix JNA-based affinity not usable because it failed to load!");
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean isLinuxJNAAffinityUsable() {
        if (isJNAAvailable()) {
            try {
                return LinuxJNAAffinity.LOADED;
            } catch (Throwable t) {
                logThrowable(t, "Linux JNA-based affinity not usable because it failed to load!");
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean isMacJNAAffinityUsable() {
        if (isJNAAvailable()) {
            return true;

        } else {
            return false;
        }
    }

    private static boolean isSolarisJNAAffinityUsable() {
        if (isJNAAvailable()) {
            return true;

        } else {
            return false;
        }
    }

    private static void logThrowable(Throwable t, String description) {
        StringWriter sw = new StringWriter();
        sw.append(description);
        sw.append(" Reason: ");
        t.printStackTrace(new PrintWriter(sw));
    }

    public static BitSet getAffinity() {
        return AFFINITY_IMPL.getAffinity();
    }

    public static void setAffinity(final BitSet affinity) {
        AFFINITY_IMPL.setAffinity(affinity);
    }

    public static void setAffinity(int cpu) {
        BitSet affinity = new BitSet(Runtime.getRuntime().availableProcessors());
        affinity.set(cpu);
        setAffinity(affinity);
    }

    public static int getCpu() {
        return AFFINITY_IMPL.getCpu();
    }

    public static int getThreadId() {
        return AFFINITY_IMPL.getThreadId();
    }

    public static void setThreadId() {
        try {
            int threadId = Affinity.getThreadId();
            final Field tid = Thread.class.getDeclaredField("tid");
            tid.setAccessible(true);
            final Thread thread = Thread.currentThread();
            tid.setLong(thread, threadId);
            System.out.println("Set {} to thread id {}" + thread.getName()+  threadId);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static boolean isJNAAvailable() {
        if (JNAAvailable == null) {
            int majorVersion = Integer.parseInt(Native.VERSION.split("\\.")[0]);
            if(majorVersion < 5) {
            	System.out.println("Affinity library requires JNA version >= 5");
                JNAAvailable = false;
            }
            else {
                try {
                    Class.forName("com.sun.jna.Platform");
                    JNAAvailable = true;
                } catch (ClassNotFoundException ignored) {
                    JNAAvailable = false;
                }
            }
        }
        return JNAAvailable;
    }

    public static AffinityLock acquireLock() {
        return AffinityLock.acquireLock();
    }

    public static AffinityLock acquireCore() {
        return AffinityLock.acquireCore();
    }

    public static AffinityLock acquireLock(boolean bind) {
        return AffinityLock.acquireLock(bind);
    }

    public static AffinityLock acquireCore(boolean bind) {
        return AffinityLock.acquireCore(bind);
    }

    public static void resetToBaseAffinity() {
        Affinity.setAffinity(AffinityLock.BASE_AFFINITY);
    }
}
