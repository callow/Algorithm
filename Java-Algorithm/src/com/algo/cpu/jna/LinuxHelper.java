package com.algo.cpu.jna;

import java.util.BitSet;

import com.algo.cpu.jna.cmethods.CLibrary;
import com.algo.cpu.jna.cmethods.cpu_set_t;
import com.algo.cpu.jna.cmethods.utsname;
import com.sun.jna.Function;
import com.sun.jna.LastErrorException;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public class LinuxHelper {
	private static final VersionHelper UNKNOWN = new VersionHelper(0, 0, 0);
	
	/**
	 * 
	 * 获取Linux Version
	 */
	public static VersionHelper getLinuxVersion() {
		utsname uname = new utsname();
		VersionHelper ver = UNKNOWN;
		if (CLibrary.INSTANCE.uname(uname) == 0) { // 调用C的uname方法获取版本号
            ver = new VersionHelper(uname.getRealeaseVersion());
        }
		return ver;
	}
	
	/**
	 * 
	 * 获取CPU Affinity数据
	 */
	public static cpu_set_t sched_getaffinity() {
         CLibrary lib = CLibrary.INSTANCE;
         cpu_set_t cpuset = new cpu_set_t();
         int size = getLinuxVersion().isSameOrNewer(new VersionHelper(2, 6, 0)) ? cpu_set_t.SIZE_OF_CPU_SET_T : NativeLong.SIZE;

        try {
            if (lib.sched_getaffinity(0, size, cpuset) != 0) {
                throw new IllegalStateException("sched_getaffinity(0, " + size + ", cpuset) failed; errno=" + Native.getLastError());
            }
        } catch (LastErrorException e) {
            throw new IllegalStateException("sched_getaffinity(0, (" + size + ") , cpuset) failed; errno=" + e.getErrorCode(), e);
        }
        return cpuset;
	 }
	
	/**
	 * 
	 * 设置CPU Affinity
	 */
    public static void sched_setaffinity(final int pid, final BitSet affinity) {
        final CLibrary lib = CLibrary.INSTANCE;
        final cpu_set_t cpuset = new cpu_set_t();
        final int size = getLinuxVersion().isSameOrNewer(new VersionHelper(2, 6, 0)) ? cpu_set_t.SIZE_OF_CPU_SET_T : NativeLong.SIZE;
        final long[] bits = affinity.toLongArray();
        for (int i = 0; i < bits.length; i++) {
            if (Platform.is64Bit()) {
                cpuset.__bits[i].setValue(bits[i]);
            } else {
                cpuset.__bits[i * 2].setValue(bits[i] & 0xFFFFFFFFL);
                cpuset.__bits[i * 2 + 1].setValue((bits[i] >>> 32) & 0xFFFFFFFFL);
            }
        }
        try {
            if (lib.sched_setaffinity(pid, size, cpuset) != 0) {
                throw new IllegalStateException("sched_setaffinity(" + pid + ", " + size +
                        ", 0x" + Utilities.toHexString(affinity) + ") failed; errno=" + Native.getLastError());
            }
        } catch (LastErrorException e) {
            throw new IllegalStateException("sched_setaffinity(" + pid + ", " + size +
                    ", 0x" + Utilities.toHexString(affinity) + ") failed; errno=" + e.getErrorCode(), e);
        }
    }
	
	/**
	 * 执行Linux系统方法调用
	 */
    public static int syscall(int number, Object... args) {
        CLibrary lib = CLibrary.INSTANCE;
        try {
            final int ret = lib.syscall(number, args);
            if (ret < 0) {
                throw new IllegalStateException("sched_getcpu() failed; errno=" + Native.getLastError());
            }
            return ret;
        } catch (LastErrorException e) {
            throw new IllegalStateException("sched_getcpu() failed; errno=" + e.getErrorCode(), e);
        }
    }
    
    /**
	 * 获取PID
	 */
    public static int getpid() {
        CLibrary lib = CLibrary.INSTANCE;
        try {
            final int ret = lib.getpid();
            if (ret < 0) {
                throw new IllegalStateException("getpid() failed; errno=" + Native.getLastError());
            }
            return ret;
        } catch (LastErrorException e) {
            throw new IllegalStateException("getpid() failed; errno=" + e.getErrorCode(), e);
        }
    }
    
    /**
     * 
     * 获取当前线程运行在哪一个CPU
     */
    public static int sched_getcpu() {
        CLibrary lib = CLibrary.INSTANCE;
        try {
            int ret = lib.sched_getcpu();
            if (ret < 0) {
                throw new IllegalStateException("sched_getcpu() failed; errno=" + Native.getLastError());
            }
            return ret;
        } catch (LastErrorException e) {
            throw new IllegalStateException("sched_getcpu() failed; errno=" + e.getErrorCode(), e);
        } catch (UnsatisfiedLinkError ule) {
            try {
                IntByReference cpu = new IntByReference();
                IntByReference node = new IntByReference();
                int ret = lib.syscall(318, cpu, node, null);
                if (ret != 0) {
                    throw new IllegalStateException("getcpu() failed; errno=" + Native.getLastError());
                }
                return cpu.getValue();
            } catch (LastErrorException lee) {
                if (lee.getErrorCode() == 38 && Platform.is64Bit()) { // unknown call
                    Pointer getcpuAddr = new Pointer((-10L << 20) + 1024L * 2L);
                    Function getcpu = Function.getFunction(getcpuAddr, Function.C_CONVENTION);
                    IntByReference cpu = new IntByReference();
                    if (getcpu.invokeInt(new Object[]{cpu, null, null}) < 0) {
                        throw new IllegalStateException("getcpu() failed; errno=" + Native.getLastError());

                    } else {
                        return cpu.getValue();
                    }
                } else {
                    throw new IllegalStateException("getcpu() failed; errno=" + lee.getErrorCode(), lee);
                }
            }
        }
    }
}
