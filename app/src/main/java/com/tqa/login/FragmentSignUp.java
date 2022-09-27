package com.tqa.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tqa.login.model.User;

public class FragmentSignUp extends Fragment {

    private Button btn_xac_nhan;
    private EditText edt_ho_ten_fragment, edt_tai_khoan_fragment, edt_mat_khau_fragment;
    private String mDataHoTen, mDataTaiKhoan, mDataMatKhau;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        setWigetFragment(view);
        btn_xac_nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToActivity();
            }
        });

        return view;
    }

    private void sendDataToActivity() {
        mDataHoTen = edt_ho_ten_fragment.getText().toString().trim();
        mDataTaiKhoan = edt_tai_khoan_fragment.getText().toString().trim();
        mDataMatKhau = edt_mat_khau_fragment.getText().toString().trim();
        if (TextUtils.isEmpty(mDataHoTen)) {
            Toast.makeText(getActivity(), "Bạn chưa nhập Họ và tên!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mDataTaiKhoan)) {
            Toast.makeText(getActivity(), "Bạn chưa nhập Tài khoản!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mDataMatKhau)) {
            Toast.makeText(getActivity(), "Bạn chưa nhập Mật khẩu!", Toast.LENGTH_SHORT).show();
        } else {
            User user = new User(mDataHoTen, mDataTaiKhoan, mDataMatKhau);
            Intent intent = new Intent(getActivity(), MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data_user", user);
            intent.putExtra("data_bundle", bundle);
            startActivity(intent);
        }

    }

    private void setWigetFragment(View view) {
        btn_xac_nhan = view.findViewById(R.id.btn_xac_nhan);
        edt_ho_ten_fragment = view.findViewById(R.id.edt_ho_ten_fragment);
        edt_tai_khoan_fragment = view.findViewById(R.id.edt_tai_khoan_fragment);
        edt_mat_khau_fragment = view.findViewById(R.id.edt_mat_khau_fragment);
    }
}