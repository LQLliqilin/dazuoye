package com.example;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liqilin.databinding.FragmentPersonBinding;

public class PersonFragment extends Fragment {
    private FragmentPersonBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentPersonBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvAccount.setText(Contants.username);
        binding.llExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contants.username=null;
                ((Activity)requireContext()).finish();
            }
        });
    }
}