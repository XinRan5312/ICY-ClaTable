package com.example.zane.icy_clatable.data.bean;

/**
 * Created by Zane on 16/3/14.
 */
public class Clazz {

    /**
     * classname : 健康教育2
     * teachername : 陈敏
     * classroom : 3110
     * object : 必修
     * time : 1-3周
     * status : 选课状态:正常
     * other :
     */

    private String classname;
    private String teachername;
    private String classroom;
    private String object;
    private String time;
    private String status;
    private String other;

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getClassname() {
        return classname;
    }

    public String getTeachername() {
        return teachername;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getObject() {
        return object;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public String getOther() {
        return other;
    }
}
