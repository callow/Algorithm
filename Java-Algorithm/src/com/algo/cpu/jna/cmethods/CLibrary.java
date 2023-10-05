package com.algo.cpu.jna.cmethods;

import com.sun.jna.LastErrorException;
import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * 
 * JNA 主入口，singleton : https://www.baeldung.com/java-jna-dynamic-libraries
 *
 */
public interface CLibrary extends Library {
	
	// 决定使用c语言类库
    CLibrary INSTANCE = Native.load("c", CLibrary.class);

    int sched_setaffinity(int pid,int cpusetsize,cpu_set_t cpuset) throws LastErrorException;

    int sched_getaffinity(int pid,int cpusetsize,cpu_set_t cpuset) throws LastErrorException;

    int getpid() throws LastErrorException;

    int sched_getcpu() throws LastErrorException;

    int uname(utsname name) throws LastErrorException;

    int syscall(int number, Object... args) throws LastErrorException;
}
