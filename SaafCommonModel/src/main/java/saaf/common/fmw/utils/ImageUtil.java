package saaf.common.fmw.utils;


import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;


public class ImageUtil {

    // 水印透明度
    private static float alpha = 0.5f;
    // 水印横向位置
    private static int positionWidth = 20;
    // 水印纵向位置
    private static int positionHeight = 20;
    // 水印文字字体
    private static Font font = new Font("宋体", Font.BOLD, 20);
    // 水印文字颜色
    private static Color color = Color.RED;


    /**
     *
     * @param logoText
     * @param inputStream
     * @param fileName
     * @return
     */
    public static InputStream imageWaterMark(String logoText, InputStream inputStream, String fileName) {
        return imageWaterMark(logoText, inputStream, fileName, null);
    }

    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     *
     * @param logoText
     * @param inputStream
     * @param fileName
     * @param degree
     */
    public static InputStream imageWaterMark(String logoText, InputStream inputStream, String fileName, Integer degree) {
        if (inputStream == null)
            return null;
        try {
            // 1、源图片
            Image srcImg = ImageIO.read(inputStream);
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree), (double)buffImg.getWidth() / 2, (double)buffImg.getHeight() / 2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            g.drawString(logoText, positionWidth, positionHeight);
            // 9、释放资源
            g.dispose();

            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            ImageIO.write(buffImg, ext, bs);
            InputStream resulstream = new ByteArrayInputStream(bs.toByteArray());
            bs.close();
            return resulstream;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }


    /**
     * 直接指定压缩后的宽高：
     * (先保存原文件，再压缩、上传)

     * @param width 压缩后的宽度
     * @param height 压缩后的高度
     * @param quality 压缩质量
     * @return
     */
    public static InputStream zipImageFile(InputStream inputStream, int width, int height, float quality) {
        if (inputStream == null) {
            return null;
        }
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ScaleParameter scaleParam = new ScaleParameter(width, height);
            ImageRender rr = new ReadRender(inputStream);
            ImageRender sr = new ScaleRender(rr, scaleParam);
            WriteRender wr = new WriteRender(sr, bs);
            wr.render();
            InputStream resultStream = new ByteArrayInputStream(bs.toByteArray());
            wr.dispose();
            bs.close();
            return resultStream;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    private static byte[] InputStreamTOByte(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int count = -1;
        while ((count = in.read(data, 0, 1024)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return outStream.toByteArray();
    }


    public static String getImageStr(InputStream in) { //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        //读取图片字节数组
        try {
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        //对字节数组Base64编码
        return Base64.encodeBase64String(data);
    }

    //base64字符串转化成图片

    public static byte[] generateImage(String imgStr) throws DecoderException { //对字节数组字符串进行Base64解码并生成图片
        if (StringUtils.isBlank(imgStr)) //图像数据为空
            throw new DecoderException("decode string can not be null");
        //Base64解码
        byte[] b = Base64.decodeBase64(imgStr);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) { //调整异常数据
                b[i] += 256;
            }
        }
        return b;
    }


}
