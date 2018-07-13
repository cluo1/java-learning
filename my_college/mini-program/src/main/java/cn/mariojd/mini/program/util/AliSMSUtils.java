package cn.mariojd.mini.program.util;

import cn.mariojd.mini.program.exception.MessageCodes;
import cn.mariojd.mini.program.exception.ValidationException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jdq on 2017/7/7.
 */
public class AliSMSUtils {

    private static Logger logger = LoggerFactory.getLogger(AliSMSUtils.class);

    private static final String OK = "OK";
    private static final String PRODUCT = "Dysmsapi";
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAIrlFpIqebLtYy";
    private static final String ACCESS_KEY_SECRET = "vaD6KgSoTpD6iNxNyWSdnKFGwDVnSY";
    private static final String CN_HANGZHOU = "MY4cFQiqoh7WBRQ6JHIkiiLqqUdpvS";
    private static final String SIGN_NAME = "大学课堂";
    private static final String DEFAULT_CONNECT_TIMEOUT = "sun.net.client.defaultConnectTimeout";
    private static final String DEFAULT_READ_TIMEOUT = "sun.net.client.defaultReadTimeout";
    private static final String THOUSAND = "10000";

    /**
     * 发送阿里云手机验证码
     *
     * @param code
     * @param phone
     */
    public static void sendSMSCode(String code, String phone) {
        logger.info("start send ali sms : phone is {} , verCode is : {}", phone, code);
        try {
            //设置超时时间-可自行调整
            System.setProperty(DEFAULT_CONNECT_TIMEOUT, THOUSAND);
            System.setProperty(DEFAULT_READ_TIMEOUT, THOUSAND);
            //初始化ascClient,暂时不支持多region
            IClientProfile profile = DefaultProfile.getProfile(CN_HANGZHOU, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            DefaultProfile.addEndpoint(CN_HANGZHOU, CN_HANGZHOU, PRODUCT, DOMAIN);
            final IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            final SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(SIGN_NAME);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode("SMS_130835522");
            //可选:模板中的变量替换JSON串
            request.setTemplateParam("{'code':'" + code + "'}");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            logger.info("sendSmsResponse.getMessage()：{}, sendSmsResponse.getCode()：{}",
                    sendSmsResponse.getMessage(), sendSmsResponse.getCode());
            if (OK.equals(sendSmsResponse.getMessage()) || OK.equals(sendSmsResponse.getCode())) {
                logger.info("------------验证码发送成功------------------");
            } else {
                logger.info("-------------验证码发送失败-----------------");
                throw new ValidationException(MessageCodes.AUTH_PHOCAPTCHA_SEND_FAIL);
            }
        } catch (ClientException e) {
            logger.info("-------------验证码异常发送失败-----------------", e);
            throw new ValidationException(MessageCodes.AUTH_PHOCAPTCHA_SEND_FAIL);
        }
    }

}
