package com.stylefeng.guns.modular.mini;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.modular.announcement.service.IAnnouncementService;
import com.stylefeng.guns.modular.attendence.service.IAttendenceService;
import com.stylefeng.guns.modular.attendenceDetail.service.IAttendenceDetailService;
import com.stylefeng.guns.modular.classes.service.IClassesService;
import com.stylefeng.guns.modular.course.service.ICourseService;
import com.stylefeng.guns.modular.mini.vo.*;
import com.stylefeng.guns.modular.miniuser.service.IMiniUserService;
import com.stylefeng.guns.modular.province.service.IProvinceService;
import com.stylefeng.guns.modular.school.service.ISchoolService;
import com.stylefeng.guns.modular.student.service.IStudentService;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.teacher.service.ITeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("mini")
public class MiniController {

    private final Logger logger = LoggerFactory.getLogger(MiniController.class);

    @Autowired
    private IMiniUserService miniUserService;

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ISchoolService schoolService;

    @Autowired
    private IClassesService classesService;

    @Autowired
    private IAnnouncementService announcementService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IAttendenceService attendenceService;

    @Autowired
    private IAttendenceDetailService attendenceDetailService;

    /**
     * 根据openId拿MiniUser信息
     *
     * @param openId
     * @return MiniUser
     */
    @GetMapping("user/openId/{openId}")
    public MiniUser getMiniUser(@PathVariable String openId) {
        logger.info("--- 根据openId拿MiniUser信息 {}", openId);
        EntityWrapper<MiniUser> entityWrapper = new EntityWrapper<>();
        Wrapper<MiniUser> wrapper = entityWrapper.eq("openId", openId);
        return miniUserService.selectOne(wrapper);
    }

    /**
     * 保存MiniUser
     *
     * @param miniUser
     * @return MiniUser
     */
    @PostMapping("user")
    public MiniUser saveMiniUser(@RequestBody MiniUser miniUser) {
        logger.info("--- 保存MiniUser {}", miniUser);
        miniUser.setCreatetime(new Date());
        miniUserService.insert(miniUser);
        return miniUser;
    }

    /**
     * 登录
     *
     * @param loginVO
     * @return
     */
    @PostMapping("login")
    public LoginSuccessVO<?> login(@RequestBody @Valid LoginVO loginVO) {
        logger.info("--- 登录 {}", loginVO);
        if (loginVO.getRole().equals(0)) {
            EntityWrapper<Student> entityWrapper = new EntityWrapper<>();
            Wrapper<Student> wrapper = entityWrapper.eq("student_num", loginVO.getAccount())
                    .eq("password", loginVO.getPassword())
                    .in("status", String.valueOf(ManagerStatus.OK.getCode()));
            Student student = studentService.selectOne(wrapper);
            Assert.notNull(student, " student is null");

            //班级id
            int cid = student.getCid();
            EntityWrapper<Course> entityWrapper2 = new EntityWrapper<>();
            Wrapper<Course> wrapper2 = entityWrapper2.eq("cid", cid)
                    .in("status", String.valueOf(ManagerStatus.OK.getCode()));
            List<Course> courses = courseService.selectList(wrapper2);
            LoginSuccessVO<CourseVO> loginSuccessVO = new LoginSuccessVO<>();
            loginSuccessVO.setItems(buildCourseVO(courses));
            loginSuccessVO.setUid(student.getId());
            loginSuccessVO.setSysRole(student.getRole());
            return loginSuccessVO;
        } else {
            EntityWrapper<Teacher> entityWrapper = new EntityWrapper<>();
            Wrapper<Teacher> wrapper = entityWrapper.eq("phone", loginVO.getAccount())
                    .eq("password", loginVO.getPassword())
                    .in("status", String.valueOf(ManagerStatus.OK.getCode()));
            Teacher teacher = teacherService.selectOne(wrapper);
            Assert.notNull(teacher, " teacher is null");

            //负责课程
            EntityWrapper<Course> entityWrapper2 = new EntityWrapper<>();
            Wrapper<Course> wrapper2 = entityWrapper2.eq("tid", teacher.getId())
                    .in("status", String.valueOf(ManagerStatus.OK.getCode()));
            List<Course> courses = courseService.selectList(wrapper2);

            //负责班级ids
            Set<Integer> cidSet = courses.stream().map(Course::getCid).collect(Collectors.toSet());
            LoginSuccessVO<Classes> loginSuccessVO = new LoginSuccessVO<>();

            if (!cidSet.isEmpty()) {
                //负责班级
                EntityWrapper<Classes> entityWrapper3 = new EntityWrapper<>();
                Wrapper<Classes> wrapper3 = entityWrapper3.in("id", cidSet)
                        .in("status", String.valueOf(ManagerStatus.OK.getCode()));
                List<Classes> classesList = classesService.selectList(wrapper3);
                loginSuccessVO.setItems(classesList);
            }
            loginSuccessVO.setUid(teacher.getId());
            return loginSuccessVO;
        }
    }

