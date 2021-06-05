package top.luhancc.qr.code.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luHan
 * @create 2021/6/4 17:57
 * @since 1.0.0
 */
public final class ZxingUtil {

    /**
     * 获取二维码图片  BASE64形式的字符串
     *
     * @param text
     * @param image
     * @return
     * @throws Exception
     */
    public static String createImage(String text, InputStream image) throws Exception {
        Map<EncodeHintType, Object> param = new HashMap<>();
        param.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 二维码精度,纠错程度
        param.put(EncodeHintType.CHARACTER_SET, "UTF-8");// 设置二维码编码格式
        param.put(EncodeHintType.MARGIN, 1);// 边距

        /**
         * String contents 二维码内容
         * BarcodeFormat format 二维码类型
         * int width, int height 宽高
         * Map<EncodeHintType, ?> hints 参数
         */
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 300, 300, param);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 根据坐标向BufferedImage里面写入黑色还是白色
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                boolean b = bitMatrix.get(x, y);
                int rgb = b ? 0xFF000000 : 0xFFFFFFFF;// 如果从BitMatrix根据x、y取到了内容，那么就是黑色，否则就是白色
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        if (image == null) {
            return bufferedImageToString(bufferedImage);
        }
        return null;
    }

    public static String bufferedImageToString(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", bos);
        byte[] bytes = bos.toByteArray();
        try {
            return Base64.encodeBase64String(bytes);
        } finally {
            bos.close();
        }
    }
}
