package com.example.lab07;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


public class MyFragment extends Fragment {
    public static Fragment newInstance(int position) {
        MyFragment fragment = new MyFragment();

        // uses a Bundle to pass args
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    // creates the fragment/view
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    // once the fragment has been created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView myTextView = view.findViewById(R.id.text_field);
        int position = getArguments().getInt("position", 0);
        String[] quotes = getResources().getStringArray(R.array.quotes_array);
        myTextView.setText(quotes[position - 1]);
        super.onViewCreated(view, savedInstanceState);
    }
}