    /**
     * 教师根据班级id获得负责课程
     *
     * @param teacherId
     * @param classesId
     * @return
     */
    @GetMapping("/courses/{teacherId}/{classesId}")
    public List<CourseVO> getCourseList(@PathVariable int teacherId, @PathVariable int classesId) {
        EntityWrapper<Course> entityWrapper = new EntityWrapper<>();
        Wrapper<Course> wrapper = entityWrapper.eq("tid", teacherId)
                .eq("cid", classesId)
                .in("status", String.valueOf(ManagerStatus.OK.getCode()));
        List<Course> courses = courseService.selectList(wrapper);
        return buildCourseVO(courses);
    }

    private List<CourseVO> buildCourseVO(List<Course> courses){
        List<CourseVO> courseVOList = new LinkedList<>();
        courses.forEach(course -> {
            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(course, courseVO);
            Boolean canAttendance = canAttendance(course);
            courseVO.setAttendance(canAttendance);

            if(canAttendance){
                EntityWrapper<Attendence> entityWrapper1 = new EntityWrapper<>();
                Wrapper<Attendence> wrapper1 = entityWrapper1.eq("cid", course.getId())
                        .eq("end_time", getTodayEndTime(course.getEndTime()));
                Attendence attendence = attendenceService.selectOne(wrapper1);
                if (Objects.nonNull(attendence)) {
                    courseVO.setAttendancing(true);
                    courseVO.setType(attendence.getType());
                    courseVO.setAttendanceId(attendence.getId());
                }
            }
            courseVOList.add(courseVO);
        });
        return courseVOList;
    }

    /**
     * 生成考勤
     */
    @PutMapping("attendance/{courseId}/{type}")
    public AttendenceIdVO generateAttendance(@PathVariable int courseId, @PathVariable int type) {
        EntityWrapper<Course> entityWrapper = new EntityWrapper<>();
        Wrapper<Course> wrapper = entityWrapper.eq("id", courseId)
                .in("status", String.valueOf(ManagerStatus.OK.getCode()));
        Course course = courseService.selectOne(wrapper);
        Assert.notNull(course, "course is null");

        Date todayEndTime = getTodayEndTime(course.getEndTime());
        EntityWrapper<Attendence> entityWrapper1 = new EntityWrapper<>();
        Wrapper<Attendence> wrapper1 = entityWrapper1.eq("cid", courseId)
                .eq("end_time", todayEndTime);
        Attendence attendence = attendenceService.selectOne(wrapper1);

        AttendenceIdVO attendenceIdVO = new AttendenceIdVO();
        if (Objects.isNull(attendence)) {
            attendence = new Attendence();
            attendence.setCreateTime(new Date());
            attendence.setCid(courseId);
            attendence.setType(type);
            attendence.setEndTime(todayEndTime);
            attendenceService.insert(attendence);
            int aid = attendence.getId();

            //班级id
            int cid = course.getCid();
            EntityWrapper<Student> entityWrapper2 = new EntityWrapper<>();
            Wrapper<Student> wrapper2 = entityWrapper2.eq("cid", cid)
                    .in("status", String.valueOf(ManagerStatus.OK.getCode()));
            List<Student> students = studentService.selectList(wrapper2);
            students.forEach(student -> {
                AttendenceDetail attendenceDetail = new AttendenceDetail();
                attendenceDetail.setAid(aid);
                attendenceDetail.setPunch(false);
                attendenceDetail.setSid(student.getId());
                attendenceDetailService.insert(attendenceDetail);
            });
        }

        attendenceIdVO.setAid(attendence.getId());
        return attendenceIdVO;
    }

