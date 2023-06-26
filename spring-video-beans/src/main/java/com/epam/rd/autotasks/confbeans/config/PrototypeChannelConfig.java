package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@Import(SingletonChannelConfig.class)
public class PrototypeChannelConfig {

    @Autowired
    Video video1;
    @Autowired
    Video video2;
    @Autowired
    Video video3;

    @Bean
    @Scope("prototype")
    public Channel channel() {
        Channel channel = new Channel();
        channel.addVideo(video1);
        channel.addVideo(video2);
        channel.addVideo(video3);
        return channel;
    }
}
