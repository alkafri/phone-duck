package com.example.phoneduck;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.phoneduck.model.Channel;
import com.example.phoneduck.repository.JpaChannelRepository;

@SpringBootApplication
public class PhoneDuckApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneDuckApplication.class, args);
    }


    @Bean
    public CommandLineRunner run(JpaChannelRepository channelRepository) {
        return args -> {
            Channel channel = new Channel();
            channel.setChannelName("MainChannel");
            channel.setOnline(true);
            System.out.println("******** Main Channel Open for Chat ********");

            channelRepository.save(channel);
        };
    }
}
