package cn.mariojd.mini.program.service;

import cn.mariojd.mini.program.dto.WeChatUser;
import cn.mariojd.mini.program.entity.*;
import cn.mariojd.mini.program.util.WeChatUtil;
import cn.mariojd.mini.program.vo.req.*;
import cn.mariojd.mini.program.vo.resp.AuthorizeResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MiniService {

    private Logger logger = LoggerFactory.getLogger(MiniService.class);

    @Resource
    private WeChatUtil weChatUtil;

    @Resource
    private AliSMSWebService aliSMSWebService;

//    @Resource
//    private ShiroTokenService shiroTokenService;

    @Resource
    private RestTemplate restTemplate;

    @Value("${guns.admin.port}")
    private Integer port;

    /**
     * 微信授权登录
     *
     * @param authorizeVO
     * @return 返回认证票据token
     */
    public AuthorizeResultVO authorize(AuthorizeVO authorizeVO) {
        WeChatUser weChatUser = weChatUtil.authorize(authorizeVO);
        Assert.notNull(weChatUser, "授权失败");

        String url = "http://127.0.0.1:" + port + "/mini/user/openId/{openId}";
        MiniUser miniUser = restTemplate.getForObject(url, MiniUser.class, weChatUser.getOpenId());
        if (Objects.isNull(miniUser)) {
            miniUser = new MiniUser();
            BeanUtils.copyProperties(weChatUser, miniUser);

            url = "http://127.0.0.1:" + port + "/mini/user";
            miniUser = restTemplate.postForObject(url, miniUser, MiniUser.class);
            logger.info(" response mini user is {}", miniUser);
        }

        int userId = miniUser.getId();
        //shiroTokenService.afterLogout(userId);
        String token = StringUtils.deleteAny(UUID.randomUUID().toString(), "-");
        //shiroTokenService.afterLogin(userId, token);
        return new AuthorizeResultVO(token);
    }

    /**
     * 发送手机验证码
     *
     * @param phone
     */
    public void sendVerCode(String phone) {
        logger.info("-------------------------校验phone：{}------------------------------", phone);
        String url = "http://127.0.0.1:" + port + "/mini/user/phone/{phone}";
        MiniUser miniUser = restTemplate.getForObject(url, MiniUser.class, phone);
        if (Objects.isNull(miniUser)) {
            //手机号可以注册
            //aliSMSWebService.getVerCode(phone);
            logger.info("-------------------------验证码发送成功-------------------------------");
        }
    }

    /**
     * 绑定手机号
     *
     * @param phoneVO
     * @param userId
     */
    public void bindPhone(BindPhoneVO phoneVO, int userId) {
        String phone = phoneVO.getPhone();
//        if (aliSMSWebService.checkVerCode(phone, phoneVO.getCode())) {
//            logger.info("-------------------------通过验证码验证--------------------");
//            String url = "http://127.0.0.1:" + port + "/mini/user/{uid}/phone/{phone}";
//            restTemplate.put(url, null, userId, phone);
//        } else {
//            logger.info("-------------------------通过验证码验证错误--------------------");
//            throw new ValidationException(MessageCodes.AUTH_PICCAPTCHA_WRONG);
//        }
    }

    /**
     * 获得所有省份信息
     *
     * @return
     */
    public List<Province> getProvinces() {
        String url = "http://127.0.0.1:" + port + "/mini/provinces";
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        return responseEntity.getBody();
    }

    /**
     * 获得所有学校信息
     *
     * @return
     */
    public List<School> getSchools() {
        String url = "http://127.0.0.1:" + port + "/mini/schools";
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        return responseEntity.getBody();
    }

    /**
     * 保存选择的省份、学校和角色信息
     *
     * @param vo
     * @param userId
     */
    public void submitConfirmInfo(ProvAndSchoolAndRoleVO vo, int userId) {
        String url = "http://127.0.0.1:" + port + "/mini/province/school/{uid}";
        restTemplate.put(url, vo, userId);
    }

    /**
     * 获得所有班级信息
     *
     * @return
     */
    public List<Classes> getClasses() {
        String url = "http://127.0.0.1:" + port + "/mini/classes";
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        return responseEntity.getBody();
    }

    /**
     * 老师提交班级信息
     *
     * @param classesVO
     * @param userId
     */
    public void teacherSubmitClassesInfo(TeacherClassesVO classesVO, int userId) {
        String url = "http://127.0.0.1:" + port + "/mini/classes/user/{uid}";
        ClassesAndUserInfoVO infoVO = new ClassesAndUserInfoVO();
        BeanUtils.copyProperties(classesVO, infoVO);
        restTemplate.put(url, infoVO, userId);
    }

    /**
     * 学生提交班级信息
     *
     * @param classesVO
     * @param userId
     */
    public void studentSubmitClassesInfo(StudentClassesVO classesVO, int userId) {
        String url = "http://127.0.0.1:" + port + "/mini/classes/user/{uid}";
        ClassesAndUserInfoVO infoVO = new ClassesAndUserInfoVO();
        BeanUtils.copyProperties(classesVO, infoVO);
        restTemplate.put(url, infoVO, userId);
    }

    /**
     * 获得个人信息
     *
     * @param userId
     */
    public MiniUser getInfo(int userId) {
        String url = "http://127.0.0.1:" + port + "/mini/user/{uid}";
        return restTemplate.getForObject(url, MiniUser.class, userId);
    }

    /**
     * 获得班级的公告信息
     */
    public List<Announcement> getAnnouncements(int cid) {
        String url = "http://127.0.0.1:" + port + "/mini/{cid}/announcements";
        ResponseEntity<List> entity = restTemplate.getForEntity(url, List.class, cid);
        return entity.getBody();
    }

    /**
     * 老师获得所有的班级信息
     *
     * @param uid
     */
    public List<Classes> getClassesByUid(int uid) {
        String url = "http://127.0.0.1:" + port + "/mini/classes/{uid}";
        ResponseEntity<List> entity = restTemplate.getForEntity(url, List.class, uid);
        return entity.getBody();
    }

}
