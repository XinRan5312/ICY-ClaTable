package com.example.zane.icy_clatable.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zane on 16/4/9.
 */
public class Clazz_Two implements Serializable{
    /**
     * status : 200
     * message : 操作成功
     * data : [{"zc":"11100000000000000000","sfsjlp":"false","course_name":"健康教育2","teacher":"曾靖婷","classroom":"2115","kclbdm":"必修","weekday":"3","during":"第9-10节","begin_class":"9","end_class":10,"begin_week":1,"end_week":3},{"zc":"10101010101010100000","sfsjlp":"false","course_name":"计算机系统结构与系统软件","teacher":"李昌兵  ","classroom":"2314","kclbdm":"必修","weekday":"3","during":"第1-2节","begin_class":"1","end_class":"2","begin_week":1,"end_week":15},{"zc":"11111101111111110000","sfsjlp":"false","course_name":"计算机系统结构与系统软件","teacher":"李昌兵  ","classroom":"2314","kclbdm":"必修","weekday":"5","during":"第5-6节","begin_class":"5","end_class":"6","begin_week":1,"end_week":16},{"zc":"11111111111111100000","sfsjlp":"false","course_name":"数据结构","teacher":"刘友军  ","classroom":"2314","kclbdm":"必修","weekday":"1","during":"第1-2节","begin_class":"1","end_class":"2","begin_week":1,"end_week":15},{"zc":"11111101111111000000","sfsjlp":"false","course_name":"数据结构","teacher":"刘友军  ","classroom":"2314","kclbdm":"必修","weekday":"4","during":"第3-4节","begin_class":"3","end_class":"4","begin_week":1,"end_week":14},{"zc":"11111111111111110000","sfsjlp":"false","course_name":"电子商务概论","teacher":"卢华玲","classroom":"2314","kclbdm":"限选","weekday":"1","during":"第5-6节","begin_class":"5","end_class":"6","begin_week":1,"end_week":16},{"zc":"01010101010101010100","sfsjlp":"false","course_name":"统计学","teacher":"卢安文","classroom":"2314","kclbdm":"必修","weekday":"3","during":"第1-2节","begin_class":"1","end_class":"2","begin_week":2,"end_week":18},{"zc":"11111101111111110000","sfsjlp":"false","course_name":"统计学","teacher":"卢安文","classroom":"2314","kclbdm":"必修","weekday":"5","during":"第1-2节","begin_class":"1","end_class":"2","begin_week":1,"end_week":16},{"zc":"11111111111111000000","sfsjlp":"false","course_name":"手机应用程序开发","teacher":"罗文龙","classroom":"2314","kclbdm":"限选","weekday":"2","during":"第3-4节","begin_class":"3","end_class":"4","begin_week":1,"end_week":14},{"zc":"10101000101010000000","sfsjlp":"false","course_name":"手机应用程序开发","teacher":"罗文龙","classroom":"2314","kclbdm":"限选","weekday":"4","during":"第7-8节","begin_class":"7","end_class":"8","begin_week":1,"end_week":13},{"zc":"01010101010101010000","sfsjlp":"false","course_name":"体育(俱乐部)","teacher":"教师1","classroom":"运动场1","kclbdm":"必修","weekday":"4","during":"第7-8节","begin_class":"7","end_class":"8","begin_week":2,"end_week":16},{"zc":"11111111000000000000","sfsjlp":"false","course_name":"现当代文学鉴赏","teacher":"张梦婕","classroom":"3104","kclbdm":"任选","weekday":"2","during":"第9-10节","begin_class":"9","end_class":10,"begin_week":1,"end_week":8},{"zc":"00000000000000001000","sfsjlp":"false","course_name":"专业统计软件应用","teacher":"袁野","classroom":"经管楼2508","kclbdm":"实践","weekday":"2","during":"第1-2节","begin_class":"1","end_class":"2","begin_week":17,"end_week":17},{"zc":"00000000000000001000","sfsjlp":"false","course_name":"专业统计软件应用","teacher":"袁野","classroom":"经管楼2508","kclbdm":"实践","weekday":"2","during":"第3-4节","begin_class":"3","end_class":"4","begin_week":17,"end_week":17},{"zc":"00000000000000001000","sfsjlp":"false","course_name":"专业统计软件应用","teacher":"袁野","classroom":"经管楼2508","kclbdm":"实践","weekday":"3","during":"第1-2节","begin_class":"1","end_class":"2","begin_week":17,"end_week":17},{"zc":"00000000000000001000","sfsjlp":"false","course_name":"专业统计软件应用","teacher":"袁野","classroom":"经管楼2508","kclbdm":"实践","weekday":"3","during":"第3-4节","begin_class":"3","end_class":"4","begin_week":17,"end_week":17},{"zc":"00000000000000001000","sfsjlp":"false","course_name":"专业统计软件应用","teacher":"袁野","classroom":"经管楼2508","kclbdm":"实践","weekday":"4","during":"第1-2节","begin_class":"1","end_class":"2","begin_week":17,"end_week":17},{"zc":"00000001111111100000","sfsjlp":"false","course_name":"数据结构","teacher":"刘友军  ","classroom":"经管楼2310","kclbdm":"实践","weekday":"3","during":"第9-10节","begin_class":"9","end_class":10,"begin_week":8,"end_week":15},{"zc":"00000011101000000000","sfsjlp":"false","course_name":"电子商务概论","teacher":"卢华玲","classroom":"经管楼2308","kclbdm":"实践","weekday":"1","during":"第3-4节","begin_class":"3","end_class":"4","begin_week":7,"end_week":11},{"zc":"00000011110111100000","sfsjlp":"false","course_name":"手机应用程序开发","teacher":"罗文龙","classroom":"经管楼2508","kclbdm":"实践","weekday":"2","during":"第5-6节","begin_class":"5","end_class":"6","begin_week":7,"end_week":15},{"zc":"00011001010100000000","sfsjlp":"false","course_name":"健康教育2","teacher":"曾靖婷","classroom":"教学楼2116","kclbdm":"实践","weekday":"3","during":"第11-12节","begin_class":11,"end_class":12,"begin_week":4,"end_week":12},{"zc":"00011110000000000000","sfsjlp":"false","course_name":"形势与政策","teacher":"王贵喜","classroom":"3111","kclbdm":"必修","weekday":"3","during":"第9-10节","begin_class":"9","end_class":10,"begin_week":4,"end_week":7},{"zc":"11101111111000000000","sfsjlp":"false","course_name":"中国近现代史纲要","teacher":"罗贤","classroom":"2105","kclbdm":"必修","weekday":"2","during":"第7-8节","begin_class":"7","end_class":"8","begin_week":1,"end_week":11},{"zc":"00000000000000001000","sfsjlp":"false","course_name":"专业统计软件应用","teacher":"袁野","classroom":"经管楼2508","kclbdm":"实践","weekday":"4","during":"第3-4节","begin_class":"3","end_class":"4","begin_week":17,"end_week":17},{"zc":"00000000000000001000","sfsjlp":"false","course_name":"专业统计软件应用","teacher":"袁野","classroom":"经管楼2508","kclbdm":"实践","weekday":"5","during":"第1-2节","begin_class":"1","end_class":"2","begin_week":17,"end_week":17},{"zc":"00000000000000001000","sfsjlp":"false","course_name":"专业统计软件应用","teacher":"袁野","classroom":"经管楼2508","kclbdm":"实践","weekday":"5","during":"第3-4节","begin_class":"3","end_class":"4","begin_week":17,"end_week":17}]
     */

