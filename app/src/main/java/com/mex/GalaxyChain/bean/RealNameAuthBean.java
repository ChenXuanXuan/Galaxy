package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

public class RealNameAuthBean extends BaseBean {
/*
*  王皓
*
*
* */

    /**
     * data : {"idcard":"420683198702107813","realname":"哈哈","province":"湖北","city":"襄阳","town":"枣阳市","sex":"男","birth":"1987年02月10日","verifystatus":"1","verifymsg":"抱歉，身份证校验不一致！"}
     * code : 200
     * msg : 成功
     *
     *
     *
     * {"data":{"idcard":"420683198702107813","realname":"方明飞","province":"湖北","city":"襄阳","town":"枣阳市","sex":"男","birth":"1987年02月10日","verifystatus":"0","verifymsg":"恭喜您，身份证校验一致！"},"code":200,"msg":"成功"}
     *
     *
     */

    private DataBean data;
    private int code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * idcard : 420683198702107813
         * realname : 方明飞
         * province : 湖北
         * city : 襄阳
         * town : 枣阳市
         * sex : 男
         * birth : 1987年02月10日
         * verifystatus : 0
         * verifymsg :  身份证校验一致！
         */

        private String idcard;
        private String realname;
        private String province;
        private String city;
        private String town;
        private String sex;
        private String birth;
        private String verifystatus;
        private String verifymsg;

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
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

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getVerifystatus() {
            return verifystatus;
        }

        public void setVerifystatus(String verifystatus) {
            this.verifystatus = verifystatus;
        }

        public String getVerifymsg() {
            return verifymsg;
        }

        public void setVerifymsg(String verifymsg) {
            this.verifymsg = verifymsg;
        }
    }
}
