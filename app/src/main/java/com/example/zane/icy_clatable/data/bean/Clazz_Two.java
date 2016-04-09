package com.example.zane.icy_clatable.data.bean;

import java.util.List;

/**
 * Created by Zane on 16/4/9.
 */
public class Clazz_Two {

    /**
     * status : 200
     * message : 操作成功
     * data : [{"xqj":"2","djj":"3","skcd":"2","dsz":" ","qsz":"1","jsz":"14","week":"1-14周","kcmc":"手机应用程序开发","skls":"罗文龙","jsmc":"2314","range":[1,2,3,4,5,6,7,8,9,10,11,12,13,14]},{"xqj":"1","djj":"5","skcd":"2","dsz":" ","qsz":"1","jsz":"16","week":"1-16周","kcmc":"电子商务概论","skls":"卢华玲","jsmc":"2314","range":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]},{"xqj":"3","djj":"9","skcd":"2","dsz":" ","qsz":"4","jsz":"7","week":"4-7周","kcmc":"形势与政策","skls":"王贵喜","jsmc":"3111","range":[4,5,6,7]},{"xqj":"2","djj":"7","skcd":"2","dsz":" ","qsz":"1","jsz":"11","week":"1-11周","kcmc":"中国近现代史纲要","skls":"罗贤","jsmc":"2105","range":[1,2,3,4,5,6,7,8,9,10,11]},{"xqj":"1","djj":"1","skcd":"2","dsz":" ","qsz":"1","jsz":"15","week":"1-15周","kcmc":"数据结构","skls":"刘友军 ","jsmc":"2314","range":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]},{"xqj":"3","djj":"1","skcd":"2","dsz":"2","qsz":"2","jsz":"18","week":"2-18周","kcmc":"统计学","skls":"卢安文","jsmc":"2314","range":[2,4,6,8,10,12,14,16,18]},{"xqj":"3","djj":"1","skcd":"2","dsz":"1","qsz":"1","jsz":"15","week":"1-15周","kcmc":"计算机系统结构与系统软件","skls":"李昌兵 ","jsmc":"2314","range":[1,3,5,7,9,11,13,15]},{"xqj":"4","djj":"3","skcd":"2","dsz":" ","qsz":"1","jsz":"14","week":"1-14周","kcmc":"数据结构","skls":"刘友军 ","jsmc":"2314","range":[1,2,3,4,5,6,7,8,9,10,11,12,13,14]},{"xqj":"5","djj":"1","skcd":"2","dsz":" ","qsz":"1","jsz":"16","week":"1-16周","kcmc":"统计学","skls":"卢安文","jsmc":"2314","range":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]},{"xqj":"4","djj":"7","skcd":"2","dsz":"1","qsz":"1","jsz":"13","week":"1-13周","kcmc":"手机应用程序开发","skls":"罗文龙","jsmc":"2314","range":[1,3,5,7,9,11,13]},{"xqj":"5","djj":"5","skcd":"2","dsz":" ","qsz":"1","jsz":"16","week":"1-16周","kcmc":"计算机系统结构与系统软件","skls":"李昌兵 ","jsmc":"2314","range":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]},{"xqj":"3","djj":"9","skcd":"2","dsz":" ","qsz":"1","jsz":"3","week":"1-3周","kcmc":"健康教育2","skls":"曾靖婷","jsmc":"2115","range":[1,2,3]}]
     */

    private int status;
    private String message;
    /**
     * xqj : 2
     * djj : 3
     * skcd : 2
     * dsz :
     * qsz : 1
     * jsz : 14
     * week : 1-14周
     * kcmc : 手机应用程序开发
     * skls : 罗文龙
     * jsmc : 2314
     * range : [1,2,3,4,5,6,7,8,9,10,11,12,13,14]
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

    public static class DataEntity {
        private String xqj;
        private String djj;
        private String skcd;
        private String dsz;
        private String qsz;
        private String jsz;
        private String week;
        private String kcmc;
        private String skls;
        private String jsmc;
        private List<Integer> range;

        public String getXqj() {
            return xqj;
        }

        public void setXqj(String xqj) {
            this.xqj = xqj;
        }

        public String getDjj() {
            return djj;
        }

        public void setDjj(String djj) {
            this.djj = djj;
        }

        public String getSkcd() {
            return skcd;
        }

        public void setSkcd(String skcd) {
            this.skcd = skcd;
        }

        public String getDsz() {
            return dsz;
        }

        public void setDsz(String dsz) {
            this.dsz = dsz;
        }

        public String getQsz() {
            return qsz;
        }

        public void setQsz(String qsz) {
            this.qsz = qsz;
        }

        public String getJsz() {
            return jsz;
        }

        public void setJsz(String jsz) {
            this.jsz = jsz;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getKcmc() {
            return kcmc;
        }

        public void setKcmc(String kcmc) {
            this.kcmc = kcmc;
        }

        public String getSkls() {
            return skls;
        }

        public void setSkls(String skls) {
            this.skls = skls;
        }

        public String getJsmc() {
            return jsmc;
        }

        public void setJsmc(String jsmc) {
            this.jsmc = jsmc;
        }

        public List<Integer> getRange() {
            return range;
        }

        public void setRange(List<Integer> range) {
            this.range = range;
        }
    }
}
