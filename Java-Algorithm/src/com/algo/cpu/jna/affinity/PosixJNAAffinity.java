package com.algo.cpu.jna.affinity;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.BitSet;

import com.algo.cpu.jna.Utilities;
import com.sun.jna.LastErrorException;
import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.PointerType;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * sched_setaffinity(3)/sched_getaffinity(3) from 'c' library. Applicable for most
 * linux/unix platforms.
 * 
 * Windows ֧�� POSIX ��׼��ԭ����Ϊ���� UNIX �� Linux ϵͳ���л�������
 *
 */
public enum PosixJNAAffinity implements IAffinity {
    INSTANCE;
    public static final boolean LOADED;
    private static final String LIBRARY_NAME = Platform.isWindows() ? "msvcrt" : "c";
    private static final int PROCESS_ID;
    private static final int SYS_gettid = Utilities.is64Bit() ? 186 : 224;
    private static final Object[] NO_ARGS = {};
    private final ThreadLocal<Integer> THREAD_ID = new ThreadLocal<>();

    static {
        int processId;
        try {
            processId = CLibrary.INSTANCE.getpid();
        } catch (Exception ignored) {
            processId = -1;
        }
        PROCESS_ID = processId;
    }

    static {
        boolean loaded = false;
        try {
            INSTANCE.getAffinity();
            loaded = true;
        } catch (UnsatisfiedLinkError e) {
        	System.out.println("Unable to load jna library");
        }
        LOADED = loaded;
    }

    @Override
    public BitSet getAffinity() {
        final CLibrary lib = CLibrary.INSTANCE;
        final int procs = Runtime.getRuntime().availableProcessors(); // number of logical cores

        final int cpuSetSizeInLongs = (procs + 63) / 64;
        final int cpuSetSizeInBytes = cpuSetSizeInLongs * 8;
        final Memory cpusetArray = new Memory(cpuSetSizeInBytes);
        final PointerByReference cpuset = new PointerByReference(cpusetArray);
        try {
            final int ret = lib.sched_getaffinity(0, cpuSetSizeInBytes, cpuset);
            if (ret < 0) {
                throw new IllegalStateException("sched_getaffinity((" + cpuSetSizeInBytes + ") , &(" + cpusetArray + ") ) return " + ret);
            }
            ByteBuffer buff = cpusetArray.getByteBuffer(0, cpuSetSizeInBytes);
            return BitSet.valueOf(buff.array());
        } catch (LastErrorException e) {
            if (e.getErrorCode() != 22) {
                throw new IllegalStateException("sched_getaffinity((" + cpuSetSizeInBytes + ") , &(" + cpusetArray + ") ) errorNo=" + e.getErrorCode(), e);
            }
        }

        // fall back to the old method
        final IntByReference cpuset32 = new IntByReference(0);
        try {
            final int ret = lib.sched_getaffinity(0, Integer.SIZE / 8, cpuset32);
            if (ret < 0) {
                throw new IllegalStateException("sched_getaffinity((" + Integer.SIZE / 8 + ") , &(" + cpuset32 + ") ) return " + ret);
            }
            long[] longs = new long[1];
            longs[0] = cpuset32.getValue() & 0xFFFFFFFFL;
            return BitSet.valueOf(longs);
        } catch (LastErrorException e) {
            throw new IllegalStateException("sched_getaffinity((" + Integer.SIZE / 8 + ") , &(" + cpuset32 + ") ) errorNo=" + e.getErrorCode(), e);
        }
    }

    @Override
    public void setAffinity(final BitSet affinity) {
        int procs = Runtime.getRuntime().availableProcessors();
        if (affinity.isEmpty()) {
            throw new IllegalArgumentException("Cannot set zero affinity");
        }

        final CLibrary lib = CLibrary.INSTANCE;
        byte[] buff = affinity.toByteArray();
        final int cpuSetSizeInBytes = buff.length;
        final Memory cpusetArray = new Memory(cpuSetSizeInBytes);
        try {
            cpusetArray.write(0, buff, 0, buff.length);
            final int ret = lib.sched_setaffinity(0, cpuSetSizeInBytes, new PointerByReference(cpusetArray));
            if (ret < 0) {
                throw new IllegalStateException("sched_setaffinity((" + cpuSetSizeInBytes + ") , &(" + affinity + ") ) return " + ret);
            }
        } catch (LastErrorException e) {
            if (e.getErrorCode() != 22 || !Arrays.equals(buff, cpusetArray.getByteArray(0, cpuSetSizeInBytes))) {
                throw new IllegalStateException("sched_setaffinity((" + cpuSetSizeInBytes + ") , &(" + affinity + ") ) errorNo=" + e.getErrorCode(), e);
            }
        }

        final int value = (int) affinity.toLongArray()[0];
        if (value == 0) {
            throw new IllegalArgumentException("Cannot set zero affinity");
        }
        final IntByReference cpuset32 = new IntByReference(0);
        cpuset32.setValue(value);
        try {
            final int ret = lib.sched_setaffinity(0, Integer.SIZE / 8, cpuset32);
            if (ret < 0)
                throw new IllegalStateException("sched_setaffinity((" + Integer.SIZE / 8 + ") , &(" + Integer.toHexString(cpuset32.getValue()) + ") ) return " + ret);
        } catch (LastErrorException e) {
            throw new IllegalStateException("sched_setaffinity((" + Integer.SIZE / 8 + ") , &(" + Integer.toHexString(cpuset32.getValue()) + ") ) errorNo=" + e.getErrorCode(), e);
        }
    }

    @Override
    public int getCpu() {
        final CLibrary lib = CLibrary.INSTANCE;
        try {
            final int ret = lib.sched_getcpu();
            if (ret < 0)
                throw new IllegalStateException("sched_getcpu( ) return " + ret);
            return ret;
        } catch (LastErrorException e) {
            throw new IllegalStateException("sched_getcpu( ) errorNo=" + e.getErrorCode(), e);
        } catch (UnsatisfiedLinkError ule) {
            try {
                final IntByReference cpu = new IntByReference();
                final IntByReference node = new IntByReference();
                final int ret = lib.syscall(318, cpu, node, null);
                if (ret != 0) {
                    throw new IllegalStateException("getcpu( ) return " + ret);
                }

                return cpu.getValue();
            } catch (LastErrorException lee) {
                throw new IllegalStateException("getcpu( ) errorNo=" + lee.getErrorCode(), lee);
            }
        }
    }

    @Override
    public int getProcessId() {
        return PROCESS_ID;
    }

    @Override
    public int getThreadId() {
        if (Utilities.ISLINUX) {
            Integer tid = THREAD_ID.get();
            if (tid == null)
                THREAD_ID.set(tid = CLibrary.INSTANCE.syscall(SYS_gettid, NO_ARGS));
            return tid;
        }
        return -1;
    }

    /**
     * @author BegemoT
     */
    interface CLibrary extends Library {
        CLibrary INSTANCE = Native.load(LIBRARY_NAME, CLibrary.class);

        int sched_setaffinity(final int pid,
                              final int cpusetsize,
                              final PointerType cpuset) throws LastErrorException;

        int sched_getaffinity(final int pid,
                              final int cpusetsize,
                              final PointerType cpuset) throws LastErrorException;

        int sched_getcpu() throws LastErrorException;

        int getcpu(final IntByReference cpu,
                   final IntByReference node,
                   final PointerType tcache) throws LastErrorException;

        int getpid() throws LastErrorException;

        int syscall(int number, Object... args) throws LastErrorException;
    }
}
