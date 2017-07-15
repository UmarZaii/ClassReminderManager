package com.umarzaii.classremindermanager.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class UniversityModel {

    public String uniAlias;
    public String uniName;
    public String uniID;

    public UniversityModel() {

    }

    public UniversityModel(String uniAlias, String uniName, String uniID) {
        this.uniAlias = uniAlias;
        this.uniName = uniName;
        this.uniID = uniID;
    }

    @Exclude
    public Map<String, Object> detailsToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uniAlias", uniAlias);
        result.put("uniName", uniName);
        result.put("uniID", uniID);

        return result;
    }

}
