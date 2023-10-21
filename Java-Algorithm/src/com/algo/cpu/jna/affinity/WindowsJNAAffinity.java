package com.algo.cpu.jna.affinity;

import java.util.BitSet;

import com.sun.jna.LastErrorException;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.LongByReference;

/**
 * 
 * sched_SetThreadAffinityMask/GetProcessAffinityMask from Windows 'kernel32' library. Applicable for
 * most windows platforms
 *
 */
public enum WindowsJNAAffinity implements IAffinity {
    INSTANCE;
    public static final boolean LOADED;
    private static final ThreadLocal<BitSet> currentAffinity = new ThreadLocal<>();

    static {
        boolean loaded = false;
        try {
            INSTANCE.getAffinity();
            loaded = true;
        } catch (UnsatisfiedLinkError e) {
        }
        LOADED = loaded;
    }

    private final ThreadLocal<Integer> THREAD_ID = new ThreadLocal<>();

    @Override
    public BitSet getAffinity() {
        BitSet bitSet = currentAffinity.get();
        if (bitSet != null)
            return bitSet;
        BitSet longs = getAffinity0();
        return longs != null ? longs : new BitSet();
    }

    @Override
    public void setAffinity(final BitSet affinity) {
        final CLibrary lib = CLibrary.INSTANCE;

        WinDef.DWORD aff;
        long[] longs = affinity.toLongArray();
        switch (longs.length) {
            case 0:
                aff = new WinDef.DWORD(0);
                break;
            case 1:
                aff = new WinDef.DWORD(longs[0]);
                break;
            default:
                throw new IllegalArgumentException("Windows API does not support more than 64 CPUs for thread affinity");
        }

        int pid = getTid();
        try {
            lib.SetThreadAffinityMask(handle(pid), aff);
        } catch (LastErrorException e) {
            throw new IllegalStateException("SetThreadAffinityMask((" + pid + ") , &(" + affinity + ") ) errorNo=" + e.getErrorCode(), e);
        }
        BitSet affinity2 = getAffinity0();
        if (!affinity2.equals(affinity)) {
        }
        currentAffinity.set((BitSet) affinity.clone());
    }

    private BitSet getAffinity0() {
        final CLibrary lib = CLibrary.INSTANCE;
        final LongByReference cpuset1 = new LongByReference(0);
        final LongByReference cpuset2 = new LongByReference(0);
        try {

            final int ret = lib.GetProcessAffinityMask(handle(-1), cpuset1, cpuset2);
            // Successful result is positive, according to the docs
            // https://msdn.microsoft.com/en-us/library/windows/desktop/ms683213%28v=vs.85%29.aspx
            if (ret <= 0) {
                throw new IllegalStateException("GetProcessAffinityMask(( -1 ), &(" + cpuset1 + "), &(" + cpuset2 + ") ) return " + ret);
            }

            long[] longs = new long[1];
            longs[0] = cpuset1.getValue();
            return BitSet.valueOf(longs);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private WinNT.HANDLE handle(int pid) {
        return new WinNT.HANDLE(new Pointer(pid));
    }

    public int getTid() {
        final CLibrary lib = CLibrary.INSTANCE;

        try {
            return lib.GetCurrentThread();
        } catch (LastErrorException e) {
            throw new IllegalStateException("GetCurrentThread( ) errorNo=" + e.getErrorCode(), e);
        }
    }

    @Override
    public int getCpu() {
        return -1;
    }

    @Override
    public int getProcessId() {
        return Kernel32.INSTANCE.GetCurrentProcessId();
    }

    @Override
    public int getThreadId() {
        Integer tid = THREAD_ID.get();
        if (tid == null)
            THREAD_ID.set(tid = Kernel32.INSTANCE.GetCurrentThreadId());
        return tid;
    }

    /**
     * @author BegemoT
     */
    private interface CLibrary extends Library {
        CLibrary INSTANCE = Native.load("kernel32", CLibrary.class);

        int GetProcessAffinityMask(final WinNT.HANDLE pid, final PointerType lpProcessAffinityMask, final PointerType lpSystemAffinityMask) throws LastErrorException;

        void SetThreadAffinityMask(final WinNT.HANDLE pid, final WinDef.DWORD lpProcessAffinityMask) throws LastErrorException;

        int GetCurrentThread() throws LastErrorException;
    }
}
