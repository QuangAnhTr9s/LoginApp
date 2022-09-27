package com.tqa.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class FragmentLogin extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        //xoa du lieu da nhap khi back ve main
        EditText editTextTaiKhoan = requireActivity().findViewById(R.id.edt_tai_khoan);
        editTextTaiKhoan.setText("");
        EditText editTextMatKhau = requireActivity().findViewById(R.id.edt_mat_khau);
        editTextMatKhau.setText("");
        TextView textView = requireActivity().findViewById(R.id.txt_thong_bao_dang_nhap);
        textView.setText("");
        return view;
    }
}