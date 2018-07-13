1.0. POST /authorize（小程序授权）

2.1. GET /code（获得手机验证码，校验是否已后台注册）
2.2. PUT /phone （根据手机验证码绑定手机）

3.1. GET /provinces
3.2. GET /schools
3.3. 选择角色：老师 or 班干部 or 普通学生

4.1 GET classes
4.2 填写：真实姓名
4.3 填写：性别
4.4 填写：学号（学生）

5.0. GET /info

6.0. GET /{cid}/announcements （学生直接获得班级的所有公告）
6.1. GET /classes （老师先获得班级列表）
6.2. GET /{cid}/announcements（老师通过选择班级获得对应班级的公告列表）

7.0. GET /courses （学生直接获得班级的所有课程）
7.1. GET /classes （老师先获得班级列表）
7.2. GET /{cid}/courses （老师通过选择班级获得对应班级的课程列表）

8.0 考勤
- 老师
    1. 指定考勤方式（老师考勤、班干考勤、学生考勤）
    2. 进行考勤
    3. 查看考勤结果

- 学生
    1. 进行考勤（学生考勤下）
    2. 查看考勤状态

9.0 UI
- 学生
    1. 课表
    2. 通知
    3. 我的

- 老师
    1. 考勤
    2. 通知
    3. 我的