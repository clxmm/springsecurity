package org.clxmm.validate.code;

import org.clxmm.properties.core.SecurityProperties;
import org.clxmm.validate.code.image.ImageCode;
import org.clxmm.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author clx
 * @date 2020-06-27 18:42
 */
@RestController
public class ValidateCodeController {


    // 操作session的工具类
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;


    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * 图形验证码的接口
     *
     * @param request
     * @param response
     */
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());

    }

    /**
     * 短信验证码的接口
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        validateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);

        //通过短信服务商发送短信---------> 提供一个短信发送的接口，可以让不同的短信供应商使用

        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");

        smsCodeSender.send(mobile, smsCode.getCode());
    }


    /**
     * 生成图形验证码
     *   ---->改成调用实现类的,实现生成图片的可扩展
     * @param request
     * @return
     */



    /*
    private ImageCode generate(ServletWebRequest request) {


        ImageCodeProperties imageProperties = securityProperties.getCode().getImage();
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", imageProperties.getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", imageProperties.getHeight());

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < imageProperties.getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageCode(image, sRand, imageProperties.getExpireIn());
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
*/

}
