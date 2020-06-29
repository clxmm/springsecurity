package org.clxmm.validate.code.image;

import lombok.Data;
import org.clxmm.validate.code.validateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图像验证码类
 *
 * @author clx
 * @date 2020-06-27 18:30
 */
@Data
public class ImageCode extends validateCode {


    private BufferedImage image;


    /**
     * @param image
     * @param code
     * @param expireTime 多少秒之后过期
     */
    public ImageCode(BufferedImage image, String code, int expireTime) {
        super(code, expireTime);
        this.image = image;

    }


    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;

    }


}
