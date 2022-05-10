package com.example.sskdt_app_v01.model;

import java.util.Date;

public class User {
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
    private String image;
    private String password;

    public User(String name, Date birthday, boolean gender, String phone, String identification, String email, String city, String district, String ward, String address, String image, String password) {
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
        this.image = image;
        this.password = password;
    }

    public User(String name, Date birthday, boolean gender, String identification, String email, String city, String district, String ward, String address) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.identification = identification;
        this.email = email;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
    }

    public User(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
