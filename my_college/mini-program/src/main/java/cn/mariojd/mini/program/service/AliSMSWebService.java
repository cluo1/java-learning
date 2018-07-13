package cn.mariojd.mini.program.service;

import cn.mariojd.mini.program.exception.MessageCodes;
import cn.mariojd.mini.program.exception.RedisKeyPrefix;
import cn.mariojd.mini.program.exception.ValidationException;
import cn.mariojd.mini.program.util.AliSMSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by jdq on 2017/7/7.
 */
@Service
public class AliSMSWebService {

    private static final Logger logger = LoggerFactory.getLogger(AliSMSWebService.class);

    private Random random = new Random();

    @Resource
    protected ValueOperations<String, String> stringRedisTemplate;

    @Autowired
    protected StringRedisTemplate redisTemplate;

    /**
     * 发送验证码
     */
    public void getVerCode(String mobilePhone) {
        //删除原有的验证码
        deleteCaptchaRedisCache(mobilePhone);
        Integer phoneCaptcha = random.nextInt(9000) + 1000;
        String phoneCaptchaStr = phoneCaptcha.toString();
        AliSMSUtils.sendSMSCode(phoneCaptchaStr, mobilePhone);
        logger.info("----------------------生成的手机验证码为：{}--------------------", phoneCaptchaStr);
        //放入reids中，寿命为3分钟
        stringRedisTemplate.set(RedisKeyPrefix.phoneCaptchaUser(mobilePhone), phoneCaptchaStr, 3, TimeUnit.MINUTES);
    }

    /**
     * 验证验证码是否正确
     */
    public boolean checkVerCode(String mobilePhone, String verCode) {
        String rightVerCode = stringRedisTemplate.get(RedisKeyPrefix.phoneCaptchaUser(mobilePhone));
        if (!StringUtils.isEmpty(rightVerCode)) {
            if (rightVerCode.equals(verCode)) {
                //获取后删除
                deleteCaptchaRedisCache(mobilePhone);
                return true;
            } else {
                return false;
            }
        } else {
            throw new ValidationException(MessageCodes.AUTH_PICCAPTCHA_LOST);
        }
    }

    /**
     * 清空redis缓存中的验证码
     */
    private void deleteCaptchaRedisCache(String mobilePhone) {
        final String captcha = stringRedisTemplate.get(RedisKeyPrefix.phoneCaptchaUser(mobilePhone));
        if (!StringUtils.isEmpty(captcha)) {
            redisTemplate.delete(Collections.singletonList(RedisKeyPrefix.phoneCaptchaUser(mobilePhone)));
        }
    }

}
