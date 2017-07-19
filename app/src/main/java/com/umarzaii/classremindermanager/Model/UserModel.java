package com.umarzaii.classremindermanager.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

import static com.umarzaii.classremindermanager.Handler.DatabaseHandler.uniAdminDepartment;
import static com.umarzaii.classremindermanager.Handler.DatabaseHandler.uniHeadDepartment;
import static com.umarzaii.classremindermanager.Handler.DatabaseHandler.uniLecturer;

@IgnoreExtraProperties
public class UserModel {

    public String userID;
    public String userEmail;
    public String userName;

    public String uniID;
    public String userType;
    public String employeeID;
    public String courseID;

    public UserModel() {

    }

    public UserModel(String userID, String userEmail, String userName) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public UserModel(String userID, String uniID, String userType, String employeeID, String courseID) {
        this.userID = userID;
        this.uniID = uniID;
        this.userType = userType;
        this.employeeID = employeeID;
        this.courseID = courseID;
    }

    @Exclude
    public Map<String, Object> userIDToMap() {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("userID", userID);

        HashMap<String, Object> result = new HashMap<>();
        result.put(userID, userMap);

        return result;
    }

    @Exclude
    public Map<String, Object> detailsToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userID", userID);
        result.put("userEmail", userEmail);
        result.put("userName", userName);

        return result;
    }

    @Exclude
    public Map<String, Object> credentialsToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uniID", uniID);
        result.put("userRole", userRoleToMap(userType));
        result.put("employeeID", employeeID);
        result.put("courseID", courseID);

        return result;
    }

    @Exclude
    public Map<String, Object> userRoleToMap(String userRole) {
        HashMap<String, Object> result = new HashMap<>();

        if (userRole.equals(uniAdminDepartment)) {

            result.put(uniAdminDepartment, userTypeToMap(uniAdminDepartment));
            result.put(uniLecturer, userTypeToMap(uniLecturer));

        } else if (userRole.equals(uniHeadDepartment)) {

            result.put(uniHeadDepartment, userTypeToMap(uniHeadDepartment));
            result.put(uniLecturer, userTypeToMap(uniLecturer));

        } else if (userRole.equals(uniLecturer)) {

            result.put(uniLecturer, uniLecturer);

        } else {

            //student

        }

        return result;
    }

    @Exclude
    public Map<String, Object> userTypeToMap(String userType) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userType", userType);

        return result;
    }

}
