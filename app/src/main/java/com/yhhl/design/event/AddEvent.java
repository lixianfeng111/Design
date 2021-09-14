package com.yhhl.design.event;


import androidx.fragment.app.Fragment;

public class AddEvent {
    private Fragment needAdd;

    public AddEvent(Fragment needAdd) {
        this.needAdd = needAdd;
    }


    public Fragment getNeedAdd() {
        return needAdd;
    }

}
