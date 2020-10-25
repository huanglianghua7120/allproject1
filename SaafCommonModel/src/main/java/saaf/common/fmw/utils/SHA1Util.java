package saaf.common.fmw.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


public class SHA1Util {
    public SHA1Util() {
    }

    /* 此方法是SAAF自带的，但是加密出来，会出现与前台不一致的情况，停用
    public String getEncrypt(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(str.getBytes("UTF-8"));
        byte[] result = md.digest();

        StringBuffer sb = new StringBuffer();

        for (byte b : result) {
            int i = b & 0xff;
            if (i < 0xf) {
                sb.append(0);
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString().substring(0, 32);
    }
    */

    public String getEncrypt(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        //把字符串转为字节数组
        byte[] b = data.getBytes();
        //使用指定的字节来更新我们的摘要
        md.update(b);
        //获取密文  （完成摘要计算）
        byte[] b2 = md.digest();
        //获取计算的长度
        int len = b2.length;
        //16进制字符串
        String str = "0123456789abcdef";
        //把字符串转为字符串数组
        char[] ch = str.toCharArray();

        //创建一个40位长度的字节数组
        char[] chs = new char[len*2];
        //循环20次
        for(int i=0,k=0;i<len;i++) {
            byte b3 = b2[i];//获取摘要计算后的字节数组中的每个字节
            // >>>:无符号右移
            // &:按位与
            //0xf:0-15的数字
            chs[k++] = ch[b3 >>> 4 & 0xf];
            chs[k++] = ch[b3 & 0xf];
        }
        //字符数组转为字符串
        return (new String(chs)).substring(0, 32);
    }

    public String getAuthCode() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(new Date());
        String authCode = "";
        try {
            authCode = getEncrypt(today);
        } catch (NoSuchAlgorithmException e) {
            //e.printStackTrace();
        }
        return authCode;
    }

    public static void main(String[] args) {
        SHA1Util sha1 = new SHA1Util();
        try {
            System.out.println(sha1.getEncrypt("9658"));
        } catch (NoSuchAlgorithmException e) {
            //e.printStackTrace();
        }
    }
}
