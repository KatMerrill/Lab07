package com.example.lab07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Fragment fragment1;
    Fragment[] fragmentList;
    FragmentManager myManager;
    Button show_button, add_button, replace_button;
    boolean panel_visible, panel_added;
    int next_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // booleans that store the current state for each fragment
        panel_visible = true;
        panel_added = true;
        next_pos = 0;

        // fragment 1 = show/hide
//        fragment1 = MyFragment.newInstance(1);

        // sets up for fragment 1
        myManager = getSupportFragmentManager();
        FragmentTransaction ft = myManager.beginTransaction();
//        ft.add(R.id.placeholder1, fragment1, "FRAGMENT1");
//        ft.hide(fragment1);
//        ft.commit();

        // fragment 3 - replace
        fragmentList = new Fragment[3];
        for(int x = 0; x < 3; x++) {
            fragmentList[x] = MyFragment.newInstance(x + 1);
        }

        ft = myManager.beginTransaction();
        ft.add(R.id.transitions_layout, fragmentList[0], "CURRENT_FRAGMENT");
        ft.commit();

        replace_button = findViewById(R.id.button_3);
        replace_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if the panel is hidden, this doesn't do anything
                if(panel_visible) {
                    next_pos = (next_pos + 1) % fragmentList.length;

                    Fragment old_fragment = myManager.findFragmentByTag("CURRENT_FRAGMENT");
                    FragmentTransaction ft = myManager.beginTransaction();
                    ft.setCustomAnimations(R.anim.fade, R.anim.fade);
                    ft.replace(old_fragment.getId(), fragmentList[next_pos], "CURRENT_FRAGMENT");
                    ft.commit();
                }
            }
        });

        show_button = findViewById(R.id.button_1);
        show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment current_fragment = myManager.findFragmentByTag("CURRENT_FRAGMENT");

                FragmentTransaction ft = myManager.beginTransaction();
                if(panel_visible) {
                    ft.hide(current_fragment);
                    panel_visible = false;
                    show_button.setText("Show");
                }
                else {
                    ft.show(current_fragment);
                    panel_visible = true;
                    show_button.setText("Hide");
                }
                ft.commit();
            }
        });

//        // fragment 2 = add/remove
//        add_button = findViewById(R.id.button_2);
//        add_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction ft = myManager.beginTransaction();
//                if(panel_added) {
//                    ft.remove(current_fragment);
//                    // both versions commit after the if/else
//                    panel_added = false;
//                    add_button.setText("Add");
//                }
//                else {
//                    ft.add(current_fragment, MyFragment.newInstance(2), "FRAGMENT2");
//
//                    panel_added = true;
//                    add_button.setText("Remove");
//                }
//                ft.commit();
//            }
//        });
    }
}