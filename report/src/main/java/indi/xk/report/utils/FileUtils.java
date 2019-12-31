package indi.xk.report.utils;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/31 13:01
 * @Version 1.0
 */
public class FileUtils {
    public static final long ONE_KB = 1024L;
    public static final BigInteger ONE_KB_BI = BigInteger.valueOf(1024L);
    public static final long ONE_MB = 1048576L;
    public static final BigInteger ONE_MB_BI;
    private static final long FILE_COPY_BUFFER_SIZE = 31457280L;
    public static final long ONE_GB = 1073741824L;
    public static final BigInteger ONE_GB_BI;
    public static final long ONE_TB = 1099511627776L;
    public static final BigInteger ONE_TB_BI;
    public static final long ONE_PB = 1125899906842624L;
    public static final BigInteger ONE_PB_BI;
    public static final long ONE_EB = 1152921504606846976L;
    public static final BigInteger ONE_EB_BI;
    public static final BigInteger ONE_ZB;
    public static final BigInteger ONE_YB;
    public static final File[] EMPTY_FILE_ARRAY;

    public FileUtils() {
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            } else if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            } else {
                return new FileInputStream(file);
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        return openOutputStream(file, false);
    }

    public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }

            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("Directory '" + parent + "' could not be created");
            }
        }

        return new FileOutputStream(file, append);
    }

    public static void copyToFile(InputStream source, File destination) throws IOException {
        FileOutputStream output = openOutputStream(destination);

        try {
            IOUtils.copy(source, output);
            output.close();
        } finally {
            IOUtils.closeQuietly(output);
        }

    }

//    public static void deleteDirectory(File directory) throws IOException {
//        if (directory.exists()) {
//            if (!isSymlink(directory)) {
//                cleanDirectory(directory);
//            }
//
//            if (!directory.delete()) {
//                String message = "Unable to delete directory " + directory + ".";
//                throw new IOException(message);
//            }
//        }
//    }


