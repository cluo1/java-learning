package cn.mariojd.mini.program.enums;

/**
 * 角色信息的枚举
 */
public enum RoleEnum {
    /**
     * 老师
     */
    TEACHER(1),

    /**
     * 学生：班干部
     */
    CLASS_LEADER(2),
    /**
     * 学生：普通学生
     */
    SIMPLE_STUDENT(3),;

    private int role;

    RoleEnum(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }
}
