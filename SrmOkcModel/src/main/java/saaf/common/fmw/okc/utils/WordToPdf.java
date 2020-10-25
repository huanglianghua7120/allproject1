package saaf.common.fmw.okc.utils;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.apache.commons.io.IOUtils;
import org.jodconverter.JodConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.document.DocumentFormat;
import org.jodconverter.office.OfficeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：WordToPdf.java
 * Description：word转pdf的工具类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/6/17      欧阳岛          创建
 * <p>
 * ==============================================================================
 */
public class WordToPdf {
    private static Logger logger = LoggerFactory.getLogger(WordToPdf.class);

    public static void word2Pdf(InputStream inStream, String desPath, String ext) throws IOException {
        // 输出文件目录
        File outputFile = new File(desPath);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }
        try {
            // 转换文档到pdf
            long time = System.currentTimeMillis();
            DocumentFormat documentFormat = DefaultDocumentFormatRegistry.DOCX;
            if ("doc".equalsIgnoreCase(ext)) {
                documentFormat = DefaultDocumentFormatRegistry.DOC;
            }
            JodConverter.convert(inStream, true).as(documentFormat).to(outputFile).execute();
            logger.info("文件：转换PDF：{}完成，用时{}毫秒！", desPath, System.currentTimeMillis() - time);
        } catch (OfficeException e) {
            //e.printStackTrace();
            logger.warn("文件：转换PDF：{}失败！", desPath);
        }
    }

    public static void imageWatermark(BufferedOutputStream os, String inputPath, String imagePath) throws IOException, DocumentException {
        imageWatermark(os, inputPath, imagePath, 100);
    }

    public static void imageWatermark(BufferedOutputStream os, String inputPath, String imagePath, int scalePercent) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(inputPath);
        PdfStamper stamp = new PdfStamper(reader, os);
        PdfGState gs1 = new PdfGState();
        gs1.setFillOpacity(0.1f);// 透明度设置
        URL url = new URL(imagePath);
//        Image image = Image.getInstance(IOUtils.toByteArray(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/images/sielogo.png")));
        Image image = Image.getInstance(IOUtils.toByteArray(url.openStream()));
        image.setRotation(-20);// 旋转 弧度
        image.setRotationDegrees(45);// 旋转 角度
        image.setAbsolutePosition(50, 150);// 坐标
//        image.scaleAbsolute(300, 300);// 自定义大小
        image.scalePercent(scalePercent);// 依照比例缩放

        int n = reader.getNumberOfPages();
        for (int i = 1; i <= n; i++) {
            PdfContentByte pdfContentByte = stamp.getOverContent(i);
            pdfContentByte.setGState(gs1);
            pdfContentByte.addImage(image);
        }

        stamp.close();
        reader.close();
        // 删除不带水印文件
        File tempfile = new File(inputPath);
        if (tempfile.exists()) {
            tempfile.delete();
        }

    }
}
