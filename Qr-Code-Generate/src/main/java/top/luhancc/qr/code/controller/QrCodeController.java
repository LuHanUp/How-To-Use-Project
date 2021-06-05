package top.luhancc.qr.code.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.luhancc.qr.code.util.QRCodeUtil;
import top.luhancc.qr.code.util.ZxingUtil;

import java.io.InputStream;

/**
 * @author luHan
 * @create 2021/6/4 17:55
 * @since 1.0.0
 */
@RestController
public class QrCodeController {

    @RequestMapping("/upload")
    public String createQrCode(@RequestParam(value = "logoFile", required = false) MultipartFile file,
                               @RequestParam(value = "text") String text,
                               @RequestParam(value = "flag") String flag) {
        try {
            InputStream inputStream = file == null ? null : file.getInputStream();
            if (inputStream == null && "normal".equals(flag)) {
                return ZxingUtil.createImage(text, null);
            } else {
                if ("normal".equals(flag)) {
                    return QRCodeUtil.normalQRCode(text);
                } else if ("logo".equals(flag)) {
                    return QRCodeUtil.logoQRCode(text, inputStream);
                } else if ("color".equals(flag)) {
                    return QRCodeUtil.colorQRCode(text);
                } else if ("background".equals(flag)) {
                    return QRCodeUtil.backgroundQRCode(text, inputStream);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
