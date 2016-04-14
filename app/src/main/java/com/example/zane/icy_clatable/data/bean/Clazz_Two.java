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
     * data : [{"weekday":"4","during":"第9-10节","begin_class":"9","end_class":"10","singel_or_double":" ","bengin_week":"1","end_week":"16","course_name":"数学建模","teacher":"数模组A","classrom":"3104"},{"weekday":"5","during":"第5-6节","begin_class":"5","end_class":"6","singel_or_double":"2","bengin_week":"2","end_week":"16","course_name":"数据库原理","teacher":"夏英","classrom":"2409"},{"weekday":"3","during":"第9-10节","begin_class":"9","end_class":"10","singel_or_double":" ","bengin_week":"3","end_week":"6","course_name":"形势与政策","teacher":"王小运","classrom":"2301"},{"weekday":"3","during":"第1-2节","begin_class":"1","end_class":"2","singel_or_double":" ","bengin_week":"1","end_week":"17","course_name":"数据库原理","teacher":"夏英","classrom":"2409"},{"weekday":"5","during":"第5-6节","begin_class":"5","end_class":"6","singel_or_double":"1","bengin_week":"1","end_week":"15","course_name":"计算机网络","teacher":"尚凤军  ","classrom":"2411"},{"weekday":"4","during":"第1-2节","begin_class":"1","end_class":"2","singel_or_double":" ","bengin_week":"1","end_week":"17","course_name":"面向对象程序设计－Java","teacher":"唐晓军","classrom":"S319"},{"weekday":"3","during":"第5-6节","begin_class":"5","end_class":"6","singel_or_double":" ","bengin_week":"1","end_week":"17","course_name":"计算机网络","teacher":"尚凤军  ","classrom":"2411"},{"weekday":"2","during":"第3-4节","begin_class":"3","end_class":"4","singel_or_double":" ","bengin_week":"1","end_week":"16","course_name":"操作系统","teacher":"熊安萍  ","classrom":"2105"},{"weekday":"1","during":"第9-10节","begin_class":"9","end_class":"10","singel_or_double":" ","bengin_week":"1","end_week":"3","course_name":"健康教育2","teacher":"严小燕","classrom":"3104"},{"weekday":"4","during":"第7-8节","begin_class":"7","end_class":"8","singel_or_double":" ","bengin_week":"1","end_week":"17","course_name":"英语演讲与辩论","teacher":"伍喆","classrom":"4303"},{"weekday":"4","during":"第3-4节","begin_class":"3","end_class":"4","singel_or_double":"2","bengin_week":"2","end_week":"16","course_name":"操作系统","teacher":"熊安萍  ","classrom":"2105"}]
     */

    private int status;
    private String message;
    /**
     * weekday : 4
     * during : 第9-10节
     * begin_class : 9
     * end_class : 10
     * singel_or_double :
     * bengin_week : 1
     * end_week : 16
     * course_name : 数学建模
     * teacher : 数模组A
     * classrom : 3104
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
        private String weekday;
        private String during;
        private String begin_class;
        private String end_class;
        private String singel_or_double;
        private String bengin_week;
        private String end_week;
        private String course_name;
        private String teacher;
        private String classrom;
        private String kinds;

        public String getKinds() {
            return kinds;
        }

        public void setKinds(String kinds) {
            this.kinds = kinds;
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

        public String getSingel_or_double() {
            return singel_or_double;
        }

        public void setSingel_or_double(String singel_or_double) {
            this.singel_or_double = singel_or_double;
        }

        public String getBengin_week() {
            return bengin_week;
        }

        public void setBengin_week(String bengin_week) {
            this.bengin_week = bengin_week;
        }

        public String getEnd_week() {
            return end_week;
        }

        public void setEnd_week(String end_week) {
            this.end_week = end_week;
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

        public String getClassrom() {
            return classrom;
        }

        public void setClassrom(String classrom) {
            this.classrom = classrom;
        }
    }
}
