package com.example.phoneduck.model;

import lombok.Getter;

@Getter
public class ChannelStateDetails {
    private Channel oldState;
    private Channel newState;

    public ChannelStateDetails(Channel oldState, Channel newState) {
        this.oldState = oldState.clone();
        this.newState = newState.clone();
    }
}

