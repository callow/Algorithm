package com.algo.cpu.jna.cpudetails;

public interface CpuLayout {
    /**
     * @return the number of cpus.
     */
    int cpus(); // ����cpu

    int sockets(); // �������

    int coresPerSocket(); // ÿ������ϼ���cpu

    int threadsPerCore();// ÿ��core��������߳�

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
