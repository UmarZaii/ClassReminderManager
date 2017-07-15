package com.umarzaii.classremindermanager.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.umarzaii.classremindermanager.Handler.DatabaseHandler;
import com.umarzaii.classremindermanager.Handler.FragmentHandler;
import com.umarzaii.classremindermanager.R;

public class AddCourseFragment extends Fragment {

    private FragmentHandler fragmentHandler;
    private DatabaseHandler databaseHandler;

    private EditText edtCourseIDReg, edtCourseNameReg;
    private Button btnGoToAddUniAdmin;

    private String strCourseID, strCourseName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragm_addcourse,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Add Course");
        View v = getView();

        fragmentHandler = new FragmentHandler(getActivity().getSupportFragmentManager());
        databaseHandler = new DatabaseHandler();

        edtCourseIDReg = (EditText)v.findViewById(R.id.edtCourseIDReg);
        edtCourseNameReg = (EditText)v.findViewById(R.id.edtCourseNameReg);
        btnGoToAddUniAdmin = (Button) v.findViewById(R.id.btnGoToAddUniAdmin);

        btnGoToAddUniAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCourseID = edtCourseIDReg.getText().toString().trim();
                strCourseName = edtCourseNameReg.getText().toString().trim();

                if (inputCheck()) {
                    goToAddUniAdmin();
                }
            }
        });

    }

    private boolean inputCheck() {
        if (TextUtils.isEmpty(strCourseID)) {
            Toast.makeText(getActivity(), "Please input course id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strCourseName)) {
            Toast.makeText(getActivity(), "Please input course name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void goToAddUniAdmin() {
        Bundle bundle = new Bundle();
        bundle.putString("courseID", strCourseID);
        bundle.putString("courseName", strCourseName);

        fragmentHandler.stackFragment(new AddUniAdminFragment(), bundle,"AddUniAdmin");
    }
}
