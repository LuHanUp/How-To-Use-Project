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

    /**
     * 生成指定背景图片的二维码
     *
     * @param text  二维码内容
     * @param image 背景图片
     * @return
     */
    public static String backgroundQRCode(String text, InputStream image) throws Exception {
        // 填充模式，需要设置二维码的位置，不然会跑到左上角
//        return QrCodeGenWrapper.of(text)
//                .setBgImg(image)
//                .setBgOpacity(0.5f) // 设置背景图片的透明度
//                .setBgStyle(QrCodeOptions.BgImgStyle.FILL) // 填充模式添加背景图片,默认是全覆盖模式
//                .setBgW(500)
//                .setBgH(500)
//                .setBgStartX(130)
//                .setBgStartY(130)
//                .asString();

        // 渲染模式
        return QrCodeGenWrapper.of(text)
                .setBgImg(image)
                .setBgOpacity(0.5f) // 设置背景图片的透明度
                .setBgStyle(QrCodeOptions.BgImgStyle.PENETRATE) // 填充模式添加背景图片,默认是全覆盖模式
                .asString();
    }
}
