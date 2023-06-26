package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Configuration
public class ChannelWithPhantomVideoStudioConfig {

    private int counter=1;

    @Bean("video")
    @Scope("prototype")
    public Video video() {
        String franchaseName = "Cat & Curious";
        Video video = new Video(franchaseName + " " + counter, LocalDateTime.of(2001, 10, 18, 10, 0).plusYears((counter - 1) * 2L));
        counter++;
        return video;
    }

    @Bean("channel")
    public Channel channel() {
        Channel channel = new Channel();
        for (int i = 0; i < 8; i++) {
            channel.addVideo(video());
        }
        return channel;
    }
}
