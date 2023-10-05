package com.algo.cpu.jna.cmethods;

import java.util.Collections;
import java.util.List;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;

public class cpu_set_t extends Structure {
	public static final int __CPU_SETSIZE = 1024;
    public static final int __NCPUBITS = 8 * NativeLong.SIZE;
    public static final int SIZE_OF_CPU_SET_T = (__CPU_SETSIZE / __NCPUBITS) * NativeLong.SIZE;
    public static List<String> FIELD_ORDER = Collections.singletonList("__bits");
    public NativeLong[] __bits = new NativeLong[__CPU_SETSIZE / __NCPUBITS];

    public cpu_set_t() {
        for (int i = 0; i < __bits.length; i++) {
            __bits[i] = new NativeLong(0);
        }
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public static void __CPU_ZERO(cpu_set_t cpuset) {
        for (NativeLong bits : cpuset.__bits) {
            bits.setValue(0L);
        }
    }

    public static int __CPUELT(int cpu) {
        return cpu / __NCPUBITS;
    }

    public static long __CPUMASK(int cpu) {
        return 1L << (cpu % __NCPUBITS);
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public static void __CPU_SET(int cpu, cpu_set_t cpuset) {
        cpuset.__bits[__CPUELT(cpu)].setValue(
                cpuset.__bits[__CPUELT(cpu)].longValue() | __CPUMASK(cpu));
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public static void __CPU_CLR(int cpu, cpu_set_t cpuset) {
        cpuset.__bits[__CPUELT(cpu)].setValue(
                cpuset.__bits[__CPUELT(cpu)].longValue() & ~__CPUMASK(cpu));
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public static boolean __CPU_ISSET(int cpu, cpu_set_t cpuset) {
        return (cpuset.__bits[__CPUELT(cpu)].longValue() & __CPUMASK(cpu)) != 0;
    }

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }
}
