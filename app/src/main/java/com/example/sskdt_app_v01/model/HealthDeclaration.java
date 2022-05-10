package com.example.sskdt_app_v01.model;

import java.util.Date;

public class HealthDeclaration {
    private String userCreate;
    private String name;
    private Date birthday;
    private boolean gender;
    private String phone;
    private String identification;
    private String email;
    private String city;
    private String district;
    private String ward;
    private String address;
    private Date create_at;
    private boolean ans_01;
    private boolean ans_02;
    private boolean ans_03;
    private boolean ans_04;
    private boolean ans_05;

    public HealthDeclaration(String userCreate, String name, Date birthday, boolean gender, String phone, String identification, String email, String city, String district, String ward, String address, Date create_at, boolean ans_01, boolean ans_02, boolean ans_03, boolean ans_04, boolean ans_05) {
        this.userCreate = userCreate;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.identification = identification;
        this.email = email;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.create_at = create_at;
        this.ans_01 = ans_01;
        this.ans_02 = ans_02;
        this.ans_03 = ans_03;
        this.ans_04 = ans_04;
        this.ans_05 = ans_05;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public boolean isAns_01() {
        return ans_01;
    }

    public void setAns_01(boolean ans_01) {
        this.ans_01 = ans_01;
    }

    public boolean isAns_02() {
        return ans_02;
    }

    public void setAns_02(boolean ans_02) {
        this.ans_02 = ans_02;
    }

    public boolean isAns_03() {
        return ans_03;
    }

    public void setAns_03(boolean ans_03) {
        this.ans_03 = ans_03;
    }

    public boolean isAns_04() {
        return ans_04;
    }

    public void setAns_04(boolean ans_04) {
        this.ans_04 = ans_04;
    }

    public boolean isAns_05() {
        return ans_05;
    }

    public void setAns_05(boolean ans_05) {
        this.ans_05 = ans_05;
    }
}