    private int status;
    private String message;
    /**
     * zc : 11100000000000000000
     * sfsjlp : false
     * course_name : 健康教育2
     * teacher : 曾靖婷
     * classroom : 2115
     * kclbdm : 必修
     * weekday : 3
     * during : 第9-10节
     * begin_class : 9
     * end_class : 10
     * begin_week : 1
     * end_week : 3
     */

    private List<DataEntity> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable{
        private String zc;//课程数组
        private String sfsjlp;
        private String course_name;
        private String teacher;
        private String classroom;
        private String kclbdm;//课程类别
        private String weekday;
        private String during;
        private String begin_class;
        private String end_class;
        private int begin_week;
        private int end_week;

        public String getZc() {
            return zc;
        }

        public void setZc(String zc) {
            this.zc = zc;
        }

        public String getSfsjlp() {
            return sfsjlp;
        }

        public void setSfsjlp(String sfsjlp) {
            this.sfsjlp = sfsjlp;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getClassroom() {
            return classroom;
        }

        public void setClassroom(String classroom) {
            this.classroom = classroom;
        }

        public String getKclbdm() {
            return kclbdm;
        }

        public void setKclbdm(String kclbdm) {
            this.kclbdm = kclbdm;
        }

        public String getWeekday() {
            return weekday;
        }

        public void setWeekday(String weekday) {
            this.weekday = weekday;
        }

        public String getDuring() {
            return during;
        }

        public void setDuring(String during) {
            this.during = during;
        }

        public String getBegin_class() {
            return begin_class;
        }

        public void setBegin_class(String begin_class) {
            this.begin_class = begin_class;
        }

        public String getEnd_class() {
            return end_class;
        }

        public void setEnd_class(String end_class) {
            this.end_class = end_class;
        }

        public int getBegin_week() {
            return begin_week;
        }

        public void setBegin_week(int begin_week) {
            this.begin_week = begin_week;
        }

        public int getEnd_week() {
            return end_week;
        }

        public void setEnd_week(int end_week) {
            this.end_week = end_week;
        }
    }
}
