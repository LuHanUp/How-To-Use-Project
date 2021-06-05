package top.luhancc.qr.code.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
            String imageStr = ZxingUtil.createImage(text, inputStream);
            return imageStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
