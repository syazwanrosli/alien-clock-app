package com.assessment.alien_clock;

public class AlarmResponse {
    private String message;

    public AlarmResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
