package com.algo.cpu.jna.cpudetails;

public interface CpuLayout {
    /**
     * @return the number of cpus.
     */
    int cpus(); // 几个cpu

    int sockets(); // 几个插槽

    int coresPerSocket(); // 每个插槽上几块cpu

    int threadsPerCore();// 每个core上最大并行线程

    /**
     * @param cpuId the logical processor number
     * @return which socket id this cpu is on.
     */
    int socketId(int cpuId);

    /**
     * @param cpuId the logical processor number
     * @return which core on a socket this cpu is on.
     */
    int coreId(int cpuId);

    /**
     * @param cpuId the logical processor number
     * @return which thread on a core this cpu is on.
     */
    int threadId(int cpuId);
}
