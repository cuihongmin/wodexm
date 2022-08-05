package com.cui.thread.pool;

/**
 * @Descripttion
 * @Author cuihongmin
 * @Date 2022/8/4 11:26
 */
public class MyTask implements Runnable{
    private int taskId;
    private String taskName;

    public MyTask(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("runtaskId="+this.taskId + ",Thread:" + Thread.currentThread().getId() + ",runtaskName:" + this.taskName);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
