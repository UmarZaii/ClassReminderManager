package com.umarzaii.classremindermanager.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.umarzaii.classremindermanager.Activity.LoginActivity;
import com.umarzaii.classremindermanager.Handler.DatabaseHandler;
import com.umarzaii.classremindermanager.Handler.FragmentHandler;
import com.umarzaii.classremindermanager.R;

public class MainFragment extends Fragment {

    private FragmentHandler fragmentHandler;
    private DatabaseHandler databaseHandler;

    private Button btnLogOut, btnGoToAddCourse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragm_main,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Main Fragment");
        View v = getView();

        fragmentHandler = new FragmentHandler(getActivity().getSupportFragmentManager());
        databaseHandler = new DatabaseHandler();

        btnGoToAddCourse = (Button)v.findViewById(R.id.btnGoToAddCourse);
        btnLogOut = (Button)v.findViewById(R.id.btnLogOut);

        btnGoToAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentHandler.stackFragment(new AddCourseFragment(),"AddCourse");
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHandler.getFirebaseAuth().signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }

}