    /**
     * 查看考勤
     */
    @GetMapping("attendance/{attendanceId}")
    public List<AttendenceDetailVO> lookAttendance(@PathVariable int attendanceId) {
        return buildAttendenceDetail(attendanceId);
    }

    private List<AttendenceDetailVO> buildAttendenceDetail(int attendanceId) {
        EntityWrapper<AttendenceDetail> entityWrapper = new EntityWrapper<>();
        Wrapper<AttendenceDetail> wrapper = entityWrapper.eq("aid", attendanceId);
        List<AttendenceDetail> attendenceDetails = attendenceDetailService.selectList(wrapper);
        List<AttendenceDetailVO> detailVOList = new LinkedList<>();
        attendenceDetails.forEach((AttendenceDetail attendenceDetail) -> {
            AttendenceDetailVO attendenceDetailVO = new AttendenceDetailVO();
            BeanUtils.copyProperties(attendenceDetail, attendenceDetailVO);

            EntityWrapper<Student> entityWrapper1 = new EntityWrapper<>();
            Wrapper<Student> wrapper1 = entityWrapper1.eq("id", attendenceDetail.getSid());
            Student student = studentService.selectOne(wrapper1);
            attendenceDetailVO.setPhone(student.getPhone());
            attendenceDetailVO.setStudentNum(student.getStudentNum());
            attendenceDetailVO.setStudentName(student.getName());
            attendenceDetailVO.setRole(student.getRole());

            detailVOList.add(attendenceDetailVO);
        });
        return detailVOList;
    }

    /**
     * 签到
     */
    @PutMapping("attendance/detail/{id}")
    public List<AttendenceDetailVO> attendance(@PathVariable int id) {
        EntityWrapper<AttendenceDetail> entityWrapper = new EntityWrapper<>();
        Wrapper<AttendenceDetail> wrapper = entityWrapper.eq("id", id);
        AttendenceDetail attendenceDetail = attendenceDetailService.selectOne(wrapper);
        if (!attendenceDetail.getPunch()) {
            attendenceDetail.setPunch(true);
            attendenceDetail.setPunchTime(new Date());
            attendenceDetailService.updateById(attendenceDetail);
        }
        return buildAttendenceDetail(attendenceDetail.getAid());
    }

