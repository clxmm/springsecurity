package org.clxmm.validate.code.sms;

/**
 * 发送短信验证码的接口
 *
 * @author clx
 * @date 2020-06-29 21:36
 */
public interface SmsCodeSender {

    void send(String mobile, String code);



}
