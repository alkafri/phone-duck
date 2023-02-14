package com.example.phoneduck.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter @Getter
public class Channel implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String channelName;
    private boolean isOnline;

    @Override
    public Channel clone() {
        try {
            return (Channel) super.clone();
        } catch (CloneNotSupportedException e) {
            Channel channel = new Channel();

            channel.setId(this.getId());
            channel.setChannelName(this.getChannelName());
            channel.setOnline(this.isOnline());

            return channel;
        }
    }
}

