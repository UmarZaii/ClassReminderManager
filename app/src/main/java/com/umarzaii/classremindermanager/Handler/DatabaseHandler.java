package com.umarzaii.classremindermanager.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DatabaseHandler {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private static boolean isPersistenceEnabled = false;

    private String userID;

    private String tblAdmin = "tblAdmin";
    private String tblUser = "tblUser";
    private String tblUniversity = "tblUniversity";
    private String tblCourse = "tblCourse";

    public static final String tblClassLocation = "tblClassLocation";
    public static final String tblUserClass = "tblUserClass";
    public static final String tblSubject = "tblSubject";

    public static final String courseID = "courseID";
    public static final String courseAdmin = "courseAdmin";
    public static final String uniAdminDepartment = "uniAdminDepartment";
    public static final String uniHeadDepartment = "uniHeadDepartment";
    public static final String uniLecturer = "uniLecturer";
    public static final String uniStudent = "uniStudent";
    public static final String credentials = "credentials";

    public static final String timeFrameDay = "timeFrameDay";
    public static final String timeFrameHour = "timeFrameHour";

    public static final String PSMZAID = "a2wx2pyFWJbRcOYWqkwwu7YwgRo2";

    public DatabaseHandler() {
        if (!isPersistenceEnabled) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            isPersistenceEnabled = true;
        }
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            userID = firebaseAuth.getCurrentUser().getUid();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }

    public DatabaseReference getTblAdmin() {
        return databaseReference.child(tblAdmin);
    }

    public DatabaseReference getTblUser() {
        return databaseReference.child(tblUser);
    }

    public DatabaseReference getTblUser(String userID) {
        return databaseReference.child(tblUser).child(userID);
    }

    public DatabaseReference getTblUserCredentials(String userID) {
        return databaseReference.child(tblUser).child(userID).child(credentials);
    }

    public DatabaseReference getTblUniversity(String uniID) {
        return databaseReference.child(tblUniversity).child(uniID);
    }

    public DatabaseReference getTblUniversityCourse(String uniID) {
        return databaseReference.child(tblUniversity).child(uniID).child(tblCourse);
    }

    public DatabaseReference getTblUniversityCourse(String uniID, String courseID) {
        return databaseReference.child(tblUniversity).child(uniID).child(tblCourse).child(courseID);
    }

    public DatabaseReference getTblUniversityCourseAdmin(String uniID, String courseID) {
        return databaseReference.child(tblUniversity).child(uniID).child(tblCourse).child(courseID).child(courseAdmin);
    }

    public String getUserID() {
        if (firebaseAuth.getCurrentUser() != null) {
            userID = firebaseAuth.getCurrentUser().getUid();
        }
        return userID;
    }

}
