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

    /**
     * 关键线程绑定在某个cpu Core上，然后避免各种中断源（IRQ）向这个Core发送中断。 该函数设置进程为pid的这个进程,让它运行在cpuset所设定的CPU上
     * 
     * 线程CPU亲和力 sched_getaffinity(0, sizeof(get), &get)
     * 	
     *  若pid为0，则表示指定当前进程
     *  cpusetsize为cpuset的大小，通常为sizeof(cpu_set_t)
     *  cpuset即用cpu_set_t结构体表示的CPU Core集合: 运行在哪个/哪几个CPU上
     * 	
     	CPU_ZERO(cpu_set_t* cpusetp);               // 清空一个集合
	 	CPU_SET(size_t cpu, cpu_set_t* cpusetp);    // 加入CPU Core到集合
	 	CPU_CLR(size_t cpu, cpu_set_t* cpusetp);    // 从集合中删除指定的CPU Core
     	CPU_ISSET(size_t cpu, cpu_set_t* cpusetp);  // 检查一个CPU Core是否在集合中
     */
    int sched_setaffinity(int pid,int cpusetsize,cpu_set_t cpuset) throws LastErrorException;

    // 获得指定pid当前可以运行在哪些CPU上， 如果pid的值为0.也表示的是当前进程
    int sched_getaffinity(int pid,int cpusetsize,cpu_set_t cpuset) throws LastErrorException;

    //  ps -s：  获取本进程号（PID）
    int getpid() throws LastErrorException;

    int sched_getcpu() throws LastErrorException;

    // uname: 获取操作系统信息，例如内核版本、主机名、处理器类
    int uname(utsname name) throws LastErrorException;

    // 原生调用linux系统函数，此处调用gettid() returns the caller's thread ID (TID)
    int syscall(int number, Object... args) throws LastErrorException;
    
    // 获取pid， 适用于某些系统 
    int pthread_self() throws LastErrorException;
}
