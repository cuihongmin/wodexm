package com.wanqiao.mogao.project.publishfeedback.enums;

/**
 * 报警方式预警
 */
public enum AlarmWayEnum {
    MESSAGE("短信", "665ea8169d6c4206b9bf3e96e915d441", 1l),
    EMAIL("邮箱", "3bad8139781d44b6875c4ea0da98a4e8", 2l);

    //名称
    private final String name;
    //编码
    private final String code;
    //id
    private final Long id;

    AlarmWayEnum(String name, String code, Long id) {
        this.name = name;
        this.code = code;
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getCode() {
        return code;
    }


    public Long getId() {
        return id;
    }



    public static void main(String[] args) {
        System.out.println(AlarmWayEnum.MESSAGE.name);
    }
}
