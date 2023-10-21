package com.algo.cpu.jna.cpudetails;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;

public class VanillaCpuLayout implements CpuLayout {
    public static final int MAX_CPUS_SUPPORTED = 256;

    private final List<CpuInfo> cpuDetails;
    private final int sockets;
    private final int coresPerSocket;
    private final int threadsPerCore;

    VanillaCpuLayout(List<CpuInfo> cpuDetails) {
        this.cpuDetails = cpuDetails;
        SortedSet<Integer> sockets = new TreeSet<>(),
                cores = new TreeSet<>(),
                threads = new TreeSet<>();
        for (CpuInfo cpuDetail : cpuDetails) {
            sockets.add(cpuDetail.socketId);
            cores.add((cpuDetail.socketId << 16) + cpuDetail.coreId);
            threads.add(cpuDetail.threadId);
        }
        this.sockets = sockets.size();
        this.coresPerSocket = cores.size() / sockets.size();
        this.threadsPerCore = threads.size();
        if (cpuDetails.size() != sockets() * coresPerSocket() * threadsPerCore()) {
            StringBuilder error = new StringBuilder();
            error.append("cpuDetails.size= ").append(cpuDetails.size())
                    .append(" != sockets: ").append(sockets())
                    .append(" * coresPerSocket: ").append(coresPerSocket())
                    .append(" * threadsPerCore: ").append(threadsPerCore()).append('\n');
            for (CpuInfo detail : cpuDetails) {
                error.append(detail).append('\n');
            }
        }
    }

    public static VanillaCpuLayout fromProperties(String fileName) throws IOException {
        return fromProperties(openFile(fileName));
    }

    public static VanillaCpuLayout fromProperties(InputStream is) throws IOException {
        Properties prop = new Properties();
        prop.load(is);
        return fromProperties(prop);
    }

    public static VanillaCpuLayout fromProperties(Properties prop) {
        List<CpuInfo> cpuDetails = new ArrayList<>();
        for (int i = 0; i < MAX_CPUS_SUPPORTED; i++) {
            String line = prop.getProperty("" + i);
            if (line == null) break;
            String[] word = line.trim().split(" *, *");
            CpuInfo details = new CpuInfo(Integer.parseInt(word[0]),
                    Integer.parseInt(word[1]), Integer.parseInt(word[2]));
            cpuDetails.add(details);
        }
        return new VanillaCpuLayout(cpuDetails);
    }

    public static VanillaCpuLayout fromCpuInfo() throws IOException {
        return fromCpuInfo("/proc/cpuinfo");
    }

    public static VanillaCpuLayout fromCpuInfo(String filename) throws IOException {
        return fromCpuInfo(openFile(filename));
    }

    private static InputStream openFile(String filename) throws FileNotFoundException {
        try {
            return new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            if (is == null)
                throw e;
            return is;
        }
    }
    
    /**
     * 读取/proc/cpuinfo并填充到CpuInfo中: 7块CPU
     *         ("0: CpuInfo{socketId=0, coreId=0, threadId=0}\n" +
                "1: CpuInfo{socketId=0, coreId=1, threadId=0}\n" +
                "2: CpuInfo{socketId=0, coreId=2, threadId=0}\n" +
                "3: CpuInfo{socketId=0, coreId=3, threadId=0}\n" +
                "4: CpuInfo{socketId=0, coreId=0, threadId=1}\n" +
                "5: CpuInfo{socketId=0, coreId=1, threadId=1}\n" +
                "6: CpuInfo{socketId=0, coreId=2, threadId=1}\n" +
                "7: CpuInfo{socketId=0, coreId=3, threadId=1}\n" );
     */
    public static VanillaCpuLayout fromCpuInfo(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line;
        List<CpuInfo> cpuDetails = new ArrayList<>();
        CpuInfo details = new CpuInfo();
        Map<String, Integer> threadCount = new LinkedHashMap<>();

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                String key = details.socketId + "," + details.coreId;
                Integer count = threadCount.get(key);
                if (count == null)
                    threadCount.put(key, count = 1);
                else
                    threadCount.put(key, count += 1);
                details.threadId = count - 1;
                cpuDetails.add(details);
                details = new CpuInfo();
                details.coreId = cpuDetails.size();
                continue;
            }
            String[] words = line.split("\\s*:\\s*", 2);
            if (words[0].equals("physical id"))
                details.socketId = Integer.parseInt(words[1]);
            else if (words[0].equals("core id"))
                details.coreId = Integer.parseInt(words[1]);
        }
        return new VanillaCpuLayout(cpuDetails);
    }

    @Override
    public int cpus() {
        return cpuDetails.size();
    }

    @Override
	public int sockets() {
        return sockets;
    }

    @Override
	public int coresPerSocket() {
        return coresPerSocket;
    }

    @Override
    public int threadsPerCore() {
        return threadsPerCore;
    }

    @Override
    public int socketId(int cpuId) {
        return cpuDetails.get(cpuId).socketId;
    }

    @Override
    public int coreId(int cpuId) {
        return cpuDetails.get(cpuId).coreId;
    }

    @Override
    public int threadId(int cpuId) {
        return cpuDetails.get(cpuId).threadId;
    }

    static class CpuInfo {
        int socketId, coreId, threadId;

        CpuInfo() {
        }

        CpuInfo(int socketId, int coreId, int threadId) {
            this.socketId = socketId;
            this.coreId = coreId;
            this.threadId = threadId;
        }
    }
}
