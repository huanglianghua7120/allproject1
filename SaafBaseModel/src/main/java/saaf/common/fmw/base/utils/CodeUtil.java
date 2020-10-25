package saaf.common.fmw.base.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;



/**
 * @author pillar
 * @date 2019-05-08
 */
public class CodeUtil {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    public static String prefix = "RS";
    public static String suffix = "EOT";
    public static String sp = "GS";
    /**
     *二维码实现
     * @param msg
     */
    public static byte[] getBarCode(String msg){
        try {

            String format = "png";
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map<EncodeHintType,String> map =new HashMap<EncodeHintType, String>();
            //设置编码 EncodeHintType类中可以设置MAX_SIZE， ERROR_CORRECTION，CHARACTER_SET，DATA_MATRIX_SHAPE，AZTEC_LAYERS等参数
            map.put(EncodeHintType.CHARACTER_SET,"UTF-8");
            map.put(EncodeHintType.MARGIN,"2");
            //生成二维码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(msg, BarcodeFormat.QR_CODE,280,280,map);
            BufferedImage image = toBufferedImage(bitMatrix);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", os);
            return os.toByteArray();
        }catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void main(String[] args) {
        String msg = "pillar666";
        String path = "D:\\pillar\\pilar666.png";
        //codeUtil.getBarCode(msg,path);
    }
}
