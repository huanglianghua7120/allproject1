package saaf.common.fmw.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: ZHJ
 * @date:Created in 11:52 2020/4/14
 * @modify By:
 * @description : base64位进行加密与解密
 */
public class Base64Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(Base64Utils.class);
    /**
     * 将流进行base64加密
     * @param in 输入流
     * @return
     * @throws IOException
     */
    public static String ioToBase64(InputStream in) throws IOException {
        String strBase64 = null;
        try {
            byte[] bytes = new byte[in.available()];
            // 将文件中的内容读入到数组中
            in.read(bytes);
            //将字节流数组转换为字符串
            strBase64 = org.springframework.util.Base64Utils.encodeToString(bytes);
            in.close();
        } catch (IOException ioe) {
        }
        return strBase64;
    }

    /**
     * base64解密
     * @param encodeStr 密文字符串
     * @return 返回明文
     */
    public static String decodeToString(String encodeStr){
        byte[] b = null;
        String result = null;
        if(null != encodeStr){
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(encodeStr);
                result = new String(b,"utf-8");
            }catch (Exception e){
                LOGGER.error(e.getMessage());
            }
        }
        return result;
    }

    /**
     * base64加密
     * @param decodeStr 明文字符串
     * @return 返回密文
     */
    public static String encodeToString(String decodeStr){
        byte[] b = null;
        String result = null;
        if(null != decodeStr){
            BASE64Encoder encoder = new BASE64Encoder();
            try{
                b = decodeStr.getBytes("utf-8");
                result = encoder.encode(b);
            }catch (Exception e){
                LOGGER.error(e.getMessage());
            }
        }
        return result;
    }

    /**
     * base64解密，根据明文前缀解密之后，返回明文（前缀密文+明文+前缀密文）
     * @param pre 明文前缀
     * @param encodeStr 密文字符串
     * @return 返回明文
     */
    public static String decode2PreToString(String pre,String encodeStr){
        String result = null;
        if(null != pre && null != encodeStr){
            //解密
            String str = decodeToString(encodeStr);
            //加密后的前缀
            String preValue = encodeToString(pre);
            result = str.substring(preValue.length(),(str.length()-preValue.length()));
        }
        return result;
    }
    //测试示例
    public static void main(String[] args) {
        String pre = "赛意御捷集团";
        String passWord = "U9571w";
        String preValue = Base64Utils.encodeToString(pre);
        System.out.println(preValue);
        String v = Base64Utils.encodeToString(preValue+passWord+preValue);
        System.out.println(v);
        System.out.println(Base64Utils.decode2PreToString(pre,v));
    }
}
