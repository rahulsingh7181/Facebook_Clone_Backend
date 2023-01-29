package com.rahul.social.utility;

public class GeneralUtility {

    public static String concat(Object... strings){
        StringBuilder builder = new StringBuilder();
        for(Object str : strings){
            builder.append(String.valueOf(str));
        }
        return builder.toString();
    }
}
