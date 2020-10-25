package saaf.common.fmw.rule.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 2017/7/6.
 */
public class test {

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        File directory = new File(""); //设定为当前文件夹
        System.out.println(directory.getCanonicalPath()); //获取标准的路径
        System.out.println(directory.getAbsolutePath()); //获取绝对路径

    }
}
