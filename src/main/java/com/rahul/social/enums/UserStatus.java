package com.rahul.social.enums;

public enum UserStatus {
    PENDING(0),
    ACCEPTED(1),
    REJECTED(2);

    private int value;

    UserStatus(int value){
        this.value = value;
    }
}
