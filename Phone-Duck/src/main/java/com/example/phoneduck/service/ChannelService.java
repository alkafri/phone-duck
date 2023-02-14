package com.example.phoneduck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.phoneduck.model.Channel;
import com.example.phoneduck.repository.JpaChannelRepository;

@Service
public class ChannelService {
    @Autowired
    private JpaChannelRepository channelRepository;
    public Channel get(int id) {
        return channelRepository.getReferenceById(id);
    }
    public List<Channel> getAll() {
        return channelRepository.findAll();
    }
    public Channel save(Channel channel) {
        return channelRepository.save(channel);
    }
    public void delete(int channelId) {channelRepository.deleteById(channelId);}
}
