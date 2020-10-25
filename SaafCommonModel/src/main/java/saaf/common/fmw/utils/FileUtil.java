package saaf.common.fmw.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Administrator on 2016/12/6.
 */
public class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public FileUtil() {
    }

    public static InputStream getUserResourceAsStream(String resource) {
        boolean hasLeadingSlash = resource.startsWith("/");
        String stripped = hasLeadingSlash ? resource.substring(1) : resource;
        InputStream stream = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            stream = classLoader.getResourceAsStream(resource);
            if (stream == null && hasLeadingSlash) {
                stream = classLoader.getResourceAsStream(stripped);
            }
        }

        if (stream == null) {
            stream = Environment.class.getClassLoader().getResourceAsStream(resource);
        }

        if (stream == null && hasLeadingSlash) {
            stream = Environment.class.getClassLoader().getResourceAsStream(stripped);
        }

        if (stream == null) {
            throw new HibernateException(resource + " not found");
        } else {
            return stream;
        }
    }

    public static URL getConfigurationInputStream(String resource) {
        return getResourceAsStream(resource);
    }

    private static URL getResourceAsStream(String resource) {
        URL url = null;
        String stripped = resource.startsWith("/") ? resource.substring(1) : resource;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            url = classLoader.getResource(stripped);
        }

        if (url == null) {
            url = Environment.class.getResource(resource);
        }

        if (url == null) {
            url = Environment.class.getClassLoader().getResource(stripped);
        }

        if (url == null) {
            ;
        }

        return url;
    }

    public static String writeString2FileSys(String filePath, String fileContent) throws IOException {
        File fileObject = new File(filePath);
        fileObject.createNewFile();
        byte[] bytes = new byte[1024];
        bytes = fileContent.getBytes();
        int length_ = fileContent.length();
        FileOutputStream fileOutputStream = new FileOutputStream(fileObject);
        fileOutputStream.write(bytes, 0, length_);
        fileOutputStream.close();
        return fileObject.getPath();
    }

    public static void writeInputStream2FileSys(InputStream inputStream, String targetFilePath) throws FileNotFoundException, IOException {
        FileOutputStream outputStream = new FileOutputStream(targetFilePath, false);
        byte[] flush = new byte[1024];
        boolean len = false;

        int len1;
        while (-1 != (len1 = inputStream.read(flush))) {
            new String(flush, 0, len1);
            outputStream.write(flush, 0, len1);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public static void copyFile(String sourcePath, String targetPath) throws FileNotFoundException, IOException {
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        copFile(sourceFile, targetFile);
    }

    public static void copFile(File sourceFile, File targetFile) throws FileNotFoundException, IOException {
        if (sourceFile.isFile()) {
            FileInputStream inputStream = new FileInputStream(sourceFile);
            FileOutputStream outputStream = new FileOutputStream(targetFile, true);
            byte[] flush = new byte[1024];
            boolean len = false;

            int len1;
            while (-1 != (len1 = inputStream.read(flush))) {
                String outputValue = new String(flush, 0, len1);
                System.out.println(outputValue);
                outputStream.write(flush, 0, len1);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
    }

    private static void write() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String str = "say Hello";
        byte[] strs = str.getBytes();
        bos.write(strs, 0, strs.length);

        try {
            bos.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    private static void read() throws IOException {
        String str = "say Hello";
        byte[] strs = str.getBytes();
        BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(strs));
        byte[] flush = new byte[1024];
        boolean len = false;

        int len1;
        while (-1 != (len1 = is.read(flush))) {
            System.out.println(new String(flush, 0, len1));
        }

        try {
            is.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public static void unzipFile(String directory, File zip) throws Exception {
        try {
            ZipInputStream e = new ZipInputStream(new FileInputStream(zip));
            ZipEntry ze = e.getNextEntry();
            File parent = new File(directory);
            if (!parent.exists() && !parent.mkdirs()) {
                throw new Exception("创建解压目录 \"" + parent.getAbsolutePath() + "\" 失败");
            }

            while (ze != null) {
                String name = ze.getName();
                System.out.println(name);
                File child = new File(parent, name);
                FileOutputStream output = new FileOutputStream(child);
                byte[] buffer = new byte[10240];
                boolean bytesRead = false;

                int bytesRead1;
                while ((bytesRead1 = e.read(buffer)) > 0) {
                    output.write(buffer, 0, bytesRead1);
                }

                output.flush();
                output.close();
                ze = e.getNextEntry();
            }

            e.close();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {


    }

    public static List<String> queryDirFileName(String targetPath) {
        ArrayList fileNames = new ArrayList();
        File file = new File(targetPath);
        File[] files = file.listFiles();

        for (int i = 0; i < files.length; ++i) {
            File file_ = files[i];
            System.out.println("gavinYuan_" + file_.getName());
            fileNames.add(file_.getName());
        }

        return fileNames;
    }
}
