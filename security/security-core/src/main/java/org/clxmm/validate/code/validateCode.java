package org.clxmm.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 短信验证码
 *
 * @author clx
 * @date 2020-06-27 18:30
 */
@Data
public class validateCode {


    private String code;

    private LocalDateTime expireTime;

    /**
     * @param code
     * @param expireTime 多少秒之后过期
     */
    public validateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }


    public validateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
