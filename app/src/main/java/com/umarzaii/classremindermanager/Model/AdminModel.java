package com.umarzaii.classremindermanager.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class AdminModel {

    public String adminID;
    public String adminEmail;
    public String adminName;

    public AdminModel() {

    }

    public AdminModel(String adminID, String adminEmail, String adminName) {
        this.adminID = adminID;
        this.adminEmail = adminEmail;
        this.adminName = adminName;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("adminID", adminID);
        result.put("adminEmail", adminEmail);
        result.put("adminName", adminName);

        return result;
    }

}
