package org.clxmm.validate.code.sms;

/**
 * 默认发送短信验证码的实现
 *
 * @author clx
 * @date 2020-06-29 21:38
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送短信验证码" + code);
    }
}
