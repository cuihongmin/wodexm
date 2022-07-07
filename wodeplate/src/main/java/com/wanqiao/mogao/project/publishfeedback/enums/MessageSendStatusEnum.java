package com.wanqiao.mogao.project.publishfeedback.enums;

public enum MessageSendStatusEnum {

    fail("发送失败", 0), success("发送成功", 1), noSend("没有发送", 2);

    MessageSendStatusEnum(String message, int index) {
    }

}
