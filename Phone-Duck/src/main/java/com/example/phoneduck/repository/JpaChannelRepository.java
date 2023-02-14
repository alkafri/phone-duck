package com.example.phoneduck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.phoneduck.model.Channel;


@Repository("channelRepository")
public interface JpaChannelRepository extends JpaRepository<Channel, Integer> {
}
