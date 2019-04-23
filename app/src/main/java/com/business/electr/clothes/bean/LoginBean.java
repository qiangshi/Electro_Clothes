package com.business.electr.clothes.bean;

import java.io.Serializable;

/**
 * Created by zenghaiqiang on 2018/12/21. 描述：
 */

public class LoginBean {


    /**
     * user : {"id":"7","phone":"15510018752","nickname":null,"portrait":null,"gender":"2","contact":null,"birthday":null,"country":null,"province":null,"city":null,"coins":"0","company_id":"0","join_company_id":"0"}
     * token : 698afc9003372634a0ea429131761266
     */

    private UserBean user;
    private String token;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserBean implements Serializable {

        /**
         * id : 7 phone : 15510018752 nickname : null portrait : null gender : 2 contact : null
         * birthday : null country : null province : null city : null coins : 0 company_id : 0
         * join_company_id : 0
         */
        private String id;
        private String phone;
        private String nickname;
        private String portrait;
        private String gender;
        private String contact;
        private String birthday;
        private String country;
        private String province;
        private String city;
        private String coins;
        private String company_id;
        private String join_company_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
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

        public String getCoins() {
            return coins;
        }

        public void setCoins(String coins) {
            this.coins = coins;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getJoin_company_id() {
            return join_company_id;
        }

        public void setJoin_company_id(String join_company_id) {
            this.join_company_id = join_company_id;
        }
    }
}
