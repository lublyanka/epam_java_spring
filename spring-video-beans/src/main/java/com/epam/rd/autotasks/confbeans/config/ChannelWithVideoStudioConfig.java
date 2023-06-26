package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;
import com.epam.rd.autotasks.confbeans.video.VideoStudioImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.time.LocalDateTime;
import java.time.Period;

@Configuration
public class ChannelWithVideoStudioConfig {

    @Bean("videoStudio")
    public VideoStudio videoStudio(){
        return new VideoStudioImpl("Cat & Curious", Period.ofYears(2), LocalDateTime.of(2001,10,18,10,0));
    }

    @Bean("channel")
    @DependsOn("videoStudio")
    public Channel channel(){
        Channel channel = new Channel();
        for (int i = 0; i < 8; i++) {
            channel.addVideo(videoStudio().produce());
        }
        return channel;
    }

}
