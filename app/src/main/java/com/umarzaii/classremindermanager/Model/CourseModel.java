package com.umarzaii.classremindermanager.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class CourseModel {

    public String courseID;
    public String courseName;

    public CourseModel() {

    }

    public CourseModel(String courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }

    @Exclude
    public Map<String, Object> detailsToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("courseID", courseID);
        result.put("courseName", courseName);

        return result;
    }

}
