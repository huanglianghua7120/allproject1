package saaf.common.fmw.base.utils;

import com.base.sie.common.utils.StringUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PrintUtil {

    private static final Logger logger = LoggerFactory.getLogger(PrintUtil.class);

    /*public static void drawImage() {
        try {

            //PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
            String s = "ZDesigner ZT410-300dpi ZPL";
            PrintService ps = getPrintService(s);

            for (int i=0;i<5;i ++) {

                PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                pras.add(OrientationRequested.PORTRAIT);

                //pras.add(new Copies(count));
                pras.add(PrintQuality.HIGH);
                DocAttributeSet das = new HashDocAttributeSet();

                // 设置打印纸张的大小（以毫米为单位）
                das.add(new MediaPrintableArea(0, 0, 80, 50, MediaPrintableArea.MM));

                BufferedImage img = null;

                img = createImage(80, 1600, 1000);

                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(img, "jpg", os);
                InputStream is = new ByteArrayInputStream(os.toByteArray());

                Doc doc = new SimpleDoc(is, DocFlavor.INPUT_STREAM.JPEG, das);


                DocPrintJob job = ps.createPrintJob();

                job.print(doc, pras);
            }
        } catch (Exception e) {
            logger.error("未知错误 : {}", e);
        }
    }*/

    /**
     * Description：获取打印机信息
     * @param printURI 打印机名称
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-22     吴嘉利             创建
     * =======================================================================
     */
    public static PrintService getPrintService(String printURI){
        PrintService ps = null;
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null,null);
        if (services != null && services.length > 0) {
            for (PrintService service : services) {
                if (printURI.equals(service.getName())) {
                    ps = service;
                    break;
                }
            }
        }
        return ps;
    }


/*    public static void main(String[] args) {
        PrintUtil.drawImage();
    }*/

    /**
     * Description：创建图片
     * @param fontSize 字体大小
     * @param width 图片宽度
     * @param height 图片高度
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-22     吴嘉利             创建
     * =======================================================================
     */
    /*public static BufferedImage createImage(int fontSize, Integer width, Integer height) throws Exception {
        String itemNumberShow = "料号: "+"123";
        String numShow = "数量: "+"1";
        String expDateShow="过期日期: "+"2000-01-01";
        String lotNumShow = "批号: "+"2";
        //String descriptionShow = "描述: "+"123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
        String descriptionShow = "描述: "+"一二三四五六七八九十1234567890一二三四五六七八九十一二三四五1234567890六七八九十一二1234567890三四五六七八九十一二三四五六七八九十一二三四五六七八九十";

        Font font = new Font("宋体",Font.BOLD,fontSize);

        // 创建图片
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);

        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setClip(0, 0, width, height);
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
        g.setColor(Color.black);// 在换成黑色
        g.setFont(font);// 设置画笔字体

        FontMetrics fm = g.getFontMetrics(font);
        int ascent = fm.getAscent();  //获取字体高度
        int y = 2 + ascent;	//距离顶部空2个像素
        // 文字叠加,自动换行叠加
        int tempX = 100;
        int tempY = 630;
        int tempCharLen = 0;// 单字符长度
        int tempLineLen = 0;// 单行字符总长度临时计算
        //二维码
        ByteArrayInputStream bais = new ByteArrayInputStream(CodeUtil.getBarCode("RSGS104111203GS103000GSW170426A12GS170426GS170627GS11111111GS20GSGS 0001GSEOT"));
        BufferedImage img = ImageIO.read(bais);
        g.drawImage(img, 80, 50, 360, 360, null);
        g.drawString(itemNumberShow, 500, 150);// 最后叠加余下的文字
        g.drawString(numShow, 500, 250);// 最后叠加余下的文字
        g.drawString(expDateShow, 500, 350);// 最后叠 加余下的文字
        g.drawString(lotNumShow, 100, 530);// 最后叠加余下的文字

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < descriptionShow.length(); i++) {
            char tempChar = descriptionShow.charAt(i);
            tempCharLen = getCharLength(tempChar, 100);
            tempLineLen += tempCharLen;
            if (tempLineLen >= width-fontSize) {
                // 长度已经满一行,进行文字叠加
                g.drawString(sb.toString(), tempX, tempY);

                sb.delete(0, sb.length());// 清空内容,重新追加

                tempY += fontSize;

                tempLineLen = 0;
            }
            sb.append(tempChar);// 追加字符
        }
        if(!StringUtil.isEmpty(sb.toString())){
            g.drawString(sb.toString(), tempX, tempY);// 最后叠加余下的文字
        }
        g.dispose();
        return image;
    }*/

    /**
     * Description：获取字符长度
     * @param tempChar 字符
     * @param fontSize 字体大小
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-22     吴嘉利             创建
     * =======================================================================
     */
    public static int getCharLength(char tempChar, int fontSize){
        if (isChinese(tempChar)){
            return fontSize;
        }else {
            return fontSize / 2;
        }
    }

    /**
     * Description：判断是否中文字符
     * @param c 字符
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-22     吴嘉利             创建
     * =======================================================================
     */
    private static final boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

}
