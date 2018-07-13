package cn.mariojd.mini.program.controller;

import cn.mariojd.mini.program.entity.*;
import cn.mariojd.mini.program.service.MiniService;
import cn.mariojd.mini.program.vo.req.*;
import cn.mariojd.mini.program.vo.resp.AuthorizeResultVO;
import com.luwei.module.shiro.service.IdHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("mini")
public class MiniController {

    private final Logger logger = LoggerFactory.getLogger(MiniController.class);

    @Resource
    private MiniService miniService;

    /**
     * 小程序授权
     *
     * @param authorizeVO
     * @return
     */
    @PostMapping("authorize")
    public AuthorizeResultVO authorize(@RequestBody @Valid AuthorizeVO authorizeVO) {
        return miniService.authorize(authorizeVO);
    }

    /**
     * 手机验证码
     */
    @GetMapping("code")
    public void sendVerCode(@RequestParam String phone) {
        miniService.sendVerCode(phone);
    }

    /**
     * 手机号绑定
     */
    @PutMapping("phone")
    public void bindPhone(@RequestBody @Valid BindPhoneVO phoneVO) {
        miniService.bindPhone(phoneVO, IdHelper.getId());
    }

    /**
     * 省份列表
     */
    @GetMapping("provinces")
    public List<Province> getProvinces() {
        return miniService.getProvinces();
    }

    /**
     * 学校列表
     */
    @GetMapping("schools")
    public List<School> getSchools() {
        return miniService.getSchools();
    }

    /**
     * 提交所选省份、学校等信息
     */
    @PutMapping("province/school")
    public void submitConfirmInfo(@RequestBody @Valid ProvAndSchoolAndRoleVO vo) {
        miniService.submitConfirmInfo(vo, IdHelper.getId());
    }

    /**
     * 班级列表
     */
    @GetMapping("classes")
    public List<Classes> getClasses() {
        return miniService.getClasses();
    }

    /**
     * 老师提交所选班级信息
     */
    @PutMapping("teacher/classes")
    public void teacherSubmitClassesInfo(@RequestBody @Valid TeacherClassesVO classesVO) {
        miniService.teacherSubmitClassesInfo(classesVO, IdHelper.getId());
    }

    /**
     * 学生提交所选班级信息
     */
    @PutMapping("student/classes")
    public void studentSubmitClassesInfo(@RequestBody @Valid StudentClassesVO classesVO) {
        miniService.studentSubmitClassesInfo(classesVO, IdHelper.getId());
    }

    /**
     * 获得个人信息
     */
    @GetMapping("info")
    public MiniUser getInfo() {
        return miniService.getInfo(IdHelper.getId());
    }

    /**
     * 获得班级的公告信息
     */
    @GetMapping("{cid}/announcements")
    public List<Announcement> getAnnouncements(@PathVariable int cid) {
        return miniService.getAnnouncements(cid);
    }

    /**
     * 老师获得所有的班级信息
     */
    @GetMapping("teacher/classes")
    public List<Classes> getClassesByUid() {
        return miniService.getClassesByUid(IdHelper.getId());
    }

}
