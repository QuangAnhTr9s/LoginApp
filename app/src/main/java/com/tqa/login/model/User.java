package com.tqa.login.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String mHoTen;
    private String mTaiKhoan;
    private String mMatKhau;


    public User(String mTaiKhoan, String mMatKhau) {
        this.mTaiKhoan = mTaiKhoan;
        this.mMatKhau = mMatKhau;
    }

    public User(String mHoTen, String mTaiKhoan, String mMatKhau) {
        this.mHoTen = mHoTen;
        this.mTaiKhoan = mTaiKhoan;
        this.mMatKhau = mMatKhau;
    }

    public String getmHoTen() {
        return mHoTen;
    }

    public void setmHoTen(String mHoTen) {
        this.mHoTen = mHoTen;
    }

    public String getmTaiKhoan() {
        return mTaiKhoan;
    }

    public void setmTaiKhoan(String mTaiKhoan) {
        this.mTaiKhoan = mTaiKhoan;
    }

    public String getmMatKhau() {
        return mMatKhau;
    }

    public void setmMatKhau(String mMatKhau) {
        this.mMatKhau = mMatKhau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(mTaiKhoan, user.mTaiKhoan);
    }

}
