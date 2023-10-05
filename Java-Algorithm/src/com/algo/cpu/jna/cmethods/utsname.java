package com.algo.cpu.jna.cmethods;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class utsname extends Structure {
    public static final int _UTSNAME_LENGTH = 65;

    static List<String> FIELD_ORDER = Arrays.asList(
            "sysname",
            "nodename",
            "release",
            "version",
            "machine",
            "domainname"
    );

    /**
     * Name of the implementation of the operating system.
     */
    public byte[] sysname = new byte[_UTSNAME_LENGTH];

    /**
     * Name of this node on the network.
     */
    public byte[] nodename = new byte[_UTSNAME_LENGTH];

    /**
     * Current release level of this implementation.
     */
    public byte[] release = new byte[_UTSNAME_LENGTH];

    /**
     * Current version level of this release.
     */
    public byte[] version = new byte[_UTSNAME_LENGTH];

    /**
     * Name of the hardware type the system is running on.
     */
    public byte[] machine = new byte[_UTSNAME_LENGTH];

    /**
     * NIS or YP domain name
     */
    public byte[] domainname = new byte[_UTSNAME_LENGTH];

    static int length(final byte[] data) {
        int len = 0;
        final int datalen = data.length;
        while (len < datalen && data[len] != 0)
            len++;
        return len;
    }

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public String getSysname() {
        return new String(sysname, 0, length(sysname));
    }

    public String getNodename() {
        return new String(nodename, 0, length(nodename));
    }

    public String getRelease() {
        return new String(release, 0, length(release));
    }

    public String getRealeaseVersion() {
        final String release = getRelease();
        final int releaseLen = release.length();
        int len = 0;
        for (; len < releaseLen; len++) {
            final char c = release.charAt(len);
            if (Character.isDigit(c) || c == '.') {
                continue;
            }
            break;
        }
        return release.substring(0, len);
    }

    public String getVersion() {
        return new String(version, 0, length(version));
    }

    public String getMachine() {
        return new String(machine, 0, length(machine));
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public String getDomainname() {
        return new String(domainname, 0, length(domainname));
    }

    @Override
    public String toString() {
        return getSysname() + " " + getRelease() +
                " " + getVersion() + " " + getMachine();
    }
}
