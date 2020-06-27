package org.clxmm.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图像验证码类
 *
 * @author clx
 * @date 2020-06-27 18:30
 */
@Data
public class ImageCode {


    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    /**
     * @param image
     * @param code
     * @param expireTime 多少秒之后过期
     */
    public ImageCode(BufferedImage image, String code, int expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }


    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
