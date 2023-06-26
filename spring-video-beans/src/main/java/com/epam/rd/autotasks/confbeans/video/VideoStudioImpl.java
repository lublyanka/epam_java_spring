package com.epam.rd.autotasks.confbeans.video;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Period;

@Component
public class VideoStudioImpl implements VideoStudio{
    private final Period period;
    private final String franchasName;
    private int counter=0;
    private final LocalDateTime firstReleaseDate;

    public VideoStudioImpl(String franchaseName, Period period, LocalDateTime firstReleaseDate) {
        this.franchasName = franchaseName;
        this.period = period;
        this.firstReleaseDate = firstReleaseDate;
    }

    @Override
    public Video produce() {
        return new Video(franchasName + " " + ++counter, firstReleaseDate.plus((period.multipliedBy(counter - 1))));
    }
}
