package com.tqa.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tqa.login.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView txt_thong_bao_dang_nhap;
    private EditText edt_tai_khoan, edt_mat_khau;
    private Button btn_dang_ky, btn_dang_nhap;
    private String checkTaiKhoan, checkMatKhau;
    private SharedPreferences sharedPreferences;
    private List<User> listUser = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWiget();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        //lay du lieu tu sharedPreferences ra list
        String stringListUser = sharedPreferences.getString("list_obj", null); //lay gia tri ra, neu k co thi gan la null
        Gson gson = new Gson();
        if (stringListUser != null) {
            Type type = new TypeToken<List<User>>() {
            }.getType();
            listUser = gson.fromJson(stringListUser, type);
        }
        receiveAndSaveDataFromFragmentSignUp();

        btn_dang_ky.setOnClickListener(v -> dangKyTaiKhoan());
        btn_dang_nhap.setOnClickListener(v -> dangNhap());
    }

    private void dangNhap() {
        checkTaiKhoan = edt_tai_khoan.getText().toString().trim();
        checkMatKhau = edt_mat_khau.getText().toString().trim();
        if (checkTaiKhoan.isEmpty() || checkMatKhau.isEmpty()) {
            txt_thong_bao_dang_nhap.setText("Bạn cần nhập tài khoản và mật khẩu!");
        } else {
            User userDangNhap = new User(checkTaiKhoan, checkMatKhau);
            if (checkUser(userDangNhap.getmTaiKhoan())) {
                if (checkPassUser(userDangNhap.getmMatKhau())) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.layout_main_activity, new FragmentLogin());
                    fragmentTransaction.addToBackStack("2");
                    fragmentTransaction.commit();
                } else {
                    txt_thong_bao_dang_nhap.setText("Bạn đã nhập sai tài khoản hoặc mật khẩu!");
                }
            } else {
                txt_thong_bao_dang_nhap.setText("Bạn đã nhập sai tài khoản hoặc mật khẩu!");
            }
        }
    }


    private void receiveAndSaveDataFromFragmentSignUp() {
        //nhan du lieu tu fragmentSignUp
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data_bundle");

        if (bundle != null) {
            User user = (User) bundle.getSerializable("data_user");
            if (checkUser(user.getmTaiKhoan())) {
                Toast.makeText(this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
            } else {
                listUser.add(user);
            }
        }

        //luu list vao sharedPreferences
        Gson gson = new Gson();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(listUser);
        editor.putString("list_obj", json);
        editor.apply();
//            editor.clear().commit();
    }


    private boolean checkUser(String getmTaiKhoan) {
        for (User userCheck : listUser) {
            if (userCheck.getmTaiKhoan().contains(getmTaiKhoan)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPassUser(String getmMatKhau) {
        for (User userCheck : listUser) {
            if (userCheck.getmMatKhau().contains(getmMatKhau)) {
                return true;
            }
        }
        return false;
    }

    private void dangKyTaiKhoan() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_main_activity, new FragmentSignUp());
        fragmentTransaction.addToBackStack("1");
        fragmentTransaction.commit();
    }

    private void setWiget() {
        edt_tai_khoan = findViewById(R.id.edt_tai_khoan);
        edt_mat_khau = findViewById(R.id.edt_mat_khau);
        btn_dang_ky = findViewById(R.id.btn_dang_ky);
        btn_dang_nhap = findViewById(R.id.btn_dang_nhap);
        txt_thong_bao_dang_nhap = findViewById(R.id.txt_thong_bao_dang_nhap);
    }

}