package laslib;

import java.nio.ByteBuffer;

public final class Cstring {

    private Cstring() {
    }

    public static int strcmp(byte[] s1, String s2) {
        return trim(MyDefs.stringFromByteArray(s1)).compareTo(trim(s2));
    }

    public static int strcmp(String s1, String s2) {
        return trim(s1).compareTo(trim(s2));
    }

    public static int strncmp(String s1, String s2, int num) {
        s1 = trim(s1);
        s2 = trim(s2);
        if (s1.length() > num) {
            s1 = s1.substring(0, num);
        }
        if (s2.length() > num) {
            s2 = s2.substring(0, num);
        }
        return s1.compareTo(s2);
    }

    public static int memcmp(byte[] b1, byte[] b2, int num) {
        for (int i = 0; i < num; i++) {
            int cmp = Byte.compare(b1[i], b2[i]);
            if (cmp != 0) {
                return cmp;
            }
        }
        return 0;
    }

    public static byte[] memcpy(byte[] dest, byte[] src, int num) {
        System.arraycopy(src, 0, dest, 0, num);
        return dest;
    }

    public static void memset(ByteBuffer bb, byte value, int num) {
        for (int i = 0; i < num; i++) {
            bb.put(i, value);
        }
    }

    public static int strlen(String s) {
        return trim(s).length();
    }

    public static int strlen(char[] s) {
        return strlen(new String(s));
    }

    public static String trim(char[] s) {
        return trim(new String(s));
    }

    public static String trim(String s) {
        int idx = s.indexOf('\0');
        if (idx != -1) {
            return s.substring(0, idx);
        } else {
            return s;
        }
    }
}
