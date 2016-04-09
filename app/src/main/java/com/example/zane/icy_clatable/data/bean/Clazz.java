package com.example.zane.icy_clatable.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zane on 16/3/14.
 * 建议你全部改成Parceable，因为我看内部类比较多就简便用这个序列化接口了。但是网络传输这块用Par更好。
 */
public class Clazz implements Serializable{

    @SerializedName("class")
    private List<ClassEntity> classX;

    public void setClassX(List<ClassEntity> classX) {
        this.classX = classX;
    }

    public List<ClassEntity> getClassX() {
        return classX;
    }


    public static class ClassEntity implements Serializable{
        /**
         * classname : 数据结构
         * teachername : 刘友军
         * classroom : 2314
         * object : 必修
         * time : 1-15周
         * status : 选课状态:正常
         * other :
         */

        private List<MutilpleEntity> mutilple;

        public void setMutilple(List<MutilpleEntity> mutilple) {
            this.mutilple = mutilple;
        }

        public List<MutilpleEntity> getMutilple() {
            return mutilple;
        }

        public static class MutilpleEntity implements Serializable{
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
    }
}
