package com.algo.cpu.jna.cmethods;

import com.sun.jna.LastErrorException;
import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * 
 * JNA ����ڣ�singleton : https://www.baeldung.com/java-jna-dynamic-libraries
 *
 */
public interface CLibrary extends Library {
	
	// ����ʹ��c�������
    CLibrary INSTANCE = Native.load("c", CLibrary.class);

    /**
     * �ؼ��̰߳���ĳ��cpu Core�ϣ�Ȼ���������ж�Դ��IRQ�������Core�����жϡ� �ú������ý���Ϊpid���������,����������cpuset���趨��CPU��
     * 
     * �߳�CPU�׺��� sched_getaffinity(0, sizeof(get), &get)
     * 	
     *  ��pidΪ0�����ʾָ����ǰ����
     *  cpusetsizeΪcpuset�Ĵ�С��ͨ��Ϊsizeof(cpu_set_t)
     *  cpuset����cpu_set_t�ṹ���ʾ��CPU Core����: �������ĸ�/�ļ���CPU��
     * 	
     	CPU_ZERO(cpu_set_t* cpusetp);               // ���һ������
	 	CPU_SET(size_t cpu, cpu_set_t* cpusetp);    // ����CPU Core������
	 	CPU_CLR(size_t cpu, cpu_set_t* cpusetp);    // �Ӽ�����ɾ��ָ����CPU Core
     	CPU_ISSET(size_t cpu, cpu_set_t* cpusetp);  // ���һ��CPU Core�Ƿ��ڼ�����
     */
    int sched_setaffinity(int pid,int cpusetsize,cpu_set_t cpuset) throws LastErrorException;

    // ���ָ��pid��ǰ������������ЩCPU�ϣ� ���pid��ֵΪ0.Ҳ��ʾ���ǵ�ǰ����
    int sched_getaffinity(int pid,int cpusetsize,cpu_set_t cpuset) throws LastErrorException;

    //  ps -s��  ��ȡ�����̺ţ�PID��
    int getpid() throws LastErrorException;

    int sched_getcpu() throws LastErrorException;

    // uname: ��ȡ����ϵͳ��Ϣ�������ں˰汾������������������
    int uname(utsname name) throws LastErrorException;

    // ԭ������linuxϵͳ�������˴�����gettid() returns the caller's thread ID (TID)
    int syscall(int number, Object... args) throws LastErrorException;
    
    // ��ȡpid�� ������ĳЩϵͳ 
    int pthread_self() throws LastErrorException;
}
