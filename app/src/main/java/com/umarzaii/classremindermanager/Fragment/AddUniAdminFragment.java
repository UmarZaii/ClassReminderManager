package com.umarzaii.classremindermanager.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.umarzaii.classremindermanager.Handler.DatabaseHandler;
import com.umarzaii.classremindermanager.Handler.FragmentHandler;
import com.umarzaii.classremindermanager.Model.CourseModel;
import com.umarzaii.classremindermanager.Model.UserModel;
import com.umarzaii.classremindermanager.R;

import java.util.HashMap;
import java.util.Map;

import static com.umarzaii.classremindermanager.Handler.DatabaseHandler.PSMZAID;
import static com.umarzaii.classremindermanager.Handler.DatabaseHandler.courseAdmin;
import static com.umarzaii.classremindermanager.Handler.DatabaseHandler.uniAdminDepartment;

public class AddUniAdminFragment extends Fragment {

    private FragmentHandler fragmentHandler;
    private DatabaseHandler databaseHandler;

    private EditText edtEmployeeID;
    private TextView txtUserID, txtUserName;
    private Button btnScanUniAdmin, btnAddUniAdmin;

    private String strCourseID, strCourseName, strUserID, strUserName, strEmployeeID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragm_adduniadmin,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Add Uni Admin");
        View v = getView();

        fragmentHandler = new FragmentHandler(getActivity().getSupportFragmentManager());
        databaseHandler = new DatabaseHandler();

        edtEmployeeID = (EditText)v.findViewById(R.id.edtEmployeeID);
        txtUserID = (TextView)v.findViewById(R.id.txtUserID);
        txtUserName = (TextView)v.findViewById(R.id.txtUserName);
        btnAddUniAdmin = (Button)v.findViewById(R.id.btnAddUniAdmin);
        btnScanUniAdmin = (Button)v.findViewById(R.id.btnScanUniAdmin);

        strCourseID = getArguments().getString("courseID");
        strCourseName = getArguments().getString("courseName");

        btnScanUniAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                Intent intent = integrator.createScanIntent();
                startActivityForResult(intent, integrator.REQUEST_CODE);
            }
        });

        btnAddUniAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmployeeID = edtEmployeeID.getText().toString().trim();

                if (inputCheck()) {
                    addUniAdmin();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null){
                Toast.makeText(getActivity(), "You cancelled the scanning", Toast.LENGTH_SHORT).show();
            } else {
                checkUserDetails(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void checkUserDetails(final String userID) {

        databaseHandler.getTblUser().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(userID)) {
                    getUserDetails(userID);
                } else {
                    Toast.makeText(getActivity(), "User Does Not Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getUserDetails(String userID) {

        databaseHandler.getTblUser(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                strUserID = userModel.userID;
                strUserName = userModel.userName;

                txtUserID.setText(strUserID);
                txtUserName.setText(strUserName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private boolean inputCheck() {
        if (strUserID == null) {
            Toast.makeText(getActivity(), "Please scan user first", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strEmployeeID)) {
            Toast.makeText(getActivity(), "Please input user employeeID", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void addUniAdmin() {

        final Map<String, Object> dataMapUser = new HashMap<String, Object>();
        final Map<String, Object> dataMapCourse = new HashMap<String, Object>();
        final Map<String, Object> dataMapCourseAdmin = new HashMap<String, Object>();

        UserModel userModel = new UserModel(strUserID,PSMZAID,uniAdminDepartment,strEmployeeID,strCourseID);
        CourseModel courseModel = new CourseModel(strCourseID,strCourseName);

        dataMapUser.put("credentials", userModel.credentialsToMap());
        databaseHandler.getTblUser(strUserID).updateChildren(dataMapUser);

        dataMapCourse.put(strCourseID, courseModel.detailsToMap());
        databaseHandler.getTblUniversityCourse(PSMZAID).updateChildren(dataMapCourse);

        dataMapCourseAdmin.put(courseAdmin, userModel.userIDToMap());
        databaseHandler.getTblUniversityCourse(PSMZAID,strCourseID).updateChildren(dataMapCourseAdmin);

        fragmentHandler.popBackStack("AddUniAdmin");
        fragmentHandler.popBackStack("AddCourse");

    }

}
