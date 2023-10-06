package com.algo.cpu.jna.affinity;

import java.util.BitSet;

import com.algo.cpu.jna.LinuxHelper;
import com.algo.cpu.jna.cmethods.cpu_set_t;
import com.sun.jna.NativeLong;
import com.sun.jna.Platform;

public enum LinuxJNAAffinity implements IAffinity	{
	INSTANCE; // 枚举天然单例
	public static final boolean LOADED;
    private static final int PROCESS_ID;
    private static final int SYS_gettid = Platform.isPPC() ? 207 : Platform.is64Bit() ? 186 : 224;
    private static final Object[] NO_ARGS = {};
    private final ThreadLocal<Integer> THREAD_ID = new ThreadLocal<>();

    private static final String OS = System.getProperty("os.name").toLowerCase(); // 获取操作系统类型
	private static final boolean IS_LINUX = OS.startsWith("linux");

    static {
        int pid = -1;
        try {
            pid = LinuxHelper.getpid(); // 获取当前程序的PID
        } catch (NoClassDefFoundError | Exception ignored) {
        }
        PROCESS_ID = pid;
    }

    static {
        boolean loaded = false;
        try {
            INSTANCE.getAffinity(); // 获取亲和性
            loaded = true;
        } catch (NoClassDefFoundError | UnsatisfiedLinkError e) {
            if (IS_LINUX) {
            	System.out.println("Unable to load jna library");
            }
        }
        LOADED = loaded;
    }

    @Override
    public BitSet getAffinity() {
        cpu_set_t cpuset = LinuxHelper.sched_getaffinity();

        BitSet ret = new BitSet(cpu_set_t.__CPU_SETSIZE);
        int i = 0;
        for (NativeLong nl : cpuset.__bits) {
            for (int j = 0; j < Long.SIZE; j++)
                ret.set(i++, ((nl.longValue() >>> j) & 1) != 0);
        }
        return ret;
    }

    // TODO: FIXME!!! CHANGE IAffinity TO SUPPORT PLATFORMS WITH 64+ CORES FIXME!!!
    @Override
    public void setAffinity(BitSet affinity) {
        LinuxHelper.sched_setaffinity(0,affinity);
    }

    @Override
    public int getCpu() {
        return LinuxHelper.sched_getcpu();
    }

    @Override
    public int getProcessId() {
        return PROCESS_ID;
    }

    @Override
    public int getThreadId() {
        Integer tid = THREAD_ID.get();
        if (tid == null)
            THREAD_ID.set(tid = LinuxHelper.syscall(SYS_gettid, NO_ARGS)); // 实在找不到pid，就去调用内核方法
        return tid;
    }
}
