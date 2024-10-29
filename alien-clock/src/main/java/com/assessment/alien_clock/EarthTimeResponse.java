package com.assessment.alien_clock;

import java.time.LocalDateTime;

public class EarthTimeResponse {
    private LocalDateTime earthTime;

    public EarthTimeResponse(LocalDateTime earthTime) {
        this.earthTime = earthTime;
    }

    public LocalDateTime getEarthTime() {
        return earthTime;
    }

    public void setEarthTime(LocalDateTime earthTime) {
        this.earthTime = earthTime;
    }

}
