package com.umarzaii.classremindermanager.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

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

    public UserModel(String uniID, String userType, String employeeID, String courseID) {
        this.uniID = uniID;
        this.userType = userType;
        this.employeeID = employeeID;
        this.courseID = courseID;
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
        result.put("userType", userType);
        result.put("employeeID", employeeID);
        result.put("courseID", courseID);

        return result;
    }

}
