package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class utils {
    public static RandomAccessFile fopenRAF(char[] filename, String mode) {
        File f = new File(new String(filename));
        if (mode.contains("w") && f.exists() && !f.delete()) {
            return null;
        }
        try {
            mode = mode.replace("b", "");
            return new RandomAccessFile(f, mode);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
