package com.basico91.inmobiliariatransversal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.basico91.inmobiliariatransversal.databinding.FragmentNavegaBinding;

public class NavegaFragment extends Fragment {
    private FragmentNavegaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
binding = FragmentNavegaBinding.inflate(inflater, container, false);
return binding.getRoot();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}
