package top.luhancc.qr.code.util;

import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeOptions;
import com.google.zxing.WriterException;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author luHan
 * @create 2021/6/5 21:05
 * @since 1.0.0
 */
public final class QRCodeUtil {

    /**
     * 生成普通的二维码
     * <p>
     * 返回的是Base64编码的图片数据
     *
     * @param text 二维码暗含的内容
     * @return
     */
    public static String normalQRCode(String text) throws Exception {
        return QrCodeGenWrapper.of(text).asString();
    }

    /**
     * 生成带logo的二维码
     *
     * @param text      二维码内容
     * @param logoImage logo图片
     * @return
     * @throws Exception
     */
    public static String logoQRCode(String text, InputStream logoImage) throws Exception {
        return QrCodeGenWrapper.of(text)
                .setLogo(logoImage)
                .setLogoRate(7)
                .setLogoStyle(QrCodeOptions.LogoStyle.ROUND) // 设置logo图片为圆角
                .setLogoBorder(true)
                .asString();
    }

    /**
     * 生成彩色的二维码
     *
     * @param text 二维码内容
     * @return
     * @throws IOException
     * @throws WriterException
     */
    public static String colorQRCode(String text) throws Exception {
        return QrCodeGenWrapper.of(text)
                .setDrawPreColor(Color.blue) // 设置二维码那个黑色点的颜色
                .asString();
    }
}
