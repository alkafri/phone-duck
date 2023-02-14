package com.example.phoneduck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.phoneduck.model.Channel;
import com.example.phoneduck.service.ChannelService;
import com.example.phoneduck.ws.ChannelStateSocketHandlar;


@RestController
public class ChannelController {

    @Autowired
    private ChannelStateSocketHandlar channelStateSocketHandlar;

    @Autowired
    private ChannelService channelService;

    @GetMapping("channel/{channelId}")
    public ResponseEntity<Channel> getChannelById(@PathVariable int channelId) {
        return ResponseEntity.ok(channelService.get(channelId));
    }

    @GetMapping("channel")
    public ResponseEntity<List<Channel>> getAllChannels() {
        return ResponseEntity.ok(channelService.getAll());
    }



    @DeleteMapping("channel/{channelId}")
    public ResponseEntity<List<Channel>> deleteChannel(@PathVariable int channelId) {
        channelService.delete(channelId);
        return getAllChannels();
    }

    @PatchMapping("channel/online/{state}/{channelId}")
    public void setOnlineState(@PathVariable String state, @PathVariable int channelId) {
        Channel newChannel = channelService.get(channelId);
        Channel oldChannel = newChannel.clone();

        switch(state) {
            case "online": newChannel.setOnline(true); break;
            case "offline": newChannel.setOnline(false); break;
            default: throw new IllegalStateException(state + " was illdefined");
        }

        channelService.save(newChannel);

        if(newChannel.isOnline()) {
            channelStateSocketHandlar.broadcast("online", oldChannel, newChannel);
        } else {
            channelStateSocketHandlar.broadcast("offline", oldChannel, newChannel);
        }
    }

    @PostMapping("channel")
    @PatchMapping("channel")
    @PutMapping("channel")
    public ResponseEntity<List<Channel>> addChannel(@RequestBody Channel channel) {
        channelService.save(channel);
        channelStateSocketHandlar.broadcast("new-channel", channel.getChannelName() + " was created");
        return getAllChannels();
    }
}

