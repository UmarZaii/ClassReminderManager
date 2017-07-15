package com.umarzaii.classremindermanager.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class CourseModel {

    public String courseID;
    public String courseName;
    public String employeeID;

    public CourseModel() {

    }

    public CourseModel(String courseID, String courseName, String employeeID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.employeeID = employeeID;
    }

    @Exclude
    public Map<String, Object> detailsToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("courseID", courseID);
        result.put("courseName", courseName);

        return result;
    }

    @Exclude
    public Map<String, Object> employeeToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("employeeID", employeeID);

        return result;
    }

}
