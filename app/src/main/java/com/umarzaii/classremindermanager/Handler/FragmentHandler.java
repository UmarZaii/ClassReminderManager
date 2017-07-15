package com.umarzaii.classremindermanager.Handler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.umarzaii.classremindermanager.R;

public class FragmentHandler {

    private FragmentManager fragmentManager;
    private Fragment currentFragment;

    public FragmentHandler(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void startFragment(Fragment fragmentClass) {
        currentFragment = fragmentClass;
        startTransaction();
    }

    public void startFragment(Fragment fragmentClass, Bundle bundle) {
        currentFragment = fragmentClass;
        currentFragment.setArguments(bundle);
        startTransaction();
    }

    public void stackFragment(Fragment fragmentClass, String stackName) {
        currentFragment = fragmentClass;
        startTransaction(stackName);
    }

    public void stackFragment(Fragment fragmentClass, Bundle bundle, String stackName) {
        currentFragment = fragmentClass;
        currentFragment.setArguments(bundle);
        startTransaction(stackName);
    }

    public void popBackStack(String stackName) {
        fragmentManager.popBackStack (stackName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void startTransaction() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_main, currentFragment);
        transaction.commit();
    }

    private void startTransaction(String stackName) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_main, currentFragment);
        transaction.addToBackStack(stackName);
        transaction.commit();
    }

}