package com.luwei.module.alipay.login.pojo;

public class User {

    //支付宝用户id
    private String userId;

    //用户头像
    private String avatar;

    //用户昵称
    private String nickname;

    //省份
    private String province;

    //城市
    private String city;

    //性别（M为男性，F为女性）
    private String gender;

    //用户类型（1代表公司账户，2代表个人账户 ）
    private String user_type;

    //用户状态（Q代表快速注册用户 T代表已认证用户 B代表被冻结账户 W代表已注册，未激活的账户 ）
    private String userStatus;

    //是否通过实名认证（T是通过 F是没有实名认证 ）
    private String isCertified;

    //是否是学生（T是学生 F不是学生）
    private String isStudentCertified;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(String isCertified) {
        this.isCertified = isCertified;
    }

    public String getIsStudentCertified() {
        return isStudentCertified;
    }

    public void setIsStudentCertified(String isStudentCertified) {
        this.isStudentCertified = isStudentCertified;
    }
}
