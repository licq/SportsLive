package com.licq.sportslive;

import androidx.annotation.NonNull;

public enum Status {
    NOT_STARTED("未开始"),
    IN_PROGRESS("进行中"),
    ENDED("结束");


    Status(String text) {
        this.text = text;
    }

    private String text;

    public static Status from(String text) {
        for (Status status : Status.values()) {
            if (status.text.equals(text)) {
                return status;
            }
        }
        if (text.equals("直播")) {
            return IN_PROGRESS;
        }
        if (text.equals("")) {
            return NOT_STARTED;
        }
        throw new IllegalArgumentException("No such status: " + text);
    }

    @NonNull
    @Override
    public String toString() {
        return this.text;
    }
}
