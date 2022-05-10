package com.example.sskdt_app_v01.model;

import java.util.Date;

public class HealthDeclaration {
    private String userDocument;// userCreate
    private String user_decleared_document;//bỏ
    private Date create_at;
    private boolean ans_01;
    private boolean ans_02;
    private boolean ans_03;
    private boolean ans_04;
    private boolean ans_05;

    public HealthDeclaration(String userDocument, String user_decleared_document, Date create_at, boolean ans_01, boolean ans_02, boolean ans_03, boolean ans_04, boolean ans_05) {
        this.userDocument = userDocument;
        this.user_decleared_document = user_decleared_document;
        this.create_at = create_at;
        this.ans_01 = ans_01;
        this.ans_02 = ans_02;
        this.ans_03 = ans_03;
        this.ans_04 = ans_04;
        this.ans_05 = ans_05;
    }

    public String getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }

    public String getUser_decleared_document() {
        return user_decleared_document;
    }

    public void setUser_decleared_document(String user_decleared_document) {
        this.user_decleared_document = user_decleared_document;
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