    /**
     * 获得今日的考勤结束时间
     */
    private Date getTodayEndTime(Date endTime) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date todayEndTime = new Date();
        try {
            String strEndTime = df.format(endTime);
            df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(new Date());
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            todayEndTime = df.parse(date + " " + strEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return todayEndTime;
    }

    /**
     * 判断是否可以考勤
     *
     * @param course
     * @return
     */
    private Boolean canAttendance(Course course) {
        long currentTimeMillis = System.currentTimeMillis();
        if (course.getStartDate().getTime() < currentTimeMillis && currentTimeMillis < course.getEndDate().getTime()) {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            if (Objects.equals(day, course.getWeekDay() + 1) ||
                    (day == 0 && course.getWeekDay() == 7)) {
                if (belongCalendar(format(new Date()), format(course.getStartTime()),
                        format(course.getEndTime()))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 格式化日期
     *
     * @param time
     * @return
     */
    public Date format(Date time) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        try {
            time = df.parse(df.format(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    //-------------------------------  @Deprecated -------------------------------//

    /**
     * 根据phone校验是否已被使用注册
     *
     * @param phone
     * @return
     */
    @GetMapping("user/phone/{phone}")
    public MiniUser checkPhone(@PathVariable String phone) {
        EntityWrapper<MiniUser> entityWrapper = new EntityWrapper<>();
        Wrapper<MiniUser> wrapper = entityWrapper.eq("phone", phone);
        return miniUserService.selectOne(wrapper);
    }

    /**
     * 保存注册手机号
     *
     * @param uid
     * @param phone
     */
    @PutMapping("user/{uid}/phone/{phone}")
    public void bindPhone(@PathVariable int uid, @PathVariable String phone) {
        MiniUser miniUser = miniUserService.selectById(uid);
        miniUser.setPhone(phone);
        miniUserService.updateById(miniUser);
    }

    /**
     * 获得所有省份信息
     *
     * @return
     */
    @GetMapping("provinces")
    public List<Province> getProvinces() {
        return provinceService.findList();
    }

    /**
     * 获得所有学校信息
     *
     * @return
     */
    @GetMapping("schools")
    public List<School> getSchools() {
        return schoolService.findList();
    }

    /**
     * 保存用户所选择的省份、学校和角色信息
     *
     * @param uid
     * @param vo
     */
    @PutMapping("province/school/{uid}")
    public void saveProvAndSchoolAndRole(@PathVariable int uid, @RequestBody ProvAndSchoolAndRoleVO vo) {
        MiniUser miniUser = miniUserService.selectById(uid);
        // pid and sid
        miniUser.setRole(vo.getRole());
        miniUserService.updateById(miniUser);
    }

    /**
     * 获得所有班级信息
     *
     * @return
     */
    @GetMapping("classes")
    public List<Classes> getClasses() {
        return classesService.findList();
    }

    /**
     * 保存用户所选择的班级、姓名、学号和性别信息
     *
     * @param uid
     * @param vo
     */
    @PutMapping("classes/user/{uid}")
    public void saveClassesAndUserInfo(@PathVariable int uid, @RequestBody ClassesAndUserInfoVO vo) {
        MiniUser miniUser = miniUserService.selectById(uid);
        miniUser.setStudentId(vo.getStudentId());
        miniUser.setGender(vo.getGender());
        miniUser.setRealName(vo.getRealName());
        //cids cid
        miniUserService.updateById(miniUser);
    }

    /**
     * 获得个人信息
     *
     * @return
     */
    @GetMapping("user/{uid}")
    public MiniUser getUserInfoByUid(@PathVariable int uid) {
        return miniUserService.selectById(uid);
    }


    /**
     * 学生或老师获得班级公告
     *
     * @param cid 班级id
     * @return
     */
    @GetMapping("{cid}/announcements")
    public List<Announcement> findAnnouncements(@PathVariable int cid) {
        EntityWrapper<Announcement> entityWrapper = new EntityWrapper<>();
        Wrapper<Announcement> wrapper = entityWrapper.eq("cid", cid)
                .notIn("status", ManagerStatus.DELETED.getCode());
        return announcementService.selectList(wrapper);
    }

    /**
     * 老师获得所有的班级信息
     *
     * @param uid
     * @return
     */
    @GetMapping("classes/{uid}")
    public List<Classes> findClassesByUid(@PathVariable int uid) {
        MiniUser miniUser = miniUserService.selectById(uid);
        EntityWrapper<Classes> entityWrapper = new EntityWrapper<>();
        String cid = miniUser.getCids();
        Wrapper<Classes> wrapper = entityWrapper.in("id", cid)
                .notIn("status", ManagerStatus.DELETED.getCode());
        return classesService.selectList(wrapper);
    }

}