//    public static void cleanDirectory(File directory) throws IOException {
//        File[] files = verifiedListFiles(directory);
//        IOException exception = null;
//        File[] arr$ = files;
//        int len$ = files.length;
//
//        for (int i$ = 0; i$ < len$; ++i$) {
//            File file = arr$[i$];
//
//            try {
//                forceDelete(file);
//            } catch (IOException var8) {
//                exception = var8;
//            }
//        }
//
//        if (null != exception) {
//            throw exception;
//        }
//    }

    private static File[] verifiedListFiles(File directory) throws IOException {
        String message;
        if (!directory.exists()) {
            message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        } else if (!directory.isDirectory()) {
            message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        } else {
            File[] files = directory.listFiles();
            if (files == null) {
                throw new IOException("Failed to list contents of " + directory);
            } else {
                return files;
            }
        }
    }

    public static String readFileToString(File file, Charset encoding) throws IOException {
        FileInputStream in = null;

        String var3;
        try {
            in = openInputStream(file);
            var3 = IOUtils.toString(in, Charsets.toCharset(encoding));
        } finally {
            IOUtils.closeQuietly(in);
        }

        return var3;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String readFileToString(File file) throws IOException {
        return readFileToString(file, Charset.defaultCharset());
    }

    public static List<String> readLines(File file, Charset encoding) throws IOException {
        FileInputStream in = null;

        List var3;
        try {
            in = openInputStream(file);
            var3 = IOUtils.readLines(in, Charsets.toCharset(encoding));
        } finally {
            IOUtils.closeQuietly(in);
        }

        return var3;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static List<String> readLines(File file) throws IOException {
        return readLines(file, Charset.defaultCharset());
    }

    public static void writeStringToFile(File file, String data, Charset encoding, boolean append) throws IOException {
        FileOutputStream out = null;

        try {
            out = openOutputStream(file, append);
            IOUtils.write(data, out, encoding);
            out.close();
        } finally {
            IOUtils.closeQuietly(out);
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void writeStringToFile(File file, String data) throws IOException {
        writeStringToFile(file, data, Charset.defaultCharset(), false);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void writeStringToFile(File file, String data, boolean append) throws IOException {
        writeStringToFile(file, data, Charset.defaultCharset(), append);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void write(File file, CharSequence data) throws IOException {
        write(file, data, Charset.defaultCharset(), false);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void write(File file, CharSequence data, boolean append) throws IOException {
        write(file, data, Charset.defaultCharset(), append);
    }

    public static void write(File file, CharSequence data, Charset encoding, boolean append) throws IOException {
        String str = data == null ? null : data.toString();
        writeStringToFile(file, str, encoding, append);
    }

//    public static void forceDelete(File file) throws IOException {
//        if (file.isDirectory()) {
//            deleteDirectory(file);
//        } else {
//            boolean filePresent = file.exists();
//            if (!file.delete()) {
//                if (!filePresent) {
//                    throw new FileNotFoundException("File does not exist: " + file);
//                }
//
//                String message = "Unable to delete file: " + file;
//                throw new IOException(message);
//            }
//        }
//
//    }

//    public static void forceDeleteOnExit(File file) throws IOException {
//        if (file.isDirectory()) {
//            deleteDirectoryOnExit(file);
//        } else {
//            file.deleteOnExit();
//        }
//
//    }

//    private static void deleteDirectoryOnExit(File directory) throws IOException {
//        if (directory.exists()) {
//            directory.deleteOnExit();
//            if (!isSymlink(directory)) {
//                cleanDirectoryOnExit(directory);
//            }
//
//        }
//    }

//    private static void cleanDirectoryOnExit(File directory) throws IOException {
//        File[] files = verifiedListFiles(directory);
//        IOException exception = null;
//        File[] arr$ = files;
//        int len$ = files.length;
//
//        for (int i$ = 0; i$ < len$; ++i$) {
//            File file = arr$[i$];
//
//            try {
//                forceDeleteOnExit(file);
//            } catch (IOException var8) {
//                exception = var8;
//            }
//        }
//
//        if (null != exception) {
//            throw exception;
//        }
//    }

//    private static long sizeOfDirectory0(File directory) {
//        File[] files = directory.listFiles();
//        if (files == null) {
//            return 0L;
//        } else {
//            long size = 0L;
//            File[] arr$ = files;
//            int len$ = files.length;
//
//            for (int i$ = 0; i$ < len$; ++i$) {
//                File file = arr$[i$];
//
//                try {
//                    if (!isSymlink(file)) {
//                        size += sizeOf0(file);
//                        if (size < 0L) {
//                            break;
//                        }
//                    }
//                } catch (IOException var9) {
//                }
//            }
//
//            return size;
//        }
//    }

//    private static long sizeOf0(File file) {
//        return file.isDirectory() ? sizeOfDirectory0(file) : file.length();
//    }

//    private static BigInteger sizeOfDirectoryBig0(File directory) {
//        File[] files = directory.listFiles();
//        if (files == null) {
//            return BigInteger.ZERO;
//        } else {
//            BigInteger size = BigInteger.ZERO;
//            File[] arr$ = files;
//            int len$ = files.length;
//
//            for (int i$ = 0; i$ < len$; ++i$) {
//                File file = arr$[i$];
//
//                try {
//                    if (!isSymlink(file)) {
//                        size = size.add(sizeOfBig0(file));
//                    }
//                } catch (IOException var8) {
//                }
//            }
//
//            return size;
//        }
//    }

//    private static BigInteger sizeOfBig0(File fileOrDir) {
//        return fileOrDir.isDirectory() ? sizeOfDirectoryBig0(fileOrDir) : BigInteger.valueOf(fileOrDir.length());
//    }

//    public static boolean isSymlink(File file) throws IOException {
//        if (Java7Support.isAtLeastJava7()) {
//            return Java7Support.isSymLink(file);
//        } else if (file == null) {
//            throw new NullPointerException("File must not be null");
//        } else if (FilenameUtils.isSystemWindows()) {
//            return false;
//        } else {
//            File fileInCanonicalDir = null;
//            if (file.getParent() == null) {
//                fileInCanonicalDir = file;
//            } else {
//                File canonicalDir = file.getParentFile().getCanonicalFile();
//                fileInCanonicalDir = new File(canonicalDir, file.getName());
//            }
//
//            return fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile()) ? isBrokenSymlink(file) : true;
//        }
//    }

    private static boolean isBrokenSymlink(File file) throws IOException {
        if (file.exists()) {
            return false;
        } else {
            final File canon = file.getCanonicalFile();
            File parentDir = canon.getParentFile();
            if (parentDir != null && parentDir.exists()) {
                File[] fileInDir = parentDir.listFiles(new FileFilter() {
                    public boolean accept(File aFile) {
                        return aFile.equals(canon);
                    }
                });
                return fileInDir != null && fileInDir.length > 0;
            } else {
                return false;
            }
        }
    }

    static {
        ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);
        ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
        ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
        ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
        ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);
        ONE_ZB = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(1152921504606846976L));
        ONE_YB = ONE_KB_BI.multiply(ONE_ZB);
        EMPTY_FILE_ARRAY = new File[0];
    }
}
