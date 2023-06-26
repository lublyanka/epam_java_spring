package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.stream.Stream;

@Configuration
public class ChannelWithInjectedPrototypeVideoConfig {
    public static final LocalDateTime firstReleaseDate = LocalDateTime.of(2001, 3, 20, 10, 0);
    public static final Period PERIOD = Period.ofDays(1);
    private static final String name = "Cat Failure Compilation";
    private int counter = 0;


    @Bean("video")
    @Scope("prototype")
    public Video video() {
        return new Video(name, firstReleaseDate.plus((PERIOD.multipliedBy(counter++))));
    }

    @Bean("channel")
    public Channel channel() {

        class ChannelP extends Channel {

            @Override
            public Stream<Video> videos() {
                return Stream.iterate(0, i -> i + 1).map(x -> video());
            }
        }
        return new ChannelP();
    }
}
